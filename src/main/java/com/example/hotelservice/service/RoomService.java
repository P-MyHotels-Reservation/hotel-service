package com.example.hotelservice.service;

import com.example.hotelservice.model.request.RoomCreatedRequest;
import com.example.hotelservice.model.request.RoomUpdatedRequest;
import com.example.hotelservice.model.response.RoomResponse;

import java.math.BigInteger;
import java.util.List;

public interface RoomService {

  RoomResponse create(RoomCreatedRequest hotelCreatedRequest);

  RoomResponse get(BigInteger hotelId);

  RoomResponse update(BigInteger hotelId, RoomUpdatedRequest hotel);

  Boolean delete(BigInteger hotelId);

}
