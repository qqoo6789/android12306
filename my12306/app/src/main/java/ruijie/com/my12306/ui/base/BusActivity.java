package ruijie.com.my12306.ui.base;

import android.os.Bundle;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by Administrator on 2016/8/19.
 */

public abstract class BusActivity extends BaseActivity{

    public Subscription rxSubscription;

    @Override protected void onDestroy() {
        super.onDestroy();
        if(rxSubscription!=null&&rxSubscription.isUnsubscribed()){
            rxSubscription.unsubscribe();
        }
    }
}
