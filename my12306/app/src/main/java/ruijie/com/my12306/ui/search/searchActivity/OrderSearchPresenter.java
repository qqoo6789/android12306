package ruijie.com.my12306.ui.search.searchActivity;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import ruijie.com.my12306.Constant;
import ruijie.com.my12306.api.Order.OrderApi;
import ruijie.com.my12306.bean.OrderInfo;
import rx.Observable;
import rx.Subscription;

/**
 * Created by yum on 2016/9/3.
 */

public class OrderSearchPresenter implements OrderSearchContract.Presenter {
    OrderSearchContract.View ordersearchView;
    private Subscription mSubscription;
    private OrderApi orderApi;
    @Inject
    public OrderSearchPresenter(OrderApi orderApi){
        this.orderApi = orderApi;
    }
    @Override
    public void attachView(@NonNull OrderSearchContract.View View) {
        ordersearchView = View;
    }

    @Override
    public void detachView() {
        if(mSubscription!=null&&!mSubscription.isUnsubscribed()){
            mSubscription.unsubscribe();
        }
        ordersearchView = null;
    }


    @Override
    public void getOrderInfo(int uid, String startdate,int action) {
        ordersearchView.showLoading();
        Observable<OrderInfo> observable = null;
        switch (action){
            case 1:observable = orderApi.getOrderTodayInfo(uid,startdate);break;
            case 2:observable = orderApi.getOrderHistoryInfo(uid,startdate);break;
            case 3:observable = orderApi.getOrderFutureInfo(uid,startdate);break;
            case 4:observable = orderApi.getOrderUncompletedInfo(uid,startdate);break;
        }

        mSubscription = observable
                .subscribe(orderInfo -> {
                    if(orderInfo.getStatus().equals(Constant.SUCCESS))
                        ordersearchView.showOrderSearchData(orderInfo.getData());
                    else
                        ordersearchView.showError(orderInfo.getMsg());
                    ordersearchView.dismissLoading();
                },throwable -> {
                    throwable.printStackTrace();
                    ordersearchView.dismissLoading();
                    ordersearchView.showError(throwable.toString());
                });
    }
}
