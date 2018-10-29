package com.bm.mytestdemo.customview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by xiao on 2018/9/10.
 */

public class CustomRecyclerView extends RecyclerView {
    private int count;
    private int countY;
    private int value;

    public CustomRecyclerView(Context context) {
        this(context, null);
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            countY = (int) ev.getY();
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:
                count = (int) e.getY();
                if (!canScrollUp() && countY - count < 0) {
                    value = countY - count;
                    listener.TopViewShow(value);
                    return true;
                }

                break;
            case MotionEvent.ACTION_UP:
                if (!canScrollUp() && countY - count < 0) {
                    listener.TopViewDismiss(value);
                }
                break;
        }
        return super.onTouchEvent(e);
    }

    /**
     * 判断是不是滚动到了最顶部，这个是从SwipeRefreshLayout里面copy过来的源代码
     */
    public boolean canScrollUp() {
        if (android.os.Build.VERSION.SDK_INT < 14) {
            return ViewCompat.canScrollVertically(this, -1) || this.getScrollY() > 0;
        } else {
            return ViewCompat.canScrollVertically(this, -1);
        }
    }

    ShowTopViewListener listener;

    public void setShowTopViewListenter(ShowTopViewListener listener) {
        this.listener = listener;
    }

    public interface ShowTopViewListener {
        void TopViewShow(int value);

        void TopViewDismiss(int value);
    }

}
