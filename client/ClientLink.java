package client;

import java.io.IOException;
import java.util.*;
import common.*;

/** 
 * Class that implements the client interface.
 * 
 */
public class ClientLink implements ClientInterface {
	
	private static List<User> registeredUsers = new ArrayList<User>();		//list of registered users
	private static List<User> onlineUsers = new ArrayList<User>();			//list of users currently online
	
	@Override 
	public User register(String username, String password, String address, Postcode postcode) {			
		if (username.length() > 15 || username.length() < 5) {
			System.err.println("Username is too short. Please enter another username.");
			return null;
		} else if (checkUsername(username)) {
			System.err.println("This username already exists. Please enter another username.");
			return null;
		} else if (password.length()<5) {
			System.err.println("Password is too short. Please enter a character with at least 5 characters.");
			return null;
		} else if (!password.matches(".*[0-9].*") || !password.matches(".*[A-Z].*") || !password.matches(".*[a-z].*")) {
			System.err.println("Password is invalid. Password should be a combination of uppercase, lowercase letters and digits.");
			return null;
		}
		
		User user = new User(username, password, address, postcode);
		registeredUsers.add(user);
		onlineUsers.add(user);
		return user;
	}

	/**
	 * method that checks if a username already exists in the list of registered users
	 * @param str
	 * @return boolean
	 */
	private Boolean checkUsername(String str) {
		for (User u: registeredUsers) {
			if(u.getName().equals(str))
				return true;
		}
		return false;
	}

	@Override
	public User login(String username, String password) {
		for(User user: registeredUsers) {
		    if(user.getName().equals(username) && user.getPassword().equals(password)) {
		    	onlineUsers.add(user);
		    	return user;
		    }    
		}
		System.err.println("Invalid username or password.");
		return null;
	}

	@Override
	public List<Postcode> getPostcodes() {
		try {
			return Comms.messagePostcodes();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Dish> getDishes() {
		try {
			return Comms.messageDishes();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getDishDescription(Dish dish) {
		return dish.getDescription();
	}

	@Override
	public Number getDishPrice(Dish dish) {
		return dish.getPrice();
	}

	@Override
	public Map<Dish, Number> getBasket(User user) {
		return user.getBasket();
	}

	@Override
	public Number getBasketCost(User user) {
		return user.getTotalCost();
	}

	@Override
	public void addDishToBasket(User user, Dish dish, Number quantity) {
		user.getBasket().put(dish, quantity);
	}

	@Override
	public void updateDishInBasket(User user, Dish dish, Number quantity) {
		user.updateBasket(dish, quantity.intValue());
	}

	@Override
	public Order checkoutBasket(User user) {
		Order order = new Order(user.getName() + "'s Order " + String.valueOf(user.getUserOrders().size()+1), user);
		Comms.sendClientMessage(order);
		user.addUserOrder(order);
		try {
			Comms.messageOrder(Comms.sendClientMessage(order));
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return order;
	}

	@Override
	public void clearBasket(User user) {
		user.clearBasket();
	}

	@Override
	public List<Order> getOrders(User user) {
		return user.getUserOrders();
	}

	@Override
	public boolean isOrderComplete(Order order) {
		return order.isCompleted();
	}

	@Override
	public String getOrderStatus(Order order) {
		return order.getOrderStatus();
	}

	@Override
	public Number getOrderCost(Order order) {
		return order.getCost();
	}

	@Override
	public void cancelOrder(Order order) {
		order.cancelOrder();
	}
	
	/**
	 * method that gets the list of users from comms 
	 * @return List<User>
	 */
	public List<User> getUsers(){
		try {
			return Comms.messageUsers();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;
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
