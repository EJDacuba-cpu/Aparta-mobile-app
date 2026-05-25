package com.firstapp.apartaapp.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firstapp.apartaapp.R;
import com.firstapp.apartaapp.adapters.AnnouncementAdapter;
import com.firstapp.apartaapp.models.Announcement;
import com.firstapp.apartaapp.models.User;
import com.firstapp.apartaapp.network.ApiClient;
import com.firstapp.apartaapp.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    private RecyclerView rvAnnouncements;
    private AnnouncementAdapter adapter;
    private TextView tvTenantName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize UI
        tvTenantName = view.findViewById(R.id.tv_tenant_name);
        rvAnnouncements = view.findViewById(R.id.rv_announcements);

        rvAnnouncements.setLayoutManager(
                new LinearLayoutManager(getContext())
        );

        // Get saved user email from session
        SharedPreferences sharedPreferences =
                requireActivity().getSharedPreferences(
                        "user_session",
                        Context.MODE_PRIVATE
                );

        final String userEmail =
                sharedPreferences.getString("user_email", "Tenant");

        // Use string resource to fix translation warning
        tvTenantName.setText(R.string.loading);

        // Create API service instance once
        ApiService apiService =
                ApiClient.getClient().create(ApiService.class);

        // Fetch current logged-in user profile
        apiService.getUserByEmail("eq." + userEmail)
                .enqueue(new Callback<>() {

                    @Override
                    public void onResponse(@NonNull Call<List<User>> call,
                                           @NonNull Response<List<User>> response) {
                        if (!isAdded()) return;

                        if (response.isSuccessful()
                                && response.body() != null
                                && !response.body().isEmpty()) {

                            // Get first user from database result
                            User currentUser = response.body().get(0);

                            // Display real full name
                            tvTenantName.setText(currentUser.getFull_name());
                        } else {
                            // Fallback to email if user record not found
                            tvTenantName.setText(userEmail);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<User>> call,
                                          @NonNull Throwable t) {
                        if (!isAdded()) return;

                        // Fallback to email if error occurs
                        tvTenantName.setText(userEmail);
                        Log.e(TAG, "Error fetching user profile", t);
                    }
                });

        // Fetch announcements from Supabase
        apiService.getAnnouncements()
                .enqueue(new Callback<>() {

                    @Override
                    public void onResponse(@NonNull Call<List<Announcement>> call,
                                           @NonNull Response<List<Announcement>> response) {
                        if (!isAdded()) return;

                        if (response.isSuccessful()
                                && response.body() != null) {

                            adapter = new AnnouncementAdapter(response.body());
                            rvAnnouncements.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Announcement>> call,
                                          @NonNull Throwable t) {
                        if (!isAdded()) return;
                        Log.e(TAG, "Error fetching announcements", t);
                    }
                });

        return view;
    }
}
