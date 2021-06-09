package com.example.hotelservice.model.response;

import com.example.hotelservice.constant.RoomStatusConstant;
import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.time.Instant;

@Data
@Builder
public class RoomResponse {
  private BigInteger id;
  private String uuid;
  private BigInteger hotelId;
  private String name;
  private String floor;
  private String roomType;
  private String price;
  private String comments;
  private RoomStatusConstant status;
  private Instant updatedDate;
  private Instant createdDate;
}
