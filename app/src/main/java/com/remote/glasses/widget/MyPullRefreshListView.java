package com.remote.glasses.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

/**
 * Created by admin on 2016/7/11.
 */
public class MyPullRefreshListView extends PullToRefreshListView {
    public MyPullRefreshListView(Context context) {
        super(context);
    }

    public MyPullRefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyPullRefreshListView(Context context, Mode mode) {
        super(context, mode);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
