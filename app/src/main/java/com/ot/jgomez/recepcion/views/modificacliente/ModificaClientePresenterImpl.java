package com.ot.jgomez.recepcion.views.modificacliente;

import android.content.Context;
import android.util.Log;

import com.ot.jgomez.recepcion.control.MergeSort;
import com.ot.jgomez.recepcion.database.DBClientes;
import com.ot.jgomez.recepcion.database.DBClientes_Table;
import com.ot.jgomez.recepcion.items.ConsultaClientes;
import com.ot.jgomez.recepcion.items.NombrePos;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jgomez on 22/02/17.
 */

public class ModificaClientePresenterImpl implements ModificaClienteContract.Presenter {

    private Context mContext;
    private ModificaClienteContract.View mView;
    private List<ConsultaClientes> mListClientesBusqueda;
    private List<ConsultaClientes> mListClientesSinBusqueda;
    private List<ConsultaClientes> mListAuxClientesBusqueda;
    private List<ConsultaClientes> mListAuxClientesSinBusqueda;
    private List<NombrePos> mListNombresPosBusqueda;
    private List<NombrePos> mListNombresPosSinBusqueda;
    private boolean mBoolConBusqueda;
    private boolean mBoolSinBusqueda;

    /**
     * Devuelve una lista de clientes sin tratarla préviamente
     *
     * @return lista de clientes
     */
    @Override
    public List<ConsultaClientes> getClientesSinTratarLista() {
        List<ConsultaClientes> list = new ArrayList<>();
        List<DBClientes> clientes = DBClientes.getAllClientes();
        if (clientes.size() > 0) {
            for (DBClientes entries : clientes) {
                list.add(new ConsultaClientes(
                        entries.getNombreCliente(),
                        entries.getPrimerApellidoCliente(),
                        entries.getSegundoApellidoCliente(),
                        entries.getNombreApellidos(),
                        entries.getTelefonoCliente(),
                        entries.getMarcaVehiculo(),
                        entries.getModeloVehiculo(),
                        entries.getMatriculaVehiculo()
                ));
            }
        }
        return list;
    }

    /**
     * Guarda un vehículo nuevo mediante los datos personales del cliente
     *
     * @param nombre
     * @param primerApellido
     * @param segundoApellido
     * @param nombreApellidos
     * @param telefono
     * @param marca
     * @param modelo
     * @param matricula
     */
    @Override
    public void guardaVehiculoNuevo(String nombre, String primerApellido, String segundoApellido,
                                    String nombreApellidos, String telefono, String marca,
                                    String modelo, String matricula) {

        DBClientes cliente = new DBClientes(nombre, primerApellido, segundoApellido, nombreApellidos,
                marca, modelo, matricula, telefono);
        cliente.save();

        //  this.mView.showMessage("Añadido nuevo vehículo");
    }

    /**
     * Elimina el vehículo que se le pasa por parámetros junto con los datos personales.
     *
     * @param nombre
     * @param primerApellido
     * @param segundoApellido
     * @param nombreApellidos
     * @param telefono
     * @param marca
     * @param modelo
     * @param matricula
     */
    @Override
    public void eliminaVehiculo(String nombre, String primerApellido, String segundoApellido,
                                String nombreApellidos, String telefono, String marca, String modelo,
                                String matricula) {
        SQLite.delete()
                .from(DBClientes.class)
                .where(DBClientes_Table.nombreCliente.is(nombre))
                .and(DBClientes_Table.primerApellidoCliente.is(primerApellido))
                .and(DBClientes_Table.segundoApellidoCliente.is(segundoApellido))
                .and(DBClientes_Table.nombreApellidos.is(nombreApellidos))
                .and(DBClientes_Table.telefonoCliente.is(telefono))
                .and(DBClientes_Table.marcaVehiculo.is(marca))
                .and(DBClientes_Table.modeloVehiculo.is(modelo))
                .and(DBClientes_Table.matriculaVehiculo.is(matricula))
                .async()
                .execute();
    }

    /**
     * Modifica la marca, el modelo y la matrícula de un vehículo en concreto
     *
     * @param nombre
     * @param primerApellido
     * @param segundoApellido
     * @param nombreApellidos
     * @param telefono
     * @param marcaAntigua
     * @param modeloAntiguo
     * @param matriculaAntigua
     * @param marcaNueva
     * @param modeloNuevo
     * @param matriculaNueva
     */
    //SSS
    @Override
    public void modificaMarcaModeloMatricula(String nombre, String primerApellido, String segundoApellido,
                                             String nombreApellidos, String telefono, String marcaAntigua,
                                             String modeloAntiguo, String matriculaAntigua, String marcaNueva,
                                             String modeloNuevo, String matriculaNueva) {

        SQLite.update(DBClientes.class)
                .set(DBClientes_Table.marcaVehiculo.eq(marcaNueva),
                        DBClientes_Table.modeloVehiculo.eq(modeloNuevo),
                        DBClientes_Table.matriculaVehiculo.eq(matriculaNueva))
                .where(DBClientes_Table.marcaVehiculo.is(marcaAntigua))
                .and(DBClientes_Table.modeloVehiculo.is(modeloAntiguo))
                .and(DBClientes_Table.matriculaVehiculo.is(matriculaAntigua))
                .and(DBClientes_Table.nombreCliente.is(nombre))
                .and(DBClientes_Table.primerApellidoCliente.is(primerApellido))
                .and(DBClientes_Table.segundoApellidoCliente.is(segundoApellido))
                .and(DBClientes_Table.nombreApellidos.is(nombreApellidos))
                .and(DBClientes_Table.telefonoCliente.is(telefono))
                .async()
                .execute();

        //   this.mView.showMessage("Marca, modelo y matrícula modificados");
    }

