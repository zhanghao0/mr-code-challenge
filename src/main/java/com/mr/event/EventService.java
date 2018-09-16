package com.mr.event;

import java.util.UUID;

public interface EventService {
	Event create(EventType type, UUID numberId, String parameter);
	
	Iterable<Event> findAll(UUID numberId, Integer page);
}
