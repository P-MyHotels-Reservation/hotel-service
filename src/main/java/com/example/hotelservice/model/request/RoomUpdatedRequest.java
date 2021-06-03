package com.example.hotelservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomUpdatedRequest {
  private String name;
  private String floor;
  private String type;
  private String comment;
  private String status;
}
