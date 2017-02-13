package com.wwt.test.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.wwt.test.BR;
import com.wwt.test.R;
import com.wwt.test.databinding.ViewMenuItemViewBinding;
import com.wwt.test.utils.Utils;

/**
 * Created by CBS on 2016/5/27.
 */
public class MenuItemView extends RelativeLayout {
    private ViewMenuItemViewBinding mBinding;
    private boolean mHasTopDivider = false;
    private boolean mHasBottomDivider = false;
    private String mMenuText = "";
    private int mIconResID;
    private int mIconWidth;
    private int mIconHeight;
    private boolean mHasRightArrow;
    private int mNum = 0;

    public MenuItemView(Context context) {
        super(context);
        initViews();
    }

    public MenuItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
        initViews();
    }

    public MenuItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
        initViews();
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray array = getContext().obtainStyledAttributes(attrs,
                R.styleable.MenuItemView);
        mHasTopDivider = array.getBoolean(R.styleable.MenuItemView_top_divider, false);
        mHasBottomDivider = array.getBoolean(R.styleable.MenuItemView_bottom_divider, false);
        mMenuText = array.getString(R.styleable.MenuItemView_text_content);
        mIconResID = array.getResourceId(R.styleable.MenuItemView_icon_res_id, 0);
        mIconWidth = array.getInt(R.styleable.MenuItemView_icon_width, 0);
        mIconHeight = array.getInt(R.styleable.MenuItemView_icon_height, 0);
        mHasRightArrow = array.getBoolean(R.styleable.MenuItemView_has_right_arrow, true);

        array.recycle();
    }

    public void setDividerEnable(boolean topEnable, boolean bottomEnable) {
        mHasTopDivider = topEnable;
        mHasBottomDivider = bottomEnable;
        mBinding.executePendingBindings();
    }

    public void setMenuText(String text) {
        mMenuText = text;
        mBinding.executePendingBindings();
    }

    public void setNum(int num) {
        mNum = num;
        updateNumView();
    }

    private void initViews() {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.view_menu_item_view, this, true);
        mBinding.setVariable(BR.hasTopDivider, mHasTopDivider);
        mBinding.setVariable(BR.hasBottomDivider, mHasBottomDivider);
        mBinding.setVariable(BR.menuText, mMenuText);
        mBinding.executePendingBindings();

        int screenW = Utils.getScreenWidth(getContext());
        mBinding.menuLayout.setPadding((30 * screenW) / 750, 0, 0, 0);

        LinearLayout.LayoutParams lParam = (LinearLayout.LayoutParams)mBinding.menuItem.getLayoutParams();
        lParam.height = (100 * screenW) / 750;

        LayoutParams rParam;
//        LayoutParams rParam = (LayoutParams)mBinding.iconRightArrow.getLayoutParams();
//        rParam.width = (16 * screenW) / 750;
//        rParam.height = (260 * screenW) / 750;
//        rParam.rightMargin = (30 * screenW) / 750;
//        mBinding.iconRightArrow.setVisibility(mHasRightArrow ? View.VISIBLE : View.GONE);

//        rParam = (LayoutParams)mBinding.tvNum.getLayoutParams();
//        rParam.width = (40 * screenW) / 750;
//        rParam.height = rParam.width;
//        rParam.rightMargin = (40 * screenW) / 750;

        updateNumView();

        if(mIconResID > 0 && mIconWidth > 0 && mIconHeight > 0) {
            rParam = (LayoutParams)mBinding.menuItemIcon.getLayoutParams();
            rParam.width = (mIconWidth * screenW) / 750;
            rParam.height = (mIconWidth * screenW) / 750;
            rParam.rightMargin = (20 * screenW) / 750;
            mBinding.menuItemIcon.setVisibility(View.VISIBLE);
            mBinding.menuItemIcon.setImageResource(mIconResID);
        }else {
            mBinding.menuItemIcon.setVisibility(View.GONE);
        }
    }

    private void updateNumView() {
//        if(mNum == 0) {
//            mBinding.tvNum.setVisibility(GONE);
//        }else {
//            mBinding.tvNum.setVisibility(VISIBLE);
//            if(mNum > 99) {
//                mBinding.tvNum.setText("N");
//            }else {
//                mBinding.tvNum.setText("" + mNum);
//            }
//        }
    }
}
