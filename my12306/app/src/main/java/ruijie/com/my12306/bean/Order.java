package ruijie.com.my12306.bean;

public class Order {
    private Integer oid;

    private Integer cid;

    private Integer uid;

    private Integer onumber;

    private Double price;

    private Integer status;

    private Integer ztype;

    private String startsite;

    private String endsite;

    private String znumber;

    private Integer carriagenumber;

    private Integer ttype;

    private Double mileage;

    private String odate;

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getOnumber() {
        return onumber;
    }

    public void setOnumber(Integer onumber) {
        this.onumber = onumber;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public String getStartsite() {
        return startsite;
    }

    public void setStartsite(String startsite) {
        this.startsite = startsite == null ? null : startsite.trim();
    }

    public String getEndsite() {
        return endsite;
    }

    public void setEndsite(String endsite) {
        this.endsite = endsite == null ? null : endsite.trim();
    }

    public String getZnumber() {
        return znumber;
    }

    public void setZnumber(String znumber) {
        this.znumber = znumber == null ? null : znumber.trim();
    }

    public Integer getCarriagenumber() {
        return carriagenumber;
    }

    public void setCarriagenumber(Integer carriagenumber) {
        this.carriagenumber = carriagenumber;
    }

    public Integer getTtype() {
        return ttype;
    }

    public void setTtype(Integer ttype) {
        this.ttype = ttype;
    }

    public Double getMileage() {
        return mileage;
    }

    public void setMileage(Double mileage) {
        this.mileage = mileage;
    }

    public String getOdate() {
        return odate;
    }

    public void setOdate(String odate) {
        this.odate = odate == null ? null : odate.trim();
    }
}