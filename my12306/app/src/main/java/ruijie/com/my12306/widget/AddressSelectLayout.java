package ruijie.com.my12306.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.OnClick;
import ruijie.com.my12306.R;
import ruijie.com.my12306.util.TextUtil;

/**
 * Created by prj on 2016/8/20.
 */

public class AddressSelectLayout extends LinearLayout{

    private TextView tv_from;
    private TextView tv_to;
    private TextView tv_sync;

    private String leftText;
    private String rightText;
    private String centerText;
    private float leftTextSize;
    private float rightTextSize;
    private float centerTextSize;
    private int leftTextColor;
    private int rightTextColor;
    private int centerTextColr;
    private Drawable leftBackground;
    private Drawable rightBackground;
    private Drawable centerBcakground;

    private final int DEFAULT_COLOR = 0xff000000;
    private final float DEFAULT_TEXT_SIZE = 20;
    private Animation animation_fade_in;
    private Animation animation_fade_out;
    private Animation animation_rotate_self;

    private OnAddressSelectListener selectListener;

    public AddressSelectLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(attrs);
    }

    public AddressSelectLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AddressSelectLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initViews(attrs);
    }

    private void initViews(AttributeSet attrs) {
        inflate(getContext(), R.layout.item_address_select, this);
        tv_from = (TextView) findViewById(R.id.tv_from);
        tv_to = (TextView) findViewById(R.id.tv_to);
        tv_sync = (TextView) findViewById(R.id.tv_sync);
        ButterKnife.bind(this);

        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.AddressSelectLayout);
        leftText = array.getString(R.styleable.AddressSelectLayout_leftText);
        rightText = array.getString(R.styleable.AddressSelectLayout_rightText);
        centerText = array.getString(R.styleable.AddressSelectLayout_centerText);
        leftTextSize = array.getDimension(R.styleable.AddressSelectLayout_leftTextSize, DEFAULT_TEXT_SIZE);
        rightTextSize = array.getDimension(R.styleable.AddressSelectLayout_rightTextSize, DEFAULT_TEXT_SIZE);
        centerTextSize = array.getDimension(R.styleable.AddressSelectLayout_centerTextSize, DEFAULT_TEXT_SIZE);
        leftTextColor = array.getColor(R.styleable.AddressSelectLayout_leftTextColor, DEFAULT_COLOR);
        rightTextColor = array.getColor(R.styleable.AddressSelectLayout_rightTextColor, DEFAULT_COLOR);
        centerTextColr = array.getColor(R.styleable.AddressSelectLayout_centerTextColor, DEFAULT_COLOR);
        leftBackground = array.getDrawable(R.styleable.AddressSelectLayout_leftTextBackground);
        rightBackground = array.getDrawable(R.styleable.AddressSelectLayout_righTextBackground);
        centerBcakground = array.getDrawable(R.styleable.AddressSelectLayout_centerTextBackground);

        setLeftText(leftText);
        setRightText(rightText);
        setCenterText(centerText);
        setLeftTextSize(leftTextSize);
        setRightTextSize(rightTextSize);
        setCenterTextSize(centerTextSize);
        setLeftTextColor(leftTextColor);
        setRightTextColor(rightTextColor);
        setCenterTextColor(centerTextColr);
        setLeftBackground(leftBackground);
        setRightBackground(rightBackground);
        setCenterBackground(centerBcakground);
        array.recycle();

        animation_fade_in = AnimationUtils.loadAnimation(getContext(),R.anim.fade_in);
        animation_fade_out = AnimationUtils.loadAnimation(getContext(),R.anim.fade_out);
        animation_rotate_self = AnimationUtils.loadAnimation(getContext(),R.anim.rotate_self);
    }

    public void setRightBackground(Drawable rightBackground) {
        if (rightBackground != null)
            tv_to.setBackground(rightBackground);
        else
            tv_to.setBackground(getResources().getDrawable(R.drawable.item_select_clickable));
    }

    public void setLeftBackground(Drawable leftBackground) {
        if (leftBackground != null)
            tv_from.setBackground(leftBackground);
        else
            tv_from.setBackground(getResources().getDrawable(R.drawable.item_select_clickable));
    }

    public void setCenterBackground(Drawable centerBackground) {
        if (centerBackground != null)
            tv_sync.setBackground(centerBackground);
        else
            tv_sync.setBackground(getResources().getDrawable(R.drawable.item_select_clickable));
    }

    public void setLeftTextColor(int leftTextColor) {
        tv_from.setTextColor(leftTextColor);
    }

    public void setRightTextColor(int rightTextColor) {
        tv_to.setTextColor(rightTextColor);
    }

    public void setCenterTextColor(int centerTextColor) {
        tv_sync.setTextColor(centerTextColor);
    }

    public void setRightTextSize(float rightTextSize) {
        tv_to.setTextSize(rightTextSize);
    }

    public void setLeftTextSize(float leftTextSize) {
        tv_from.setTextSize(leftTextSize);
    }

    public void setCenterTextSize(float centerTextSize) {
        tv_sync.setTextSize(centerTextSize);
    }

    public void setRightText(String rightText) {
        tv_to.setText(rightText);
    }

    public void setLeftText(String leftText) {
        tv_from.setText(leftText);
    }

    public void setCenterText(String leftText) {
        if(leftText==null||leftText.equals(""))
            tv_sync.setText("⇄");
        else
            tv_sync.setText(centerText);
    }

    @OnClick({R.id.tv_from, R.id.tv_sync, R.id.tv_to})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_from:
                if(selectListener!=null)
                selectListener.OnAddressFromClick();
                break;
            case R.id.tv_sync:
                clickSync(TextUtil.getText(tv_from.getText().toString()),TextUtil.getText(tv_to.getText().toString()));
                break;
            case R.id.tv_to:
                if(selectListener!=null)
                selectListener.OnAddressToClick();
                break;
        }
    }

    private void clickSync(String from,String to) {
        tv_from.setText(to);
        tv_to.setText(from);
        tv_from.startAnimation(animation_fade_in);
        tv_to.startAnimation(animation_fade_in);
        tv_sync.startAnimation(animation_rotate_self);
    }

    public void setAddress(String from,String to) {
        tv_from.setText(to);
        tv_to.setText(from);
        tv_from.startAnimation(animation_fade_in);
        tv_to.startAnimation(animation_fade_in);
        //tv_sync.startAnimation(animation_rotate_self);
    }

    public void OnAddressSelectListener(OnAddressSelectListener selectListener){
        this.selectListener = selectListener;
    }

    public interface OnAddressSelectListener{
        void OnAddressFromClick();

        void OnAddressToClick();
    }

    public String getTv_from() {
        return TextUtil.getText(tv_from);
    }

    public String getTv_to() {
        return TextUtil.getText(tv_to);
    }

    public void setTv_from(String s) {
        this.tv_from.setText(s);
    }

    public void setTv_to(String s) {
        this.tv_to.setText(s);
    }
}
