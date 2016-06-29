package com.android.mvp2.ui.main;

import com.android.mvp2.base.BaseComponent;
import com.android.mvp2.ui.ActivityScope;

import dagger.Subcomponent;

// 具体的某个Module，可以对应一个或多个activity。它必须要包含需要注入的activity。

@ActivityScope
@Subcomponent(modules = {MainModule.class})
public interface MainComponent extends BaseComponent {

    void inject(MainActivity mainActivity);

}
