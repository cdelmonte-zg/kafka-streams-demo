package de.cdelmonte.afs.dante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import de.cdelmonte.afs.dante.service.DiscoveryService;
import java.util.List;

@RestController
@RequestMapping(value = "v1/tools")
public class ToolsController {
	@Autowired
	private DiscoveryService discoveryService;

	@RequestMapping(value = "/eureka/services", method = RequestMethod.GET)
	public List<String> getEurekaServices() {
		return discoveryService.getEurekaServices();
	}
}
