package ruijie.com.my12306.bean;

/**
 * Created by yum on 2016/9/3.
 */

public class OrderData {

    private int oid;
    private int cid;
    private int uid;
    private int onumber;//订单号
    private double price;//价格
    private int status;//车票状态，已售出，未售出
    private int ztype;//座位类型
    private String startsite;//开始站
    private String endsite;//终点站
    private String znumber;//座位号
    private int carriagenumber;//车厢号
    private int ttype;//学生票
    private double mileage;//路程
    private String odate;//订票日期

    private String username;
    private String nickname;//订单人名字
    private String idcard;
    private String email;
    private String identity;
    private String startdate;//始乘车日期
    private String enddate;//终乘车日期
    private String codenumber;//车次名g123

    public OrderData(int oid, int cid, int uid, int onumber, double price, int status, int ztype, String startsite, String endsite, String znumber, int carriagenumber, int ttype, double mileage, String odate, String username, String nickname, String idcard, String email, String identity, String startdate, String enddate, String codenumber) {
        this.oid = oid;
        this.cid = cid;
        this.uid = uid;
        this.onumber = onumber;
        this.price = price;
        this.status = status;
        this.ztype = ztype;
        this.startsite = startsite;
        this.endsite = endsite;
        this.znumber = znumber;
        this.carriagenumber = carriagenumber;
        this.ttype = ttype;
        this.mileage = mileage;
        this.odate = odate;
        this.username = username;
        this.nickname = nickname;
        this.idcard = idcard;
        this.email = email;
        this.identity = identity;
        this.startdate = startdate;
        this.enddate = enddate;
        this.codenumber = codenumber;
    }

    @Override
    public String toString() {
        return "OrderData{" +
                "oid=" + oid +
                ", cid=" + cid +
                ", uid=" + uid +
                ", onumber=" + onumber +
                ", price=" + price +
                ", status=" + status +
                ", ztype=" + ztype +
                ", startsite='" + startsite + '\'' +
                ", endsite='" + endsite + '\'' +
                ", znumber='" + znumber + '\'' +
                ", carriagenumber=" + carriagenumber +
                ", ttype=" + ttype +
                ", mileage=" + mileage +
                ", odate='" + odate + '\'' +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", idcard='" + idcard + '\'' +
                ", email='" + email + '\'' +
                ", identity='" + identity + '\'' +
                ", startdate='" + startdate + '\'' +
                ", enddate='" + enddate + '\'' +
                ", codenumber='" + codenumber + '\'' +
                '}';
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getOnumber() {
        return onumber;
    }

    public void setOnumber(int onumber) {
        this.onumber = onumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getZtype() {
        return ztype;
    }

    public void setZtype(int ztype) {
        this.ztype = ztype;
    }

    public String getStartsite() {
        return startsite;
    }

    public void setStartsite(String startsite) {
        this.startsite = startsite;
    }

    public String getEndsite() {
        return endsite;
    }

    public void setEndsite(String endsite) {
        this.endsite = endsite;
    }

    public String getZnumber() {
        return znumber;
    }

    public void setZnumber(String znumber) {
        this.znumber = znumber;
    }

    public int getCarriagenumber() {
        return carriagenumber;
    }

    public void setCarriagenumber(int carriagenumber) {
        this.carriagenumber = carriagenumber;
    }

    public int getTtype() {
        return ttype;
    }

    public void setTtype(int ttype) {
        this.ttype = ttype;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public String getOdate() {
        return odate;
    }

    public void setOdate(String odate) {
        this.odate = odate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
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
}
