package com.wwt.test;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class WebBrowserActivity extends BaseActivity implements View.OnClickListener{

	private String mTitle;
	private TextView mTitleView;
	private ImageView mBackBtn;
	private MyWebView mWebView;
	private View mBlankView;
	private String mUrl;
	private boolean mNeedTitle = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_browser);
		
		initIntentData();
		initViews();
	}
	
	private void initIntentData(){
		Intent intent = getIntent();
		if(intent == null){
			finish();
			return;
		}
		
		mUrl = intent.getStringExtra("url");
		if (TextUtils.isEmpty(mUrl)){
			mUrl = "http://hats.haibao.cn/www/delivery/ck.php?oaparams=2__bannerid=11656__zoneid=1019__cb=58107557e5120__oadest=http://equity-vip.tmall.com/agent/mobile.htm?agentId=5752&_bind=true%20";
		}
		Log.i("wwt","url:"+mUrl);
		mNeedTitle = intent.getBooleanExtra("showtitle", false);
		if(mNeedTitle) {
			mTitle = intent.getStringExtra("title");
		}
	}

	@Override
	protected void onDestroy() {
		if (mWebView != null) {
			mWebView.removeAllViews();
			mWebView.setDownloadListener(null);
			mWebView.destroy();
			mWebView = null;
        }
		
		super.onDestroy();
	}
	
	private void initViews(){
		int screenW = getScreenWidth(this);
		RelativeLayout.LayoutParams lparam = (RelativeLayout.LayoutParams)findViewById(R.id.ll_title).getLayoutParams();
		lparam.height = (90 * screenW) / 750;

		mTitleView = (TextView)findViewById(R.id.tv_title);
		mTitleView.setPadding(((60 + 2 * 30)*screenW)/750, 0, ((60 + 2 * 30)*screenW)/750, 0);
		if(!mNeedTitle) {
			mTitleView.setVisibility(View.GONE);
			findViewById(R.id.ll_title).setVisibility(View.GONE);
		}
		if(!TextUtils.isEmpty(mTitle)) {
			mTitleView.setText(mTitle);
		}

		mBackBtn = (ImageView)findViewById(R.id.btn_back);
		mBackBtn.setOnClickListener(this);
		int paddingV = (15 * screenW) / 750;
		int paddingH = (30 * screenW) / 750;
		RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)mBackBtn.getLayoutParams();
		params.width = (60 * screenW) / 750 + paddingH * 2;
		params.height = (60 * screenW) / 750 + paddingV * 2;
		mBackBtn.setLayoutParams(params);
		mBackBtn.setPadding(paddingH, paddingV, paddingH, paddingV);
		mWebView = (MyWebView)findViewById(R.id.webview);
		mBlankView = findViewById(R.id.blank_view);
		mBlankView.setVisibility(View.GONE);
		
		mWebView.setInterface(new MyWebView.MyWebViewInterface() {
			@Override
			public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
				//当页面加载失败时，显示空白页，并给出相应提示，然后自动关闭
				mBlankView.setVisibility(View.VISIBLE);
				Log.i("wwt", "errorCode:" + errorCode);
				Log.i("wwt", "description:"+description);
				Log.i("wwt", "failingUrl:"+failingUrl);
				//Utils.showToast(WebBrowserActivity.this, R.string.AppIOException, Toast.LENGTH_SHORT);
//				new Handler().postDelayed(new Runnable() {
//
//					@Override
//					public void run() {
//						finish();
//					}
//				}, 1000);
			}

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				return false;
			}
		});
		mWebView.loadUrl(mUrl);
	}

	public static void getScreenRect(Context ctx_, Rect outrect_) {
		if (ctx_ == null || outrect_ == null){
			return;
		}
		Display screenSize = ((WindowManager) ctx_
				.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		outrect_.set(0, 0, screenSize.getWidth(), screenSize.getHeight());
	}

	public static int getScreenWidth(Context context){
		Rect rect = new Rect();
		getScreenRect(context, rect);
		return (rect.right - rect.left);
	}

	public void back(){
		if (mWebView.canGoBack()) {
			mWebView.goBack();
        } else {
			finish();
		}
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.btn_back){
			back();
		}
	}
}
