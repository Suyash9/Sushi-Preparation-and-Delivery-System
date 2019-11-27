package common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User extends Model implements Serializable{

	private static final long serialVersionUID = 6446882539398995855L;
	private String username;
	private String password;
	private String address;
	private Postcode postcode;
    private Map<Dish, Number> basket = new HashMap<Dish, Number>();
	private List<Order> userOrders = new ArrayList<Order>();

    public User(String username, String password, String address, Postcode postcode) {
        this.username = username;
        this.password = password;
        this.address = address;
        this.postcode = postcode;
    }
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    /**
     * method that adds order to the list of the users orders
     * @param order
     */
    public void addUserOrder(Order order) {
    	userOrders.add(order);
    }
    
    /**
     * method that returns cost of the items in user's basket
     * @return double
     */
    public Number getTotalCost () {
    	double total = 0;

        for (Dish d : basket.keySet()) {
            total += d.getPrice() * basket.get(d).doubleValue();
        }

        return total;
    }
    
    /**
     * method that adds dish to user's basket
     * @param dish
     */
    public void addDish(Dish dish) {
        int amount = 1;

        for (Dish d : basket.keySet()) {
            if (d.equals(dish)) {
                amount += basket.get(dish).intValue();
                break;
            }
        }
        
        basket.put(dish, amount);
    }
    
    /**
     * method to add dish to basket when dish and amount both are specified 
     * @param dish
     * @param amount
     */
    public void addDish(Dish dish, Integer amount) {
        basket.put(dish, amount);
    }

    /**
     * method to remove dish from user's basket
     * @param dish
     */
    public void removeDish(Dish dish) {
        for (Dish d : basket.keySet()) {
            if (d.equals(dish)) {
                int amount = basket.get(dish).intValue() - 1;

                if (amount > 0) {
                    basket.put(dish, amount);
                } else {
                    basket.remove(dish);
                }
            }
        }
    }
    
    /**
     * method to replace dish in basket with a dish with a certain amount
     * @param dish
     * @param newAmount
     */
    public void updateBasket(Dish dish, Integer newAmount) {
    	basket.replace(dish, newAmount);
    }
    
    /**
     * method to clear user's basket
     */
    public void clearBasket() {
    	basket.clear();
    }
    
    @Override
	public String getName() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getAddress() {
		return address;
	}

	public Postcode getPostcode() {
		return postcode;
	}

	public Map<Dish, Number> getBasket() {
		return basket;
	}
	
	public List<Order> getUserOrders() {
		return userOrders;
	}
}
