package com.example.hotelservice.model.response;

import com.example.hotelservice.constant.HotelStatusConstant;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class HotelResponse {
  private String uuid;
  private String name;
  private HotelStatusConstant status;
  private String address;
  private String contact;
  private Instant createdTime;
}
