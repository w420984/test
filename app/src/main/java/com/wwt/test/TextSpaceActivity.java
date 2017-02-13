package com.wwt.test;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.BackgroundColorSpan;
import android.util.Log;

import com.wwt.test.databinding.ActivityTextSpaceBinding;

/**
 * Created by Administrator on 2016/10/27 0027.
 */

public class TextSpaceActivity extends BaseActivity{
    ActivityTextSpaceBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startService(new Intent(this, LockScreenService.class));
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_text_space);
        mBinding.setVariable(com.wwt.test.BR.size, 10);
        mBinding.executePendingBindings();
        mBinding.tv3.setLineSpacing(0, (float) 1.0);
        mBinding.tv4.setLineSpacing(0, (float) 1.0);

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                update();
            }
        };

        mBinding.etSize.addTextChangedListener(watcher);
        //mBinding.etMulline.addTextChangedListener(watcher);
        mBinding.etLinespace.addTextChangedListener(watcher);


        BackgroundColorSpan span1 = new BackgroundColorSpan(getResources().getColor(R.color.colorAccent));
        String str1 = getString(R.string.app_name);
        SpannableString spanStr1 = new SpannableString(str1);
        spanStr1.setSpan(span1, 0, str1.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        mBinding.tvSpan1.setText(spanStr1);

        BackgroundColorSpan span2 = new BackgroundColorSpan(getResources().getColor(R.color.colorAccent));
        String str2 = getString(R.string.test);
        SpannableString spanStr2 = new SpannableString(str2);
        spanStr2.setSpan(span2, 0, str2.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        mBinding.tvSpan2.setText(spanStr2);
        mBinding.tvSpan2.setLineSpacing(10, 1.0f);
    }

    private void update(){
        String input1 = mBinding.etSize.getText().toString();
        //String input2 = mBinding.etMulline.getText().toString();
        String input3 = mBinding.etLinespace.getText().toString();

        if (!TextUtils.isEmpty(input1)){
            mBinding.setVariable(com.wwt.test.BR.size, Integer.valueOf(input1.toString()));
        }
//        if (!TextUtils.isEmpty(input2)){
//            mBinding.tv3.setLineSpacing(0, Float.valueOf(input2.toString()));
//        }
        if (!TextUtils.isEmpty(input3)){
            mBinding.tv4.setLineSpacing(Float.valueOf(input3.toString()), (float) 1.0);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mBinding.tvSpace0.setText(getString(R.string.line_height) + mBinding.tv1.getLineHeight());
                String input = mBinding.etSize.getText().toString();
                if (TextUtils.isEmpty(input)){
                    input = "10";
                }
                int size = Integer.valueOf(input);
                int height = DisplayUtil.getFontHeight(TextSpaceActivity.this, size);
                int space = (height - DisplayUtil.sp2px(TextSpaceActivity.this, size))/2;
                Log.i("wwt", "size:"+size + "  height:"+height + "  space:"+space);
                mBinding.tvSpace1.setText(getString(R.string.line_height) + mBinding.tv3.getLineHeight());
                mBinding.tvSpace2.setText(getString(R.string.line_height) + mBinding.tv4.getLineHeight());
            }
        }, 500);
    }

}
