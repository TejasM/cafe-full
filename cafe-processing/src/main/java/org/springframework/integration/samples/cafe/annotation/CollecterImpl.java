package org.springframework.integration.samples.cafe.annotation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.integration.samples.cafe.Delivery;
import org.springframework.integration.samples.cafe.dao.DeliveryDao;
import org.springframework.stereotype.Component;

@Component
public class CollecterImpl implements Collecter {
		
	@Autowired
	private DeliveryDao deliveryDao;
	
	
	public List<Delivery> getDelivered(){
		return deliveryDao.findAll(new Sort("orderNumber"));
	}

}
