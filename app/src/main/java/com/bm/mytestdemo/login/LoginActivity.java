package com.bm.mytestdemo.login;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.bm.mytestdemo.R;
import com.bm.mytestdemo.adapter.LoginAdapter;
import com.bm.mytestdemo.base.mvp.BasePresenter;
import com.bm.mytestdemo.base.view.BaseActivity;
import com.bm.mytestdemo.customview.CustomRecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class LoginActivity extends BaseActivity implements CustomRecyclerView
        .ShowTopViewListener, Callback {
    private CustomRecyclerView mRecyclerView;
    private List<String> list = new ArrayList<>();
    private LoginAdapter adapter;
    private LinearLayout mLinearLayout;
    private RelativeLayout mRelativeLayout;
    private View mView;
    private TextSwitcher mTextSwitcher;
    private String path = "http://api.douban.com/v2/movie/in_theaters";
    List<String> lists = Arrays.asList("a", "b", "c", "d", "e");
    String[] str = {"a", "b", "c", "d", "e"};
    int count = 0;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //super.handleMessage(msg);
            Log.e("@@@", "11111111");
            count++;
            mTextSwitcher.setText(str[count % 5]);
            handler.sendEmptyMessageDelayed(1, 5000);
        }
    };
    String string = "2.4.8";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mRecyclerView = findViewById(R.id.mRecyclerView);
        mTextSwitcher = findViewById(R.id.mTextSwitcher);
        mLinearLayout = findViewById(R.id.mLinearLayout);
        mView = findViewById(R.id.mView);
        mRelativeLayout = findViewById(R.id.mRelativeLayout);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        for (int i = 0; i < 10; ++i) {
            list.add("中国" + i);
        }

        adapter = new LoginAdapter(list);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout
                .LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setShowTopViewListenter(this);
        /*findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //list.addAll(0,lists);
                //RecyclerView列表进行批量UI数据更新
                //adapter.notifyItemRangeInserted(0,lists.size());
                 //scrollToPosition（0）作用是把列表移动到顶端
                 //mRecyclerView.smoothScrollToPosition(0);
                //handler.sendEmptyMessage(1);
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });*/

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Log.e("@@@",position+"");
            }
        });
        adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                list.remove(position);
                adapter.notifyItemRemoved(position);
                return false;
            }
        });
        mTextSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(LoginActivity.this);
                textView.setTextSize(36);
                return textView;
            }
        });

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request request1 = request.newBuilder()
                        .addHeader("Content-Type", "application/x-www-form-urlencoded")
                        .addHeader("Accept", "application/json")
                        .method(request.method(), request.body())
                        .build();
                return chain.proceed(request1);
            }
        }).addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("retrofit", "retrofit:" + message);
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY)).build();
        Request request = new Request.Builder().url(path).build();
        client.newCall(request).enqueue(this);


    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void TopViewShow(int value) {
        ViewGroup.LayoutParams layoutParams = mRelativeLayout.getLayoutParams();
        layoutParams.height = (int) Math.abs(value * 0.6f);
        mRelativeLayout.setLayoutParams(layoutParams);
    }

    @Override
    public void TopViewDismiss(int value) {
        final ViewGroup.LayoutParams layoutParams = mRelativeLayout.getLayoutParams();
        layoutParams.height = 0;
        mRelativeLayout.setLayoutParams(layoutParams);
        list.addAll(0, lists);
        //RecyclerView列表进行批量UI数据更新
        adapter.notifyItemRangeInserted(0, lists.size());

        //scrollToPosition（0）作用是把列表移动到顶端
        mRecyclerView.scrollToPosition(0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeMessages(1);

    }

    @Override
    public void onFailure(Call call, IOException e) {
        Log.e("onFailure", e.toString());
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        //Log.e("onResponse",response.body().string()+"---"+response.code());
    }

    public void BtnOnClick(View view) {
        //Log.e("@@@",getConnectedType(this)+"");
        /*Intent intent = new Intent();
        intent.setComponent(new ComponentName())*/
        //startActivity(new Intent(LoginActivity.this, MainActivity.class));

    }






}
