package ruijie.com.my12306.ui.booking.searchActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

import ruijie.com.my12306.R;
import ruijie.com.my12306.bean.CheciData;
import ruijie.com.my12306.bean.Station;
import ruijie.com.my12306.event.ActivityEvent;
import ruijie.com.my12306.ui.booking.orderActivty.BookingOrderActivity;
import ruijie.com.my12306.util.RxBus;

/**
 * Created by prj on 2016/9/3.
 */

public class ExpandableItemAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity> {

    private static final String TAG = ExpandableItemAdapter.class.getSimpleName();

    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;
    private Context context;

    public ExpandableItemAdapter(Context context,List<MultiItemEntity> data) {
        super(data);
        this.context = context;
        addItemType(TYPE_LEVEL_0, R.layout.item_checi_select);
        addItemType(TYPE_LEVEL_1, R.layout.item_checi_station);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MultiItemEntity multiItemEntity) {
        switch (baseViewHolder.getItemViewType()){
            case TYPE_LEVEL_0:
                CheciData checiData = (CheciData) multiItemEntity;
                baseViewHolder.setText(R.id.textview_cnumber,checiData.getCodenumber())
                        .setText(R.id.textview_from_city,checiData.getStartsite())
                        .setText(R.id.textview_to_city,checiData.getEndsite())
                        .setText(R.id.textview_sum_time, "0")
                        .setText(R.id.textview_to_time,checiData.getEnddate())
                        .setText(R.id.textview_from_time,checiData.getStartdate());

                baseViewHolder.getView(R.id.linearlayout_switch).setOnClickListener(view -> {
                    int pos = baseViewHolder.getAdapterPosition();
                    if (checiData.isExpanded()) {
                        collapse(pos);
                    } else {
                        expand(pos);
                    }
                });

                baseViewHolder.itemView.setOnClickListener(view -> {
                    ActivityEvent.getInstance().setCheciData(checiData);
                    RxBus.getDefault().post(ActivityEvent.getInstance());
                });
                break;
            case TYPE_LEVEL_1:
                Station station = (Station) multiItemEntity;
                baseViewHolder.setText(R.id.tv_cnumber,station.getCodenumber()+"")
                        .setText(R.id.tv_from,station.getName())
                        .setText(R.id.tv_out,station.getOutbounddate())
                        .setText(R.id.tv_stop,"3分钟")
                        .setText(R.id.tv_start,station.getArrivedate());
                break;
        }
    }

}

