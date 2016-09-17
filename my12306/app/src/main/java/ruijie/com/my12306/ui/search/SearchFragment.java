package ruijie.com.my12306.ui.search;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import ruijie.com.my12306.R;
import ruijie.com.my12306.ui.base.BaseFragment;
import ruijie.com.my12306.ui.base.BaseFragmentPagerAdapter;
import ruijie.com.my12306.ui.main.MainActivity;
import ruijie.com.my12306.ui.main.MainComponent;

/**
 * Created by Administrator on 2016/8/18.
 */

public class SearchFragment extends BaseFragment {

    @Inject
    Context context;
    @Inject
    MainActivity mainActivity;
    public static SearchFragment instance;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    private BaseFragmentPagerAdapter adapter;
    private ArrayList<Fragment> fragmentList;
    private String[] title;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    View root;

    public static SearchFragment getInstance() {
        if (instance == null) {
            synchronized (SearchFragment.class) {
                if (instance == null)
                    instance = new SearchFragment();
            }
        }
        return instance;
    }

    public SearchFragment() {

    }

    @Override
    public void initInjector() {
        getComponent(MainComponent.class).inject(this);
    }

    @Override
    public int initContentView() {
        return R.layout.fragment_search;
    }

    @Override
    public void getBundle(Bundle bundle) {

    }

    @Override
    public void initUI(View view) {
        ButterKnife.bind(this, view);
        root = view;

        fragmentList = new ArrayList<>();
        fragmentList.add(FinishFragment.getInstance());
        fragmentList.add(UnfinishFragment.getInstance());
        title = new String[2];
        title[0] = "已完成订单";
        title[1] = "未完成订单";
        adapter = new BaseFragmentPagerAdapter(mainActivity.getSupportFragmentManager(), fragmentList, title);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

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
