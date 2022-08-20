package com.example.nilingallery.Fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.nilingallery.Adapter.UsersAdapter;
import com.example.nilingallery.Classes.Model.Base.User;
import com.example.nilingallery.Classes.Model.Utilities.DBManager;
import com.example.nilingallery.Classes.Model.Utilities.Helper;
import com.example.nilingallery.Classes.Network.WebServiceHelper;
import com.example.nilingallery.Dialogs.LoadingDialog;
import com.example.nilingallery.databinding.FragmentUsersBinding;

import org.jetbrains.annotations.NotNull;

import java.net.SocketTimeoutException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersFragment extends Fragment {
    public static final String TAG = "UsersFragment";
    private FragmentUsersBinding binding;
    private UsersAdapter mAdapter;
    private final ArrayList<User> mUsers = new ArrayList<>();
    private LoadingDialog loadingDialog;
    private Helper helper;
    private DBManager dbManager;


    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater,
                             @Nullable @org.jetbrains.annotations.Nullable ViewGroup container,
                             @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = FragmentUsersBinding.inflate(inflater, container, false);
        helper = new Helper(getContext());

        //if network is available , get the data from server
        //otherwise , check if there is any data saved in local db ---if yes---> fetch it
        if (helper.isNetworkConnected()) {
            Log.e(TAG, "call");
            callApi();
        } else fetchDB();


        listener();

        return binding.getRoot();
    }

    private void fetchDB() {
        dbManager = new DBManager(getContext());
        dbManager.open();
        Cursor cursor = dbManager.fetch();
        Log.e(TAG, "db");

        if (cursor.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                mUsers.clear();
                mUsers.add(new User(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2), null, null, null, null, null));
            } while (cursor.moveToNext());
            // moving our cursor to next.
        }

        // at last closing our cursor
        cursor.close();
        if (mUsers.isEmpty()) {
            Toast.makeText(getContext(), "db is empty , check your connection and try again", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(), "no connection , loading from db", Toast.LENGTH_LONG).show();
            recycler();
        }
    }

    private void insertDB() {

        dbManager = new DBManager(getContext());
        dbManager.open();
        for (int i = 0; i < mUsers.size(); i++) {
            dbManager.insert(mUsers.get(i).name, mUsers.get(i).username);

        }
        dbManager.close();
    }


    private void recycler() {
        mAdapter = new UsersAdapter(mUsers, getContext());
        binding.usersContainer.setAdapter(mAdapter);
        binding.usersContainer.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
    }

    private void callApi() {
        loadingDialog = new LoadingDialog(getContext());
        loadingDialog.show(getParentFragmentManager(), TAG);

        Call<ArrayList<User>> call = WebServiceHelper.service.getUsers();
        call.enqueue(new Callback<ArrayList<User>>() {

            @Override
            public void onResponse(@NotNull Call<ArrayList<User>> call,
                                   @NotNull Response<ArrayList<User>> response) {
                if (response.isSuccessful()) {
//                    Toast.makeText(getContext(), "successful", Toast.LENGTH_SHORT).show();

                    assert response.body() != null;
                    mUsers.clear();
                    mUsers.addAll(response.body());
                    if (loadingDialog != null)
                        loadingDialog.dismiss();
                    if (!mUsers.isEmpty()) {
                        insertDB();
                        recycler();
                    } else Toast.makeText(getContext(), "no user", Toast.LENGTH_SHORT).show();


                } else
                    switch (response.code()) {
                        case 504:
                            if (!helper.isInternetAvailable())
                                loadingDialog.dismiss();
                            Toast.makeText(getContext(), "no internet connection", Toast.LENGTH_SHORT).show();
                            break;
                    }
            }

            @Override
            public void onFailure(@NotNull Call<ArrayList<User>> call,
                                  @NotNull Throwable t) {
                Log.e(TAG, t.getMessage());
                if (loadingDialog != null)
                    loadingDialog.dismiss();
                Log.e(TAG, t.getMessage());
                if (helper.isInternetAvailable()) {
                    if (t instanceof SocketTimeoutException)
                        Toast.makeText(getContext(), "socket timeout", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getContext(), "no internet connection", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void listener() {

    }
}
