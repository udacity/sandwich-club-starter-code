package com.udacity.sandwichclub.utils;

import android.support.v4.content.res.TypedArrayUtils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = new Sandwich();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject name = jsonObject.optJSONObject("name");
            sandwich.setDescription(jsonObject.optString("description"));
            sandwich.setImage(jsonObject.optString("image"));
            sandwich.setMainName(name.optString("mainName"));
            sandwich.setPlaceOfOrigin(jsonObject.optString("placeOfOrigin"));


            sandwich.setAlsoKnownAs(getListFromJsonArray(name.optJSONArray("alsoKnownAs")));
            sandwich.setIngredients(getListFromJsonArray(jsonObject.optJSONArray("ingredients")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sandwich;
    }


    public static List<String> getListFromJsonArray(JSONArray jsonArray1){
        List<String> list = new ArrayList<String>();
        try {
        for (int i=0, l=jsonArray1.length(); i<l; i++){

            String value = jsonArray1.getString(i);

            list.add(value);
        }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}

