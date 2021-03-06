package com.example.hotelservice.exception;

import lombok.Getter;

@Getter
public class HotelServiceException extends RuntimeException {

  final HotelErrorResponse hotelErrorResponse;

  public HotelServiceException(HotelErrorResponse hotelErrorResponse) {
    this.hotelErrorResponse = hotelErrorResponse;
  }
}
