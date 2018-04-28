package com.udacity.sandwichclub;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.databinding.ActivityDetailBinding;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_detail);

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

        if(sandwich !=null) {
            binding.alsoKnownTv.setText(convertListToString(sandwich.getAlsoKnownAs()));
            binding.originTv.setText(sandwich.getPlaceOfOrigin().isEmpty() ? getString(R.string.detail_not_availbale) : sandwich.getPlaceOfOrigin());
            binding.descriptionTv.setText(sandwich.getDescription().isEmpty() ? getString(R.string.detail_not_availbale) : sandwich.getDescription());
            binding.ingredientsTv.setText(convertListToString(sandwich.getIngredients()));

            Picasso.with(this)
                    .load(sandwich.getImage())
                    .placeholder(R.drawable.placeholder)
                    .into(binding.imageIv);

        }
    }

    private String convertListToString(List<String> list){

        if(list !=null && list.size() > 0){

            int count = 0;
            StringBuilder builder = new StringBuilder("");

            for(String string : list){
                if(count > 0){
                    builder.append(", ");

                }
                builder.append(string);
                count++;
            }
            return builder.toString();
        }
        return getString(R.string.detail_not_availbale);
    }
}
