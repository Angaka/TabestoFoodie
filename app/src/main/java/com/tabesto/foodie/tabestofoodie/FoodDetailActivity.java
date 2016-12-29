package com.tabesto.foodie.tabestofoodie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by beau- on 29/12/2016.
 */

public class FoodDetailActivity extends AppCompatActivity {

    @BindView(R.id.imageview_food)
    ImageView imgViewFood;
    @BindView(R.id.textview_food_name)
    TextView tvFoodName;
    @BindView(R.id.textview_food_description)
    TextView tvFoodDesc;
    @BindView(R.id.textview_food_price)
    TextView tvFoodPrice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        ButterKnife.bind(this);

        Food food = (Food) Parcels.unwrap(getIntent().getParcelableExtra("food"));
        Glide.with(this)
                .load(food.getImage())
                .centerCrop()
                .crossFade()
                .into(imgViewFood);
        tvFoodName.setText(food.getName());
        tvFoodDesc.setText(food.getDescription());
        tvFoodPrice.setText(String.valueOf(food.getPrice()) + " €");
        
    }
}
