package com.example.yfysoftware.simpleviewpager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;

import android.transition.Fade;
import android.view.View;
import android.widget.Button;

public class HolderActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.holder);

        Fade fade = new Fade();
        View decor = getWindow().getDecorView();
        fade.excludeTarget(decor.findViewById(R.id.action_bar_container), true);
        fade.excludeTarget(android.R.id.statusBarBackground, true);
        fade.excludeTarget(android.R.id.navigationBarBackground, true);
        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);


        Button button = (Button) findViewById(R.id.buton);
        button.setOnClickListener((View.OnClickListener) view -> {
            Intent intent =  new Intent(HolderActivity.this , MainActivity.class);
            ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) HolderActivity.this, button, "transition");
            startActivity(intent,activityOptionsCompat.toBundle());

        });



    }
}
