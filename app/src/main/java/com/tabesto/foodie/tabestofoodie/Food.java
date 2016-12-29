package com.tabesto.foodie.tabestofoodie;

import android.graphics.drawable.Drawable;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

/**
 * Created by beau- on 26/12/2016.
 */
@Parcel
public class Food {

    private String  name;
    private double  price;
    private int image;
    private String  description;

    @ParcelConstructor
    public Food(String name, double price, int image, String description) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
