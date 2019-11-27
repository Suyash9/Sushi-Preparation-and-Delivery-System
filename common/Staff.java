package common;

import java.time.LocalTime;
import java.util.Random;

public class Staff extends Model implements Runnable {
	
	private String name;
	private Boolean active = false;			//boolean to check if staff is currently active
	private Boolean enabled = true;			//boolean to check if staff is enabled
	private Thread thread;
	
	public Staff(String name) {
		this.name = name;
		System.out.println("Hired " + name);
	}
	
	@Override
	public void run() {
		while(!active && enabled)
			prepareDish(Stock.checkDishStock());
	}
	
	/**
	 * method to prepare dish and restock if necessary
	 */
	public synchronized void prepareDish(Dish dish) {
		LocalTime time = LocalTime.now().withNano(0);
		Integer prep = new Random().nextInt(35) + 10;
		LocalTime prepTime = time.plusMinutes(prep);
		Integer i = time.compareTo(prepTime);
		
		if (dish != null) {
			while(i == -1) {
				active = true;
			}
			System.out.println(name + " is currently preparing " + dish.getName());
			System.out.println(name + " spent " + prep + " minutes preparing " + dish.getName());
			Stock.checkDishStock();
		}
		active = false;
	}
	
	/**
	 * method to start thread
	 */
	public void start() {
		System.out.println(name + " is starting work");
		if (thread == null) {
			thread = new Thread (this, name);
			thread.start();
		}
	}
	
	/**
	 * method that returns current status of the staff
	 * @return string
	 */
	public String getStaffStatus() {
		if(active) 
			return name + " is currently preparing a dish.";
		return name + " is idle.";
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
  