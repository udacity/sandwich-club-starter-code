package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class JsonUtils {

    private static final String TAG = "JsonUtils";

    private static final String SANDWICH_NAME = "name";
    private static final String SANDWICH_MAIN_NAME = "mainName";
    private static final String SANDWICH_AKA = "alsoKnownAs";
    private static final String SANDWICH_ORIGIN = "placeOfOrigin";
    private static final String SANDWICH_DESCRIPTION = "description";
    private static final String SANDWICH_IMAGE = "image";
    private static final String SANDWICH_INGREDIENTS = "ingredients";


    public static Sandwich parseSandwichJson(String json) {
        // Create our sandwich
        Sandwich sandwich = new Sandwich();

        // Json sandwich anyone? We are manually parsing the json here by using the default json.org library
        try {
            JSONObject jSondwich = new JSONObject(json);
            JSONObject jSondwichName = new JSONObject(jSondwich.getString(SANDWICH_NAME));
            JSONArray jSondwichAkaArray = jSondwichName.getJSONArray(SANDWICH_AKA);

            List<String> AKAList = new ArrayList<>();
            for (int i = 0; i < jSondwichAkaArray.length(); i++) {
                AKAList.add(jSondwichAkaArray.getString(i));
            }

            JSONArray jSondwichIngredientsArray = jSondwich.getJSONArray(SANDWICH_INGREDIENTS);

            List<String> ingredientList = new ArrayList<>();
            for (int i = 0; i < jSondwichIngredientsArray.length(); i++) {
                ingredientList.add(jSondwichIngredientsArray.getString(i));
            }

            sandwich.setMainName(jSondwichName.getString(SANDWICH_MAIN_NAME));
            sandwich.setAlsoKnownAs(AKAList);
            sandwich.setPlaceOfOrigin(jSondwich.getString(SANDWICH_ORIGIN));
            sandwich.setDescription(jSondwich.getString(SANDWICH_DESCRIPTION));
            sandwich.setImage(jSondwich.getString(SANDWICH_IMAGE));
            sandwich.setIngredients(ingredientList);
            Log.d(TAG, "sandwich.getMainName: " + sandwich.getMainName());
            Log.d(TAG, "sandwich.getAlsoKnownAs: " + sandwich.getAlsoKnownAs());
            Log.d(TAG, "sandwich.getPlaceOfOrigin: " + sandwich.getPlaceOfOrigin());
            Log.d(TAG, "sandwich.getDescription: " + sandwich.getDescription());
            Log.d(TAG, "sandwich.getImage: " + sandwich.getImage());
            Log.d(TAG, "sandwich.getIngredients: " + sandwich.getIngredients());

        } catch (final JSONException e) {
            e.printStackTrace();
            return null;
        }

        return sandwich;
    }




}
