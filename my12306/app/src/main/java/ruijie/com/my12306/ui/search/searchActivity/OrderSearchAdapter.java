package ruijie.com.my12306.ui.search.searchActivity;

import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import ruijie.com.my12306.R;
import ruijie.com.my12306.bean.OrderData;
import ruijie.com.my12306.util.TypeConvertUtil;

/**
 * Created by yum on 2016/9/3.
 */

public class OrderSearchAdapter extends BaseQuickAdapter<OrderData> {

    @Override
    protected void convert(BaseViewHolder baseViewHolder, OrderData orderData) {
        Log.d("convert", "convertconvertconvert");
        baseViewHolder.setText(R.id.textview_onumber, orderData.getOnumber() + "")
                .setText(R.id.textview_price, orderData.getPrice() + "元")
                .setText(R.id.textview_ztype, TypeConvertUtil.convertZtype(orderData.getZtype()))
                .setText(R.id.textview_startsite, orderData.getStartsite())
                .setText(R.id.textview_endsite, orderData.getEndsite())
                .setText(R.id.textview_znumber, orderData.getZnumber() + "")
                .setText(R.id.textview_carriage, orderData.getCarriagenumber() + "")
                .setText(R.id.textview_mileage, orderData.getMileage() + "KM")
                .setText(R.id.textview_odate, orderData.getOdate() + "")
                .setText(R.id.textview_nickname, orderData.getNickname() + "")
                .setText(R.id.textview_startdate, orderData.getStartdate() + "")
                .setText(R.id.textview_enddate, orderData.getEnddate() + "")
                .setText(R.id.textview_codenumber, orderData.getCodenumber() + "");

    }

    public OrderSearchAdapter(List<OrderData> list) {
        super(R.layout.item_order_search, list);
    }


}
