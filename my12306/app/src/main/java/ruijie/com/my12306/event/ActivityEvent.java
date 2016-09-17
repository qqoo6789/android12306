package ruijie.com.my12306.event;

import ruijie.com.my12306.bean.CheciData;
import ruijie.com.my12306.bean.Station;

/**
 * Created by Administrator on 2016/8/23.
 */

public class ActivityEvent {

    private CheciData checiData;
    public static ActivityEvent instance;

    public static ActivityEvent getInstance(){
        if(instance==null){
            synchronized (ActivityEvent.class){
                if (instance==null){
                    instance = new ActivityEvent();
                }
            }
        }
        return instance;
    }

    public ActivityEvent(){

    }

    public CheciData getCheciData() {
        return checiData;
    }

    public void setCheciData(CheciData checiData) {
        this.checiData = checiData;
    }
}
