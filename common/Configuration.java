package common;

import java.io.*;
import java.util.*;
 
public class Configuration {

    private static BufferedReader reader;
	
    /** method that splits the file based on where the ":" symbol is and then calls appropriate methods to add them
     *  to their respective lists 
     */
	public static void configure(String file) throws IOException, ClassNotFoundException {
		reader = new BufferedReader(new FileReader(new File(file)));
		String line = reader.readLine();
		String[] strArray;
		while (line != null) {
	        strArray = line.split(":");
	        switch (strArray[0]) {
	        	case ("SUPPLIER"):
	        		addSupplier(strArray[1], strArray[2]);
	        		break;
	        	case ("INGREDIENT"):
	        		addIngredient(strArray[1], strArray[2], strArray[3], strArray[4], strArray[5]);
	        		break;
	        	case ("DISH"):
	        		addDish(strArray[1], strArray[2], strArray[3], strArray[4], strArray[5], strArray[6]);
	        		break;
	        	case ("POSTCODE"):
	        		addPostcode(strArray[1], strArray[2]);
	        		break;
	        	case ("USER"):
	        		addUser(strArray[1], strArray[2], strArray[3], strArray[4]);
	        		break;
	        	case ("ORDER"):
	        		addOrder(strArray[1], strArray[2]);
	        		break;
	        	case ("STOCK"):
	        		addStock(strArray[1], strArray[2]);
	        		break;
	        	case ("STAFF"):
	        		addStaff(strArray[1]);
	        		break;
	        	case ("DRONE"):
	        		addDrone(strArray[1]);
	        		break;
	        }
	    }
	}

	/**
	 * method to add new supplier to list of suppliers
	 * @param str1
	 * @param str2
	 */
	private static void addSupplier(String str1, String str2) {
		Supplier s = new Supplier(str1, Double.parseDouble(str2));
		StaffItemsManager.getSuppliers().add(s);
	}
	
	/**
	 * method to get a particular supplier from list of suppliers
	 * @param str
	 * @return supplier
	 */
	private static Supplier getSupplier(String str) {
		for (Supplier s: StaffItemsManager.getSuppliers()) {
			if(s.getName().equals(str))
				return s;
		}
		return null;
	}
	
	/**
	 * method to add new ingredient to list of ingredients
	 * @param str1
	 * @param str2
	 * @param str3
	 * @param str4
	 * @param str5
	 */
	private static void addIngredient(String str1, String str2, String str3, String str4, String str5) {
		Ingredient i = new Ingredient(str1, getSupplier(str3), str2, Integer.parseInt(str4), Integer.parseInt(str5), Integer.parseInt(str4));
		StaffItemsManager.getIngredients().add(i); 
	}
	
	/**
	 * method to get a particular ingredient from list of ingredients
	 * @param str
	 * @return Ingredient
	 */
	private static Ingredient getIngredient(String str) {
		for (Ingredient i: StaffItemsManager.getIngredients()) {
			if(i.getName().equals(str))
				return i;
		}
		return null;
	}
	
	/**
	 * method to add a new dish to list of dishes
	 * @param str1
	 * @param str2
	 * @param str3
	 * @param str4
	 * @param str5
	 * @param str6
	 */
	private static void addDish(String str1, String str2, String str3, String str4, String str5, String str6) {	
	    String[] array = str6.split(",");
	    Map<Ingredient, Number> recipe = new HashMap<Ingredient, Number>();
	    
	    for (int i = 0; i < array.length; i++){
            String[] ingredients = array[i].split(" ");
            recipe.put(getIngredient(ingredients[0]), Integer.parseInt(ingredients[2]));
	    }
	    
        Dish dish = new Dish(str1, str2, Double.parseDouble(str3), recipe, Integer.parseInt(str4), Integer.parseInt(str5), Integer.parseInt(str4));
	    StaffItemsManager.getDishes().add(dish);
	}
	
	/**
	 * method to get a particular dish from list of dishes
	 * @param str
	 * @return Dish
	 */
	private static Dish getDish(String str) {
		for(Dish d: StaffItemsManager.getDishes()) {
			if(d.getName().equals(str)) {
				return d;
			}
		}
		return null;
	}
	
	/**
	 * method to add new postcode to list of postcodes
	 * @param str1
	 * @param str2
	 */
	private static void addPostcode(String str1, String str2) {
		Postcode p = new Postcode(str1, Double.parseDouble(str2));
		StaffItemsManager.getPostcodes().add(p);
	}
	
	/**
	 * method to get a particular postcode from list of postcodes
	 * @param str
	 * @return Postcode
	 */
	private static Postcode getPostcode(String str) {
		for(Postcode p: StaffItemsManager.getPostcodes()) {
			if(p.getName().equals(str))
				return p;
		}
		return null;
	}
	
	/**
	 * method to add new user to list of users
	 * @param str1
	 * @param str2
	 * @param str3
	 * @param str4
	 */
	private static void addUser(String str1, String str2, String str3, String str4) {
		User u = new User(str1, str2, str3, getPostcode(str4));
		StaffItemsManager.getUsers().add(u);
	}
	
	/**
	 * method to get a particular user from list of users
	 * @param str
	 * @return User
	 */
	private static User getUser(String str) {
		for (User u: StaffItemsManager.getUsers()) {
			if(u.getName().equals(str)) {
				return u;
			}
		}
		return null;
	}
	
	/**
	 * method to add new order to list of orders
	 * @param str1
	 * @param str2
	 */
	private static void addOrder(String str1, String str2) {
		User user = getUser(str1);
        String[] array = str2.split(",");
        
        for (int i = 0; i < array.length; i++){
            String[] dishes = array[i].split(" ");
            user.addDish(getDish(dishes[0]), Integer.valueOf(dishes[2]));
        }
        
        String name = "Order" + Integer.toString(user.getUserOrders().size()+1);
        Order newOrder = new Order(name, user);
        user.addUserOrder(newOrder);
        user.clearBasket();
	}
	
	/**
	 * method to add the current quantity for a dish
	 * @param str1
	 * @param str2
	 */
	private static void addStock(String str1, String str2) {
		for (Dish d: StaffItemsManager.getDishes()) {
			if(d.getName().equals(str1)) {
				d.getDishManager().setQuantity(Integer.parseInt(str2));
			}
		}
	}
	
	/**
	 * method to add new staff to list of staff
	 * @param str
	 */
	private static void addStaff(String str) {
		Staff s = new Staff(str);
		StaffItemsManager.getStaff().add(s);
	}
	
	/**
	 * method to add new drone to list of drones
	 * @param str
	 */
	private static void addDrone(String str) {
		Integer i = Integer.parseInt(str);
		Drone d = new Drone("Drone"+i, i);
		StaffItemsManager.getDrones().add(d);
	}
}
