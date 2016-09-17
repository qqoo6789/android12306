package ruijie.com.my12306.ui.booking;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import ruijie.com.my12306.R;
import ruijie.com.my12306.bean.AddressItem;
import ruijie.com.my12306.bean.CheciData;
import ruijie.com.my12306.bean.User;
import ruijie.com.my12306.event.addressEvent;
import ruijie.com.my12306.event.calendarEvent;
import ruijie.com.my12306.ui.base.BusFragment;
import ruijie.com.my12306.ui.booking.searchActivity.BookingSearchActivity;
import ruijie.com.my12306.ui.main.MainActivity;
import ruijie.com.my12306.ui.main.MainComponent;
import ruijie.com.my12306.util.HidingScrollListener;
import ruijie.com.my12306.util.RxBus;
import ruijie.com.my12306.util.SnackbarUtils;
import ruijie.com.my12306.util.TextUtil;
import ruijie.com.my12306.widget.AddressSelectLayout;
import ruijie.com.my12306.widget.calendarSelector.CalendarSelectorActivity;
import ruijie.com.my12306.widget.FlowLayout;
import ruijie.com.my12306.widget.citySelector.CitySelecterActivity;

/**
 * Created by Administrator on 2016/8/18.
 */
public class BookingFragment extends BusFragment implements BookingContact.View,View.OnClickListener {

