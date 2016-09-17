package ruijie.com.my12306.bean;

public class Ticket {
    private Integer tid;

    private Integer cid;

    private Integer zid;

    private Integer sidStart;

    private Integer sidEnd;

    private Integer codenumberStart;

    private Integer codenumberEnd;

    private Integer status;

    private Integer ztype;

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getZid() {
        return zid;
    }

    public void setZid(Integer zid) {
        this.zid = zid;
    }

    public Integer getSidStart() {
        return sidStart;
    }

    public void setSidStart(Integer sidStart) {
        this.sidStart = sidStart;
    }

    public Integer getSidEnd() {
        return sidEnd;
    }

    public void setSidEnd(Integer sidEnd) {
        this.sidEnd = sidEnd;
    }

    public Integer getCodenumberStart() {
        return codenumberStart;
    }

    public void setCodenumberStart(Integer codenumberStart) {
        this.codenumberStart = codenumberStart;
    }

    public Integer getCodenumberEnd() {
        return codenumberEnd;
    }

    public void setCodenumberEnd(Integer codenumberEnd) {
        this.codenumberEnd = codenumberEnd;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getZtype() {
        return ztype;
    }

    public void setZtype(Integer ztype) {
        this.ztype = ztype;
    }
}