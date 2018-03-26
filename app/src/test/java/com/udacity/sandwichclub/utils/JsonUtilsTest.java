package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by fabian@mikolajczak.org on 2018-03-25.
 */
public class JsonUtilsTest {
    private String jsonString = "{\"name\":{\"mainName\":\"Ham and cheese" +
            "            sandwich\",\"alsoKnownAs\":[]},\"placeOfOrigin\":\"\",\"description\":\"A ham and cheese" +
            "            sandwich is a common type of sandwich. It is made by putting cheese and sliced ham" +
            "            between two slices of bread. The bread is sometimes buttered and/or toasted. Vegetables" +
            "            like lettuce, tomato, onion or pickle slices can also be included. Various kinds of" +
            "            mustard and mayonnaise are also" +
            "            common.\",\"image\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Grilled_ham_and_cheese_014.JPG/800px-Grilled_ham_and_cheese_014.JPG\",\"ingredients\":[\"Sliced" +
            "            bread\",\"Cheese\",\"Ham\"]}";

    @Test
    public void parseSandwichJsonNotNull() throws Exception {
        Sandwich sandwich = JsonUtils.parseSandwichJson(jsonString);
        assertNotNull(sandwich);
    }

    @Test
    public void parseSandwichJsonValuesNotNull() {
        Sandwich sandwich = JsonUtils.parseSandwichJson(jsonString);
        assertNotNull("mainName have to be not null",sandwich.getMainName());
        assertNotNull("alsoKnownAs have to be not null",sandwich.getAlsoKnownAs());
        assertNotNull("placeOfOrigin have to be not null",sandwich.getPlaceOfOrigin());
        assertNotNull("description have to be not null",sandwich.getDescription());
        assertNotNull("image have to be not null",sandwich.getImage());
        assertNotNull("ingredients have to be not null",sandwich.getIngredients());
    }

}