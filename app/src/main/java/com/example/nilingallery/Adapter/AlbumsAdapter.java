package com.example.nilingallery.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nilingallery.Classes.Model.Base.Album;
import com.example.nilingallery.Classes.Model.Base.User;
import com.example.nilingallery.Classes.Model.Utilities.Helper;
import com.example.nilingallery.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.MyViewHolder> {
    private static final String TAG = "AlbumsAdapter";
    private List<Album> mAlbums;
    private Context context;
    private String parentFragment;
    private Helper helper;


    public AlbumsAdapter(List<Album> users, Context context) {
        this.mAlbums = users;
        this.parentFragment = parentFragment;
        this.context = context;
        helper = new Helper(context);
    }


    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_album, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AlbumsAdapter.MyViewHolder holder, int position) {
        holder.title.setText(mAlbums.get(position).title);
        listeners(holder, position);


    }

    private void listeners(MyViewHolder holder, int position) {

        holder.itemView.setOnClickListener(view -> {
            //set the user id in preferences
            helper.setAlbumId(mAlbums.get(position).id);
            Navigation.findNavController(view).navigate(R.id.action_albums_to_photos);
        });


    }


    @Override
    public int getItemCount() {

        return mAlbums.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;


        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.album_title);


        }
    }
}
