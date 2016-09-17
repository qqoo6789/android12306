package ruijie.com.my12306.ui.splash;

import dagger.Module;
import dagger.Provides;

/**
 * Created by prj on 2016/8/17.
 */
@Module
public class SplashMoudle {

    private SplashActivity splashActivity;

    public SplashMoudle(SplashActivity splashActivity){
        this.splashActivity = splashActivity;
    }
}
