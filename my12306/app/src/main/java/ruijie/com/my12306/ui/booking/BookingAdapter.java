package ruijie.com.my12306.ui.booking;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import ruijie.com.my12306.R;
import ruijie.com.my12306.bean.AddressItem;

/**
 * Created by prj on 2016/8/20.
 */

public class BookingAdapter extends BaseQuickAdapter<AddressItem> {

    public BookingAdapter(List<AddressItem> data) {
        super(R.layout.item_booking,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, AddressItem addressItem) {
        baseViewHolder.setText(R.id.tv_from,addressItem.getFrom())
                .setText(R.id.tv_to,addressItem.getTo());
    }
}