    /**
     * Modifica la marca y el modelo de un vehículo en concreto
     *
     * @param nombre
     * @param primerApellido
     * @param segundoApellido
     * @param nombreApellidos
     * @param telefono
     * @param marcaAntigua
     * @param modeloAntiguo
     * @param matriculaAntigua
     * @param marcaNueva
     * @param modeloNuevo
     */
    //SSN
    @Override
    public void modificaMarcaModelo(String nombre, String primerApellido, String segundoApellido,
                                    String nombreApellidos, String telefono, String marcaAntigua,
                                    String modeloAntiguo, String matriculaAntigua, String marcaNueva,
                                    String modeloNuevo) {
        SQLite.update(DBClientes.class)
                .set(DBClientes_Table.marcaVehiculo.eq(marcaNueva),
                        DBClientes_Table.modeloVehiculo.eq(modeloNuevo))
                .where(DBClientes_Table.marcaVehiculo.is(marcaAntigua))
                .and(DBClientes_Table.modeloVehiculo.is(modeloAntiguo))
                .and(DBClientes_Table.matriculaVehiculo.is(matriculaAntigua))
                .and(DBClientes_Table.nombreCliente.is(nombre))
                .and(DBClientes_Table.primerApellidoCliente.is(primerApellido))
                .and(DBClientes_Table.segundoApellidoCliente.is(segundoApellido))
                .and(DBClientes_Table.nombreApellidos.is(nombreApellidos))
                .and(DBClientes_Table.telefonoCliente.is(telefono))
                .async()
                .execute();

        // this.mView.showMessage("Marca y modelo modificados");
    }

    /**
     * Modifica la marca y la matrícula de un vehículo en concreto.
     *
     * @param nombre
     * @param primerApellido
     * @param segundoApellido
     * @param nombreApellidos
     * @param telefono
     * @param marcaAntigua
     * @param modeloAntiguo
     * @param matriculaAntigua
     * @param marcaNueva
     * @param matriculaNueva
     */
    //SNS
    @Override
    public void modificaMarcaMatricula(String nombre, String primerApellido, String segundoApellido,
                                       String nombreApellidos, String telefono, String marcaAntigua,
                                       String modeloAntiguo, String matriculaAntigua, String marcaNueva,
                                       String matriculaNueva) {
        SQLite.update(DBClientes.class)
                .set(DBClientes_Table.marcaVehiculo.eq(marcaNueva),
                        DBClientes_Table.matriculaVehiculo.eq(matriculaNueva))
                .where(DBClientes_Table.marcaVehiculo.is(marcaAntigua))
                .and(DBClientes_Table.modeloVehiculo.is(modeloAntiguo))
                .and(DBClientes_Table.matriculaVehiculo.is(matriculaAntigua))
                .and(DBClientes_Table.nombreCliente.is(nombre))
                .and(DBClientes_Table.primerApellidoCliente.is(primerApellido))
                .and(DBClientes_Table.segundoApellidoCliente.is(segundoApellido))
                .and(DBClientes_Table.nombreApellidos.is(nombreApellidos))
                .and(DBClientes_Table.telefonoCliente.is(telefono))
                .async()
                .execute();

        // this.mView.showMessage("Marca y matrícula modificados");
    }

    /**
     * Modifica la marca de un vehículo en concreto.
     *
     * @param nombre
     * @param primerApellido
     * @param segundoApellido
     * @param nombreApellidos
     * @param telefono
     * @param marcaAntigua
     * @param modeloAntiguo
     * @param matriculaAntigua
     * @param marcaNueva
     */
    //SNN
    @Override
    public void modificaMarca(String nombre, String primerApellido, String segundoApellido,
                              String nombreApellidos, String telefono, String marcaAntigua,
                              String modeloAntiguo, String matriculaAntigua, String marcaNueva) {
        SQLite.update(DBClientes.class)
                .set(DBClientes_Table.marcaVehiculo.eq(marcaNueva))
                .where(DBClientes_Table.marcaVehiculo.is(marcaAntigua))
                .and(DBClientes_Table.modeloVehiculo.is(modeloAntiguo))
                .and(DBClientes_Table.matriculaVehiculo.is(matriculaAntigua))
                .and(DBClientes_Table.nombreCliente.is(nombre))
                .and(DBClientes_Table.primerApellidoCliente.is(primerApellido))
                .and(DBClientes_Table.segundoApellidoCliente.is(segundoApellido))
                .and(DBClientes_Table.nombreApellidos.is(nombreApellidos))
                .and(DBClientes_Table.telefonoCliente.is(telefono))
                .async()
                .execute();

        //    this.mView.showMessage("Marca modificada");
    }

