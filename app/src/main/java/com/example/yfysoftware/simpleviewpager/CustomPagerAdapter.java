package com.example.yfysoftware.simpleviewpager;


import android.content.Context;
import androidx.viewpager.widget.PagerAdapter;
import android.view.LayoutInflater;
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


        View view=inflater.inflate(R.layout.tek_satir, container, false);

        ImageView imgView= (ImageView) view.findViewById(R.id.image_item);
        TextView tv= (TextView) view.findViewById(R.id.textView);

        DataModel gecici=itemList.get(position);

        imgView.setImageResource(gecici.getImageID());
        tv.setText(gecici.getBaslik());

        //tek_Satir.xml'i container'a yani viewpager'a ekliyoruz.
        container.addView(view);

        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        //container=viewpager, object ise tek_satir xml'in root elementi olan FrameLayout
       container.removeView((FrameLayout) object);

    }
}
