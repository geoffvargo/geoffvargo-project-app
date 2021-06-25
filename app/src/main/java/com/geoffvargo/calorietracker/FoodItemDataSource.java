package com.geoffvargo.calorietracker;

import android.content.*;
import android.database.sqlite.*;

import java.sql.*;

class FoodItemDataSource {
	private SQLiteDatabase database;
	private FoodItemDBHelper dbHelper;

	FoodItemDataSource(Context context) {
		dbHelper = new FoodItemDBHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public void insertItem(FoodItem foodItem) {
		String statement = "INSERT INTO food_item(food_name, servings, serving_size, time, carbs, protein, fat, meal_name) " +
		                   "VALUES (" + foodItem.getFood_name() + ", " + foodItem.getServings() + ", " + foodItem.getServing_size() + ", " + foodItem.getTime() +
		                   ", " + foodItem.getCarbs() + ", " + foodItem.getProtein() + ", " + foodItem.getFat() + ", " + foodItem.getMeal_name() + ");";
		database.execSQL(statement);
	}
}
