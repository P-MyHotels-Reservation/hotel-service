package com.example.hotelservice.controller;

import com.example.hotelservice.controller.implementation.HotelController;
import com.example.hotelservice.model.request.HotelCreatedRequest;
import com.example.hotelservice.model.request.HotelUpdatedRequest;
import com.example.hotelservice.model.response.HotelResponse;
import com.example.hotelservice.model.response.RoomResponse;
import com.example.hotelservice.service.HotelService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
class HotelControllerTest {


  public IHotelController iHotelController;
  public HotelService hotelService;

  @BeforeEach
  public void init() {
    hotelService = Mockito.mock(HotelService.class);
    iHotelController = new HotelController(hotelService);
  }

  @Test
  void createHotelController_shouldWork() {
    Mockito.when(hotelService.create(Mockito.any(HotelCreatedRequest.class)))
        .thenReturn(HotelResponse.builder().build());

    ResponseEntity<HotelResponse> responses = iHotelController.createHotel(Mockito.any(HotelCreatedRequest.class));

    Assertions.assertEquals(HttpStatus.OK, responses.getStatusCode());
  }

  @Test
  void getHotelController_shouldWork() {
    Mockito.when(hotelService.get(new BigInteger("1")))
        .thenReturn(HotelResponse.builder().build());

    ResponseEntity<HotelResponse> responses = iHotelController.getHotel(new BigInteger("1"));

    Assertions.assertEquals(HttpStatus.OK, responses.getStatusCode());
  }

  @Test
  void updateHotelController_shouldWork() {
    Mockito.when(hotelService.update(new BigInteger("1"), HotelUpdatedRequest.builder().build()))
        .thenReturn(HotelResponse.builder().build());

    ResponseEntity<HotelResponse> responses = iHotelController
        .updateHotel(new BigInteger("1"), HotelUpdatedRequest.builder().build());

    Assertions.assertEquals(HttpStatus.OK, responses.getStatusCode());
  }

  @Test
  void deleteHotelController_shouldWork() {
    Mockito.when(hotelService.delete(new BigInteger("1")))
        .thenReturn(Boolean.TRUE);

    ResponseEntity<Boolean> responses = iHotelController.deleteHotel(new BigInteger("1"));

    Assertions.assertEquals(HttpStatus.OK, responses.getStatusCode());
  }

  @Test
  void getHotelListController_shouldWork() {
    List<HotelResponse> hotelResponseList =  new ArrayList<>();
    Mockito.when(hotelService.getHotelList())
        .thenReturn(hotelResponseList);

    ResponseEntity<List<HotelResponse>> responses = iHotelController.getHotelList();

    Assertions.assertEquals(HttpStatus.OK, responses.getStatusCode());
  }

  @Test
  void getRoomListController_shouldWork() {
    List<RoomResponse> roomResponseList =  new ArrayList<>();
    Mockito.when(hotelService.getRooms(new BigInteger("1")))
        .thenReturn(roomResponseList);

    ResponseEntity<List<RoomResponse>> responses = iHotelController.getRoomList(new BigInteger("1"));

    Assertions.assertEquals(HttpStatus.OK, responses.getStatusCode());
  }
}
