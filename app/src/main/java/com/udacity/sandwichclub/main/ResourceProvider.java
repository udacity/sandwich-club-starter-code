package com.udacity.sandwichclub.main;


import android.content.res.Resources;

import com.udacity.sandwichclub.R;

public class ResourceProvider {
    private Resources resources;

    public ResourceProvider(Resources resources) {
        this.resources = resources;
    }

    String[] readSandwichesFromResources() {
        return resources.getStringArray(R.array.sandwich_names);
    }

}
