package com.tejendramachinetest.imagetaskmodule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tejendramachinetest.Constant;
import com.tejendramachinetest.R;
import com.tejendramachinetest.utils.RecyclerViewMargin;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ImageListActivity extends AppCompatActivity implements iImageClickListner, SearchView.OnQueryTextListener {

    private Disposable disposable;
    private RecyclerView rvImageList;
    private ArrayList<ImageListModel.HitsBean> hitsBeans;
    private ImageListAdapter imageListAdapter;
    private FloatingActionButton fbViewMode;
    private RelativeLayout loaderView;
    private TextView tvLoaderMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        SearchView searchView = findViewById(R.id.searchView);
        rvImageList = findViewById(R.id.rv_image);
        fbViewMode = findViewById(R.id.fb_view_mode);
        loaderView = findViewById(R.id.loader_view);
        tvLoaderMessage = findViewById(R.id.tv_loadertext);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        iApiService apiService = ApiClient.getClient(getApplicationContext())
                .create(iApiService.class);
        FetchImages(apiService);


        hitsBeans = new ArrayList<>();
        imageListAdapter = new ImageListAdapter(hitsBeans, this);
        rvImageList.setAdapter(imageListAdapter);

        setMarginBetweenCells();

        fbViewMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeGridToListToGrid(v);
            }
        });
        searchView.setOnQueryTextListener(this);

    }

    private void setMarginBetweenCells() {
        RecyclerViewMargin decoration = new RecyclerViewMargin(10, 1);
        rvImageList.addItemDecoration(decoration);
    }

    private void changeGridToListToGrid(View v) {
        String tag = v.getTag().toString();

        if (tag.equals("List")) {
            changeImageListView(Constant.GRIDMODE);
            v.setTag("Grid");
            fbViewMode.setImageResource(R.drawable.list);
        } else {
            changeImageListView(Constant.LISTMODE);
            v.setTag("List");
            fbViewMode.setImageResource(R.drawable.grid);

        }
    }

    private void changeImageListView(int mode) {
        GridLayoutManager gridLayoutManager = (GridLayoutManager) rvImageList.getLayoutManager();
        gridLayoutManager.setSpanCount(mode);
        rvImageList.setLayoutManager(gridLayoutManager);
    }

    private void FetchImages(iApiService apiService) {
        // Fetching all images

        disposable = apiService.fetchImages(Constant.API_KEY, "cars", "photo")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ImageListModel>() {
                    @Override
                    public void onSuccess(ImageListModel imageListModels) {
                        Log.d("size", "" + imageListModels.getHits().size());
                        hitsBeans.addAll(imageListModels.getHits());
                        imageListAdapter.notifyDataSetChanged();
                        loaderView.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("error", "" + e.getLocalizedMessage());
                        tvLoaderMessage.setText(getString(R.string.not_able_to_request));
                    }
                });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }

    @Override
    public void onImageClick(int position, ArrayList<ImageListModel.HitsBean> filteredhitsBeans) {

        showImagesOnSlider(position, filteredhitsBeans);
    }


    private void showImagesOnSlider(int position, ArrayList<ImageListModel.HitsBean> filteredhitsBeans) {

        Intent intent = new Intent(this, FullImageActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.IMAGELIST, filteredhitsBeans);
        bundle.putInt(Constant.POSITION, position);
        intent.putExtra(Constant.IMAGELIST, bundle);
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        imageListAdapter.getFilter().filter(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        imageListAdapter.getFilter().filter(newText);
        return false;
    }


}
