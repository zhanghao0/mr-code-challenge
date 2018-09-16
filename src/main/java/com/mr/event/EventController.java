package com.mr.event;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {
	@Autowired
    private EventService service;

    @RequestMapping(path = "/api/v1/events", method = RequestMethod.GET)
    public Iterable<Event> findAll(
    		@RequestParam(value = "numberId", required = false) UUID numberId,
    		@RequestParam(value = "page", required = false) Integer page) {
        return this.service.findAll(numberId, page);
    }
}
