package com.mr.addon;

import java.util.Set;
import java.util.UUID;

public interface AddonService {
	Set<Addon> findAll();
	Set<Addon> findAllById(Set<UUID> ids);
}
