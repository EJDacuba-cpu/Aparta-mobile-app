package com.firstapp.apartaapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firstapp.apartaapp.R;
import com.firstapp.apartaapp.models.MaintenanceRequest;
import java.util.List;

public class MaintenanceAdapter extends RecyclerView.Adapter<MaintenanceAdapter.ViewHolder> {
    private List<MaintenanceRequest> requests;

    public MaintenanceAdapter(List<MaintenanceRequest> requests) {
        this.requests = requests;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_maintenance, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MaintenanceRequest request = requests.get(position);
        holder.tvTitle.setText(request.getTitle());
        holder.tvDate.setText(request.getDate());
        holder.tvStatus.setText(request.getStatus());
        holder.tvDescription.setText(request.getDescription());

        // Simple status color logic
        if (request.getStatus().equalsIgnoreCase("Resolved")) {
            holder.tvStatus.setBackgroundResource(R.drawable.bg_status_badge_resolved);
        } else if (request.getStatus().equalsIgnoreCase("In Progress")) {
            holder.tvStatus.setBackgroundResource(R.drawable.bg_status_badge_progress);
        } else {
            holder.tvStatus.setBackgroundResource(R.drawable.bg_status_badge_pending);
        }
    }

    @Override
    public int getItemCount() {
        return requests.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDate, tvStatus, tvDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_maintenance_title);
            tvDate = itemView.findViewById(R.id.tv_maintenance_date);
            tvStatus = itemView.findViewById(R.id.tv_maintenance_status);
            tvDescription = itemView.findViewById(R.id.tv_maintenance_description);
        }
    }
}