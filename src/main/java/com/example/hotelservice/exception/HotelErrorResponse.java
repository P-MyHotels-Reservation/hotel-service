package com.example.hotelservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@ToString
public enum HotelErrorResponse implements Serializable {

  HOTEL_IS_NOT_FOUND(HttpStatus.NOT_FOUND, "2000", "Hotel is not found."),
  ROOM_IS_NOT_FOUND(HttpStatus.NOT_FOUND, "2001", "Room is not found."),
  ROOM_TYPE_IS_NOT_FOUND(HttpStatus.NOT_FOUND, "2002", "Room type is not found.");

  private final HttpStatus httpStatus;
  private final String errorCode;
  private final String message;
}
