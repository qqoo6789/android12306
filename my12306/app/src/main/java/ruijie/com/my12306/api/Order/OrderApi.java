package ruijie.com.my12306.api.Order;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import ruijie.com.my12306.api.Ticket.TicketService;
import ruijie.com.my12306.bean.OrderInfo;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yum on 2016/9/3.
 */

public class OrderApi{
    static final String BASE_URL = "http://172.18.0.139:8080/my12306/order/";
    private OrderService orderService;

    public OrderApi(OkHttpClient okHttpClient){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        orderService = retrofit.create(OrderService.class);
    }

    public Observable<OrderInfo> getOrderTodayInfo(int uid,  String startdate) {
        return orderService.getOrderTodayInfo(uid,startdate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public Observable<OrderInfo> getOrderHistoryInfo(int uid, String startdate) {
        return orderService.getOrderHistoryInfo(uid,startdate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<OrderInfo> getOrderFutureInfo(int uid,  String startdate) {
        return orderService.getOrderFutureInfo(uid,startdate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<OrderInfo> getOrderUncompletedInfo( int uid, String startdate) {
        return orderService.getOrderUncompletedInfo(uid,startdate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
