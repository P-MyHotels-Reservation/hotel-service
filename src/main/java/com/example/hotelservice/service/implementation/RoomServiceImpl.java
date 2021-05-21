package com.example.hotelservice.service.implementation;

import com.example.hotelservice.constant.RoomStatusConstant;
import com.example.hotelservice.exception.HotelErrorResponse;
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
import com.example.hotelservice.service.RoomService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

  private final RoomRepository roomRepository;
  private final HotelRepository hotelRepository;
  private final RoomTypeRepository roomTypeRepository;


  public RoomServiceImpl(
      RoomRepository roomRepository,
      HotelRepository hotelRepository,
      RoomTypeRepository roomTypeRepository) {
    this.roomRepository = roomRepository;
    this.hotelRepository = hotelRepository;
    this.roomTypeRepository = roomTypeRepository;
  }

  @Override
  public RoomResponse create(RoomCreatedRequest roomCreatedRequest) {

    var hotelEntity = hotelRepository.findById(roomCreatedRequest.getHotelId())
        .orElseThrow(() -> new HotelServiceException(HotelErrorResponse.HOTEL_IS_NOT_FOUND));

    var roomTypeEntity = roomTypeRepository.findByRoomType(roomCreatedRequest.getType())
        .orElseThrow(() -> new HotelServiceException(HotelErrorResponse.ROOM_TYPE_IS_NOT_FOUND));

    var room = buildRoomEntity(roomCreatedRequest, hotelEntity, roomTypeEntity);
    return convertEntityToResponse(roomRepository.save(room));
  }

  @Override
  public RoomResponse get(BigInteger roomId) {
    return roomRepository.findById(roomId).map(this::convertEntityToResponse)
        .orElseThrow(() -> new HotelServiceException(HotelErrorResponse.ROOM_IS_NOT_FOUND));
  }

  @Override
  public RoomResponse update(BigInteger roomId, RoomUpdatedRequest room) {
    var updatedRoom = roomRepository.findById(roomId).orElseThrow(
        () -> new HotelServiceException(HotelErrorResponse.ROOM_IS_NOT_FOUND));

    var roomTypeEntity = roomTypeRepository.findByRoomType(room.getType())
        .orElseThrow(() -> new HotelServiceException(HotelErrorResponse.ROOM_TYPE_IS_NOT_FOUND));

    var roomEntity = buildUpdatedRoomEntity(updatedRoom, room, roomTypeEntity);
    return convertEntityToResponse(roomRepository.save(roomEntity));
  }

  @Override
  public Boolean delete(BigInteger roomId) {
    return roomRepository.deleteById(roomId).map(t-> Boolean.TRUE)
        .orElseThrow(() -> new HotelServiceException(HotelErrorResponse.ROOM_IS_NOT_FOUND));
  }

  private RoomEntity buildUpdatedRoomEntity(RoomEntity roomEntity, RoomUpdatedRequest roomUpdatedRequest, RoomTypeEntity roomTypeEntity) {
    roomEntity.setName(Optional.ofNullable(roomUpdatedRequest.getName()).orElse(roomEntity.getName()));
    roomEntity.setFloor(Optional.ofNullable(roomUpdatedRequest.getFloor()).orElse(roomEntity.getFloor()));
    roomEntity.setType(Optional.ofNullable(roomTypeEntity).orElse(roomEntity.getType()));
    roomEntity.setComments(Optional.ofNullable(roomUpdatedRequest.getComment()).orElse(roomEntity.getComments()));
    roomEntity.setStatus(Optional.ofNullable(RoomStatusConstant.fromValue(roomUpdatedRequest.getStatus()))
        .orElse(roomEntity.getStatus()));
    roomEntity.setUpdatedDate(Instant.now());
    return roomEntity;
  }

  private RoomEntity buildRoomEntity(RoomCreatedRequest roomRequest, HotelEntity hotelEntity, RoomTypeEntity roomTypeEntity) {
    return RoomEntity.builder()
        .hotel(hotelEntity)
        .name(roomRequest.getName())
        .floor(roomRequest.getFloor())
        .type(roomTypeEntity)
        .comments(roomRequest.getComment())
        .status(RoomStatusConstant.fromValue(roomRequest.getStatus()))
        .createdDate(Instant.now())
        .updatedDate(Instant.now())
        .build();
  }

  private RoomResponse convertEntityToResponse(RoomEntity save) {
    return RoomResponse.builder()
        .id(save.getId())
        .hotelId(save.getHotel().getId())
        .name(save.getName())
        .floor(save.getFloor())
        .roomType(save.getType().getRoomType())
        .price(save.getType().getPrice())
        .comments(save.getComments())
        .status(save.getStatus())
        .createdDate(save.getCreatedDate())
        .createdDate(save.getUpdatedDate())
        .build();
  }
}
