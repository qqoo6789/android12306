package ruijie.com.my12306.ui.search.searchActivity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.afollestad.materialdialogs.MaterialDialog;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import ruijie.com.my12306.MyApplication;
import ruijie.com.my12306.R;
import ruijie.com.my12306.bean.OrderData;
import ruijie.com.my12306.ui.base.BaseSwipeBackActivity;
import ruijie.com.my12306.util.LoadMoreRecyclerView;
import ruijie.com.my12306.util.SharedPreferencesUtil;
import ruijie.com.my12306.util.SnackbarUtils;

public class OrderSearchActivity extends BaseSwipeBackActivity implements OrderSearchContract.View {
    int intent_action;
    int intent_uid ;
    String intent_odate = "2016-9-12";
    String intent_title;
    @Inject
    Context context;
    @Inject
    OrderSearchPresenter presenter;
    OrderSearchComponent orderSearchComponent;
    List<OrderData> list;
    OrderSearchAdapter adapter;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.AppBarLayout)
    android.support.design.widget.AppBarLayout AppBarLayout;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private MaterialDialog progressDialog;

    @Override
    protected int initContentView() {
        return R.layout.activity_orderserch;
    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return false;
    }

    @Override
    public void initInjector() {
        orderSearchComponent = DaggerOrderSearchComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityMoudle(getActivityMoudle())
                .build();
        orderSearchComponent.inject(this);
    }

    @Override
    public void initUiAndListener() {
        ButterKnife.bind(this);
        //intent_uid = getIntent().getIntExtra("intent_uid", -1);
        //intent_odate = getIntent().getStringExtra("intent_odate");
        intent_action = getIntent().getIntExtra("intent_action", 1);
        intent_title = getIntent().getStringExtra("intent_title");

        presenter.attachView(this);
        initToolBar(toolbar,intent_title);
        list = new ArrayList<>();
        adapter = new OrderSearchAdapter(list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        progressDialog  = new MaterialDialog
                .Builder(this)
                .title("提示")
                .content("正在查询，请稍后...")
                .progress(true, 0)
                .build();
        intent_uid = Integer.parseInt(SharedPreferencesUtil.getString(MyApplication.context,"uid","-1"));
        presenter.getOrderInfo(intent_uid,intent_odate,intent_action);
    }

    @Override
    protected boolean isApplyStatusBarColor() {
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }


    @Override
    public void showLoading() {
        if (!isFinishing() && !progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void dismissLoading() {
        if (!isFinishing() && progressDialog.isShowing())
            progressDialog.dismiss();
    }

    @Override
    public void showOrderSearchData(List<OrderData> list) {
        /*for (int i=0;i<list.size();i++)
            this.list.add(list.get(i));


        this.list = list;*/
        adapter.setNewData(list);
        adapter.notifyDataSetChanged();
        SnackbarUtils.show(recyclerView,adapter.getData().toString(),0,null);
    }

    @Override
    public void showError(String Error) {

        SnackbarUtils.show(recyclerView,Error,0,null);
    }
}
