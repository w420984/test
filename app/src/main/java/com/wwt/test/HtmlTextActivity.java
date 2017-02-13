package com.wwt.test;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;

import com.wwt.test.databinding.ActivityHtmlTextBinding;

import org.xml.sax.XMLReader;

/**
 * Created by Administrator on 2016/12/21 0021.
 */

public class HtmlTextActivity extends BaseActivity{
    private ActivityHtmlTextBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_html_text);

        init();
    }

    private void init(){
        String text = "确保主体<a href=\"http://www.haibao.com\"><span>影像</span></a>清晰可辨，不采用拼图格式。";

        mBinding.text.setText(Html.fromHtml(text, null, new Html.TagHandler() {
            @Override
            public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
                int sIndex = 0;
                int eIndex=0;
                if (tag.toLowerCase().equals("span")) {
                    if (opening) {
                        sIndex=output.length();
                    }else {
                        eIndex=output.length();
                        Log.i("wwt", "tag:"+tag + "  output:"+output.toString().substring(sIndex, eIndex));
                        //output.setSpan(new MyTagHandler.MxgsaSpan(), sIndex, eIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                }
            }
        }));
        mBinding.text.setClickable(true);
        mBinding.text.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public class MyTagHandler implements Html.TagHandler{
        private int sIndex = 0;
        private  int eIndex=0;
        private final Context mContext;

        public MyTagHandler(Context context){
            mContext=context;
        }

        @Override
        public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
            // TODO Auto-generated method stub

            if (tag.toLowerCase().equals("span")) {
                if (opening) {
                    sIndex=output.length();
                }else {
                    eIndex=output.length();
                    Log.i("wwt", "tag:"+tag + "  output:"+output.toString().substring(sIndex, eIndex));
                    output.setSpan(new MxgsaSpan(), sIndex, eIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }

        }
        private class MxgsaSpan extends ClickableSpan implements View.OnClickListener{
            @Override
            public void onClick(View widget) {
                // TODO Auto-generated method stub
                //具体代码，可以是跳转页面，可以是弹出对话框，下面是跳转页面
                //mContext.startActivity(new Intent(mContext,MainActivity.class));
                Log.i("wwt", "onclick");
            }
        }

    }
}
