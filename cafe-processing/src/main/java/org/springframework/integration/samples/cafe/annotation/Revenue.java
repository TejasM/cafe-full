package org.springframework.integration.samples.cafe.annotation;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Revenue implements Serializable {
	
	@Id
	private int orderId;
	
	private double revenue;
	
	@SuppressWarnings("unused")
	private Date date;
	
	public Revenue() {
		this.date = new Date();
	}
	
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public double getRevenue() {
		return revenue;
	}

	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}
}
