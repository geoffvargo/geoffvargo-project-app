/* Geoff Vargo 101908362 */

package com.geoffvargo.calorietracker;

import android.os.*;
import android.view.*;
import android.widget.*;

import com.geoffvargo.calorietracker.databinding.*;
import com.google.android.material.bottomnavigation.*;

import java.util.*;

import androidx.appcompat.app.*;
import androidx.core.content.*;
import androidx.navigation.*;
import androidx.navigation.ui.*;
import androidx.recyclerview.widget.*;

//public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
public class MainActivity extends AppCompatActivity {
	private ActivityMainBinding binding;

	private RecyclerView foodItemView;
	private ArrayList<FoodItem> foodItems = new ArrayList<>();
	private FoodItemAdapter adapter;
	private ArrayAdapter<Meal> mealArrayAdapter;
	private ImageButton settingsBTN;
	private ImageButton insightsBTN;
	private String sortBy;
	private String sortOrder;
	private BottomNavigationView bottomNavView;
	private NavController navController;

	private Home home = new Home();
	private Today today = new Today();
	private Meals meals = new Meals();



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.app_bar, menu);
		return true;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		binding = ActivityMainBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());

//		setContentView(R.layout.activity_main);
		Window w = getWindow();
		w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
		w.setNavigationBarColor(ContextCompat.getColor(this, android.R.color.holo_blue_dark));



//		toolBarLayout.setTitle(getTitle());

		bottomNavView = findViewById(R.id.bottomNavVIEW);
//		bottomNavView.setSelectedItemId(R.id.homeNavITEM);
//		bottomNavView.setOnNavigationItemSelectedListener(this);
		NavController navController = Navigation.findNavController(this, R.id.navHost);

		NavigationUI.setupWithNavController(binding.bottomNavVIEW, navController);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	public ArrayList<FoodItem> getFoodItems() {
		return foodItems;
	}

	public void setFoodItems(ArrayList<FoodItem> foodItems) {
		this.foodItems = foodItems;
	}

//	@Override
//	public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
//		int itemId = item.getItemId();
//		if (itemId == R.id.homeNavITEM) {
//			getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, home).commit();
//			Log.d("bottomNav", "home");
//			return true;
//		} else if (itemId == R.id.todayNavITEM) {
//			getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, today).commit();
//			Log.d("bottomNav", "today");
//			return true;
//		} else if (itemId == R.id.mealsNavITEM) {
//			getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, meals).commit();
//			Log.d("bottomNav", "meals");
//			return true;
//		}
//		return false;
//	}
}