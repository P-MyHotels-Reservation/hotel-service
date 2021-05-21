package com.example.hotelservice.model.response;

import com.example.hotelservice.constant.HotelStatusConstant;
import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.time.Instant;

@Data
@Builder
public class HotelResponse {
  private BigInteger id;
  private String name;
  private HotelStatusConstant status;
  private String address;
  private String contact;
  private Instant createdTime;
}
