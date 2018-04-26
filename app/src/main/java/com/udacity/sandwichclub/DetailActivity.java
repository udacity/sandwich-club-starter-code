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

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    
    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    ImageView ingredientsIv;
    TextView alsoKnownTv;
    TextView ingredientsTv;
    TextView descriptionTv;
    TextView originTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ingredientsIv = findViewById(R.id.image_iv);
         alsoKnownTv= findViewById(R.id.also_known_tv);
         ingredientsTv= findViewById(R.id.ingredients_tv);
         descriptionTv= findViewById(R.id.description_tv);
         originTv= findViewById(R.id.origin_tv);
        
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
        alsoKnownTv.setText(listToString(sandwich.getAlsoKnownAs()));
        ingredientsTv.setText(listToString(sandwich.getIngredients()));
        descriptionTv.setText(sandwich.getDescription());
        originTv.setText(sandwich.getPlaceOfOrigin());
    }

    private static String listToString(List<String> strings){
        StringBuilder formattedString = new StringBuilder();

         if(strings !=null && !strings.isEmpty()){
             for (String string:strings) {
                 formattedString.append(string).append("\n");
             }
         }

        return formattedString.toString();
    }
}
