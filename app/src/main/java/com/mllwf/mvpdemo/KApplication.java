package com.mllwf.mvpdemo;

import android.app.Application;

import com.mllwf.core.AppAction;
import com.mllwf.core.AppActionImpl;

/**
 * Created by ML on 2017/4/19.
 */

public class KApplication extends Application {


    private AppAction appAction;

    @Override
    public void onCreate() {
        super.onCreate();
        appAction = new AppActionImpl(this);
    }

    public AppAction getAppAction() {
        return appAction;
    }
}
