package com.mr.event;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface EventRepository extends PagingAndSortingRepository<Event, UUID> {
	Page<Event> findAll(Pageable pageable);
	Page<Event> findAllByNumberId(@Param("number_id") UUID numberId, Pageable pageable);
}