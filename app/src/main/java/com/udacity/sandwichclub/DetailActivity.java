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

public class DetailActivity extends AppCompatActivity {
    private Sandwich mSandwich;
    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private TextView mMainNameTextView;
    private TextView mOriginTextView;
    private TextView mAlsoKnownAsView;
    private TextView mIngredientsTextView;
    private TextView mPlaceOfOriginTextView;
    private TextView mDescriptionTextView;
    //private TextView mIngredientsTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mAlsoKnownAsView=(TextView)findViewById(R.id.also_known_tv) ;
        mIngredientsTextView=(TextView)findViewById(R.id.ingredients_tv) ;
        mPlaceOfOriginTextView=(TextView)findViewById(R.id.origin_tv) ;
        mDescriptionTextView=(TextView)findViewById(R.id.description_tv) ;

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
        mSandwich = sandwich;
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
        mDescriptionTextView.setText(mSandwich.getDescription());

        /*for(String anAlias:mSandwich.getAlsoKnownAs())
            mAlsoKnownAsView.append(anAlias + "\n\n\n");*/

        mPlaceOfOriginTextView.setText(mSandwich.getPlaceOfOrigin());

        /*for(String anIngredient: mSandwich.getIngredients())
            mIngredientsTextView.append(anIngredient+ "\n\n\n");*/
    }
}