    /**
     * Modifica el modelo y la matrícula de un vehículo en concreto.
     *
     * @param nombre
     * @param primerApellido
     * @param segundoApellido
     * @param nombreApellidos
     * @param telefono
     * @param marcaAntigua
     * @param modeloAntiguo
     * @param matriculaAntigua
     * @param modeloNuevo
     * @param matriculaNueva
     */
    //NSS
    @Override
    public void modificaModeloMatricula(String nombre, String primerApellido, String segundoApellido,
                                        String nombreApellidos, String telefono, String marcaAntigua,
                                        String modeloAntiguo, String matriculaAntigua, String modeloNuevo,
                                        String matriculaNueva) {

        SQLite.update(DBClientes.class)
                .set(DBClientes_Table.modeloVehiculo.eq(modeloNuevo),
                        DBClientes_Table.matriculaVehiculo.eq(matriculaNueva))
                .where(DBClientes_Table.marcaVehiculo.is(marcaAntigua))
                .and(DBClientes_Table.modeloVehiculo.is(modeloAntiguo))
                .and(DBClientes_Table.matriculaVehiculo.is(matriculaAntigua))
                .and(DBClientes_Table.nombreCliente.is(nombre))
                .and(DBClientes_Table.primerApellidoCliente.is(primerApellido))
                .and(DBClientes_Table.segundoApellidoCliente.is(segundoApellido))
                .and(DBClientes_Table.nombreApellidos.is(nombreApellidos))
                .and(DBClientes_Table.telefonoCliente.is(telefono))
                .async()
                .execute();

//        this.mView.showMessage("Modelo y matrícula modificados");
    }

    /**
     * Modifica el modelo de un vehículo en concreto.
     *
     * @param nombre
     * @param primerApellido
     * @param segundoApellido
     * @param nombreApellidos
     * @param telefono
     * @param marcaAntigua
     * @param modeloAntiguo
     * @param matriculaAntigua
     * @param modeloNuevo
     */
    //NSN
    @Override
    public void modificaModelo(String nombre, String primerApellido, String segundoApellido,
                               String nombreApellidos, String telefono, String marcaAntigua,
                               String modeloAntiguo, String matriculaAntigua, String modeloNuevo) {

        SQLite.update(DBClientes.class)
                .set(DBClientes_Table.modeloVehiculo.eq(modeloNuevo))
                .where(DBClientes_Table.marcaVehiculo.is(marcaAntigua))
                .and(DBClientes_Table.modeloVehiculo.is(modeloAntiguo))
                .and(DBClientes_Table.matriculaVehiculo.is(matriculaAntigua))
                .and(DBClientes_Table.nombreCliente.is(nombre))
                .and(DBClientes_Table.primerApellidoCliente.is(primerApellido))
                .and(DBClientes_Table.segundoApellidoCliente.is(segundoApellido))
                .and(DBClientes_Table.nombreApellidos.is(nombreApellidos))
                .and(DBClientes_Table.telefonoCliente.is(telefono))
                .async()
                .execute();

        //   this.mView.showMessage("Modelo modificados");
    }

    /**
     * Modifica la matrícula de un vehículo en concreto.
     *
     * @param nombre
     * @param primerApellido
     * @param segundoApellido
     * @param nombreApellidos
     * @param telefono
     * @param marcaAntigua
     * @param modeloAntiguo
     * @param matriculaAntigua
     * @param matriculaNueva
     */
    //NNS
    @Override
    public void modificaMatricula(String nombre, String primerApellido, String segundoApellido,
                                  String nombreApellidos, String telefono, String marcaAntigua,
                                  String modeloAntiguo, String matriculaAntigua,
                                  String matriculaNueva) {
        SQLite.update(DBClientes.class)
                .set(DBClientes_Table.matriculaVehiculo.eq(matriculaNueva))
                .where(DBClientes_Table.marcaVehiculo.is(marcaAntigua))
                .and(DBClientes_Table.modeloVehiculo.is(modeloAntiguo))
                .and(DBClientes_Table.matriculaVehiculo.is(matriculaAntigua))
                .and(DBClientes_Table.nombreCliente.is(nombre))
                .and(DBClientes_Table.primerApellidoCliente.is(primerApellido))
                .and(DBClientes_Table.segundoApellidoCliente.is(segundoApellido))
                .and(DBClientes_Table.nombreApellidos.is(nombreApellidos))
                .and(DBClientes_Table.telefonoCliente.is(telefono))
                .async()
                .execute();

        //   this.mView.showMessage("matrícula modificados");
    }

    /**
     * Guarda el nombre, el primer, el segundo apellido y el teléfono del cliente
     *
     * @param nombre
     * @param primerApellido
     * @param segundoApellido
     * @param telefono
     * @param marca
     * @param modelo
     * @param matricula
     */
    //SSSS
    @Override
    public void guardaNombrePrimerSegundoTelefono(String nombre, String primerApellido,
                                                  String segundoApellido, String telefono, String marca,
                                                  String modelo, String matricula) {
        String nombreApellidos = nombre + " " + primerApellido + " " + segundoApellido;

        SQLite.update(DBClientes.class)
                .set(DBClientes_Table.nombreCliente.eq(nombre),
                        DBClientes_Table.primerApellidoCliente.eq(primerApellido),
                        DBClientes_Table.segundoApellidoCliente.eq(segundoApellido),
                        DBClientes_Table.nombreApellidos.eq(nombreApellidos),
                        DBClientes_Table.telefonoCliente.eq(telefono))
                .where(DBClientes_Table.marcaVehiculo.is(marca))
                .and(DBClientes_Table.modeloVehiculo.is(modelo))
                .and(DBClientes_Table.matriculaVehiculo.is(matricula))
                .async()
                .execute();

        // this.mView.showMessage("Nombre, primer, segundo apellido y teléfono modificados");
    }

