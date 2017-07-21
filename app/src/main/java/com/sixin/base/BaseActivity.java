package com.sixin.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sixin.manager.ActivityManager;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.instance().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.instance().removeActivity(this);
    }
}
