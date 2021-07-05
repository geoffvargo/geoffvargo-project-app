/* Geoff Vargo 101908362 */
package com.geoffvargo.calorietracker;

import android.content.*;
import android.os.*;
import android.widget.*;

import androidx.appcompat.app.*;

public class SettingsActivity extends AppCompatActivity {
	private RadioGroup sortByRGRP;
	private String sortBy;
	private Button applyBTN;
	private Button doneBTN;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		this.setFinishOnTouchOutside(false);

		RadioButton foodNameRB = findViewById(R.id.settings_foodNameRB);
		RadioButton caloriesRB = findViewById(R.id.settings_caloriesRB);
		RadioButton carbsRB    = findViewById(R.id.settings_carbsRB);
		RadioButton fatRB      = findViewById(R.id.settings_fatRB);
		RadioButton proteinRB = findViewById(R.id.settings_proteinRB);

		sortBy = getSharedPreferences("Settings", Context.MODE_PRIVATE).getString("field", "food_name");

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
			} else {
				getSharedPreferences("Settings", Context.MODE_PRIVATE).edit().putString("field", "protein").apply();
			}
		});
	}
}