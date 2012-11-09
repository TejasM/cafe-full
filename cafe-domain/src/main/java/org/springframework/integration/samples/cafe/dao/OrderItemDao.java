package org.springframework.integration.samples.cafe.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.integration.samples.cafe.OrderItem;

public interface OrderItemDao extends JpaRepository<OrderItem, Integer> {
	
}
