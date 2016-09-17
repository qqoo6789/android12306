package ruijie.com.my12306.bean;

/**
 * Created by prj on 2016/8/20.
 */

public class AddressItem {

    private String from;
    private String to;

    public AddressItem(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
