package com.android.mvp2.ui.repos;

import com.android.mvp2.base.BaseComponent;
import com.android.mvp2.ui.ActivityScope;
import com.android.mvp2.ui.main.MainActivity;
import com.android.mvp2.ui.main.MainModule;

import dagger.Component;
import dagger.Module;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {ReposModule.class})
public interface ReposComponent extends BaseComponent {

    void inject(ReposListActivity mainActivity);

}
