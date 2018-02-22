package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Testing parseSandwichJson with json Strings from resources
 */
public class JsonUtilsTest {
    @Test
    public void parseSandwichJson() throws Exception {

        String json = "{\"name\":{\"mainName\":\"Ham and cheese sandwich\",\"alsoKnownAs\":[]},\"pla" +
                "ceOfOrigin\":\"\",\"description\":\"A ham and cheese sandwich is a common type of " +
                "sandwich. It is made by putting cheese and sliced ham between two slices of bread. " +
                "The bread is sometimes buttered and/or toasted. Vegetables like lettuce, tomato, " +
                "onion or pickle slices can also be included. Various kinds of mustard and mayonnaise " +
                "are also common.\",\"image\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/" +
                "5/50/Grilled_ham_and_cheese_014.JPG/800px-Grilled_ham_and_cheese_014.JPG\",\"" +
                "ingredients\":[\"Sliced bread\",\"Cheese\",\"Ham\"]}";

        Sandwich sandwich = JsonUtils.parseSandwichJson(json);

        assertEquals(sandwich.getMainName(), "Ham and cheese sandwich");
        assertEquals(sandwich.getAlsoKnownAs(), new ArrayList<String>());
        assertEquals(sandwich.getDescription(), "A ham and cheese sandwich is a common type of " +
                "sandwich. It is made by putting cheese and sliced ham between two slices of bread. " +
                "The bread is sometimes buttered and/or toasted. Vegetables like lettuce, tomato, " +
                "onion or pickle slices can also be included. Various kinds of mustard and mayonnaise " +
                "are also common." );
        assertEquals(sandwich.getImage(),"https://upload.wikimedia.org/wikipedia/commons/thumb/" +
        "5/50/Grilled_ham_and_cheese_014.JPG/800px-Grilled_ham_and_cheese_014.JPG");

        assertEquals(sandwich.getPlaceOfOrigin(), "");
        assertEquals(sandwich.getIngredients(),
                new ArrayList<>(Arrays.asList(
                        "Sliced bread",
                        "Cheese",
                        "Ham"
                        )));
    }

    @Test
    public void parseSandwichJson2() throws Exception {
        String json = "{\"name\":{\"mainName\":\"Bosna\",\"alsoKnownAs\":[\"Bosner\"]},\"place" +
                "OfOrigin\":\"Austria\",\"description\":\"Bosna is a spicy Austrian fast food " +
                "dish, said to have originated in either Salzburg or Linz. It is now popular all " +
                "over western Austria and southern Bavaria.\",\"image\":\"https://upload.wikimedia" +
                ".org/wikipedia/commons/c/ca/Bosna_mit_2_Bratw%C3%BCrsten.jpg\",\"ingredients\":" +
                "[\"White bread\",\"Bratwurst\",\"Onions\",\"Tomato ketchup\",\"Mustard\",\"Curry " +
                "powder\"]}";

        Sandwich sandwich = JsonUtils.parseSandwichJson(json);

        assertEquals(sandwich.getMainName(), "Bosna");
        assertEquals(sandwich.getAlsoKnownAs(), new ArrayList<>(Arrays.asList(
                "Bosner"
        )));
        assertEquals(sandwich.getDescription(), "Bosna is a spicy Austrian fast food " +
                "dish, said to have originated in either Salzburg or Linz. It is now popular all " +
                "over western Austria and southern Bavaria." );

        assertEquals(sandwich.getImage(), "https://upload.wikimedia" +
                ".org/wikipedia/commons/c/ca/Bosna_mit_2_Bratw%C3%BCrsten.jpg");

        assertEquals(sandwich.getPlaceOfOrigin(),"Austria" );

        assertEquals(sandwich.getIngredients(), new ArrayList<>(Arrays.asList(
                "White bread",
                "Bratwurst",
                "Onions",
                "Tomato ketchup",
                "Mustard",
                "Curry powder"
        )));

    }

}