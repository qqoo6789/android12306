package ruijie.com.my12306.ui.booking;

import java.util.ArrayList;
import java.util.List;

import ruijie.com.my12306.bean.CheciData;
import ruijie.com.my12306.bean.TicketData;
import ruijie.com.my12306.bean.User;
import ruijie.com.my12306.ui.base.BasePresenter;
import ruijie.com.my12306.ui.base.BaseView;

/**
 * Created by prj on 2016/8/21.
 */

public interface BookingContact {

    interface View extends BaseView{
        void showLoading();

        void dimissLoading();

        void showError(String error);

        void getCheck(boolean[] check);

        void addUser(ArrayList<User> list);

        void startTicketActivtyByList(List<CheciData> list,String from,String to);
    }

    interface Presenter extends BasePresenter<View>{

        void search(String from,String to,String startData,String startTime,String seatType,
            String ticketType,boolean isStudent);

        void onSeatClick(boolean[] check,int position);

        void onCustomClick(ArrayList<User> list);

    }
}
