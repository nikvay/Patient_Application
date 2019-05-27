package com.nikvay.patientapplication.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.nikvay.patientapplication.R;
import com.nikvay.patientapplication.view.activity.ClassActivity;
import com.nikvay.patientapplication.view.activity.ServiceListActivity;

public class SettingFragment extends Fragment {

    Context mContext;
    private RelativeLayout relativeLayoutService,relativeLayoutStaff,relativeLayoutSetting,relativeLayoutClass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        mContext = getActivity();

        find_All_IDs(view);
        events();

        return view;

    }

    private void find_All_IDs(View view) {
        relativeLayoutService=view.findViewById(R.id.relativeLayoutService);
        relativeLayoutStaff=view.findViewById(R.id.relativeLayoutStaff);
        relativeLayoutSetting=view.findViewById(R.id.relativeLayoutSetting);
        relativeLayoutClass=view.findViewById(R.id.relativeLayoutClass);
    }


    private void events() {
        relativeLayoutService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, ServiceListActivity.class);
                startActivity(intent);
            }
        });
        relativeLayoutSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Setting Under Development", Toast.LENGTH_SHORT).show();
            }
        });
        relativeLayoutStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Staff Under Development", Toast.LENGTH_SHORT).show();
            }
        });
        relativeLayoutClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(mContext, ClassActivity.class);
               startActivity(intent);
            }
        });



    }


}
