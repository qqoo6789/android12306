package ruijie.com.my12306.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/2.
 */

public class StationInfo {
    private String errortype;
    private String status;
    private List<Station> data;
    private String msg;

    public String getErrortype() {
        return errortype;
    }

    public void setErrortype(String errortype) {
        this.errortype = errortype;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Station> getData() {
        return data;
    }

    public void setData(List<Station> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
