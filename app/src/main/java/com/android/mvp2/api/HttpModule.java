package com.android.mvp2.api;

import android.app.Application;

import com.android.mvp2.api.service.HttpService;
import com.android.mvp2.data.model.HttpResult;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

@Module
public class HttpModule {

    public static final String BASE_URL = "https://api.douban.com/v2/movie/";
    private static final int DEFAULT_TIMEOUT = 60 * 1000;

    @Provides
    public OkHttpClient provideOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.setReadTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);
        return okHttpClient;
    }

    @Provides
    public Retrofit provideRetrofit(Application application, OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder() //
                .baseUrl(BASE_URL) // application.getString(R.string.api_github)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 添加Rx适配器
                .addConverterFactory(GsonConverterFactory.create()) // 添加Gson转换器
                .client(okHttpClient).build();
        return retrofit;
    }

    @Provides
    @Singleton
    protected HttpService provideHttpService(Retrofit retrofit) {

        return retrofit.create(HttpService.class);
    }

}