package com.example.vetservefirebase.PetDashboard;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.vetservefirebase.Others.CircleTransform;
import com.example.vetservefirebase.R;


import java.util.ArrayList;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class MyCustomPagerAdapter extends PagerAdapter {
    Context context;
    ArrayList<String> photoUrls;
    ArrayList<String> petnames;
    LayoutInflater layoutInflater;


    public MyCustomPagerAdapter(Context context, ArrayList<String> photoUrls, ArrayList<String> petnames) {
        this.context = context;
        this.photoUrls = photoUrls;
        this.petnames = petnames;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return photoUrls.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.item, container, false);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.petpictures);
        TextView displaypetname = itemView.findViewById(R.id.displaypetname);
        Glide.with(context).load(photoUrls.get(position))
                .transition(withCrossFade())
                .thumbnail(0.5f)
                .transform(new CircleTransform())
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
        displaypetname.setText(petnames.get(position));
        container.addView(itemView);
        return itemView;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }

}