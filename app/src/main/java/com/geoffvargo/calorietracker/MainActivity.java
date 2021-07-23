/* Geoff Vargo 101908362 */

package com.geoffvargo.calorietracker;

import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;

import com.google.android.material.appbar.*;
import com.google.android.material.floatingactionbutton.*;

import java.util.*;

import androidx.appcompat.app.*;
import androidx.core.content.*;
import androidx.recyclerview.widget.*;

public class MainActivity extends AppCompatActivity {
	private RecyclerView foodItemView;
	private ArrayList<FoodItem> foodItems = new ArrayList<>();
	private FoodItemAdapter adapter;
	private ArrayAdapter<Meal> mealArrayAdapter;
	private ImageButton settingsBTN;
	private ImageButton insightsBTN;
	private String sortBy;
	private String sortOrder;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.app_bar, menu);
		return true;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Window w = getWindow();
		w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
		w.setNavigationBarColor(ContextCompat.getColor(this, android.R.color.holo_blue_dark));

		CollapsingToolbarLayout toolBarLayout = findViewById(R.id.toolbar_layout);
		toolBarLayout.setTitle(getTitle());

//		setSupportActionBar(findViewById(R.id.toolbar));

//		insightsBTN = findViewById(R.id.insightsBTN);
//		insightsBTN.setOnClickListener(c -> {
//			Intent intent = new Intent(MainActivity.this, InsightsActivity.class);
//			startActivity(intent);
//		});

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

	@Override
	protected void onResume() {
		super.onResume();

		sortBy = getSharedPreferences("Settings", Context.MODE_PRIVATE).getString("field", "food_name");
		sortOrder = getSharedPreferences("Settings", Context.MODE_PRIVATE).getString("sortorder", "ASC");

		FoodItemDataSource dataSource = new FoodItemDataSource(this);

		try {
			dataSource.open();
			foodItems = dataSource.getItems(sortBy, sortOrder);
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
		} catch (Exception throwables) {
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