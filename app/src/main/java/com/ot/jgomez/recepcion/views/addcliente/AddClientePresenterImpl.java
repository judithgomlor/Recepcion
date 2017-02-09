package com.ot.jgomez.recepcion.views.addcliente;

import android.content.Context;

import com.ot.jgomez.recepcion.R;
import com.ot.jgomez.recepcion.api.ServiceVehicle;
import com.ot.jgomez.recepcion.api.entities.DataResponse;
import com.ot.jgomez.recepcion.api.entities.Makes;
import com.ot.jgomez.recepcion.api.entities.ModelsForMake;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jgomez on 9/02/17.
 */

public class AddClientePresenterImpl implements AddClienteContract.Presenter {

    private final ServiceVehicle mService;
    private Context mContext;
    private AddClienteContract.View mView;

    public AddClientePresenterImpl(ServiceVehicle service) {
        this.mService = service;
    }

    @Override
    public void getMakes(String search) {
        Call<DataResponse<Makes>> call = this.mService.getService(this.mContext)
                .getAllMakes();
        call.enqueue(new Callback<DataResponse<Makes>>() {
            @Override
            public void onResponse(Call<DataResponse<Makes>> call, Response<DataResponse<Makes>> response) {
                if(response.isSuccessful()) {
                    mView.fillDataMakes(response.body().Results);
                } else {
                    mView.showMessage(mContext.getString(R.string.no_resultados));
                }
            }

            @Override
            public void onFailure(Call<DataResponse<Makes>> call, Throwable t) {
                mView.showMessage(mContext.getString(R.string.error_resultados));
            }
        });
    }

    @Override
    public void getModelsbyMakes(int Id) {
        Call<DataResponse<ModelsForMake>> call = this.mService.getService(this.mContext)
                .getModelsForMakeId(Id);
        call.enqueue(new Callback<DataResponse<ModelsForMake>>() {
            @Override
            public void onResponse(Call<DataResponse<ModelsForMake>> call, Response<DataResponse<ModelsForMake>> response) {
                if(response.isSuccessful()) {
                    if (response.body().Results.size() > 0) {
                        mView.fillDataModelsbyMakes(response.body().Results);
                    } else {
                        mView.showMessage(mContext.getString(R.string.no_resultados));
                    }
                }
            }

            @Override
            public void onFailure(Call<DataResponse<ModelsForMake>> call, Throwable t) {
                mView.showMessage(mContext.getString(R.string.error_resultados));
            }
        });
    }

    @Override
    public void attach(Context context, AddClienteContract.View view) {
        this.mContext = context;
        this.mView = view;
    }
}
