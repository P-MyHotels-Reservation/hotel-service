package com.example.hotelservice.model.entity;

import com.example.hotelservice.constant.RoomStatusConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.Instant;


@Entity
@Data
@Table(name = "room", schema = "hotel")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private BigInteger id;
  private String uuid;
  @ManyToOne(targetEntity = HotelEntity.class)
  @JoinColumn(name = "hotel_id")
  private HotelEntity hotel;
  private String name;
  private String floor;
  @ManyToOne(targetEntity = RoomTypeEntity.class)
  @JoinColumn(name = "type_id")
  private RoomTypeEntity type;
  private String comments;
  private RoomStatusConstant status;
  private Instant updatedDate;
  private Instant createdDate;


}
