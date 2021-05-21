package com.example.hotelservice.exception;

import lombok.Getter;

@Getter
public class HotelServiceException extends RuntimeException {

  HotelErrorResponse hotelErrorResponse;

  public HotelServiceException(HotelErrorResponse hotelErrorResponse) {
    this.hotelErrorResponse = hotelErrorResponse;
  }
}
