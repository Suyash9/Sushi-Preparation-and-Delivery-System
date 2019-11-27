package common;

import java.io.Serializable;

public class Ingredient extends Model implements Serializable{
	
	private static final long serialVersionUID = -6075543907994337053L;
	private String name;
	private String unit;
	private Supplier supplier;
	private IngredientStockManager ingredientManager;
	
	public Ingredient(String name, Supplier supplier, String unit, Integer quantity, Integer threshold, Integer restock) {
		this.name = name;
		this.supplier = supplier;
		this.unit = unit;
		ingredientManager = new IngredientStockManager(this, quantity, threshold, restock);
	}

	@Override
	public String getName() {
		return name;
	}

	public Supplier getSupplier() {
		return supplier;
	}
	
	public IngredientStockManager getIngredientManager() {
		return ingredientManager;
	}
}
