package com.firstapp.apartaapp.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firstapp.apartaapp.R;
import com.firstapp.apartaapp.models.Payment;
import java.util.List;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.ViewHolder> {
    private List<Payment> payments;

    public PaymentAdapter(List<Payment> payments) {
        this.payments = payments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_payment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Payment payment = payments.get(position);
        holder.tvMonth.setText(payment.getMonth());
        holder.tvDate.setText(payment.getDate());
        holder.tvAmount.setText(payment.getAmount());
        holder.tvStatus.setText(payment.getStatus());

        if (payment.getStatus().equalsIgnoreCase("Paid")) {
            holder.tvStatus.setTextColor(holder.itemView.getContext().getColor(R.color.status_paid));
        } else if (payment.getStatus().equalsIgnoreCase("Unpaid")) {
            holder.tvStatus.setTextColor(holder.itemView.getContext().getColor(R.color.status_unpaid));
        } else {
            holder.tvStatus.setTextColor(holder.itemView.getContext().getColor(R.color.status_pending));
        }
    }

    @Override
    public int getItemCount() {
        return payments.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMonth, tvDate, tvAmount, tvStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMonth = itemView.findViewById(R.id.tv_payment_month);
            tvDate = itemView.findViewById(R.id.tv_payment_date);
            tvAmount = itemView.findViewById(R.id.tv_payment_amount);
            tvStatus = itemView.findViewById(R.id.tv_payment_status);
        }
    }
}