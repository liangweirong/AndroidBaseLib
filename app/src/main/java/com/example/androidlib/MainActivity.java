package com.example.androidlib;

import android.os.Bundle;
import android.widget.TextView;

import com.example.androidlib.api.AppApi;
import com.example.baselib.net.BaseNetBean;
import com.example.baselib.net.RetrofitClient;
import com.example.baselib.net.observer.DefaultObserver;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends RxAppCompatActivity {

    @BindView(R.id.btn)
    TextView btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
        RetrofitClient.getInstance().getRetrofit().create(AppApi.class)
                .query("yuantong","806004019069559148")
                .compose(this.<BaseNetBean>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<BaseNetBean>() {
                    @Override
                    public void onSuccess(BaseNetBean baseNetBean) {

                    }
                });
    }
}
