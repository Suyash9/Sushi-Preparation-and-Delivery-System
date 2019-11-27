package common;

/**
 * Abstract class for stock of ingredients and dishes.
 * Implemented separately for ingredients and dishes.
 *
 */
public abstract class StockManager {
	
	Integer quantity;
	Integer threshold;
	Integer restock;
	
	public StockManager (Integer quantity, Integer threshold, Integer restock) {
		this.quantity = quantity;
		this.threshold = threshold;
		this.restock = restock;
	}
	
	abstract boolean checkRestock();
	abstract void usage(Integer quantity);
	abstract boolean checkQuantity(Integer quantity);
	abstract void restock();
	
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
