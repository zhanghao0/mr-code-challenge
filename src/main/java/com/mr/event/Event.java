package com.mr.event;

import java.time.ZonedDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "events")
public class Event {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private EventType type;

    @Column(name="number_id")
    private UUID numberId;
    
    private String parameter;

    @Column(name="created_at")
    private ZonedDateTime createdAt;

    public Event() {
		super();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public UUID getNumberId() {
		return numberId;
	}

	public void setNumberId(UUID numberId) {
		this.numberId = numberId;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public ZonedDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(ZonedDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
