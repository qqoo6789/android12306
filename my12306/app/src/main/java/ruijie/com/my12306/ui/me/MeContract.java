package ruijie.com.my12306.ui.me;

import android.widget.EditText;

import ruijie.com.my12306.bean.User;
import ruijie.com.my12306.ui.base.BasePresenter;
import ruijie.com.my12306.ui.base.BaseView;

/**
 * Created by Administrator on 2016/8/29.
 */

public interface MeContract {

    interface View extends BaseView{

        void showLoginDialog();

        void showCommitDialog();

        void showRegDialogF();

        void showRegDialogS();

        void dimissRegDialogF();

        void dimissRegDialogS();

        void dimissLoginDialog();

        void dimissCommitDialog();

        void registerSuccess();

        void loginSuccess();

        void loginFailure(String error);

        void registerFailure(String error);

        void showRegNameError(String error);

        void showRegPwdError(String error);

        void showRegSPwdError(String error);

        void showNickNameError(String error);

        void showIdCardError(String error);

        void showEmailError(String error);

        void showIdentityError(String error);

        void showPhoneError(String error);

    }

    interface Presenter extends BasePresenter<View>{

        void login(String username,String password);

        void LoginBtClick();

        void RegisterBtClick();

        void RegisterNextClick();

        void register(User user);

        boolean getCheckRegF(EditText username,EditText password,EditText spassword);

        boolean getCheckRegS(EditText nickname,EditText idcard,EditText email,EditText identity,
                             EditText phone);
    }
}
