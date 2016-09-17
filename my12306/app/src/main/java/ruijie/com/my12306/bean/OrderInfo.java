package ruijie.com.my12306.bean;

import java.util.List;

/**
 * Created by yum on 2016/9/3.
 */

public class OrderInfo {
    private String errortype;
    private String status;
    private List<OrderData> data;
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

    public List<OrderData> getData() {
        return data;
    }

    public void setData(List<OrderData> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public OrderInfo(String errortype, String status, List<OrderData> data, String msg) {
        this.errortype = errortype;
        this.status = status;
        this.data = data;
        this.msg = msg;
    }
}
