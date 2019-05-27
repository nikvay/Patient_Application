package com.nikvay.patientapplication.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.nikvay.patientapplication.R;
import com.nikvay.patientapplication.apicallcommon.ApiClient;
import com.nikvay.patientapplication.apicallcommon.ApiInterface;
import com.nikvay.patientapplication.model.DoctorModel;
import com.nikvay.patientapplication.model.ServiceModel;
import com.nikvay.patientapplication.model.SuccessModel;
import com.nikvay.patientapplication.utils.ErrorMessageDialog;
import com.nikvay.patientapplication.utils.NetworkUtils;
import com.nikvay.patientapplication.utils.SharedUtils;
import com.nikvay.patientapplication.view.adapter.ServiceListAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceListActivity extends AppCompatActivity {


    private RecyclerView recyclerViewServiceList;
    ArrayList<ServiceModel> serviceModelArrayList=new ArrayList<>();
    private ServiceListAdapter serviceListAdapter;
    private ImageView  iv_close;
    private ApiInterface apiInterface;
    private ErrorMessageDialog errorMessageDialog;
    private String device_token,TAG = getClass().getSimpleName(),doctor_id;
    private ArrayList<DoctorModel> doctorModelArrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);


        find_All_IDs();
        events();

        if (NetworkUtils.isNetworkAvailable(ServiceListActivity.this))
            callServiceList();
        else
            NetworkUtils.isNetworkNotAvailable(ServiceListActivity.this);

    }



    private void events() {
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void find_All_IDs() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        recyclerViewServiceList=findViewById(R.id.recyclerViewServiceList);
        iv_close=findViewById(R.id.iv_close);

        doctorModelArrayList= SharedUtils.getUserDetails(ServiceListActivity.this);
        doctor_id=doctorModelArrayList.get(0).getDoctor_id();

        errorMessageDialog= new ErrorMessageDialog(ServiceListActivity.this);


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(ServiceListActivity.this);
        recyclerViewServiceList.setLayoutManager(linearLayoutManager);



    }
    private void callServiceList() {

        Call<SuccessModel> call = apiInterface.serviceList(doctor_id);


        call.enqueue(new Callback<SuccessModel>() {
            @Override
            public void onResponse(Call<SuccessModel> call, Response<SuccessModel> response) {
                String str_response = new Gson().toJson(response.body());
                Log.e("" + TAG, "Response >>>>" + str_response);

                try {
                    if (response.isSuccessful()) {
                        SuccessModel successModel = response.body();

                        String message = null, code = null;
                        if (successModel != null) {
                            message = successModel.getMsg();
                            code = successModel.getError_code();


                            if (code.equalsIgnoreCase("1")) {

                                serviceModelArrayList=successModel.getServiceModelArrayList();

                                if(doctorModelArrayList.size()!=0) {

                                    serviceListAdapter = new ServiceListAdapter(ServiceListActivity.this, serviceModelArrayList);
                                    recyclerViewServiceList.setAdapter(serviceListAdapter);
                                    recyclerViewServiceList.addItemDecoration(new DividerItemDecoration(ServiceListActivity.this, DividerItemDecoration.VERTICAL));
                                    recyclerViewServiceList.setHasFixedSize(true);
                                }
                                else
                                {
                                    errorMessageDialog.showDialog("List Not found");
                                }

                            } else {
                                errorMessageDialog.showDialog("List Not found");
                            }

                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            @Override
            public void onFailure(Call<SuccessModel> call, Throwable t) {
                errorMessageDialog.showDialog(t.getMessage());
            }
        });

    }

}
