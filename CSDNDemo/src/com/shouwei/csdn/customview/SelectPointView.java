package com.shouwei.csdn.customview;

import com.shouwei.csdn.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * 小圆点
 * @author sw
 * @date 2015-6-8
 */
public class SelectPointView extends View {
    int count = 4, singleWidth, width, height;
    OnPointSelectListener listener;
    int position = 0;
    Paint paint = new Paint();
    Bitmap selected, unselect;
    int drawHeight,drawWidth;

    public SelectPointView(Context context, int count) {
        super(context);
    }

    public SelectPointView(Context context, AttributeSet attr) {
        super(context, attr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        selected = BitmapFactory.decodeResource(getResources(), R.drawable.page_true);
        unselect = BitmapFactory.decodeResource(getResources(), R.drawable.page_false);
        width = getWidth();
        height = getHeight();
        drawHeight = getHeight()/2-(selected.getHeight()/2);
        if (count != 0) {
            singleWidth = width / count;
            for (int i = 0; i < count; i++) {
                drawWidth = (i+1)*singleWidth-singleWidth/2-selected.getWidth()/2;
                if (i == position) {
                    canvas.drawBitmap(selected, drawWidth, drawHeight, paint);
                } else {
                    canvas.drawBitmap(unselect, drawWidth, drawHeight, paint);
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int currentPos = (int) (event.getX()/getWidth()*count);
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            listener.onPointSelect(currentPos);
        }
        return true;
    }

    public void setPosition(int position) {
        this.position = position;
        invalidate();
    }

    public void setOnPointSelectListener(OnPointSelectListener l) {
        this.listener = l;
    }
    public interface OnPointSelectListener {
        public void onPointSelect(int position);
    }
}


