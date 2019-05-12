package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        // COMPLETED (1) parse sandwich json to Sandwich model
        Sandwich sandwich = null;
        try {
            JSONObject sandwichJson = new JSONObject(json);

            JSONObject sandwichNameProp = sandwichJson.getJSONObject("name");
            String sandwichMainName = sandwichNameProp.getString("mainName");

            JSONArray sandwichAlsoKnownAsArray = sandwichNameProp.getJSONArray("alsoKnownAs");
            List<String> sandwichAlsoKnownAs = new ArrayList<>();
            for (int i = 0; i < sandwichAlsoKnownAsArray.length(); i++) {
                sandwichAlsoKnownAs.add(sandwichAlsoKnownAsArray.getString(i));
            }

            String sandwichPlaceOfOrigin = sandwichJson.getString("placeOfOrigin");
            String sandwichDescription = sandwichJson.getString("description");

            String sandwichImage = sandwichJson.getString("image");

            JSONArray sandwichIngredientsArray = sandwichJson.getJSONArray("ingredients");
            List<String> sandwichIngredients = new ArrayList<>();
            for (int i = 0; i < sandwichIngredientsArray.length(); i++) {
                sandwichIngredients.add(sandwichIngredientsArray.getString(i));
            }

            sandwich = new Sandwich(sandwichMainName, sandwichAlsoKnownAs, sandwichPlaceOfOrigin, sandwichDescription, sandwichImage, sandwichIngredients);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sandwich;
    }
}
