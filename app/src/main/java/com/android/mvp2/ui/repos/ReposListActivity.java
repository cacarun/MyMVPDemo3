package com.android.mvp2.ui.repos;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.mvp2.R;
import com.android.mvp2.base.BaseActivity;
import com.android.mvp2.base.BaseComponent;
import com.android.mvp2.data.model.Subject;
import com.android.mvp2.ui.ActivityComponent;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import rx.Subscriber;

public class ReposListActivity extends BaseActivity implements ReposContract.View {

    private static final String TAG = ReposListActivity.class.getName();

    @Bind(R.id.click_me)
    TextView mTVClickMe;

    @Bind(R.id.repos_rv_list)
    RecyclerView mRvList;

    @Bind(R.id.pbLoading)
    ProgressBar pbLoading;


//    @Inject
//    HttpService httpService;

    @Inject
    ReposPresenter reposPresenter;

    @Inject
    ListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    @Override
    protected void injectDagger(@NonNull BaseComponent component) {
        ((ReposComponent)component).inject(this);
    }

    @NonNull
    @Override
    protected BaseComponent createComponent(@NonNull ActivityComponent component) {
        return component.plus(new ReposModule());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_repo_list;
    }

    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvList.setLayoutManager(manager);

        mRvList.setAdapter(mAdapter);
        loadData(mAdapter);
    }

    private void loadData(final ListAdapter adapter) {
        showLoading(true);

        reposPresenter.getData(new Subscriber<Subject>() {
            @Override
            public void onCompleted() {
                Toast.makeText(ReposListActivity.this, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                mTVClickMe.setText(e.getMessage());
            }

            @Override
            public void onNext(Subject subject) {
                mTVClickMe.setText(subject.toString());
            }
        });

    }

    private String getUser() {
        return "bird1015";
    }

    public void showLoading(boolean loading) {
        Log.e("info", loading + " Repos");
        pbLoading.setVisibility(loading ? View.VISIBLE : View.GONE);
    }

}