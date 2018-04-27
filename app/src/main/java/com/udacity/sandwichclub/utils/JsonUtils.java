package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JsonUtils {
    private static final String TAG = "JsonUtilSandwich";

    public static Sandwich parseSandwichJson(String json) {

        Sandwich newSandwich = new Sandwich();

        try {
            //Create JSONObjects

            JSONObject jsonSandwichObject = new JSONObject(json);
            JSONObject jsonSandwichName = jsonSandwichObject.getJSONObject("name");


            //Create JSONArrays for both the ArrayList data types

            JSONArray jsonAlsoKnownAs;

            if(jsonSandwichName.getJSONArray("alsoKnownAs").length() > 0) {

                jsonAlsoKnownAs = jsonSandwichName.getJSONArray("alsoKnownAs");

            } else {
                jsonAlsoKnownAs = null;
            }


            JSONArray jsonIngredients;

            if(jsonSandwichObject.getJSONArray("ingredients").length() > 0) {

                jsonIngredients = jsonSandwichObject.getJSONArray("ingredients");

            } else {
                jsonIngredients = null;
            }

            //Store values to pass in sandwich object

            String mainName = jsonSandwichName.getString("mainName");

            //Convert JSONArrays for both alsoKnownAs and ingredients to String list since the object requires it

            List<String> alsoKnownAs = new ArrayList<String>();

            if (jsonAlsoKnownAs != null) {

                for (int i = 0; i < jsonAlsoKnownAs.length(); i++) {
                    alsoKnownAs.add(jsonAlsoKnownAs.getString(i));
                }
            }

            List<String> ingredients = new ArrayList<String>();

            if(jsonIngredients != null) {

                for (int i = 0; i < jsonIngredients.length(); i++) {
                    ingredients.add(jsonIngredients.getString(i));
                }

            }

            String placeOfOrigin = jsonSandwichObject.getString("placeOfOrigin");
            String description = jsonSandwichObject.getString("description");

            String image = jsonSandwichObject.getString("image");

            //Create new sandwich object using values above

            newSandwich = new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newSandwich;
    }
}
