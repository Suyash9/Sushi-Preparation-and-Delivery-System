package server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import common.*;

/**
 * Class that implements the server interface.
 *
 */
public class ServerLink implements ServerInterface {

	@Override
	public void loadConfiguration(String filename) throws FileNotFoundException {
		try {
			Configuration.configure(filename);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setRestockingIngredientsEnabled(boolean enabled) {
		for (Drone d: StaffItemsManager.getDrones()) {
			d.setEnabled(enabled);
		}
	}

	@Override
	public void setRestockingDishesEnabled(boolean enabled) {
		for(Staff s: StaffItemsManager.getStaff()) {
			s.setEnabled(enabled);
		}
	}

	@Override
	public void setStock(Dish dish, Number stock) {
		for(Dish d : StaffItemsManager.getDishes()) {
		    if(d.getName().equals(dish.getName())) {
		    	d.getDishManager().setQuantity((int) stock);
		    }
		}
	}

	@Override
	public void setStock(Ingredient ingredient, Number stock) {
		for(Ingredient i : StaffItemsManager.getIngredients()) {
		    if(i.getName().equals(ingredient.getName())) {
		    	i.getIngredientManager().setQuantity((int) stock);
		    }
		}
	}

	@Override
	public List<Dish> getDishes() {
		Comms.sendDishes(StaffItemsManager.getDishes());
		return StaffItemsManager.getDishes();
	}

	@Override
	public Dish addDish(String name, String description, Number price, Number restockThreshold, Number restockAmount) {
		Dish newDish = new Dish(name, description, (double) price, new HashMap<Ingredient, Number>(), (int) restockAmount, (int) restockThreshold, (int) restockAmount);
		StaffItemsManager.getDishes().add(newDish);
		return newDish;
	}

	@Override
	public void removeDish(Dish dish) throws UnableToDeleteException {
		for(Dish d : StaffItemsManager.getDishes()) {
		    if(d.getName().equals(dish.getName())) {
		    	StaffItemsManager.getDishes().remove(d);
		    }
		}
	}

	@Override
	public void addIngredientToDish(Dish dish, Ingredient ingredient, Number quantity) {
		for(Dish d : StaffItemsManager.getDishes()) {
		    if(d.getName().equals(dish.getName())) {
		    	d.getDishRecipe().put(ingredient, quantity);
		    }
		}
	}

	@Override
	public void removeIngredientFromDish(Dish dish, Ingredient ingredient) {
		for(Dish d : StaffItemsManager.getDishes()) {
		    if(d.getName().equals(dish.getName())) {
		    	List<Ingredient> ingredients = new ArrayList<Ingredient>(dish.getDishRecipe().keySet());
		    	for(Ingredient i : StaffItemsManager.getIngredients()) {
		    		if(i.getName().equals(ingredient.getName())) {
		    			d.getDishRecipe().remove(i);
		    		}
		    	}
		    }
		}
	}

	@Override
	public void setRecipe(Dish dish, Map<Ingredient, Number> recipe) {
		for(Dish d : StaffItemsManager.getDishes()) {			
		    if(d.getName().equals(dish.getName())) {
		    	d.setDishRecipe(recipe);
		    }
		}
	}

	@Override
	public void setRestockLevels(Dish dish, Number restockThreshold, Number restockAmount) {
		for(Dish d : StaffItemsManager.getDishes()) {
		    if(d.getName().equals(dish.getName())) {
		    	d.getDishManager().setThreshold((int) restockThreshold);
		    	d.getDishManager().setRestock((int) restockAmount);
		    }
		}
	}

	@Override
	public Number getRestockThreshold(Dish dish) {
		for(Dish d : StaffItemsManager.getDishes()) {
		    if(d.getName().equals(dish.getName())) {
		    	return d.getDishManager().getThreshold();
		    }
		}
		return null;
	}

	@Override
	public Number getRestockAmount(Dish dish) {
		for(Dish d : StaffItemsManager.getDishes()) {
		    if(d.getName().equals(dish.getName())) {
		    	return d.getDishManager().getRestock();
		    }
		}
		return null;
	}

	@Override
	public Map<Ingredient, Number> getRecipe(Dish dish) {
		for(Dish d : StaffItemsManager.getDishes()) {
		    if(d.getName().equals(dish.getName())) {
		    	return d.getDishRecipe();
		    }
		}
		return null;
	}

	@Override
	public Map<Dish, Number> getDishStockLevels() {
		Map<Dish, Number> dishQuantities = new HashMap<Dish, Number>();
		for(Dish d : StaffItemsManager.getDishes()) {
		    dishQuantities.put(d, d.getDishManager().getQuantity());
		}
		return dishQuantities;
	}

	@Override
	public List<Ingredient> getIngredients() {
		return StaffItemsManager.getIngredients();
	}

	@Override
	public Ingredient addIngredient(String name, String unit, Supplier supplier, Number restockThreshold,
			Number restockAmount) {
		Ingredient newIngredient = new Ingredient(name, supplier, unit, (int) restockAmount, (int) restockThreshold, (int) restockAmount);
		StaffItemsManager.getIngredients().add(newIngredient);
		return newIngredient;
	}

	@Override
	public void removeIngredient(Ingredient ingredient) throws UnableToDeleteException {
		for(Ingredient i : StaffItemsManager.getIngredients()) {
		    if(i.getName().equals(ingredient.getName())) {
		    	StaffItemsManager.getIngredients().remove(i);
		    }
		}
	}

	@Override
	public void setRestockLevels(Ingredient ingredient, Number restockThreshold, Number restockAmount) {
		for(Ingredient i : StaffItemsManager.getIngredients()) {
		    if(i.getName().equals(ingredient.getName())) {
		    	i.getIngredientManager().setThreshold((int) restockThreshold);
		    	i.getIngredientManager().setQuantity((int) restockAmount);
		    }
		}
	}

	@Override
	public Number getRestockThreshold(Ingredient ingredient) {
		for(Ingredient i : StaffItemsManager.getIngredients()) {
		    if(i.getName().equals(ingredient.getName())) {
		    	return i.getIngredientManager().getThreshold();
		    }
		}
		return null;
	}

	@Override
	public Number getRestockAmount(Ingredient ingredient) {
		for(Ingredient i : StaffItemsManager.getIngredients()) {
		    if(i.getName().equals(ingredient.getName())) {
		    	return i.getIngredientManager().getRestock();
		    }
		}
		return null;
	}

	@Override
	public Map<Ingredient, Number> getIngredientStockLevels() {
		Map<Ingredient, Number> ingredientQuantities = new HashMap<Ingredient, Number>();
		for(Ingredient i : StaffItemsManager.getIngredients()) {
		    ingredientQuantities.put(i, i.getIngredientManager().getQuantity());
		}
		return ingredientQuantities;
	}

	@Override
	public List<Supplier> getSuppliers() {
		return StaffItemsManager.getSuppliers();
	}

	@Override
	public Supplier addSupplier(String name, Number distance) {
		Supplier newSupplier = new Supplier(name, (double) distance); 
		StaffItemsManager.getSuppliers().add(newSupplier);
		return newSupplier;
	}

	@Override
	public void removeSupplier(Supplier supplier) throws UnableToDeleteException {
		for(Supplier s : StaffItemsManager.getSuppliers()) {
		    if(s.getName().equals(supplier.getName())) {
		    	StaffItemsManager.getSuppliers().remove(s);
		    }
		}
	}

	@Override
	public Number getSupplierDistance(Supplier supplier) {
		for(Supplier s : StaffItemsManager.getSuppliers()) {
		    if(s.getName().equals(supplier.getName())) {
		    	return s.getDistance();
		    }
		}
		return null;
	}

	@Override
	public List<Drone> getDrones() {
		return StaffItemsManager.getDrones();
	}

	@Override
	public Drone addDrone(Number speed) {
		String str = String.valueOf(speed);
		Drone newDrone = new Drone("Drone"+str, (int) speed);
		StaffItemsManager.getDrones().add(newDrone);
		return newDrone;
	}

	@Override
	public void removeDrone(Drone drone) throws UnableToDeleteException {
		for(Drone d : StaffItemsManager.getDrones()) {
		    if(d.getName().equals(drone.getName())) {
		    	StaffItemsManager.getDrones().remove(d);
		    }
		}
	}

	@Override
	public Number getDroneSpeed(Drone drone) {
		for(Drone d : StaffItemsManager.getDrones()) {
		    if(d.getName().equals(drone.getName())) {
		    	return d.getSpeed();
		    }
		}
		return null;
	}

	@Override
	public String getDroneStatus(Drone drone) {
		for(Drone d : StaffItemsManager.getDrones()) {
		    if(d.getName().equals(drone.getName())) {
		    	return d.getDroneStatus();
		    }
		}
		return null;
	}

	@Override
	public List<Staff> getStaff() {
		return StaffItemsManager.getStaff();
	}

	@Override
	public Staff addStaff(String name) {
		Staff newStaff = new Staff(name);
		StaffItemsManager.getStaff().add(newStaff);
		return newStaff;
	}

	@Override
	public void removeStaff(Staff staff) throws UnableToDeleteException {
		for(Staff s : StaffItemsManager.getStaff()) {
		    if(s.getName().equals(staff.getName())) {
		    	StaffItemsManager.getStaff().remove(s);
		    }
		}
	}

	@Override
	public String getStaffStatus(Staff staff) {
		for(Staff s : StaffItemsManager.getStaff()) {
		    if(s.getName().equals(staff.getName())) {
		    	return s.getStaffStatus();
		    }
		}
		return null;
	}
	
	@Override
	public List<Order> getOrders() {
		return StaffItemsManager.getOrders();
	}

	@Override
	public void removeOrder(Order order) throws UnableToDeleteException {
		for(Order o : StaffItemsManager.getOrders()) {
		    if(o.getName().equals(order.getName())) {
				StaffItemsManager.getOrders().remove(o);
		    }
		}
	}

	@Override
	public Number getOrderDistance(Order order) {
		for(Order o : StaffItemsManager.getOrders()) {
		    if(o.getName().equals(order.getName())) {
		    	return order.getUser().getPostcode().getDistance();
		    }
		}
		return null;
	}

	@Override
	public boolean isOrderComplete(Order order) {
		for(Order o : StaffItemsManager.getOrders()) {
		    if(o.getName().equals(order.getName())) {
				return order.isCompleted();
		    }
		}
		return false;
	}

	@Override
	public String getOrderStatus(Order order) {
		for(Order o : StaffItemsManager.getOrders()) {
		    if(o.getName().equals(order.getName())) {
		    	return order.getOrderStatus();
		    }
		}
		return null;
	}

	@Override
	public Number getOrderCost(Order order) {
		for(Order o : StaffItemsManager.getOrders()) {
		    if(o.getName().equals(order.getName())) {
		    	return order.getCost();
		    }
		}
		return null;
	}

	@Override
	public List<Postcode> getPostcodes() {
		Comms.sendPostcodes(StaffItemsManager.getPostcodes());
		return StaffItemsManager.getPostcodes();
	}

	@Override
	public void addPostcode(String code, Number distance) {
		Postcode newPostcode = new Postcode(code, (double) distance);
		StaffItemsManager.getPostcodes().add(newPostcode);
	}

	@Override
	public void removePostcode(Postcode postcode) throws UnableToDeleteException {
		for(Postcode p: StaffItemsManager.getPostcodes()) {
		    if(p.getName().equals(postcode.getName())) {
		    	StaffItemsManager.getPostcodes().remove(p);
		    }
		}
	}

	@Override
	public List<User> getUsers() {
		Comms.sendUsers(StaffItemsManager.getUsers());
		return StaffItemsManager.getUsers();		
	}

	@Override
	public void removeUser(User user) throws UnableToDeleteException {
		for(User u: StaffItemsManager.getUsers()) {
		    if(u.getName().equals(user.getName())) {
		    	StaffItemsManager.getUsers().remove(u);
		    }
		}
	}

	@Override
	public void addUpdateListener(UpdateListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyUpdate() {
		// TODO Auto-generated method stub
		
	}

}
