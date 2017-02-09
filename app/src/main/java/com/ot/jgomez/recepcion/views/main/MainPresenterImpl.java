package com.ot.jgomez.recepcion.views.main;

import android.content.Context;

import com.ot.jgomez.recepcion.R;
import com.ot.jgomez.recepcion.api.ServiceVehicle;
import com.ot.jgomez.recepcion.api.entities.DataResponse;
import com.ot.jgomez.recepcion.api.entities.Makes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jgomez on 9/02/17.
 */

public class MainPresenterImpl implements MainContract.Presenter {

    private final ServiceVehicle mService;
    private Context mContext;
    private MainContract.View mView;

    public MainPresenterImpl(ServiceVehicle service) {
        this.mService = service;
    }

    @Override
    public void getMakes(String search) {
        Call<DataResponse<Makes>> call = mService.getService(this.mContext)
                .getAllMakes();
        call.enqueue(new Callback<DataResponse<Makes>>() {
            @Override
            public void onResponse(Call<DataResponse<Makes>> call, Response<DataResponse<Makes>> response) {
                if(response.isSuccessful()) {
                    if(response.body().Results.size() > 0) {
                        mView.fillDataMakes(response.body().Results);
                    } else {
                        mView.showMessage(mContext.getString(R.string.no_resultados));
                    }
                }
            }

            @Override
            public void onFailure(Call<DataResponse<Makes>> call, Throwable t) {
                mView.showMessage(mContext.getString(R.string.error_resultados));
            }
        });
    }

    @Override
    public void getModelsbyMakes(String search) {

    }

    @Override
    public void attach(Context context, MainContract.View view) {
        this.mContext = context;
        this.mView = view;
    }
}
