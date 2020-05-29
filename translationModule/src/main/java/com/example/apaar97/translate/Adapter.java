package com.example.apaar97.translate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class Adapter extends PagerAdapter {

    private List<Model> models;
    private LayoutInflater layoutInflater;
    private Context context;
    private View vie;

    public Adapter(List<Model> models, Context context, View vie) {
        this.models = models;
        this.context = context;
        this.vie = vie;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item, container, false);

        ImageView imageView;
        TextView title, desc;

        imageView = view.findViewById(R.id.image);
        title = view.findViewById(R.id.title);
        desc = view.findViewById(R.id.desc);

        String urls = "https://mangoess.000webhostapp.com/MangoFolder/"+models.get(position).geturlimage();
     //   String urls = models.get(position).geturlimage();

        Glide.with(context)
                .load(urls)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(imageView);

  //      imageView.setImageResource(models.get(position).getImage());
        title.setText(models.get(position).geturlimage());
        desc.setText(models.get(position).getDesc());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Action", Toast.LENGTH_SHORT).show();
                // finish();
            }
        });



        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull final ViewGroup container, int position, @NonNull final Object object) {

                container.removeView((View)object);
                // finish();
    }


}
