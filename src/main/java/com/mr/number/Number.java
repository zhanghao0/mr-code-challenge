package com.mr.number;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.mr.addon.Addon;

@Entity
@Table(name = "numbers")
public class Number {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;
	
    private String number;
    
    @Enumerated(EnumType.STRING)
    private NumberStatus status;

    @Column(name="last_updated_by")
    private String lastUpdatedBy;
    
    @Column(name="last_updated_at")
    private ZonedDateTime lastUpdatedAt;
    
    @Version
    private Long version;
    
    @ManyToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinTable(name = "numbers_addons",
    	joinColumns = {@JoinColumn(name = "number_id")},
    	inverseJoinColumns = {@JoinColumn(name = "addon_id")}
    )
    private Set<Addon> addons = new HashSet<>();

    public Number() {
		super();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public NumberStatus getStatus() {
		return status;
	}

	public void setStatus(NumberStatus status) {
		this.status = status;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public ZonedDateTime getLastUpdatedAt() {
		return lastUpdatedAt;
	}

	public void setLastUpdatedAt(ZonedDateTime lastUpdatedAt) {
		this.lastUpdatedAt = lastUpdatedAt;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Set<Addon> getAddons() {
		return addons;
	}

	public void setAddons(Set<Addon> addons) {
		this.addons = addons;
	}

}
