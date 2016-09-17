package ruijie.com.my12306.ui.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import ruijie.com.my12306.R;
import ruijie.com.my12306.util.BlurUtil;

/**
 * Created by prj on 2016/8/20.
 */

public class testActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.layout_test);

        /*iv = (ImageView) findViewById(R.id.iv);
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.mipmap.girl);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(BlurUtil.fastblur(this,bitmap,120));
        iv.setBackground(bitmapDrawable);*/
    }
}
