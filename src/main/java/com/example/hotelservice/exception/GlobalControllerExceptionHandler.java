package com.example.hotelservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalControllerExceptionHandler {

  private final HotelExceptionConverter hotelExceptionConverter = new HotelExceptionConverter();

  @ExceptionHandler(HotelServiceException.class)
  public ResponseEntity<Object> handleAccountNotFoundException(HotelServiceException ex) {
    log.error("HotelException", ex);

    return new ResponseEntity<>(
        hotelExceptionConverter.toJsonNode(ex.getHotelErrorResponse(), StringUtils.EMPTY),
        new HttpHeaders(), ex.getHotelErrorResponse().getHttpStatus());
  }
}
