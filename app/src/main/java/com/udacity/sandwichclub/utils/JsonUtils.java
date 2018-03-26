package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        String mainName;
        List<String> alsoKnownAs;
        String placeOfOrigin;
        String description;
        String image;
        List<String> ingredients;

        try {
            JSONObject nameObject = jsonObject.getJSONObject("name");
            mainName = nameObject.getString("mainName");
            JSONArray alsoKnowAsObject = nameObject.getJSONArray("alsoKnownAs");

            alsoKnownAs = new ArrayList<>();
            for(int i = 0 ; i < alsoKnowAsObject.length(); i++) {
                alsoKnownAs.add(alsoKnowAsObject.getString(i));
            }
            placeOfOrigin = jsonObject.getString("placeOfOrigin");
            description = jsonObject.getString("description");
            image = jsonObject.getString("image");

            JSONArray ingredientsObject = jsonObject.getJSONArray("ingredients");
            ingredients = new ArrayList<>();
            for(int i=0; i < ingredientsObject.length(); i++) {
                ingredients.add(ingredientsObject.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
    }
}
