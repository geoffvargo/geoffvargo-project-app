package com.geoffvargo.calorietracker;

import android.app.*;
import android.os.*;
import android.text.format.*;
import android.widget.*;

import org.jetbrains.annotations.*;

import java.sql.*;
import java.util.*;

import androidx.appcompat.app.*;
import androidx.lifecycle.*;

public class TimeChooser extends AppCompatDialogFragment implements TimePickerDialog.OnTimeSetListener {
	private TimeViewModel tvm;
	private Timestamp timestamp;
	private Calendar c;
	long currtime;

	@Override
	public @NotNull Dialog onCreateDialog(Bundle savedInstanceState) {
		tvm = new ViewModelProvider(requireActivity()).get(TimeViewModel.class);
		// Use the current time as the default values for the picker
		c      = Calendar.getInstance();
		int            hour   = c.get(Calendar.HOUR_OF_DAY);
		int            minute = c.get(Calendar.MINUTE);
//		c.setTimeInMillis();
		currtime = c.getTimeInMillis();
//		timestamp = new Timestamp(c.getTimeInMillis());

		// Create a new instance of TimePickerDialog and return it
		return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
	}



	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		// Do something with the time chosen by the user
//		timestamp.setTime(c.getTimeInMillis());
		c.set(Calendar.HOUR_OF_DAY, hourOfDay);
		c.set(Calendar.MINUTE, minute);
		timestamp = new Timestamp(c.getTimeInMillis());
		tvm.setSelectedTime(timestamp);
	}
}