    /**
     * Guarda el nombre, el primer apellido y el segundo del cliente
     *
     * @param nombre
     * @param primerApellido
     * @param segundoApellido
     * @param telefono
     * @param marca
     * @param modelo
     * @param matricula
     */
    //SSSN
    @Override
    public void guardaNombrePrimerSegundoApellido(String nombre, String primerApellido,
                                                  String segundoApellido, String telefono,
                                                  String marca, String modelo, String matricula) {
        String nombreApellidos = nombre + " " + primerApellido + " " + segundoApellido;

        SQLite.update(DBClientes.class)
                .set(DBClientes_Table.nombreCliente.eq(nombre),
                        DBClientes_Table.primerApellidoCliente.eq(primerApellido),
                        DBClientes_Table.segundoApellidoCliente.eq(segundoApellido),
                        DBClientes_Table.nombreApellidos.eq(nombreApellidos))
                .where(DBClientes_Table.telefonoCliente.is(telefono))
                .and(DBClientes_Table.marcaVehiculo.is(marca))
                .and(DBClientes_Table.modeloVehiculo.is(modelo))
                .and(DBClientes_Table.matriculaVehiculo.is(matricula))
                .async()
                .execute();

        // this.mView.showMessage("Nombre, primer y segundo apellido modificados");
    }

    /**
     * Guarda el nombre, el primer apellido y el teléfono del cliente.
     *
     * @param nombre
     * @param primerApellido
     * @param segundoApellido
     * @param telefono
     * @param marca
     * @param modelo
     * @param matricula
     */
    //SSNS
    @Override
    public void guardaNombrePrimerTelefono(String nombre, String primerApellido,
                                           String segundoApellido, String telefono, String marca,
                                           String modelo, String matricula) {
        String nombreApellidos = nombre + " " + primerApellido + " " + segundoApellido;

        SQLite.update(DBClientes.class)
                .set(DBClientes_Table.nombreCliente.eq(nombre),
                        DBClientes_Table.primerApellidoCliente.eq(primerApellido),
                        DBClientes_Table.nombreApellidos.eq(nombreApellidos),
                        DBClientes_Table.telefonoCliente.eq(telefono))
                .where(DBClientes_Table.segundoApellidoCliente.is(segundoApellido))
                .and(DBClientes_Table.marcaVehiculo.is(marca))
                .and(DBClientes_Table.modeloVehiculo.is(modelo))
                .and(DBClientes_Table.matriculaVehiculo.is(matricula))
                .async()
                .execute();

        //  this.mView.showMessage("Nombre, primer apellido y teléfono modificados");
    }

    /**
     * Guarda el nombre y el primer apellido del cliente.
     *
     * @param nombre
     * @param primerApellido
     * @param segundoApellido
     * @param telefono
     * @param marca
     * @param modelo
     * @param matricula
     */
    //SSNN
    @Override
    public void guardaNombrePrimerApellidoModificados(String nombre, String primerApellido,
                                                      String segundoApellido, String telefono,
                                                      String marca, String modelo, String matricula) {
        String nombreApellidos = nombre + " " + primerApellido + " " + segundoApellido;

        SQLite.update(DBClientes.class)
                .set(DBClientes_Table.nombreCliente.eq(nombre),
                        DBClientes_Table.primerApellidoCliente.eq(primerApellido),
                        DBClientes_Table.nombreApellidos.eq(nombreApellidos))
                .where(DBClientes_Table.segundoApellidoCliente.is(segundoApellido))
                .and(DBClientes_Table.telefonoCliente.is(telefono))
                .and(DBClientes_Table.marcaVehiculo.is(marca))
                .and(DBClientes_Table.modeloVehiculo.is(modelo))
                .and(DBClientes_Table.matriculaVehiculo.is(matricula))
                .async()
                .execute();

        //  this.mView.showMessage("Nombre y primer apellido modificados");
    }

    /**
     * Guarda nombre, el segundo apellido y el teléfono del cliente.
     *
     * @param nombre
     * @param primerApellido
     * @param segundoApellido
     * @param telefono
     * @param marca
     * @param modelo
     * @param matricula
     */
    //SNSS
    @Override
    public void guardaNombreSegundoTelefono(String nombre, String primerApellido,
                                            String segundoApellido, String telefono, String marca,
                                            String modelo, String matricula) {
        String nombreApellidos = nombre + " " + primerApellido + " " + segundoApellido;

        SQLite.update(DBClientes.class)
                .set(DBClientes_Table.nombreCliente.eq(nombre),
                        DBClientes_Table.segundoApellidoCliente.eq(segundoApellido),
                        DBClientes_Table.nombreApellidos.eq(nombreApellidos),
                        DBClientes_Table.telefonoCliente.eq(telefono))
                .where(DBClientes_Table.primerApellidoCliente.is(primerApellido))
                .and(DBClientes_Table.marcaVehiculo.is(marca))
                .and(DBClientes_Table.modeloVehiculo.is(modelo))
                .and(DBClientes_Table.matriculaVehiculo.is(matricula))
                .async()
                .execute();

        //   this.mView.showMessage("Nombre, segundo apellido y teléfono modificados");
    }

