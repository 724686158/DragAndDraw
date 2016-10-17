package com.example.draganddraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 离子态狍子 on 2016/10/17.
 */

public class BoxDrawingView extends View {
    private static final String TAG = "BoxDrawingView";

    private Box mCurrentBox;
    private List<Box> mBoxen = new ArrayList<>();
    private Paint mBoxPaint;
    private Paint mBackgroundPaint;


    public BoxDrawingView(Context context) {
        this(context, null);
    }
    public BoxDrawingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        mBoxPaint = new Paint();
        mBoxPaint.setColor(R.color.colorAccent);

        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(R.color.colorPrimaryDark);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        PointF current = new PointF(event.getX(), event.getY());
        String action = "";

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                action = "ACTION_DOWN";
                CreatBox(current);
                break;
            case MotionEvent.ACTION_MOVE:

                action = "ACTION_MOVE";
                ChangeBox(current);
                break;
            case MotionEvent.ACTION_UP:
                action = "ACTION_UP";
                SaveBox(current);
                break;
            case MotionEvent.ACTION_CANCEL:
                action = "ACTION_CANCEL";
                SaveBox(current);
                break;
        }
        Log.i(TAG, "onTouchEvent: ACTION " + action + "---at x=" + current.x + ", y=" + current.y);
        return true;
    }

    private void SaveBox(PointF current) {
        if (mCurrentBox != null) {
            mBoxen.add(mCurrentBox);
            mCurrentBox = null;
        }
    }

    private void ChangeBox(PointF current) {
        if (mCurrentBox != null) {
            mCurrentBox.setCurrent(current);
            invalidate();
        }
    }

    private void CreatBox(PointF current) {
        mCurrentBox = new Box(current);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPaint(mBackgroundPaint);
        for (Box box : mBoxen) {
            float left = Math.min(box.getOrigin().x, box.getCurrent().x);
            float right = Math.max(box.getOrigin().x, box.getCurrent().x);
            float top = Math.min(box.getOrigin().y, box.getCurrent().y);
            float buttom = Math.max(box.getOrigin().y, box.getCurrent().y);
            canvas.drawRect(left, top, right, buttom, mBoxPaint);

        }
    }
}
