package com.example.hotelservice.service;

import com.example.hotelservice.constant.RoomStatusConstant;
import com.example.hotelservice.exception.HotelServiceException;
import com.example.hotelservice.model.entity.HotelEntity;
import com.example.hotelservice.model.entity.RoomEntity;
import com.example.hotelservice.model.entity.RoomTypeEntity;
import com.example.hotelservice.model.request.RoomCreatedRequest;
import com.example.hotelservice.model.request.RoomUpdatedRequest;
import com.example.hotelservice.model.response.RoomResponse;
import com.example.hotelservice.repository.HotelRepository;
import com.example.hotelservice.repository.RoomRepository;
import com.example.hotelservice.repository.RoomTypeRepository;
import com.example.hotelservice.service.implementation.RoomServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class RoomServiceTest {
  private static final String EMAIL = "test@gmail.com";
  private static final String NAME = "test";
  private static final String PHONE = "999";
  private static final String ADDRESS = "999";
  private static final String TIME = "2021-05-20T10:16:41.928407100Z";
  private static final String ONE = "1";
  private RoomEntity room = RoomEntity.builder().build();
  private HotelEntity hotel = HotelEntity.builder().build();
  private RoomTypeEntity roomTypeEntity = RoomTypeEntity.builder().build();

  @Mock
  RoomRepository roomRepository;
  @Mock
  HotelRepository hotelRepository;
  @Mock
  RoomTypeRepository roomTypeRepository;
  RoomService roomService;

  @BeforeEach
  public void init() {
    roomService = new RoomServiceImpl(roomRepository, hotelRepository, roomTypeRepository);
    hotel.setName(NAME);
    hotel.setAddress(ADDRESS);
    hotel.setContact(PHONE);
    hotel.setId(new BigInteger("1"));

    roomTypeEntity.setRoomType("KING_SUPERIOR_ROOM");
    roomTypeEntity.setId(1);
    roomTypeEntity.setPrice("700000");

    room.setName(NAME);
    room.setHotel(hotel);
    room.setType(RoomTypeEntity.builder().roomType("KING_SUPERIOR_ROOM").build());

    hotel.setRoomEntities(List.of(room));

  }
  @Test
  void createRoom_shouldWork() {
    Mockito.when(hotelRepository.findById(new BigInteger("1"))).thenReturn(Optional.ofNullable(hotel));
    Mockito.when(roomTypeRepository.findByRoomType("KING_SUPERIOR_ROOM")).thenReturn(Optional.ofNullable(roomTypeEntity));
    Mockito.when(roomRepository.save(Mockito.any(RoomEntity.class))).thenReturn(room);
    RoomResponse result  = roomService.create(RoomCreatedRequest.builder().hotelId(new BigInteger("1")).name(NAME).type("KING_SUPERIOR_ROOM").build());
    Assertions.assertEquals(NAME, result.getName());
  }

  @Test
  void createRoomWhenHotelNotFoundException_shouldWork() {
    Mockito.when(hotelRepository.findById(Mockito.any(BigInteger.class))).thenReturn(Optional.empty());
    Assertions.assertThrows(HotelServiceException.class, () -> roomService.create(RoomCreatedRequest.builder().build()));
  }

  @Test
  void createRoomWhenRoomTypeNotFoundException_shouldWork() {
    Mockito.when(hotelRepository.findById(new BigInteger("1"))).thenReturn(Optional.ofNullable(hotel));
    Mockito.when(roomTypeRepository.findByRoomType("KING_SUPERIOR_ROOM")).thenReturn(Optional.empty());
    Assertions.assertThrows(HotelServiceException.class, () -> roomService.create(RoomCreatedRequest.builder().build()));
  }

  @Test
  void getRoom_shouldWork() {
    Mockito.when(roomRepository.findById(Mockito.any(BigInteger.class))).thenReturn(Optional.ofNullable(room));
    RoomResponse result  = roomService.get(new BigInteger(ONE));
    Assertions.assertEquals(NAME, result.getName());
  }

  @Test
  void getRoomNotFoundException_shouldWork() {
    Mockito.when(roomRepository.findById(Mockito.any(BigInteger.class))).thenReturn(Optional.empty());
    Assertions.assertThrows(HotelServiceException.class, () -> roomService.get(new BigInteger(ONE)));
  }

  @Test
  void updateRoom_shouldWork() {
    Mockito.when(roomRepository.findById(new BigInteger("1"))).thenReturn(Optional.ofNullable(room));
    Mockito.when(roomTypeRepository.findByRoomType("KING_SUPERIOR_ROOM")).thenReturn(Optional.ofNullable(roomTypeEntity));

    Mockito.when(roomRepository.save(Mockito.any(RoomEntity.class))).thenReturn(room);
    RoomResponse result  = roomService.update(new BigInteger("1"),
        RoomUpdatedRequest.builder().name(NAME).type("KING_SUPERIOR_ROOM").build());
    Assertions.assertEquals(NAME, result.getName());
  }

  @Test
  void updateRoomWhenHotelNotFoundException_shouldWork() {
    Mockito.when(hotelRepository.findById(Mockito.any(BigInteger.class))).thenReturn(Optional.empty());
    Assertions.assertThrows(HotelServiceException.class, () -> roomService.update(new BigInteger("1"),
        RoomUpdatedRequest.builder().build()));
  }

  @Test
  void updateRoomWhenRoomTypeNotFoundException_shouldWork() {
    Mockito.when(hotelRepository.findById(new BigInteger("1"))).thenReturn(Optional.ofNullable(hotel));
    Mockito.when(roomTypeRepository.findByRoomType("KING_SUPERIOR_ROOM")).thenReturn(Optional.empty());
    Assertions.assertThrows(HotelServiceException.class, () -> roomService.update(new BigInteger("1"),
        RoomUpdatedRequest.builder().build()));
  }

  @Test
  void updateRoomNotFoundException_shouldWork() {
    Mockito.when(roomRepository.findById(Mockito.any(BigInteger.class))).thenReturn(Optional.empty());
    Assertions.assertThrows(HotelServiceException.class, () -> roomService.update(new BigInteger(ONE)
        ,RoomUpdatedRequest.builder().build()));
  }

  @Test
  void deleteRoom_shouldWork() {
    Mockito.when(roomRepository.deleteById(Mockito.any(BigInteger.class))).thenReturn(Optional.ofNullable(room));
    Boolean result  = roomService.delete(new BigInteger(ONE));
    Assertions.assertEquals(Boolean.TRUE, result);
  }

  @Test
  void deleteRoomNotFoundException_shouldWork() {
    Mockito.when(roomRepository.findById(Mockito.any(BigInteger.class))).thenReturn(Optional.empty());
    Assertions.assertThrows(HotelServiceException.class, () -> roomService.delete(new BigInteger(ONE)));
  }
}
