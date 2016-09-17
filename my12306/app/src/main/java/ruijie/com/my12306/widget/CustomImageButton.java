package ruijie.com.my12306.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.ImageButton;

/**
 * Created by prj on 2016/8/20.
 */

public class CustomImageButton extends ImageButton {

    private String text = null;
    private int textColor;
    Paint paint = new Paint();

    public CustomImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(textColor);
        canvas.drawText(text, canvas.getWidth()/2, (canvas.getHeight()/2)+12, paint);  //绘制文字
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }
}
