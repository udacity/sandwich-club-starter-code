package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    // Json key names.
    private static final String NAME = "name";
    private static final String MAIN_NAME = "mainName";
    private static final String ALSO_KNOWN_AS = "alsoKnownAs";
    private static final String PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "image";
    private static final String INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {

        try {
            JSONObject jsonObject = new JSONObject(json);

            JSONObject nameObject = jsonObject.optJSONObject(NAME);
            String mainName = nameObject.optString(MAIN_NAME);
            JSONArray alsoKnowAsArray = nameObject.optJSONArray(ALSO_KNOWN_AS);

            String placeOfOrigin = jsonObject.optString(PLACE_OF_ORIGIN);
            String description = jsonObject.optString(DESCRIPTION);
            String image = jsonObject.optString(IMAGE);
            JSONArray ingredientsArray = jsonObject.optJSONArray(INGREDIENTS);

            Sandwich sandwich = new Sandwich(mainName, convertArrayToList(alsoKnowAsArray), placeOfOrigin, description, image, convertArrayToList(ingredientsArray));
            return sandwich;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<String> convertArrayToList(JSONArray jsonArray) {

        if (jsonArray != null) {

            List<String> list = new ArrayList();

            for (int i = 0; i < jsonArray.length(); i++) {
                String value = jsonArray.optString(i);
                if (!value.isEmpty()) {
                    list.add(value);
                }
            }
            return list;
        }
        return null;
    }
}
