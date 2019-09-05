package com.example.vetservefirebase.PetDashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.vetservefirebase.Model.Pet;
import com.example.vetservefirebase.Others.CircleTransform;
import com.example.vetservefirebase.R;


import java.util.ArrayList;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class MyCustomPagerAdapter extends PagerAdapter {
    Context context;
    ArrayList<Pet> pets;
    LayoutInflater layoutInflater;


    public MyCustomPagerAdapter(Context context, ArrayList<Pet> pets) {
        this.context = context;
        this.pets = pets;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return pets.size();
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
        Glide.with(context).load(pets.get(position).getPhotoUrl())
                .transition(withCrossFade())
                .thumbnail(0.5f)
                .transform(new CircleTransform())
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
        displaypetname.setText(pets.get(position).getPet_name());
        container.addView(itemView);
        return itemView;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}