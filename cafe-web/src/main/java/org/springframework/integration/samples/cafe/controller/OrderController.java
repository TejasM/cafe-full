package org.springframework.integration.samples.cafe.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.integration.samples.cafe.Cafe;
import org.springframework.integration.samples.cafe.Order;
import org.springframework.integration.samples.cafe.OrderItem;
import org.springframework.integration.samples.cafe.dao.OrderItemDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/order")
public class OrderController {
	private int ID = 0;

	public int getID() {
		return ID++;
	}

	@Autowired
	private Cafe cafe;

	@Autowired
	private OrderItemDao dao;

	@RequestMapping(method = RequestMethod.GET)
	public void orderForm(Model model) {
		model.addAttribute("orderItems", dao.findAll());
		model.addAttribute("order", new Order());
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody String orderCreate(Order order) {
		order.setNumber(getID());
		for (OrderItem orderitem : order.getOrderItems()) {
			orderitem.setOrderNumber(order.getNumber());
		}
		
		OutputStream stream;
		try {
			HashMap<String, Order> map = new HashMap<String, Order>();
			 
			map.put("k", order);
			stream = new FileOutputStream("sample.txt");
			ObjectOutputStream out = new ObjectOutputStream(stream);
			 
			out.writeObject(map);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
		cafe.placeOrder(order);
		return "Successfully created Order #: " + order.getNumber();
	}
}
