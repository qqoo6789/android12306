package ruijie.com.my12306.ui.booking.orderActivty;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import ruijie.com.my12306.Constant;
import ruijie.com.my12306.api.Ticket.TicketApi;
import ruijie.com.my12306.bean.BaseData;
import ruijie.com.my12306.bean.TicketInfo;
import ruijie.com.my12306.ui.booking.searchActivity.BookingSearchContract;
import rx.Observable;
import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by prj on 2016/9/3.
 */

public class BookingOrderPresenter implements BookingOrderContract.Presenter{

    private BookingOrderContract.View mBookingOrderView;
    private Subscription mSubscription;
    private TicketApi ticketApi;

    @Inject
    public BookingOrderPresenter(TicketApi ticketApi){
        this.ticketApi = ticketApi;
    }

    @Override
    public void getTicketInfo(String startsite, String endsite, String type, String startdate) {
        mBookingOrderView.showLoading();
        mSubscription = ticketApi.getTicketInfo(startsite,endsite,type,startdate)
                .subscribe(ticketInfo -> {
                    if (ticketInfo.getStatus().equals(Constant.SUCCESS))
                        mBookingOrderView.getTicketInfo(ticketInfo.getData());
                    else {
                        mBookingOrderView.showError(ticketInfo.getMsg());
                        mBookingOrderView.dismissLoading();
                    }
                },throwable -> {
                    throwable.printStackTrace();
                    mBookingOrderView.dismissLoading();
                    mBookingOrderView.showError(throwable.toString());
                });
    }

    @Override
    public void insertOrder(Integer cid, Integer uid, Integer onumber, Double price,
                            Integer ztype, String startsite, String endsite,
                            String znumber, Integer carriagenumber, Integer ttype, Double mileage,
                            String odate, int zid, int sidEnd, int sidStart, int codenumberEnd,
                            int codenumberStart, int status) {
        mSubscription = Observable.zip(
                ticketApi.insertOrder(cid, uid, onumber, price, status, ztype, startsite,
                        endsite, znumber, carriagenumber, ttype, mileage, odate),
                ticketApi.insertTicket(cid, zid, sidStart, sidEnd, codenumberStart, codenumberEnd, status, ztype),
                (item1, item2) -> item1.getStatus().equals(Constant.SUCCESS)&&
                        item2.getStatus().equals(Constant.SUCCESS)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {
                    mBookingOrderView.dismissLoading();
                    if (aBoolean){
                        mBookingOrderView.showError("提交成功");
                    }else {
                        mBookingOrderView.showError("提交失败");
                    }
                },throwable -> {
                    throwable.printStackTrace();
                    mBookingOrderView.dismissLoading();
                    mBookingOrderView.showError(throwable.toString());
                });
    }

    @Override
    public void attachView(@NonNull BookingOrderContract.View View) {
        mBookingOrderView = View;
    }

    @Override
    public void detachView() {
        if(mSubscription!=null&&!mSubscription.isUnsubscribed()){
            mSubscription.unsubscribe();
        }
        mBookingOrderView = null;
    }
}
