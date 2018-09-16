package com.mr.number;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "numbers_addons")
public class NumberAddon {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private UUID id;

    @Column(name="number_id")
    private UUID numberId;

    @Column(name="addon_id")
    private UUID addonId;

    public NumberAddon() {
		super();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getNumberId() {
		return numberId;
	}

	public void setNumberId(UUID numberId) {
		this.numberId = numberId;
	}

	public UUID getAddonId() {
		return addonId;
	}

	public void setAddonId(UUID addonId) {
		this.addonId = addonId;
	}


}
