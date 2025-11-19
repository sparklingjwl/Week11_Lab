package com.kb.week11.lab;

import java.util.Scanner;

public class DriverClass {

	public static void main(String[] args) {
		
		InventoryMgmt inventory = new InventoryMgmt();
		Scanner scanner = new Scanner(System.in);
		
		// Get initial product setup
		System.out.println("Welcome to the Inventory Management System");
		System.out.print("Enter product ID: ");
		String productId = scanner.next();
		System.out.print("Enter initial stock quantity: ");
		int initialStock = scanner.nextInt();
		inventory.addStock(productId, initialStock);
		
		// Get operation quantities
		System.out.println("\nEnter warehouse restock quantity: ");
		int warehouseQty = scanner.nextInt();
		
		System.out.println("\nEnter supplier delivery quantity: ");
		int supplierQty = scanner.nextInt();
		
		System.out.println("Enter customer order quantity to fulfill: ");
		int orderQty = scanner.nextInt();
		
		scanner.close();
		
		// Create threads		
		Runnable warehouseTask = () -> {
			inventory.addStock(productId, warehouseQty);
		};
			
		Runnable supplierTask = () -> {
			inventory.addStock(productId, supplierQty);				
		};
			
		Runnable salesTask = () -> {
			inventory.removeStock(productId, orderQty);
		};
			
		// Create three threads for the tasks
		Thread warehouseThread = new Thread(warehouseTask, "Warehouse-Restock");
		Thread supplierThread = new Thread(supplierTask, "Supplier-Delivery");
		Thread salesThread = new Thread(salesTask, "Sales-Order");
			
		// Start the add threads
		System.out.println("Starting restock operations...");
		warehouseThread.start();
		supplierThread.start();
			
		// Wait for restock threads to finish
		try {
			warehouseThread.join();
			supplierThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// Start the sales thread
		System.out.println("Processing sales order...");
		salesThread.start();
		
		// Wait for sales thread to finish		
		try {
			salesThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();			
		}

		System.out.println("Inventory management completed");		
	}
}
	

