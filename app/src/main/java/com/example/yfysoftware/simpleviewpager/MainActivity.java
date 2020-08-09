package com.example.yfysoftware.simpleviewpager;

import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.TransitionInflater;
import android.view.View;

import com.commit451.elasticdragdismisslayout.ElasticDragDismissFrameLayout;
import com.commit451.elasticdragdismisslayout.ElasticDragDismissListener;
import com.example.yfysoftware.simpleviewpager.R;


public class MainActivity extends AppCompatActivity {
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
        ElasticDragDismissFrameLayout mDraggableFrame = (ElasticDragDismissFrameLayout) findViewById(R.id.elasticDragDismissFrameLayout);

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
                if (Build.VERSION.SDK_INT >= 21) {
                    finishAfterTransition();
                } else {
                    finish();
                }
            }
        });

    }


}
