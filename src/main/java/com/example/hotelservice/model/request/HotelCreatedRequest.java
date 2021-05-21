package com.example.hotelservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelCreatedRequest {
  private String name;
  private String contact;
  private String address;
  private String status;
}
