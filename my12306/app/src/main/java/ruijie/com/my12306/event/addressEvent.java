package ruijie.com.my12306.event;

/**
 * Created by Administrator on 2016/8/23.
 */

public class addressEvent {

    public String address;
    public boolean isFrom;
    public static addressEvent instance;

    public addressEvent(String address,boolean isFrom){
        this.address = address;
        this.isFrom = isFrom;
    }

    public static addressEvent getInstance(){
        if(instance==null){
            synchronized (addressEvent.class){
                if (instance==null){
                    instance = new addressEvent();
                }
            }
        }
        return instance;
    }

    public addressEvent(){

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isFrom() {
        return isFrom;
    }

    public void setFrom(boolean from) {
        isFrom = from;
    }
}
