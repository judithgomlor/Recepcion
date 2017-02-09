package com.ot.jgomez.recepcion.items;

/**
 * Created by jgomez on 8/02/17.
 */

public class ConsultaReparaciones {
    private String mNombreApellidos;
    private String mResumen;
    private String mVehiculo;

    public ConsultaReparaciones(String mNombreApellidos, String mResumen, String mVehiculo) {
        this.mNombreApellidos = mNombreApellidos;
        this.mResumen = mResumen;
        this.mVehiculo = mVehiculo;
    }

    public String getNombreApellidos() {
        return mNombreApellidos;
    }

    public void setNombreApellidos(String mNombreApellidos) {
        this.mNombreApellidos = mNombreApellidos;
    }

    public String getResumen() {
        return mResumen;
    }

    public void setResumen(String mResumen) {
        this.mResumen = mResumen;
    }

    public String getVehiculo() {
        return mVehiculo;
    }

    public void setVehiculo(String mVehiculo) {
        this.mVehiculo = mVehiculo;
    }
}
