package com.udacity.sandwichclub.architecture;

public abstract class MVPPresenter<T extends  MVPView> {
    protected T view;

    public void attachView(T view) {
        this.view = view;
    }

    public void detachView() {
        view = null;
    }

}
