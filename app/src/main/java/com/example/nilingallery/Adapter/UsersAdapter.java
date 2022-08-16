package com.example.nilingallery.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nilingallery.Classes.Model.Base.User;
import com.example.nilingallery.Classes.Model.Utilities.Helper;
import com.example.nilingallery.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {
    private static final String TAG = "UsersAdapter";
    private List<User> mUsers;
    private Context context;

    private Helper helper;


    public UsersAdapter(List<User> users, Context context) {
        this.mUsers = users;
        this.context = context;
        helper = new Helper(context);
    }


    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull UsersAdapter.MyViewHolder holder, int position) {
        holder.name.setText(mUsers.get(position).name);
        holder.username.setText(mUsers.get(position).username);
        listeners(holder, position);


    }

    private void listeners(MyViewHolder holder, int position) {

        holder.itemView.setOnClickListener(view -> {
            //set the user id in preferences
            helper.setUserId(mUsers.get(position).id);
            Navigation.findNavController(view).navigate(R.id.action_users_to_albums);
        });


    }


    @Override
    public int getItemCount() {

        return mUsers.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, username;


        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.name);
            this.username = itemView.findViewById(R.id.username);


        }
    }
}
