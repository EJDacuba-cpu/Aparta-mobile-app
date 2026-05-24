package com.firstapp.apartaapp.fragments;

// Supabase API connection
import com.firstapp.apartaapp.network.ApiClient;
import com.firstapp.apartaapp.network.ApiService;

// Retrofit response handling
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firstapp.apartaapp.R;
import com.firstapp.apartaapp.adapters.AnnouncementAdapter;
import com.firstapp.apartaapp.models.Announcement;
import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    
    private RecyclerView rvAnnouncements;
    private AnnouncementAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        
        rvAnnouncements = view.findViewById(R.id.rv_announcements);
        rvAnnouncements.setLayoutManager(new LinearLayoutManager(getContext()));

        // Create Supabase API service
        ApiService apiService = ApiClient.getClient().create(ApiService.class);

// Fetch announcements from Supabase
        apiService.getAnnouncements().enqueue(new Callback<List<Announcement>>() {

            @Override
            public void onResponse(Call<List<Announcement>> call,
                                   Response<List<Announcement>> response) {

                // Check if request is successful
                if (response.isSuccessful() && response.body() != null) {

                    // Get announcements from Supabase
                    List<Announcement> announcementList = response.body();

                    // Set RecyclerView adapter
                    adapter = new AnnouncementAdapter(announcementList);
                    rvAnnouncements.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Announcement>> call, Throwable t) {

                // Print error in Logcat
                t.printStackTrace();
            }
        });

        return view;
    }
}