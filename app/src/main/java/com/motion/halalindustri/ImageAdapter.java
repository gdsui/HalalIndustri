package com.motion.halalindustri;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    List<ImageClass>imageClasses;

    public ImageAdapter(List<ImageClass> imageClasses) {
        this.imageClasses = imageClasses;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_image,parent,false);

        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
  holder.bind(imageClasses.get(position));
    }

    @Override
    public int getItemCount() {
        return imageClasses.size();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.selected_image);
            textView=itemView.findViewById(R.id.title_image);

        }

        public void bind(ImageClass imageClass) {
            textView.setText(imageClass.title);
            imageView.setImageURI(imageClass.imageUri);
        }
    }
}
