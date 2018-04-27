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

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    //create references for views

    TextView origin_tv;
    TextView also_known_tv;
    TextView description_tv;
    TextView ingredients_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Store views in references

        ImageView image_iv = findViewById(R.id.image_iv);
        origin_tv = findViewById(R.id.origin_tv);
        also_known_tv = findViewById(R.id.also_known_tv);
        description_tv = findViewById(R.id.description_tv);
        ingredients_tv = findViewById(R.id.ingredients_tv);

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

        //Populate views with sandwich data

        populateUI(sandwich);

        //Populate image view

        Picasso.with(this)
                .load(sandwich.getImage())
                .into(image_iv);

        Picasso.with(this)
                .load(sandwich.getImage())
                .error(R.drawable.ic_broken_image_black_24dp)
                .into(image_iv);



        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        origin_tv.setText(sandwich.getPlaceOfOrigin());
        also_known_tv.setText(sandwich.parseStringList(sandwich.getAlsoKnownAs()));
        description_tv.setText(sandwich.getDescription());
        ingredients_tv.setText(sandwich.parseStringList(sandwich.getIngredients()));
    }
}
