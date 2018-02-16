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
            JSONObject sandwichJson = new JSONObject(json);
            // mainName
            String mainName = sandwichJson.getJSONObject("name").getString("mainName");
            // alsoKnownAs
            List<String> alsoKnownAs = new ArrayList<String>();
            JSONArray alsoKnownAsArray = sandwichJson.getJSONObject("name")
                    .getJSONArray("alsoKnownAs");
            if (alsoKnownAsArray != null && alsoKnownAsArray.length() > 0) {
                for (int i=0;i < alsoKnownAsArray.length(); i++) {
                    alsoKnownAs.add(alsoKnownAsArray.getString(i));
                }
            }
            // placeOfOrigin
            String placeOfOrigin = sandwichJson.getString("placeOfOrigin");
            // description
            String description = sandwichJson.getString("description");
            // image
            String image = sandwichJson.getString("image");
            // ingredients
            List<String> ingredients = new ArrayList<String>();
            JSONArray ingredientsArray = sandwichJson.getJSONArray("ingredients");
            if (ingredientsArray != null && ingredientsArray.length() > 0) {
                for (int i=0;i < ingredientsArray.length(); i++) {
                    ingredients.add(ingredientsArray.getString(i));
                }
            }
            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
