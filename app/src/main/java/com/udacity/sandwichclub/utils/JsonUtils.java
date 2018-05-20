package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static String NAME = "name";
    private static String MAIN_NAME = "mainName";
    private static String ALSO_KNOWN_AS = "alsoKnownAs";
    private static String PLACE_OF_ORIGIN = "placeOfOrigin";
    private static String DESCRIPTION = "description";
    private static String IMAGE = "image";
    private static String INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject sandwich = new JSONObject(json);

            // Grab embedded mainName object for populating AKA section
            JSONObject name = sandwich.getJSONObject(NAME);
            String mainName = name.getString(MAIN_NAME);

            List<String> alsoKnownAs = new ArrayList<>();
            JSONArray akaArray = name.getJSONArray(ALSO_KNOWN_AS);
            for (int i = 0; i < akaArray.length(); i++) {
                alsoKnownAs.add(akaArray.getString(i));
            }

            // Rest of params live at top level of JSON object
            String placeOfOrigin = sandwich.getString(PLACE_OF_ORIGIN);
            String description = sandwich.getString(DESCRIPTION);
            String image = sandwich.getString(IMAGE);

            List<String> ingredients = new ArrayList<>();
            JSONArray ingredientArray = sandwich.getJSONArray(INGREDIENTS);
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
