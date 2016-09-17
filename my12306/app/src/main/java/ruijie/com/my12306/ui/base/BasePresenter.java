package ruijie.com.my12306.ui.base;

import android.support.annotation.NonNull;

/**
 * Created by Administrator on 2016/8/17.
 */

public interface BasePresenter<T extends BaseView> {

    void attachView(@NonNull T View);

    void detachView();
}
