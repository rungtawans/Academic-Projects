package com.cp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cp.model.Room;

public interface RoomRepository extends JpaRepository<Room,String> {

}
