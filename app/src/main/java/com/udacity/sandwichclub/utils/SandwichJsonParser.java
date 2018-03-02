package com.udacity.sandwichclub.utils;

import com.orhanobut.logger.Logger;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static com.udacity.sandwichclub.utils.ParseJsonUtil.parseList;
import static com.udacity.sandwichclub.utils.ParseJsonUtil.parseSingleEntry;

public class SandwichJsonParser {

    private static final String DESCRIPTION = "description";
    private static final String NAME_ROOT = "name";
    private static final String MAIN_NAME = "mainName";
    private static final String ALSO_KNOWN_AS_TABLE = "alsoKnownAs";
    private static final String PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String INGREDIENTS = "ingredients";
    private static final String IMAGE = "image";


    public static Sandwich parseSandwichJson(String sourceJson) {
        try {
            JSONObject json = new JSONObject(sourceJson);

            JSONObject name = json.getJSONObject(NAME_ROOT);
            String mainName = name.getString(MAIN_NAME);
            List<String> alsoKnownAs = parseList(name, ALSO_KNOWN_AS_TABLE);

            String placeOfOrigin = parseSingleEntry(json, PLACE_OF_ORIGIN);

            String description = parseSingleEntry(json, DESCRIPTION);

            String imgLink = parseSingleEntry(json, IMAGE);

            List<String> ingredients = parseList(json, INGREDIENTS);

            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, imgLink, ingredients);
        } catch (JSONException e) {
            Logger.e("Exception occurred during parsing Sandwich " , e);
        }
        return null;
    }
}
