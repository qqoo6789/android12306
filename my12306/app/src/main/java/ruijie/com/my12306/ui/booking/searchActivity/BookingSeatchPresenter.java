package ruijie.com.my12306.ui.booking.searchActivity;

import android.support.annotation.NonNull;
import android.util.Log;

import javax.inject.Inject;

import ruijie.com.my12306.Constant;
import ruijie.com.my12306.api.Ticket.TicketApi;
import ruijie.com.my12306.api.Ticket.TicketService;
import ruijie.com.my12306.bean.StationInfo;
import ruijie.com.my12306.injector.PerActivity;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by prj on 2016/8/21.
 */
@PerActivity
public class BookingSeatchPresenter implements BookingSearchContract.Presenter{

    private BookingSearchContract.View mBookingSearchView;
    private Subscription mSubscription;
    private TicketApi ticketApi;

    @Inject
    public BookingSeatchPresenter(TicketApi ticketApi){
        this.ticketApi = ticketApi;
    }

    @Override
    public void attachView(@NonNull BookingSearchContract.View view) {
        mBookingSearchView = view;
    }

    @Override
    public void detachView() {
        if(mSubscription!=null&&!mSubscription.isUnsubscribed()){
            mSubscription.unsubscribe();
        }
        mBookingSearchView = null;
    }

    @Override
    public void getPassStation(int cid,int position) {
        //mBookingSearchView.showLoading();
        mSubscription = ticketApi.getPassstation(cid)
                .subscribe(stationInfo -> {
                    if(stationInfo.getStatus().equals(Constant.SUCCESS))
                         mBookingSearchView.showPassStation(stationInfo.getData(),position);
                    else
                        mBookingSearchView.showError(stationInfo.getMsg());
                    //mBookingSearchView.dismissLoading();
                },throwable -> {
                    throwable.printStackTrace();
                    //mBookingSearchView.dismissLoading();
                    mBookingSearchView.showError(throwable.toString());
                });
    }
}
