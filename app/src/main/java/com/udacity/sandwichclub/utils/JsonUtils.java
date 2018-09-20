package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private final static String TAG = JsonUtils.class.getSimpleName();
    private final static String NAME_CODE = "name";
    private final static String MAIN_NAME_CODE = "mainName";
    private final static String ALSO_KNOWN_AS_CODE = "alsoKnownAs";
    private final static String PLACE_OF_ORIGIN_CODE = "placeOfOrigin";
    private final static String DESCRIPTION_CODE = "description";
    private final static String IMAGE_CODE = "image";
    private final static String INGREDIENTS_CODE = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject mainJsonObject = new JSONObject(json);
            JSONObject name = mainJsonObject.getJSONObject(NAME_CODE);
            String mainName = name.getString(MAIN_NAME_CODE);
            JSONArray JSONArrayAlsoKnownAs = name.getJSONArray(ALSO_KNOWN_AS_CODE);
            List<String> alsoKnownAs = new ArrayList<>(JSONArrayAlsoKnownAs.length());

            for (int i = 0; i < JSONArrayAlsoKnownAs.length(); i++) {
                alsoKnownAs.add(JSONArrayAlsoKnownAs.getString(i));
            }
            String placeOfOrigin = mainJsonObject.getString(PLACE_OF_ORIGIN_CODE);
            String description = mainJsonObject.getString(DESCRIPTION_CODE);
            String image = mainJsonObject.getString(IMAGE_CODE);
            JSONArray JSONArrayIngredients = mainJsonObject.getJSONArray(INGREDIENTS_CODE);
            List<String> ingredients = new ArrayList<>(JSONArrayIngredients.length());

            for (int i = 0; i < JSONArrayIngredients.length(); i++) {
                ingredients.add(JSONArrayIngredients.getString(i));
            }
            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
