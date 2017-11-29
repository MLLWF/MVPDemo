package com.mllwf.mvpdemo.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.mllwf.core.AppAction;
import com.mllwf.mvpdemo.KApplication;

/**
 * Created by ML on 2017/4/19.
 */

public class KBaseActivity extends AppCompatActivity {

    // 上下文实例
    public Context context;
    // 应用全局的实例
    public KApplication application;
    // 核心层的Action实例
    public AppAction appAction;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        context = getApplicationContext();
        application = (KApplication) this.getApplication();
        appAction = application.getAppAction();
    }
}
