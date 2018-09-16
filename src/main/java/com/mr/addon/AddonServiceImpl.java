package com.mr.addon;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AddonServiceImpl implements AddonService {
	@Autowired
    private AddonRepository repository;
	
	@Override
	public Set<Addon> findAll() {
		Iterable<Addon> iterable = this.repository.findAll(Sort.by("type"));
		
		Set<Addon> addons = new HashSet<>();
		
		iterable.forEach(addons::add);
		
		return addons;
	}

	@Override
	public Set<Addon> findAllById(Set<UUID> ids) {
		Iterable<Addon> iterable = this.repository.findAllById(ids);
		
		Set<Addon> addons = new HashSet<>();
		
		iterable.forEach(addons::add);
		
		return addons;
	}

}
