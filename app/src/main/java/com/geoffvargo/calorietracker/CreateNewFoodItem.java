package com.geoffvargo.calorietracker;

import android.os.*;
import android.widget.*;

import androidx.appcompat.app.*;

public class CreateNewFoodItem extends AppCompatActivity {
	private FoodItem curr;
	private Button saveBTN;
	private Button canceBTN;
	private ArrayAdapter<Meal> mealArrayAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_new_food_item);
		this.setFinishOnTouchOutside(false);

		saveBTN = findViewById(R.id.createFoodItem_saveBTN);
		canceBTN = findViewById(R.id.createFoodItem_cancelBTN);

		Spinner mealChooser = findViewById(R.id.mealChooserSPNR);
		mealArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Meal.values());
		mealChooser.setAdapter(mealArrayAdapter);

		saveBTN.setOnClickListener(c -> {
			FoodItemDataSource ds = new FoodItemDataSource(this);
			try {
				ds.open();
				ds.insertItem(curr);
				ds.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			finish();
		});

		canceBTN.setOnClickListener(c ->{
			finish();
		});
	}
}