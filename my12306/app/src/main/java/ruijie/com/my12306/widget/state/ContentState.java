package ruijie.com.my12306.widget.state;

import ruijie.com.my12306.R;

/**
 * Created by Administrator on 2016/8/17.
 */

public class ContentState extends AbstractShowState{

    @Override
    public void show(boolean animate) {
        showViewById(R.id.epf_content, animate);
    }

    @Override
    public void dismiss(boolean animate) {
        dismissViewById(R.id.epf_content, animate);
    }
}
