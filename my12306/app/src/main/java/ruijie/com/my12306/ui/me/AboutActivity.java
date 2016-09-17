package ruijie.com.my12306.ui.me;

import android.animation.Animator;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import ruijie.com.my12306.R;
import ruijie.com.my12306.ui.base.BaseSwipeBackActivity;
import ruijie.com.my12306.util.AnimationUtil;
import ruijie.com.my12306.util.DisplayUtil;

/**
 * Created by Administrator on 2016/8/30.
 */

public class AboutActivity extends BaseSwipeBackActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.ll_reveal)
    LinearLayout llReveal;
    @Bind(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;
    @Bind(R.id.tv_pro_address)
    TextView tvProAddress;

    @Override
    protected int initContentView() {
        return R.layout.activity_about;
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
        ButterKnife.bind(this);

        initToolBar(toolbar, "关于作者");
        llReveal.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                llReveal.getViewTreeObserver().removeOnPreDrawListener(this);
                Animator animator = AnimationUtil.getCircularReveal(llReveal, 2, 600);
                animator.start();
                return true;
            }
        });

    }

    @Override
    protected boolean isApplyStatusBarColor() {
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
