package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String NAME = "name";
    private static final String MAIN_NAME = "mainName";
    private static final String PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "image";
    private static final String INGREDIENTS = "ingredients";
    private static final String ALSO_KNOWN_AS = "alsoKnownAs";

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject sandwichJson = new JSONObject(json);

            JSONObject nameObject = sandwichJson.getJSONObject(NAME);
            String mainName = nameObject.getString(MAIN_NAME);
            JSONArray alsoKnownAsArray = nameObject.getJSONArray(ALSO_KNOWN_AS);
            List<String> alsoKnownAs = parseJsonArrayAsStringList(alsoKnownAsArray);

            String placeOfOrigin = sandwichJson.getString(PLACE_OF_ORIGIN);
            String description = sandwichJson.getString(DESCRIPTION);
            String image = sandwichJson.getString(IMAGE);

            JSONArray ingredientsJson = sandwichJson.getJSONArray(INGREDIENTS);
            List<String> ingredients = parseJsonArrayAsStringList(ingredientsJson);

            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<String> parseJsonArrayAsStringList(JSONArray jsonArray) throws JSONException {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            stringList.add(jsonArray.getString(i));
        }
        return stringList;
    }
}
