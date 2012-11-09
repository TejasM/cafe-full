package org.springframework.integration.samples.cafe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.samples.cafe.DrinkType;
import org.springframework.integration.samples.cafe.OrderItem;
import org.springframework.integration.samples.cafe.dao.OrderItemDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/orderItem")
public class OrderItemController {
	
	@Autowired
	private OrderItemDao dao;
	
	@RequestMapping(method=RequestMethod.GET)
	public void orderItemForm(OrderItem orderItem, Model model){
		model.addAttribute("types", DrinkType.values());
		model.addAttribute("orderItem", new OrderItem());
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody String orderItem(OrderItem orderItem, Model model){
		dao.saveAndFlush(orderItem);
		return "Successfully created Order Item: " + orderItem.toString();
	}
	
}
