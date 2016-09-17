package ruijie.com.my12306.ui.booking.orderActivty;

import dagger.Component;
import ruijie.com.my12306.injector.PerActivity;
import ruijie.com.my12306.injector.component.ApplicationComponent;
import ruijie.com.my12306.injector.moudel.ActivityMoudle;
import ruijie.com.my12306.ui.booking.searchActivity.BookingSearchActivity;

/**
 * Created by Administrator on 2016/8/17.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = ActivityMoudle.class)
public interface BookingOrderComponent {

    void inject(BookingOrderActivity bookingOrderActivity);
}