    /**
     * Guarda el nombre y el segundo apellido del teléfono.
     *
     * @param nombre
     * @param primerApellido
     * @param segundoApellido
     * @param telefono
     * @param marca
     * @param modelo
     * @param matricula
     */
    //SNSN
    @Override
    public void guardaNombreSegundo(String nombre, String primerApellido, String segundoApellido,
                                    String telefono, String marca, String modelo, String matricula) {
        String nombreApellidos = nombre + " " + primerApellido + " " + segundoApellido;

        SQLite.update(DBClientes.class)
                .set(DBClientes_Table.nombreCliente.eq(nombre),
                        DBClientes_Table.segundoApellidoCliente.eq(segundoApellido),
                        DBClientes_Table.nombreApellidos.eq(nombreApellidos))
                .where(DBClientes_Table.telefonoCliente.is(telefono))
                .and(DBClientes_Table.primerApellidoCliente.is(primerApellido))
                .and(DBClientes_Table.marcaVehiculo.is(marca))
                .and(DBClientes_Table.modeloVehiculo.is(modelo))
                .and(DBClientes_Table.matriculaVehiculo.is(matricula))
                .async()
                .execute();

        //  this.mView.showMessage("Nombre y segundo apellido modificados");
    }

    /**
     * Guarda el nombre y el teléfono del cliente.
     *
     * @param nombre
     * @param primerApellido
     * @param segundoApellido
     * @param telefono
     * @param marca
     * @param modelo
     * @param matricula
     */
    //SNNS
    @Override
    public void guardaNombreTelefono(String nombre, String primerApellido, String segundoApellido,
                                     String telefono, String marca, String modelo, String matricula) {
        String nombreApellidos = nombre + " " + primerApellido + " " + segundoApellido;

        SQLite.update(DBClientes.class)
                .set(DBClientes_Table.nombreCliente.eq(nombre),
                        DBClientes_Table.telefonoCliente.eq(telefono),
                        DBClientes_Table.nombreApellidos.eq(nombreApellidos))
                .where(DBClientes_Table.primerApellidoCliente.is(primerApellido))
                .and(DBClientes_Table.segundoApellidoCliente.is(segundoApellido))
                .and(DBClientes_Table.marcaVehiculo.is(marca))
                .and(DBClientes_Table.modeloVehiculo.is(modelo))
                .and(DBClientes_Table.matriculaVehiculo.is(matricula))
                .async()
                .execute();

        //   this.mView.showMessage("Nombre y teléfono modificados");
    }

    /**
     * Guarda el nombre del cliente.
     *
     * @param nombre
     * @param primerApellido
     * @param segundoApellido
     * @param telefono
     * @param marca
     * @param modelo
     * @param matricula
     */
    //SNNN
    @Override
    public void guardaNombreModificado(String nombre, String primerApellido, String segundoApellido,
                                       String telefono, String marca,
                                       String modelo, String matricula) {
        String nombreApellidos = nombre + " " + primerApellido + " " + segundoApellido;

        SQLite.update(DBClientes.class)
                .set(DBClientes_Table.nombreCliente.eq(nombre),
                        DBClientes_Table.nombreApellidos.eq(nombreApellidos))
                .where(DBClientes_Table.primerApellidoCliente.is(primerApellido))
                .and(DBClientes_Table.segundoApellidoCliente.is(segundoApellido))
                .and(DBClientes_Table.telefonoCliente.is(telefono))
                .and(DBClientes_Table.marcaVehiculo.is(marca))
                .and(DBClientes_Table.modeloVehiculo.is(modelo))
                .and(DBClientes_Table.matriculaVehiculo.is(matricula))
                .async()
                .execute();

        //  this.mView.showMessage("Nombre modificado");
    }

    /**
     * Guarda el primer apellido, el segundo y el teléfono.
     *
     * @param nombre
     * @param primerApellido
     * @param segundoApellido
     * @param telefono
     * @param marca
     * @param modelo
     * @param matricula
     */
    //NSSS
    @Override
    public void guardaPrimeroSegundoTelefono(String nombre, String primerApellido, String segundoApellido,
                                             String telefono, String marca, String modelo, String matricula) {
        String nombreApellidos = nombre + " " + primerApellido + " " + segundoApellido;

        SQLite.update(DBClientes.class)
                .set(DBClientes_Table.primerApellidoCliente.eq(primerApellido),
                        DBClientes_Table.segundoApellidoCliente.eq(segundoApellido),
                        DBClientes_Table.nombreApellidos.eq(nombreApellidos),
                        DBClientes_Table.telefonoCliente.eq(telefono))
                .where(DBClientes_Table.nombreCliente.is(nombre))
                .and(DBClientes_Table.marcaVehiculo.is(marca))
                .and(DBClientes_Table.modeloVehiculo.is(modelo))
                .and(DBClientes_Table.matriculaVehiculo.is(matricula))
                .async()
                .execute();

        //   this.mView.showMessage("Primer, segundo apellido y teléfono modificados");
    }

