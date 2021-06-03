package com.example.hotelservice.controller.implementation;

import com.example.hotelservice.controller.IHotelController;
import com.example.hotelservice.model.request.HotelCreatedRequest;
import com.example.hotelservice.model.request.HotelUpdatedRequest;
import com.example.hotelservice.model.response.HotelResponse;
import com.example.hotelservice.model.response.RoomResponse;
import com.example.hotelservice.service.HotelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
public class HotelController implements IHotelController {

  private final HotelService hotelService;

  public HotelController(HotelService hotelService) {
    this.hotelService = hotelService;
  }

  @Override
  public ResponseEntity<HotelResponse> createHotel(HotelCreatedRequest hotel) {
    return ResponseEntity.ok(hotelService.create(hotel));
  }

  @Override
  public ResponseEntity<HotelResponse> getHotel(BigInteger hotelId) {
    return ResponseEntity.ok(hotelService.get(hotelId));
  }

  @Override
  public ResponseEntity<HotelResponse> updateHotel(BigInteger hotelId, HotelUpdatedRequest hotel) {
    return ResponseEntity.ok(hotelService.update(hotelId, hotel));
  }

  @Override
  public ResponseEntity<Boolean> deleteHotel(BigInteger hotelId) {
    return ResponseEntity.ok(hotelService.delete(hotelId));
  }

  @Override
  public ResponseEntity<List<HotelResponse>> getHotelList() {
    return ResponseEntity.ok(hotelService.getHotelList());
  }

  @Override
  public ResponseEntity<List<RoomResponse>> getRoomList(BigInteger hotelId) {
    return ResponseEntity.ok(hotelService.getRooms(hotelId));
  }
}
