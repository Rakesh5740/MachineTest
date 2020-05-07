package com.tejendramachinetest.imagetaskmodule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.tejendramachinetest.Constant;
import com.tejendramachinetest.R;

import java.util.ArrayList;
import java.util.List;

public class FullImageActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private List<ImageListModel.HitsBean> hitsBeans;
    private ImageButton ibLeft,ibRight;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);

        Bundle bundle = getIntent().getBundleExtra(Constant.IMAGELIST);
        assert bundle != null;
        position = bundle.getInt(Constant.POSITION);
        hitsBeans = (ArrayList<ImageListModel.HitsBean>) bundle.getSerializable(Constant.IMAGELIST);

        viewPager = findViewById(R.id.viewPager);
        ibLeft = findViewById(R.id.ib_left);
        ibRight = findViewById(R.id.ib_right);
        ibLeft.setOnClickListener(this);
        ibRight.setOnClickListener(this);
        viewPagerAdapter = new ViewPagerAdapter(hitsBeans,this);
        viewPager.setAdapter(viewPagerAdapter);
        setCurrentItemOfViewPager(position);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_left:
                if(position>=1)
                    position = position-1;
                setCurrentItemOfViewPager(position);
                break;
            case R.id.ib_right:
                if(position <=hitsBeans.size())
                    position = position+1;
                setCurrentItemOfViewPager(position);
                break;
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        this.position = position;
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void setCurrentItemOfViewPager(int position){

        viewPager.setCurrentItem(position);

    }
}
