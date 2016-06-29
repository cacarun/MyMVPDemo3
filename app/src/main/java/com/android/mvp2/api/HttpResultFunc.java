package com.android.mvp2.api;

import com.android.mvp2.data.model.HttpResult;
import com.android.mvp2.exception.HttpException;

import rx.functions.Func1;

/**
 * Created by cjw on 2016/6/29.
 */

public class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

    @Override
    public T call(HttpResult<T> httpResult) {
        if (httpResult.getErrorCode() != 0) {
            throw new HttpException(100);
        }
        return httpResult.getData();
    }
}
