package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject masterJSONObject = new JSONObject(json);
            JSONObject name = masterJSONObject.getJSONObject("name");
            String placeOfOrigin = masterJSONObject.getString("placeOfOrigin");
            String description = masterJSONObject.getString("description");
            String imageURL = masterJSONObject.getString("image");
            JSONArray ingredients = masterJSONObject.getJSONArray("ingredients");
            return new Sandwich(name.getString("mainName"),null,
                    placeOfOrigin,description,
                    imageURL,null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
