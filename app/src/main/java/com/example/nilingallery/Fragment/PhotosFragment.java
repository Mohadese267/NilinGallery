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
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.nilingallery.Adapter.PhotosAdapter;
import com.example.nilingallery.Classes.Model.Base.Photo;
import com.example.nilingallery.Classes.Model.Utilities.Helper;
import com.example.nilingallery.Classes.Network.WebServiceHelper;
import com.example.nilingallery.Dialogs.LoadingDialog;
import com.example.nilingallery.databinding.FragmentPhotosBinding;

import org.jetbrains.annotations.NotNull;

import java.net.SocketTimeoutException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotosFragment extends Fragment {
    public static final String TAG = "PhotosFragment";
    private FragmentPhotosBinding binding;
    private PhotosAdapter mAdapter;
    private final ArrayList<Photo> mPhotos = new ArrayList<>();
    private Helper helper;
    private LoadingDialog loadingDialog;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @org.jetbrains.annotations.NotNull LayoutInflater inflater,
                             @Nullable @org.jetbrains.annotations.Nullable ViewGroup container,
                             @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = FragmentPhotosBinding.inflate(inflater, container, false);
        helper = new Helper(getContext());
        callApi();
        listeners();
        return binding.getRoot();
    }

    private void listeners() {
        binding.backBtn.setOnClickListener(view -> {
            Navigation.findNavController(view).navigateUp();
            if (loadingDialog != null)
                loadingDialog.dismiss();
        });
    }

    private void recycler() {
        mAdapter = new PhotosAdapter(mPhotos, getContext());
        binding.photosContainer.setAdapter(mAdapter);
        binding.photosContainer.setLayoutManager(new StaggeredGridLayoutManager(4,
                StaggeredGridLayoutManager.VERTICAL));
    }

    private void callApi() {
        loadingDialog = new LoadingDialog(getContext());
        loadingDialog.show(getParentFragmentManager(), TAG);

        Call<ArrayList<Photo>> call = WebServiceHelper.service.getPhotos(helper.getAlbumId());
        call.enqueue(new Callback<ArrayList<Photo>>() {

            @Override
            public void onResponse(@NotNull Call<ArrayList<Photo>> call,
                                   @NotNull Response<ArrayList<Photo>> response) {
                if (response.isSuccessful()) {
//                    Toast.makeText(getContext(), "successful", Toast.LENGTH_SHORT).show();

//                    Log.e(TAG, response.body().toString() + "");
                    assert response.body() != null;
                    mPhotos.clear();
                    mPhotos.addAll(response.body());
                    if (loadingDialog != null)
                        loadingDialog.dismiss();
                    recycler();


                }else
                    switch (response.code()) {
                        case 504:
                            if (!helper.isInternetAvailable())
                                loadingDialog.dismiss();
                            Toast.makeText(getContext(), "no internet connection", Toast.LENGTH_SHORT).show();
                            break;
                    }
            }

            @Override
            public void onFailure(@NotNull Call<ArrayList<Photo>> call,
                                  @NotNull Throwable t) {
                Log.e(TAG, t.getMessage());
                if (loadingDialog!=null)
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
}
