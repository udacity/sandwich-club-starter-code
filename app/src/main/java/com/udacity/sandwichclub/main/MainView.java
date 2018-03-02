package com.udacity.sandwichclub.main;

import com.udacity.sandwichclub.architecture.MVPView;


public interface MainView extends MVPView {

    void bindSandwiches(String[] sandwiches);

    void showSandwichDetails(String sandwichName);
}
