package com.geoffvargo.calorietracker;

public enum Meal {
	BREAKFAST, LUNCH, DINNER, SNACK, NONE;

	public static Meal stringToMeal(String str) {
		switch (str) {
			case "breakfast":
			case "Breakfast":
			case "BREAKFAST":
				return Meal.BREAKFAST;
			case "lunch":
			case "Lunch":
			case "LUNCH":
				return Meal.LUNCH;
			case "dinner":
			case "Dinner":
			case "DINNER":
				return Meal.DINNER;
			case "snack":
			case "Snack":
			case "STACK":
				return Meal.SNACK;
			default:
				return Meal.NONE;
		}
	}

	public static Meal intToMeal(int n) {
		switch (n) {
			case 0:
				return Meal.BREAKFAST;
			case 1:
				return Meal.LUNCH;
			case 2:
				return Meal.DINNER;
			case 3:
				return Meal.SNACK;
			default:
				return Meal.NONE;
		}
	}

	public static int mealToInt(Meal meal) {
		switch (meal) {
			case BREAKFAST:
				return 0;
			case LUNCH:
				return 1;
			case DINNER:
				return 2;
			case SNACK:
				return 3;
			case NONE:
				return 4;
			default:
				return -1;
		}
	}

	public static String mealToString(Meal meal) {
		switch (meal) {
			case BREAKFAST:
				return "BREAKFAST";
			case LUNCH:
				return "LUNCH";
			case DINNER:
				return "DINNER";
			case SNACK:
				return "SNACK";
			case NONE:
			default:
				return "NONE";
		}
	}
}
