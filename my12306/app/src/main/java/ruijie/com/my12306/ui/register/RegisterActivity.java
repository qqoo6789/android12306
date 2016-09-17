package ruijie.com.my12306.ui.register;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.blankj.utilcode.utils.KeyboardUtils;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.codetail.widget.RevealLinearLayout;
import ruijie.com.my12306.R;
import ruijie.com.my12306.injector.moudel.ActivityMoudle;
import ruijie.com.my12306.ui.base.BaseSwipeBackActivity;
import ruijie.com.my12306.ui.main.MainMoudle;
import ruijie.com.my12306.util.AnimationUtil;
import ruijie.com.my12306.util.BimpUtil;
import ruijie.com.my12306.util.BlurUtil;
import ruijie.com.my12306.util.MTextWatcher;
import ruijie.com.my12306.util.ReboundScrollView;
import ruijie.com.my12306.util.TextUtil;

/**
 * Created by Administrator on 2016/8/26.
 */

public class RegisterActivity extends BaseSwipeBackActivity implements RegisterContract.View {

    RegisterComponent registerComponent;
    @Inject
    RegisterPresenter presenter;
    @Inject
    Context context;
    @Inject
    Activity registerActivity;
    MaterialDialog dialog;
    MaterialDialog identityDialog;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.etUserName)
    EditText etUserName;
    @Bind(R.id.textInputUserName)
    TextInputLayout textInputUserName;
    @Bind(R.id.etPassWord)
    EditText etPassWord;
    @Bind(R.id.textInputPassword)
    TextInputLayout textInputPassword;
    @Bind(R.id.etSPassWord)
    EditText etSPassWord;
    @Bind(R.id.textInputSecondPassword)
    TextInputLayout textInputSecondPassword;
    @Bind(R.id.etId)
    EditText etId;
    @Bind(R.id.textInputId)
    TextInputLayout textInputId;
    @Bind(R.id.etNumber)
    EditText etNumber;
    @Bind(R.id.textInputNumber)
    TextInputLayout textInputNumber;
    @Bind(R.id.etIdentity)
    EditText etIdentity;
    @Bind(R.id.textInputIdentity)
    TextInputLayout textInputIdentity;
    @Bind(R.id.btnCommit)
    Button btnCommit;
    @Bind(R.id.root)
    ReboundScrollView root;
    @Bind(R.id.etEmail)
    EditText etEmail;
    @Bind(R.id.textInputEmial)
    TextInputLayout textInputEmial;
    RevealLinearLayout reveall;
    ImageView iv;

    @Override
    protected int initContentView() {
        return R.layout.activity_register;
    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return true;
    }

    @Override
    public void initInjector() {
        registerComponent = DaggerRegisterComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityMoudle(getActivityMoudle())
                .build();
        registerComponent.inject(this);
    }

    @Override
    public void initUiAndListener() {
        ButterKnife.bind(this);

        /*Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.girl);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(BlurUtil.fastblur(context,bitmap,200));
        root.setBackground(bitmapDrawable);*/

        initToolBar(toolbar, "注册");
        dialog = new MaterialDialog.Builder(context)
                .content("正在登录...")
                .progress(true, 0)
                .build();

        identityDialog  = new MaterialDialog
                .Builder(context)
                .title("身份类型")
                .items(R.array.customer_type)
                .itemsCallbackSingleChoice(0, (dialog, itemView, which, text) -> {
                    etIdentity.setText(text);
                    identityDialog.dismiss();
                    return true;
                })
                .build();

        etUserName.addTextChangedListener(new MTextWatcher(textInputUserName));
        etPassWord.addTextChangedListener(new MTextWatcher(textInputPassword));
        etSPassWord.addTextChangedListener(new MTextWatcher(textInputSecondPassword));
        etId.addTextChangedListener(new MTextWatcher(textInputId));
        etNumber.addTextChangedListener(new MTextWatcher(textInputNumber));
        etEmail.addTextChangedListener(new MTextWatcher(textInputEmial));
        etIdentity.addTextChangedListener(new MTextWatcher(textInputIdentity));

        root.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                root.getViewTreeObserver().removeOnPreDrawListener(this);
                Animator animator = AnimationUtil.getCircularReveal(root,2,600);
                animator.start();
                return true;
            }
        });
        presenter.attachView(this);
    }

    @Override
    protected boolean isApplyStatusBarColor() {
        return true;
    }

    @Override
    public void showLoading() {
        if (!isFinishing() && !dialog.isShowing())
            dialog.show();
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
    public void showSPasswordError(String error) {
        textInputSecondPassword.setError(error);
        textInputSecondPassword.setErrorEnabled(true);
    }

    @Override
    public void showEmailError(String error) {
        textInputEmial.setError(error);
        textInputEmial.setErrorEnabled(true);
    }

    @Override
    public void showNumberError(String error) {
        textInputNumber.setError(error);
        textInputNumber.setErrorEnabled(true);
    }

    @Override
    public void showIdError(String error) {
        textInputId.setError(error);
        textInputId.setErrorEnabled(true);
    }

    @Override
    public void showIdentityError(String error) {
        textInputIdentity.setError(error);
        textInputIdentity.setErrorEnabled(true);
    }

    @Override
    public void registerSuccess() {

    }

    @Override
    public void registerFailure() {

    }

    @OnClick({R.id.etIdentity, R.id.btnCommit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.etIdentity:
                identityDialog.show();
                break;
            case R.id.btnCommit:
                presenter.register(TextUtil.getText(etUserName), TextUtil.getText(etPassWord)
                        , TextUtil.getText(etSPassWord), TextUtil.getText(etId),
                        TextUtil.getText(etNumber), TextUtil.getText(etEmail), TextUtil.getText(etIdentity));
                KeyboardUtils.hideSoftInput(registerActivity);
                break;
        }
    }
}
