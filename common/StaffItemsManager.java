package common;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Class that contains arraylists of various classes and maintains of them.
 * 
 */
public class StaffItemsManager {
	
	private static List<Postcode> postcodes = new ArrayList<Postcode>();			//list of postcodes
	private static List<Dish> dishes = new ArrayList<Dish>();						//list of dishes
	private static List<Ingredient> ingredients = new ArrayList<Ingredient>();		//list of ingredients
	private static List<Supplier> suppliers = new ArrayList<Supplier>();			//list of suppliers
	private static List<Drone> drones = new ArrayList<Drone>();						//list of drones
	private static List<Staff> staff = new ArrayList<Staff>(); 						//list of staff
	private static List<Order> orders = new ArrayList<Order>();						//list of orders
	private static List<User> users = new ArrayList<User>();						//list of users
	
	static {
		addDishesandIngredients();
		addSuppliers();
		addPostcodes();
		addDrones();
		addStaff();
		addUsers();
		Comms.sendUsers(users);
		Comms.sendPostcodes(postcodes);
	}
	
	/**
	 * method to add dishes and ingredients to their respective lists
	 */
	private static void addDishesandIngredients() {
		
		final Ingredient salmon = new Ingredient("Salmon", new Supplier("Tesco", 20.4), "kg", 7, 5, 3);
		final Ingredient salt = new Ingredient("Salt", new Supplier("Sainsbury's", 10.6), "kg", 5, 2, 5);
		final Ingredient vinegar = new Ingredient("Vinegar", new Supplier("Tesco Express", 30.2), "l", 10, 5, 7);
		final Ingredient rice = new Ingredient("Rice", new Supplier("Waitrose", 25.4), "kg", 25, 15, 10);
		final Ingredient cucumber = new Ingredient("Cucumber", new Supplier("Aldi", 40.7), "long strip", 35, 25, 15);
		final Ingredient tuna = new Ingredient("Tuna", new Supplier("Asda", 32.3), "kg", 15, 10, 7);
		final Ingredient seaweed = new Ingredient("Seaweed", new Supplier("Morrison's", 42.9), "sheet", 30, 22, 10);
		
		ingredients.add(salmon);
		ingredients.add(salt);
		ingredients.add(vinegar);
		ingredients.add(rice);
		ingredients.add(cucumber);
		ingredients.add(tuna);
		ingredients.add(seaweed);
		
		final String nigiriDesc = "A topping, usually fish, served on top of sushi rice";
		final String sashimiDesc = "Fish or shellfish served alone (no rice)";
		final String makiDesc = "Rice and filling wrapped in seaweed";
		final String uramakiDesc = "Similar to the above, but rice is on the outside and seaweed wraps around the filling";
		final String temakiDesc = "Sushi that has been hand-rolled into a cone shape";
		
		final HashMap<Ingredient, Number> nigiriRecipe = new HashMap<Ingredient, Number>();
		nigiriRecipe.put(salmon, 226);
		nigiriRecipe.put(rice, 220);
		nigiriRecipe.put(vinegar, 45);
		
		final HashMap<Ingredient, Number> sashimiRecipe = new HashMap<Ingredient, Number>();
		sashimiRecipe.put(salmon, 150);
		sashimiRecipe.put(rice, 227);
		sashimiRecipe.put(cucumber, 1);
		
		final HashMap<Ingredient, Number> makiRecipe = new HashMap<Ingredient, Number>();
		makiRecipe.put(rice, 255);
		makiRecipe.put(tuna, 340);
		makiRecipe.put(cucumber, 1);
		makiRecipe.put(seaweed, 5);
		
		final HashMap<Ingredient, Number> uramakiRecipe = new HashMap<Ingredient, Number>();
		uramakiRecipe.put(rice, 660);
		uramakiRecipe.put(vinegar, 50);
		uramakiRecipe.put(cucumber, 1);
		uramakiRecipe.put(tuna, 320);
		
		final Map<Ingredient, Number> temakiRecipe = new HashMap<Ingredient, Number>();
		makiRecipe.put(rice, 256);
		makiRecipe.put(salmon, 338);
		makiRecipe.put(seaweed, 3);
		
		final Dish nigiri = new Dish("Nigiri", nigiriDesc, 5.25, nigiriRecipe, 10, 5, 8);
		final Dish sashimi = new Dish("Sashimi", sashimiDesc, 6.50, sashimiRecipe, 12, 6, 8);
		final Dish maki = new Dish("Maki", makiDesc, 7.50, makiRecipe, 15, 7, 8);
		final Dish uramaki = new Dish("Uramaki", uramakiDesc, 8.25, uramakiRecipe, 14, 7, 8);
		final Dish temaki = new Dish("Temaki", temakiDesc, 9.75, temakiRecipe, 16, 8, 8);
		
		dishes.add(nigiri);
		dishes.add(sashimi);
		dishes.add(maki);
		dishes.add(uramaki);
		dishes.add(temaki);
	}
	
