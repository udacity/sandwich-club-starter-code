package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich  = new Sandwich();

        try {
            JSONObject sandwichData =  new JSONObject(json);
            JSONObject sandwichNameData = sandwichData.getJSONObject("name");
            sandwich.setMainName( sandwichNameData.getString("mainName") );
            sandwich.setAlsoKnownAs( getJsonList(sandwichNameData.getJSONArray("alsoKnownAs")));

            sandwich.setPlaceOfOrigin( sandwichData.getString("placeOfOrigin") );
            sandwich.setDescription( sandwichData.getString("description") );
            sandwich.setImage( sandwichData.getString("image") );
            sandwich.setIngredients( getJsonList(sandwichData.getJSONArray("ingredients")));

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
