package ruijie.com.my12306.ui.booking.searchActivity;

import dagger.Component;
import ruijie.com.my12306.injector.PerActivity;
import ruijie.com.my12306.injector.component.ApplicationComponent;
import ruijie.com.my12306.injector.moudel.ActivityMoudle;
import ruijie.com.my12306.ui.login.LoginActivity;

/**
 * Created by Administrator on 2016/8/17.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = ActivityMoudle.class)
public interface BookingSearchComponent {

    void inject(BookingSearchActivity bookingSearchActivity);
}
