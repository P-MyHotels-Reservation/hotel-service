package com.example.hotelservice.model.entity;

import com.example.hotelservice.constant.HotelStatusConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.Instant;
import java.util.List;


@Entity
@Data
@Table(name = "hotel", schema = "hotel")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private BigInteger id;
  private HotelStatusConstant status;
  private String address;
  private Instant createdTime;
  private Instant updatedTime;
  private String contact;
  private String name;
  @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  private List<RoomEntity> roomEntities;
}
