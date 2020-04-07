package com.xuyu.myview.activity.list.planb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Path;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.xuyu.myview.R;
import com.xuyu.myview.activity.list.PathView;


public class ListPlanbActivity extends AppCompatActivity {
    public static final String TAG = "ListPlanbActivity";
    private PathView mPathView;
    private Path path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_planb);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new PathPlanbAdapter(this));


        int screenWidthDp = getResources().getConfiguration().screenWidthDp;
        final int screenHeightDp = getResources().getConfiguration().screenHeightDp;
        final float scale = getResources().getDisplayMetrics().density;
        int screenWidth = (int) (screenWidthDp * scale + 0.5f);
        mPathView = findViewById(R.id.path_view);
        path = new Path();
        for (int i = 0; i < 50; i++) {
            path.moveTo(screenWidth / 3, 0 + 300 * i * 2);
            path.lineTo(screenWidth / 3, 250 + 300 * i * 2);
            path.lineTo(screenWidth / 3 * 2, 250 + 300 * i * 2);
            path.lineTo(screenWidth / 3 * 2, 300 + 300 * i * 2);

            path.lineTo(screenWidth / 3 * 2, 250 + 300 * (i * 2 + 1));
            path.lineTo(screenWidth / 3, 250 + 300 * (i * 2 + 1));
            path.lineTo(screenWidth / 3, 300 + 300 * (i * 2 + 1));

        }
        mPathView.setImage(R.mipmap.airplane2);
        mPathView.setPath(path);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int scrollY = 0;

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                path.offset(dx, -dy);
                mPathView.setPath(path);
                scrollY += dy;
                int progress = (int) (scrollY / (300 * 100 - screenHeightDp * scale) * 100);
                mPathView.setProgress(progress);
                Log.e(TAG, "onScrolled: scrollY " + scrollY);
                Log.e(TAG, "onScrolled: progress " + progress);

            }
        });

    }
}
