package edu.miu.cs590.hoteldetailsservice.repository;

import edu.miu.cs590.hoteldetailsservice.model.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HotelRepository extends MongoRepository<Hotel, UUID> {
    Hotel findHotelByName(String name);

    List<Hotel> findAll();

    List<Hotel> findHotelByLocation(String location);

}
