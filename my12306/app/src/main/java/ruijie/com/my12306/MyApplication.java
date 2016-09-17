package ruijie.com.my12306;

import android.app.Application;
import android.content.Context;

import com.anupcowkur.reservoir.Reservoir;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.util.ByteConstants;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.SimpleProgressiveJpegConfig;
import com.google.gson.Gson;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.util.FileDownloadHelper;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import ruijie.com.my12306.components.storage.UserStorage;
import ruijie.com.my12306.injector.component.ApplicationComponent;
import ruijie.com.my12306.injector.component.DaggerApplicationComponent;
import ruijie.com.my12306.injector.moudel.ApplicationMoudle;
import ruijie.com.my12306.widget.citySelector.db.DBManager;

/**
 * Created by prj on 2016/8/15.
 */

public class MyApplication extends Application {

    private DBManager dbHelper;
    public static Context context ;
    private static final int MAX_HEAP_SIZE = (int) Runtime.getRuntime().maxMemory();
    public static final int MAX_DISK_CACHE_SIZE = 50 * ByteConstants.MB;
    public static final int MAX_MEMORY_CACHE_SIZE = MAX_HEAP_SIZE / 8;
    public static final long ONE_KB = 1024L;
    public static final long ONE_MB = ONE_KB * 1024L;
    public static final long CACHE_DATA_MAX_SIZE = ONE_MB * 3L;
    private ApplicationComponent mApplicationComponent;

    @Inject UserStorage mUserStorage;
    @Inject OkHttpClient mOkHttpClient;

    @Override
    public void onCreate() {
        super.onCreate();
        initComponent();
        initUser();
        initReservoir();
        initCitySelectorDB();
        FileDownloader.init(this, () -> mOkHttpClient);
        initFrescoConfig();
        context = this;
    }

    private void initComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationMoudle(new ApplicationMoudle(this))
                .build();
        mApplicationComponent.inject(this);
    }

    private void initUser() {

    }

    private void initReservoir() {
        try {
            Reservoir.init(this, CACHE_DATA_MAX_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initCitySelectorDB() {
        //导入数据库
        dbHelper = new DBManager(this);
        dbHelper.openDatabase();
    }

    private void initFrescoConfig() {
        final MemoryCacheParams bitmapCacheParams =
                new MemoryCacheParams(MAX_MEMORY_CACHE_SIZE, // Max total size of elements in the cache
                        Integer.MAX_VALUE,                     // Max entries in the cache
                        MAX_MEMORY_CACHE_SIZE, // Max total size of elements in eviction queue
                        Integer.MAX_VALUE,                     // Max length of eviction queue
                        Integer.MAX_VALUE);

        ImagePipelineConfig config = OkHttpImagePipelineConfigFactory.newBuilder(this,mOkHttpClient)
                .setProgressiveJpegConfig(new SimpleProgressiveJpegConfig())
                .setBitmapMemoryCacheParamsSupplier(()->bitmapCacheParams)
                .setMainDiskCacheConfig(DiskCacheConfig.newBuilder(this).setMaxCacheSize(MAX_DISK_CACHE_SIZE).build())
                .setDownsampleEnabled(true)
                .build();
        Fresco.initialize(this,config);
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
