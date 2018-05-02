package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject sandwich = new JSONObject(json);

            JSONObject name = sandwich.getJSONObject("name");
            String mainName = name.getString("mainName");

            List<String> alsoKnownAs = new ArrayList<>();
            JSONArray akaArray = name.getJSONArray("alsoKnownAs");
            for (int i = 0; i < akaArray.length(); i++) {
                alsoKnownAs.add(akaArray.getString(i));
            }

            String placeOfOrigin = sandwich.getString("placeOfOrigin");
            String description = sandwich.getString("description");
            String image = sandwich.getString("image");

            List<String> ingredients = new ArrayList<>();
            JSONArray ingredientArray = sandwich.getJSONArray("ingredients");
            for (int j = 0; j < ingredientArray.length(); j++) {
                ingredients.add(ingredientArray.getString(j));
            }

            return new Sandwich(
                    mainName,
                    alsoKnownAs,
                    placeOfOrigin,
                    description,
                    image,
                    ingredients);
        } catch (JSONException e) {
            return null;
        }
    }
}
