package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.screens.ViewHolderScreen;
import com.udacity.sandwichclub.utils.JsonUtils;
import com.udacity.sandwichclub.utils.Utility;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private ViewHolderScreen.DetailActivityViewHolder mDetailActivityViewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mDetailActivityViewHolder = new ViewHolderScreen.DetailActivityViewHolder(findViewById(R.id.frameLayout));

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        assert intent != null;

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
                .into(mDetailActivityViewHolder.SANDWICH_IMAGE_VIEW);

        setTitle(sandwich.getMainName());

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        mDetailActivityViewHolder.SANDWICH_NAME_TEXT_VIEW.setText(sandwich.getMainName());
        mDetailActivityViewHolder.SANDWICH_ALSO_KNOWN_AS_TEXT_VIEW.setText(Utility.filterListAsString(sandwich.getAlsoKnownAs()));
        mDetailActivityViewHolder.SANDWICH_DESCRIPTION_TEXT_VIEW.setText(sandwich.getDescription());
        mDetailActivityViewHolder.SANDWICH_PLACE_OF_ORIGIN_TEXT_VIEW.setText(sandwich.getPlaceOfOrigin());
        mDetailActivityViewHolder.SANDWICH_INGREDIENTS_TEXT_VIEW.setText(Utility.filterListAsString(sandwich.getIngredients()));
    }
}
