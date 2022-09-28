package edu.miu.cs590.hoteldetailsservice.util;

import org.springframework.http.HttpStatus;

public class ResponseUtility {
    public static DefaultResponse getSuccessfulServerResponse(String message) {
        return DefaultResponse.builder()
                .success(true)
                .message(message)
                .httpStatus(HttpStatus.OK)
                .httpCode(HttpStatus.OK.value())
                .build();
    }

    public static DefaultResponse getSuccessfulServerResponse(Object data, String message) {
        return DefaultResponse.builder()
                .success(true)
                .message(message)
                .data(data)
                .httpStatus(HttpStatus.OK)
                .httpCode(HttpStatus.OK.value())
                .build();
    }
    public static DefaultResponse getFailedServerResponse(String message) {
        return DefaultResponse.builder()
                .success(false)
                .message(message)
                .httpStatus(HttpStatus.NOT_ACCEPTABLE)
                .httpCode(HttpStatus.NOT_ACCEPTABLE.value())
                .build();
    }

    public static DefaultResponse getFailedServerResponse(Object data, String message) {
        return DefaultResponse.builder()
                .success(false)
                .message(message)
                .data(data)
                .httpStatus(HttpStatus.NOT_ACCEPTABLE)
                .httpCode(HttpStatus.NOT_ACCEPTABLE.value())
                .build();
    }
}
