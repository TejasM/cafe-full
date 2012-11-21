package org.springframework.integration.samples.cafe.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.samples.cafe.Delivery;
import org.springframework.integration.samples.cafe.Order;
import org.springframework.integration.samples.cafe.annotation.Cafe;


public class PlaceOrder implements ItemProcessor<Order, Delivery> {
	
	@Autowired
	private Cafe cafe;

	public Delivery process(Order item) throws Exception {
		Delivery delivery = cafe.placeOrder(item);
		System.out.println("Sent order: " + item.getNumber());
		return delivery;
	}	
	

}
