package ruijie.com.my12306.ui.booking.orderActivty;

import java.util.List;

import ruijie.com.my12306.bean.Station;
import ruijie.com.my12306.bean.Ticket;
import ruijie.com.my12306.bean.TicketData;
import ruijie.com.my12306.bean.TicketInfo;
import ruijie.com.my12306.ui.base.BasePresenter;
import ruijie.com.my12306.ui.base.BaseView;

/**
 * Created by Administrator on 2016/8/18.
 */

public interface BookingOrderContract {

    interface View extends BaseView{

        void showLoading();

        void dismissLoading();

        void showError(String error);

        void getTicketInfo(TicketData ticketData);

    }

    interface Presenter extends BasePresenter<View>{

        void getTicketInfo(String startsite,String endsite,String type,String startdate);

        void insertOrder(Integer cid, Integer uid, Integer onumber, Double price,
                         Integer ztype, String startsite, String endsite,
                         String znumber, Integer carriagenumber, Integer ttype, Double mileage,
                         String odate, int zid, int sidEnd, int sidStart, int codenumberEnd,
                         int codenumberStart, int status);
    }
}
