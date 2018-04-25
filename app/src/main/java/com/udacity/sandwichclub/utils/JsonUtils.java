package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        Sandwich newSandwich = new Sandwich();

        try {
            //Create JSONObject from json String that was passed in

            JSONObject jsonSandwichObject = new JSONObject(json);

            //Store the values from JSONObject to use when creating a new sandwich

            String mainName = jsonSandwichObject.getString("mainName");
            List<String> alsoKnownAs = Collections.singletonList(jsonSandwichObject.getString("alsoKnownAs"));
            String placeOfOrigin = jsonSandwichObject.getString("placeOfOrigin");
            String description = jsonSandwichObject.getString("description");
            String image = jsonSandwichObject.getString("image");
            List<String> ingredients = Collections.singletonList(jsonSandwichObject.getString("ingredients"));

            //Create new sandwich object using values above

            newSandwich = new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);  

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newSandwich;
    }
}
