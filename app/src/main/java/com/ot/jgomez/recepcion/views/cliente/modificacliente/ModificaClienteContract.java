package com.ot.jgomez.recepcion.views.cliente.modificacliente;

import android.content.Context;

import com.ot.jgomez.recepcion.items.ConsultaClientes;
import com.ot.jgomez.recepcion.views.BasePresenter;
import com.ot.jgomez.recepcion.views.BaseView;

import java.util.List;

/**
 * Created by jgomez on 22/02/17.
 */

public interface ModificaClienteContract {
    interface View extends BaseView<Presenter> {
        void fillData(List<ConsultaClientes> list);
    }

    interface Presenter extends BasePresenter<Context, View> {
        List<ConsultaClientes> getClientes(String search);

        List<ConsultaClientes> getAllClientes();

        List<ConsultaClientes> getClientesSinTratarLista();

        void guardaVehiculoNuevo(String nombre, String primerApellido, String segundoApellido,
                                 String nombreApellidos, String telefono,
                                 String marca, String modelo, String matricula);

        void eliminaVehiculo(String nombre, String primerApellido, String segundoApellido,
                             String nombreApellidos, String telefono,
                             String marca, String modelo, String matricula);

        void modificaMarcaModeloMatricula(String nombre, String primerApellido, String segundoApellido,
                                          String nombreApellidos, String telefono, String marcaAntigua,
                                          String modeloAntiguo, String matriculaAntigua, String marcaNueva,
                                          String modeloNuevo, String matriculaNueva);

        void modificaMarcaModelo(String nombre, String primerApellido, String segundoApellido,
                                 String nombreApellidos, String telefono, String marcaAntigua,
                                 String modeloAntiguo, String matriculaAntigua, String marcaNueva,
                                 String modeloNuevo);

        void modificaMarcaMatricula(String nombre, String primerApellido, String segundoApellido,
                                    String nombreApellidos, String telefono, String marcaAntigua,
                                    String modeloAntiguo, String matriculaAntigua, String marcaNueva,
                                    String matriculaNueva);

        void modificaMarca(String nombre, String primerApellido, String segundoApellido,
                           String nombreApellidos, String telefono, String marcaAntigua,
                           String modeloAntiguo, String matriculaAntigua, String marcaNueva);

        void modificaModeloMatricula(String nombre, String primerApellido, String segundoApellido,
                                     String nombreApellidos, String telefono, String marcaAntigua,
                                     String modeloAntiguo, String matriculaAntigua,
                                     String modeloNuevo, String matriculaNueva);

        void modificaModelo(String nombre, String primerApellido, String segundoApellido,
                            String nombreApellidos, String telefono, String marcaAntigua,
                            String modeloAntiguo, String matriculaAntigua,
                            String modeloNuevo);

        void modificaMatricula(String nombre, String primerApellido, String segundoApellido,
                               String nombreApellidos, String telefono, String marcaAntigua,
                               String modeloAntiguo, String matriculaAntigua, String matriculaNueva);

        void guardaNombrePrimerSegundoTelefono(String nombre, String primerApellido,
                                               String segundoApellido, String telefono, String marca,
                                               String modelo, String matricula); //SSSS

        void guardaNombrePrimerSegundoApellido(String nombre, String primerApellido,
                                               String segundoApellido, String telefono, String marca,
                                               String modelo, String matricula); //SSSN

        void guardaNombrePrimerTelefono(String nombre, String primerApellido,
                                        String segundoApellido, String telefono, String marca,
                                        String modelo, String matricula); //SSNS

        void guardaNombrePrimerApellidoModificados(String nombre, String primerApellido,
                                                   String segundoApellido, String telefono,
                                                   String marca, String modelo, String matricula); //SSNN

        void guardaNombreSegundoTelefono(String nombre, String primerApellido,
                                         String segundoApellido, String telefono,
                                         String marca, String modelo, String matricula); //SNSS

        void guardaNombreSegundo(String nombre, String primerApellido,
                                 String segundoApellido, String telefono,
                                 String marca, String modelo, String matricula); //SNSN

        void guardaNombreTelefono(String nombre, String primerApellido,
                                  String segundoApellido, String telefono,
                                  String marca, String modelo, String matricula); //SNNS

        void guardaNombreModificado(String nombre, String primerApellido, String segundoApellido,
                                    String telefono, String marca,
                                    String modelo, String matricula); //SNNN

        void guardaPrimeroSegundoTelefono(String nombre, String primerApellido,
                                          String segundoApellido, String telefono,
                                          String marca, String modelo, String matricula); //NSSS

        void guardaPrimeroSegundo(String nombre, String primerApellido,
                                  String segundoApellido, String telefono,
                                  String marca, String modelo, String matricula); //NSSN

        void guardaPrimeroTelefono(String nombre, String primerApellido,
                                   String segundoApellido, String telefono,
                                   String marca, String modelo, String matricula); //NSNS

        void guardaPrimero(String nombre, String primerApellido,
                           String segundoApellido, String telefono,
                           String marca, String modelo, String matricula); //NSNN

        void guardaSegundoTelefono(String nombre, String primerApellido,
                                   String segundoApellido, String telefono,
                                   String marca, String modelo, String matricula); //NNSS

        void guardaSegundo(String nombre, String primerApellido,
                           String segundoApellido, String telefono,
                           String marca, String modelo, String matricula); //NNSN

        void guardaTelefono(String nombre, String primerApellido,
                            String segundoApellido, String telefono,
                            String marca, String modelo, String matricula); //NNNS

    }
}
