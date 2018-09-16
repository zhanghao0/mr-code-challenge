package com.mr.addon;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface AddonRepository extends PagingAndSortingRepository<Addon, UUID> {
}