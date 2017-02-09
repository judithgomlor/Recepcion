package com.ot.jgomez.recepcion.model;

/**
 * Created by jgomez on 8/02/17.
 */

public class FichasClientes {
    private String mNombre;
    private String mPrimerApellido;
    private String mSegundoApellido;
    private String mTipoVehiculo;
    private String mMarcaVehiculo;
    private String mModeloVehiculo;
    private String mMatriculaVehiculo;
    private String mTelefono;

    public FichasClientes(String mNombre, String mPrimerApellido, String mSegundoApellido,
                          String mTipoVehiculo, String mMarcaVehiculo, String mModeloVehiculo,
                          String mMatriculaVehiculo, String mTelefono) {
        this.mNombre = mNombre;
        this.mPrimerApellido = mPrimerApellido;
        this.mSegundoApellido = mSegundoApellido;
        this.mTipoVehiculo = mTipoVehiculo;
        this.mMarcaVehiculo = mMarcaVehiculo;
        this.mModeloVehiculo = mModeloVehiculo;
        this.mMatriculaVehiculo = mMatriculaVehiculo;
        this.mTelefono = mTelefono;
    }

    public String getNombre() {
        return mNombre;
    }

    public void setNombre(String mNombre) {
        this.mNombre = mNombre;
    }

    public String getPrimerApellido() {
        return mPrimerApellido;
    }

    public void setPrimerApellido(String mPrimerApellido) {
        this.mPrimerApellido = mPrimerApellido;
    }

    public String getSegundoApellido() {
        return mSegundoApellido;
    }

    public void setSegundoApellido(String mSegundoApellido) {
        this.mSegundoApellido = mSegundoApellido;
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

    public String getTelefono() {
        return mTelefono;
    }

    public void setTelefono(String mTelefono) {
        this.mTelefono = mTelefono;
    }
}
