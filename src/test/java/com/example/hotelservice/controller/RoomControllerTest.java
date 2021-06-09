package com.example.hotelservice.controller;

import com.example.hotelservice.controller.implementation.RoomController;
import com.example.hotelservice.model.request.RoomCreatedRequest;
import com.example.hotelservice.model.request.RoomUpdatedRequest;
import com.example.hotelservice.model.response.RoomResponse;
import com.example.hotelservice.service.RoomService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigInteger;

@ExtendWith(SpringExtension.class)
class RoomControllerTest {


  public IRoomController iRoomController;
  public RoomService roomService;

  @BeforeEach
  public void init() {
    roomService = Mockito.mock(RoomService.class);
    iRoomController = new RoomController(roomService);
  }

  @Test
  void createRoomController_shouldWork() {
    Mockito.when(roomService.create(Mockito.any(RoomCreatedRequest.class)))
        .thenReturn(RoomResponse.builder().build());

    ResponseEntity<RoomResponse> responses = iRoomController.createRoom(Mockito.any(RoomCreatedRequest.class));

    Assertions.assertEquals(HttpStatus.OK, responses.getStatusCode());
  }

  @Test
  void getRoomController_shouldWork() {
    Mockito.when(roomService.get(new BigInteger("1")))
        .thenReturn(RoomResponse.builder().build());

    ResponseEntity<RoomResponse> responses = iRoomController.getRoom(new BigInteger("1"));

    Assertions.assertEquals(HttpStatus.OK, responses.getStatusCode());
  }

  @Test
  void updateRoomController_shouldWork() {
    Mockito.when(roomService.update(new BigInteger("1"), RoomUpdatedRequest.builder().build()))
        .thenReturn(RoomResponse.builder().build());

    ResponseEntity<RoomResponse> responses = iRoomController
        .updateRoom(new BigInteger("1"), RoomUpdatedRequest.builder().build());

    Assertions.assertEquals(HttpStatus.OK, responses.getStatusCode());
  }

  @Test
  void deleteRoomController_shouldWork() {
    Mockito.when(roomService.delete(new BigInteger("1")))
        .thenReturn(Boolean.TRUE);

    ResponseEntity<Boolean> responses = iRoomController.deleteRoom(new BigInteger("1"));

    Assertions.assertEquals(HttpStatus.OK, responses.getStatusCode());
  }
}
