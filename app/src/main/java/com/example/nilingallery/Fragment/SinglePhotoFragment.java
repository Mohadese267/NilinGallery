package com.example.nilingallery.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.nilingallery.Classes.Model.Utilities.Helper;
import com.example.nilingallery.R;
import com.example.nilingallery.databinding.FragmentPhotoSingleBinding;
import com.squareup.picasso.Picasso;

public class SinglePhotoFragment extends Fragment {
    public static final String TAG = "SinglePhotoFragment";
    private FragmentPhotoSingleBinding binding;
    private Context context;
    private Helper helper;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @org.jetbrains.annotations.NotNull LayoutInflater inflater,
                             @Nullable @org.jetbrains.annotations.Nullable ViewGroup container,
                             @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = FragmentPhotoSingleBinding.inflate(inflater, container, false);
        context = getContext();
        helper = new Helper(context);
        initialize();
        listeners();
        return binding.getRoot();
    }

    private void initialize() {

        Picasso.get().load(helper.getPhotoUrl()).placeholder(R.drawable.placeholder).into(binding.image);

    }

    private void listeners() {
        binding.backBtn.setOnClickListener(view -> {
            Navigation.findNavController(view).navigateUp();

        });
    }
}
