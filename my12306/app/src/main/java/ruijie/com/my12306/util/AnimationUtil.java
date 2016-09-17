package ruijie.com.my12306.util;

import android.animation.Animator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import io.codetail.animation.ViewAnimationUtils;

/**
 * Created by prj on 2016/8/26.
 */

public class AnimationUtil {

    public static Animator getCircularReveal(View view,int MultipleRadius,int Duration){

        int cx = (view.getLeft() + view.getRight()) / 2;
        int cy = (view.getTop() + view.getBottom()) / 2;

        int dx = Math.max(cx, view.getWidth() - cx);
        int dy = Math.max(cy, view.getHeight() - cy);
        float finalRadius = (float) Math.hypot(dx, dy);

        // Android native animator
        Animator animator =
                ViewAnimationUtils.createCircularReveal(view, 0, 0, 0, finalRadius*MultipleRadius);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(Duration);
        return animator;
    }
}