    /**
     * Guarda el primer y el segundo apellido del cliente.
     *
     * @param nombre
     * @param primerApellido
     * @param segundoApellido
     * @param telefono
     * @param marca
     * @param modelo
     * @param matricula
     */
    //NSSN
    @Override
    public void guardaPrimeroSegundo(String nombre, String primerApellido, String segundoApellido,
                                     String telefono, String marca, String modelo, String matricula) {
        String nombreApellidos = nombre + " " + primerApellido + " " + segundoApellido;

        SQLite.update(DBClientes.class)
                .set(DBClientes_Table.primerApellidoCliente.eq(primerApellido),
                        DBClientes_Table.segundoApellidoCliente.eq(segundoApellido),
                        DBClientes_Table.nombreApellidos.eq(nombreApellidos))
                .where(DBClientes_Table.nombreCliente.is(nombre))
                .and(DBClientes_Table.telefonoCliente.is(telefono))
                .and(DBClientes_Table.marcaVehiculo.is(marca))
                .and(DBClientes_Table.modeloVehiculo.is(modelo))
                .and(DBClientes_Table.matriculaVehiculo.is(matricula))
                .async()
                .execute();

        //   this.mView.showMessage("Primer y segundo apellido modificados");
    }

    /**
     * Guarda el primer apellido y el teléfono del cliente.
     *
     * @param nombre
     * @param primerApellido
     * @param segundoApellido
     * @param telefono
     * @param marca
     * @param modelo
     * @param matricula
     */
    //NSNS
    @Override
    public void guardaPrimeroTelefono(String nombre, String primerApellido, String segundoApellido,
                                      String telefono, String marca, String modelo, String matricula) {
        String nombreApellidos = nombre + " " + primerApellido + " " + segundoApellido;

        SQLite.update(DBClientes.class)
                .set(DBClientes_Table.primerApellidoCliente.eq(primerApellido),
                        DBClientes_Table.telefonoCliente.eq(telefono),
                        DBClientes_Table.nombreApellidos.eq(nombreApellidos))
                .where(DBClientes_Table.nombreCliente.is(nombre))
                .and(DBClientes_Table.segundoApellidoCliente.eq(segundoApellido))
                .and(DBClientes_Table.marcaVehiculo.is(marca))
                .and(DBClientes_Table.modeloVehiculo.is(modelo))
                .and(DBClientes_Table.matriculaVehiculo.is(matricula))
                .async()
                .execute();

        //  this.mView.showMessage("Primer apellido y teléfono modificados");
    }

    /**
     * Guarda el primer apellido del cliente.
     *
     * @param nombre
     * @param primerApellido
     * @param segundoApellido
     * @param telefono
     * @param marca
     * @param modelo
     * @param matricula
     */
    //NSNN
    @Override
    public void guardaPrimero(String nombre, String primerApellido, String segundoApellido,
                              String telefono, String marca, String modelo, String matricula) {
        String nombreApellidos = nombre + " " + primerApellido + " " + segundoApellido;

        SQLite.update(DBClientes.class)
                .set(DBClientes_Table.primerApellidoCliente.eq(primerApellido),
                        DBClientes_Table.nombreApellidos.eq(nombreApellidos))
                .where(DBClientes_Table.nombreCliente.is(nombre))
                .and(DBClientes_Table.segundoApellidoCliente.is(segundoApellido))
                .and(DBClientes_Table.telefonoCliente.is(telefono))
                .and(DBClientes_Table.marcaVehiculo.is(marca))
                .and(DBClientes_Table.modeloVehiculo.is(modelo))
                .and(DBClientes_Table.matriculaVehiculo.is(matricula))
                .async()
                .execute();

        //  this.mView.showMessage("Primer apellido modificado");
    }

    /**
     * Guarda el segundo apellido y el teléfono del cliente.
     *
     * @param nombre
     * @param primerApellido
     * @param segundoApellido
     * @param telefono
     * @param marca
     * @param modelo
     * @param matricula
     */
    //NNSS
    @Override
    public void guardaSegundoTelefono(String nombre, String primerApellido, String segundoApellido,
                                      String telefono, String marca, String modelo, String matricula) {
        String nombreApellidos = nombre + " " + primerApellido + " " + segundoApellido;

        SQLite.update(DBClientes.class)
                .set(DBClientes_Table.segundoApellidoCliente.eq(segundoApellido),
                        DBClientes_Table.nombreApellidos.eq(nombreApellidos),
                        DBClientes_Table.telefonoCliente.eq(telefono))
                .where(DBClientes_Table.nombreCliente.is(nombre))
                .and(DBClientes_Table.primerApellidoCliente.is(primerApellido))
                .and(DBClientes_Table.marcaVehiculo.is(marca))
                .and(DBClientes_Table.modeloVehiculo.is(modelo))
                .and(DBClientes_Table.matriculaVehiculo.is(matricula))
                .async()
                .execute();

        //   this.mView.showMessage("Segundo apellido y teléfono modificados");
    }

    /**
     * Guarda el segundo apellido del cliente.
     *
     * @param nombre
     * @param primerApellido
     * @param segundoApellido
     * @param telefono
     * @param marca
     * @param modelo
     * @param matricula
     */
    //NNSN
    @Override
    public void guardaSegundo(String nombre, String primerApellido, String segundoApellido,
                              String telefono, String marca, String modelo, String matricula) {
        String nombreApellidos = nombre + " " + primerApellido + " " + segundoApellido;

        SQLite.update(DBClientes.class)
                .set(DBClientes_Table.segundoApellidoCliente.eq(segundoApellido),
                        DBClientes_Table.nombreApellidos.eq(nombreApellidos))
                .where(DBClientes_Table.nombreCliente.is(nombre))
                .and(DBClientes_Table.primerApellidoCliente.is(primerApellido))
                .and(DBClientes_Table.telefonoCliente.is(telefono))
                .and(DBClientes_Table.marcaVehiculo.is(marca))
                .and(DBClientes_Table.modeloVehiculo.is(modelo))
                .and(DBClientes_Table.matriculaVehiculo.is(matricula))
                .async()
                .execute();

        //  this.mView.showMessage("Segundo apellido modificados");
    }

