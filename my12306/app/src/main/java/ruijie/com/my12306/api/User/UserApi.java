package ruijie.com.my12306.api.User;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.PUT;
import ruijie.com.my12306.bean.BaseData;
import ruijie.com.my12306.bean.Link;
import ruijie.com.my12306.bean.LinkInfoData;
import ruijie.com.my12306.bean.LoginData;
import ruijie.com.my12306.bean.User;
import ruijie.com.my12306.bean.UserInfoData;
import ruijie.com.my12306.components.retrofit.RequestHelper;
import ruijie.com.my12306.util.RxUtil;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/9/1.
 */

public class UserApi {
    static final String BASE_URL = "http://172.18.0.139:8080/my12306/user/";

    private UserService userService;

    public UserApi(OkHttpClient okHttpClient){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        userService = retrofit.create(UserService.class);
    }

    public Observable<LoginData> login(String username, String password){
        return userService.login(username,password).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread());
    }

    /*public Observable<BaseData> register(User user){
        return userService.register(user).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }*/

    public Observable<BaseData> register(String username, String password
            ,String nickname,String idcard, String email,String identity,String phone){
        return userService.register(username,password,nickname,idcard,email,identity,phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BaseData> quit(String uid,String token){
        return userService.quit(uid,token).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<LinkInfoData> search_linkman(String uid, String token){
        return userService.search_linkman(uid,token).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread());
    }


    public Observable<UserInfoData> search_personalinfo(String uid, String token){
        return userService.search_personalinfo(uid,token).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread());
    }

    Observable<BaseData> update_personalinfo(User user){
        return userService.update_personalinfo(user).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread());
    }

    Observable<BaseData> add_linkman(String uid,Link link){
        return userService.add_linkman(uid,link).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread());
    }
}
