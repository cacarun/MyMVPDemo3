package com.android.mvp2.ui;

import com.android.mvp2.base.BaseComponent;
import com.android.mvp2.ui.main.MainComponent;
import com.android.mvp2.ui.main.MainModule;
import com.android.mvp2.ui.repos.ReposComponent;
import com.android.mvp2.ui.repos.ReposModule;

import dagger.Subcomponent;

// 第一层SubComponent，它的作用如同AppComponent，关联它下面的所有的SubComponent。

@ActivityScope
@Subcomponent(modules = {ActivityModule.class})
public interface ActivityComponent extends BaseComponent {

    MainComponent plus(MainModule module);
    ReposComponent plus(ReposModule module);

}
