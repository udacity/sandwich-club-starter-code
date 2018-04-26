package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String TAG = JsonUtils.class.getName();

    /* Uses a combination of the builtin JSONObject and a newly built parser to seperate JSON string
    into relevant data for Sandwich class.

    Input: String json - A string of the JSON data to be parsed.

    Output: A Sandwich object with all relevant data assigned.
     */
    public static Sandwich parseSandwichJson(String json) throws JSONException {
        JSONObject dataHold = null;
        Sandwich infoSandwich = null;
        try {
            dataHold = new JSONObject(json);
        } catch (JSONException e) {
            Log.e(TAG, "grabEntry: Problem with Json parsing.");
            e.printStackTrace();
        }

        infoSandwich = null;
        JSONObject dataHold2 = null;
        if (dataHold != null) {
            try {
                dataHold2 = dataHold.getJSONObject("name");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.e(TAG, "parseSandwichJson: Something wrong with dataHold2");
        }
        if (dataHold2 == null) {
            Log.e(TAG, "parseSandwichJson: here");
        }


        infoSandwich = new Sandwich();

        infoSandwich.setMainName(dataHold2.getString("mainName"));
        Log.i(TAG, "parseSandwichJson: alsoKnownAs: " + dataHold2.getString("alsoKnownAs").getClass());
        infoSandwich.setAlsoKnownAs(toList(dataHold2.getString("alsoKnownAs")));
        Log.i(TAG, "parseSandwichJson: alsoKnownAs final result: " + infoSandwich.getAlsoKnownAs());
        infoSandwich.setPlaceOfOrigin(dataHold.getString("placeOfOrigin"));
        infoSandwich.setDescription(dataHold.getString("description"));
        infoSandwich.setImage(dataHold.getString("image"));
        Log.i(TAG, "parseSandwichJson: ingredients: " + dataHold.get("ingredients").getClass().getName());
        infoSandwich.setIngredients(toList(dataHold.getString("ingredients")));
        return infoSandwich;
    }

    /* Parser built to parse Strings of JSONArray Objects.

    Input: String input - A string of the JSONArray data to be parsed.

    Output: A List<String> object of the list items from the original JSONArray.
     */
    private static List<String> toList(String input) {
        ArrayList<String> output = new ArrayList<String>(50);
        Log.i(TAG, "toList: output size" + output.size());
        int counter = 0;
        while (!input.equals("]")) {
//            Log.i(TAG, "toList: start: " + input);
            if (input.charAt(0) != ',' && input.charAt(0) != '"' && input.charAt(0) != '[') {
//                Log.i(TAG, "toList: does input index");
                String hold = input.substring(0, input.indexOf('"'));
//                Log.i(TAG, "toList: does output index");
                output.add(counter++, hold);
//                Log.i(TAG, "toList: does resetting input break");
                input = input.substring(input.indexOf(hold) + hold.length());
            } else {
                input = input.substring(1);
            }
//            Log.i(TAG, "toList: end: " + input);
        }
        return output;
    }

}


