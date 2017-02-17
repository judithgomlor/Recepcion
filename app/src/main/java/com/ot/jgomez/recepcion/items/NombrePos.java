package com.ot.jgomez.recepcion.items;

/**
 * Created by jgomez on 17/02/17.
 */

public class NombrePos {
    private String mNombre;
    private int mPosicion;

    public NombrePos(String mNombre, int mPosicion) {
        this.mNombre = mNombre;
        this.mPosicion = mPosicion;
    }

    public String getmNombre() {
        return mNombre;
    }

    public void setmNombre(String mNombre) {
        this.mNombre = mNombre;
    }

    public int getmPosicion() {
        return mPosicion;
    }

    public void setmPosicion(int mPosicion) {
        this.mPosicion = mPosicion;
    }
}
