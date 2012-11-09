package org.springframework.integration.samples.cafe.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.integration.samples.cafe.Delivery;

public interface DeliveryDao extends JpaRepository<Delivery, Integer> {

}
