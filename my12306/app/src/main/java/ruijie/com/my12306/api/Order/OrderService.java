package ruijie.com.my12306.api.Order;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import ruijie.com.my12306.bean.CheciInfo;
import ruijie.com.my12306.bean.OrderData;
import ruijie.com.my12306.bean.OrderInfo;
import rx.Observable;

/**
 * Created by yum on 2016/9/3.
 */
//http://172.18.0.139:8080/my12306/order/search_today?uid=1&odate=2016-9-12
public interface OrderService {
    @FormUrlEncoded
    @POST("search_today")
    Observable<OrderInfo> getOrderTodayInfo(@Field("uid") int uid, @Field("odate") String startsite);
    @FormUrlEncoded
    @POST("search_history")
    Observable<OrderInfo> getOrderHistoryInfo(@Field("uid") int uid, @Field("odate") String startsite);
    @FormUrlEncoded
    @POST("search_future")
    Observable<OrderInfo> getOrderFutureInfo(@Field("uid") int uid, @Field("odate") String startsite);
    @FormUrlEncoded
    @POST("search_uncompleted")
    Observable<OrderInfo> getOrderUncompletedInfo(@Field("uid") int uid, @Field("odate") String startsite);

}
