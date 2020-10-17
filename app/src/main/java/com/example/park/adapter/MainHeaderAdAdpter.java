package com.example.park.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import java.util.List;

public class MainHeaderAdAdpter extends PagerAdapter {
    protected Context context;
    protected List<ImageView> images;

    public MainHeaderAdAdpter(Context context, List<ImageView> images) {
        this.context = context;
        this.images = images;
    }

    public int getCount() {
        return null != this.images ? this.images.size() : 0;
    }

    @NonNull
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView((View)this.images.get(position));
        return this.images.get(position);
    }

    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)this.images.get(position));
    }
}