    /**
     * Guarda el teléfono del cliente.
     *
     * @param nombre
     * @param primerApellido
     * @param segundoApellido
     * @param telefono
     * @param marca
     * @param modelo
     * @param matricula
     */
    //NNNS
    @Override
    public void guardaTelefono(String nombre, String primerApellido, String segundoApellido,
                               String telefono, String marca, String modelo, String matricula) {
        String nombreApellidos = nombre + " " + primerApellido + " " + segundoApellido;

        SQLite.update(DBClientes.class)
                .set(DBClientes_Table.telefonoCliente.eq(telefono))
                .where(DBClientes_Table.nombreCliente.is(nombre))
                .and(DBClientes_Table.primerApellidoCliente.is(primerApellido))
                .and(DBClientes_Table.segundoApellidoCliente.is(segundoApellido))
                .and(DBClientes_Table.nombreApellidos.is(nombreApellidos))
                .and(DBClientes_Table.marcaVehiculo.is(marca))
                .and(DBClientes_Table.modeloVehiculo.is(modelo))
                .and(DBClientes_Table.matriculaVehiculo.is(matricula))
                .async()
                .execute();

//        this.mView.showMessage("Teléfono modificado");
    }

    /**
     * Búsqueda de clientes mediante una palabra clave
     *
     * @param search contiene la búsqueda del cliente
     * @return Lista de clientes que coincidan con el parámetro
     */
    @Override
    public List<ConsultaClientes> getClientes(String search) {
        this.mBoolConBusqueda = true;
        this.mBoolSinBusqueda = false;
        this.mListClientesBusqueda.clear();
        this.mListNombresPosBusqueda.clear();
        this.mListAuxClientesBusqueda.clear();
        List<DBClientes> clientes = DBClientes.getAllClientes();
        String search_aux = this.trataBusqueda(search);
        if (clientes.size() > 0) {
            for (DBClientes entries : clientes) {
                if (entries.getNombreApellidos().contains(search_aux)) {
                    this.mListClientesBusqueda.add(new ConsultaClientes(
                            entries.getNombreCliente(),
                            entries.getPrimerApellidoCliente(),
                            entries.getSegundoApellidoCliente(),
                            entries.getNombreApellidos(),
                            entries.getTelefonoCliente(),
                            entries.getMarcaVehiculo(),
                            entries.getModeloVehiculo(),
                            entries.getMatriculaVehiculo()
                    ));
                }
            }
            this.copiaNombres();
            MergeSort merge = new MergeSort(this.mListNombresPosBusqueda);
            merge.sort();
            this.ordenaListaInicial();
            this.preparaListas();
        }
        return this.mListClientesBusqueda;
    }

    /**
     * Búsqueda de todos los clientes
     *
     * @return Lista de todos los clientes del sistema
     */
    @Override
    public List<ConsultaClientes> getAllClientes() {
        this.mBoolSinBusqueda = true;
        this.mBoolConBusqueda = false;
        this.mListClientesSinBusqueda.clear();
        this.mListNombresPosSinBusqueda.clear();
        this.mListAuxClientesSinBusqueda.clear();
        List<DBClientes> clientes = DBClientes.getAllClientes();
        if (clientes.size() > 0) {
            for (DBClientes entries : clientes) {
                this.mListClientesSinBusqueda.add(new ConsultaClientes(
                        entries.getNombreCliente(),
                        entries.getPrimerApellidoCliente(),
                        entries.getSegundoApellidoCliente(),
                        entries.getNombreApellidos(),
                        entries.getTelefonoCliente(),
                        entries.getMarcaVehiculo(),
                        entries.getModeloVehiculo(),
                        entries.getMatriculaVehiculo()
                ));
            }
            this.copiaNombres();
            MergeSort merge = new MergeSort(this.mListNombresPosSinBusqueda);
            merge.sort();
            this.ordenaListaInicial();
            this.preparaListas();
        }
        return this.mListClientesSinBusqueda;
    }

    /**
     * Copiaremos los nombres de la lista de clientes junto con la posición que ocupen
     */
    private void copiaNombres() {
        if (this.mBoolConBusqueda) {
            for (int i = 0; i < this.mListClientesBusqueda.size(); ++i) {
                this.mListNombresPosBusqueda.add(new NombrePos(
                        this.mListClientesBusqueda.get(i).getmNombre(),
                        i
                ));
            }
        } else if (this.mBoolSinBusqueda) {
            for (int i = 0; i < this.mListClientesSinBusqueda.size(); ++i) {
                this.mListNombresPosSinBusqueda.add(new NombrePos(
                        this.mListClientesSinBusqueda.get(i).getmNombre(),
                        i
                ));
            }
        }
    }

