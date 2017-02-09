package com.ot.jgomez.recepcion.api;

import android.content.Context;

/**
 * Created by jgomez on 9/02/17.
 */

public class ServiceVehicle {
    private static serverAPI service = null;

    public static serverAPI getService(Context context) {
        service = ServiceGenerator.createService(serverAPI.class, context);
        return service;
    }
}
