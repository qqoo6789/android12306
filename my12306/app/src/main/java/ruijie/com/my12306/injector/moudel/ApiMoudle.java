package ruijie.com.my12306.injector.moudel;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import ruijie.com.my12306.api.Order.OrderApi;
import ruijie.com.my12306.api.Ticket.TicketApi;
import ruijie.com.my12306.api.User.UserApi;
import ruijie.com.my12306.components.retrofit.RequestHelper;

/**
 * Created by Administrator on 2016/8/16.
 */

@Module
public class ApiMoudle {

    @Provides @Singleton
    public UserApi proviceUserApi(@Named("api") OkHttpClient okHttpClient){
        return new UserApi(okHttpClient);
    }

    @Provides @Singleton
    public TicketApi proviceTicketApi(@Named("api") OkHttpClient okHttpClient){
        return new TicketApi(okHttpClient);
    }

    @Provides @Singleton
    public OrderApi proviceOrderApi(@Named("api") OkHttpClient okHttpClient){
        return new OrderApi(okHttpClient);
    }
}


