package ruijie.com.my12306.ui.me;

import android.content.Context;
import android.content.Intent;
import android.database.Observable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ruijie.com.my12306.R;
import ruijie.com.my12306.bean.User;
import ruijie.com.my12306.ui.base.BaseFragment;
import ruijie.com.my12306.ui.base.BusFragment;
import ruijie.com.my12306.ui.login.LoginActivity;
import ruijie.com.my12306.ui.main.MainActivity;
import ruijie.com.my12306.ui.main.MainComponent;
import ruijie.com.my12306.ui.register.RegisterActivity;
import ruijie.com.my12306.util.MTextWatcher;
import ruijie.com.my12306.util.SnackbarUtils;
import ruijie.com.my12306.util.TextUtil;
import ruijie.com.my12306.widget.citySelector.utils.ClearEditText;
import rx.Subscriber;
import rx.observers.Observers;

/**
 * Created by Administrator on 2016/8/18.
 */

public class MeFragment extends BusFragment implements View.OnClickListener,MeContract.View{

    public static MeFragment instance;
    @Inject
    MainActivity mainActivity;
    @Inject
    MePresenter mePresenter;
    @Inject
    Context context;
    @Bind(R.id.bt_login)
    Button btLogin;
    @Bind(R.id.bt_register)
    Button btRegister;
    @Bind(R.id.bt_guide)
    Button btGuide;
    @Bind(R.id.bt_service)
    Button btService;
    @Bind(R.id.bt_travel)
    Button btTravel;
    @Bind(R.id.bt_update)
    Button btUpdate;
    @Bind(R.id.bt_notification)
    Button btNotification;
    @Bind(R.id.bt_about)
    Button btAbout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    View root;
    private MaterialDialog loginDialog;
    private MaterialDialog commitDialog;
    private MaterialDialog regDialogF;
    private MaterialDialog regDialogS;
    private MaterialDialog aboutDialog;
    private View loginView;
    private View regViewF;
    private View regViewS;
    private ClearEditText et_username;
    private ClearEditText et_password;
    private ClearEditText rg_usernmae;
    private ClearEditText rg_password;
    private ClearEditText rg_spassword;
    private ClearEditText rg_nickname;
    private ClearEditText rg_idcard;
    private ClearEditText rg_email;
    private ClearEditText rg_identity;
    private ClearEditText rg_phone;
    private TextInputLayout rgTextInputUserName;
    private TextInputLayout rgTextInputPassWord;
    private TextInputLayout rgTextInputSPassWord;
    private TextInputLayout textInputPassword;
    private TextInputLayout textInputUserName;
    private TextInputLayout rgTextInputNickName;
    private TextInputLayout rgTextInputIdcard;
    private TextInputLayout rgTextInputEmail;
    private TextInputLayout rgTextInputIdentity;
    private TextInputLayout rgTextInputPhone;

    public static MeFragment getInstance() {
        if (instance == null) {
            synchronized (MeFragment.class) {
                if (instance == null)
                    instance = new MeFragment();
            }
        }
        return instance;
    }

    public MeFragment() {
    }

    @Override
    public void initInjector() {
        getComponent(MainComponent.class).inject(this);
    }

    @Override
    public int initContentView() {
        return R.layout.fragment_me;
    }

    @Override
    public void getBundle(Bundle bundle) {

    }

