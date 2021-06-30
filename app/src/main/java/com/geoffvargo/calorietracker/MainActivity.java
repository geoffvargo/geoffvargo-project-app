package com.geoffvargo.calorietracker;

import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;

import com.google.android.material.appbar.*;
import com.google.android.material.floatingactionbutton.*;

import java.sql.*;
import java.util.*;

import androidx.appcompat.app.*;
import androidx.recyclerview.widget.*;

public class MainActivity extends AppCompatActivity {
	private RecyclerView foodItemView;
	private ArrayList<FoodItem> foodItems = new ArrayList<>();
	private FoodItemAdapter adapter;
	private ArrayAdapter<Meal> mealArrayAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Window w = getWindow();
//		w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
//		View v = findViewById(android.R.id.navigationBarBackground);
		w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//		w.setNavigationBarColor(ContextCompat.getColor(this, android.R.color.holo_blue_dark));
//		w.setStatusBarColor(ContextCompat.getColor(this, android.R.color.holo_blue_dark));
//		v.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);

		CollapsingToolbarLayout toolBarLayout = findViewById(R.id.toolbar_layout);
		toolBarLayout.setTitle(getTitle());
		setSupportActionBar(findViewById(R.id.toolbar));

		FloatingActionButton fab = findViewById(R.id.newItemBTN);
		fab.setOnClickListener(fabClick -> {
			Intent intent = new Intent(MainActivity.this, CreateNewFoodItem.class);
			startActivity(intent);
		});


	}

	@Override
	protected void onResume() {
		super.onResume();

		FoodItemDataSource dataSource = new FoodItemDataSource(this);

		try {
			dataSource.open();
			foodItems = dataSource.getItems();
			dataSource.close();

			if (foodItems.size() > 0) {
				foodItemView = findViewById(R.id.bottomContent);
				RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
				foodItemView.setLayoutManager(layoutManager);
				adapter = new FoodItemAdapter(foodItems, this);
				foodItemView.setAdapter(adapter);
			} else {
				Intent intent = new Intent(MainActivity.this, CreateNewFoodItem.class);
				startActivity(intent);
			}
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