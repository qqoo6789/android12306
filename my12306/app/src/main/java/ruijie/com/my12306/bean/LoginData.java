package ruijie.com.my12306.bean;

/**
 * Created by Administrator on 2016/8/16.
 */

public class LoginData {


    /**
     * errortype :
     * status : success
     * data : {"uid":1,"identity":"学生","username":"BeJson","phone":"15622625081","nickname":"wuyiming","email":"270949894@qq.com","idcard":"44098120000000000","password":"123456"}
     * msg : value
     */

    private String errortype;
    private String status;
    /**
     * uid : 1
     * identity : 学生
     * username : BeJson
     * phone : 15622625081
     * nickname : wuyiming
     * email : 270949894@qq.com
     * idcard : 44098120000000000
     * password : 123456
     */

    private User data;
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

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
