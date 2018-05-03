package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws JSONException {

        /* Constants - To hold the keys needed to extract the info from JSON String */
        final String SANDWICH_NAME = "name";
        final String SANDWICH_MAIN_NAME = "mainName";
        final String SANDWICH_KNOWN_AS = "alsoKnownAs";
        final String SANDWICH_PLACE_OF_ORIGIN = "placeOfOrigin";
        final String SANDWICH_DESCRIPTION = "description";
        final String SANDWICH_IMAGE = "image";
        final String SANDWICH_INGREDIENTS = "ingredients";

        /* Sandwich Object to hold each Sandwich Details */
        Sandwich sandwich = new Sandwich();

        if (json == null) {
            return null;
        }

        JSONObject sandwichJSON = new JSONObject(json);

        /* Extract Information from the JSON Object */
        JSONObject sandwichName = sandwichJSON.getJSONObject(SANDWICH_NAME);
        String sandwichMainName = sandwichName.getString(SANDWICH_MAIN_NAME);
        JSONArray sandwichAlsoKnownAs = sandwichName.getJSONArray(SANDWICH_KNOWN_AS);
        String sandwichPlaceOfOrigin = sandwichJSON.getString(SANDWICH_PLACE_OF_ORIGIN);
        String sandwichDescription = sandwichJSON.getString(SANDWICH_DESCRIPTION);
        String sandwichImage = sandwichJSON.getString(SANDWICH_IMAGE);
        JSONArray sandwichIngredients = sandwichJSON.getJSONArray(SANDWICH_INGREDIENTS);

        /* Set the Sandwich Details */
        sandwich.setMainName(sandwichMainName);
        List<String> listAlsoKnownAs = new ArrayList<String>();
        if (sandwichAlsoKnownAs != null) {
            for (int i=0; i<sandwichAlsoKnownAs.length(); i++){
                listAlsoKnownAs.add(sandwichAlsoKnownAs.getString(i));
            }
        }
        sandwich.setAlsoKnownAs(listAlsoKnownAs);
        sandwich.setPlaceOfOrigin(sandwichPlaceOfOrigin);
        sandwich.setDescription(sandwichDescription);
        sandwich.setImage(sandwichImage);
        List<String> listIngredients = new ArrayList<String>();
        if (sandwichIngredients != null) {
            for (int i=0; i<sandwichIngredients.length(); i++){
                listIngredients.add(sandwichIngredients.getString(i));
            }
        }
        sandwich.setIngredients(listIngredients);

        return sandwich;
    }
}
