package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        String sSandwich = sandwich.getDescription();
        TextView descriptionTv = findViewById(R.id.description_tv);
        descriptionTv.setText(sSandwich.length() > 0 ? sSandwich : "No Description");

        String sPlaceOfOrgin = sandwich.getPlaceOfOrigin();
        TextView placeOfOriginTv = findViewById(R.id.origin_tv);
        placeOfOriginTv.setText(sPlaceOfOrgin.length() > 0 ? sPlaceOfOrgin : "No Place of Origin");

        String sAlsoKnownAs = sandwich.getAlsoKnownAs().toString()
                .replace('[', ' ').replace(']', ' ').trim();
        TextView alsoKnownAsTv = findViewById(R.id.also_known_tv);
        alsoKnownAsTv.setText(sAlsoKnownAs.length() > 0 ? sAlsoKnownAs : "No Also Known As");

        String sIngredients = sandwich.getIngredients().toString()
                .replace('[', ' ').replace(']', ' ').trim();
        TextView ingredientsTv = findViewById(R.id.ingredients_tv);
        ingredientsTv.setText(sIngredients.length() > 0 ? sIngredients : "No Ingredients");
    }
}
