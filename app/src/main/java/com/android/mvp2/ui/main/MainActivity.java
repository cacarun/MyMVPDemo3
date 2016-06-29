package com.android.mvp2.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.mvp2.R;
import com.android.mvp2.base.BaseActivity;
import com.android.mvp2.base.BaseComponent;
import com.android.mvp2.ui.ActivityComponent;
import com.android.mvp2.ui.repos.ReposListActivity;

import javax.inject.Inject;

import butterknife.OnClick;
import dagger.Component;
import dagger.Module;

public class MainActivity extends BaseActivity implements MainContract.View {

    private static final String TAG = MainActivity.class.getName();

    // Inject 当注解一个属性的时候，表示该属性需要依赖（需要被注入一个对象）。
    //        当注解一个构造函数的时候，表示该构造函数可以提供依赖。
    @Inject
    MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMainPresenter.attachView(this);

    }

    @Override
    protected void injectDagger(@NonNull BaseComponent component) {
        ((MainComponent)component).inject(this);
    }

    @NonNull
    @Override
    protected BaseComponent createComponent(@NonNull ActivityComponent component) {
        return component.plus(new MainModule());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.detachView();
    }


    @Override
    public int getLayoutId(){
        return R.layout.activity_main;
    }

    @OnClick(R.id.id_main_button)
    public void onShowRepositoriesClick() {
        Log.e(TAG, "1. click");
        mMainPresenter.getClick();
    }

    @Override
    public void toReposListActivity() {
        Log.e(TAG, "3. click back");
        startActivity(new Intent(this, ReposListActivity.class));
    }
}
