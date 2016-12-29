package com.tabesto.foodie.tabestofoodie;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.rohit.recycleritemclicksupport.RecyclerItemClickSupport;
import com.tabesto.foodie.tabestofoodie.helpers.JsonLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

public class FoodActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview_food)
    RecyclerView rvFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        initList();
    }

    private List<Food>    initFoodList() {
        List<Food> foodList = new ArrayList<>();

        try {
            JSONArray foodsArray = new JSONArray(JsonLoader.getJsonFromAsset(this, "food.json"));

            for (int i = 0; i < foodsArray.length(); i++) {
                JSONObject foodObject = foodsArray.getJSONObject(i);
                String name = foodObject.getString("name");
                double price = foodObject.getDouble("price");
                String image = foodObject.getString("image");
                int imageId = getResources().getIdentifier(image, null, getPackageName());
                String description = foodObject.getString("description");

                foodList.add(new Food(name, price, imageId, description));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return foodList;
    }

    private void initList() {
        rvFood.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        final List<Food> foodList = initFoodList();

        FoodAdapter adapter = new FoodAdapter(this, foodList);
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(adapter);
        alphaAdapter.setFirstOnly(false);
        rvFood.setAdapter(new ScaleInAnimationAdapter(alphaAdapter));
        RecyclerItemClickSupport.addTo(rvFood).setOnItemClickListener(new RecyclerItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(FoodActivity.this, FoodDetailActivity.class);
                intent.putExtra("food", Parcels.wrap(foodList.get(position)));
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
