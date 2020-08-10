package com.example.yfysoftware.simpleviewpager;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

class CustomPagerAdapter extends PagerAdapter {

    private List<DataModel> itemList;

    private Context context;

    private LayoutInflater inflater;
    private float xCoOrdinate, yCoOrdinate;
    private double screenCenterX, screenCenterY;
    private int alpha;
    ImageView imageView;
    View view;
    public CustomPagerAdapter(Context context, List<DataModel> itemList){

        this.context=context;
        this.itemList=itemList;
        inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {


         view=inflater.inflate(R.layout.tek_satir, container, false);

        ImageView imageView= (ImageView) view.findViewById(R.id.image_item);
        TextView tv= (TextView) view.findViewById(R.id.textView);

        DataModel gecici=itemList.get(position);

       // view.getBackground().setAlpha(255);

        imageView.setImageResource(gecici.getImageID());
        tv.setText(gecici.getBaslik());

        //tek_Satir.xml'i container'a yani viewpager'a ekliyoruz.
        container.addView(view);

        final DisplayMetrics display = context.getResources().getDisplayMetrics();
        screenCenterX = display.widthPixels / 2;
        screenCenterY = (display.heightPixels - getStatusBarHeight()) / 2;
        final double maxHypo = Math.hypot(screenCenterX, screenCenterY);


        return view;

    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        //container=viewpager, object ise tek_satir xml'in root elementi olan FrameLayout
       container.removeView((FrameLayout) object);

    }
}
