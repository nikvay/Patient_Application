package com.nikvay.patientapplication.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nikvay.patientapplication.R;

public class NotificationFragment extends Fragment {

    Context mContext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        mContext = getActivity();

        find_All_IDs(view);
        events();
        return view;
    }
    private void find_All_IDs(View view) {
    }

    private void events() {
    }

}
