package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    public static final String TAG = "DetailActivity";


    ImageView sandwichIv;
    TextView placeOfOriginTv;
    TextView ingredientsTv;
    TextView alsoKnownAsTv;
    TextView descriptionTv;

    Sandwich sandwich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        sandwichIv = findViewById(R.id.detail_image);
        placeOfOriginTv = findViewById(R.id.detail_origin_value);
        ingredientsTv = findViewById(R.id.details_ingredients_value);
        alsoKnownAsTv = findViewById(R.id.detail_also_known_as_value);
        descriptionTv = findViewById(R.id.detail_description_value);

        Intent intent = getIntent();
        if (intent == null) {
            Log.d(TAG, "Intent is null!");
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            Log.d(TAG, "No position found!");
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            Log.d(TAG, "Sandwich is null!");
            // Sandwich data unavailable
            closeOnError();
            return;
        } else {
            Log.d(TAG, "Sandwich is NOT null!");
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(sandwichIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        String placeOforigin = sandwich.getPlaceOfOrigin();
        Log.d(TAG, "" + placeOforigin.length() + " " + (placeOforigin != null));
        if (placeOforigin.length() > 0){
            placeOfOriginTv.setText(placeOforigin);
        } else {
            placeOfOriginTv.setText("Unknown");
        }

        ArrayList<String> ingredients = (ArrayList) sandwich.getIngredients();
        Log.d(TAG, "" + ingredients.size() + " " + (ingredients != null));
        if (ingredients.size() > 0){
            for (int i = 0; i < ingredients.size(); i++){
                ingredientsTv.append(ingredients.get(i) + "\n");
            }
        } else {
            ingredientsTv.setText("/");
        }

        ArrayList<String> alsoKnownAs = (ArrayList) sandwich.getAlsoKnownAs();
        Log.d(TAG, "" + alsoKnownAs.size() + " " + (alsoKnownAs != null));
        if (alsoKnownAs.size() > 0){
            for (int i = 0; i < alsoKnownAs.size(); i++){
                alsoKnownAsTv.append(alsoKnownAs.get(i) + "\n");
            }
        } else {
            alsoKnownAsTv.setText("/");
        }

        String description = sandwich.getDescription();
        Log.d(TAG, "" + description.length() + " " + (description != null));
        if (description.length() > 0){
            descriptionTv.setText(description);
        } else {
            descriptionTv.setText("/");
        }
    }
}
