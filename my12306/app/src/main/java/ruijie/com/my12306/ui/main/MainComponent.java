package ruijie.com.my12306.ui.main;

import javax.inject.Singleton;

import dagger.Component;
import ruijie.com.my12306.injector.PerActivity;
import ruijie.com.my12306.injector.component.ApplicationComponent;
import ruijie.com.my12306.ui.booking.BookingFragment;
import ruijie.com.my12306.ui.login.LoginFragment;
import ruijie.com.my12306.ui.me.MeFragment;
import ruijie.com.my12306.ui.search.SearchFragment;

/**
 * Created by Administrator on 2016/8/18.
 */
@PerActivity @Component(dependencies = ApplicationComponent.class,modules = MainMoudle.class)
public interface MainComponent {

    void inject(MainActivity mainActivity);

    void inject(LoginFragment loginFragment);

    void inject(BookingFragment bookingFragment);

    void inject(SearchFragment searchFragment);

    void inject(MeFragment meFragment);
}

