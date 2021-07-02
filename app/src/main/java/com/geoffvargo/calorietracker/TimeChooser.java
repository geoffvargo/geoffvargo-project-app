package com.geoffvargo.calorietracker;

import android.app.*;
import android.os.*;
import android.text.format.*;
import android.widget.*;

import org.jetbrains.annotations.*;

import java.util.*;

import androidx.appcompat.app.*;

public class TimeChooser extends AppCompatDialogFragment implements TimePickerDialog.OnTimeSetListener {

	@Override
	public @NotNull Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current time as the default values for the picker
		final Calendar c      = Calendar.getInstance();
		int            hour   = c.get(Calendar.HOUR_OF_DAY);
		int            minute = c.get(Calendar.MINUTE);

		// Create a new instance of TimePickerDialog and return it
		return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
	}

	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		// Do something with the time chosen by the user
	}
}