	/**
	 * method to add suppliers to list
	 */
	private static void addSuppliers() {
		suppliers.add(new Supplier("Tesco", 20.4));
		suppliers.add(new Supplier("Sainsbury's", 10.6));
		suppliers.add(new Supplier("Tesco Express", 30.2));
		suppliers.add(new Supplier("Waitrose", 25.4));
		suppliers.add(new Supplier("Aldi", 40.7));
		suppliers.add(new Supplier("Asda", 32.3));
		suppliers.add(new Supplier("Morrisons", 42.9));
	}
	
	/**
	 * method to add postcodes to list of postcodes
	 */
	private static void addPostcodes() {
		postcodes.add(new Postcode("SO18 2NU", 2.3));
		postcodes.add(new Postcode("SO14 1GJ", 3.1));
		postcodes.add(new Postcode("SO17 7PU", 1.7));
		postcodes.add(new Postcode("SO16 1QM", 2.6));
		postcodes.add(new Postcode("SO15 1AD", 0.8));
	}
	
	/**
	 * method to add staff to list of staff
	 */
	private static void addStaff() {
		staff.add(new Staff("James"));
		staff.add(new Staff("Mo"));
	}

	/**
	 * method to add drone to list of drones
	 */
	private static void addDrones() {
		drones.add(new Drone("Drone1", 100));
		drones.add(new Drone("Drone2", 70));
	}
	
	/**
	 * method to add users to list of users
	 */
	public static void addUsers(){
		users.add(new User("Roberto", "boBby123", "23 Wessex Lane, Southampton", new Postcode("SO16 1QM", 2.6)));
		users.add(new User("Sadio", "sadIO364", "22 Mayflower, Southampton", new Postcode ("SO18 2NU", 2.3)));
	}
	
	/**
	 * method to add order to list of orders
	 * @param order
	 */
	public static void addOrders(Order order) {
		orders.add(order);
	}
	
	/**
	 * method that initiate delivery of order using drone 
	 * @param order
	 */
	public static synchronized void requestDelivery(Order order) {
		for(Drone d: drones) {
			if(!d.isActive()) {
				System.out.println(d.getName() + " is delivering " + order.getName());
				d.delivery(order);
				break;
			}
		}
	}
	
	public static void receiveOrder(File order) {
		if(order!=null) {
			try {
				Comms.messageOrder(order);
				addOrder(Comms.messageOrder(order));
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void addOrder(Order order) {
		requestDelivery(order);
		order.orderReceived();
		orders.add(order);
	}

	public static List<Postcode> getPostcodes() {
		return postcodes;
	}

	public static List<Dish> getDishes() {
		return dishes;
	}

	public static List<Ingredient> getIngredients() {
		return ingredients;
	}

	public static List<Supplier> getSuppliers() {
		return suppliers;
	}

	public static List<Drone> getDrones() {
		return drones;
	}

	public static List<Staff> getStaff() {
		return staff;
	}

	public static List<Order> getOrders() {
		return orders;
	}

	public static List<User> getUsers() {
		return users;
	}
}
