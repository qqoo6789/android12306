package ruijie.com.my12306.api.Ticket;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import ruijie.com.my12306.bean.BaseData;
import ruijie.com.my12306.bean.CheciData;
import ruijie.com.my12306.bean.CheciInfo;
import ruijie.com.my12306.bean.LoginData;
import ruijie.com.my12306.bean.Station;
import ruijie.com.my12306.bean.StationInfo;
import ruijie.com.my12306.bean.TicketData;
import ruijie.com.my12306.bean.TicketInfo;
import rx.Observable;

/**
 * Created by Administrator on 2016/9/2.
 */

public interface TicketService {

    @FormUrlEncoded
    @POST("getCheciInfo")
    Observable<CheciInfo> getCheciInfo(@Field("endsite")String endsite, @Field("startsite") String startsite
            , @Field("type") String type, @Field("startdate")String startdate);

    @FormUrlEncoded
    @POST("search_passstation")
    Observable<StationInfo> getPassstation(@Field("cid")Integer cid);

    @FormUrlEncoded
    @POST("getTicketInfo")
    Observable<TicketInfo> getTicketInfo(@Field("startsite") String startsite, @Field("endsite")String endsite,
                                         @Field("type") String type, @Field("startdate")String startdate);


    @FormUrlEncoded
    @POST("insertOrder")
    Observable<BaseData> insertOrder(@Field("cid") Integer cid, @Field("uid")Integer uid
            , @Field("onumber") Integer onumber, @Field("price")Double price
            , @Field("status") Integer status, @Field("ztype") Integer ztype
            , @Field("startsite") String startsite, @Field("endsite") String endsite
            , @Field("znumber") String znumber, @Field("carriagenumber") Integer carriagenumber
            , @Field("ttype") Integer ttype, @Field("mileage") Double mileage
            , @Field("odate") String odate);

    @FormUrlEncoded
    @POST("insertTicket")
    Observable<BaseData> insertTicket(@Field("cid") Integer cid, @Field("zid")Integer zid
            , @Field("sidStart") Integer sidStart, @Field("sidEnd")Integer sidEnd
            , @Field("codenumberStart") Integer codenumberStart, @Field("codenumberEnd")Integer codenumberEnd
            , @Field("status") Integer status, @Field("ztype") Integer ztype);

}
