package com.udacity.sandwichclub.utils;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

class ParseJsonUtil {

    static String parseSingleEntry(JSONObject json, String key) throws JSONException {
        return json.has(key) ? json.getString(key) : "";
    }

    static List<String> parseList(JSONObject json, String key) throws JSONException {
        List<String> result = new ArrayList<>();
        if (json.has(key)) {
            JSONArray jsonArray = json.getJSONArray(key);
            if (jsonArray.length() > 0) {
                for (int index = 0; index < jsonArray.length(); index++) {
                    String entry = jsonArray.getString(index);
                    result.add(entry);
                }
            }
        }

        return result;
    }

}
