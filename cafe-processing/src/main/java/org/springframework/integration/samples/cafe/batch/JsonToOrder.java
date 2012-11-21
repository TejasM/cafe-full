package org.springframework.integration.samples.cafe.batch;

import org.springframework.batch.item.file.LineMapper;
import org.springframework.integration.samples.cafe.Order;

import com.google.gson.Gson;

public class JsonToOrder implements LineMapper<Order>{
	
	private Gson converter = new Gson();

	public Order mapLine(String line, int lineNumber) throws Exception {
		Order order = converter.fromJson(line, Order.class);
		return order;
	}

}
