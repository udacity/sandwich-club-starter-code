package com.udacity.sandwichclub.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sandwich {

    private String mainName;
    private List<String> alsoKnownAs = null;
    private String placeOfOrigin;
    private String description;
    private String image;
    private List<String> ingredients = null;

    /**
     * No args constructor for use in serialization
     */
    public Sandwich() {
    }

    public Sandwich(String mainName, List<String> alsoKnownAs, String placeOfOrigin, String description, String image, List<String> ingredients) {

        List<String> noDataArray = new ArrayList<>();
        noDataArray.add(0,"No Data found");

        String noDataString = "No Data found";

        this.mainName = mainName;

        //During construction of Sandwich, testing for empty values and if so, setting value to "No Data Found"

        if (alsoKnownAs.isEmpty()) {
            this.alsoKnownAs = noDataArray;
        } else {
            this.alsoKnownAs = alsoKnownAs;
        }

        if (placeOfOrigin.isEmpty()) {
            this.placeOfOrigin = noDataString;
        } else {
            this.placeOfOrigin = placeOfOrigin;
        }

        if (description.isEmpty()) {
            this.description = noDataString;
        } else {
            this.description = description;
        }

        this.image = image;

        if (ingredients.isEmpty()) {
            this.ingredients = noDataArray;
        } else {
            this.ingredients = ingredients;
        }
    }

    public String getMainName() {
        return mainName;
    }

    public void setMainName(String mainName) {
        this.mainName = mainName;
    }

    public List<String> getAlsoKnownAs() {
        return alsoKnownAs;
    }

    public void setAlsoKnownAs(List<String> alsoKnownAs) {
        this.alsoKnownAs = alsoKnownAs;
    }

    public String getPlaceOfOrigin() {
        return placeOfOrigin;
    }

    public void setPlaceOfOrigin(String placeOfOrigin) {
        this.placeOfOrigin = placeOfOrigin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getIngredients() {

        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    //Takes a String list array and returns a String

    public String parseStringList(List<String> stringList){
        StringBuilder stringBuilder = new StringBuilder();

        if(stringList != null) {

            for (String string : stringList) {
                stringBuilder.append(string);
                stringBuilder.append(" ");
            }

        return stringBuilder.toString();
        } else {
            return null;
        }
    }
}
