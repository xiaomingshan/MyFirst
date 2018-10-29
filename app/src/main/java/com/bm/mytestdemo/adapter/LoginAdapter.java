package com.bm.mytestdemo.adapter;

import android.support.annotation.Nullable;

import com.bm.mytestdemo.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by xiao on 2018/9/10.
 */

public class LoginAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public LoginAdapter(@Nullable List<String> data) {
        super(R.layout.layout_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tvTxt,item);
    }
}
