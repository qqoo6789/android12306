package ruijie.com.my12306.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import rx.Subscription;

/**
 * Created by prj on 2016/8/22.
 */

public abstract class BusFragment extends BaseFragment{

    public Subscription rxSubscription;

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(rxSubscription!=null&&rxSubscription.isUnsubscribed()){
            rxSubscription.unsubscribe();
        }
    }
}
