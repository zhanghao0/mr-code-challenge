package com.mr.number;

import java.util.Set;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mr.addon.Addon;
import com.mr.addon.AddonService;
import com.mr.event.EventService;
import com.mr.event.EventType;

@Service
public class NumberSerivceImpl implements NumberService {
	private static final int NUMBER_LENGTH = 8;
	private static final int FIND_NUMBERS_PAGE_SIZE = 5;
	
	@Autowired
    private NumberRepository repository;
	
	@Autowired
	private EventService eventService;

	@Autowired
	private AddonService addonService;
	
	@Transactional
	public Number provision(String numberString, Set<UUID> addons) {
    	if (numberString == null || numberString.length() != NUMBER_LENGTH) {
    		throw new InvalidNumberException(numberString);
    	}
    	
    	Number number = new Number();
    	
    	number.setId(UUID.randomUUID());
    	number.setNumber(numberString);
    	number.setStatus(NumberStatus.Available);
    	number.setLastUpdatedAt(ZonedDateTime.now());
    	
    	if (addons != null) {
    		Set<Addon> convertedAddons = addonService.findAllById(addons);

        	number.setAddons(convertedAddons);
    	}
    	
    	Number savedNumber = this.repository.save(number);
    	
    	//event parameter can store any additional information
    	this.eventService.create(EventType.Provision, number.getId(), numberString);
    	
    	return savedNumber;
	}

	@Transactional
	public Iterable<Number> findAllByStatus(NumberStatus status, Integer page) {
		Pageable pageable = Pageable.unpaged();
		if (page != null) {
			pageable = PageRequest.of(page, FIND_NUMBERS_PAGE_SIZE, Sort.by("number"));
		}
		
		return this.repository.findAllByStatus(status, pageable);
	}
	
	@Transactional
	public Number subscribe(UUID numberId) {
    	if (numberId == null) {
    		throw new NumberNotFoundException(null);
    	}
    	
    	Optional<Number> numberOpt = this.repository.findById(numberId);
    	
    	if (!numberOpt.isPresent()) {
    		throw new NumberNotFoundException(numberId.toString());
    	}
    	
    	Number number = numberOpt.get();
    	
    	if (number.getStatus() != NumberStatus.Available) {
    		throw new InvalidNumberStatusException(numberId.toString());
    	}
    	
    	number.setStatus(NumberStatus.Subscribed);
    	number.setLastUpdatedAt(ZonedDateTime.now());

    	this.eventService.create(EventType.Subscribe, number.getId(), number.getNumber());
    	
    	return this.repository.save(number);
	}

	@Transactional
	public Number terminate(UUID numberId) {
    	if (numberId == null) {
    		throw new NumberNotFoundException(null);
    	}
    	
    	Optional<Number> numberOpt = this.repository.findById(numberId);
    	
    	if (!numberOpt.isPresent()) {
    		throw new NumberNotFoundException(numberId.toString());
    	}
    	
    	Number number = numberOpt.get();
    	
    	if (number.getStatus() != NumberStatus.Subscribed) {
    		throw new InvalidNumberStatusException(numberId.toString());
    	}
    	
    	//a scheduled job may be used to update terminated numbers to Available again
    	number.setStatus(NumberStatus.Terminated);
    	number.setLastUpdatedAt(ZonedDateTime.now());
    	
    	this.eventService.create(EventType.Terminate, number.getId(), number.getNumber());
    	
    	return this.repository.save(number);
	}

	@Transactional
	public Number quarantine(UUID numberId, String lastUpdatedBy) {
    	if (numberId == null) {
    		throw new NumberNotFoundException(null);
    	}
    	
    	Optional<Number> numberOpt = this.repository.findById(numberId);
    	
    	if (!numberOpt.isPresent()) {
    		throw new NumberNotFoundException(numberId.toString());
    	}
    	
    	Number number = numberOpt.get();
    	
    	//assume only subscribed numbers can be quarantined
    	if (number.getStatus() != NumberStatus.Subscribed) {
    		throw new InvalidNumberStatusException(numberId.toString());
    	}
    	
    	number.setStatus(NumberStatus.Quarantined);
    	number.setLastUpdatedBy(lastUpdatedBy);
    	number.setLastUpdatedAt(ZonedDateTime.now());
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append(number.getNumber());
    	
    	if (lastUpdatedBy != null) {
    		sb.append(',');
    		sb.append(lastUpdatedBy);
    	}
    	
    	this.eventService.create(EventType.Quarantine, number.getId(), sb.toString());
    	
    	return this.repository.save(number);
	}

	@Transactional
	public Number release(UUID numberId, String lastUpdatedBy) {
    	if (numberId == null) {
    		throw new NumberNotFoundException(null);
    	}
    	
    	Optional<Number> numberOpt = this.repository.findById(numberId);
    	
    	if (!numberOpt.isPresent()) {
    		throw new NumberNotFoundException(numberId.toString());
    	}
    	
    	Number number = numberOpt.get();
    	
    	if (number.getStatus() != NumberStatus.Quarantined) {
    		throw new InvalidNumberStatusException(numberId.toString());
    	}
    	
    	number.setStatus(NumberStatus.Released);
    	number.setLastUpdatedBy(lastUpdatedBy);
    	number.setLastUpdatedAt(ZonedDateTime.now());
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append(number.getNumber());
    	
    	if (lastUpdatedBy != null) {
    		sb.append(',');
    		sb.append(lastUpdatedBy);
    	}
    	
    	this.eventService.create(EventType.Release, number.getId(), sb.toString());
    	
    	return this.repository.save(number);
	}
}
