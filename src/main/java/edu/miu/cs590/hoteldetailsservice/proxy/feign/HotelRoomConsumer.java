package edu.miu.cs590.hoteldetailsservice.proxy.feign;

import edu.miu.cs590.hoteldetailsservice.dto.RoomResponseDto;
import edu.miu.cs590.hoteldetailsservice.proxy.config.HotelRoomFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="HOTEL-ROOM-SERVICE", url = "http://localhost:8081/hotel-room", configuration = HotelRoomFeignConfig.class)
public interface HotelRoomConsumer {
    @GetMapping("/hotel/{hotelId}")
    public List<RoomResponseDto> getHotelRooms(@PathVariable("hotelId") String hotelId);
}
