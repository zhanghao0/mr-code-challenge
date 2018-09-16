package com.mr.number;

import java.util.Set;
import java.util.UUID;

public class ProvisionNumberRequest {
	private String number;
	private Set<UUID> addons;
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Set<UUID> getAddons() {
		return addons;
	}
	public void setAddons(Set<UUID> addons) {
		this.addons = addons;
	}

}
