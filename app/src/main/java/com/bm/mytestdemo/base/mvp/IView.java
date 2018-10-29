package com.bm.mytestdemo.base.mvp;

import android.app.Activity;
import android.content.Context;

import io.reactivex.disposables.Disposable;

/**
 * Created by xiao on 2018/5/28.
 */

public interface IView {

    void addDisposable(Disposable disposable);

    Context getContext();

    Activity getActivity();

}