    @Override
    public void initUI(View view) {
        ButterKnife.bind(this, view);
        root = view;

        mePresenter.attachView(this);
        mainActivity.setSupportActionBar(toolbar);
        mainActivity.setTitle("我的12306");

        loginView = mainActivity.getLayoutInflater().inflate(R.layout.layout_login,null,false);
        et_username = (ClearEditText) loginView.findViewById(R.id.etUserName);
        et_password = (ClearEditText) loginView.findViewById(R.id.etPassWord);
        textInputUserName = (TextInputLayout) loginView.findViewById(R.id.textInputUserName);
        textInputPassword = (TextInputLayout) loginView.findViewById(R.id.textInputPassword);
        et_username.addTextChangedListener(new LoginextWatcher(textInputUserName));
        et_password.addTextChangedListener(new LoginextWatcher(textInputPassword));

        regViewF = mainActivity.getLayoutInflater().inflate(R.layout.layout_register_f,null,false);
        rg_usernmae = (ClearEditText) regViewF.findViewById(R.id.rgUserName);
        rg_password = (ClearEditText) regViewF.findViewById(R.id.rgPassWord);
        rg_spassword = (ClearEditText) regViewF.findViewById(R.id.rgSPassWord);
        rgTextInputUserName = (TextInputLayout) regViewF.findViewById(R.id.rgTextInputUserName);
        rgTextInputPassWord = (TextInputLayout) regViewF.findViewById(R.id.rgTextInputPassword);
        rgTextInputSPassWord = (TextInputLayout) regViewF.findViewById(R.id.rgTextInputSecondPassword);
        rg_usernmae.addTextChangedListener(new LoginextWatcher(rgTextInputUserName));
        rg_spassword.addTextChangedListener(new LoginextWatcher(rgTextInputPassWord));
        rg_spassword.addTextChangedListener(new LoginextWatcher(rgTextInputSPassWord));

        regViewS = mainActivity.getLayoutInflater().inflate(R.layout.layout_register_s,null,false);
        rg_nickname = (ClearEditText) regViewS.findViewById(R.id.rgNickName);
        rg_idcard = (ClearEditText) regViewS.findViewById(R.id.rgIdcard);
        rg_email = (ClearEditText) regViewS.findViewById(R.id.rgEmail);
        rg_identity = (ClearEditText) regViewS.findViewById(R.id.rgIdentity);
        rg_phone = (ClearEditText) regViewS.findViewById(R.id.rgPhone);
        rgTextInputNickName = (TextInputLayout) regViewS.findViewById(R.id.rgTextInputNickName);
        rgTextInputIdcard = (TextInputLayout) regViewS.findViewById(R.id.rgTextInputIdcard);
        rgTextInputEmail = (TextInputLayout) regViewS.findViewById(R.id.rgTextInputEmail);
        rgTextInputIdentity = (TextInputLayout) regViewS.findViewById(R.id.rgTextInputIdentity);
        rgTextInputPhone = (TextInputLayout) regViewS.findViewById(R.id.rgTextInputPhone);
        rg_nickname.addTextChangedListener(new LoginextWatcher(rgTextInputNickName));
        rg_idcard.addTextChangedListener(new LoginextWatcher(rgTextInputIdcard));
        rg_email.addTextChangedListener(new LoginextWatcher(rgTextInputEmail));
        rg_identity.addTextChangedListener(new LoginextWatcher(rgTextInputIdentity));
        rg_phone.addTextChangedListener(new LoginextWatcher(rgTextInputPhone));

        loginDialog = new MaterialDialog.Builder(getContext())
                .title("登陆")
                .customView(loginView,true)
                .positiveText("登陆")
                .neutralText("无法登陆？")
                .neutralColor(getResources().getColor(R.color.base_text_black))
                .onPositive((dialog, which) -> mePresenter.login(TextUtil.getText(et_username),TextUtil.getText(et_password)))
                .onNeutral((dialog, which) -> SnackbarUtils.show(root,"点击",0,null))
                .build();
        loginDialog.getActionButton(DialogAction.POSITIVE).setEnabled(false);

        commitDialog = new MaterialDialog.Builder(getContext())
                .content("请稍后...")
                .progress(true, 0)
                .build();

        regDialogF = new MaterialDialog.Builder(getContext())
                .title("注册")
                .customView(regViewF,true)
                .positiveText("下一步")
                .negativeText("取消")
                .onPositive((dialog, which) -> mePresenter.RegisterNextClick())
                .onNegative((dialog, which) -> regDialogF.dismiss())
                .build();
        regDialogF.getActionButton(DialogAction.POSITIVE).setEnabled(false);

        regDialogS = new MaterialDialog.Builder(getContext())
                .title("注册")
                .customView(regViewS,true)
                .positiveText("注册")
                .negativeText("取消")
                .neutralText("返回")
                .neutralColor(getResources().getColor(R.color.base_text_black))
                .onPositive((dialog, which) -> {
                    User user = new User("",TextUtil.getText(rg_usernmae),TextUtil.getText(rg_password),
                            TextUtil.getText(rg_nickname),TextUtil.getText(rg_idcard),TextUtil.getText(rg_email)
                    ,TextUtil.getText(rg_identity),TextUtil.getText(rg_phone),"",0,"");
                    mePresenter.register(user);
                })
                .onNegative((dialog,which) -> regDialogS.dismiss())
                .onNeutral((dialog, which) -> {
                    regDialogS.dismiss();
                    regDialogF.show();
                })
                .build();
        regDialogS.getActionButton(DialogAction.POSITIVE).setEnabled(false);
        showContent(true);

    }

