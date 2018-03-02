package com.udacity.sandwichclub.main;

import com.udacity.sandwichclub.architecture.MVPPresenter;
import com.udacity.sandwichclub.architecture.ViewNotAttachedException;


public class MainPresenter extends MVPPresenter<MainView> {
    private ResourceProvider resourceProvider;

    public MainPresenter(ResourceProvider resourceProvider) {
        this.resourceProvider = resourceProvider;
    }

    @Override
    public void attachView(MainView view) {
        super.attachView(view);
        view.bindSandwiches(resourceProvider.readSandwichesFromResources());
    }

    public void sandwichSelected(String sandwichName) {
        verifyViewAttached();
        view.showSandwichDetails(sandwichName);

    }

    private void verifyViewAttached() {
        if (view == null) throw new ViewNotAttachedException();
    }
}
