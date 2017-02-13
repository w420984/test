package com.wwt.test.modle;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.wwt.test.R;

/**
 * Created by Administrator on 2016/8/9 0009.
 */
public class SliderSwitcher extends FrameLayout{
    public interface OnChangedListener {
        public void OnChanged(SliderSwitcher sliderSwitcher, boolean checkState);
    }

    private Drawable mOffDrable;
    private Drawable mOnDrable;
    private Drawable mButtonDrable;
    private boolean mStatus;

    private ImageView mBgImage;
    private ImageView mBtnImageLeft;
    private ImageView mBtnImageRight;

    private OnChangedListener mListener;

    public SliderSwitcher(Context context) {
        this(context, null, 0);
    }

    public SliderSwitcher(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SliderSwitcher(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SliderSwitcher, defStyleAttr, 0);
        mStatus = array.getBoolean(R.styleable.SliderSwitcher_status, true);
        mOffDrable = array.getDrawable(R.styleable.SliderSwitcher_bgOff);
        mOnDrable = array.getDrawable(R.styleable.SliderSwitcher_bgOn);
        mButtonDrable = array.getDrawable(R.styleable.SliderSwitcher_button);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SliderSwitcher(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this(context, attrs, defStyleAttr);
    }

    private void init(){
        mBgImage = new ImageView(getContext());
        mBtnImageLeft = new ImageView(getContext());
        mBtnImageRight = new ImageView(getContext());
        addView(mBgImage);
        addView(mBtnImageLeft);
        addView(mBtnImageRight);
        mBtnImageLeft.setImageDrawable(mButtonDrable);
        mBtnImageRight.setImageDrawable(mButtonDrable);
        FrameLayout.LayoutParams lpLeft = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lpLeft.gravity = Gravity.LEFT;
        mBtnImageLeft.setLayoutParams(lpLeft);
        FrameLayout.LayoutParams lpRight = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lpRight.gravity = Gravity.RIGHT;
        mBtnImageRight.setLayoutParams(lpRight);
        update();

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                float deltaX = mOffDrable.getIntrinsicWidth() - mButtonDrable.getIntrinsicWidth();
                if (mStatus) {
                    mBtnImageRight.setVisibility(GONE);
                    startAnimation(mBtnImageRight, 0, -deltaX);
                } else {
                    mBtnImageLeft.setVisibility(GONE);
                    startAnimation(mBtnImageLeft, 0, deltaX);
                }
                mStatus = !mStatus;
                if (mListener != null){
                    mListener.OnChanged(SliderSwitcher.this, mStatus);
                }
            }
        });
    }

    private void update(){
        if (mStatus){
            mBtnImageLeft.setVisibility(GONE);
            mBtnImageRight.setVisibility(VISIBLE);
            mBgImage.setImageDrawable(mOnDrable);
        }else {
            mBtnImageLeft.setVisibility(VISIBLE);
            mBtnImageRight.setVisibility(GONE);
            mBgImage.setImageDrawable(mOffDrable);
        }

    }

    private void startAnimation(View view, float fromX, float toX){
        TranslateAnimation animation = new TranslateAnimation(fromX, toX, 0, 0);
        animation.setDuration(200);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                update();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(animation);
    }

    public void setChangeListener(OnChangedListener listener){
        mListener = listener;
    }
}
