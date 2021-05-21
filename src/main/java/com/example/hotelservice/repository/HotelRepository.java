package com.example.hotelservice.repository;

import com.example.hotelservice.model.entity.HotelEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface HotelRepository extends CrudRepository<HotelEntity, Integer> {

  Optional<HotelEntity> findById(BigInteger hotelId);

  Optional<HotelEntity> deleteById(BigInteger hotelId);

  List<HotelEntity> findAll();

}
