package com.example.nilingallery.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.nilingallery.Adapter.AlbumsAdapter;
import com.example.nilingallery.Classes.Model.Base.Album;
import com.example.nilingallery.Classes.Model.Utilities.Helper;
import com.example.nilingallery.Classes.Network.WebServiceHelper;
import com.example.nilingallery.Dialogs.LoadingDialog;
import com.example.nilingallery.R;
import com.example.nilingallery.databinding.FragmentAlbumsBinding;

import org.jetbrains.annotations.NotNull;

import java.net.SocketTimeoutException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumsFragment extends Fragment {
    public static final String TAG = "AlbumsFragment";
    private FragmentAlbumsBinding binding;
    private AlbumsAdapter mAdapter;
    private final ArrayList<Album> mAlbum = new ArrayList<>();
    private Helper helper;
    private LoadingDialog loadingDialog;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @org.jetbrains.annotations.NotNull LayoutInflater inflater,
                             @Nullable @org.jetbrains.annotations.Nullable ViewGroup container,
                             @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = FragmentAlbumsBinding.inflate(inflater, container, false);
        helper = new Helper(getContext());
        callApi();
        listener();

        return binding.getRoot();
    }

    private void listener() {
        searchHandler();
        binding.backBtn.setOnClickListener(view -> {
            Navigation.findNavController(view).navigateUp();
            if (loadingDialog != null)
                loadingDialog.dismiss();
        });
        binding.searchBtn.setOnClickListener(view -> {
            if (binding.searchBar.getVisibility() == View.VISIBLE) {
                binding.title.setVisibility(View.VISIBLE);
                binding.searchBar.setVisibility(View.GONE);
                binding.searchBtn.setImageResource(R.drawable.ic_search);
            } else {
                binding.searchBtn.setImageResource(R.drawable.ic_close);
                binding.title.setVisibility(View.GONE);
                binding.searchBar.setVisibility(View.VISIBLE);
            }
        });


    }

    private void recycler() {
        mAdapter = new AlbumsAdapter(mAlbum, getContext());
        binding.albumContainer.setAdapter(mAdapter);
        binding.albumContainer.setLayoutManager(new StaggeredGridLayoutManager(3,
                StaggeredGridLayoutManager.VERTICAL));
    }

    private void callApi() {
        loadingDialog = new LoadingDialog(getContext());
        loadingDialog.show(getParentFragmentManager(), TAG);

        Call<ArrayList<Album>> call = WebServiceHelper.service.getAlbums(helper.getUserId());
        call.enqueue(new Callback<ArrayList<Album>>() {

            @Override
            public void onResponse(@NotNull Call<ArrayList<Album>> call,
                                   @NotNull Response<ArrayList<Album>> response) {
                if (response.isSuccessful()) {
//                    Toast.makeText(getContext(), "successful", Toast.LENGTH_SHORT).show();

//                    Log.e(TAG, response.body().toString() + "");
                    assert response.body() != null;
                    mAlbum.clear();
                    mAlbum.addAll(response.body());
                    if (loadingDialog != null)
                        loadingDialog.dismiss();
                    recycler();


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
            public void onFailure(@NotNull Call<ArrayList<Album>> call,
                                  @NotNull Throwable t) {
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

    private void searchHandler() {

        binding.searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                mAdapter.filter(binding.searchBar.getQuery().toString());


                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                mAdapter.filter(binding.searchBar.getQuery().toString());

                return false;
            }
        });
    }


}
