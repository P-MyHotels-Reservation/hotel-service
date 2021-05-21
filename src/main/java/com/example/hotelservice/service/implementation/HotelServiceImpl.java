package com.example.hotelservice.service.implementation;

import com.example.hotelservice.constant.HotelStatusConstant;
import com.example.hotelservice.exception.HotelErrorResponse;
import com.example.hotelservice.exception.HotelServiceException;
import com.example.hotelservice.model.entity.HotelEntity;
import com.example.hotelservice.model.entity.RoomEntity;
import com.example.hotelservice.model.request.HotelCreatedRequest;
import com.example.hotelservice.model.request.HotelUpdatedRequest;
import com.example.hotelservice.model.response.HotelResponse;
import com.example.hotelservice.model.response.RoomResponse;
import com.example.hotelservice.repository.HotelRepository;
import com.example.hotelservice.service.HotelService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

  private final HotelRepository hotelRepository;

  public HotelServiceImpl(HotelRepository hotelRepository) {
    this.hotelRepository = hotelRepository;
  }

  @Override
  public HotelResponse create(HotelCreatedRequest hotelCreatedRequest) {
    var hotel = buildGuestEntity(hotelCreatedRequest);
    return convertEntityToResponse(hotelRepository.save(hotel));
  }

  @Override
  public HotelResponse get(BigInteger hotelId) {
    return hotelRepository.findById(hotelId).map(this::convertEntityToResponse)
        .orElseThrow(() -> new HotelServiceException(HotelErrorResponse.HOTEL_IS_NOT_FOUND));
  }

  @Override
  public HotelResponse update(BigInteger hotelId, HotelUpdatedRequest hotel) {
    var updatedGuestHotel = hotelRepository.findById(hotelId).orElseThrow(
        () -> new HotelServiceException(HotelErrorResponse.HOTEL_IS_NOT_FOUND));
    var hotelEntity = buildUpdatedGuestEntity(updatedGuestHotel, hotel);
    return convertEntityToResponse(hotelRepository.save(hotelEntity));
  }

  @Override
  public Boolean delete(BigInteger hotelId) {
    return hotelRepository.deleteById(hotelId).map(t-> Boolean.TRUE)
        .orElseThrow(() -> new HotelServiceException(HotelErrorResponse.HOTEL_IS_NOT_FOUND));
  }

  @Override
  public List<HotelResponse> getHotelList() {
    return hotelRepository.findAll().stream().map(this::convertEntityToResponse).collect(Collectors.toList());
  }

  @Override
  public List<RoomResponse> getRooms(BigInteger hotelId) {
    var hotelEntity = hotelRepository.findById(hotelId)
        .orElseThrow(() -> new HotelServiceException(HotelErrorResponse.HOTEL_IS_NOT_FOUND));
    var rooms = new ArrayList<>(hotelEntity.getRoomEntities());
    return rooms.stream().map(this::toRoomResponse)
        .sorted(Comparator.comparing(RoomResponse::getId)).collect(Collectors.toList());
  }

  private RoomResponse toRoomResponse(RoomEntity room) {
    return RoomResponse.builder()
        .id(room.getId())
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

  private HotelEntity buildUpdatedGuestEntity(HotelEntity hotelEntity, HotelUpdatedRequest hotelUpdatedRequest) {
    hotelEntity.setContact(Optional.ofNullable(hotelUpdatedRequest.getContact()).orElse(hotelEntity.getContact()));
    hotelEntity.setName(Optional.ofNullable(hotelUpdatedRequest.getName()).orElse(hotelEntity.getName()));
    hotelEntity.setAddress(Optional.ofNullable(hotelUpdatedRequest.getAddress()).orElse(hotelEntity.getAddress()));
    hotelEntity.setStatus(Optional.ofNullable(HotelStatusConstant.fromValue(hotelUpdatedRequest.getStatus()))
        .orElse(hotelEntity.getStatus()));
    hotelEntity.setUpdatedTime(Instant.now());
    return hotelEntity;
  }

  private HotelEntity buildGuestEntity(HotelCreatedRequest hotelRequest) {
    return HotelEntity.builder()
        .name(hotelRequest.getName())
        .createdTime(Instant.now())
        .contact(hotelRequest.getContact())
        .status(HotelStatusConstant.fromValue(hotelRequest.getStatus()))
        .updatedTime(Instant.now())
        .address(hotelRequest.getAddress())
        .build();
  }

  private HotelResponse convertEntityToResponse(HotelEntity save) {
    return HotelResponse.builder()
        .id(save.getId())
        .name(save.getName())
        .address(save.getAddress())
        .contact(save.getContact())
        .createdTime(save.getCreatedTime())
        .createdTime(save.getCreatedTime())
        .status(save.getStatus())
        .build();
  }
}
