package com.geoffvargo.calorietracker;

import android.content.*;
import android.view.*;
import android.widget.*;

import com.google.android.material.switchmaterial.*;

import org.jetbrains.annotations.*;

import java.util.*;

import androidx.recyclerview.widget.*;

/**
 * The type Food item adapter.
 */
public class FoodItemAdapter extends RecyclerView.Adapter {
	private ArrayList<FoodItem> foodItemData;
	private Context parentContext;
	private FoodItem curr;

	public FoodItemAdapter(ArrayList<FoodItem> foodItemData, Context parentContext) {
		this.foodItemData = foodItemData;
		this.parentContext = parentContext;
	}

	/**
	 * Populates each ViewHolder with the details from its corresponding FoodItem object in the database.
	 *
	 * @param parent
	 * 		The ViewGroup into which the new View will be added after it is bound to
	 * 		an adapter position.
	 * @param viewType
	 * 		The view type of the new View.
	 * @return A new ViewHolder that holds a View of the given view type.
	 * @see #getItemViewType(int)
	 * @see #onBindViewHolder(RecyclerView.ViewHolder, int)
	 */
	@NotNull
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parentContext).inflate(R.layout.fooditem_view, parent, false);
		return new FoodItemViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NotNull RecyclerView.ViewHolder holder, int position) {
		FoodItemViewHolder fh = (FoodItemViewHolder) holder;
		curr = foodItemData.get(position);

		fh.getName().setText(curr.getFood_name());
		fh.getCalories().setText(String.format(Locale.getDefault(), "%d", curr.getCalories()));
		fh.getTime().setText(curr.getTime().toString());
		fh.getCarbs().setText(String.format(Locale.getDefault(), "%.1f", curr.getCarbs()));
		fh.getFat().setText(String.format(Locale.getDefault(), "%.1f", curr.getFat()));
		fh.getProtein().setText(String.format(Locale.getDefault(), "%.1f", curr.getProtein()));
		fh.getServingNum().setText(String.valueOf(curr.getServings()));
		fh.getServingSize().setText(String.valueOf(curr.getServing_size()));
		fh.getId().setText(String.valueOf(curr.get_id()));
		fh.getMeal().setText(String.valueOf(curr.getMeal_name()));

		Button editBTN = fh.getEditBTN();
		editBTN.setOnClickListener(v -> {
			TextView itemID      = (TextView) holder.itemView.findViewById(R.id._id);
			TextView name        = (TextView) holder.itemView.findViewById(R.id.nameLBL);
			TextView calories    = (TextView) holder.itemView.findViewById(R.id.caloriesLBL);
			TextView servings    = (TextView) holder.itemView.findViewById(R.id.detail_servingNumLBL);
			TextView servingSize = (TextView) holder.itemView.findViewById(R.id.detail_servingSizeLBL);
			TextView carbs       = (TextView) holder.itemView.findViewById(R.id.detail_carbsLBL);
			TextView fat         = (TextView) holder.itemView.findViewById(R.id.detail_fatLBL);
			TextView protein     = (TextView) holder.itemView.findViewById(R.id.detail_proteinLBL);
			TextView time        = (TextView) holder.itemView.findViewById(R.id.timeLBL);
			TextView meal        = (TextView) holder.itemView.findViewById(R.id.detail_mealLBL);


			Context context = v.getContext();
			Intent  intent  = new Intent(context, EditFoodItem.class);
			intent.putExtra("_id", Integer.parseInt(itemID.getText().toString()));
			intent.putExtra("name", name.getText().toString());
			intent.putExtra("timestamp", time.getText().toString());
			intent.putExtra("calories", Integer.parseInt(calories.getText().toString()));
			intent.putExtra("servings", Float.parseFloat(servings.getText().toString()));
			intent.putExtra("serving_size", Float.parseFloat(servingSize.getText().toString()));
			intent.putExtra("carbs", Float.parseFloat(carbs.getText().toString()));
			intent.putExtra("fat", Float.parseFloat(fat.getText().toString()));
			intent.putExtra("protein", Float.parseFloat(protein.getText().toString()));
			intent.putExtra("meal", meal.getText().toString());

			context.startActivity(intent);
		});
	}

	@Override
	public int getItemCount() {
		return foodItemData.size();
	}

	public class FoodItemViewHolder extends RecyclerView.ViewHolder {
		private TextView id;
		private TextView name;
		private TextView calories;
		private TextView time;
		private TextView servingNum;
		private TextView servingSize;
		private TextView carbs;
		private TextView fat;
		private TextView protein;
		private TextView meal;
		private View details;
		private SwitchMaterial detailBTN;
		private Button editBTN;

		public FoodItemViewHolder(@NotNull View itemView) {
			super(itemView);
			name = itemView.findViewById(R.id.nameLBL);
			calories = itemView.findViewById(R.id.caloriesLBL);
			time = itemView.findViewById(R.id.timeLBL);
			carbs = itemView.findViewById(R.id.detail_carbsLBL);
			fat = itemView.findViewById(R.id.detail_fatLBL);
			protein = itemView.findViewById(R.id.detail_proteinLBL);
			servingSize = itemView.findViewById(R.id.detail_servingSizeLBL);
			servingNum = itemView.findViewById(R.id.detail_servingNumLBL);
			details = itemView.findViewById(R.id.viewDetailsLAYOUT);
			detailBTN = itemView.findViewById(R.id.detailsBTN);
			editBTN = itemView.findViewById(R.id.editBTN);
			meal = itemView.findViewById(R.id.detail_mealLBL);
			id = itemView.findViewById(R.id._id);

			detailBTN.setOnCheckedChangeListener((buttonView, c) -> {
				if (c) {
					details.setVisibility(View.VISIBLE);
				} else {
					details.setVisibility(View.GONE);
				}
			});
		}

		public TextView getMeal() {
			return meal;
		}

		public TextView getId() {
			return id;
		}

		public Button getEditBTN() {
			return editBTN;
		}

		public TextView getServingNum() {
			return servingNum;
		}

		public TextView getServingSize() {
			return servingSize;
		}

		public TextView getCarbs() {
			return carbs;
		}

		public TextView getFat() {
			return fat;
		}

		public TextView getProtein() {
			return protein;
		}

		public View getDetails() {
			return details;
		}

		public SwitchMaterial getDetailBTN() {
			return detailBTN;
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
