package com.bm.mytestdemo.main;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.bm.mytestdemo.R;
import com.bm.mytestdemo.base.mvp.BasePresenter;
import com.bm.mytestdemo.base.view.BaseActivity;
import com.bm.mytestdemo.customview.MySurfaceView;

import java.lang.ref.WeakReference;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    RelativeLayout mRelativeLayout;
    Button tvTxt;
    MySurfaceView mySurfaceView;
    private double d;
    WeakReference<MyAdd> weakReference;
    MyAdd m;
    int j = 0;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRelativeLayout = findViewById(R.id.mRelativeLayout);
        tvTxt = findViewById(R.id.tvTxt);
        mySurfaceView = findViewById(R.id.mySurfaceView);
        tvTxt.setOnClickListener(this);
        m = new MyAdd();
        weakReference = new WeakReference<MyAdd>(m);

    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        if (!m.isInterrupted()) {
            m.interrupt();
            m.start();
        }

    }

    class MyAdd extends Thread {
        @Override
        public void run() {
            for (double i = 1d; i <= 100d; i++) {
                d += 1 / i;
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println(weakReference.get());
        weakReference.clear();
        System.gc();
        System.out.println(weakReference.get());
    }
}
