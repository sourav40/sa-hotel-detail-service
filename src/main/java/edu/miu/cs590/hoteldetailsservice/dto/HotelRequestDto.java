package edu.miu.cs590.hoteldetailsservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@NoArgsConstructor
public class HotelRequestDto {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private String address;
    @NotBlank
    private String location;
    private MultipartFile file;
//    @UUIDValidation
    private UUID userId;
    private String feature;
    private String amenity;
    private String cancellationPolicy;
}
