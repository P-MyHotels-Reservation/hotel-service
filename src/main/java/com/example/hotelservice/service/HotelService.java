package com.example.hotelservice.service;

import com.example.hotelservice.model.request.HotelCreatedRequest;
import com.example.hotelservice.model.request.HotelUpdatedRequest;
import com.example.hotelservice.model.response.HotelResponse;
import com.example.hotelservice.model.response.RoomResponse;

import java.util.List;
import java.math.BigInteger;

public interface HotelService {

  HotelResponse create(HotelCreatedRequest hotelCreatedRequest);

  HotelResponse get(BigInteger hotelId);

  HotelResponse update(BigInteger hotelId, HotelUpdatedRequest hotel);

  Boolean delete(BigInteger hotelId);

  List<HotelResponse> getHotelList();

  List<RoomResponse> getRooms(BigInteger hotelId);

}
