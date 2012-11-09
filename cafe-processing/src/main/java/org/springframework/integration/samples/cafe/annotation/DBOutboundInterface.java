package org.springframework.integration.samples.cafe.annotation;

import org.springframework.integration.samples.cafe.Delivery;

public interface DBOutboundInterface {

	public abstract void addDelivery(Delivery delivery);
	
}