    @Override
    public void initData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.bt_login, R.id.bt_register, R.id.bt_guide, R.id.bt_service, R.id.bt_travel, R.id.bt_update, R.id.bt_notification, R.id.bt_about})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                mePresenter.LoginBtClick();
                break;
            case R.id.bt_register:
                mePresenter.RegisterBtClick();
                break;
            case R.id.bt_guide:
                break;
            case R.id.bt_service:
                break;
            case R.id.bt_travel:
                break;
            case R.id.bt_update:
                break;
            case R.id.bt_notification:
                break;
            case R.id.bt_about:
                Intent i = new Intent(context,AboutActivity.class);
                startActivity(i);
                mainActivity.overridePendingTransition(0,0);
                break;
        }
    }

    @Override
    public void showLoginDialog() {
        loginDialog.show();
    }

    @Override
    public void showCommitDialog() {
        commitDialog.show();
    }

    @Override
    public void showRegDialogF() {
        regDialogF.show();
    }

    @Override
    public void showRegDialogS() {
        regDialogS.show();
    }

    @Override
    public void dimissRegDialogF() {
        regDialogF.dismiss();
    }

    @Override
    public void dimissRegDialogS() {
        regDialogS.dismiss();
    }

    @Override
    public void dimissLoginDialog() {
        loginDialog.dismiss();
    }

    @Override
    public void dimissCommitDialog() {
        commitDialog.dismiss();
    }

    @Override
    public void registerSuccess() {
        rg_usernmae.setText("");
        rg_password.setText("");
        rg_spassword.setText("");
        rg_nickname.setText("");
        rg_idcard.setText("");
        rg_email.setText("");
        rg_identity.setText("");
        rg_phone.setText("");
        SnackbarUtils.show(root,"注册成功",0,null);
    }

    @Override
    public void loginSuccess() {
        SnackbarUtils.show(root,"登录成功",0,null);
        et_username.setText("");
        et_password.setText("");
    }

    @Override
    public void loginFailure(String error) {
        SnackbarUtils.show(root,error,0,null);
    }

    @Override
    public void registerFailure(String error) {
        textInputPassword.setError(error);
        textInputPassword.setEnabled(true);
    }

    @Override
    public void showRegNameError(String error) {
        rgTextInputUserName.setError(error);
        rgTextInputUserName.setEnabled(true);
    }

    @Override
    public void showRegPwdError(String error) {
        rgTextInputPassWord.setError(error);
        rgTextInputPassWord.setEnabled(true);
    }

    @Override
    public void showRegSPwdError(String error) {
        rgTextInputSPassWord.setError(error);
        rgTextInputSPassWord.setEnabled(false);
    }

    @Override
    public void showNickNameError(String error) {
        rgTextInputNickName.setError(error);
        rgTextInputNickName.setEnabled(true);
    }

    @Override
    public void showIdCardError(String error) {
        rgTextInputIdcard.setError(error);
        rgTextInputIdcard.setEnabled(true);
    }

    @Override
    public void showEmailError(String error) {
        rgTextInputEmail.setError(error);
        rgTextInputEmail.setEnabled(true);
    }

    @Override
    public void showIdentityError(String error) {
        rgTextInputIdentity.setError(error);
        rgTextInputIdentity.setEnabled(true);
    }

    @Override
    public void showPhoneError(String error) {
        rgTextInputPhone.setError(error);
        rgTextInputPhone.setEnabled(true);
    }

    public class LoginextWatcher implements TextWatcher {

        TextInputLayout textInputLayout;

        public LoginextWatcher(TextInputLayout textInputLayout) {
            this.textInputLayout = textInputLayout;
        }

        @Override
        public void afterTextChanged(Editable arg0) {
            if(TextUtil.getLength(et_username)>=2&&
                    TextUtil.getLength(et_password)>=6){
                loginDialog.getActionButton(DialogAction.POSITIVE).setEnabled(true);
            }else {
                loginDialog.getActionButton(DialogAction.POSITIVE).setEnabled(false);
            }

            if(mePresenter.getCheckRegF(rg_usernmae,rg_password,rg_spassword)){
                regDialogF.getActionButton(DialogAction.POSITIVE).setEnabled(true);
            }else {
                regDialogF.getActionButton(DialogAction.POSITIVE).setEnabled(false);
            }

            if(mePresenter.getCheckRegS(rg_nickname,rg_idcard,rg_email,rg_identity,rg_phone)){
                regDialogS.getActionButton(DialogAction.POSITIVE).setEnabled(true);
            }else {
                regDialogS.getActionButton(DialogAction.POSITIVE).setEnabled(false);
            }
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            textInputLayout.setErrorEnabled(false);
        }
    }
}

