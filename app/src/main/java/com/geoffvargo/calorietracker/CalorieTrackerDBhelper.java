package com.geoffvargo.calorietracker;

import android.content.*;
import android.database.sqlite.*;
import android.util.*;

import androidx.annotation.*;

class CalorieTrackerDBhelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "calorieTracker.db";
	private static final int DATABASE_VERSION = 1;

	// Database creation sql statement
	private static final String CREATE_TABLE_ITEM = "CREATE TABLE item(_id INTEGER PRIMARY KEY AUTOINCREMENT, food_name TEXT NOT NULL, " +
	                                                "servings FLOAT DEFAULT 1.0, carbs FLOAT DEFAULT 0.0, protein FLOAT DEFAULT 0.0, fat FLOAT DEFAULT 0.0);";

	public CalorieTrackerDBhelper(@Nullable Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_ITEM);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(CalorieTrackerDBhelper.class.getName(),
		      "Upgrading database from version " + oldVersion + " to "
		      + newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS item");
		onCreate(db);

	}
}
