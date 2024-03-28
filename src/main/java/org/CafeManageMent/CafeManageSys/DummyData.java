package org.CafeManageMent.CafeManageSys;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.dataBases.DataManagement;
import com.dataBases.PerformOperations;

public class DummyData {
	
	public DummyData() {
		hoola();
	}
	
	public void hoola() {
		 // Creating a Map to store items and their prices
        Map<String, Integer> itemPrices = new HashMap<>();
        itemPrices.put("Tea", 15);
        itemPrices.put("GreenTea", 40);
        itemPrices.put("Coffee", 40);
        itemPrices.put("Chocolate-Pastry", 50);
        itemPrices.put("Nothing", 100);
        itemPrices.put("Burger", 120);
        itemPrices.put("Pizza", 120);
        PerformOperations ope = new PerformOperations();
        // Simulating data storage for the last 60 days
        LocalDate today = LocalDate.now();
        Random random = new Random();
        for (int i = 0; i < 60; i++) {
            LocalDate date = today.minusDays(i);
            int itemCount = random.nextInt(3) + 1; // Randomly pick 1 to 3 items per day
            for (int j = 0; j < itemCount; j++) {
                String randomItem = getRandomItem(itemPrices);
                int price = itemPrices.get(randomItem);
                int quantity = random.nextInt(5) + 1; // Random quantity between 1 and 5
               
                ope.updateInBillDatabase(new DataManagement(randomItem, String.valueOf(price), String.valueOf(quantity), date.toString(), String.valueOf(price * quantity)));
            }
	}
	}
	  // Method to get a random item from the map
    private static String getRandomItem(Map<String, Integer> itemPrices) {
        List<String> items = new ArrayList<>(itemPrices.keySet());
        Random random = new Random();
        return items.get(random.nextInt(items.size()));
    }
}
