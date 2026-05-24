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
import com.firstapp.apartaapp.adapters.PaymentAdapter;
import com.firstapp.apartaapp.models.Payment;
import java.util.ArrayList;
import java.util.List;

public class PaymentsFragment extends Fragment {

    private RecyclerView rvPaymentHistory;
    private PaymentAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payments, container, false);

        rvPaymentHistory = view.findViewById(R.id.rv_payment_history);
        rvPaymentHistory.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Payment> paymentList = new ArrayList<>();
        paymentList.add(new Payment("May 2024", "$1,245.50", "May 01, 2024", "Unpaid"));
        paymentList.add(new Payment("April 2024", "$1,200.00", "Apr 02, 2024", "Paid"));
        paymentList.add(new Payment("March 2024", "$1,200.00", "Mar 01, 2024", "Paid"));
        paymentList.add(new Payment("February 2024", "$1,200.00", "Feb 05, 2024", "Late"));
        paymentList.add(new Payment("January 2024", "$1,200.00", "Jan 01, 2024", "Paid"));

        adapter = new PaymentAdapter(paymentList);
        rvPaymentHistory.setAdapter(adapter);

        return view;
    }
}