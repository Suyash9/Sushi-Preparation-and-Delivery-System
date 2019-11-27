package common;

import java.io.Serializable;
import java.util.Map;

public class Dish extends Model implements Serializable{
	
	private static final long serialVersionUID = 7572136795864918741L;
	private String name;
	private String description;
	private Double price;
	private Map<Ingredient, Number> dishRecipe;
	private DishStockManager dishManager;
	
	public Dish (String name, String description, Double price, Map<Ingredient, Number> dishRecipe, 
				Integer quantity, Integer threshold, Integer restock) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.dishRecipe = dishRecipe;
		dishManager = new DishStockManager(this, quantity, threshold, restock);
	}
	
	@Override
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Double getPrice() {
		return price;
	}

	public Map<Ingredient, Number> getDishRecipe() {
		return dishRecipe;
	}

	public void setDishRecipe(Map<Ingredient, Number> dishRecipe) {
		this.dishRecipe = dishRecipe;
	}

	public DishStockManager getDishManager() {
		return dishManager;
	}
}
