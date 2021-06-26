package com.geoffvargo.calorietracker;

import android.os.*;
import android.view.*;
import android.widget.*;

import com.google.android.material.appbar.*;

import java.sql.*;
import java.util.*;

import androidx.appcompat.app.*;
import androidx.recyclerview.widget.*;

public class MainActivity extends AppCompatActivity {
	private RecyclerView foodItemView;
	private ArrayList<FoodItem> foodItems = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Window w = getWindow();
		w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

		CollapsingToolbarLayout toolBarLayout = findViewById(R.id.toolbar_layout);
		toolBarLayout.setTitle(getTitle());
		setSupportActionBar(findViewById(R.id.toolbar));
	}

	@Override
	protected void onResume() {
		super.onResume();

		FoodItemDataSource dataSource = new FoodItemDataSource(this);

		try {
			dataSource.open();
			foodItems = dataSource.getItems();
			dataSource.close();
		} catch (SQLException throwables) {
			Toast.makeText(this, "Error retrieving list.", Toast.LENGTH_LONG).show();
		}
	}

	public ArrayList<FoodItem> getFoodItems() {
		return foodItems;
	}

	public void setFoodItems(ArrayList<FoodItem> foodItems) {
		this.foodItems = foodItems;
	}
}