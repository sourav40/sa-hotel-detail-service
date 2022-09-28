package edu.miu.cs590.hoteldetailsservice.service.hotel;

import edu.miu.cs590.hoteldetailsservice.controller.HotelController;
import edu.miu.cs590.hoteldetailsservice.dto.HotelRequestDto;
import edu.miu.cs590.hoteldetailsservice.dto.HotelResponseDto;
import edu.miu.cs590.hoteldetailsservice.dto.HotelRoomResponseDto;
import edu.miu.cs590.hoteldetailsservice.mapper.HotelMapper;
import edu.miu.cs590.hoteldetailsservice.model.Hotel;
import edu.miu.cs590.hoteldetailsservice.repository.HotelRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class HotelServiceImplTest {

    private MockMvc mockMvc;

    @Mock
    HotelMapper hotelMapper;

    @Mock
    private HotelRepository hotelRepository;

    @InjectMocks
    private HotelServiceImpl hotelService;

    List<Hotel> hotels = new ArrayList<>();

    @Test
    void getAllHotels() {
        hotels.add(Hotel.builder().id(UUID.fromString("759f6bf2-4771-42da-83b3-7d6c89ae8834")).name("Hotel Annapurna").imageName(null).description("Located at Fairfield").address("1000th N St, Fairfield, Iowa").location("Fairfield").feature("Features").cancellationPolicy("Cancellation Policy").amenity("Swimming Poll").build());
        hotels.add(Hotel.builder().id(UUID.fromString("759f6bf2-4771-42da-83b3-7d6c89ae8834")).name("Hotel Prakash").description("Located at Chicago").address("1000th N St, Chicago, Illions").location("Chicago").feature("Features Chicago").cancellationPolicy("Cancellation Policy").amenity("Swimming Poll").build());

        when(hotelRepository.findAll()).thenReturn(hotels);
        List<HotelResponseDto> _hotels = hotelService.getAllHotels();
        assertEquals(2, _hotels.size());
    }

    @Test
    void getHotelById() {
        Hotel hotel = Hotel.builder().id(UUID.fromString("759f6bf2-4771-42da-83b3-7d6c89ae8834")).name("Hotel Annapurna").imageName(null).description("Located at Fairfield").address("1000th N St, Fairfield, Iowa").location(null).feature("Features").cancellationPolicy("Cancellation Policy").amenity("Swimming Poll").build();
//        Hotel Hotel.builder().id(UUID.fromString("759f6bf2-4771-42da-83b3-7d6c89ae8835")).name("Hotel Annapurna").imageName(null).description("Located at Fairfield").address("1000th N St, Fairfield, Iowa").location("Fairfield").feature("Features").cancellationPolicy("Cancellation Policy").amenity("Swimming Poll").build());


        UUID id = UUID.fromString("759f6bf2-4771-42da-83b3-7d6c89ae8834");
        Mockito.when(hotelRepository.findById(id)).thenReturn(Optional.of(hotel));
        HotelRoomResponseDto hotelRoomResponseDto = new HotelRoomResponseDto();
        hotelRoomResponseDto.setHotel(hotel);
        hotelRoomResponseDto.setRooms(null);
        assertEquals(hotelRoomResponseDto,hotelService.getHotelById(id));
    }

    @Test
    void addHotel() {
        Hotel hotel = Hotel.builder().id(UUID.fromString("759f6bf2-4771-42da-83b3-7d6c89ae8834")).name("Hotel Annapurna").imageName(null).description("Located at Fairfield").address("1000th N St, Fairfield, Iowa").location(null).feature("Features").cancellationPolicy("Cancellation Policy").amenity("Swimming Poll").build();


        HotelRequestDto hotelRequestDto = new HotelRequestDto();
        hotelRequestDto.setAddress(hotel.getAddress());
        hotelRequestDto.setName(hotel.getName());
        hotelRequestDto.setAmenity(hotel.getAmenity());
        hotelRequestDto.setDescription(hotel.getDescription());
        hotelRequestDto.setFeature(hotel.getFeature());
//        hotelRequestDto.setLocation(hotel.getLocation());
        hotelRequestDto.setUserId(hotel.getUserId());
        hotelRequestDto.setCancellationPolicy(hotel.getCancellationPolicy());
        hotelRequestDto.setLocation(hotel.getLocation());

//        Hotel hotel1 = hotelService.addHotel(hotelRequestDto);
        when(hotelRepository.save(hotel)).thenReturn(hotel);
        assertEquals(hotel, hotelRepository.save(hotel));
    }

    @Test
    void updateHotel() {
        Hotel hotel = Hotel.builder().id(UUID.fromString("759f6bf2-4771-42da-83b3-7d6c89ae8834")).name("Hotel Annapurna").imageName(null).description("Located at Fairfield").address("1000th N St, Fairfield, Iowa").location(null).feature("Features").cancellationPolicy("Cancellation Policy").amenity("Swimming Poll").build();

        hotel.setLocation("London");

        when(hotelRepository.save(hotel)).thenReturn(hotel);
        assertEquals("London", hotel.getLocation());

    }

    @Test
    void deleteHotelById() {
        Hotel hotel = Hotel.builder().id(UUID.fromString("759f6bf2-4771-42da-83b3-7d6c89ae8834")).name("Hotel Annapurna").imageName(null).description("Located at Fairfield").address("1000th N St, Fairfield, Iowa").location(null).feature("Features").cancellationPolicy("Cancellation Policy").amenity("Swimming Poll").build();

        UUID id = UUID.fromString("759f6bf2-4771-42da-83b3-7d6c89ae8834");
        hotelRepository.deleteById(UUID.fromString("759f6bf2-4771-42da-83b3-7d6c89ae8834"));
        verify(hotelRepository, times(1)).deleteById(id);
    }

    @Test
    void getAllHotelsByLocation() {
        hotels.add(Hotel.builder().id(UUID.fromString("759f6bf2-4771-42da-83b3-7d6c89ae8834")).name("Hotel Annapurna").imageName(null).description("Located at Fairfield").address("1000th N St, Fairfield, Iowa").location("Fairfield").feature("Features").cancellationPolicy("Cancellation Policy").amenity("Swimming Poll").build());
        hotels.add(Hotel.builder().id(UUID.fromString("759f6bf2-4771-42da-83b3-7d6c89ae8835")).name("Hotel Annapurna").imageName(null).description("Located at Fairfield").address("1000th N St, Fairfield, Iowa").location("Fairfield").feature("Features").cancellationPolicy("Cancellation Policy").amenity("Swimming Poll").build());
        hotels.add(Hotel.builder().id(UUID.fromString("759f6bf2-4771-42da-83b3-7d6c89ae8836")).name("Hotel Annapurna").imageName(null).description("Located at Fairfield").address("1000th N St, Fairfield, Iowa").location("Fairfield").feature("Features").cancellationPolicy("Cancellation Policy").amenity("Swimming Poll").build());
        hotels.add(Hotel.builder().id(UUID.fromString("759f6bf2-4771-42da-83b3-7d6c89ae8837")).name("Hotel Annapurna").imageName(null).description("Located at Fairfield").address("1000th N St, Fairfield, Iowa").location("Fairfield").feature("Features").cancellationPolicy("Cancellation Policy").amenity("Swimming Poll").build());
        hotels.add(Hotel.builder().id(UUID.fromString("759f6bf2-4771-42da-83b3-7d6c89ae8838")).name("Hotel Annapurna").imageName(null).description("Located at Fairfield").address("1000th N St, Fairfield, Iowa").location("Fairfield").feature("Features").cancellationPolicy("Cancellation Policy").amenity("Swimming Poll").build());


        when(hotelRepository.findHotelByLocation("Fairfield")).thenReturn(hotels);
        List<HotelResponseDto> hotelLists = hotelService.getAllHotelsByLocation("Fairfield");
        assertEquals(5, hotelLists.size());


    }
}