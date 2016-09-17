package ruijie.com.my12306.components.okhttp;

import java.io.File;
import java.io.IOException;

import dagger.Provides;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.BufferedSink;
import okio.Okio;

/**
 * Created by prj on 2016/8/15.
 */

public class OkHttpHelper {

    private OkHttpClient mOkHttpClient;

    public OkHttpHelper(OkHttpClient okHttpClient){
        this.mOkHttpClient = okHttpClient;
    }

    public Response execute(Request request) throws IOException{
        return mOkHttpClient.newCall(request).execute();
    }

    /**
     * 开启异步线程访问网络
     */
    public void enqueue(Request request, Callback responseCallback) {
        mOkHttpClient.newCall(request).enqueue(responseCallback);
    }

    /**
     * 开启异步线程访问网络, 且不在意返回结果（实现空callback）
     */
    public void enqueue(Request request) {
        mOkHttpClient.newCall(request).enqueue(new Callback() {

            @Override public void onFailure(Call call, IOException e) {

            }

            @Override public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

    public String getStringFromServer(String url)throws IOException{
        Request request = new Request.Builder().url(url).build();
        Response response = execute(request);
        if(response.isSuccessful()){
            return response.body().toString();
        }else {
            throw new IOException("Unexpected code " + response);
        }
    }

    public void httpDownload(String url,File target)throws Exception{
        Request request = new Request.Builder().url(url).build();
        Response response = mOkHttpClient.newCall(request).execute();
        if(response.isSuccessful()){
            BufferedSink sink = Okio.buffer(Okio.sink(target));
            sink.writeAll(response.body().source());
            sink.close();
        }else {
            throw new IOException("Unexpected code " + response);
        }
    }
}
