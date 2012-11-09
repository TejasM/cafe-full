package org.springframework.integration.samples.converter;

import java.util.Collections;
import java.util.Set;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.integration.samples.cafe.OrderItem;
import org.springframework.integration.samples.cafe.dao.OrderItemDao;

public class OrderItemConverter implements GenericConverter {
	
	private OrderItemDao dao;
	
	public OrderItemConverter(OrderItemDao dao) {
		this.dao = dao;
	}

	public Object convert(Object arg0, TypeDescriptor arg1, TypeDescriptor arg2) {
		String compare = arg0.toString();
		for(OrderItem item : dao.findAll()){
			if(item.equals(compare)){
				return item;
			}
		}
		return null;
	}

	public Set<ConvertiblePair> getConvertibleTypes() {
	      return Collections.singleton(new ConvertiblePair(String.class, OrderItem.class));
	}

}
