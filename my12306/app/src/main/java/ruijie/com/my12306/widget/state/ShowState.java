package ruijie.com.my12306.widget.state;

import android.view.View;
import android.view.animation.Animation;

/**
 * Created by Administrator on 2016/8/17.
 */

public interface ShowState {

    /**
     * 显示该状态
     *
     * @param animate 是否动画
     */
    public void show(boolean animate);

    /**
     * 隐藏该状态
     *
     * @param animate 是否动画
     */
    public void dismiss(boolean animate);

    /**
     * 设置FragmentView
     */
    public void setFragmentView(View fragmentView);

    /**
     * 进入动画
     */
    public void setAnimIn(Animation in);

    /**
     * 退出动画
     */
    public void setAnimOut(Animation out);
}
