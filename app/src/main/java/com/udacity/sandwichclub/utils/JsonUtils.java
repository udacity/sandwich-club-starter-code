/*
 * Copyright (C) 2018 The Android Open Source Project
 */
package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility functions to handle Sandwich JSON data.
 */
public class JsonUtils {

    /**
     * This method parses JSON String and returns a Sandwich Object
     * describing details about the sandwich
     *
     * @param json JSON String
     * @return Sandwich Object
     * @throws JSONException If JSON data cannot be properly parsed
     */
    public static Sandwich parseSandwichJson(String json) throws JSONException {

        /* Constants - To hold the keys needed to extract the info from JSON String */
        final String SANDWICH_NAME = "name";
        final String SANDWICH_MAIN_NAME = "mainName";
        final String SANDWICH_KNOWN_AS = "alsoKnownAs";
        final String SANDWICH_PLACE_OF_ORIGIN = "placeOfOrigin";
        final String SANDWICH_DESCRIPTION = "description";
        final String SANDWICH_IMAGE = "image";
        final String SANDWICH_INGREDIENTS = "ingredients";

        /* Local Variable - To hold each Sandwich Details */
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
        sandwich.setAlsoKnownAs(jsonArrayToListString(sandwichAlsoKnownAs));
        sandwich.setPlaceOfOrigin(sandwichPlaceOfOrigin);
        sandwich.setDescription(sandwichDescription);
        sandwich.setImage(sandwichImage);
        sandwich.setIngredients(jsonArrayToListString(sandwichIngredients));

        return sandwich;
    }

    /**
     * Helper Method to convert a JSONArray to a List<String>
     */
    private static List<String> jsonArrayToListString(JSONArray jsonArray) throws JSONException {
        List<String> list = new ArrayList<String>();
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                list.add(jsonArray.getString(i));
            }
        }
        return list;
    }
}
