package ruijie.com.my12306.ui.register;

import ruijie.com.my12306.ui.base.BasePresenter;
import ruijie.com.my12306.ui.base.BaseView;

/**
 * Created by Administrator on 2016/8/26.
 */

public interface RegisterContract {

    interface View extends BaseView{

        void showLoading();

        void dimissLoading();

        void showUserNameError(String error);

        void showPasswordError(String error);

        void showSPasswordError(String error);

        void showEmailError(String error);

        void showNumberError(String error);

        void showIdError(String error);

        void showIdentityError(String error);

        void registerSuccess();

        void registerFailure();
    }

    interface Presenter extends BasePresenter<View>{
        void register(String username,String password,String spassword,String id,String number,String email,String identity);
    }
}
