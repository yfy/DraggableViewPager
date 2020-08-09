package com.example.yfysoftware.simpleviewpager;

import android.os.Build;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.transition.Fade;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.commit451.elasticdragdismisslayout.ElasticDragDismissFrameLayout;
import com.commit451.elasticdragdismisslayout.ElasticDragDismissListener;

import xyz.klinker.android.drag_dismiss.activity.DragDismissActivity;


public class MainActivity extends DragDismissActivity {



    @Override
    public View onCreateContent(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
      //  super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);

        View v = inflater.inflate(R.layout.activity_main, parent, false);

        Fade fade = new Fade();
        View decor = getWindow().getDecorView();
        fade.excludeTarget(decor.findViewById(R.id.action_bar_container), true);
        fade.excludeTarget(android.R.id.statusBarBackground, true);
        fade.excludeTarget(android.R.id.navigationBarBackground, true);

        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);

        ViewPager viewPager= (ViewPager) v.findViewById(R.id.viewPager);
        ElasticDragDismissFrameLayout mDraggableFrame = (ElasticDragDismissFrameLayout) v.findViewById(R.id.elasticDragDismissFrameLayout);

        CustomPagerAdapter adapter=new CustomPagerAdapter(this, DataModel.getDataList());

        viewPager.setAdapter(adapter);

        mDraggableFrame.addListener(new ElasticDragDismissListener() {
            @Override
            public void onDrag(float elasticOffset, float elasticOffsetPixels, float rawOffset, float rawOffsetPixels) {}

            @Override
            public void onDragDismissed() {
                if (mDraggableFrame.getTranslationY() > 0 && Build.VERSION.SDK_INT >= 21) {
                    getWindow().setReturnTransition(
                            TransitionInflater.from(MainActivity.this)
                                    .inflateTransition(R.transition.about_return_downward));
                }

                finishAfterTransition();

            }
        });
        return v;
    }
}
