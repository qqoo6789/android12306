package ruijie.com.my12306.util;

/**
 * Created by yum on 2016/9/3.
 */

public class TypeConvertUtil {
    public static String convertZtype(int ztype){
        switch (ztype){
            case 0:
                return "商务";
            case 1:
                return "一等座";
            case 2:
                return "二等座";
        }
        return "商务";
    }
}
