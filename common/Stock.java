package common;

/**
 * Class that manages stocks of both dishes and ingredients
 *
 */
public class Stock {
	
	//method that restocks a dish if its quantity is low
	public static Dish checkDishStock() {
		for (Dish dish: StaffItemsManager.getDishes()) {
			if (dish.getDishManager().checkRestock()) {
				dish.getDishManager().restock();
				System.out.println(dish.getName() + " current stock: " + dish.getDishManager().getQuantity());
				return dish;
			}
			return dish;
		}
		return null;
	}
	
	//method that restocks an ingredient if its quantity is low
	public static Ingredient checkIngredientStock() {
		for (Ingredient ingredient: StaffItemsManager.getIngredients()) {
			if (ingredient.getIngredientManager().checkRestock()) {
				ingredient.getIngredientManager().restock();
				System.out.println(ingredient.getName() + " current stock: " + ingredient.getIngredientManager().getQuantity());
				return ingredient;
			}
		}
		return null;
	}
}
