package com.cp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cp.model.Type;
import com.cp.repository.TypeRepository;

@Service
public class TypeService {
	private TypeRepository typeRepository;

	// constructor dependency injection
	public TypeService(TypeRepository typeRepository) {
		super();
		this.typeRepository = typeRepository;
	}

	public List<Type> getAllType() {
		return (List<Type>) this.typeRepository.findAll();
	}

	public Type getTypeById(String id) {
		return this.typeRepository.findById(id).get();
	}

	public void deleteTypeById(String id) {
		Type type = this.typeRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Type Id:" + id));
		typeRepository.delete(type);
	}

}
