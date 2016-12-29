package com.tabesto.foodie.tabestofoodie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.himanshusoni.quantityview.QuantityView;

/**
 * Created by beau- on 29/12/2016.
 */

public class FoodDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.imageview_food)
    ImageView imgViewFood;
    @BindView(R.id.textview_food_name)
    TextView tvFoodName;
    @BindView(R.id.textview_food_description)
    TextView tvFoodDesc;
    @BindView(R.id.textview_food_price)
    TextView tvFoodPrice;


    @BindView(R.id.textview_food_name_qty)
    TextView tvFoodNameQty;
    @BindView(R.id.quantityview_food)
    QuantityView qtyViewFood;
    @BindView(R.id.textview_total)
    TextView tvTotal;

    @BindView(R.id.button_order)
    Button btnOrder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final Food food = Parcels.unwrap(getIntent().getParcelableExtra("food"));
        setTitle(food.getName());
        Glide.with(this)
                .load(food.getImage())
                .centerCrop()
                .crossFade()
                .into(imgViewFood);
        tvFoodName.setText(food.getName());
        tvFoodDesc.setText(food.getDescription());
        tvFoodPrice.setText(String.valueOf(food.getPrice()) + " €");

        tvFoodNameQty.setText(food.getName() + " quantity");
        tvTotal.setText(String.format("%.2f €", food.getPrice()));
        qtyViewFood.setOnQuantityChangeListener(new QuantityView.OnQuantityChangeListener() {
            @Override
            public void onQuantityChanged(int newQuantity, boolean programmatically) {
                double total = food.getPrice() * newQuantity;

                tvTotal.setText(String.format("%.2f €", total));
            }

            @Override
            public void onLimitReached() {}
        });
    }

    @OnClick(R.id.button_order)
    public void orderAction() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}
