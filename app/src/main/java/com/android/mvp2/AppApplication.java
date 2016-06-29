package com.android.mvp2;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.mvp2.base.BaseComponent;

import java.util.HashMap;
import java.util.Map;

// 通过 HashMap 来维护所有的 Component，提供AppComponent的唯一实例
public class AppApplication extends Application {

    private AppComponent mAppComponent = null;

    private final Map<String, ? super BaseComponent> components = new HashMap<>();

    public static AppApplication get(@NonNull  Context context) {
        return (AppApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (mAppComponent == null) {
            mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        }

        String band = mAppComponent.getOSHelper().getDeviceBrand();
        Log.e("App", band);
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public BaseComponent getComponent(@NonNull String key) {
        //noinspection unchecked
        return (BaseComponent) components.get(key);
    }

    public void putComponent(@NonNull String key, @NonNull BaseComponent component) {
        components.put(key, component);
    }

    public void removeComponent(@NonNull String key) {
        components.remove(key);
    }
}
