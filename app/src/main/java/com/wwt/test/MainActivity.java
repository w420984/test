package com.wwt.test;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.wwt.test.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startService(new Intent(this, LockScreenService.class));
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initViews();
    }

    private void initViews(){
        mBinding.menuTextSpace.setOnClickListener(this);
        mBinding.menuTextBg.setOnClickListener(this);
        mBinding.menuWeb.setOnClickListener(this);
        mBinding.menuQuickAdapter.setOnClickListener(this);
        mBinding.menuGallery.setOnClickListener(this);
        mBinding.pageCurl.setOnClickListener(this);
        mBinding.menuHtmlText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mBinding.menuTextSpace){
            startActivity(new Intent(this, TextSpaceActivity.class));
        }else if (v == mBinding.menuWeb){
            startActivity(new Intent(this, WebBrowserActivity.class));
        }else if(v == mBinding.menuTextBg){
            startActivity(new Intent(this, TextBgActivity.class));
        }else if (v == mBinding.menuGallery){
            startActivity(new Intent(this, GalleryActivity.class));
        }else if(v == mBinding.pageCurl){
            startActivity(new Intent(this, PageCurlActivity.class));
        }else if(v == mBinding.menuHtmlText){
            startActivity(new Intent(this, HtmlTextActivity.class));
        }
    }
}
