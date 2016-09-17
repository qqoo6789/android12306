package ruijie.com.my12306.ui.booking.orderActivty;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ruijie.com.my12306.R;
import ruijie.com.my12306.bean.CheciData;
import ruijie.com.my12306.bean.TicketData;
import ruijie.com.my12306.components.storage.UserStorage;
import ruijie.com.my12306.ui.base.BaseSwipeBackActivity;
import ruijie.com.my12306.util.SnackbarUtils;

/**
 * Created by prj on 2016/9/3.
 */

public class BookingOrderActivity extends BaseSwipeBackActivity implements BookingOrderContract.View{

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.textview_preday)
    TextView textviewPreday;
    @Bind(R.id.textview_afterday)
    TextView textviewAfterday;
    @Bind(R.id.root)
    RelativeLayout root;
    @Bind(R.id.AppBarLayout)
    android.support.design.widget.AppBarLayout AppBarLayout;
    @Bind(R.id.textview_from_city)
    TextView textviewFromCity;
    @Bind(R.id.textview_from_time)
    TextView textviewFromTime;
    @Bind(R.id.textview_cnumber)
    TextView textviewCnumber;
    @Bind(R.id.textview_sum_time)
    TextView textviewSumTime;
    @Bind(R.id.linearlayout_center)
    LinearLayout linearlayoutCenter;
    @Bind(R.id.textview_to_time)
    TextView textviewToTime;
    @Bind(R.id.textview_to_city)
    TextView textviewToCity;
    @Bind(R.id.textview_type1)
    TextView textviewType1;
    @Bind(R.id.textview_type1_number)
    TextView textviewType1Number;
    @Bind(R.id.textview_type1_price)
    TextView textviewType1Price;
    @Bind(R.id.textview_type2)
    TextView textviewType2;
    @Bind(R.id.textview_type2_number)
    TextView textviewType2Number;
    @Bind(R.id.textview_type2_price)
    TextView textviewType2Price;
    @Bind(R.id.textview_type3)
    TextView textviewType3;
    @Bind(R.id.textview_type3_number)
    TextView textviewType3Number;
    @Bind(R.id.textview_type3_price)
    TextView textviewType3Price;
    @Bind(R.id.bt_search)
    Button btSearch;
    @Bind(R.id.textview_today)
    TextView textviewToday;
    private CheciData checiData;
    private LinearLayout ll;
    @Inject
    BookingOrderPresenter orderPresenter;
    @Inject
    Context context;
    @Inject
    UserStorage userStorage;
    BookingOrderComponent component;
    private MaterialDialog progressDialog;

    @Override
    protected int initContentView() {
        return R.layout.activity_booking_confirm;
    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return true;
    }

    @Override
    public void initInjector() {
        component = DaggerBookingOrderComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityMoudle(getActivityMoudle())
                .build();
        component.inject(this);
    }

    @Override
    public void initUiAndListener() {
        ButterKnife.bind(this);
        orderPresenter.attachView(this);
        ll = (LinearLayout) findViewById(R.id.ll);
        initToolBar(toolbar, "确认订单");

        checiData = (CheciData) getIntent().getSerializableExtra("data");
        textviewToday.setText(checiData.getStartdate());
        textviewFromCity.setText(checiData.getStartsite());
        textviewToCity.setText(checiData.getEndsite());
        textviewCnumber.setText(checiData.getCodenumber());
        textviewSumTime.setText("1小时20分");

        progressDialog  = new MaterialDialog
                .Builder(this)
                .title("提示")
                .content("正在查询，请稍后...")
                .progress(true, 0)
                .build();
    }

    @Override
    protected boolean isApplyStatusBarColor() {
        return true;
    }

    @OnClick(R.id.bt_search)
    public void onClick() {
        orderPresenter.getTicketInfo(checiData.getStartsite(),checiData.getEndsite(),
                checiData.getType(),checiData.getStartdate());
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
    public void showError(String error) {
        SnackbarUtils.show(ll,error,0,null);
    }

    @Override
    public void getTicketInfo(TicketData ticketData) {
        /*orderPresenter.insertOrder(ticketData.getCid(),Integer.parseInt(userStorage.getUid()),111,50.5,
                Integer.parseInt(ticketData.getZtype()),
                checiData.getStartsite(),checiData.getEndsite(),ticketData.getCodenumber(),
                Integer.parseInt(ticketData.getCarriagenumber()),Integer.parseInt(checiData.getType()),
                20.5,checiData.getStartdate(),
                ticketData.getZid(),ticketData.getSid_end(),ticketData.getSid_start(),
                ticketData.getCodenumber_end(),ticketData.getCodenumber_start(),1);*/
        orderPresenter.insertOrder(ticketData.getCid(),6,111,50.5,
                Integer.parseInt(ticketData.getZtype()),
                checiData.getStartsite(),checiData.getEndsite(),ticketData.getCodenumber(),
                Integer.parseInt(ticketData.getCarriagenumber()),Integer.parseInt(checiData.getType()),
                20.5,checiData.getStartdate(),
                ticketData.getZid(),ticketData.getSid_end(),ticketData.getSid_start(),
                ticketData.getCodenumber_end(),ticketData.getCodenumber_start(),1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        orderPresenter.detachView();
    }
}
