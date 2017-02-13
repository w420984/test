package com.wwt.test;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.example.like.mylibrary.BaseAdapterHelper;
import com.example.like.mylibrary.QuickPagerAdapter;
import com.wwt.test.databinding.ActivityGalleryBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/7 0007.
 */

public class GalleryActivity extends BaseActivity {
    private ActivityGalleryBinding mBinding;
    private QuickPagerAdapter mAdapter;
    private List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_gallery);
        init();
    }

    private void init(){
        data = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            String str = getString(R.string.gallery) + (i+1);
            data.add(str);
        }

        mAdapter = new QuickPagerAdapter<String>(this, R.layout.view_gallery_item, data) {
            @Override
            protected void convertView(BaseAdapterHelper helper, String item) {
                helper.setText(R.id.text, item);
            }
        };
        mBinding.gallery.setAdapter(mAdapter);
    }
}
