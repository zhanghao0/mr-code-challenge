package com.mr.number;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumberController {
	@Autowired
    private NumberService service;

    @RequestMapping(path = "/api/v1/numbers", method = RequestMethod.POST)
    public Number provision(@RequestBody ProvisionNumberRequest request) {
    	return this.service.provision(request.getNumber(), request.getAddons());
    }

    @RequestMapping(path = "/api/v1/numbers", method = RequestMethod.GET)
    public Iterable<Number> findAllByStatus(
    		@RequestParam(value = "status") NumberStatus status,
    		@RequestParam(value = "page", required = false) Integer page) {
        return this.service.findAllByStatus(status, page);
    }

    @RequestMapping(path = "/api/v1/numbers/subscribed/{numberId}", method = RequestMethod.POST)
    public void subscribe(@PathVariable UUID numberId) {
    	this.service.subscribe(numberId);
    }

    @RequestMapping(path = "/api/v1/numbers/terminated/{numberId}", method = RequestMethod.POST)
    public void terminate(@PathVariable UUID numberId) {
    	this.service.terminate(numberId);
    }

    @RequestMapping(path = "/api/v1/numbers/quarantined/{numberId}", method = RequestMethod.POST)
    public void quarantine(@PathVariable UUID numberId, @RequestBody QuarantineNumberRequest request) {
    	this.service.quarantine(numberId, request.getLastUpdatedBy());
    }

    @RequestMapping(path = "/api/v1/numbers/released/{numberId}", method = RequestMethod.POST)
    public void release(@PathVariable UUID numberId, @RequestBody ReleaseNumberRequest request) {
    	this.service.release(numberId, request.getLastUpdatedBy());
    }
}
