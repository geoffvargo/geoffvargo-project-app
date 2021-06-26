package com.geoffvargo.calorietracker;

import android.content.*;
import android.view.*;

import org.jetbrains.annotations.*;

import java.util.*;

import androidx.annotation.*;
import androidx.recyclerview.widget.*;

class FoodItemAdapter extends RecyclerView.Adapter {
	private ArrayList<FoodItem> foodItemData;
	private Context parentContext;

	public FoodItemAdapter(ArrayList<FoodItem> foodItemData, Context parentContext) {
		this.foodItemData = foodItemData;
		this.parentContext = parentContext;
	}

	@NonNull
	@NotNull
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
		return null;
	}

	@Override
	public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {

	}

	@Override
	public int getItemCount() {
		return foodItemData.size();
	}

	public class FoodItemViewHolder extends RecyclerView.ViewHolder {
		public FoodItemViewHolder(@NonNull @NotNull View itemView) {
			super(itemView);
		}
	}
}
