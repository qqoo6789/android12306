package ruijie.com.my12306.ui.main;

import android.support.v4.app.Fragment;

import ruijie.com.my12306.ui.login.LoginContact;
import ruijie.com.my12306.ui.base.BasePresenter;
import ruijie.com.my12306.ui.base.BaseView;
import ruijie.com.my12306.ui.login.LoginContact;

/**
 * Created by Administrator on 2016/8/18.
 */

public interface MainContract {

    interface View extends BaseView{

        void showFragment(Fragment fragment);
    }

    interface Presenter extends BasePresenter<View>{

        void onBottomNavClick(int position);
    }
}
