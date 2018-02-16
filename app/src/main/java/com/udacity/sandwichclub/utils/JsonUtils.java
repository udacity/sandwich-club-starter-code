package com.udacity.sandwichclub.utils;


import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static final String NAME = "name";
    private static final String IMAGE = "image";
    private static final String MAIN_NAME = "mainName";
    private static final String DESCRIPTION = "description";
    private static final String INGREDIENTS = "ingredients";
    private static final String ALSO_KNOWN_AS = "alsoKnownAs";
    private static final String PLACE_OF_ORIGIN = "placeOfOrigin";

    public static Sandwich parseSandwichJson(String json) {
        assert json != null;

        String mainName, placeOfOrigin, description, imageUrl;
        List<String> alsoKnownAsList, ingredientsList;
        Sandwich sandwich = null;

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject nameJsonObject = jsonObject.getJSONObject(NAME);

            imageUrl = jsonObject.getString(IMAGE);
            mainName = nameJsonObject.getString(MAIN_NAME);
            description = jsonObject.getString(DESCRIPTION);
            placeOfOrigin = jsonObject.getString(PLACE_OF_ORIGIN);

            alsoKnownAsList = jsonArrayFilter(nameJsonObject.getJSONArray(ALSO_KNOWN_AS));
            ingredientsList = jsonArrayFilter(jsonObject.getJSONArray(INGREDIENTS));

            sandwich = new Sandwich(
                    mainName,
                    alsoKnownAsList,
                    placeOfOrigin,
                    description,
                    imageUrl,
                    ingredientsList
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        assert sandwich != null;

        return sandwich;
    }

    private static List<String> jsonArrayFilter(JSONArray jsonArray) throws JSONException {
        List<String> stringList = new ArrayList<>();
        for (int index = 0;index < jsonArray.length();index++){
            stringList.add(jsonArray.get(index).toString());
        }

        return stringList;
    }
}
