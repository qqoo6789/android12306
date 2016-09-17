package ruijie.com.my12306.ui.base;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;

import ruijie.com.my12306.R;
import ruijie.com.my12306.injector.HasComponent;
import ruijie.com.my12306.ui.main.MainActivity;
import ruijie.com.my12306.util.ResourceUtil;
import ruijie.com.my12306.widget.ProgressBarCircularIndeterminate;
import ruijie.com.my12306.widget.ProgressFragment;

/**
 * Created by Administrator on 2016/8/17.
 */

public abstract class BaseFragment extends ProgressFragment{

    private TextView tvError,tvEmpty,tvLoading;
    private Button btnReload;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initInjector();
        getBundle(getArguments());
        initUI(view);
        initData();
        super.onViewCreated(view, savedInstanceState);
    }

    public abstract void initInjector();

    public abstract int initContentView();

    /**
     * 得到Activity传进来的值
     */
    public abstract void getBundle(Bundle bundle);

    /**
     * 初始化控件
     */
    public abstract void initUI(View view);

    /**
     * 在监听器之前把数据准备好
     */
    public abstract void initData();

    @Override public View onCreateContentErrorView(LayoutInflater inflater) {
        View error = inflater.inflate(R.layout.error_view_layout, null);
        tvError = (TextView) error.findViewById(R.id.tvError);
        error.findViewById(R.id.btnReload).setOnClickListener(view -> onReloadClicked());
        return error;
    }

    @Override public View onCreateContentView(LayoutInflater inflater) {
        return inflater.inflate(initContentView(), null);
    }

    @Override public View onCreateContentEmptyView(LayoutInflater inflater) {
        View empty = inflater.inflate(R.layout.empty_view_layout, null);
        tvEmpty = (TextView) empty.findViewById(R.id.tvEmpty);
        btnReload = (Button) empty.findViewById(R.id.btnReload);
        empty.findViewById(R.id.btnReload).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                onReloadClicked();
            }
        });
        return empty;
    }

    @Override public View onCreateProgressView(LayoutInflater inflater) {
        View loading = inflater.inflate(R.layout.loading_view_layout, null);
        tvLoading = (TextView) loading.findViewById(R.id.tvLoading);
        ProgressBarCircularIndeterminate progressBar =
                (ProgressBarCircularIndeterminate) loading.findViewById(R.id.progress_view);
        progressBar.setBackgroundColor(ResourceUtil.getThemeColor(getActivity()));
        return loading;
    }

    public void setErrorText(String text) {
        tvError.setText(text);
    }

    public void setErrorText(int textResId) {
        setErrorText(getString(textResId));
    }

    public void setEmptyText(String text) {
        tvEmpty.setText(text);
    }

    public void setEmptyButtonVisible(int visible) {
        btnReload.setVisibility(visible);
    }

    public void setEmptyText(int textResId) {
        setEmptyText(getString(textResId));
    }

    public void setLoadingText(String text) {
        tvLoading.setText(text);
    }

    public void setLoadingText(int textResId) {
        setLoadingText(getString(textResId));
    }

    //Override this to reload
    public void onReloadClicked() {

    }

    @SuppressWarnings("unchecked") protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }

    public void hideViews(AppBarLayout appBarLayout) {
        appBarLayout.animate().translationY(-appBarLayout.getHeight()).setInterpolator(new LinearInterpolator()).setDuration(250);
    }

    public void showViews(AppBarLayout appBarLayout) {
        appBarLayout.animate().translationY(0).setInterpolator(new LinearInterpolator()).setDuration(250);
    }
}
