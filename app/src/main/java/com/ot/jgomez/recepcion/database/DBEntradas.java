package com.ot.jgomez.recepcion.database;

import com.ot.jgomez.recepcion.model.FichasEntradas;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

/**
 * Created by jgomez on 8/02/17.
 */
@Table(database = EntradasDatabase.class)
public class DBEntradas extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    int id;

    @Column
    String fechaEntrada;

    @Column
    String tipoVehiculo;

    @Column
    String marcaVehiculo;

    @Column
    String modeloVehículo;

    @Column
    String matriculaVehiculo;

    @Column
    String resumenEntrada;

    @Column
    String descripcionEntrada;

    @Column
    String resolucionEntrada;

    @Column
    String fechaSalida;

    public DBEntradas(String fechaEntrada, String tipoVehiculo, String marcaVehiculo,
                      String modeloVehículo, String matriculaVehiculo, String resumenEntrada,
                      String descripcionEntrada, String resolucionEntrada, String fechaSalida) {
        this.fechaEntrada = fechaEntrada;
        this.tipoVehiculo = tipoVehiculo;
        this.marcaVehiculo = marcaVehiculo;
        this.modeloVehículo = modeloVehículo;
        this.matriculaVehiculo = matriculaVehiculo;
        this.resumenEntrada = resumenEntrada;
        this.descripcionEntrada = descripcionEntrada;
        this.resolucionEntrada = resolucionEntrada;
        this.fechaSalida = fechaSalida;
    }

    public DBEntradas() {

    }

    public static List<DBEntradas> getAllEntradas() {
        return new SQLite().select().from(DBEntradas.class).queryList();
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getMarcaVehiculo() {
        return marcaVehiculo;
    }

    public void setMarcaVehiculo(String marcaVehiculo) {
        this.marcaVehiculo = marcaVehiculo;
    }

    public String getModeloVehículo() {
        return modeloVehículo;
    }

    public void setModeloVehículo(String modeloVehículo) {
        this.modeloVehículo = modeloVehículo;
    }

    public String getMatriculaVehiculo() {
        return matriculaVehiculo;
    }

    public void setMatriculaVehiculo(String matriculaVehiculo) {
        this.matriculaVehiculo = matriculaVehiculo;
    }

    public String getResumenEntrada() {
        return resumenEntrada;
    }

    public void setResumenEntrada(String resumenEntrada) {
        this.resumenEntrada = resumenEntrada;
    }

    public String getDescripcionEntrada() {
        return descripcionEntrada;
    }

    public void setDescripcionEntrada(String descripcionEntrada) {
        this.descripcionEntrada = descripcionEntrada;
    }

    public String getResolucionEntrada() {
        return resolucionEntrada;
    }

    public void setResolucionEntrada(String resolucionEntrada) {
        this.resolucionEntrada = resolucionEntrada;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
}
