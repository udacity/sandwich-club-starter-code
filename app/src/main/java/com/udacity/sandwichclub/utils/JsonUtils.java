package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject object = new JSONObject(json);
            String SandwichName = "";
            String placeOfOrigin = "";
            String description = "";
            String image = "";
            ArrayList<String> alsoKnownAs = new ArrayList<>();
            ArrayList<String> ingredients = new ArrayList<>();

            if (object.has("name")) {
                JSONObject nameObj = object.getJSONObject("name");

                if (nameObj.has("mainName"))
                    SandwichName = nameObj.getString("mainName");

                if (nameObj.has("alsoKnownAs")) {
                    JSONArray alternativeNames = nameObj.getJSONArray("alsoKnownAs");
                    for (int i=0; i<alternativeNames.length(); i++)
                        alsoKnownAs.add(alternativeNames.getString(i));
                }
            }

            if (object.has("ingredients")) {
                JSONArray ingr = object.getJSONArray("ingredients");
                for (int i=0; i<ingr.length(); i++)
                    ingredients.add(ingr.getString(i));
            }

            if (object.has("description"))
                description = object.getString("description");

            if (object.has("placeOfOrigin"))
                placeOfOrigin = object.getString("placeOfOrigin");

            if (object.has("image"))
                image = object.getString("image");

            return new Sandwich(SandwichName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
        } catch (JSONException e) {
            return null;
        }

    }
}
