package com.example.draganddraw;

import android.graphics.Point;
import android.graphics.PointF;

/**
 * Created by 离子态狍子 on 2016/10/17.
 */

public class Box {
    private PointF mOrigin;
    private PointF mCurrent;

    public Box(PointF origin) {
        mCurrent = origin;
        mOrigin = origin;
    }

    public PointF getOrigin() {
        return mOrigin;
    }

    public PointF getCurrent() {
        return mCurrent;
    }

    public void setCurrent(PointF current) {
        mCurrent = current;
    }
}
