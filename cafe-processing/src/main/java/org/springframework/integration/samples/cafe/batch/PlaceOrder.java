package org.springframework.integration.samples.cafe.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Scope;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.samples.cafe.Delivery;
import org.springframework.integration.samples.cafe.Order;

@Scope("step")
public interface PlaceOrder extends ItemProcessor<Order, Delivery> {
	
	@Gateway
	Delivery process(Order item);
}
