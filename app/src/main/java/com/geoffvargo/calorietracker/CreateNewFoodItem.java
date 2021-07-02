package com.geoffvargo.calorietracker;

import android.os.*;
import android.text.*;
import android.util.*;
import android.view.*;
import android.widget.*;

import androidx.appcompat.app.*;
import androidx.fragment.app.*;

public class CreateNewFoodItem extends AppCompatActivity {
	private FoodItem curr;
	private EditText name;
	private EditText serving_size;
	private EditText calories;
	private EditText carbs;
	private EditText protein;
	private EditText fat;
	private Spinner mealChooser;
	private Button saveBTN;
	private Button cancelBTN;
	private ArrayAdapter<Meal> mealArrayAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_new_food_item);
		this.setFinishOnTouchOutside(false);

		saveBTN = findViewById(R.id.createFoodItem_saveBTN);
		cancelBTN = findViewById(R.id.createFoodItem_cancelBTN);

		mealChooser = findViewById(R.id.mealChooserSPNR);
		mealArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Meal.values());
		mealChooser.setAdapter(mealArrayAdapter);

		saveBTN.setOnClickListener(c -> {
			FoodItemDataSource ds = new FoodItemDataSource(this);
			try {
				ds.open();
				ds.insertItem(curr);
				ds.close();
			} catch (Exception e) {
				Log.e(e.getClass().getCanonicalName(), e.getMessage(), e);
			}
			finish();
		});

		cancelBTN.setOnClickListener(c -> finish());
		initTextFields();
		curr = new FoodItem();
		initFieldWatchers();
	}

	private void initTextFields() {
		name = findViewById(R.id.foodItemNameINPUT);
		calories = findViewById(R.id.foodItemCaloriesINPUT);
		serving_size = findViewById(R.id.foodItemServingSizeINPUT);
		carbs = findViewById(R.id.foodItemCarbsINPUT);
		protein = findViewById(R.id.foodItemProteinINPUT);
		fat = findViewById(R.id.foodItemFatINPUT);
	}

	private void initFieldWatchers() {
		name.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				curr.setFood_name(name.getText().toString());
			}
		});
		calories.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				curr.setCalories(Integer.parseInt(calories.getText().toString()));
			}
		});
		serving_size.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				curr.setCalories(Integer.parseInt(serving_size.getText().toString()));
			}
		});
		carbs.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				curr.setCarbs(Float.parseFloat(carbs.getText().toString()));
			}
		});
		protein.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				curr.setProtein(Float.parseFloat(protein.getText().toString()));
			}
		});
		fat.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				curr.setProtein(Float.parseFloat(fat.getText().toString()));
			}
		});
		mealChooser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				Meal[] values = Meal.values();
				String x      = values[position].toString();
				curr.setMeal_name(values[position]);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
	}

	public void showTimePickerDialog(View v) {
		DialogFragment newFragment = new TimeChooser();
		newFragment.show(getSupportFragmentManager(), "timeChooser");
	}
}