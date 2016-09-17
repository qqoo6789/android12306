package ruijie.com.my12306.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import ruijie.com.my12306.ui.booking.searchActivity.ExpandableItemAdapter;

public class Station implements MultiItemEntity{
    private Integer sid;

    private Integer cid;

    private Double premilege;

    private Double preprice;

    private Integer codenumber;

    private String arrivedate;

    private String outbounddate;

    private String name;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Double getPremilege() {
        return premilege;
    }

    public void setPremilege(Double premilege) {
        this.premilege = premilege;
    }

    public Double getPreprice() {
        return preprice;
    }

    public void setPreprice(Double preprice) {
        this.preprice = preprice;
    }

    public Integer getCodenumber() {
        return codenumber;
    }

    public void setCodenumber(Integer codenumber) {
        this.codenumber = codenumber;
    }

    public String getArrivedate() {
        return arrivedate;
    }

    public void setArrivedate(String arrivedate) {
        this.arrivedate = arrivedate == null ? null : arrivedate.trim();
    }

    public String getOutbounddate() {
        return outbounddate;
    }

    public void setOutbounddate(String outbounddate) {
        this.outbounddate = outbounddate == null ? null : outbounddate.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getItemType() {
        return ExpandableItemAdapter.TYPE_LEVEL_1;
    }

    public Station(Integer sid, Integer cid, Double premilege, Double preprice, Integer codenumber, String arrivedate, String outbounddate, String name) {
        this.sid = sid;
        this.cid = cid;
        this.premilege = premilege;
        this.preprice = preprice;
        this.codenumber = codenumber;
        this.arrivedate = arrivedate;
        this.outbounddate = outbounddate;
        this.name = name;
    }
}