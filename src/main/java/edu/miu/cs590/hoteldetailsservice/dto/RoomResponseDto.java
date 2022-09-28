package edu.miu.cs590.hoteldetailsservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class RoomResponseDto {
    private String hotelId;
    private String id;
    private Integer capacity;
    private String roomType;
    private Double roomPrice;
    private Double defaultRoomPrice;
    private String description;
    private Integer numberOfBeds;
    private String bedType;
    private byte[] images;
    private Date createdDate;
}
