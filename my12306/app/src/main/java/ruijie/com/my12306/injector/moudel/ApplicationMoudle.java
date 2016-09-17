package ruijie.com.my12306.injector.moudel;

import android.content.Context;
import android.view.LayoutInflater;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import ruijie.com.my12306.components.okhttp.HttpLoggingInterceptor;
import ruijie.com.my12306.components.okhttp.OkHttpHelper;
import ruijie.com.my12306.components.retrofit.RequestHelper;
import ruijie.com.my12306.components.storage.UserStorage;

/**
 * Created by prj on 2016/8/15.
 */

@Module
public class ApplicationMoudle {

    private Context context;

    public ApplicationMoudle(Context context){
        this.context = context;
    }

    @Provides @Singleton
    public Context provideApplicationContext(){
        return context;
    }

    @Provides @Singleton @Named("api")
    OkHttpClient provideApiOkHttpClient(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS);
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);
        //builder.addInterceptor(mCookieInterceptor);
        return builder.build();
    }

    @Provides @Singleton
    OkHttpClient provideOkHttpClient(@Named("api") OkHttpClient mOkHttpClient) {
        OkHttpClient.Builder builder = mOkHttpClient.newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);
        builder.interceptors().clear();
        return builder.build();
    }

    @Provides @Singleton
    LayoutInflater provideLayoutInflater(Context context) {
        return (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Provides @Singleton
    UserStorage proviceUserStorage(Context mContext){
        return new UserStorage(mContext);
    }

    @Provides @Singleton
    RequestHelper proviceRequestHelper(Context mContext,UserStorage mUserStorage){
        return new RequestHelper(mContext,mUserStorage);
    }

    @Provides @Singleton
    OkHttpHelper provideOkHttpHelper(OkHttpClient mOkHttpClient) {
        return new OkHttpHelper(mOkHttpClient);
    }
}
