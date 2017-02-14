package com.ot.jgomez.recepcion.items;

/**
 * Created by jgomez on 13/02/17.
 */

public class ConsultaClientes {
    private String mNombre;
    private String mPrimerApellido;
    private String mSegundoApellido;
    private String mTelefono;
    private String mMarcaVehiculo;
    private String mModeloVehiculo;
    private String mMatriculaVehiculo;

    public ConsultaClientes(String mNombre, String mPrimerApellido, String mSegundoApellido,
                            String mTelefono, String mMarcaVehiculo, String mModeloVehiculo,
                            String mMatriculaVehiculo) {
        this.mNombre = mNombre;
        this.mPrimerApellido = mPrimerApellido;
        this.mSegundoApellido = mSegundoApellido;
        this.mTelefono = mTelefono;
        this.mMarcaVehiculo = mMarcaVehiculo;
        this.mModeloVehiculo = mModeloVehiculo;
        this.mMatriculaVehiculo = mMatriculaVehiculo;
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

    public String getmTelefono() {
        return mTelefono;
    }

    public void setmTelefono(String mTelefono) {
        this.mTelefono = mTelefono;
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
}
