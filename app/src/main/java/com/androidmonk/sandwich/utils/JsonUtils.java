package com.androidmonk.sandwich.utils;

import com.androidmonk.sandwich.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json){
        if (json == null || json.isEmpty()){
            return null;
        }

        try {
            JSONObject sandwichJson = new JSONObject(json);
            JSONArray ingredientsJson = sandwichJson.getJSONArray("ingredients");
            JSONArray alsoKnownAsJson = sandwichJson.getJSONObject("name").getJSONArray("alsoKnownAs");

            String name = sandwichJson.getJSONObject("name").getString("mainName");
            String image = sandwichJson.getString("image");

            String description = sandwichJson.getString("description");
            String placeOfOrigin = sandwichJson.getString("placeOfOrigin");

            List<String> ingredients = new ArrayList<>();
            for (int i=0; i<ingredientsJson.length(); i++){
                ingredients.add(ingredientsJson.getString(i));
            }

            List<String> alsoKnownAs = new ArrayList<>();
            for (int i=0; i<alsoKnownAsJson.length(); i++){
                alsoKnownAs.add(alsoKnownAsJson.getString(i));
            }

            return new Sandwich(name, alsoKnownAs, placeOfOrigin, description, image, ingredients);
        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }

    }
}