    /**
     * Ordena la lista inicial alfabéticamente por nombre.
     */
    private void ordenaListaInicial() {
        if (this.mBoolConBusqueda) {
            ConsultaClientes cliente;
            int pos;
            for (int i = 0; i < this.mListNombresPosBusqueda.size(); ++i) {
                pos = this.mListNombresPosBusqueda.get(i).getmPosicion();
                cliente = new ConsultaClientes(
                        this.mListClientesBusqueda.get(pos).getmNombre(),
                        this.mListClientesBusqueda.get(pos).getmPrimerApellido(),
                        this.mListClientesBusqueda.get(pos).getmSegundoApellido(),
                        this.mListClientesBusqueda.get(pos).getmNombreApellidos(),
                        this.mListClientesBusqueda.get(pos).getmTelefono(),
                        this.mListClientesBusqueda.get(pos).getmMarcaVehiculo(),
                        this.mListClientesBusqueda.get(pos).getmModeloVehiculo(),
                        this.mListClientesBusqueda.get(pos).getmMatriculaVehiculo()
                );
                this.mListAuxClientesBusqueda.add(cliente);
            }
        } else if (this.mBoolSinBusqueda) {
            ConsultaClientes cliente;
            int pos;
            for (int i = 0; i < this.mListNombresPosSinBusqueda.size(); ++i) {
                pos = this.mListNombresPosSinBusqueda.get(i).getmPosicion();
                cliente = new ConsultaClientes(
                        this.mListClientesSinBusqueda.get(pos).getmNombre(),
                        this.mListClientesSinBusqueda.get(pos).getmPrimerApellido(),
                        this.mListClientesSinBusqueda.get(pos).getmSegundoApellido(),
                        this.mListClientesSinBusqueda.get(pos).getmNombreApellidos(),
                        this.mListClientesSinBusqueda.get(pos).getmTelefono(),
                        this.mListClientesSinBusqueda.get(pos).getmMarcaVehiculo(),
                        this.mListClientesSinBusqueda.get(pos).getmModeloVehiculo(),
                        this.mListClientesSinBusqueda.get(pos).getmMatriculaVehiculo()
                );
                this.mListAuxClientesSinBusqueda.add(cliente);
            }
        }
    }

    /**
     * Mira que no hayan registros repetidos.
     */
    private void preparaListas() {
        if (this.mBoolSinBusqueda) {
            List<ConsultaClientes> clientes = new ArrayList<>();
            ConsultaClientes clienteAnterior, clienteActual;
            clienteAnterior = this.mListAuxClientesSinBusqueda.get(0);
            clientes.add(clienteAnterior);
            if (this.mListAuxClientesSinBusqueda.size() > 1) {
                for (int i = 0; i < this.mListAuxClientesSinBusqueda.size(); ++i) {
                    clienteActual = this.mListAuxClientesSinBusqueda.get(i);
                    if (!clienteAnterior.getmNombre().equals(clienteActual.getmNombre()) ||
                            !clienteAnterior.getmPrimerApellido().equals(clienteActual.getmPrimerApellido()) ||
                            !clienteAnterior.getmSegundoApellido().equals(clienteActual.getmSegundoApellido()) ||
                            !clienteAnterior.getmTelefono().equals(clienteActual.getmTelefono())) {
                        clientes.add(clienteActual);
                    }
                    clienteAnterior = clienteActual;
                }
            }
            this.mListClientesSinBusqueda = clientes;
        } else if (this.mBoolConBusqueda) {
            List<ConsultaClientes> clientes = new ArrayList<>();
            ConsultaClientes clienteAnterior, clienteActual;
            clienteAnterior = this.mListAuxClientesBusqueda.get(0);
            clientes.add(clienteAnterior);
            if (this.mListAuxClientesBusqueda.size() > 1) {
                for (int i = 0; i < this.mListAuxClientesBusqueda.size(); ++i) {
                    clienteActual = this.mListAuxClientesBusqueda.get(i);
                    if (!clienteAnterior.getmNombre().equals(clienteActual.getmNombre()) ||
                            !clienteAnterior.getmPrimerApellido().equals(clienteActual.getmPrimerApellido()) ||
                            !clienteAnterior.getmSegundoApellido().equals(clienteActual.getmSegundoApellido()) ||
                            !clienteAnterior.getmTelefono().equals(clienteActual.getmTelefono())) {
                        clientes.add(clienteActual);
                    }
                    clienteAnterior = clienteActual;
                }
            }
            this.mListClientesBusqueda = clientes;
        }
    }

    /**
     * Trata el parámetro por si hubiesen espacios entre palabra y palabra
     *
     * @param search búsqueda de cliente
     * @return un string tratado con espacios
     */
    private String trataBusqueda(String search) {
        String aux = search.substring(0, 1).toUpperCase();
        for (int i = 1; i < search.length(); ++i) {
            if (search.charAt(i - 1) == ' ') {
                aux += search.substring(i, i + 1).toUpperCase();
            } else {
                aux += search.charAt(i);
            }
        }
        return aux;
    }

    @Override
    public void attach(Context context, ModificaClienteContract.View view) {
        this.mContext = context;
        this.mView = view;
        this.mListClientesBusqueda = new ArrayList<>();
        this.mListClientesSinBusqueda = new ArrayList<>();
        this.mListAuxClientesBusqueda = new ArrayList<>();
        this.mListAuxClientesSinBusqueda = new ArrayList<>();
        this.mListNombresPosBusqueda = new ArrayList<>();
        this.mListNombresPosSinBusqueda = new ArrayList<>();
        this.mBoolConBusqueda = false;
        this.mBoolSinBusqueda = false;
    }
}
