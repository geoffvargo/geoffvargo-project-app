package com.geoffvargo.calorietracker;

import android.content.*;
import android.database.sqlite.*;

import androidx.annotation.*;

class CalorieTrackerDBhelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "shoplist.db";
	private static final int DATABASE_VERSION = 1;

	public CalorieTrackerDBhelper(@Nullable Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
