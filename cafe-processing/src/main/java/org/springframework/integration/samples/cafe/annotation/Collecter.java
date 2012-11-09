package org.springframework.integration.samples.cafe.annotation;

import java.util.List;

import org.springframework.integration.samples.cafe.Delivery;

public interface Collecter {

	public abstract List<Delivery> getDelivered();
	
}