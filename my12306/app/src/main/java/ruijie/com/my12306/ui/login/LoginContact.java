package ruijie.com.my12306.ui.login;

import ruijie.com.my12306.ui.base.BasePresenter;
import ruijie.com.my12306.ui.base.BaseView;

/**
 * Created by Administrator on 2016/8/17.
 */

public interface LoginContact {

    interface View extends BaseView{
        void showLoading();

        void dimissLoading();

        void showUserNameError(String error);

        void showPasswordError(String error);

        void loginSuccess();

        void loginFailure(String error);
    }

    interface Presenter extends BasePresenter<View>{
        void login(String username,String password);
    }
}
