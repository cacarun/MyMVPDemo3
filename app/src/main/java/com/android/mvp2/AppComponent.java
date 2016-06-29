package com.android.mvp2;

import android.app.Application;
import android.content.Context;

import com.android.mvp2.api.HttpModule;
import com.android.mvp2.api.service.HttpService;
import com.android.mvp2.base.BaseComponent;
import com.android.mvp2.ui.ActivityComponent;
import com.android.mvp2.ui.ActivityModule;
import com.android.mvp2.util.OSHelper;

import javax.inject.Singleton;

import dagger.Component;

// 全局的Component，是个单例
@Singleton
@Component(modules = { AppModule.class, HttpModule.class})
public interface AppComponent extends BaseComponent {

    // 这边把需要关联的SubComponent写入
    ActivityComponent plus(ActivityModule module);

    void inject(AppApplication application);

    // 写在这里是可以在使用的时候才开始创建；或者可以不写，直接通过在 ReposListActivity 注释 inject，这样的话在注入时就会得到OSHelper对象。
    OSHelper getOSHelper();
    // 这里的不行??
    // HttpService getHttpService();

    Context context();

    Application getApplication();

}