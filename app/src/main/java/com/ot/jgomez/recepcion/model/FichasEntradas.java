package com.ot.jgomez.recepcion.model;

import java.util.Date;

/**
 * Created by jgomez on 8/02/17.
 */

public class FichasEntradas {
    private String mFechaEntrada;
    private String mTipoVehiculo;
    private String mMarcaVehiculo;
    private String mModeloVehiculo;
    private String mMatriculaVehiculo;
    private String mResumenEntrada;
    private String mDescripcionEntrada;
    private String mResolucion;
    private String mFechaSalida;

    public FichasEntradas(String mFechaEntrada, String mTipoVehiculo, String mMarcaVehiculo,
                          String mModeloVehiculo, String mMatriculaVehiculo, String mResumenEntrada,
                          String mDescripcionEntrada) {
        this.mFechaEntrada = mFechaEntrada;
        this.mTipoVehiculo = mTipoVehiculo;
        this.mMarcaVehiculo = mMarcaVehiculo;
        this.mModeloVehiculo = mModeloVehiculo;
        this.mMatriculaVehiculo = mMatriculaVehiculo;
        this.mResumenEntrada = mResumenEntrada;
        this.mDescripcionEntrada = mDescripcionEntrada;
    }

    public String getFechaEntrada() {
        return mFechaEntrada;
    }

    public void setFechaEntrada(String mFechaEntrada) {
        this.mFechaEntrada = mFechaEntrada;
    }

    public String getTipoVehiculo() {
        return mTipoVehiculo;
    }

    public void setTipoVehiculo(String mTipoVehiculo) {
        this.mTipoVehiculo = mTipoVehiculo;
    }

    public String getMarcaVehiculo() {
        return mMarcaVehiculo;
    }

    public void setMarcaVehiculo(String mMarcaVehiculo) {
        this.mMarcaVehiculo = mMarcaVehiculo;
    }

    public String getModeloVehiculo() {
        return mModeloVehiculo;
    }

    public void setModeloVehiculo(String mModeloVehiculo) {
        this.mModeloVehiculo = mModeloVehiculo;
    }

    public String getMatriculaVehiculo() {
        return mMatriculaVehiculo;
    }

    public void setMatriculaVehiculo(String mMatriculaVehiculo) {
        this.mMatriculaVehiculo = mMatriculaVehiculo;
    }

    public String getResumenEntrada() {
        return mResumenEntrada;
    }

    public void setResumenEntrada(String mResumenEntrada) {
        this.mResumenEntrada = mResumenEntrada;
    }

    public String getDescripcionEntrada() {
        return mDescripcionEntrada;
    }

    public void setDescripcionEntrada(String mDescripcionEntrada) {
        this.mDescripcionEntrada = mDescripcionEntrada;
    }

    public String getResolucion() {
        return mResolucion;
    }

    public void setResolucion(String mResolucion) {
        this.mResolucion = mResolucion;
    }

    public String getFechaSalida() {
        return mFechaSalida;
    }

    public void setFechaSalida(String mFechaSalida) {
        this.mFechaSalida = mFechaSalida;
    }
}
