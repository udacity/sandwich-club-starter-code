package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private static final String TAG = DetailActivity.class.getName();

    ImageView ingredientsIv;
    TextView originText;
    TextView descriptionText;
    TextView ingredientsText;
    TextView synonymsText;
    TextView nameText;

    TextView staticPOO;
    TextView staticAKA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ingredientsIv = findViewById(R.id.image_iv);
        originText = findViewById(R.id.origin_tv);
        descriptionText = findViewById(R.id.description_tv);
        ingredientsText = findViewById(R.id.ingredients_tv);
        synonymsText = findViewById(R.id.also_known_tv);
        nameText = findViewById(R.id.sandwich_name_tv);

        /* The provided "Also Known As:" TextView and "Place of Origin:" TextView are declared here
        in the event that their corresponding TextViews are empty. If so, each view will be set to
        View.GONE to clean up the UI.
         */
        staticAKA = findViewById(R.id.detail2);
        staticPOO =findViewById(R.id.detail1);

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
        Log.i(TAG, "onCreate: sandwiches JSON: " + json);
        Sandwich sandwich = null;
        try {
            sandwich = JsonUtils.parseSandwichJson(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        Log.i(TAG, "onCreate: what sandwich is (Class): " + sandwich.getMainName());
//        Log.i(TAG, "onCreate: what sandwich is: " + sandwich.toString());
        if (sandwich == null) {
            // Sandwich data unavailable
            Log.e(TAG, "onCreate: sandwich is null.");
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

    /* Assigned the associated text to the appropriate view in the activity, with comma-handling for
    the lists (the ingredient and synonym views).

    Input: - Sandwich sandwich: A populated Sandwich object.
     */
    private void populateUI(Sandwich sandwich) {
        Log.i(TAG, "populateUI: start of populateUI");
        nameText.setText(sandwich.getMainName());
        Log.i(TAG, "populateUI: Sandwich name should be set. :" + sandwich.getMainName());
        for (int i = 0; i < sandwich.getAlsoKnownAs().size() - 1; i++) {
            synonymsText.setText(synonymsText.getText() + sandwich.getAlsoKnownAs().get(i) + ", ");
        }
        if (synonymsText.getText().toString().isEmpty()) {
            synonymsText.setVisibility(View.GONE);
            staticAKA.setVisibility(View.GONE);
        } else {
            synonymsText.setText(synonymsText.getText() + sandwich.getAlsoKnownAs().get(
                    sandwich.getAlsoKnownAs().size() - 1));
        }
//        synonymsText.setText(sandwich.getAlsoKnownAs());
        for (int i = 0; i < sandwich.getIngredients().size() - 1; i++) {
            ingredientsText.setText(ingredientsText.getText() + sandwich.getIngredients().get(i) + ", ");
        }
        ingredientsText.setText(ingredientsText.getText() + sandwich.getIngredients().get(
                sandwich.getIngredients().size() - 1));
//        ingredientsText.setText(sandwich.getIngredients());
        descriptionText.setText(sandwich.getDescription());
        originText.setText(sandwich.getPlaceOfOrigin());
        if (originText.getText().toString().isEmpty()) {
            originText.setVisibility(View.GONE);
            staticPOO.setVisibility(View.GONE);
        }
    }

}
