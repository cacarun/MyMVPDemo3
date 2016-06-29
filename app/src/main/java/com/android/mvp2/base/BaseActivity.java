package com.android.mvp2.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.android.mvp2.AppApplication;
import com.android.mvp2.ui.ActivityComponent;
import com.android.mvp2.ui.ActivityModule;

import java.util.UUID;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    String componentKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getLayoutId() != 0)
            setContentView(getLayoutId());

        ButterKnife.bind(this);

        injectDagger(getComponent());
    }

    protected abstract void injectDagger(@NonNull BaseComponent component);

    @NonNull protected abstract BaseComponent createComponent(@NonNull ActivityComponent component);

    protected abstract int getLayoutId();


    private BaseComponent getComponent() {
        final AppApplication app = AppApplication.get(this);
        final BaseComponent component;
        if (componentKey == null) {
            componentKey = UUID.randomUUID().toString();

            // 先获得ActivityComponent，而具体的SubComponent是跟该ActivityComponent关联的
            component = createComponent(app.getAppComponent().plus(new ActivityModule()));

            app.putComponent(componentKey, component);
        } else {
            component = app.getComponent(componentKey);
            if (component == null) {
                throw new IllegalStateException("Component was not properly stored.");
            }
        }

        return component;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);

        if (isFinishing()) {
            AppApplication.get(this).removeComponent(componentKey);
        }
    }

    // base view implement

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void close() {

    }

    @Override
    public void showProgress(String msg) {

    }

    @Override
    public void showProgress(String msg, int progress) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showErrorMessage(String msg, String content) {

    }
}