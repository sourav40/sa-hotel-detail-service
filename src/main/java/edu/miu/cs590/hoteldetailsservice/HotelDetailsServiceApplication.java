package edu.miu.cs590.hoteldetailsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableFeignClients
public class HotelDetailsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelDetailsServiceApplication.class, args);
	}

}
