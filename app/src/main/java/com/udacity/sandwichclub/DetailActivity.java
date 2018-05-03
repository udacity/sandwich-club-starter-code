/*
 * Copyright (C) 2018 The Android Open Source Project
 */
package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

import java.util.List;

/**
 * Displays details about each Sandwich
 */
public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    /* Member Variables to hold references to TextViews in Detail Activity Layout */
    private TextView mAlsoKnownAs;
    private TextView mIngredients;
    private TextView mPlaceOfOrigin;
    private TextView mDescription;

    /* Member Variables to hold a reference to current Sandwich */
    private Sandwich mSandwich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mAlsoKnownAs = findViewById(R.id.also_known_tv);
        mIngredients = findViewById(R.id.ingredients_tv);
        mPlaceOfOrigin = findViewById(R.id.origin_tv);
        mDescription = findViewById(R.id.description_tv);

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
        mSandwich = null;
        try {
            mSandwich = JsonUtils.parseSandwichJson(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (mSandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(mSandwich.getImage())
                .into(ingredientsIv);

        setTitle(mSandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Populates the UI's View with the correct sandwich's details
     */
    private void populateUI() {
        mAlsoKnownAs.setText(joinStringsInList(mSandwich.getAlsoKnownAs()));
        mIngredients.setText(joinStringsInList(mSandwich.getIngredients()));
        mPlaceOfOrigin.setText(mSandwich.getPlaceOfOrigin());
        mDescription.setText(mSandwich.getDescription());
    }

    /**
     * Helper Method to join Strings in a List with ","
     */
    private String joinStringsInList(List<String> list){
        return TextUtils.join(", ", list);
    }

}
