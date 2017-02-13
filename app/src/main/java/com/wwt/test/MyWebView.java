package com.wwt.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebView extends WebView{
    public interface MyWebViewInterface{
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl);
        public boolean shouldOverrideUrlLoading(WebView view, String url);
    }

    public interface OnscrollChanged{
        public void onScrollChanged(int l, int t, int oldl, int oldt);
    }

    private MyWebViewInterface mInterface;
    private OnscrollChanged mScrollChangeListener;

    public MyWebView(Context context) {
        super(context);
        init();
    }

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setInterface(MyWebViewInterface webViewInterface){
        mInterface = webViewInterface;
    }

    public void setScrollChangeListener(OnscrollChanged listener){
        mScrollChangeListener = listener;
    }

    private void init(){
        initWebViewParams(this);
        initWebViewSettings(this);
    }

    private void initWebViewParams(final WebView webview) {
        WebChromeClient chromeclient = new WebChromeClient(){

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);

            }

        };
        webview.setWebChromeClient(chromeclient);

        WebViewClient client = new WebViewClient(){

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                webview.getSettings().setBlockNetworkImage(false);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (mInterface != null){
                    if (mInterface.shouldOverrideUrlLoading(view, url)){
                        return true;
                    }else{
                        return super.shouldOverrideUrlLoading(view, url);
                    }
                }
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);

                if (mInterface != null){
                    mInterface.onReceivedError(view, errorCode, description, failingUrl);
                }
            }
        };
        webview.setWebViewClient(client);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mScrollChangeListener != null){
            mScrollChangeListener.onScrollChanged(l, t, oldl, oldt);
        }
    }

    private void initWebViewSettings(WebView webview){
        WebSettings settings = webview.getSettings();
        settings.setUseWideViewPort(true);
        settings.setDefaultTextEncodingName("utf-8");
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setBlockNetworkImage(true);
        if (Build.VERSION.SDK_INT >= 19) {
            settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
    }
}
