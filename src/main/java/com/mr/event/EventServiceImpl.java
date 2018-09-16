package com.mr.event;

import java.time.ZonedDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {
	private static final int FIND_EVENTS_PAGE_SIZE = 10;
	
	@Autowired
    private EventRepository repository;

	@Override
	public Event create(EventType type, UUID numberId, String parameter) {
    	Event event = new Event();
    	event.setId(UUID.randomUUID());
    	event.setType(type);
    	event.setNumberId(numberId);
    	event.setParameter(parameter);
    	event.setCreatedAt(ZonedDateTime.now());
    	
    	return this.repository.save(event);		
	}

	@Override
	public Iterable<Event> findAll(UUID numberId, Integer page) {
		Pageable pageable = Pageable.unpaged();
		if (page != null) {
			pageable = PageRequest.of(page, FIND_EVENTS_PAGE_SIZE, Sort.by("numberId"));
		}
		
		if (numberId == null) {
			return this.repository.findAll(pageable);
		} else {
			return this.repository.findAllByNumberId(numberId, pageable);
		}
	}

}
