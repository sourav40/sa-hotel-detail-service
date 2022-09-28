package edu.miu.cs590.hoteldetailsservice.dto;

import edu.miu.cs590.hoteldetailsservice.model.Hotel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class HotelRoomResponseDto {
    Hotel hotel;
    List<RoomResponseDto> rooms;

}
