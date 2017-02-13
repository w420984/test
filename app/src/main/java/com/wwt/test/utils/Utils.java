package com.wwt.test.utils;

import android.content.Context;
import android.graphics.Rect;
import android.view.Display;
import android.view.WindowManager;

public class Utils {
    private static int mScreenW = 0;

    public static void getScreenRect(Context ctx_, Rect outrect_) {
        if (ctx_ == null || outrect_ == null){
            return;
        }
        Display screenSize = ((WindowManager) ctx_
                .getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        outrect_.set(0, 0, screenSize.getWidth(), screenSize.getHeight());
    }

    public static int getScreenWidth(Context context){
        if (mScreenW == 0) {
            Rect rect = new Rect();
            Utils.getScreenRect(context, rect);
            mScreenW = rect.right - rect.left;
        }
        return mScreenW;
    }

}