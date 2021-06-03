package com.example.hotelservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@Table(name = "room_type", schema = "hotel")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomTypeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String roomType;
  private String price;
}
