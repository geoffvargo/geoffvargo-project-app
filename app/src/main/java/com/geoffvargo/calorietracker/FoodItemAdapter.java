package com.geoffvargo.calorietracker;

import android.content.*;
import android.view.*;
import android.widget.*;

import org.jetbrains.annotations.*;

import java.util.*;

import androidx.annotation.*;
import androidx.recyclerview.widget.*;

public class FoodItemAdapter extends RecyclerView.Adapter {
	private ArrayList<FoodItem> foodItemData;
	private Context parentContext;
	private FoodItem curr;

	public FoodItemAdapter(ArrayList<FoodItem> foodItemData, Context parentContext) {
		this.foodItemData = foodItemData;
		this.parentContext = parentContext;
	}

	@NonNull
	@NotNull
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parentContext).inflate(R.layout.fooditem_view, parent, false);
		return new FoodItemViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
		FoodItemViewHolder fh = (FoodItemViewHolder) holder;
		curr = foodItemData.get(position);

		fh.getName().setText(curr.getFood_name());
//		fh.getCalories().setText(curr.getCalories());
	}

	@Override
	public int getItemCount() {
		return foodItemData.size();
	}

	public class FoodItemViewHolder extends RecyclerView.ViewHolder {
		private TextView name;
		private TextView calories;
		private TextView time;

		public FoodItemViewHolder(@NotNull View itemView) {
			super(itemView);
			this.name = itemView.findViewById(R.id.nameLBL);
			this.calories = itemView.findViewById(R.id.caloriesLBL);
			this.time = itemView.findViewById(R.id.timeLBL);

			itemView.setTag(this);
		}

		public TextView getName() {
			return name;
		}

		public void setName(TextView name) {
			this.name = name;
		}

		public TextView getCalories() {
			return calories;
		}

		public void setCalories(TextView calories) {
			this.calories = calories;
		}

		public TextView getTime() {
			return time;
		}

		public void setTime(TextView time) {
			this.time = time;
		}
	}
}
