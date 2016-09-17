package ruijie.com.my12306.ui.splash;

import android.content.Intent;
import ruijie.com.my12306.R;
import ruijie.com.my12306.ui.base.BaseActivity;
import ruijie.com.my12306.ui.main.MainActivity;

/**
 * Created by prj on 2016/8/17.
 */

public class SplashActivity extends BaseActivity {

    @Override
    protected int initContentView() {
        return R.layout.activity_splash;
    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return true;
    }

    @Override
    public void initInjector() {

    }

    @Override
    public void initUiAndListener() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected boolean isApplyStatusBarColor() {
        return false;
    }
}
