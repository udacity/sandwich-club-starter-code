package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private Sandwich mSandwich = null;
    private ImageView mSandwichImage;
    private TextView mSandwichOriginTextView;
    private TextView mSandwichAkaTextView;
    private TextView mSandwichDescriptionTextView;
    private TextView mSandwichIngredientsTextView;

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
        mSandwich = JsonUtils.parseSandwichJson(json);
        if (mSandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(mSandwich.getImage())
                .into(ingredientsIv, new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError() {
                        mSandwichImage.setVisibility(View.GONE);
                    }
                });


        setTitle(mSandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        //COMPLETED (2) Fill in the method to populate the UI in detail screen
        initializeComponents();

        mSandwichOriginTextView.setText(mSandwich.getPlaceOfOrigin());

        for (String alias : mSandwich.getAlsoKnownAs()) {
            mSandwichAkaTextView.append(alias + "\n");
        }

        mSandwichDescriptionTextView.setText(mSandwich.getDescription());

        for (String ingredient : mSandwich.getIngredients()) {
            mSandwichIngredientsTextView.append(ingredient + "\n");
        }
    }

    private void initializeComponents() {
        mSandwichOriginTextView = (TextView) findViewById(R.id.origin_tv);
        mSandwichAkaTextView = (TextView) findViewById(R.id.also_known_tv);
        mSandwichDescriptionTextView = (TextView) findViewById(R.id.description_tv);
        mSandwichIngredientsTextView = (TextView) findViewById(R.id.ingredients_tv);
        mSandwichImage = (ImageView) findViewById(R.id.image_iv);
    }
}
