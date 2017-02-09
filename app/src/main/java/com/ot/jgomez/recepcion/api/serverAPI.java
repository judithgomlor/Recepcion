package com.ot.jgomez.recepcion.api;

import com.ot.jgomez.recepcion.api.entities.DataResponse;
import com.ot.jgomez.recepcion.api.entities.Makes;
import com.ot.jgomez.recepcion.api.entities.MakesForVehicle;
import com.ot.jgomez.recepcion.api.entities.ModelsForMake;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by jgomez on 9/02/17.
 */

public interface serverAPI {

    @GET("/vehicles/GetAllMakes?format=json")
    Call<DataResponse<Makes>> getAllMakes();

    @GET("/vehicles/GetMakesForVehicleType/{type}?format=json")
    Call<DataResponse<MakesForVehicle>> getMakesForVehicle (@Path("type") int type);

    @GET("/vehicles/GetModelsForMakeId/{vehicleId}?format=json")
    Call<DataResponse<ModelsForMake>> getModelsForMakeId (@Path("vehicleId") int id);
}
