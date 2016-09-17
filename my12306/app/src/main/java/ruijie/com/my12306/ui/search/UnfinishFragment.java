package ruijie.com.my12306.ui.search;

import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import ruijie.com.my12306.R;
import ruijie.com.my12306.ui.base.BaseFragment;

/**
 * Created by Administrator on 2016/8/18.
 */

public class UnfinishFragment extends BaseFragment {

    public static UnfinishFragment instance;
    View root;

    public static UnfinishFragment getInstance() {
        if (instance == null) {
            synchronized (UnfinishFragment.class) {
                if (instance == null)
                    instance = new UnfinishFragment();
            }
        }
        return instance;
    }

    public UnfinishFragment() {

    }

    @Override
    public void initInjector() {

    }

    @Override
    public int initContentView() {
        return R.layout.fragment_unfinish;
    }

    @Override
    public void getBundle(Bundle bundle) {

    }

    @Override
    public void initUI(View view) {
        ButterKnife.bind(this, view);
        root = view;

        //showEmpty(true);
        //showError(true);
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
}
