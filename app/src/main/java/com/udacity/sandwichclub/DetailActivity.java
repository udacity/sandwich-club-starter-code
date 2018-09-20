package com.udacity.sandwichclub;

import android.app.Activity;
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

        populateUI(this, sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Activity activity, Sandwich sandwich) {
        TextView alsoKnownTv = activity.findViewById(R.id.also_known_tv);
        TextView alsoKnownLabel = activity.findViewById(R.id.alsoKnownAs_label);
        TextView originTv = activity.findViewById(R.id.origin_tv);
        TextView originLabel = activity.findViewById(R.id.placeOfOrigin_label);
        TextView descriptionTv = activity.findViewById(R.id.description_tv);
        TextView ingredientTv = activity.findViewById(R.id.ingredients_tv);

        if (sandwich.getAlsoKnownAs().isEmpty()) {
            alsoKnownLabel.setVisibility(View.GONE);
            alsoKnownTv.setVisibility(View.GONE);
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(sandwich.getAlsoKnownAs().get(0));

            for (int i = 1; i < sandwich.getAlsoKnownAs().size(); i++) {
                stringBuilder.append(", ");
                stringBuilder.append(sandwich.getAlsoKnownAs().get(i));
            }
            alsoKnownTv.setText(stringBuilder.toString());
        }

        if (sandwich.getPlaceOfOrigin().isEmpty()) {
            originTv.setVisibility(View.GONE);
            originLabel.setVisibility(View.GONE);
        } else {
            originTv.setText(sandwich.getPlaceOfOrigin());
        }

        if (sandwich.getDescription().isEmpty()) {
            descriptionTv.setVisibility(View.GONE);
        } else {
            descriptionTv.setText(sandwich.getDescription());
        }

        if (sandwich.getIngredients().isEmpty()) {
            ingredientTv.setVisibility(View.GONE);
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\u2022");
            stringBuilder.append(sandwich.getIngredients().get(0));
            for (int i = 1; i < sandwich.getIngredients().size(); i++) {
                stringBuilder.append("\n");
                stringBuilder.append("\u2022");
                stringBuilder.append(sandwich.getIngredients().get(i));
            }
            ingredientTv.setText(stringBuilder.toString());
        }
    }
}
