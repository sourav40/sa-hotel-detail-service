package edu.miu.cs590.hoteldetailsservice.service.hotel;

import com.mongodb.MongoException;
import edu.miu.cs590.hoteldetailsservice.dto.HotelRequestDto;
import edu.miu.cs590.hoteldetailsservice.dto.HotelResponseDto;
import edu.miu.cs590.hoteldetailsservice.dto.HotelRoomResponseDto;
import edu.miu.cs590.hoteldetailsservice.mapper.HotelMapper;
import edu.miu.cs590.hoteldetailsservice.model.Hotel;
import edu.miu.cs590.hoteldetailsservice.proxy.feign.HotelRoomConsumer;
import edu.miu.cs590.hoteldetailsservice.repository.HotelRepository;
import edu.miu.cs590.hoteldetailsservice.service.minio.MinioService;
import org.checkerframework.checker.nullness.Opt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    MinioService minioService;

    @Autowired
    HotelRoomConsumer hotelRoomConsumer;

    @Value("${minio.bucket.name}")
    private String bucketName;

    @Autowired
    private HotelMapper hotelMapper;

    @Override
    public List<HotelResponseDto> getAllHotels() {
        List<Hotel> hotels = hotelRepository.findAll();
        return hotels.stream().map(hotel -> {
            HotelResponseDto hotelResponseDto = hotelMapper.toDto(hotel);
            if(hotel.getImageName() != null && hotel.getBucketName() != null) {
                hotelResponseDto.setImageURL(minioService.downloadImage(hotel.getImageName(), hotel.getBucketName()));
            }
            return hotelResponseDto;
        }).collect(Collectors.toList());
    }

    @Override
    public HotelRoomResponseDto getHotelById(UUID id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        HotelRoomResponseDto hotelRooms = new HotelRoomResponseDto();

        if (hotel.isPresent()) {
            Optional<Hotel> h = Optional.of(new Hotel());
            h.get().setId(hotel.get().getId());
            h.get().setName(hotel.get().getName());
            h.get().setDescription(hotel.get().getDescription());
            h.get().setFeature(hotel.get().getFeature());
            h.get().setAmenity(hotel.get().getAmenity());
            if(hotel.get().getImageName() != null && hotel.get().getBucketName() != null) {
                h.get().setImageURL(minioService.downloadImage(hotel.get().getImageName(), hotel.get().getBucketName()));
            }
            h.get().setCancellationPolicy(hotel.get().getCancellationPolicy());
            h.get().setAddress(hotel.get().getAddress());
            hotelRooms.setHotel(h.get());
            hotelRooms.setRooms(hotelRoomConsumer.getHotelRooms(hotel.get().getId().toString()));
            return hotelRooms;
        } else {
            throw new MongoException("Record not Found");
        }
    }

    @Override
    public Hotel addHotel(HotelRequestDto hotelRequestDto) {
        Hotel hotel = new Hotel();
        hotel.setName(hotelRequestDto.getName());
        hotel.setDescription(hotelRequestDto.getDescription());
        hotel.setAddress(hotelRequestDto.getAddress());
        hotel.setAmenity(hotelRequestDto.getAmenity());
        hotel.setFeature(hotelRequestDto.getFeature());
        hotel.setCancellationPolicy(hotelRequestDto.getCancellationPolicy());
        hotel.setUserId(hotelRequestDto.getUserId());
        hotel.setLocation(hotelRequestDto.getLocation());
        hotel.setBucketName(bucketName);
        if(hotelRequestDto.getFile() != null){
            String imageName = minioService.uploadFiles(hotelRequestDto.getFile());
            hotel.setImageName(imageName);
            hotel.setImageURL(minioService.downloadImage(imageName, bucketName));
        }

        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel updateHotel(UUID id, HotelRequestDto hotelRequestDto) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        if (hotel.isPresent()) {
            Hotel h = hotel.get();
            h.setName(hotelRequestDto.getName());
            h.setDescription(hotelRequestDto.getDescription());
            h.setAddress(hotelRequestDto.getAddress());
            h.setUserId(hotelRequestDto.getUserId());
            h.setAmenity(hotelRequestDto.getAmenity());
            h.setCancellationPolicy(hotelRequestDto.getCancellationPolicy());
            h.setFeature(hotelRequestDto.getFeature());
            h.setLocation(hotelRequestDto.getLocation());
            h.setBucketName(bucketName);
            if(hotelRequestDto.getFile() != null){
                String imageName = minioService.uploadFiles(hotelRequestDto.getFile());
                h.setImageName(imageName);
                h.setImageURL(minioService.downloadImage(imageName, bucketName));
            }

            return hotelRepository.save(h);
        } else {
            throw new MongoException("No data found");
        }

    }

    @Override
    public void deleteHotelById(UUID id) {
        hotelRepository.deleteById(id);
    }

    @Override
    public List<HotelResponseDto> getAllHotelsByLocation(String location) {
        List<Hotel> hotels = hotelRepository.findHotelByLocation(location);

        return hotels.stream().map(hotel -> {
            HotelResponseDto hotelResponseDto = hotelMapper.toDto(hotel);
            if (hotel.getImageName() != null && hotel.getBucketName() != null) {
                hotelResponseDto.setImageURL(minioService.downloadImage(hotel.getImageName(), hotel.getBucketName()));
            }
            return hotelResponseDto;
        }).collect(Collectors.toList());
    }
}