    @Inject
    Context context;
    @Inject
    MainActivity mainActivity;
    @Inject
    BookingPresenter presenter;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.AppBarLayout)
    AppBarLayout appBarLayout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private FlowLayout flowlayout;
    private FlowLayout fl_customer;
    private AddressSelectLayout addressLayout;
    private Button bt_start_time;
    private Button bt_start_date;
    private Button bt_seat_type;
    private Button bt_customer_type;
    private Button bt_search;
    private String[] type;
    private String[] customer;
    private View deleteView;
    private boolean[] check;
    private View root;
    private LayoutInflater inflater;
    private View headView;
    private MaterialDialog deleteDialog;
    private MaterialDialog customerDialog;
    private MaterialDialog timeDialog;
    private MaterialDialog seatTypeDialog;
    private MaterialDialog progressDialog;
    private BookingAdapter bookingAdapter;
    private List<AddressItem> list;
    public static BookingFragment instance;

    public static BookingFragment getInstance() {
        if (instance == null) {
            synchronized (BookingFragment.class) {
                if (instance == null)
                    instance = new BookingFragment();
            }
        }
        return instance;
    }

    public BookingFragment() {
    }

    @Override
    public void initInjector() {
        getComponent(MainComponent.class).inject(this);
    }

    @Override
    public int initContentView() {
        return R.layout.fragment_booking;
    }

    @Override
    public void getBundle(Bundle bundle) {

    }

    @Override
    public void initUI(View view) {
        ButterKnife.bind(this, view);
        presenter.attachView(this);
        root = view;

        mainActivity.setSupportActionBar(toolbar);
        mainActivity.setTitle("车票预订");
        inflater = mainActivity.getLayoutInflater();
        headView = inflater.inflate(R.layout.layout_booking_head, null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        headView.setLayoutParams(lp);

        addressLayout = (AddressSelectLayout) headView.findViewById(R.id.addressLayout);
        flowlayout = (FlowLayout) headView.findViewById(R.id.flowlayout);
        fl_customer = (FlowLayout) headView.findViewById(R.id.fl_customer);
        bt_start_date = (Button) headView.findViewById(R.id.bt_start_date);
        bt_start_time = (Button) headView.findViewById(R.id.bt_start_time);
        bt_seat_type = (Button) headView.findViewById(R.id.bt_seat_type);
        bt_customer_type = (Button) headView.findViewById(R.id.bt_customer_type);
        bt_search = (Button) headView.findViewById(R.id.bt_search);

        addressLayout.OnAddressSelectListener(new AddressSelectLayout.OnAddressSelectListener() {
            @Override
            public void OnAddressFromClick() {
                Intent i = new Intent(mainActivity, CitySelecterActivity.class);
                i.putExtra("isFrom",true);
                startActivity(i);
                mainActivity.overridePendingTransition(0,0);
            }

            @Override
            public void OnAddressToClick() {
                Intent i = new Intent(mainActivity, CitySelecterActivity.class);
                i.putExtra("isFrom",false);
                startActivity(i);
                mainActivity.overridePendingTransition(0,0);
            }
        });
        bt_start_date .setOnClickListener(this);
        bt_start_time.setOnClickListener(this);
        bt_seat_type.setOnClickListener(this);
        bt_customer_type.setOnClickListener(this);
        bt_search.setOnClickListener(this);
        showContent(true);
    }

    @Override
    public void initData() {
        //recyclerview先初始化，将上面布局作为head加入recyclerview
        AddressItem addressItem = new AddressItem("北京","上海");
        list = new ArrayList<>();
        for (int i=0;i<10;i++)
            list.add(addressItem);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        bookingAdapter = new BookingAdapter(list);
        bookingAdapter.addHeaderView(headView);
        // bookingAdapter.openLoadAnimation();
        recyclerView.setAdapter(bookingAdapter);
        recyclerView.setOnScrollListener(new HidingScrollListener() {
            @Override
            public void onHide() {
                hideViews(appBarLayout);
            }
            @Override
            public void onShow() {
                showViews(appBarLayout);
            }
        });

        /*bookingAdapter.setOnRecyclerViewItemClickListener((view, i) -> {
            AddressItem item = bookingAdapter.getItem(i);
            addressLayout.setAddress(item.getFrom(),item.getTo());
        });*/
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                AddressItem item = bookingAdapter.getItem(i);
                addressLayout.setAddress(item.getFrom(),item.getTo());
            }
        });

        //seatFlowLayout;
        type = new String[]{"全部", "G/D/C", "Z字头", "T字头", "K字头", "其他"};
        check = new boolean[]{true, false, false, false, false, false};
        for (int i = 0; i < type.length; i++) {
            TextView tv = (TextView) inflater.inflate(R.layout.tv, flowlayout, false);
            tv.setText(type[i]);
            flowlayout.setTag(i);
            flowlayout.addView(tv);
            int finalI = i;
            tv.setOnClickListener(view -> presenter.onSeatClick(check, finalI));
        }
        presenter.onSeatClick(check, 0);

        //customFlowLayout
        TextView tv = (TextView) inflater.inflate(R.layout.tv_add, flowlayout, false);
        tv.setText("⊕乘客");
        fl_customer.setTag(0);
        fl_customer.addView(tv);
        fl_customer.setOnClickListener(view -> presenter.onCustomClick(new ArrayList<User>()));

        rxSubscription = RxBus.getDefault().toObservable(calendarEvent.class)
                .subscribe(calendarEvent -> {
                    bt_start_date.setText(calendarEvent.getDate());
                });
        rxSubscription = RxBus.getDefault().toObservable(addressEvent.class)
                .subscribe(addressEvent -> {
                    if(addressEvent.isFrom)
                        addressLayout.setTv_from(addressEvent.getAddress());
                    else
                        addressLayout.setTv_to(addressEvent.getAddress());
                });

        deleteDialog = new MaterialDialog
                .Builder(getContext())
                .title("温馨提示")
                .content("确定删除该乘客吗?")
                .positiveText("确定")
                .negativeText("取消")
                .onPositive((dialog1, which) -> {
                    fl_customer.removeView(deleteView);
                    dialog1.dismiss();
                })
                .build();

        timeDialog = new MaterialDialog
                .Builder(getContext())
                .title("出发时间")
                .items(R.array.time)
                .itemsCallbackSingleChoice(0, (dialog, itemView, which, text) -> {
                    bt_start_time.setText(text);
                    timeDialog.dismiss();
                    return true;
                })
                .build();

        seatTypeDialog = new MaterialDialog
                .Builder(getContext())
                .title("席别")
                .items(R.array.seat_type)
                .itemsCallbackSingleChoice(0, (dialog, itemView, which, text) -> {
                    bt_seat_type.setText(text);
                    seatTypeDialog.dismiss();
                    return true;
                })
                .build();
        
        customerDialog = new MaterialDialog
                .Builder(getContext())
                .title("顾客类型")
                .items(R.array.customer_type)
                .itemsCallbackSingleChoice(0, (dialog, itemView, which, text) -> {
                    bt_customer_type.setText(text);
                    customerDialog.dismiss();
                    return true;
                })
                .build();

        progressDialog  = new MaterialDialog
                .Builder(getContext())
                .content("正在查询，请稍后...")
                .progress(true, 0)
                .build();
    }

    @Override
    public void showLoading() {
        if (!mainActivity.isFinishing() && !progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void dimissLoading() {
        if (!mainActivity.isFinishing() && progressDialog.isShowing())
            progressDialog.dismiss();
    }

    @Override
    public void showError(String error) {
        SnackbarUtils.show(root,error,0,null);
    }

    @Override
    public void getCheck(boolean[] check) {
        for (int i = 0; i < check.length; i++) {
            if (check[i]) {
                if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
                    flowlayout.getChildAt(i).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                else
                    flowlayout.getChildAt(i).setBackground(getResources().getDrawable(R.drawable.item_select_check));
                ((TextView) flowlayout.getChildAt(i)).setTextColor(getResources().getColor(R.color.md_white));
            } else {
                flowlayout.getChildAt(i).setBackground(getResources().getDrawable(R.drawable.item_select_uncheck));
                ((TextView) flowlayout.getChildAt(i)).setTextColor(getResources().getColor(R.color.base_text_black));
            }
        }
    }

    @Override
    public void addUser(ArrayList<User> list) {
        if (list != null)
            for (int i = 0; i < list.size(); i++) {
                TextView tv = (TextView) inflater.inflate(R.layout.tv_customer, fl_customer, false);
                tv.setText(list.get(i).getNickname());
                tv.setTag(i);
                fl_customer.addView(tv);
                tv.setOnClickListener(view -> {
                    deleteView = view;
                    deleteDialog.show();
                });
            }
    }

    @Override
    public void startTicketActivtyByList(List<CheciData> list,String from,String to) {
        Intent intent = new Intent(context, BookingSearchActivity.class);
        intent.putExtra("list",(Serializable)list);
        intent.putExtra("from",from);
        intent.putExtra("to",to);
        intent.putExtra("date",bt_start_date.getText().toString());
        startActivity(intent);
        SnackbarUtils.show(root,list.toString(),0,null);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_start_date:
                Intent i = new Intent(context, CalendarSelectorActivity.class);
                i.putExtra(CalendarSelectorActivity.DAYS_OF_SELECT, 60);
                i.putExtra(CalendarSelectorActivity.ORDER_DAY,TextUtil.ToSearch(bt_start_date));
                startActivity(i);
                getActivity().overridePendingTransition(0,0);
                break;
            case R.id.bt_start_time:
                timeDialog.show();
                break;
            case R.id.bt_seat_type:
                seatTypeDialog.show();
                break;
            case R.id.bt_customer_type:
                customerDialog.show();
                break;
            case R.id.bt_search:
                presenter.search(
                        addressLayout.getTv_from(), addressLayout.getTv_to(),
                        TextUtil.getText(bt_start_date),TextUtil.getText(bt_start_time),
                        TextUtil.getText(bt_seat_type),"",true);
                break;
        }
    }

}
