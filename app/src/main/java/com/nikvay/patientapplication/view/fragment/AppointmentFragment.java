package com.nikvay.patientapplication.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.nikvay.patientapplication.R;
import com.nikvay.patientapplication.utils.StaticContent;
import com.nikvay.patientapplication.view.activity.AppointmentListActivity;


public class AppointmentFragment extends Fragment {

    Context mContext;
    private CardView cardViewPending, cardViewConfirmed, cardViewCancelled, cardViewArchive;
    private FloatingActionButton fabAddNewAppointment;
    private TextView textPending, textConfirmed, textCancelled, textArchive;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_appointment, container, false);
        mContext = getActivity();

        find_All_IDs(view);
        events();
        return view;
    }

    private void find_All_IDs(View view) {
        cardViewPending = view.findViewById(R.id.cardViewPending);
        cardViewConfirmed = view.findViewById(R.id.cardViewConfirmed);
        cardViewCancelled = view.findViewById(R.id.cardViewCancelled);
        cardViewArchive = view.findViewById(R.id.cardViewArchive);
        fabAddNewAppointment = view.findViewById(R.id.fabAddNewAppointment);

        textPending = view.findViewById(R.id.textPending);
        textConfirmed = view.findViewById(R.id.textConfirmed);
        textCancelled = view.findViewById(R.id.textCancelled);
        textArchive = view.findViewById(R.id.textArchive);

    }

    private void events() {
        cardViewPending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AppointmentListActivity.class);
                intent.putExtra(StaticContent.IntentKey.STATUS, 0);
                intent.putExtra(StaticContent.IntentValue.APPOINTMENT,textPending.getText().toString().trim());
                startActivity(intent);
            }
        });

        cardViewArchive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, AppointmentListActivity.class);
                intent.putExtra(StaticContent.IntentKey.STATUS, 3);
                intent.putExtra(StaticContent.IntentValue.APPOINTMENT,textArchive.getText().toString().trim());
                startActivity(intent);

            }
        });
        cardViewCancelled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AppointmentListActivity.class);
                intent.putExtra(StaticContent.IntentKey.STATUS, 2);
                intent.putExtra(StaticContent.IntentValue.APPOINTMENT,textCancelled.getText().toString().trim());
                startActivity(intent);
            }
        });

        cardViewConfirmed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, AppointmentListActivity.class);
                intent.putExtra(StaticContent.IntentKey.STATUS, 1);
                intent.putExtra(StaticContent.IntentValue.APPOINTMENT,textConfirmed.getText().toString().trim());
                startActivity(intent);

            }
        });

        fabAddNewAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Add New Appointment", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
