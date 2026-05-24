package com.firstapp.apartaapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.firstapp.apartaapp.R;

public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        view.findViewById(R.id.btn_logout).setOnClickListener(v -> {
            Toast.makeText(getContext(), "Logging out...", Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}