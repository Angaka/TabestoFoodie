package com.tabesto.foodie.tabestofoodie;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by beau- on 29/12/2016.
 */

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    private Context     context;
    private List<Food>  foodList;

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageview_food)
        ImageView imgViewFood;
        @BindView(R.id.textview_food_name)
        TextView tvFoodName;
        @BindView(R.id.textview_food_description)
        TextView tvFoodDesc;
        @BindView(R.id.textview_food_price)
        TextView tvFoodPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public FoodAdapter(Context context, List<Food> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_food, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Food food = foodList.get(position);

        Glide.with(context)
            .load(food.getImage())
            .centerCrop()
            .crossFade()
            .into(holder.imgViewFood);
        holder.tvFoodName.setText(food.getName());
        holder.tvFoodDesc.setText(food.getDescription());
        holder.tvFoodPrice.setText(String.format("%.2f €", food.getPrice()));
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }
}
