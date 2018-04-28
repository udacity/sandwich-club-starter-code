package com.udacity.sandwichclub;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.databinding.ActivityDetailBinding;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private static final String TAG = "tag";

    ActivityDetailBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

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


        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

     private void populateUI(Sandwich sandwich) {

        // Plate_with_fork_and_spoon
         // Author: BSGStudio
         //Licence: Free for commercial with attribution.
         // Please give a backlink to all-free-download.com.
        //http://all-free-download.com/free-vector/download/plate-with-spoon-knife-and-fork_312083.html
         Picasso.with(this)
                 .load(sandwich.getImage())
                 .placeholder(R.drawable.ic_cook)
                 .error(R.drawable.ic_default_sandwich)
                 .into(mBinding.imageIv);
        mBinding.alsoKnownTv.setText(listToString(sandwich.getAlsoKnownAs()));
        mBinding.ingredientsTv.setText(listToString(sandwich.getIngredients()));
        mBinding.descriptionTv.setText(sandwich.getDescription());
        mBinding.originTv.setText(sandwich.getPlaceOfOrigin());
    }

    private static String listToString(List<String> strings){
        String string = "";

        if (strings != null) {
            string = TextUtils.join("\n", strings);
            Log.d(TAG,string);
        }
        return string;
    }
}
