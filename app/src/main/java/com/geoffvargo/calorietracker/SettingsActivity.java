/* Geoff Vargo 101908362 */
package com.geoffvargo.calorietracker;

import android.content.*;
import android.os.*;
import android.widget.*;

import androidx.appcompat.app.*;

public class SettingsActivity extends AppCompatActivity {
	private RadioGroup sortByRGRP;
	private RadioGroup sortOrderRGRP;
	private String sortBy;
	private String sortOrder;
	private Button applyBTN;
	private Button doneBTN;
	private RadioButton foodNameRB;
	private RadioButton caloriesRB;
	private RadioButton carbsRB;
	private RadioButton fatRB;
	private RadioButton proteinRB;
	private RadioButton ascendingRB;
	private RadioButton descendingRB;
	private RadioButton timeRB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		this.setFinishOnTouchOutside(false);

		foodNameRB = findViewById(R.id.settings_foodNameRB);
		caloriesRB = findViewById(R.id.settings_caloriesRB);
		carbsRB    = findViewById(R.id.settings_carbsRB);
		fatRB      = findViewById(R.id.settings_fatRB);
		proteinRB = findViewById(R.id.settings_proteinRB);
		ascendingRB = findViewById(R.id.settings_ascendingRB);
		descendingRB = findViewById(R.id.settings_descendingRB);
		timeRB = findViewById(R.id.settings_timestampRB);

		sortBy = getSharedPreferences("Settings", Context.MODE_PRIVATE).getString("field", "food_name");
		sortOrder = getSharedPreferences("Settings", Context.MODE_PRIVATE).getString("sortorder", "ASC");

		switch (sortBy) {
			case "food_name":
				foodNameRB.setChecked(true);
				break;
			case "calories":
				caloriesRB.setChecked(true);
				break;
			case "carbs":
				carbsRB.setChecked(true);
				break;
			case "fat":
				fatRB.setChecked(true);
				break;
			case "protein":
				proteinRB.setChecked(true);
				break;
			case "time":
				timeRB.setChecked(true);
				break;
			default:
				break;
		}

		switch (sortOrder) {
			case "ASC":
				ascendingRB.setChecked(true);
				break;
			case "DESC":
				descendingRB.setChecked(true);
				break;
			default:
				break;
		}

		doneBTN = findViewById(R.id.settings_doneBTN);
		doneBTN.setOnClickListener(c -> finish());

		sortByRGRP = findViewById(R.id.settings_sortByRGRP);
		sortByRGRP.setOnCheckedChangeListener((group, checkedId) -> {
			if (foodNameRB.isChecked()) {
				getSharedPreferences("Settings", Context.MODE_PRIVATE).edit().putString("field", "food_name").apply();
			} else if (caloriesRB.isChecked()) {
				getSharedPreferences("Settings", Context.MODE_PRIVATE).edit().putString("field", "calories").apply();
			} else if (carbsRB.isChecked()) {
				getSharedPreferences("Settings", Context.MODE_PRIVATE).edit().putString("field", "carbs").apply();
			} else if (fatRB.isChecked()) {
				getSharedPreferences("Settings", Context.MODE_PRIVATE).edit().putString("field", "fat").apply();
			} else if (timeRB.isChecked()) {
				getSharedPreferences("Settings", Context.MODE_PRIVATE).edit().putString("field", "time").apply();
			} else {
				getSharedPreferences("Settings", Context.MODE_PRIVATE).edit().putString("field", "protein").apply();
			}
		});

		sortOrderRGRP = findViewById(R.id.settings_sortOrderRGRP);
		sortOrderRGRP.setOnCheckedChangeListener((group, checkedId) -> {
			if (ascendingRB.isChecked()) {
				getSharedPreferences("Settings", Context.MODE_PRIVATE).edit().putString("sortorder", "ASC").apply();
			} else {
				getSharedPreferences("Settings", Context.MODE_PRIVATE).edit().putString("sortorder", "DESC").apply();
			}
		});
	}
}