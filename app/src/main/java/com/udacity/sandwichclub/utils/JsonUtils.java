package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        Sandwich sandwich = null;

        try {
            // input json is a JSONObject, so create a new  one
            JSONObject jsonResponse = new JSONObject(json);
            // one the first level, the JSONObjecy contains following elements:
            // name --> JSONObject, placeOfOrigin --> String, description --> String,
            // image --> String, ingredients --> array
            JSONObject name = jsonResponse.getJSONObject("name");
            String placeOfOrigin = jsonResponse.getString("placeOfOrigin");
            String description = jsonResponse.getString("description");
            String image = jsonResponse.getString("image");
            JSONArray ingredients = jsonResponse.getJSONArray("ingredients");

            // the JSONObject name contains following levels
            // mainName --> String, alsoKnownAs --> JSONArray
            String mainName = name.getString("mainName");
            JSONArray alsoKnownAs = name.getJSONArray("alsoKnownAs");

            // convert JSONArray alsoKnownAs to a list
            ArrayList<String> alsoKnownAsList = new ArrayList<String>();
            for(int i=0; i < alsoKnownAs.length(); i++){
                alsoKnownAsList.add(alsoKnownAs.getString(i));
            }

            // convert JSONArray ingredients to a list
            ArrayList<String> ingredientsList = new ArrayList<String>();
            for(int i=0; i < ingredients.length(); i++){
                ingredientsList.add(ingredients.getString(i));
            }

            sandwich = new Sandwich(mainName, (List) alsoKnownAsList, placeOfOrigin,
                    description, image, (List) ingredientsList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sandwich;
    }
}
