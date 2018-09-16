package com.mr.number;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NumberRepository extends PagingAndSortingRepository<Number, UUID> {
	Page<Number> findAllByStatus(NumberStatus status, Pageable pageable);
}