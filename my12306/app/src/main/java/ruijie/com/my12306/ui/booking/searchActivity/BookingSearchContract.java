package ruijie.com.my12306.ui.booking.searchActivity;

import java.util.ArrayList;
import java.util.List;

import ruijie.com.my12306.bean.Station;
import ruijie.com.my12306.bean.ZuoweiData;
import ruijie.com.my12306.ui.base.BasePresenter;
import ruijie.com.my12306.ui.base.BaseView;

/**
 * Created by Administrator on 2016/8/18.
 */

public interface BookingSearchContract {

    interface View extends BaseView{

        void showLoading();

        void dismissLoading();

        void showPassStation(List<Station> list, int position);

        void showError(String Error);
    }

    interface Presenter extends BasePresenter<View>{

        void getPassStation(int cid,int position);
    }
}
