package org.launchcode.uTrain.data;

import java.util.ArrayList;
import java.util.Collections;

public class BackgroundImage {



    String image1 = "https://s3-eu-west-2.amazonaws.com/theiceco.co.uk/wp-content/uploads/2020/07/Image-Running.jpg";
    String image2 = "https://www.stay-trained.com/wp-content/uploads/2018/04/Supplement-Fitness.jpg";
    String image3 = "https://fashionmagazine.com/wp-content/uploads/2019/12/iStock-871070868.jpg";
    String image4 = "https://ychef.files.bbci.co.uk/1376x774/p07ztf1q.jpg";
    String image5 = "https://www.hhs.k-state.edu/kines/kineseducation/images/1-weight_lifting.jpg";
    String image6 = "https://cdn2.cyclist.co.uk/sites/cyclist/files/2020/01/acp-algarve-road_12.jpg";
    String image7 = "https://www.wellandgood.com/wp-content/uploads/2020/02/GettyImages-1128646064.jpg";
    String image8 = "https://cdn.shopify.com/s/files/1/0009/1112/products/personal-trainer_1800x.jpg?v=1586368428";


            public String randomImageGenerator() {
                ArrayList<String> images = new ArrayList<>();
                Collections.addAll(images, image1, image2, image3, image4, image5, image6, image7, image8);

                int num = (int) Math.floor(Math.random()*8);

                return images.get(num);

            }
}
