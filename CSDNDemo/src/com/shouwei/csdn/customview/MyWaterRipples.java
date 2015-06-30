package com.shouwei.csdn.customview;

import com.shouwei.csdn.R;
import com.shouwei.csdn.util.DensityUtils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;
import android.view.View;

public class MyWaterRipples extends View {

	public final static double STRETCH_FACTOR_A = 8;

	public final static double OFFSET_Y = 100;

	public float riseHeight = 100;
	// 第一条水波移动速度
	private static final int TRANSLATE_X_SPEED_ONE = 7;
	// 第二条水波移动速度
	private static final int TRANSLATE_X_SPEED_TWO = 5;
	private int translate_speed_one;
	private int translate_speed_two;

	private float mCycleFactorW;
	private int mTotalWidth = 0;
	private int mTotalHeight = 0;
	private float[] mYPositions;
	private float[] mYPositionsOne;
	private float[] mYPositionsTwo;
	private Paint mWavePaint;
	private PaintFlagsDrawFilter mDrawFilter;
	
	private int YOffsetOne;
	private int YOffsetTwo;
	
	public MyWaterRipples(Context context, AttributeSet attrs) {
		super(context, attrs);
		//将dp转化为px统一不同分辨率下的速度一致
		translate_speed_one = DensityUtils
				.dp2px(context, TRANSLATE_X_SPEED_ONE);
		translate_speed_two = DensityUtils
				.dp2px(context, TRANSLATE_X_SPEED_TWO);
		// 画笔
		mWavePaint = new Paint();
		mWavePaint.setColor(getResources().getColor(R.color.seablue));
		mWavePaint.setAntiAlias(true);
		
		mDrawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG
				| Paint.FILTER_BITMAP_FLAG);

	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// 从canvas层面去除绘制时锯齿
		canvas.setDrawFilter(mDrawFilter);
		if (mTotalWidth != 0) {
			for (int i = 0; i < mTotalWidth; i++) {
				// 减400只是为了控制波纹绘制的y的在屏幕的位置，大家可以改成一个变量，然后动态改变这个变量，从而形成波纹上升下降效果
				// 绘制第一条水波纹
				canvas.drawLine(i, mTotalHeight - mYPositions[i] - riseHeight,
						i, mTotalHeight, mWavePaint);
			}
		}
	}
	
	private void resetYPosition(){
		
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		
		// 获得屏幕宽度
		mTotalWidth = w;
		mTotalHeight = h;
		// 将周期定为view总宽度
		mCycleFactorW = (float) (2 * Math.PI / mTotalWidth);
		// 保存原始Ｙ坐标数组
		mYPositions = new float[mTotalWidth + 1];
		// 保存第一条波浪的Ｙ坐标数组
		mYPositionsOne = new float[mTotalWidth + 1];
		// 保存第二条波浪的Ｙ坐标数组
		mYPositionsTwo = new float[mTotalHeight + 1];
		// 根据view总宽度得出所有对应的y值
		for (int i = 0; i < mTotalWidth; i++) {
			mYPositions[i] = (float) (STRETCH_FACTOR_A
					* Math.sin(mCycleFactorW * i) + OFFSET_Y);
		}
	}

}
