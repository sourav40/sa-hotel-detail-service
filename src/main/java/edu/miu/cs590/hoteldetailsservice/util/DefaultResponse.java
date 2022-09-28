package edu.miu.cs590.hoteldetailsservice.util;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DefaultResponse {
    private HttpStatus httpStatus;
    private int httpCode;
    private boolean success;
    private String message;
    private Object data;
}