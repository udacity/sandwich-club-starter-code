package com.udacity.sandwichclub.utils;

import java.util.List;

/**
 * Created by Mohamed Nagy on 2/15/2018.
 */

public class Utility {
    private static final String SEPARATOR  = " - ";

    public static String filterListAsString(List<String> list){
        StringBuilder stringBuilder = new StringBuilder("");
        if(!list.isEmpty()) {
            for (int i = 0; i < list.size() - 1; i++) {
                stringBuilder.append(list.get(i)).append(SEPARATOR);
            }
            stringBuilder.append(list.get(list.size() - 1));

            return stringBuilder.toString();
        }else{
            return "";
        }
    }
}
