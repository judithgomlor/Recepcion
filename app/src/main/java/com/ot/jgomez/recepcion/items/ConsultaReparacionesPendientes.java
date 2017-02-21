package com.ot.jgomez.recepcion.items;

/**
 * Created by jgomez on 14/02/17.
 */

public class ConsultaReparacionesPendientes {
    private String mNombre;
    private String mPrimerApellido;
    private String mSegundoApellido;
    private String mMarcaVehiculo;
    private String mModeloVehiculo;
    private String mMatriculaVehiculo;
    private String mFechaEntrada;
    private String mResumenEntrada;
    private String mDescripcionEntrada;
    private String mResolucionEntrada;
    private String mFechaSalida;
    private String mPrecioReparacion;

    public ConsultaReparacionesPendientes(String mNombre, String mPrimerApellido,
                                          String mSegundoApellido, String mMarcaVehiculo,
                                          String mModeloVehiculo, String mMatriculaVehiculo,
                                          String mFechaEntrada, String mResumenEntrada,
                                          String mDescripcionEntrada, String mResolucionEntrada,
                                          String mFechaSalida, String mPrecioReparacion) {
        this.mNombre = mNombre;
        this.mPrimerApellido = mPrimerApellido;
        this.mSegundoApellido = mSegundoApellido;
        this.mMarcaVehiculo = mMarcaVehiculo;
        this.mModeloVehiculo = mModeloVehiculo;
        this.mMatriculaVehiculo = mMatriculaVehiculo;
        this.mFechaEntrada = mFechaEntrada;
        this.mResumenEntrada = mResumenEntrada;
        this.mDescripcionEntrada = mDescripcionEntrada;
        this.mResolucionEntrada = mResolucionEntrada;
        this.mFechaSalida = mFechaSalida;
        this.mPrecioReparacion = mPrecioReparacion;
    }

    public String getmNombre() {
        return mNombre;
    }

    public void setmNombre(String mNombre) {
        this.mNombre = mNombre;
    }

    public String getmPrimerApellido() {
        return mPrimerApellido;
    }

    public void setmPrimerApellido(String mPrimerApellido) {
        this.mPrimerApellido = mPrimerApellido;
    }

    public String getmSegundoApellido() {
        return mSegundoApellido;
    }

    public void setmSegundoApellido(String mSegundoApellido) {
        this.mSegundoApellido = mSegundoApellido;
    }

    public String getmMarcaVehiculo() {
        return mMarcaVehiculo;
    }

    public void setmMarcaVehiculo(String mMarcaVehiculo) {
        this.mMarcaVehiculo = mMarcaVehiculo;
    }

    public String getmModeloVehiculo() {
        return mModeloVehiculo;
    }

    public void setmModeloVehiculo(String mModeloVehiculo) {
        this.mModeloVehiculo = mModeloVehiculo;
    }

    public String getmMatriculaVehiculo() {
        return mMatriculaVehiculo;
    }

    public void setmMatriculaVehiculo(String mMatriculaVehiculo) {
        this.mMatriculaVehiculo = mMatriculaVehiculo;
    }

    public String getmFechaEntrada() {
        return mFechaEntrada;
    }

    public void setmFechaEntrada(String mFechaEntrada) {
        this.mFechaEntrada = mFechaEntrada;
    }

    public String getmResumenEntrada() {
        return mResumenEntrada;
    }

    public void setmResumenEntrada(String mResumenEntrada) {
        this.mResumenEntrada = mResumenEntrada;
    }

    public String getmDescripcionEntrada() {
        return mDescripcionEntrada;
    }

    public void setmDescripcionEntrada(String mDescripcionEntrada) {
        this.mDescripcionEntrada = mDescripcionEntrada;
    }

    public String getmResolucionEntrada() {
        return mResolucionEntrada;
    }

    public void setmResolucionEntrada(String mResolucionEntrada) {
        this.mResolucionEntrada = mResolucionEntrada;
    }

    public String getmFechaSalida() {
        return mFechaSalida;
    }

    public void setmFechaSalida(String mFechaSalida) {
        this.mFechaSalida = mFechaSalida;
    }

    public String getmPrecioReparacion() {
        return mPrecioReparacion;
    }

    public void setmPrecioReparacion(String mPrecioReparacion) {
        this.mPrecioReparacion = mPrecioReparacion;
    }
}
