package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static String NAME = "name";
    private static String MAIN_NAME = "mainName";
    private static String ALSO_KNOWN_AS = "alsoKnownAs";
    private static String PLACE_OF_ORIGIN = "placeOfOrigin";
    private static String DESCRIPTION = "description";
    private static String IMAGE = "image";
    private static String INGREDIENTS = "ingredients";


    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = null;


        try {
            JSONObject sandwichJsonObject = new JSONObject(json);
            JSONObject nameObject = sandwichJsonObject.getJSONObject(NAME);
            String mainName = nameObject.getString(MAIN_NAME);
            JSONArray alsoKnowAsJsonArray = nameObject.getJSONArray(ALSO_KNOWN_AS);
            List<String> alsoKnownAs = new ArrayList<>();
            if(alsoKnowAsJsonArray != null){
                for(int i = 0; i < alsoKnowAsJsonArray.length(); i++){
                    alsoKnownAs.add(alsoKnowAsJsonArray.getString(i));
                }
            }
            String placeOfOrigin = sandwichJsonObject.getString(PLACE_OF_ORIGIN);
            String description = sandwichJsonObject.getString(DESCRIPTION);
            String image = sandwichJsonObject.getString(IMAGE);
            JSONArray ingredientsJsonArray = sandwichJsonObject.getJSONArray(INGREDIENTS);

            List<String> ingredients = new ArrayList<>();
            if(ingredientsJsonArray != null){
                for(int i = 0; i < ingredientsJsonArray.length(); i++){
                    ingredients.add(ingredientsJsonArray.getString(i));
                }
            }
            
            sandwich = new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("JsonUtils", e.getMessage());
        }
        
        return sandwich;
    }
}
