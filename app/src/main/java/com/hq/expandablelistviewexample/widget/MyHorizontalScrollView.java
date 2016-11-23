package com.hq.expandablelistviewexample.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.HorizontalScrollView;

public class MyHorizontalScrollView extends HorizontalScrollView {
    private Context mContext;
    private int mMaxXOverscrollDistance;
    private static final int MAX_X_OVERSCROLL_DISTANCE = 100;

    public MyHorizontalScrollView(Context context) {
        super(context);
        this.mContext = context;
        initBounceDistance();
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initBounceDistance();
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        initBounceDistance();
    }

    private void initBounceDistance() {
        final DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        mMaxXOverscrollDistance = (int) (metrics.density * MAX_X_OVERSCROLL_DISTANCE);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        //这块是关键性代码,实现弹性效果
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, mMaxXOverscrollDistance, maxOverScrollY, isTouchEvent);
    }
}
