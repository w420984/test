package com.wwt.test;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import com.wwt.test.databinding.ActivityTextBgBinding;

/**
 * Created by Administrator on 2016/11/2 0002.
 */

public class TextBgActivity extends BaseActivity{
    private ActivityTextBgBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_text_bg);


        mBinding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.text.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            mBinding.text.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }else {
                            mBinding.text.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        }
                        int line = mBinding.text.getLineCount();
                        if (line == 1){
                            return;
                        }
                        line--;
                        Layout layout = mBinding.text.getLayout();
                        String text = mBinding.text.getText().toString();
                        Log.e("wwt", "line:"+line+"  start:"+layout.getLineStart(line) + "  end:"+layout.getLineEnd(line));
                        String lastLine = text.substring(layout.getLineStart(line), layout.getLineEnd(line));
                        mBinding.text.setLines(line);
                        mBinding.tvLastLine.setText(lastLine);
                    }
                });
                mBinding.text.setText(getString(R.string.test));
            }
        });
    }
}
