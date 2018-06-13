package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich returnSandwich = new Sandwich();
        Map<String, String[]> map = new HashMap<String, String[]>();
        Queue parents = new LinkedList();
        String[] tempValues = {"-1", "", "P", ""};
        String sParent = "-1", sName = "", sPVL = "", sValue = "";
        json = json.trim();
        while(json.length() > 0){
            char c  = json.charAt(0);
            switch (c)
            {
                case '[':
                    //Found List
                    int iList = json.indexOf(']');
                    sValue = json.substring(1, iList).trim();
                    json = json.substring(iList + 1).trim();
                    tempValues[0] = sParent;
                    tempValues[1] = sName;
                    tempValues[2] = "L";
                    tempValues[3] = sValue;
                    map.put(sName, tempValues.clone());
                    sName = "";
                    sValue = "";
                    break;
                case '{':
                    //Found Object
                    //Get the Name for the key value pair
                    int iColon = json.indexOf(':');
                    if(sName != ""){
                        if(sParent != "-1"){
                            parents.add(sName);
                        }
                        sParent = sName;
                    }
                    sName = json.substring(2, iColon-1).trim();
                    sName = (sName.charAt(0) == '"' ? sName.substring(1, sName.length() -1) : sName);
                    json = json.substring(iColon + 1).trim();
                    break;
                case '}':
                case ':':
                case '\n':
                case '\t':
                    //Get Parent
                    json = json.substring(1).trim();
                    if(parents.size() > 0)
                        sParent = (String)parents.remove();
                    break;
                case ',':
                    int iColon1 = json.indexOf(':');
                    int iBracket1 = json.indexOf('}');
                    if(iColon1 > 0 && iBracket1 >= iColon1){
                        sName = json.substring(1, iColon1).trim();
                        sName = (sName.charAt(0) == '"' ? sName.substring(1, sName.length() -1) : sName);
                        json = json.substring(iColon1 + 1).trim();
                    } else {
                        json = json.substring(1).trim();
                    }
                    break;
                default:
                    //Value found
                    int iQuoteEnd = json.substring(1).indexOf("\",");
                    if(iQuoteEnd == 0) {
                        json = json.substring(iQuoteEnd + 2).trim();
                    } else {
                        sValue = json.substring(1, iQuoteEnd + 1).replaceAll("\\\\\"", "\"");
                        json = json.substring(iQuoteEnd + 2).trim();
                    }

                    int iBracket = json.indexOf('}');
                    int iComma = json.indexOf(',');
                    if(iComma > iBracket) {
                        json = json.substring(iBracket + 1).trim();
                    }

                    tempValues[0] = sParent;
                    tempValues[1] = sName;
                    tempValues[2] = "V";
                    tempValues[3] = sValue;
                    map.put(sName, tempValues.clone());
                    sName = "";
                    sValue = "";
                    break;
            }
        }

        if(map.size() > 0){
            returnSandwich.setMainName(map.get("mainName")[3]);
            returnSandwich.setDescription(map.get("description")[3]);
            returnSandwich.setImage(map.get("image")[3]);
            returnSandwich.setPlaceOfOrigin(map.get("placeOfOrigin")[3]);
            //Remove Extra Characters before adding to Sandwich
            returnSandwich.setIngredients(Arrays.asList(map.get("ingredients")[3]
                    .replace('"',' ').split(" , ")));
            returnSandwich.setAlsoKnownAs(Arrays.asList(map.get("alsoKnownAs")[3]
                    .replace('"',' ').split(" , ")));

        }


        return returnSandwich;
    }


}
