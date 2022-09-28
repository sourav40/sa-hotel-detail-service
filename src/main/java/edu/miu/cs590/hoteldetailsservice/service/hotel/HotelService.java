package edu.miu.cs590.hoteldetailsservice.service.hotel;

import edu.miu.cs590.hoteldetailsservice.dto.HotelRequestDto;
import edu.miu.cs590.hoteldetailsservice.dto.HotelResponseDto;
import edu.miu.cs590.hoteldetailsservice.dto.HotelRoomResponseDto;
import edu.miu.cs590.hoteldetailsservice.model.Hotel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HotelService {
    public List<HotelResponseDto> getAllHotels();

    public HotelRoomResponseDto getHotelById(UUID id);

    public Hotel addHotel(HotelRequestDto hotelRequestDto);

    public Hotel updateHotel(UUID id, HotelRequestDto hotelRequestDto);

    public void deleteHotelById(UUID id);

    public List<HotelResponseDto> getAllHotelsByLocation(String location);
}
