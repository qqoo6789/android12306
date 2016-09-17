package ruijie.com.my12306.injector;

/**
 * Created by Administrator on 2016/8/16.
 */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Scope @Retention(RetentionPolicy.RUNTIME)public @interface PerActivity {

}
