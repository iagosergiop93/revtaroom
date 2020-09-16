package com.revtaroom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revtaroom.entities.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

}
