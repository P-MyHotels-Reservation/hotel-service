package com.example.hotelservice.repository;

import com.example.hotelservice.model.entity.RoomEntity;
import com.example.hotelservice.model.entity.RoomTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoomTypeRepository extends CrudRepository<RoomTypeEntity, Integer> {

  Optional<RoomTypeEntity> findByRoomType(String roomType);

  List<RoomTypeEntity> findAll();

}
