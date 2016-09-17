package ruijie.com.my12306.ui.login;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import ruijie.com.my12306.R;
import ruijie.com.my12306.ui.base.BaseFragment;
import ruijie.com.my12306.ui.main.MainActivity;
import ruijie.com.my12306.ui.main.MainComponent;
import ruijie.com.my12306.util.SnackbarUtils;

/**
 * Created by prj on 2016/8/18.
 */

public class LoginFragment extends BaseFragment implements LoginContact.View{

    @Inject
    LoginPresenter mPresenter;
    @Inject
    Context context;
    @Inject
    MainActivity mainActivity;
    @Bind(R.id.etUserName)
    EditText etUserName;
    @Bind(R.id.textInputUserName)
    TextInputLayout textInputUserName;
    @Bind(R.id.etPassWord)
    EditText etPassWord;
    @Bind(R.id.textInputPassword)
    TextInputLayout textInputPassword;
    @Bind(R.id.btnCommit)
    Button btnCommit;
    View root;
    public static LoginFragment instance;
    private MaterialDialog dialog;

    public static LoginFragment getInstance() {
        if (instance == null) {
            synchronized (LoginFragment.class) {
                if (instance == null)
                    instance = new LoginFragment();
            }
        }
        return instance;
    }

    public LoginFragment() {

    }

    @Override
    public void initInjector() {
        getComponent(MainComponent.class).inject(this);
    }

    @Override
    public int initContentView() {
        return R.layout.activity_login;
    }

    @Override
    public void getBundle(Bundle bundle) {

    }

    @Override
    public void initUI(View view) {
        ButterKnife.bind(this, view);
        root = view;
        btnCommit.setOnClickListener(view1 ->
                mPresenter.login(etUserName.getText().toString().trim()
                        ,etPassWord.getText().toString().trim()));
        dialog = new MaterialDialog.Builder(getContext())
                .content("正在登录...")
                .progress(true, 0)
                .build();
        etUserName.addTextChangedListener(new MTextWatcher(textInputUserName));
        etPassWord.addTextChangedListener(new MTextWatcher(textInputPassword));
        showContent(true);
        mPresenter.attachView(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void showLoading() {
        //showProgress(true);
        if (!mainActivity.isFinishing() && !dialog.isShowing()) {
            dialog.show();
        }
    }

    @Override
    public void dimissLoading() {
        if (!mainActivity.isFinishing() && dialog.isShowing())
            dialog.dismiss();
    }

    @Override
    public void showUserNameError(String error) {
        SnackbarUtils.show(root, error, 0, null);
    }

    @Override
    public void showPasswordError(String error) {
        SnackbarUtils.show(root, error, 0, null);
    }

    @Override
    public void loginSuccess() {

    }

    @Override
    public void loginFailure(String error) {
        SnackbarUtils.show(root,"登录失败", 0, null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detachView();
        ButterKnife.unbind(this);
    }

    class MTextWatcher implements TextWatcher {

        TextInputLayout textInputLayout;

        public MTextWatcher(TextInputLayout textInputLayout) {
            this.textInputLayout = textInputLayout;
        }

        @Override public void afterTextChanged(Editable arg0) {

        }

        @Override public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

        }

        @Override public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            textInputLayout.setErrorEnabled(false);
        }
    }
}
