package edu.miu.cs590.hoteldetailsservice.proxy.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class HotelRoomFeignConfig {
    @Value("${room.secret.key}")
    public String serviceKey;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            System.out.println(serviceKey);
            requestTemplate.header("API_KEY", serviceKey);
        };
    }
}
