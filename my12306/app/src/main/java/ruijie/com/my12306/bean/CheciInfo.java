package ruijie.com.my12306.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/9/2.
 */

public class CheciInfo implements Serializable{
    private String errortype;
    private String status;
    private List<CheciData> data;
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

    public List<CheciData> getData() {
        return data;
    }

    public void setData(List<CheciData> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
