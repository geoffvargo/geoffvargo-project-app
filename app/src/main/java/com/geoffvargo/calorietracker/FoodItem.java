/* Geoff Vargo 101908362 */
package com.geoffvargo.calorietracker;

import java.sql.*;

/**
 * This is the data model class for the app: the FoodItem.
 */
public class FoodItem {
	private int _id;
	private String food_name;
	private float servings;
	private float serving_size;
	private Timestamp time;
	private int calories;
	private float carbs;
	private float protein;
	private float fat;
	private Meal meal_name;

	public FoodItem() {
		this._id = -1;
		this.meal_name = Meal.NONE;
	}

	/**
	 * Instantiates a new Food item.
	 *
	 * @param _id
	 * 		the id
	 * @param food_name
	 * 		the food name
	 * @param servings
	 * 		the servings
	 * @param serving_size
	 * 		the serving size
	 * @param time
	 * 		the time
	 * @param calories
	 * 		the calories
	 * @param carbs
	 * 		the carbs
	 * @param protein
	 * 		the protein
	 * @param fat
	 * 		the fat
	 * @param meal_name
	 * 		the meal name
	 */
	public FoodItem(int _id, String food_name, float servings, float serving_size, Timestamp time, int calories, float carbs, float protein, float fat, Meal meal_name) {
		this._id = _id;
		this.food_name = food_name;
		this.servings = servings;
		this.serving_size = serving_size;
		this.time = time;
		this.calories = calories;
		this.carbs = carbs;
		this.protein = protein;
		this.fat = fat;
		this.meal_name = meal_name;
	}

	/**
	 * Gets id.
	 *
	 * @return the id
	 */
	public int get_id() {
		return _id;
	}

	/**
	 * Sets id.
	 *
	 * @param _id
	 * 		the id
	 */
	public void set_id(int _id) {
		this._id = _id;
	}

	/**
	 * Gets food name.
	 *
	 * @return the food name
	 */
	public String getFood_name() {
		return food_name;
	}

	/**
	 * Sets food name.
	 *
	 * @param food_name
	 * 		the food name
	 */
	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}

	/**
	 * Gets servings.
	 *
	 * @return the servings
	 */
	public float getServings() {
		return servings;
	}

	/**
	 * Sets servings.
	 *
	 * @param servings
	 * 		the servings
	 */
	public void setServings(float servings) {
		this.servings = servings;
	}

	/**
	 * Gets serving size.
	 *
	 * @return the serving size
	 */
	public float getServing_size() {
		return serving_size;
	}

	/**
	 * Sets serving size.
	 *
	 * @param serving_size
	 * 		the serving size
	 */
	public void setServing_size(float serving_size) {
		this.serving_size = serving_size;
	}

	/**
	 * Gets time.
	 *
	 * @return the time
	 */
	public Timestamp getTime() {
		return time;
	}

	/**
	 * Sets time.
	 *
	 * @param time
	 * 		the time
	 */
	public void setTime(Timestamp time) {
		this.time = time;
	}

	/**
	 * Gets carbs.
	 *
	 * @return the carbs
	 */
	public float getCarbs() {
		return carbs;
	}

	/**
	 * Sets carbs.
	 *
	 * @param carbs
	 * 		the carbs
	 */
	public void setCarbs(float carbs) {
		this.carbs = carbs;
	}

	/**
	 * Gets protein.
	 *
	 * @return the protein
	 */
	public float getProtein() {
		return protein;
	}

	/**
	 * Sets protein.
	 *
	 * @param protein
	 * 		the protein
	 */
	public void setProtein(float protein) {
		this.protein = protein;
	}

	/**
	 * Gets fat.
	 *
	 * @return the fat
	 */
	public float getFat() {
		return fat;
	}

	/**
	 * Sets fat.
	 *
	 * @param fat
	 * 		the fat
	 */
	public void setFat(float fat) {
		this.fat = fat;
	}

	/**
	 * Gets meal name.
	 *
	 * @return the meal name
	 */
	public Meal getMeal_name() {
		return meal_name;
	}

	/**
	 * Sets meal name.
	 *
	 * @param meal_name
	 * 		the meal name
	 */
	public void setMeal_name(Meal meal_name) {
		this.meal_name = meal_name;
	}

	/**
	 * Gets calories.
	 *
	 * @return the calories
	 */
	public int getCalories() {
		return calories;
	}

	/**
	 * Sets calories.
	 *
	 * @param calories
	 * 		the calories
	 */
	public void setCalories(int calories) {
		this.calories = calories;
	}
}
