/* Geoff Vargo 101908362 */
package com.geoffvargo.calorietracker;

import android.content.*;
import android.os.*;
import android.text.*;
import android.util.*;
import android.view.*;
import android.widget.*;

import java.sql.*;
import java.util.*;

import androidx.appcompat.app.*;
import androidx.fragment.app.*;
import androidx.lifecycle.*;

public class EditFoodItem extends AppCompatActivity {
	private final Calendar callie = Calendar.getInstance();
	private Intent parentIntent;
	private int idnum;
	private TimeViewModel tvm;
	private Timestamp timestamp;
	private FoodItem curr;
	private EditText name;
	private EditText serving_size;
	private EditText servings;
	private EditText calories;
	private EditText carbs;
	private EditText protein;
	private EditText fat;
	private Button timeBTN;
	private TextView timeLBL;
	private TextView idLBL;
	private Spinner mealChooser;
	private Button saveBTN;
	private Button cancelBTN;
	private Button deleteBTN;
	private ArrayAdapter<Meal> mealArrayAdapter;

	public EditFoodItem() {
		parentIntent = getIntent();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_food_item);
		this.setFinishOnTouchOutside(false);

		parentIntent = getIntent();
		curr = new FoodItem();

		idnum = parentIntent.getIntExtra("_id", -1);

		saveBTN = findViewById(R.id.edit_foodItem_saveBTN);
		deleteBTN = findViewById(R.id.edit_foodItem_deleteBTN);
		cancelBTN = findViewById(R.id.edit_foodItem_cancelBTN);
		timeBTN = findViewById(R.id.edit_timePickerBTN);
		timeLBL = findViewById(R.id.edit_timePickerLBL);
		timestamp = new Timestamp(callie.getTimeInMillis());
		timeLBL.setText(timestamp.toString());

		tvm = new ViewModelProvider(this).get(TimeViewModel.class);
		tvm.getSelectedTime().observe(this, t -> {
			timestamp = t;
			System.out.print(timestamp);
			timeLBL.setText(timestamp.toString());
		});

		mealChooser = findViewById(R.id.edit_mealChooserSPNR);
		mealArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Meal.values());
		mealChooser.setAdapter(mealArrayAdapter);

		timeBTN.setOnClickListener(c -> {
			FragmentManager fm    = getSupportFragmentManager();
			TimeChooser     timey = new TimeChooser();
			timey.show(fm, null);
		});

		saveBTN.setOnClickListener(c -> {
			FoodItemDataSource ds = new FoodItemDataSource(this);
			try {
				ds.open();
				ds.updateItem(curr);
				ds.close();
			} catch (Exception e) {
				Log.e(e.getClass().getCanonicalName(), e.getMessage(), e);
			}
			finish();
		});

		deleteBTN.setOnClickListener(c -> {
			FoodItemDataSource ds = new FoodItemDataSource(this);
			try {
				ds.open();
				ds.deleteItem(curr);
				ds.close();
			} catch (Exception e) {
				Log.e(e.getClass().getCanonicalName(), e.getMessage(), e);
			}
			finish();
		});

		if (idnum != -1) {
			initTextFields(parentIntent);
		}

		cancelBTN.setOnClickListener(c -> finish());
		initFieldWatchers();
	}

	private void initTextFields(Intent intent) {
		name = findViewById(R.id.edit_foodItemNameINPUT);
		calories = findViewById(R.id.edit_foodItemCaloriesINPUT);
		serving_size = findViewById(R.id.edit_foodItemServingSizeINPUT);
		servings = findViewById(R.id.edit_foodItemServingsINPUT);
		carbs = findViewById(R.id.edit_foodItemCarbsINPUT);
		protein = findViewById(R.id.edit_foodItemProteinINPUT);
		fat = findViewById(R.id.edit_foodItemFatINPUT);
		idLBL = findViewById(R.id._id);

		curr.set_id(idnum);
		curr.setMeal_name(Meal.valueOf(intent.getStringExtra("meal")));
		curr.setFood_name(intent.getStringExtra("name"));
		curr.setCalories(intent.getIntExtra("calories", -1));
		curr.setServings(intent.getFloatExtra("servings", -1));
		curr.setServing_size(intent.getFloatExtra("serving_size", -1));
		curr.setTime(Timestamp.valueOf(intent.getStringExtra("timestamp")));
		curr.setCarbs(intent.getFloatExtra("carbs", -1));
		curr.setFat(intent.getFloatExtra("fat", -1));
		curr.setProtein(intent.getFloatExtra("protein", -1));

		name.setText(curr.getFood_name());
		calories.setText(String.valueOf(curr.getCalories()));
		serving_size.setText(String.valueOf(curr.getServing_size()));
		servings.setText(String.valueOf(curr.getServings()));
		carbs.setText(String.valueOf(curr.getCarbs()));
		protein.setText(String.valueOf(curr.getProtein()));
		fat.setText(String.valueOf(curr.getFat()));
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
		timeLBL.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				curr.setTime(timestamp);
			}
		});
	}

	public void showTimePickerDialog(View v) {
		DialogFragment newFragment = new TimeChooser();
		newFragment.show(getSupportFragmentManager(), "timeChooser");
	}

	public Calendar getCallie() {
		return callie;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

}