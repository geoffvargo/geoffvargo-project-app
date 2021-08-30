/* Geoff Vargo 101908362 */

package com.geoffvargo.calorietracker;

import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;

import com.google.android.material.appbar.*;
import com.google.android.material.bottomnavigation.*;
import com.google.android.material.floatingactionbutton.*;

import java.util.*;

import androidx.fragment.app.*;
import androidx.recyclerview.widget.*;

//public class Home extends AppCompatActivity {
public class Home extends Fragment {
	private RecyclerView foodItemView;
	private ArrayList<FoodItem> foodItems = new ArrayList<>();
	private FoodItemAdapter adapter;
	private ArrayAdapter<Meal> mealArrayAdapter;
	private ImageButton settingsBTN;
	private ImageButton insightsBTN;
	private String sortBy;
	private String sortOrder;
	private BottomNavigationView bottomNavView;

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		getActivity().getMenuInflater().inflate(R.menu.app_bar, menu);
//		return true;
//	}

	public Home() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home, container, false);
		CollapsingToolbarLayout toolBarLayout = view.findViewById(R.id.toolbar_layout);
		toolBarLayout.setTitle(getActivity().getTitle());

		settingsBTN = view.findViewById(R.id.settingsBTN);
		settingsBTN.setOnClickListener(c -> {
//			Intent intent = new Intent(Home.this, SettingsActivity.class);
			Intent intent = new Intent(getActivity(), SettingsActivity.class);
			startActivity(intent);
		});

		FloatingActionButton fab = view.findViewById(R.id.newItemBTN);
		fab.setOnClickListener(fabClick -> {
//			Intent intent = new Intent(Home.this, CreateNewFoodItem.class);
			Intent intent = new Intent(getActivity(), CreateNewFoodItem.class);
			startActivity(intent);
		});
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();

		sortBy = getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE).getString("field", "food_name");
		sortOrder = getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE).getString("sortorder", "ASC");

		FoodItemDataSource dataSource = new FoodItemDataSource(getActivity());

		try {
			dataSource.open();
			foodItems = dataSource.getItems(sortBy, sortOrder);
			dataSource.close();

			if (foodItems.size() > 0) {
				foodItemView = getActivity().findViewById(R.id.bottomContent);
				RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
				foodItemView.setLayoutManager(layoutManager);
				adapter = new FoodItemAdapter(foodItems, getActivity());
				foodItemView.setAdapter(adapter);
			} else {
				Intent intent = new Intent(getActivity(), CreateNewFoodItem.class);
				startActivity(intent);
			}
		} catch (Exception throwables) {
			Toast.makeText(getActivity(), "Error retrieving list.", Toast.LENGTH_LONG).show();
		}
	}

	public ArrayList<FoodItem> getFoodItems() {
		return foodItems;
	}

	public void setFoodItems(ArrayList<FoodItem> foodItems) {
		this.foodItems = foodItems;
	}
}