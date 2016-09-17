package ruijie.com.my12306.ui.search.searchActivity;

import java.util.List;

import ruijie.com.my12306.bean.OrderData;
import ruijie.com.my12306.ui.base.BasePresenter;
import ruijie.com.my12306.ui.base.BaseView;

/**
 * Created by yum on 2016/9/3.
 */

public interface OrderSearchContract {
    interface View extends BaseView{
        void showLoading();

        void dismissLoading();

        void showOrderSearchData(List<OrderData> list);

        void showError(String Error);
    }
    interface Presenter extends BasePresenter<View> {
        void getOrderInfo(int uid, String startdate, int action);
    }
}
