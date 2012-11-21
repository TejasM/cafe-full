package org.springframework.integration.samples.cafe.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.google.gson.Gson;

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
	
	private Gson converter = new Gson();

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody String orderCreate(Order order) throws IOException {
		order.setNumber(getID());
		for (OrderItem orderitem : order.getOrderItems()) {
			orderitem.setOrderNumber(order.getNumber());
		}
		
		cafe.placeOrder(order);
		return "Successfully created Order #: " + order.getNumber();
	}	
	@RequestMapping(method = RequestMethod.POST, value="batch")
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody String orderCreateBatch(Order order) throws IOException {
		order.setNumber(getID());
		for (OrderItem orderitem : order.getOrderItems()) {
			orderitem.setOrderNumber(order.getNumber());
		}
		String json = converter.toJson(order) + "\n";
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yy-kk");
		String date = format.format(new Date());
		
		File file =new File("json-" + date.substring(0, 9).concat(String.valueOf((Integer.valueOf(date.substring(9, date.length())) + 1))) + ".txt");
		 
		//if file doesnt exists, then create it
		if(!file.exists()){
			file.createNewFile();
		}

		//true = append file
		FileWriter fileWritter = new FileWriter(file.getName(),true);
        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
        bufferWritter.write(json);
        bufferWritter.close();
		return "Successfully created Order #: " + order.getNumber() + " and added for Batch processing";
	}
}
