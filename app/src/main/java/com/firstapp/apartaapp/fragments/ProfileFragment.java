package com.firstapp.apartaapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log; // Added for debugging
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.firstapp.apartaapp.LoginActivity;
import com.firstapp.apartaapp.R;
import com.firstapp.apartaapp.models.User;
import com.firstapp.apartaapp.network.ApiClient;
import com.firstapp.apartaapp.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    private TextView tvProfileName, tvProfileEmail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // 1. Initialize UI components
        tvProfileName = view.findViewById(R.id.tv_profile_name);
        tvProfileEmail = view.findViewById(R.id.tv_profile_email);

        // 2. Fetch user session data (email saved during Login)
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("user_session", Context.MODE_PRIVATE);
        String userEmail = sharedPreferences.getString("user_email", "Tenant");

        // 3. Set Placeholder Text immediately (This removes "Eric Johnson" right away)
        tvProfileName.setText("Loading profile...");
        tvProfileEmail.setText(userEmail);

        // 4. Fetch real Full Name from Supabase Database
        fetchUserProfile(userEmail);

        // 5. Logout Button Logic
        view.findViewById(R.id.btn_logout).setOnClickListener(v -> {
            sharedPreferences.edit().clear().apply();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            requireActivity().finish();
        });

        return view;
    }

    private void fetchUserProfile(String email) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        // We use "eq." + email for Supabase filter logic
        apiService.getUserByEmail("eq." + email).enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                // Ensure fragment is still attached to activity
                if (!isAdded()) return;

                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    User currentUser = response.body().get(0);

                    // Display real full name from database
                    tvProfileName.setText(currentUser.getFull_name());
                    tvProfileEmail.setText(currentUser.getEmail());
                } else {
                    // Fallback to email if no user record found in 'users' table
                    tvProfileName.setText(email);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {
                if (!isAdded()) return;
                Log.e("ProfileFragment", "Error: " + t.getMessage());
                tvProfileName.setText(email); // Fallback on failure
            }
        });
    }
}