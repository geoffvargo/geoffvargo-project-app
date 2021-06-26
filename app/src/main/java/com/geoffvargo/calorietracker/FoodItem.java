package com.geoffvargo.calorietracker;

import java.sql.*;

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

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getFood_name() {
		return food_name;
	}

	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}

	public float getServings() {
		return servings;
	}

	public void setServings(float servings) {
		this.servings = servings;
	}

	public float getServing_size() {
		return serving_size;
	}

	public void setServing_size(float serving_size) {
		this.serving_size = serving_size;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public float getCarbs() {
		return carbs;
	}

	public void setCarbs(float carbs) {
		this.carbs = carbs;
	}

	public float getProtein() {
		return protein;
	}

	public void setProtein(float protein) {
		this.protein = protein;
	}

	public float getFat() {
		return fat;
	}

	public void setFat(float fat) {
		this.fat = fat;
	}

	public Meal getMeal_name() {
		return meal_name;
	}

	public void setMeal_name(Meal meal_name) {
		this.meal_name = meal_name;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}
}
