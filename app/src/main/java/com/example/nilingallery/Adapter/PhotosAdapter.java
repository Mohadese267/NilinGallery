package com.example.nilingallery.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nilingallery.Classes.Model.Base.Photo;
import com.example.nilingallery.Classes.Model.Utilities.Helper;
import com.example.nilingallery.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.MyViewHolder> {
    private static final String TAG = "PhotosAdapter";
    private final List<Photo> mPhotos;
    private final Context context;
    private String parentFragment;
    private final Helper helper;


    public PhotosAdapter(List<Photo> photos, Context context) {
        this.mPhotos = photos;
        this.parentFragment = parentFragment;
        this.context = context;
        helper = new Helper(context);
    }


    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PhotosAdapter.MyViewHolder holder, int position) {


        Picasso.get().load(mPhotos.get(position).thumbnailUrl).placeholder(R.drawable.placeholder).into(holder.photo);


        listeners(holder, position);


    }

    private void listeners(MyViewHolder holder, int position) {

        holder.itemView.setOnClickListener(view -> {
            //set the user id in preferences
            helper.setPhotoUrl(mPhotos.get(position).url);
            Navigation.findNavController(view).navigate(R.id.action_photos_to_photo);
        });


    }


    @Override
    public int getItemCount() {

        return mPhotos.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView photo;


        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            this.photo = itemView.findViewById(R.id.photo);


        }
    }
}
