package com.wwt.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

/**
 * Created by Administrator on 2016/8/2 0002.
 */
public class LockScreenActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        setContentView(R.layout.activity_lockscreen);
    }
}
