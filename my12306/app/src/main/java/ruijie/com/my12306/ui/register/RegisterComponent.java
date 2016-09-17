package ruijie.com.my12306.ui.register;

import dagger.Component;
import ruijie.com.my12306.injector.PerActivity;
import ruijie.com.my12306.injector.component.ApplicationComponent;
import ruijie.com.my12306.injector.moudel.ActivityMoudle;

/**
 * Created by Administrator on 2016/8/26.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = ActivityMoudle.class)
public interface RegisterComponent {

    void inject(RegisterActivity registerActivity);
}
