package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject name = jsonObject.getJSONObject("name");
            String mainName = name.getString("mainName");
            JSONArray alsoKnownAsJson = name.getJSONArray("alsoKnownAs");
            List<String> alsoKnownAs = new ArrayList<>();
            if (alsoKnownAsJson != null) {
                for(int i = 0; i < alsoKnownAsJson.length(); i++) {
                    alsoKnownAs.add(alsoKnownAsJson.getString(i));
                }
            }
            String placeOfOrigin = jsonObject.getString("placeOfOrigin");
            String description = jsonObject.getString("description");
            String image = jsonObject.getString("image");
            JSONArray ingredientsJson = jsonObject.getJSONArray("ingredients");
            List<String> ingredients = new ArrayList<>();
            if (ingredientsJson != null) {
                for (int i = 0; i < ingredientsJson.length(); i++) {
                    ingredients.add(ingredientsJson.getString(i));
                }
            }
            sandwich = new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sandwich;
    }
}
