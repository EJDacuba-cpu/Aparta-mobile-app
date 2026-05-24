package com.firstapp.apartaapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firstapp.apartaapp.R;
import com.firstapp.apartaapp.models.Announcement;
import java.util.List;

public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.ViewHolder> {
    private List<Announcement> announcements;

    public AnnouncementAdapter(List<Announcement> announcements) {
        this.announcements = announcements;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_announcement, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Announcement announcement = announcements.get(position);
        holder.tvTitle.setText(announcement.getTitle());
        // Display announcement date from Supabase
        holder.tvDate.setText(announcement.getCreated_at());

        // Display announcement message from Supabase
        holder.tvContent.setText(announcement.getMessage());

    }

    @Override
    public int getItemCount() {
        return announcements.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDate, tvContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_announcement_title);
            tvDate = itemView.findViewById(R.id.tv_announcement_date);
            tvContent = itemView.findViewById(R.id.tv_announcement_content);
        }
    }
}