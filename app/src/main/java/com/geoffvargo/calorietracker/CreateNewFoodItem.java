/* Geoff Vargo 101908362 */
package com.geoffvargo.calorietracker;

import android.app.*;
import android.os.*;
import android.text.*;
import android.util.*;
import android.view.*;
import android.widget.*;

import java.sql.*;
import java.util.*;

import androidx.appcompat.app.*;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.*;

/**
 * This Activity handles the creation of individual FoodItem's.
 */
public class CreateNewFoodItem extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
	private TimeViewModel tvm;
	private final Calendar callie = Calendar.getInstance();
	private Timestamp timestamp;
	private FoodItem curr;
	private EditText name;
	private EditText serving_size;
	private EditText serving_num;
	private EditText calories;
	private EditText carbs;
	private EditText protein;
	private EditText fat;
	private Button timeBTN;
	private TextView timeLBL;
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
		timeBTN = findViewById(R.id.timePickerBTN);
		timeLBL = findViewById(R.id.timePickerLBL);
		timestamp = new Timestamp(callie.getTimeInMillis());
		timeLBL.setText(timestamp.toString());

		tvm = new ViewModelProvider(this).get(TimeViewModel.class);
		tvm.getSelectedTime().observe(this, t -> {
			timestamp = t;
			System.out.print(timestamp);
			timeLBL.setText(timestamp.toString());
		});

		mealChooser = findViewById(R.id.mealChooserSPNR);
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
		serving_num = findViewById(R.id.foodItemServingsINPUT);
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
				curr.setServing_size(Integer.parseInt(serving_size.getText().toString()));
			}
		});
		serving_num.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				curr.setServings(Integer.parseInt(serving_num.getText().toString()));
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
				curr.setFat(Float.parseFloat(fat.getText().toString()));
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

	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
	}
}