package com.tabesto.foodie.tabestofoodie.helpers;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by beau- on 29/12/2016.
 */

public class JsonLoader {

    public static String getJsonFromAsset(Context context, String filename) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }
}
