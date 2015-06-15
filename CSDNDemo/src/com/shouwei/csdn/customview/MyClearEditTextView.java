package com.shouwei.csdn.customview;
import com.shouwei.csdn.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;

/**
 * 带清空功能的edittext
 * @author sw
 * @date 2015-5-28
 */
public class MyClearEditTextView extends EditText implements TextWatcher {
	Drawable mRightDrawable;

	public MyClearEditTextView(Context context) {
		this(context, null);
	}

	public MyClearEditTextView(Context context, AttributeSet attr) {
		this(context, attr, android.R.attr.editTextStyle);
	}

	public MyClearEditTextView(Context context, AttributeSet attr, int defStyle) {
		super(context, attr, defStyle);
		init();
	}

	@SuppressWarnings("deprecation")
	public void init() {
		// getCompoundDrawables()[int]
		// 获得控件上的drawable信息0:drawleft,1:drawtop,2:drawright,3:drawbottom
		mRightDrawable = getCompoundDrawables()[2];
		if (mRightDrawable == null) {
			mRightDrawable = getResources().getDrawable(R.drawable.clear_btn);
		}
		// setBounds指定此drawable在canvas画布上绘制时的边界位置（左边界，上边界，右边界，底边界）
		mRightDrawable.setBounds(0, 0, mRightDrawable.getIntrinsicWidth(),
				mRightDrawable.getIntrinsicHeight());
		setClearIconVisible(this.getText().length()>0);
		this.addTextChangedListener(this);
	}

	/**
	 * 设置清空按钮是否显示
	 * 
	 * @param visible
	 * @auth shouwei
	 */
	public void setClearIconVisible(boolean visible) {
		Drawable right = visible ? mRightDrawable : null;
		// 设置edittext的drawleft/top/right/bottom(此方法必须在drawable.setBounds()的基础上,null即为不设置)
		setCompoundDrawables(getCompoundDrawables()[0],
				getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
	}

	/**
	 * 获取到默认的清空按钮图片
	 * 
	 * @return
	 * @auth shouwei
	 */
	@SuppressWarnings("deprecation")
	public Drawable getDefalutRightDrawable() {
		return getResources().getDrawable(R.drawable.clear_btn);
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if (event.getAction() == MotionEvent.ACTION_UP) {
			boolean b = (event.getX() > (getWidth() - getPaddingRight() - mRightDrawable
					.getIntrinsicWidth()))
					&& (event.getX() < (getWidth() - getPaddingRight()));
			if (b) {
				this.setText("");
			}
		}

		return super.onTouchEvent(event);
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int count, int after) {
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
	}

	@Override
	public void afterTextChanged(Editable s) {
		setClearIconVisible(s.length() > 0);
	}
}
