package edu.miu.cs590.hoteldetailsservice.mapper;

import edu.miu.cs590.hoteldetailsservice.dto.HotelResponseDto;
import edu.miu.cs590.hoteldetailsservice.dto.HotelRequestDto;
import edu.miu.cs590.hoteldetailsservice.model.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface HotelMapper {
    @Mappings({
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "address", source = "address"),
            @Mapping(target = "userId", source = "userId"),
            @Mapping(target = "feature", source = "feature"),
            @Mapping(target = "amenity", source = "amenity"),
            @Mapping(target = "location", source = "location"),
            @Mapping(target = "cancellationPolicy", source = "cancellationPolicy"),
    })
    Hotel toEntity(HotelRequestDto hotelRequestDto);

//    @Mappings({
////            @Mapping(target = "id", source = "id"),x/
//            @Mapping(target = "name", source = "name"),
//            @Mapping(target = "description", source = "description"),
//            @Mapping(target = "address", source = "address"),
//            @Mapping(target = "imageName", source = "image"),
//            @Mapping(target = "userId", source = "userId"),
//            @Mapping(target = "feature", source = "feature"),
//            @Mapping(target = "amenity", source = "amenity"),
//            @Mapping(target = "cancellationPolicy", source = "cancellationPolicy"),
//    })
    HotelResponseDto toDto(Hotel hotel);

}
