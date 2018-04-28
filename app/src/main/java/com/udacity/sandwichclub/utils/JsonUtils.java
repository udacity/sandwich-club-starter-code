package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String SANDWICH_NAME = "name";
    private static final String SANDWICH_MAIN_NAME = "mainName";
    private static final String ALSO_KNOWN_AS = "alsoKnownAs";
    private static final String PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "image";
    private static final String INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich  = new Sandwich();

        try {
            JSONObject sandwichData =  new JSONObject(json);
            JSONObject sandwichNameData = sandwichData.optJSONObject(SANDWICH_NAME);
            sandwich.setMainName( sandwichNameData.optString(SANDWICH_MAIN_NAME) );
            sandwich.setAlsoKnownAs( getJsonList(sandwichNameData.optJSONArray(ALSO_KNOWN_AS)));

            sandwich.setPlaceOfOrigin( sandwichData.optString(PLACE_OF_ORIGIN) );
            sandwich.setDescription( sandwichData.optString(DESCRIPTION) );
            sandwich.setImage( sandwichData.optString(IMAGE) );
            sandwich.setIngredients( getJsonList(sandwichData.optJSONArray(INGREDIENTS)));

         } catch (JSONException e) {
            e.printStackTrace();
        }

        return sandwich;
    }

    private static List<String> getJsonList(JSONArray jsonList) throws JSONException {
         List<String> list = new ArrayList<>();
        for (int i = 0 ; i < jsonList.length(); i++){
            list.add(jsonList.get(i).toString());
        }
        return list;
    }
}
