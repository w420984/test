package com.wwt.test;

import android.content.Context;
import android.graphics.Paint;

public class DisplayUtil {
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int getFontHeight(Context context, float fontSize)
    {
        Paint paint = new Paint();
        paint.setTextSize(DisplayUtil.sp2px(context, fontSize));
        Paint.FontMetrics fm = paint.getFontMetrics();
        return (int) Math.ceil(fm.descent - fm.ascent);
    }

    public static int getFontSpaceHeight(Context context, float fontSize){
        int height = getFontHeight(context, fontSize);
        int space = (height - sp2px(context, fontSize))/2;
        return space;
    }
}