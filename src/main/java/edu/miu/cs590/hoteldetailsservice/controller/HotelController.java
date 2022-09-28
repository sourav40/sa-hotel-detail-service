package edu.miu.cs590.hoteldetailsservice.controller;

import edu.miu.cs590.hoteldetailsservice.dto.HotelRequestDto;
import edu.miu.cs590.hoteldetailsservice.dto.HotelResponseDto;
import edu.miu.cs590.hoteldetailsservice.dto.HotelRoomResponseDto;
import edu.miu.cs590.hoteldetailsservice.model.Hotel;
import edu.miu.cs590.hoteldetailsservice.service.hotel.HotelService;
import edu.miu.cs590.hoteldetailsservice.util.DefaultResponse;
import edu.miu.cs590.hoteldetailsservice.util.ResponseUtility;
import org.simpleframework.xml.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/hotel")
@CrossOrigin("http://localhost:3000")
public class HotelController {
    @Autowired
    HotelService hotelService;

    @GetMapping("/ping")
    public String pingServer(){
        return "Ping server";
    }

    @GetMapping
    public ResponseEntity<DefaultResponse> getAllHotels() {
        List<HotelResponseDto> hotels = hotelService.getAllHotels();
        if(!hotels.isEmpty()) {
            DefaultResponse defaultResponse = ResponseUtility.getSuccessfulServerResponse(hotels, "Hotels List");
            return new ResponseEntity<>(defaultResponse, defaultResponse.getHttpStatus());
        }
        else {
            DefaultResponse defaultResponse = ResponseUtility.getFailedServerResponse("Failed To get Hotels List");
            return new ResponseEntity<>(defaultResponse, defaultResponse.getHttpStatus());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DefaultResponse> getHotelById(@PathVariable("id") UUID id) {
        HotelRoomResponseDto hotelRooms = hotelService.getHotelById(id);
        if(hotelRooms != null) {
            DefaultResponse defaultResponse = ResponseUtility.getSuccessfulServerResponse(hotelRooms, "Hotel");
            return new ResponseEntity<>(defaultResponse, defaultResponse.getHttpStatus());
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<DefaultResponse> addHotel(@ModelAttribute @Valid HotelRequestDto hotelRequestDto) {
        Hotel hotel = hotelService.addHotel(hotelRequestDto);
        if(hotel != null) {
            DefaultResponse defaultResponse = ResponseUtility.getSuccessfulServerResponse(hotel, "Hotel");
            return new ResponseEntity<>(defaultResponse, defaultResponse.getHttpStatus());
        }
        DefaultResponse defaultResponse = ResponseUtility.getFailedServerResponse(hotelRequestDto, "Hotel can't be added");
        return new ResponseEntity<>(defaultResponse, defaultResponse.getHttpStatus());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DefaultResponse> updateHotel(@PathVariable("id") UUID id,@RequestBody @Valid HotelRequestDto hotelRequestDto) {
        Hotel hotel = hotelService.updateHotel(id, hotelRequestDto);
        if(hotel != null){
            DefaultResponse defaultResponse = ResponseUtility.getSuccessfulServerResponse(hotel, "Hotel Updated Successfully !!");
            return new ResponseEntity<>(defaultResponse, defaultResponse.getHttpStatus());
        }
        DefaultResponse defaultResponse = ResponseUtility.getFailedServerResponse(hotelRequestDto, "Hotel can't be updated");
        return new ResponseEntity<>(defaultResponse, defaultResponse.getHttpStatus());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponse> deleteHotelById(@PathVariable("id") UUID id) {
        hotelService.deleteHotelById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<DefaultResponse> getAllHotelByLocation(@RequestParam String location) {
        List<HotelResponseDto> hotels = hotelService.getAllHotelsByLocation(location);
        if(!hotels.isEmpty()) {
            DefaultResponse defaultResponse = ResponseUtility.getSuccessfulServerResponse(hotels, "Hotel serarch ");
            return new ResponseEntity<>(defaultResponse, defaultResponse.getHttpStatus());
        }
        DefaultResponse defaultResponse = ResponseUtility.getFailedServerResponse("No hotels found in this location");
        return new ResponseEntity<>(defaultResponse, defaultResponse.getHttpStatus());
    }

}
