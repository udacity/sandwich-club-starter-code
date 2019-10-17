package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private TextView akaTextView;
    private TextView placeOfOriginTextView;
    private TextView descriptionTextView;
    private TextView ingredientsTextView;
    private static final String COMMA = ",";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        akaTextView = findViewById(R.id.aka);
        placeOfOriginTextView = findViewById(R.id.placeOfOrigin);
        descriptionTextView = findViewById(R.id.description);
        ingredientsTextView = findViewById(R.id.ingredients);

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

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());

        StringBuffer sb = new StringBuffer();

        if(sandwich.getAlsoKnownAs() != null && !sandwich.getAlsoKnownAs().isEmpty()){
            sb.append(getResources().getString(R.string.detail_also_known_as_label));
            List<String> aka = sandwich.getAlsoKnownAs();
            for(String nickname : aka){
                sb.append(nickname);
                sb.append(COMMA);
            }
            sb.setLength(sb.length()-1);
            akaTextView.setText(sb.toString());
            sb.setLength(0);
        }
        else{
            akaTextView.setVisibility(View.GONE);
        }

        if(sandwich.getPlaceOfOrigin() != null && !sandwich.getPlaceOfOrigin().isEmpty()){
            sb.append(getResources().getString(R.string.detail_place_of_origin_label));
            sb.append(sandwich.getPlaceOfOrigin());

            placeOfOriginTextView.setText(sb.toString());
            sb.setLength(0);
        }
        else{
            placeOfOriginTextView.setVisibility(View.GONE);
        }

        if(sandwich.getDescription() != null && !sandwich.getDescription().isEmpty()){
            sb.append(getResources().getString(R.string.detail_description_label));
            sb.append(sandwich.getDescription());
            descriptionTextView.setText(sb.toString());
            sb.setLength(0);
        }
        else{
            descriptionTextView.setVisibility(View.GONE);
        }

        if(sandwich.getIngredients() != null && !sandwich.getIngredients().isEmpty()){
            sb.append(getResources().getString(R.string.detail_ingredients_label));
            List<String> ingredients = sandwich.getIngredients();
            for(String ingredient : ingredients){
                sb.append(ingredient);
                sb.append(COMMA);
            }
            sb.setLength(sb.length()-1);
            ingredientsTextView.setText(sb.toString());
            sb.setLength(0);
        }
        else{
            ingredientsTextView.setVisibility(View.GONE);
        }

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {

    }
}
