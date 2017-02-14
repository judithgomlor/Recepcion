package com.ot.jgomez.recepcion.database;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

/**
 * Created by jgomez on 14/02/17.
 */
@Table(database = RegistroEntradasDatabase.class)
public class DBRegistroEntradas extends BaseModel {
    @Column
    @PrimaryKey(autoincrement = true)
    int id;

    @Column
    String nombre;

    @Column
    String primerApellido;

    @Column
    String segundoApellido;

    @Column
    String marcaVehiculo;

    @Column
    String modeloVehículo;

    @Column
    String matriculaVehiculo;

    @Column
    String fechaEntrada;

    @Column
    String resumenEntrada;

    @Column
    String descripcionEntrada;

    @Column
    String resolucionEntrada;

    @Column
    String fechaSalida;

    public DBRegistroEntradas(String nombre, String primerApellido, String segundoApellido,
                      String marcaVehiculo, String modeloVehículo, String matriculaVehiculo,
                      String fechaEntrada, String resumenEntrada, String descripcionEntrada,
                      String resolucionEntrada, String fechaSalida) {
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.marcaVehiculo = marcaVehiculo;
        this.modeloVehículo = modeloVehículo;
        this.matriculaVehiculo = matriculaVehiculo;
        this.fechaEntrada = fechaEntrada;
        this.resumenEntrada = resumenEntrada;
        this.descripcionEntrada = descripcionEntrada;
        this.resolucionEntrada = resolucionEntrada;
        this.fechaSalida = fechaSalida;
    }

    public DBRegistroEntradas() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public static List<DBRegistroEntradas> getAllEntradas() {
        return new SQLite().select().from(DBRegistroEntradas.class).queryList();
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
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
