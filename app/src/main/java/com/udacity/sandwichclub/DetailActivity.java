package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private final int DEFAULT_POSITION = -1;

    private Sandwich sandwich;

    private ImageView ingredientsIv;
    private TextView alsoKnownAsTv;
    private TextView originTv;
    private TextView descriptionTv;
    private TextView ingredientsTv;
    private LinearLayout alsoKnownLayout;
    private LinearLayout originLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ingredientsIv = findViewById(R.id.image_iv);
        alsoKnownAsTv = findViewById(R.id.also_known_tv);
        originTv = findViewById(R.id.origin_tv);
        descriptionTv = findViewById(R.id.description_tv);
        ingredientsTv = findViewById(R.id.ingredients_tv);
        alsoKnownLayout = findViewById(R.id.also_known_layout);
        originLayout = findViewById(R.id.origin_layout);


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
        sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        // AlsoKnownAs TextView
        String alsoKnownAsText = "";
        if (sandwich.getAlsoKnownAs().size() > 0){

            for (int i=0;i<sandwich.getAlsoKnownAs().size();i++) {
                alsoKnownAsText += sandwich.getAlsoKnownAs().get(i) + "\n";
            }
            alsoKnownAsTv.setText(alsoKnownAsText.subSequence(0,alsoKnownAsText.length()-1));
        } else {
            alsoKnownLayout.setVisibility(View.GONE);
        }

        // PlaceOfOrigin TextView
        if (sandwich.getPlaceOfOrigin().length() > 0) {
            originTv.setText((CharSequence) sandwich.getPlaceOfOrigin());
        } else {
            originLayout.setVisibility(View.GONE);
        }

        // Description TextView
        descriptionTv.setText((CharSequence) sandwich.getDescription());

        // Ingredients TextView
        String ingredientsText = "";
        if (sandwich.getIngredients().size() > 0){
            for (int i=0;i<sandwich.getIngredients().size();i++) {
                ingredientsText += sandwich.getIngredients().get(i) + "\n";
            }
            ingredientsTv.setText(ingredientsText.subSequence(0,ingredientsText.length()-1));
        } else {
            ingredientsTv.setText(ingredientsText);
        }
    }
}
