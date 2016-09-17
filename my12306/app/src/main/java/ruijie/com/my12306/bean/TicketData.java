package ruijie.com.my12306.bean;

/**
 * Created by Administrator on 2016/9/2.
 */
public class TicketData {

    private Integer zid;
    private Integer cid;
    private String carriagenumber;
    private String ztype;
    private String startdate;
    private String enddate;
    private String codenumber;
    private Integer codenumber_start;
    private Integer codenumber_end;
    private Integer sid_start;
    private Integer sid_end;

    public Integer getZid() {
        return zid;
    }

    public void setZid(Integer zid) {
        this.zid = zid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCarriagenumber() {
        return carriagenumber;
    }

    public void setCarriagenumber(String carriagenumber) {
        this.carriagenumber = carriagenumber;
    }

    public String getZtype() {
        return ztype;
    }

    public void setZtype(String ztype) {
        this.ztype = ztype;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getCodenumber() {
        return codenumber;
    }

    public void setCodenumber(String codenumber) {
        this.codenumber = codenumber;
    }

    public Integer getCodenumber_start() {
        return codenumber_start;
    }

    public void setCodenumber_start(Integer codenumber_start) {
        this.codenumber_start = codenumber_start;
    }

    public Integer getCodenumber_end() {
        return codenumber_end;
    }

    public void setCodenumber_end(Integer codenumber_end) {
        this.codenumber_end = codenumber_end;
    }

    public Integer getSid_start() {
        return sid_start;
    }

    public void setSid_start(Integer sid_start) {
        this.sid_start = sid_start;
    }

    public Integer getSid_end() {
        return sid_end;
    }

    public void setSid_end(Integer sid_end) {
        this.sid_end = sid_end;
    }

    //carriagenumber=1&cid=2&codenumber=2&codenumberEnd=3&codenumberStart=2&enddate=2016-9-12&sidEnd=6&sidStart=5&startdate=2016-9-12&zid=3&ztype=1
}
