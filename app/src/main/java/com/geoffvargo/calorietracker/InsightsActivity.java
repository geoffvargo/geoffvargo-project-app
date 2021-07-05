package com.geoffvargo.calorietracker;

import android.os.*;
import android.widget.*;

import androidx.appcompat.app.*;

public class InsightsActivity extends AppCompatActivity {
	private Button exitBTN;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insights);

		exitBTN = findViewById(R.id.insights_exitBTN);
		exitBTN.setOnClickListener(c -> finish());
	}
}