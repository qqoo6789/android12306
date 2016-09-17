package ruijie.com.my12306.event;

/**
 * Created by Administrator on 2016/8/23.
 */

public class calendarEvent {

    public String date;
    public static calendarEvent instance;

    public calendarEvent(String date){
        this.date = date.replace("#","-");
    }

    public static calendarEvent getInstance(){
        if(instance==null){
            synchronized (calendarEvent.class){
                if (instance==null){
                    instance = new calendarEvent();
                }
            }
        }
        return instance;
    }

    public calendarEvent(){

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date.replace("#","-");
    }
}
