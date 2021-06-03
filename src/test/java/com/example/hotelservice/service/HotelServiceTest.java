package com.example.hotelservice.service;

import com.example.hotelservice.exception.HotelServiceException;
import com.example.hotelservice.model.entity.HotelEntity;
import com.example.hotelservice.model.entity.RoomEntity;
import com.example.hotelservice.model.entity.RoomTypeEntity;
import com.example.hotelservice.model.request.HotelCreatedRequest;
import com.example.hotelservice.model.request.HotelUpdatedRequest;
import com.example.hotelservice.model.response.HotelResponse;
import com.example.hotelservice.model.response.RoomResponse;
import com.example.hotelservice.repository.HotelRepository;
import com.example.hotelservice.service.implementation.HotelServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootTest
class HotelServiceTest {

  private static final String NAME = "test";
  private static final String PHONE = "999";
  private static final String ADDRESS = "999";

  private static final String TIME = "2021-05-20T10:16:41.928407100Z";
  private static final String ONE = "1";
  private HotelEntity hotel = HotelEntity.builder().build();
  private HotelResponse hotelResponse = HotelResponse.builder().build();
  private RoomEntity roomEntity = RoomEntity.builder().build();
  @Mock
  HotelRepository hotelRepository;
  HotelService hotelService;

  @BeforeEach
  public void init() {
    hotelService = new HotelServiceImpl(hotelRepository);
    hotel.setName(NAME);
    hotel.setAddress(ADDRESS);
    hotel.setContact(PHONE);

    hotelResponse.setAddress(ADDRESS);
    hotelResponse.setName(NAME);
    hotelResponse.setContact(PHONE);

    roomEntity.setName(NAME);
    roomEntity.setHotel(hotel);
    roomEntity.setType(RoomTypeEntity.builder().roomType("QUEEN_SUPERIOR_ROOM").build());

    hotel.setRoomEntities(List.of(roomEntity));
  }

  @Test
  void createHotel_shouldWork() {
    Mockito.when(hotelRepository.save(Mockito.any(HotelEntity.class))).thenReturn(hotel);
    HotelResponse result = hotelService.create(HotelCreatedRequest.builder().build());
    Assertions.assertEquals(NAME, result.getName());
  }

  @Test
  void getHotel_shouldWork() {
    Mockito.when(hotelRepository.findById(Mockito.any(BigInteger.class))).thenReturn(Optional.ofNullable(hotel));
    HotelResponse result = hotelService.get(new BigInteger(ONE));
    Assertions.assertEquals(NAME, result.getName());
  }

  @Test
  void getHotelNotFoundException_shouldWork() {
    Mockito.when(hotelRepository.findById(Mockito.any(BigInteger.class))).thenReturn(Optional.empty());
    Assertions.assertThrows(HotelServiceException.class, () -> hotelService.get(new BigInteger(ONE)));
  }

  @Test
  void updateHotel_shouldWork() {
    Mockito.when(hotelRepository.findById(Mockito.any(BigInteger.class))).thenReturn(Optional.ofNullable(hotel));
    Mockito.when(hotelRepository.save(Mockito.any(HotelEntity.class))).thenReturn(hotel);
    HotelResponse result = hotelService.update(new BigInteger(ONE),
        HotelUpdatedRequest.builder().build());
    Assertions.assertEquals(NAME, result.getName());
  }

  @Test
  void updateHotelNotFoundException_shouldWork() {
    Mockito.when(hotelRepository.findById(Mockito.any(BigInteger.class))).thenReturn(Optional.empty());
    Assertions.assertThrows(HotelServiceException.class, () -> hotelService.update(new BigInteger(ONE)
        , HotelUpdatedRequest.builder().build()));
  }

  @Test
  void deleteHotel_shouldWork() {
    Mockito.when(hotelRepository.deleteById(Mockito.any(BigInteger.class))).thenReturn(Optional.ofNullable(hotel));
    Boolean result = hotelService.delete(new BigInteger(ONE));
    Assertions.assertEquals(Boolean.TRUE, result);
  }

  @Test
  void deleteHotelNotFoundException_shouldWork() {
    Mockito.when(hotelRepository.findById(Mockito.any(BigInteger.class))).thenReturn(Optional.empty());
    Assertions.assertThrows(HotelServiceException.class, () -> hotelService.delete(new BigInteger(ONE)));
  }

  @Test
  void getHotelList_shouldWork() {
    List<HotelEntity> hotelResponseList = List.of(hotel);
    Mockito.when(hotelRepository.findAll()).thenReturn(hotelResponseList);
    List<HotelResponse> result = hotelService.getHotelList();
    Assertions.assertEquals(List.of(hotelResponse), result);
  }

  @Test
  void getRooms_shouldWork() {
    Mockito.when(hotelRepository.findById(new BigInteger(ONE))).thenReturn(Optional.ofNullable(hotel));
    List<RoomEntity> rooms = hotel.getRoomEntities();
    List<RoomResponse> result = hotelService.getRooms(new BigInteger(ONE));
    Assertions.assertEquals(rooms.stream().map(this::toRoomResponse).collect(Collectors.toList()), result);
  }

  @Test
  void getRoomsWhenHotelIsNotFoundException_shouldWork() {
    Mockito.when(hotelRepository.findById(Mockito.any(BigInteger.class))).thenReturn(Optional.empty());
    Assertions.assertThrows(HotelServiceException.class, () -> hotelService.getRooms(new BigInteger(ONE)));
  }

  private RoomResponse toRoomResponse(RoomEntity room) {
    return RoomResponse.builder()
        .uuid(room.getUuid())
        .hotelId(room.getHotel().getId())
        .name(room.getName())
        .floor(room.getFloor())
        .roomType(room.getType().getRoomType())
        .price(room.getType().getPrice())
        .comments(room.getComments())
        .status(room.getStatus())
        .createdDate(room.getCreatedDate())
        .createdDate(room.getUpdatedDate())
        .build();
  }

}
