package ruijie.com.my12306.ui.booking.searchActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ruijie.com.my12306.R;
import ruijie.com.my12306.bean.CheciData;
import ruijie.com.my12306.bean.Station;
import ruijie.com.my12306.event.ActivityEvent;
import ruijie.com.my12306.ui.base.BaseSwipeBackActivity;
import ruijie.com.my12306.ui.booking.orderActivty.BookingOrderActivity;
import ruijie.com.my12306.util.LoadMoreRecyclerView;
import ruijie.com.my12306.util.RxBus;
import ruijie.com.my12306.util.SnackbarUtils;
import rx.Subscription;
import rx.functions.Action1;

public class BookingSearchActivity extends BaseSwipeBackActivity implements BookingSearchContract.View {

    public Subscription rxSubscription;
    @Inject
    Context context;
    @Inject
    BookingSeatchPresenter mPresenter;
    BookingSearchComponent bookingSearchComponent;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.AppBarLayout)
    android.support.design.widget.AppBarLayout AppBarLayout;
    @Bind(R.id.textview_preday)
    TextView textviewPreday;
    @Bind(R.id.textview_afterday)
    TextView textviewAfterday;
    @Bind(R.id.textview_today)
    TextView textviewToday;
    @Bind(R.id.root)
    RelativeLayout root;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private MaterialDialog progressDialog;
    private ArrayList<CheciData> list;
    private ArrayList<MultiItemEntity> datalist;
    private ExpandableItemAdapter adapter;

    @Override
    protected int initContentView() {
        return R.layout.activity_bookingserch;
    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return true;
    }

    @Override
    public void initInjector() {
        bookingSearchComponent = DaggerBookingSearchComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityMoudle(getActivityMoudle())
                .build();
        bookingSearchComponent.inject(this);
    }

    @Override
    public void initUiAndListener() {
        ButterKnife.bind(this);

        mPresenter.attachView(this);
        list = (ArrayList<CheciData>) getIntent().getSerializableExtra("list");
        String from = getIntent().getStringExtra("from");
        String to = getIntent().getStringExtra("to");
        String date = getIntent().getStringExtra("date");
        initToolBar(toolbar,from+"<>"+to);
        textviewToday.setText(date);

        datalist = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            CheciData checiData = list.get(i);
            mPresenter.getPassStation(checiData.getCid(),i);
            datalist.add(checiData);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ExpandableItemAdapter(context,datalist);
        adapter.openLoadAnimation();
        recyclerView.setAdapter(adapter);

        progressDialog  = new MaterialDialog
                .Builder(this)
                .title("提示")
                .content("正在查询，请稍后...")
                .progress(true, 0)
                .build();

        rxSubscription = RxBus.getDefault().toObservable(ActivityEvent.class)
                .subscribe(activityEvent -> {
                    Intent i = new Intent(context,BookingOrderActivity.class);
                    i.putExtra("data",activityEvent.getCheciData());
                    startActivity(i);
                });
    }

    @Override
    protected boolean isApplyStatusBarColor() {
        return true;
    }

    @OnClick({R.id.textview_preday, R.id.textview_afterday, R.id.recyclerView})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textview_preday:
                break;
            case R.id.textview_afterday:
                break;
            case R.id.recyclerView:
                break;
        }
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
    public void showPassStation(List<Station> list,int position) {

            for (int i = 0; i < list.size(); i++) {
                ((CheciData) datalist.get(position)).addSubItem(list.get(i));
            }
/*            adapter.notifyDataSetChanged();
            adapter.expand(position);*/
    }

    @Override
    public void showError(String Error) {
        SnackbarUtils.show(recyclerView,Error,0,null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
        if(rxSubscription!=null&&rxSubscription.isUnsubscribed()){
            rxSubscription.unsubscribe();
        }
    }
}
