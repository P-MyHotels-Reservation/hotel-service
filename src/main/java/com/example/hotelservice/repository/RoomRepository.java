package com.example.hotelservice.repository;

import com.example.hotelservice.model.entity.RoomEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends CrudRepository<RoomEntity, Integer> {

  Optional<RoomEntity> findById(BigInteger roomId);

  Optional<RoomEntity> deleteById(BigInteger roomId);

  List<RoomEntity> findAll();

}
