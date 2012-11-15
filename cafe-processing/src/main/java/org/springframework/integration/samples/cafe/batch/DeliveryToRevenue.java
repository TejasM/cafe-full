package org.springframework.integration.samples.cafe.batch;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.integration.samples.cafe.Delivery;
import org.springframework.integration.samples.cafe.Drink;
import org.springframework.integration.samples.cafe.DrinkType;
import org.springframework.integration.samples.cafe.annotation.Revenue;

public class DeliveryToRevenue implements ItemProcessor<Delivery, Revenue>{
	
	private static final Map<DrinkType, Double> priceList;
	static {
		priceList = new HashMap<DrinkType, Double>();
		priceList.put(DrinkType.CAPPUCCINO, 3.75);
		priceList.put(DrinkType.ESPRESSO, 2.75);
		priceList.put(DrinkType.LATTE, 2.85);
		priceList.put(DrinkType.MOCHA, 3.75);
	}

	public Revenue process(Delivery item) throws Exception {
		Revenue revenue = new Revenue();
		revenue.setOrderId(item.getId());
		Double sum = 0.0;
		for(Drink drink: item.getDeliveredDrinks()){
			sum += priceList.get(drink.getDrinkType())*drink.getShots();
		}
		revenue.setRevenue(sum);
		System.out.println("The revenue was: " + sum);
		return revenue;
	}

}
