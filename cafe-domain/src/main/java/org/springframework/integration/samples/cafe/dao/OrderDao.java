package org.springframework.integration.samples.cafe.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.integration.samples.cafe.Order;

public interface OrderDao extends JpaRepository<Order, Integer> {

}
