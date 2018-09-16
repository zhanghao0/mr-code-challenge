package com.mr.addon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddonController {
	@Autowired
    private AddonService service;

    @RequestMapping(path = "/api/v1/addons", method = RequestMethod.GET)
    public Iterable<Addon> findAll() {
        return this.service.findAll();
    }
}
