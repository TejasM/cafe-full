package org.springframework.integration.samples.cafe.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.samples.cafe.Delivery;
import org.springframework.integration.samples.cafe.dao.DeliveryDao;
import org.springframework.transaction.annotation.Transactional;

@MessageEndpoint("JPAOutbound")
@Transactional
public class JPAOutbound implements DBOutboundInterface {
	
	@Autowired
	private DeliveryDao deliveryDao;
		
	/* (non-Javadoc)
	 * @see org.springframework.integration.samples.cafe.annotation.DBOutboundInterface#addDelivery(org.springframework.integration.samples.cafe.Delivery)
	 */
	public void addDelivery(Delivery delivery){
		deliveryDao.saveAndFlush(delivery);
		//orderDao.delete(delivery.getOrderNumber());
	}
}
