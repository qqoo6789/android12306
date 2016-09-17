package ruijie.com.my12306.api.User;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import ruijie.com.my12306.bean.BaseData;
import ruijie.com.my12306.bean.Link;
import ruijie.com.my12306.bean.LinkInfoData;
import ruijie.com.my12306.bean.LoginData;
import ruijie.com.my12306.bean.User;
import ruijie.com.my12306.bean.UserInfoData;
import rx.Observable;

/**
 * Created by Administrator on 2016/9/1.
 */

public interface UserService {

    @FormUrlEncoded
    @POST("login")
    Observable<LoginData> login(@Field("username")String username, @Field("password")String password);

    @FormUrlEncoded
    @POST("register")
    Observable<BaseData> register(@Field("username")String username, @Field("password")String password
            ,@Field("nickname")String nickname,@Field("idcard")String idcard
            , @Field("email")String email,@Field("identity")String identity,@Field("phone")String phone);
    /*@POST("register")
    Observable<BaseData> register(@Body User user);*/

    @FormUrlEncoded
    @POST("quit")
    Observable<BaseData> quit(@Field("uid")String uid, @Field("token")String token);

    @FormUrlEncoded
    @POST("search_linkman")
    Observable<LinkInfoData> search_linkman(@Field("uid")String uid, @Field("token")String token);

    @FormUrlEncoded
    @POST("search_personalinfo")
    Observable<UserInfoData> search_personalinfo(@Field("uid")String uid, @Field("token")String token);

    @FormUrlEncoded
    @POST("update_personalinfo")
    Observable<BaseData> update_personalinfo(@Body User user);

    @FormUrlEncoded
    @POST("add_linkman")
    Observable<BaseData> add_linkman(@Field("uid")String uid, @Body Link link);
}
