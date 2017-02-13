package com.ot.jgomez.recepcion.database;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

/**
 * Created by jgomez on 8/02/17.
 */

@Table(database = ClientesDatabase.class)
public class DBClientes extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    int id;

    @Column
    String nombreCliente;

    @Column
    String primerApellidoCliente;

    @Column
    String segundoApellidoCliente;

    @Column
    String marcaVehiculo;

    @Column
    String modeloVehiculo;

    @Column
    String matriculaVehiculo;

    @Column
    String telefonoCliente;

    public DBClientes(String nombreCliente, String primerApellidoCliente, String segundoApellidoCliente,
                      String marcaVehiculo, String modeloVehiculo,
                      String matriculaVehiculo, String telefonoCliente) {
        this.nombreCliente = nombreCliente;
        this.primerApellidoCliente = primerApellidoCliente;
        this.segundoApellidoCliente = segundoApellidoCliente;
        this.marcaVehiculo = marcaVehiculo;
        this.modeloVehiculo = modeloVehiculo;
        this.matriculaVehiculo = matriculaVehiculo;
        this.telefonoCliente = telefonoCliente;
    }

    public DBClientes() {

    }

    public static List<DBClientes> getAllClientes() {
        return new SQLite().select().from(DBClientes.class).queryList();
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getPrimerApellidoCliente() {
        return primerApellidoCliente;
    }

    public void setPrimerApellidoCliente(String primerApellidoCliente) {
        this.primerApellidoCliente = primerApellidoCliente;
    }

    public String getSegundoApellidoCliente() {
        return segundoApellidoCliente;
    }

    public void setSegundoApellidoCliente(String segundoApellidoCliente) {
        this.segundoApellidoCliente = segundoApellidoCliente;
    }

    public String getMarcaVehiculo() {
        return marcaVehiculo;
    }

    public void setMarcaVehiculo(String marcaVehiculo) {
        this.marcaVehiculo = marcaVehiculo;
    }

    public String getModeloVehiculo() {
        return modeloVehiculo;
    }

    public void setModeloVehiculo(String modeloVehiculo) {
        this.modeloVehiculo = modeloVehiculo;
    }

    public String getMatriculaVehiculo() {
        return matriculaVehiculo;
    }

    public void setMatriculaVehiculo(String matriculaVehiculo) {
        this.matriculaVehiculo = matriculaVehiculo;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }
}
