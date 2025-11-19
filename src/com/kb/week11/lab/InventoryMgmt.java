package com.kb.week11.lab;

import java.util.HashMap;
import java.util.Map;

public class InventoryMgmt {
	
	private Map<String, Integer> inventory = new HashMap<>();
	
	public synchronized void addStock(String productId, int quantity) {
		int currentStock = inventory.getOrDefault(productId,  0);
		inventory.put(productId,  currentStock + quantity);
		System.out.println(Thread.currentThread().getName() + " - Added " + quantity + " to " + productId + ". Total stock: " + inventory.get(productId));
		
		try {
			Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
	
	public synchronized boolean removeStock(String productId, int quantity) {
		int currentStock = inventory.getOrDefault(productId, 0);
		
		
		if (currentStock >= quantity) {
			inventory.put(productId, currentStock - quantity);
			System.out.println(Thread.currentThread().getName() + " - Removed " + quantity + " from " + productId + ". Total stock: " + inventory.get(productId));
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return true;
		}
		
		System.out.println(Thread.currentThread().getName() + " - Insufficient stock to remove " + quantity + " from " + productId + ". Current stock: " + currentStock);
		
	try {
		Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return false;
	}

	public synchronized void stopSystem() {
		System.out.println("Inventory system stopped.");
	}
}




