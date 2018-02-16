package com.udacity.sandwichclub.screens;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.udacity.sandwichclub.R;

/**
 * Created by Mohamed Nagy on 2/15/2018.
 */

public class ViewHolderScreen {
    public static class DetailActivityViewHolder{
        public final ImageView SANDWICH_IMAGE_VIEW;
        public final TextView SANDWICH_NAME_TEXT_VIEW;
        public final TextView SANDWICH_ALSO_KNOWN_AS_TEXT_VIEW;
        public final TextView SANDWICH_DESCRIPTION_TEXT_VIEW;
        public final TextView SANDWICH_PLACE_OF_ORIGIN_TEXT_VIEW;
        public final TextView SANDWICH_INGREDIENTS_TEXT_VIEW;

        public DetailActivityViewHolder(View detailActivityView){
            SANDWICH_IMAGE_VIEW = detailActivityView.findViewById(R.id.sandwich_image);
            SANDWICH_NAME_TEXT_VIEW = detailActivityView.findViewById(R.id.sandwich_main_name);
            SANDWICH_ALSO_KNOWN_AS_TEXT_VIEW = detailActivityView.findViewById(R.id.sandwich_also_known);
            SANDWICH_DESCRIPTION_TEXT_VIEW = detailActivityView.findViewById(R.id.sandwich_desiption);
            SANDWICH_PLACE_OF_ORIGIN_TEXT_VIEW = detailActivityView.findViewById(R.id.sandwich_place_of_origin);
            SANDWICH_INGREDIENTS_TEXT_VIEW = detailActivityView.findViewById(R.id.sandwich_ingredients);

        }
    }
}
