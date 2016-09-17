package ruijie.com.my12306.widget;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import ruijie.com.my12306.R;

/**
 * Created by prj on 2016/8/23.
 */

public class emptyAdapter extends BaseQuickAdapter<String> {

    public emptyAdapter(List<String> data) {
        super(R.layout.item_booking,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder,String s) {
        baseViewHolder.setText(R.id.tv_from,s)
                .setText(R.id.tv_to,s);
    }
}
