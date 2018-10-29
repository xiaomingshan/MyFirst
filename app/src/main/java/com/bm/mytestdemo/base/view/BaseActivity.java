package com.bm.mytestdemo.base.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.bm.mytestdemo.base.mvp.BasePresenter;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by xiao on 2018/5/28.
 * 基类-activity
 */

public abstract class BaseActivity<V, P extends BasePresenter<V>> extends FragmentActivity {
    private P mPresenter;
    private CompositeDisposable disposables = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        if (mPresenter != null) {
            mPresenter.attach((V) this);
        }

    }

    public abstract P createPresenter();

    /**
     * umeng统计集成
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * umeng统计集成
     */
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detach();
        }
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
        super.onDestroy();

    }

    public void addDisposable(Disposable disposable) {
        if (disposable != null) {
            disposables.add(disposable);
        }
    }

    /**
     * 显示弹框
     */
    public void showDialog(boolean isShow) {

    }

    /**
     * 关闭弹框
     */
    public void dismisDialog() {

    }

    public Context getContext() {
        return getApplicationContext();
    }

    public Activity getActivity() {
        return this;
    }
}
