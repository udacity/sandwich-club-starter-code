package com.udacity.sandwichclub.architecture;


import com.orhanobut.logger.Logger;

public class ViewNotAttachedException extends RuntimeException {

    public ViewNotAttachedException() {
        super();
        Logger.e("Attach view first");
    }
}
