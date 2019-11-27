package common;

public class Order extends Model {
	
	//list of string variables to set for order status
	private static final String preparing = "Preparing Order";
	private static final String deliver = "Delivering Order";
	private static final String received = "Order Reveived";
	private static final String cancelled = "Order Cancelled";
	private static final String completed = "Order Completed";
	
	private User user;
	private String name;
	private String address;
	private String orderStatus;
    
	public Order(String name, User user) {
        super();
        this.user = user;
        this.name = name;
        this.address = user.getAddress();
        orderStatus = preparing;
    }
	
	/**
	 * method changes order status to completed 
	 */
    public void completedOrder() {
    	orderStatus = completed;
    }

	/**
	 * method changes order status to delivering
	 */
    public void deliverOrder() {
        orderStatus = deliver;
    }
    
	/**
	 * method changes order status to cancelled
	 */
    public void cancelOrder() {
        orderStatus = cancelled;
    }
    
	/**
	 * method changes order status to received
	 */
    public void orderReceived() {
    	orderStatus = received;
    }
    
    /**
     * method that returns true if order has been completed, false otherwise
     * @return boolean
     */
    public Boolean isCompleted() {
    	if(orderStatus.equals(completed)) {
			return true;
		}
		return false;
    }
    
    /**
     * method that returns cost of an order
     * @return numbers
     */
    public Number getCost() {
    	return user.getTotalCost();
    }
    
	@Override
	public String getName() {
		return name;
	}
	
	public User getUser() {
		return user;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOrderStatus() {
		return orderStatus;
	}
}
