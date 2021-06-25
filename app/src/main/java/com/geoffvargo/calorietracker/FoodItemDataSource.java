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

	public boolean insertItem(FoodItem foodItem) {
		ContentValues initialValues = new ContentValues();

		initialValues.put("food_name", foodItem.getFood_name());
		initialValues.put("servings", foodItem.getServings());
		initialValues.put("serving_size", foodItem.getServing_size());
//		initialValues.put("time", String.valueOf(foodItem.getTime()));
		initialValues.put("servings", foodItem.getServings());

		return database.insert("food_item", null, initialValues) > 0;
	}
}
