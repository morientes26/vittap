package com.vitta_pilates.core.studioadmin.service;

import com.vitta_pilates.core.shared.service.EntityService;
import com.vitta_pilates.model.dao.Room;
import com.vitta_pilates.model.repository.RoomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RoomService extends EntityService<Room> {

	private final Logger log = LoggerFactory.getLogger(RoomService.class);

	@Autowired
	RoomRepository repository;

	@Transactional
	public Room save(Room room){
		return repository.save(room);
	}

	@Override
	public boolean delete(Room room){

		try {
			repository.delete(room);
		} catch (Exception e) {
			log.warn("Cannot delete item {}", room);
			return false;
		}
		return true;
	}

	@Override
	public Room findOne(long id){ return repository.findOne(id); }

	@Override
	public List<Room> getAll(){
		return repository.findAll();
	}

	public List<Room> findByNameOrDescription(String keyword){
		keyword = (keyword==null) ? "*" : keyword;
		return repository.findByNameOrDescription(keyword);
	}
}
