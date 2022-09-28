package edu.miu.cs590.hoteldetailsservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelResponseDto {
    private UUID id;
    private String name;
    private String description;
    private String address;
    private String location;
    private String imageName;
    private String bucketName;
    private UUID userId;
    private String feature;
    private String amenity;
    private String cancellationPolicy;
    private String imageURL;
}
