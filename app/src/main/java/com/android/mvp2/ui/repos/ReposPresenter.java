package com.android.mvp2.ui.repos;

import android.content.Context;

import com.android.mvp2.api.HttpResultFunc;
import com.android.mvp2.api.service.HttpService;
import com.android.mvp2.data.model.Subject;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by cjw on 2016/6/27.
 */
public class ReposPresenter implements ReposContract.Presenter {

    private ReposContract.View mView;
    private Context mContext;

    @Inject
    HttpService httpService;

    @Inject
    public ReposPresenter(Context context) {
        this.mContext = context;
    }


    @Override
    public void attachView(ReposContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
        // if (mSubscription != null) mSubscription.unsubscribe();
    }

    @Override
    public void getData(Subscriber<Subject> subscriber) {
        httpService.getTopMovie(0, 2)
                .map(new HttpResultFunc<Subject>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }

}
