package com.innovatrics.commons.vittap.core.studioadmin.service;

import com.innovatrics.commons.vittap.model.dao.Room;
import com.innovatrics.commons.vittap.model.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RoomService {

	@Autowired
	RoomRepository repository;

	@Transactional
	public Room save(Room room){
		return repository.save(room);
	}

	@Transactional
	public void delete(Room room){
		 repository.delete(room);
	}

	public Room findOne(long id){ return repository.findOne(id); }

	public List<Room> getAll(){
		return repository.findAll();
	}

	public List<Room> findByNameOrDescription(String keyword){
		keyword = (keyword==null) ? "*" : keyword;
		return repository.findByNameOrDescription(keyword);
	}
}
