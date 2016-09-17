package ruijie.com.my12306.api.Ticket;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import ruijie.com.my12306.api.User.UserService;
import ruijie.com.my12306.bean.BaseData;
import ruijie.com.my12306.bean.CheciData;
import ruijie.com.my12306.bean.CheciInfo;
import ruijie.com.my12306.bean.Station;
import ruijie.com.my12306.bean.StationInfo;
import ruijie.com.my12306.bean.TicketData;
import ruijie.com.my12306.bean.TicketInfo;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/9/2.
 */

public class TicketApi {

    static final String BASE_URL = "http://172.18.0.139:8080/my12306/ticket/";

    private TicketService ticketService;

    public TicketApi(OkHttpClient okHttpClient){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ticketService = retrofit.create(TicketService.class);
    }

    public Observable<CheciInfo> getCheciInfo(String endsite, String startsite
            , String type, String startdate){
        return ticketService.getCheciInfo(endsite,startsite,type,startdate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<StationInfo> getPassstation(Integer cid){
        return ticketService.getPassstation(cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<TicketInfo> getTicketInfo(String startsite, String endsite, String type, String startdate){
        return ticketService.getTicketInfo(startsite,endsite,type,startdate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public Observable<BaseData> insertOrder(Integer cid, @Field("uid")Integer uid
            , Integer onumber,Double price
            , Integer status,Integer ztype
            , String startsite,String endsite
            , String znumber, Integer carriagenumber
            , Integer ttype,Double mileage
            , String odate){
        return ticketService.insertOrder(cid,uid,onumber,price,status,ztype,startsite,
                endsite,znumber,carriagenumber,ttype,mileage,odate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BaseData> insertTicket(Integer cid,Integer zid
            , Integer sidStart,Integer sidEnd
            ,Integer codenumberStart,Integer codenumberEnd
            ,Integer status, Integer ztype){
        return ticketService.insertTicket(cid,zid,sidStart,sidEnd,codenumberStart,codenumberEnd,status,ztype)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
