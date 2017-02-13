package com.wwt.test;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wwt.test.View.CurveView;
import com.wwt.test.View.FoldView;
import com.wwt.test.View.PageTurnView;
import com.wwt.test.View.TwistView;
import com.wwt.test.databinding.ActivityCurlPageBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * 应用主界面
 * 
 * @author AigeStudio
 * @since 2014/12/15
 * @version 1.0.0
 * 
 */
public class PageCurlActivity extends BaseActivity {
	private ActivityCurlPageBinding mBinding;
	private PageTurnView mPageCurlView;// 翻页控件
	private FoldView mFoldView;// 折页控件
	private CurveView mCurveView;// 曲线控件
	private TwistView mTwistView;// 曲线控件

	private List<View> mViewList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mBinding = DataBindingUtil.setContentView(this, R.layout.activity_curl_page);

		LayoutInflater inflater=getLayoutInflater();
		mViewList = new ArrayList<View>();
		{
			View view=inflater.inflate(R.layout.view_layout, null);
			view.findViewById(R.id.text).setBackgroundColor(getResources().getColor(R.color.colorAccent));
			mViewList.add(view);
		}
		{
			View view=inflater.inflate(R.layout.view_page_pic, null);
			TwistView tv = (TwistView) view.findViewById(R.id.twistView);
			twistPage(tv);
			tv.getLayoutParams().width = 1000;
			mViewList.add(view);
		}
		{
			View view=inflater.inflate(R.layout.view_layout, null);
			view.findViewById(R.id.text).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
			mViewList.add(view);
		}
		{
			View view=inflater.inflate(R.layout.view_layout, null);
			view.findViewById(R.id.text).setBackgroundColor(getResources().getColor(R.color.colorAccent));
			mViewList.add(view);
		}

		PagerAdapter adapter = new PagerAdapter() {
			@Override
			public int getCount() {
				return mViewList.size();
			}

			@Override
			public boolean isViewFromObject(View view, Object object) {
				return view == object;
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				container.addView(mViewList.get(position));
				return mViewList.get(position);
			}

			@Override
			public void destroyItem(ViewGroup container, int position, Object object) {
				container.removeView(mViewList.get(position));
			}
		};


		mBinding.viewPager.setAdapter(adapter);
		mBinding.viewPager.setCurrentItem(0);


		// curvePage();

		// foldPage();

		// turnPage();
	}


	private void twistPage(TwistView view) {
		view.setBitmaps(initBitmaps());
	}

	private List<Bitmap> initBitmaps() {
		Bitmap bitmap = null;
		List<Bitmap> bitmaps = new ArrayList<Bitmap>();

		bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.page_img_a);
		bitmaps.add(bitmap);
		bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.page_img_b);
		bitmaps.add(bitmap);
		bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.page_img_c);
		bitmaps.add(bitmap);
		bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.page_img_d);
		bitmaps.add(bitmap);
		bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.page_img_e);
		bitmaps.add(bitmap);

		return bitmaps;
	}

	@Override
	protected void onDestroy() {
		if (null != mFoldView) {
			mFoldView.slideStop();
			mFoldView.getSlideHandler().removeCallbacksAndMessages(null);
		}
		if (null != mCurveView) {
			mCurveView.slideStop();
			mCurveView.getSlideHandler().removeCallbacksAndMessages(null);
		}
		super.onDestroy();
	}
}
