package common;

import java.time.LocalTime;
import java.util.Map;

public class Drone extends Model implements Runnable {
	
	private static final long serialVersionUID = 6613147626169007647L;
	private Boolean restocking = false;			//boolean to check if drone is currently restocking
	private Boolean delivering = false;			//boolean to check if drone is currently delivering
	private Boolean active = false;				//boolean to check if drone is currently active
	private Boolean enabled = true;				//boolean to check if drone is enabled 
	private Integer speed;						//speed of drone
	private Thread thread;
	
	public Drone (String name, Integer speed) {
		this.name = name;
		this.speed = speed;
	}
		
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void run() {
		while(!active && enabled) {
			droneRestock(Stock.checkIngredientStock());
		}
	}
	
	/**
	 * method to monitor delivery activities of drone
	 * @param order
	 */
	public void delivery(Order order) {
		LocalTime time = LocalTime.now().withNano(0);
		
		if (!restocking) {
			if(order!=null) {
				delivering = true;
				order.deliverOrder();
				
				Postcode postcode = order.getUser().getPostcode();
				Integer approxTime = (int) (postcode.getDistance()/speed);
				LocalTime deliveryTime = time.plusMinutes(approxTime);
				Integer i = time.compareTo(deliveryTime);
				
				while(i==-1) {
					delivering = true;
					active = true;
				}
				System.out.println("Estimated delivery time to " + postcode.getName() + ": " + approxTime);
				for (Map.Entry<Dish, Number> entry : order.getUser().getBasket().entrySet()) {
					for (Dish dish : StaffItemsManager.getDishes()) {
						if (dish != null && (int) entry.getValue() >= dish.getDishManager().getQuantity()) {
							dish.getDishManager().usage((int) entry.getValue());
						} else {
							System.err.println(dish.getName() +" stock is low");
						}
					}
				}
				System.out.println("Delivery to " + postcode.getName() + " completed at: " + deliveryTime);
				Stock.checkDishStock();
			}
		} else
			System.out.println("Drone is currently busy restocking.");
		order.completedOrder();
		active = false;
		delivering = false;
	}
	
	/**
	 * method to monitor restocking of ingredients using drone
	 * @param ingredient
	 */
	public void droneRestock(Ingredient ingredient) {
		LocalTime time = LocalTime.now().withNano(0);
		
		if (!delivering) {
			restocking = true;
			Double d = ingredient.getSupplier().getDistance();
			Integer approxTime = (int) (d/speed);
			LocalTime restockTime = time.plusMinutes(approxTime);
			Integer i = time.compareTo(restockTime);
			
			while(i==-1) {
				restocking = true;
				active = true;
			}
			
			System.out.println("Estimated restocking time: " + approxTime );
			Stock.checkIngredientStock();
			System.out.println(ingredient.getName() + " has been restocked.");
		} else
			System.out.println("Drone is currently busy delivering.");
		active = false;
		restocking = false;
	}
	
	/**
	 * method to start thread
	 */
	public void start() {
		System.out.println(name + " is currently working.");
		if (thread.equals(null)) {
			thread = new Thread (this, name);
			thread.start();
		}
	}
	
	/**
	 * method that returns current status of drone
	 * @return String
	 */
	public String getDroneStatus() {
		if(restocking) 
			return name + " is currently restocking.";
		else if(delivering)
			return name + " is currently delivering.";
		return name + " is idle.";
	}

	public Integer getSpeed() {
		return speed;
	}

	public Boolean getRestocking() {
		return restocking;
	}
	
	public Boolean getDelivering() {
		return delivering;
	}

	public Boolean isActive() {
		return active;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
}
