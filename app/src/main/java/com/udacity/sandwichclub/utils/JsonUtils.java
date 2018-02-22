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
            JSONObject name = sandwich.getJSONObject(JSONStructure.NAME.JSON_OBJ);
            String mainName = name.getString(JSONStructure.NAME.MAIN_NAME);
            JSONArray alsoKnownAsArray = name.getJSONArray(JSONStructure.NAME.ALSO_KNOWN_AS_ARRAY);

            List<String> alsoKnownAs = new ArrayList<>();

            for(int i=0;i<alsoKnownAsArray.length();i++) {
                alsoKnownAs.add(alsoKnownAsArray.getString(i));
            }

            String placeOfOrigin = sandwich.getString(JSONStructure.PLACE_OF_ORIGIN);
            String description = sandwich.getString(JSONStructure.DESCRIPTION);
            String imageUrl = sandwich.getString(JSONStructure.IMAGE_URL);
            JSONArray ingredientsJson = sandwich.getJSONArray(JSONStructure.INGREDIENTS_ARRAY);
            List<String> ingredients = new ArrayList<>();

            for(int i=0;i<ingredientsJson.length();i++) {
                ingredients.add(ingredientsJson.getString(i));
            }

            return new Sandwich(
                    mainName,
                    alsoKnownAs,
                    placeOfOrigin,
                    description,
                    imageUrl,
                    ingredients
            );


        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}
