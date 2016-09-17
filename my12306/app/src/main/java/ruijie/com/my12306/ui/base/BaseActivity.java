package ruijie.com.my12306.ui.base;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import butterknife.ButterKnife;
import ruijie.com.my12306.AppManager;
import ruijie.com.my12306.MyApplication;
import ruijie.com.my12306.R;
import ruijie.com.my12306.injector.component.ActivityComponent;
import ruijie.com.my12306.injector.component.ApplicationComponent;
import ruijie.com.my12306.injector.moudel.ActivityMoudle;
import ruijie.com.my12306.util.ResourceUtil;
import ruijie.com.my12306.util.SettingPrefUtil;
import ruijie.com.my12306.util.StatusBarUtil;
import ruijie.com.my12306.util.ThemeUtil;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2016/8/16.
 */

public abstract class BaseActivity extends AppCompatActivity{

    private Fragment oldFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getApplicationComponent().inject(this);
        initTheme();
        super.onCreate(savedInstanceState);
        setContentView(initContentView());
        setTranslucentStatus(isApplyStatusBarTranslucency());
        setStatusBarColor(isApplyStatusBarColor());
        initInjector();
        initUiAndListener();
        AppManager.getAppManager().addActivity(this);
    }

    protected abstract int initContentView();

    protected ApplicationComponent getApplicationComponent() {
        return ((MyApplication)getApplication()).getApplicationComponent();
    }

    protected ActivityMoudle getActivityMoudle(){
        return new ActivityMoudle(this);
    }

    private void initTheme() {
        int theme;
        try {
            theme = getPackageManager().getActivityInfo(getComponentName(), 0).theme;
        } catch (PackageManager.NameNotFoundException e) {
            return;
        }
        if (theme != R.style.AppThemeLaunch) {
            theme = ThemeUtil.themeArr[SettingPrefUtil.getThemeIndex(this)][
                    SettingPrefUtil.getNightModel(this) ? 1 : 0];
        }
        setTheme(theme);
    }

    /**
     * set status bar translucency
     */
    protected void setTranslucentStatus(boolean on) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (on) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
            }
            win.setAttributes(winParams);
        }
    }

    /**
     * is applyStatusBarTranslucency
     */
    protected abstract boolean isApplyStatusBarTranslucency();

    /**
     * 注入Injector
     */
    public abstract void initInjector();

    /**
     * init UI && Listener
     */
    public abstract void initUiAndListener();

    /**
     * use SystemBarTintManager
     */
    public void setStatusBarColor(boolean on) {
        if (on) {
            StatusBarUtil.setColor(this, ResourceUtil.getThemeColor(this), 0);
        }
    }

    protected abstract boolean isApplyStatusBarColor();

    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    public int getStatusBarHeight() {
        return ResourceUtil.getStatusBarHeight(this);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
        ButterKnife.unbind(this);
    }

    public void showCurrentFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.show(fragment).commit();
        oldFragment = fragment;
    }

    public void addFragment(Fragment fragment){
        if(oldFragment==null)
            oldFragment = fragment;
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.content, fragment).hide(oldFragment).commit();
    }

    public void transFragmentTo(Fragment to){
        if(oldFragment!=to) {
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.hide(oldFragment);
            fragmentTransaction.show(to);
            fragmentTransaction.commit();
            oldFragment = to;
        }
    }

}
