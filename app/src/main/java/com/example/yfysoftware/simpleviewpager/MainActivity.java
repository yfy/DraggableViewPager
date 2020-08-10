package com.example.yfysoftware.simpleviewpager;

import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.TransitionInflater;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.commit451.elasticdragdismisslayout.ElasticDragDismissFrameLayout;
import com.commit451.elasticdragdismisslayout.ElasticDragDismissListener;
import com.example.yfysoftware.simpleviewpager.R;


public class MainActivity extends AppCompatActivity {
    private float xCoOrdinate, yCoOrdinate;
    private double screenCenterX, screenCenterY;
    private int alpha;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fade fade = new Fade();
        View decor = getWindow().getDecorView();
        fade.excludeTarget(decor.findViewById(R.id.action_bar_container), true);
        fade.excludeTarget(android.R.id.statusBarBackground, true);
        fade.excludeTarget(android.R.id.navigationBarBackground, true);

        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);

        ViewPager viewPager= (ViewPager) findViewById(R.id.viewPager);
        FrameLayout mDraggableFrame = (FrameLayout) findViewById(R.id.elasticDragDismissFrameLayout);
        view = mDraggableFrame;
        view.getBackground().setAlpha(255);
        CustomPagerAdapter adapter=new CustomPagerAdapter(this, DataModel.getDataList());

        viewPager.setAdapter(adapter);

        final DisplayMetrics display = getResources().getDisplayMetrics();
        screenCenterX = display.widthPixels / 2;
        screenCenterY = (display.heightPixels - getStatusBarHeight()) / 2;
        final double maxHypo = Math.hypot(screenCenterX, screenCenterY);

        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                /**
                 * Calculate hypo value of current imageview position according to center
                 */
                double centerYPos = viewPager.getY() + (viewPager.getHeight() / 2);
                double centerXPos = viewPager.getX() + (viewPager.getWidth() / 2);
                double a = screenCenterX - centerXPos;
                double b = screenCenterY - centerYPos;
                double hypo = Math.hypot(a, b);

                /**
                 * change alpha of background of layout
                 */
                alpha = (int) (hypo * 255) / (int) maxHypo;
                if (alpha < 255)
                    view.getBackground().setAlpha(255 - alpha);

               switch (event.getActionMasked()) {
                   case MotionEvent.ACTION_DOWN:
                       xCoOrdinate = viewPager.getX() - event.getRawX();
                       yCoOrdinate = viewPager.getY() - event.getRawY();
                       break;
                   case MotionEvent.ACTION_MOVE:
                       viewPager.animate().x(event.getRawX() + xCoOrdinate).y(event.getRawY() + yCoOrdinate).setDuration(0).start();
                       break;
                   case MotionEvent.ACTION_UP:
                       if (alpha > 70) {
                           supportFinishAfterTransition();
                           return false;
                       } else {
                           viewPager.animate().x(0).y((float) screenCenterY - viewPager.getHeight() / 2).setDuration(100).start();
                           view.getBackground().setAlpha(255);
                       }
                   default:
                       return false;
               }
                return true;
            }

        });



    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

}
