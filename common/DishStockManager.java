package common;

/**
 * Class that manages the quantity, threshold and restock amounts of dishes. 
 * 
 */

import java.io.Serializable;

public class DishStockManager extends StockManager implements Serializable{
	
	private static final long serialVersionUID = -7271294048094907524L;
	private Dish dish;
	private Integer quantity;
	private Integer threshold;
	private Integer restock;
	
	public DishStockManager(Dish dish, Integer quantity, Integer threshold, Integer restock) {
		super(quantity, threshold, restock);
		this.dish = dish;
		this.quantity = quantity;
		this.threshold = threshold;
		this.restock = restock;
	}
	
	/**
	 * method that returns true if a dish needs to be restocked
	 */
	public boolean checkRestock() {
		return (quantity < threshold);
	}
	
	/**
	 * method that reduces the current quantity of a dish after it has been used
	 */
	public void usage(Integer quantity) {
		quantity -= quantity;
	}
	
	/**
	 * method that returns true if there is enough quantity of a dish
	 */
	public boolean checkQuantity(Integer quantity) {
		return (quantity >= quantity);
	}
	
	/**
	 * method to restock a dish
	 */
	public void restock(){
		setQuantity(quantity+restock);
    }

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getThreshold() {
		return threshold;
	}

	public void setThreshold(Integer threshold) {
		this.threshold = threshold;
	}

	public Integer getRestock() {
		return restock;
	}

	public void setRestock(Integer restock) {
		this.restock = restock;
	}
}
