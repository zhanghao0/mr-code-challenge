package com.mr.number;

import java.util.Set;
import java.util.UUID;

public interface NumberService {
	Number provision(String numberString, Set<UUID> addons);
	
	Iterable<Number> findAllByStatus(NumberStatus status, Integer page);
	
	Number subscribe(UUID numberId);
	Number terminate(UUID numberId);
	
	Number quarantine(UUID numberId, String lastUpdatedBy);
	Number release(UUID numberId, String lastUpdatedBy);
}
