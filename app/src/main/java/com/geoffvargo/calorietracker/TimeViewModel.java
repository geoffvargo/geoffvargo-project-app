/* Geoff Vargo 101908362 */
package com.geoffvargo.calorietracker;

import java.sql.*;

import androidx.lifecycle.*;

public class TimeViewModel extends ViewModel {
	private final MutableLiveData<Timestamp> selectedTime = new MediatorLiveData<>();

	public MutableLiveData<Timestamp> getSelectedTime() {
		return selectedTime;
	}

	public void setSelectedTime(Timestamp time) {
		selectedTime.setValue(time);
	}
}
