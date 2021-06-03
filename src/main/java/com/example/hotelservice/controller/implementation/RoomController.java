package com.example.hotelservice.controller.implementation;

import com.example.hotelservice.controller.IRoomController;
import com.example.hotelservice.model.request.RoomCreatedRequest;
import com.example.hotelservice.model.request.RoomUpdatedRequest;
import com.example.hotelservice.model.response.RoomResponse;
import com.example.hotelservice.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
public class RoomController implements IRoomController {

  private final RoomService roomService;

  public RoomController(RoomService roomService) {
    this.roomService = roomService;
  }

  @Override
  public ResponseEntity<RoomResponse> createRoom(RoomCreatedRequest room) {
    return ResponseEntity.ok(roomService.create(room));
  }

  @Override
  public ResponseEntity<RoomResponse> getRoom(BigInteger roomId) {
    return ResponseEntity.ok(roomService.get(roomId));
  }

  @Override
  public ResponseEntity<RoomResponse> updateRoom(BigInteger roomId, RoomUpdatedRequest room) {
    return ResponseEntity.ok(roomService.update(roomId, room));
  }

  @Override
  public ResponseEntity<Boolean> deleteRoom(BigInteger roomId) {
    return ResponseEntity.ok(roomService.delete(roomId));
  }
}
