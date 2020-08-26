package com.revtaroom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revtaroom.entities.House;

@Repository
public interface HouseRepository extends JpaRepository<House, Long>{

}
