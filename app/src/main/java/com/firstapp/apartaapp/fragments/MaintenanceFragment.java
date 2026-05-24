package com.firstapp.apartaapp.fragments;

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
import com.firstapp.apartaapp.adapters.MaintenanceAdapter;
import com.firstapp.apartaapp.models.MaintenanceRequest;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceFragment extends Fragment {

    private RecyclerView rvMaintenance;
    private MaintenanceAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maintenance, container, false);

        rvMaintenance = view.findViewById(R.id.rv_maintenance_requests);
        rvMaintenance.setLayoutManager(new LinearLayoutManager(getContext()));

        List<MaintenanceRequest> requestList = new ArrayList<>();
        requestList.add(new MaintenanceRequest("Leaking Faucet", "Pending", "May 28, 2024", "Kitchen faucet is dripping constantly since this morning."));
        requestList.add(new MaintenanceRequest("AC Not Cooling", "In Progress", "May 26, 2024", "The air conditioning unit is blowing warm air even at the lowest setting."));
        requestList.add(new MaintenanceRequest("Broken Light Fixture", "Resolved", "May 20, 2024", "The hallway light bulb was replaced and the fixture secured."));

        adapter = new MaintenanceAdapter(requestList);
        rvMaintenance.setAdapter(adapter);

        return view;
    }
}