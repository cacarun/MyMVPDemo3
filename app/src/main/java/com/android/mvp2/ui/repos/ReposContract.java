package com.android.mvp2.ui.repos;

import com.android.mvp2.base.BasePresenter;
import com.android.mvp2.base.BaseView;
import com.android.mvp2.data.model.Subject;

import rx.Subscriber;

/**
 * Created by cjw on 2016/6/28.
 */
public interface ReposContract {

    interface Presenter extends BasePresenter<View> {
        void getData(Subscriber<Subject> subscriber);
    }

    interface View extends BaseView {
        // void fillData(String data);
    }

}
