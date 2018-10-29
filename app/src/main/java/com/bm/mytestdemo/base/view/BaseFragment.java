package com.bm.mytestdemo.base.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.bm.mytestdemo.R;
import com.bm.mytestdemo.base.mvp.BasePresenter;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by xiao on 2018/5/28.
 * fragment-基类
 */

public abstract class BaseFragment<V, P extends BasePresenter<V>> extends Fragment {
    private P mPresenter;
    private CompositeDisposable disposables = new CompositeDisposable();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle
            savedInstanceState) {
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attach((V)this);
        }
        View view = inflater.inflate(R.layout.fragment_loading, container, false);
        createView(inflater, (FrameLayout) view.findViewById(R.id.content), savedInstanceState);
        return view;
    }

    protected abstract void createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    public abstract P createPresenter();
    @Override
    public void onResume() {
        super.onResume();

    }
    @Override
    public void onPause() {
        super.onPause();
    }
    @Override
    public void onDestroyView() {
        if (mPresenter!=null){
            mPresenter.detach();
        }
        if (!disposables.isDisposed()){
            disposables.dispose();
        }
        super.onDestroyView();
    }
    public void addDisposable(Disposable disposable){
        if (disposable!=null){
            disposables.add(disposable);
        }
    }
    /**
     * 显示弹框
     */
    public void showDialog(boolean isShow){

    }

    /**
     * 关闭弹框
     */
    public void dismisDialog(){

    }
    /**
     * 友盟页面统计的页面名称
     * @return 页面名称
     */
    protected String getPageName() {
        return ((Object) this).getClass().getSimpleName();
    }
}
