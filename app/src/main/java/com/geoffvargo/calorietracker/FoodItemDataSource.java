package com.geoffvargo.calorietracker;

import android.content.*;
import android.database.*;
import android.database.sqlite.*;
import android.util.*;

import java.sql.SQLException;
import java.sql.*;
import java.util.*;

/**
 * The type Food item data source.
 */
class FoodItemDataSource {
	private SQLiteDatabase database;
	private FoodItemDBHelper dbHelper;

	/**
	 * Instantiates a new Food item data source.
	 *
	 * @param context
	 * 		the context used to instantiate the class
	 */
	FoodItemDataSource(Context context) {
		dbHelper = new FoodItemDBHelper(context);
	}

	/**
	 * Open.
	 *
	 * @throws SQLException
	 * 		the sql exception
	 */
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	/**
	 * Close.
	 */
	public void close() {
		dbHelper.close();
	}

	/**
	 * Update item boolean.
	 *
	 * @param s
	 * 		the s
	 * @return the boolean
	 */
	public boolean updateItem(FoodItem s) {
		boolean didSucceed = false;
		try {
			Long          rowId         = (long) s.get_id();
			ContentValues initialValues = new ContentValues();

			initialValues.put("food_name", s.getFood_name());
			initialValues.put("calories", s.getCalories());
			initialValues.put("carbs", s.getCarbs());
			initialValues.put("protein", s.getProtein());
			initialValues.put("fat", s.getFat());
			initialValues.put("meal_name", s.getMeal_name().toString());
			initialValues.put("time", s.getTime().toString());

			didSucceed = database.update("food_item", initialValues, "_id=" + rowId, null) > 0;
		} catch (Exception e) {
			Log.e(e.getClass().getCanonicalName(), e.getMessage(), e);
		}
		return didSucceed;
	}

	/**
	 * Insert item boolean.
	 *
	 * @param s
	 * 		the s
	 * @return the boolean
	 */
	public boolean insertItem(FoodItem s) {
		boolean didSucceed = false;
		try {
			Long          rowId         = (long) s.get_id();
			ContentValues initialValues = new ContentValues();

			initialValues.put("food_name", s.getFood_name());
			initialValues.put("calories", s.getCalories());
			initialValues.put("serving_size", s.getServing_size());
			initialValues.put("carbs", s.getCarbs());
			initialValues.put("protein", s.getProtein());
			initialValues.put("fat", s.getFat());
			initialValues.put("meal_name", s.getMeal_name().toString());
			initialValues.put("time", s.getTime().toString());

			didSucceed = database.insert("food_item", null, initialValues) > 0;
		} catch (Exception e) {
			Log.e(e.getClass().getCanonicalName(), e.getMessage(), e);
		}
		return didSucceed;

//		try {
//			String statement = "INSERT INTO food_item(food_name, servings, serving_size, time, calories, carbs, protein, fat, meal_name) " +
//			                   "VALUES (" + foodItem.getFood_name() + ", " + foodItem.getServings() + ", " + foodItem.getServing_size() + ", " + foodItem.getTime() +
//			                   ", " + foodItem.getCalories() + "," + foodItem.getCarbs() + ", " + foodItem.getProtein() + ", " + foodItem.getFat() + ", " +
//			                   foodItem.getMeal_name() + ");";
//			database.execSQL(statement);
//		} catch (Exception e) {
//			Log.e("calorieTracker", e.getMessage(), e);
//		}
	}

	public boolean deleteItem(FoodItem s) {
		boolean didDelete = false;
		try {
			didDelete = database.delete("food_item", "_id=" + s.get_id(), null) > 0;
		} catch (Exception e) {
			Log.e(e.getClass().getCanonicalName(), e.getMessage(), e);
		}
		return didDelete;
	}

	/**
	 * Gets items.
	 *
	 * @return the items
	 */
	public ArrayList<FoodItem> getItems() {
		ArrayList<FoodItem> items = new ArrayList<>();

		try {
			String query  = "SELECT * FROM food_item;";
			Cursor cursor = database.rawQuery(query, null);

			FoodItem newItem;
			cursor.moveToFirst();

			while (!cursor.isAfterLast()) {
				newItem = new FoodItem();
				newItem.set_id(cursor.getInt(0));

				newItem.setFood_name(cursor.getString(1));
				newItem.setServings(cursor.getFloat(2));
				newItem.setServing_size(cursor.getFloat(3));
				newItem.setTime(Timestamp.valueOf(cursor.getString(4)));
				newItem.setCalories(cursor.getInt(5));
				newItem.setCarbs(cursor.getFloat(6));
				newItem.setProtein(cursor.getFloat(7));
				newItem.setFat(cursor.getFloat(8));
				newItem.setMeal_name(Meal.stringToMeal(cursor.getString(9)));

				items.add(newItem);
				cursor.moveToNext();
			}
			cursor.close();
		} catch (Exception e) {
			items = new ArrayList<>();
			Log.e(e.getClass().getCanonicalName(), e.getMessage(), e);
		}

		return items;
	}
}
