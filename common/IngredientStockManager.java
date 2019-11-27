package common;

/**
 * Class that manages the quantity, threshold and restock amounts of ingredients. 
 * 
 */

import java.io.Serializable;

public class IngredientStockManager extends StockManager implements Serializable{
	
	private static final long serialVersionUID = 5913091331291386667L;
	private Ingredient ingredient;
	private Integer quantity;
	private Integer threshold;
	private Integer restock;
	
	public IngredientStockManager(Ingredient ingredient, Integer quantity, Integer threshold, Integer restock) {
		super(quantity, threshold, restock);
		this.ingredient = ingredient;
		this.quantity = quantity;
		this.threshold = threshold;
		this.restock = restock;
	}
	
	//method that returns true if an ingredient needs to be restocked
	public boolean checkRestock() {
		return (quantity < threshold);
	}
	
	//method that reduces the current quantity of an ingredient after it has been used
	public void usage(Integer quantity) {
		quantity -= quantity;
	}
	
	//method that returns true if there is enough quantity of an ingredient to use in a dish
	public boolean checkQuantity(Integer quantity) {
		return (quantity >= quantity);
	}
	
	//method to restock an ingredient
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
