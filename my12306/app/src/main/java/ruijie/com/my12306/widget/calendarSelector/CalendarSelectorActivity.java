package ruijie.com.my12306.widget.calendarSelector;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.widget.ListView;

import ruijie.com.my12306.R;
import ruijie.com.my12306.event.calendarEvent;
import ruijie.com.my12306.ui.base.BaseEmptyActivity;
import ruijie.com.my12306.ui.main.MainPresenter;
import ruijie.com.my12306.util.AnimationUtil;
import ruijie.com.my12306.util.RxBus;
import ruijie.com.my12306.widget.swipeback.SwipeBackActivityHelper;

/**
 * @author lvning
 * @version create time:2014-10-29_上午9:56:45
 * @Description 预订日选择
 */
public class CalendarSelectorActivity extends BaseEmptyActivity {

	/**
	 * 可选天数
	 */
	public static final String DAYS_OF_SELECT = "days_of_select";
	/**
	 * 上次预订日
	 */
	public static final String ORDER_DAY = "order_day";

	private int daysOfSelect;
	private String orderDay;

	private ListView listView;
	private Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendar_selector);
		daysOfSelect = getIntent().getIntExtra(DAYS_OF_SELECT, 30);
		orderDay = getIntent().getStringExtra(ORDER_DAY);
		listView = (ListView) findViewById(R.id.lv_calendar);
		toolbar = (Toolbar) findViewById(R.id.toolbar);

		CalendarListAdapter adapter = new CalendarListAdapter(this, daysOfSelect, orderDay);
		listView.setAdapter(adapter);
		initToolBar(toolbar);
		setTitle("选择出发日期");

		adapter.setOnCalendarOrderListener(orderInfo -> {
			calendarEvent.getInstance().setDate(orderInfo);
			RxBus.getDefault().post(calendarEvent.getInstance());
			finish();
        });

		listView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
			@Override
			public boolean onPreDraw() {
				listView.getViewTreeObserver().removeOnPreDrawListener(this);
				Animator animator = AnimationUtil.getCircularReveal(listView,2,600);
				animator.start();
				return true;
			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
