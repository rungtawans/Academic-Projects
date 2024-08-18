package com.cp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cp.model.Room;
import com.cp.repository.RoomRepository;

@Service
public class RoomService {

	private RoomRepository roomRepository;

	// constructor dependency injection
	public RoomService(RoomRepository roomRepository) {
		super();
		this.roomRepository = roomRepository;
	}

	public List<Room> getAllRoomRepository() {
		return roomRepository.findAll();
	}

	public Room getRoomById(String id) {
		return roomRepository.findById(id).get();
	}

	public void deleteRoomById(String id) {
		Room room = this.roomRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Room Id:" + id));
		roomRepository.delete(room);
	}

	public Room saveRoom(Room room) {
		return roomRepository.save(room);
	}
	
	public void setStatusToIn(String id) {
		Room room = roomRepository.findById(id).get();
		room.setRoom_status("in");
		roomRepository.save(room);
	}
	
	public void setStatusToOut(String id) {
		Room room = roomRepository.findById(id).get();
		room.setRoom_status("out");
		roomRepository.save(room);
	}
}
