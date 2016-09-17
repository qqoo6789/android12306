package ruijie.com.my12306.ui.register;

import android.support.annotation.NonNull;

import com.blankj.utilcode.utils.RegularUtils;
import com.blankj.utilcode.utils.StringUtils;

import javax.inject.Inject;

import ruijie.com.my12306.api.User.UserApi;
import ruijie.com.my12306.bean.User;
import ruijie.com.my12306.ui.base.BaseView;
import ruijie.com.my12306.ui.login.LoginContact;
import ruijie.com.my12306.util.StringUtil;
import rx.Subscription;

/**
 * Created by Administrator on 2016/8/26.
 */

public class RegisterPresenter implements RegisterContract.Presenter {

    private Subscription mSubscription;
    private RegisterContract.View mRegisterView;
    private UserApi userApi;

    @Inject
    public RegisterPresenter(UserApi loginApi) {
        this.userApi = userApi;
    }

    @Override
    public void register(String username, String password, String spassword, String id, String number,String email, String identity) {

        if (StringUtils.isEmpty(username)) {
            mRegisterView.showUserNameError("用户名不能为空");
            return;
        }
        if (StringUtils.isEmpty(password)) {
            mRegisterView.showPasswordError("密码不能为空");
            return;
        }
        if (StringUtils.isEmpty(password)) {
            mRegisterView.showSPasswordError("确认密码不能为空");
            return;
        }
        if (StringUtils.isEmpty(id)) {
            mRegisterView.showIdError("身份证号不能为空");
            return;
        }
        if (StringUtils.isEmpty(number)) {
            mRegisterView.showNumberError("联系方式不能为空");
            return;
        }
        if (StringUtils.isEmpty(email)) {
            mRegisterView.showEmailError("邮箱不能为空");
            return;
        }
        if (StringUtils.isEmpty(identity)) {
            mRegisterView.showIdentityError("身份类型不能为空");
            return;
        }
        if(!password.equals(spassword)){
            mRegisterView.showPasswordError("请确认两次密码是否相同");
            return;
        }
        if(password.length()<6){
            mRegisterView.showPasswordError("密码长度不能小于6位");
            return;
        }
        if (!RegularUtils.isIDCard15(id)&&!RegularUtils.isIDCard18(id)){
            mRegisterView.showIdError("请输入真实的身份证号码");
            return;
        }
        if(!RegularUtils.isMobileExact(number)){
            mRegisterView.showNumberError("请输入真实的手机号码");
            return;
        }
        if(!RegularUtils.isEmail(email)){
            mRegisterView.showEmailError("请输入正确的邮箱");
        }
    }


    @Override
    public void attachView(@NonNull RegisterContract.View View) {
        mRegisterView = View;
    }

    @Override
    public void detachView() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
        mRegisterView = null;
    }
}
