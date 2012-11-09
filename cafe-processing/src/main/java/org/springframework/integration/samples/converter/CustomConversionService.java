package org.springframework.integration.samples.converter;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.integration.samples.cafe.dao.OrderItemDao;
import org.springframework.stereotype.Component;


@Component("conversionService")
public class CustomConversionService extends DefaultConversionService implements InitializingBean
{
   @Autowired
   private OrderItemDao dao;

   public void afterPropertiesSet() throws Exception
   {
      this.addConverter(new OrderItemConverter(dao));
   }
}