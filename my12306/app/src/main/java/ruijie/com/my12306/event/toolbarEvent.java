package ruijie.com.my12306.event;

/**
 * Created by Administrator on 2016/8/19.
 */

public class toolbarEvent {

    private int position;
    private boolean isLogin;
    private static toolbarEvent instance;

    public toolbarEvent(int position,boolean isLogin){
        this.position = position;
        this.isLogin = isLogin;
    }

    public toolbarEvent(){

    }

    public static toolbarEvent getInstance(){
        if(instance==null){
            synchronized (toolbarEvent.class){
                if (instance==null)
                    instance = new toolbarEvent();
            }
        }
        return instance;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }
}
