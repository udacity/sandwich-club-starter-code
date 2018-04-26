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
            //Create JSONObjects from json String that was passed in

            JSONObject jsonSandwichObject = new JSONObject(json);
            JSONObject jsonSandwichName = jsonSandwichObject.getJSONObject("name");

            //If there is nothing at the 0 index of the JSON array, we need an alternative response
            String[] data = {" "};
            JSONArray emptyArray = new JSONArray(Arrays.asList(data));

            //Create JSONArrays for both the ArrayList data types

            JSONArray jsonAlsoKnownAs;

            if(jsonSandwichName.getJSONArray("alsoKnownAs").length() > 0) {

                jsonAlsoKnownAs = jsonSandwichName.getJSONArray("alsoKnownAs");

             //If JSONArray is empty, we want to set it to the empty array to prevent errors

            } else{
                jsonAlsoKnownAs = emptyArray;
            }


            JSONArray jsonIngredients;

            if(jsonSandwichObject.getJSONArray("ingredients").length() > 0) {

                jsonIngredients = jsonSandwichObject.getJSONArray("ingredients");

             //If JSONArray is empty, we want to set it to the empty array to prevent errors

            } else {
                jsonIngredients = emptyArray;
            }

            //Store the values from JSONObject to use when creating a new sandwich

            String mainName = jsonSandwichName.getString("mainName");

            //Convert JSONArrays for both alsoKnownAs and ingredients to String list since the object requires it

            List<String> alsoKnownAs = new ArrayList<String>();

            for(int i = 0; i < jsonAlsoKnownAs.length(); i++) {
                alsoKnownAs.add(jsonAlsoKnownAs.getString(i));
            }

            List<String> ingredients = new ArrayList<String>();

            for(int i = 0; i < jsonIngredients.length(); i++) {
                ingredients.add(jsonIngredients.getString(i));
            }

            String placeOfOrigin = jsonSandwichObject.getString("placeOfOrigin");
            String description = jsonSandwichObject.getString("description");

            String image = jsonSandwichObject.getString("image");

            //Some testing that I used to make sure valid JSON was being returned from Json objects/arrays

            Log.i(TAG, "parseSandwichJson: " + mainName + " " + placeOfOrigin + " " + image + " " + jsonAlsoKnownAs.get(0) + " " + jsonIngredients.get(0) + " " + description);

            //Create new sandwich object using values above

            newSandwich = new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newSandwich;
    }
}
