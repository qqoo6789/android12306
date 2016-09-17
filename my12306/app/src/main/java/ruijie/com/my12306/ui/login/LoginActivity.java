package ruijie.com.my12306.ui.login;

import android.animation.Animator;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.MaterialDialog;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.codetail.animation.ViewAnimationUtils;
import io.codetail.widget.RevealLinearLayout;
import ruijie.com.my12306.R;
import ruijie.com.my12306.ui.base.BaseSwipeBackActivity;
import ruijie.com.my12306.util.AnimationUtil;
import ruijie.com.my12306.util.MTextWatcher;
import ruijie.com.my12306.util.SnackbarUtils;

/**
 * Created by Administrator on 2016/8/17.
 */

public class LoginActivity extends BaseSwipeBackActivity implements LoginContact.View {

    @Inject
    LoginPresenter mPresenter;
    @Inject
    Context context;
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
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    //@Bind(R.id.ll)
    LoginComponent loginComponent;
    LinearLayout root;
    @Bind(R.id.AppBarLayout)
    android.support.design.widget.AppBarLayout AppBarLayout;
    private MaterialDialog dialog;

    @Override
    protected int initContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return true;
    }

    @Override
    public void initInjector() {
        loginComponent = DaggerLoginComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityMoudle(getActivityMoudle())
                .build();
        loginComponent.inject(this);
    }

    @Override
    public void initUiAndListener() {
        ButterKnife.bind(this);
        root = (LinearLayout) findViewById(R.id.root);

        initToolBar(toolbar,"登录");

        mPresenter.attachView(this);
        btnCommit.setOnClickListener(view1 ->
                mPresenter.login(etUserName.getText().toString().trim()
                        , etPassWord.getText().toString().trim()));
        dialog = new MaterialDialog.Builder(context)
                .content("正在登录...")
                .progress(true, 0)
                .build();
        etUserName.addTextChangedListener(new MTextWatcher(textInputUserName));
        etPassWord.addTextChangedListener(new MTextWatcher(textInputPassword));

        root.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                root.getViewTreeObserver().removeOnPreDrawListener(this);
                Animator animator = AnimationUtil.getCircularReveal(root,2,600);
                animator.start();
                return true;
            }
        });
        mPresenter.attachView(this);
    }

    @Override
    protected boolean isApplyStatusBarColor() {
        return true;
    }

    @Override
    public void showLoading() {
        if (!isFinishing() && !dialog.isShowing()) {
            dialog.show();
        }
    }

    @Override
    public void dimissLoading() {
        if (!isFinishing() && dialog.isShowing())
            dialog.dismiss();
    }

    @Override
    public void showUserNameError(String error) {
        textInputUserName.setError(error);
        textInputUserName.setErrorEnabled(true);
    }

    @Override
    public void showPasswordError(String error) {
        textInputPassword.setError(error);
        textInputPassword.setErrorEnabled(true);
    }

    @Override
    public void loginSuccess() {
        SnackbarUtils.show(root, "登录成功", 0, null);
    }

    @Override
    public void loginFailure(String error) {
        SnackbarUtils.show(root, "登录失败", 0, null);
    }

    @OnClick(R.id.btnCommit)
    public void onClick() {
        String mUserName = etUserName.getText().toString().trim();
        String mPassword = etPassWord.getText().toString().trim();
        mPresenter.login(mUserName, mPassword);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

}
