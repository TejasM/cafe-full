package org.springframework.integration.samples.cafe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.samples.cafe.annotation.Collecter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/status")
public class StatusController {
	
	@Autowired
	private Collecter collecter;
	
	@RequestMapping(method=RequestMethod.GET)
	public void getStatus(Model model){
		model.addAttribute("delivered", collecter.getDelivered());
	}
}
