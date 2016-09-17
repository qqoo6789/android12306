package ruijie.com.my12306.util;

import android.util.Log;

import com.anupcowkur.reservoir.Reservoir;
import com.anupcowkur.reservoir.ReservoirGetCallback;

import java.io.IOException;
import java.lang.reflect.Type;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Administrator on 2016/8/25.
 */

public class ReservoirUtil {

    private static final String TAG = "ReservoirUtil";
    private static ReservoirUtil instance;

    public static ReservoirUtil getInstance(){
        if(instance == null){
            synchronized (ReservoirUtil.class){
                if (instance == null)
                    instance = new ReservoirUtil();
            }
        }
        return instance;
    }

    public void put(String key,Object object){
        if(object==null)
            return;
        Reservoir.putUsingObservable(key,object)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {
                    Log.i(TAG, "Put success: key=" + key + " object=" + object.getClass());
                },throwable -> throwable.printStackTrace());
    }

    public boolean contains(String key) {
        try {
            return Reservoir.contains(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void delete(String key) {
        if (this.contains(key)) {
            Reservoir.deleteUsingObservable(key)
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe(aBoolean -> {
                        Log.w(TAG, "delete " + aBoolean);
                    }, throwable -> throwable.printStackTrace());
        }
    }

    public void refresh(String key,Object object){
        if(this.contains(key)){
            Reservoir.deleteUsingObservable(key)
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe(aBoolean -> {
                        ReservoirUtil.getInstance().put(key, object);
                    },throwable -> throwable.printStackTrace());
        }else {
            this.put(key,object);
        }
    }

    public <T> Observable<T> get(String key,Class<T> tClass){
        return Reservoir.getUsingObservable(key,tClass);
    }

    public <T> Observable<T> get(Class<T> clazz) {
        String key = clazz.getSimpleName();
        return get(key, clazz);
    }

    public <T> void get(final String key, final Type typeOfT, final ReservoirGetCallback<T> callback) {
        Reservoir.getAsync(key, typeOfT, callback);
    }

}
