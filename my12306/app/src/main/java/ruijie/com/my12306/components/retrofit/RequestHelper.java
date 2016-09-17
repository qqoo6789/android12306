package ruijie.com.my12306.components.retrofit;

import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import ruijie.com.my12306.components.storage.UserStorage;
import ruijie.com.my12306.util.SecurityUtil;
import ruijie.com.my12306.util.SettingPrefUtil;

/**
 * Created by prj on 2016/8/15.
 */

public class RequestHelper {

    private Context mContext;
    private UserStorage mUserStorage;

    public RequestHelper(Context context,UserStorage userStorage){
        this.mContext = context;
        this.mUserStorage = userStorage;
    }

    public Map<String,String> getHttpRequestMap(){
        HashMap<String,String> map = new HashMap<>();
        map.put("client",getDeviceId());
        map.put("night",SettingPrefUtil.getNightModel(mContext) ? "1" : "0");
        if(mUserStorage.isLogin()){
            try {
                map.put("token", URLEncoder.encode(mUserStorage.getToken(), "UTF-8"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    public String getDeviceId() {
        String deviceId;
        TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        if(tm.getDeviceId()==null){
            deviceId = getAndroidId();
        }else {
            deviceId = tm.getDeviceId();
        }
        return deviceId;
    }

    public String getAndroidId() {
        return Settings.Secure.getString(mContext.getContentResolver(),Settings.Secure.ANDROID_ID);
    }

    public String getRequestSign(Map<String,String> map){
        ArrayList<Map.Entry<String,String>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, (lhs, rhs) -> lhs.getKey().compareTo(rhs.getKey()));

        StringBuilder builder = new StringBuilder();
        for (int i=0;i< list.size();i++){
            if (builder.length() > 0) {
                builder.append("&");
            }
            Map.Entry<String,String> map1 = list.get(i);
            builder.append(map1.getKey()).append("=").append(map.values());
        }
        builder.append(SettingPrefUtil.getHuPuSign(mContext));
        return SecurityUtil.getMD5(builder.toString());
    }
}
