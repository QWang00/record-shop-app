package com.northcoders.recordshop.util;

import android.util.Log;
import android.widget.ImageView;
import androidx.databinding.BindingAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.northcoders.recordshop.R;

public class BindingAdapters {

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String imageUrl) {
        Log.d("BindingAdapters", "loadImage called with URL: " + imageUrl);

        if (imageUrl == null || imageUrl.isEmpty()) {
            Log.d("BindingAdapters", "Setting placeholder image due to null or empty URL");
            view.setImageResource(R.drawable.placeholder_image);
            return;
        }

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.error_image);

        Glide.with(view.getContext())
                .load(imageUrl)
                .apply(options)
                .into(view);
    }
}
