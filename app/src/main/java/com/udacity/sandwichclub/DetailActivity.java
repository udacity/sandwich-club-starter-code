package com.udacity.sandwichclub;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.databinding.ActivityDetailBinding;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    ActivityDetailBinding binding ;
    private static final String NOT_KNOWN = "INFORMATION NOT STILL KNOWN";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
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
                .into(binding.imageIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        if (sandwich.getAlsoKnownAs().size() > 0) {
            StringBuilder makeKnowAsList = new StringBuilder();
            for (String item : sandwich.getAlsoKnownAs())
                makeKnowAsList.append(item).append(" - ");
            makeKnowAsList.deleteCharAt(makeKnowAsList.length() - 1);
            makeKnowAsList.deleteCharAt(makeKnowAsList.length() - 1);

            binding.alsoKnownTv.setText(makeKnowAsList.toString());
        }
        else {
            binding.alsoKnownTv.setTypeface(null, Typeface.BOLD);
            binding.alsoKnownTv.setText(NOT_KNOWN);
        }

        if (!sandwich.getDescription().isEmpty()) {
            binding.descriptionTv.setText(sandwich.getDescription());
        }
         else {
            binding.descriptionTv.setTypeface(null, Typeface.BOLD);
            binding.descriptionTv.setText(NOT_KNOWN);
        }

        if (sandwich.getIngredients().size() > 0) {
            StringBuilder makeIngredients = new StringBuilder();
            for (String item : sandwich.getIngredients())
                makeIngredients.append(item).append(" - ");
            makeIngredients.deleteCharAt(makeIngredients.length() - 1);
            makeIngredients.deleteCharAt(makeIngredients.length() - 1);

            binding.ingredientsTv.setText(makeIngredients.toString());
        }else{
            binding.ingredientsTv.setTypeface(null, Typeface.BOLD);
            binding.ingredientsTv.setText(NOT_KNOWN);
        }

        if (!sandwich.getPlaceOfOrigin().isEmpty()) {
            binding.originTv.setText(sandwich.getPlaceOfOrigin());
        }
        else {
            binding.originTv.setTypeface(null, Typeface.BOLD);
            binding.originTv.setText(NOT_KNOWN);
        }
    }
}
