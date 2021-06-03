package com.example.hotelservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomCreatedRequest {
  private BigInteger hotelId;
  private String name;
  private String floor;
  private String type;
  private String comment;
  private String status;
}
