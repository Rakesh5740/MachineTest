package com.tejendramachinetest.imagetaskmodule;

import java.util.ArrayList;
import java.util.List;

public interface iImageClickListner {

    void onImageClick (int position, ArrayList<ImageListModel.HitsBean> hitsBeans);

}
