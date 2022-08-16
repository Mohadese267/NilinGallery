package com.example.nilingallery.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.nilingallery.Adapter.UsersAdapter;
import com.example.nilingallery.Classes.Model.Base.User;
import com.example.nilingallery.Classes.Network.WebServiceHelper;
import com.example.nilingallery.databinding.FragmentUsersBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersFragment extends Fragment {
    public static final String TAG = "UsersFragment";
    private FragmentUsersBinding binding;
    private UsersAdapter mAdapter;
    private ArrayList<User> mUsers = new ArrayList<>();

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @org.jetbrains.annotations.NotNull LayoutInflater inflater,
                             @Nullable @org.jetbrains.annotations.Nullable ViewGroup container,
                             @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = FragmentUsersBinding.inflate(inflater, container, false);
        callApi();
        listener();

        return binding.getRoot();
    }

    private void recycler() {
        mAdapter = new UsersAdapter(mUsers, getContext());
        binding.usersContainer.setAdapter(mAdapter);
        binding.usersContainer.setLayoutManager(new StaggeredGridLayoutManager(2,
                LinearLayoutManager.VERTICAL));
    }

    private void callApi() {

        Call<ArrayList<User>> call = WebServiceHelper.service.getUsers();
        call.enqueue(new Callback<ArrayList<User>>() {

            @Override
            public void onResponse(@NotNull Call<ArrayList<User>> call,
                                   @NotNull Response<ArrayList<User>> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "successful", Toast.LENGTH_SHORT).show();

//                    Log.e(TAG, response.body().toString() + "");
                    assert response.body() != null;
                    mUsers.addAll(response.body());

                    recycler();


                }
            }

            @Override
            public void onFailure(@NotNull Call<ArrayList<User>> call,
                                  @NotNull Throwable t) {
                Log.e(TAG, t.getMessage());
//                if (helper.isInternetAvailable()) {
//                    if (t instanceof SocketTimeoutException)
//                        searchStudent(keyword);
//                } else
//                    Snackbar.make(requireView(), R.string.no_internet, Snackbar.LENGTH_INDEFINITE)
//                            .setAction("try again", view -> {
//                                searchStudent(keyword);
//
//                            }).show();


            }
        });

    }

    private void listener() {
        binding.backBtn.setOnClickListener(view -> {
            Navigation.findNavController(view).navigateUp();
        });
    }
}
