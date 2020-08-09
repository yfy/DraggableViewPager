package com.example.yfysoftware.simpleviewpager;


import com.example.yfysoftware.simpleviewpager.R;

import java.util.ArrayList;
import java.util.List;

public class DataModel {

    private int ImageID;

    private String baslik;

    public DataModel(int imageID, String baslik){
       /*
        this.ImageID=imageID;
        this.baslik=baslik;
        */

        setImageID(imageID);
        setBaslik(baslik);

    }

    public int getImageID() {
        return ImageID;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setImageID(int imageID) {
        ImageID = imageID;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }


    public static List<DataModel> getDataList(){

        List<DataModel> itemList=new ArrayList<>();

        int[] imageIDs=new int[]{
                R.drawable.image1, R.drawable.image2, R.drawable.image3,
                R.drawable.image4, R.drawable.image5, R.drawable.image6

        };

        String[] imageBasliklari=new String[]{
          "Resim 1", "Resim 2", "Resim 3", "Resim 4", "Resim 5", "Resim 6"
        };

        for (int i=0; i<imageIDs.length; i++){
            itemList.add(new DataModel(imageIDs[i], imageBasliklari[i]));

        }



        return itemList;

    }

}
