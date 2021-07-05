/* Geoff Vargo 101908362 */
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
import androidx.core.content.*;
import androidx.recyclerview.widget.*;

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity {
	/**
	 * The Food item view.
	 */
	private RecyclerView foodItemView;
	/**
	 * The Food items.
	 */
	private ArrayList<FoodItem> foodItems = new ArrayList<>();
	/**
	 * The Adapter.
	 */
	private FoodItemAdapter adapter;
	/**
	 * The Meal array adapter.
	 */
	private ArrayAdapter<Meal> mealArrayAdapter;
	/**
	 * The Settings btn.
	 */
	private ImageButton settingsBTN;
	/**
	 * The Insights btn.
	 */
	private ImageButton insightsBTN;

	/**
	 * On create options menu boolean.
	 *
	 * @param menu
	 * 		the menu
	 * @return the boolean
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.app_bar, menu);
		return true;
	}

	/**
	 * On create.
	 *
	 * @param savedInstanceState
	 * 		the saved instance state
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Window w = getWindow();
//		View v = findViewById(android.R.id.navigationBarBackground);
		w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
		w.setNavigationBarColor(ContextCompat.getColor(this, android.R.color.holo_blue_dark));
//		w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
//		w.setStatusBarColor(ContextCompat.getColor(this, android.R.color.holo_blue_dark));
//		w.set(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
//		w.setFlags();
//		setwi
//		w.setStatusBarColor(Color.TRANSPARENT);
//		v.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
//		w.setStatusBarColor(Color.TRANSPARENT);

		CollapsingToolbarLayout toolBarLayout = findViewById(R.id.toolbar_layout);
		toolBarLayout.setTitle(getTitle());
//		setSupportActionBar(findViewById(R.id.toolbar));

		insightsBTN = findViewById(R.id.insightsBTN);
		insightsBTN.setOnClickListener(c -> {
			Intent intent = new Intent(MainActivity.this, InsightsActivity.class);
			startActivity(intent);
		});

		settingsBTN = findViewById(R.id.settingsBTN);
		settingsBTN.setOnClickListener(c -> {
			Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
			startActivity(intent);
		});

		FloatingActionButton fab = findViewById(R.id.newItemBTN);
		fab.setOnClickListener(fabClick -> {
			Intent intent = new Intent(MainActivity.this, CreateNewFoodItem.class);
			startActivity(intent);
		});
	}

	/**
	 * On resume.
	 */
	@Override
	protected void onResume() {
		super.onResume();

		String sortBy    = getSharedPreferences("Settings", Context.MODE_PRIVATE).getString("field", "food_name");
//		String sortOrder = getSharedPreferences("Settings", Context.MODE_PRIVATE).getString("sortorder", "ASC");

		FoodItemDataSource dataSource = new FoodItemDataSource(this);

		try {
			dataSource.open();
			foodItems = dataSource.getItems(sortBy, null);
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

	/**
	 * Gets food items.
	 *
	 * @return the food items
	 */
	public ArrayList<FoodItem> getFoodItems() {
		return foodItems;
	}

	/**
	 * Sets food items.
	 *
	 * @param foodItems
	 * 		the food items
	 */
	public void setFoodItems(ArrayList<FoodItem> foodItems) {
		this.foodItems = foodItems;
	}
}