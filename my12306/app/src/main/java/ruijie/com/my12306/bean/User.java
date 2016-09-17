package ruijie.com.my12306.bean;

/**
 * Created by Administrator on 2016/9/1.
 */

public class User {
    private String uid;

    private String username;

    private String password;

    private String nickname;

    private String idcard;

    private String email;

    private String identity;

    private String phone;

    private String url;

    private Integer status;

    private String token;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity == null ? null : identity.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User(String uid, String username, String password, String nickname, String idcard, String email, String identity, String phone, String url, Integer status, String token) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.idcard = idcard;
        this.email = email;
        this.identity = identity;
        this.phone = phone;
        this.url = url;
        this.status = status;
        this.token = token;
    }
}