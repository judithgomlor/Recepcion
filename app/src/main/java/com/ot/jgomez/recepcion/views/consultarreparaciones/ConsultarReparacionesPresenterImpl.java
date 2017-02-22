package com.ot.jgomez.recepcion.views.consultarreparaciones;

import android.content.Context;

import com.ot.jgomez.recepcion.database.DBRegistroEntradas;
import com.ot.jgomez.recepcion.items.ConsultaReparacionesPendientes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jgomez on 22/02/17.
 */

public class ConsultarReparacionesPresenterImpl implements ConsultarReparacionesContract.Presenter {

    private Context mContext;
    private ConsultarReparacionesContract.View mView;


    @Override
    public List<ConsultaReparacionesPendientes> getReparacionesPendientes(String fecha, boolean todas,
                                                                          boolean a_partir) {
        List<ConsultaReparacionesPendientes> list = new ArrayList<>();
        List<DBRegistroEntradas> entradas = DBRegistroEntradas.getAllEntradas();
        if (entradas.size() > 0) {
            if (todas) {
                //reparaciones pendientes en total
                for (DBRegistroEntradas entries : entradas) {
                    if (entries.getFechaSalida().equals("")) {
                        list.add(new ConsultaReparacionesPendientes(
                                entries.getNombre(),
                                entries.getPrimerApellido(),
                                entries.getSegundoApellido(),
                                entries.getMarcaVehiculo(),
                                entries.getModeloVehículo(),
                                entries.getMatriculaVehiculo(),
                                entries.getFechaEntrada(),
                                entries.getResumenEntrada(),
                                entries.getDescripcionEntrada(),
                                entries.getResolucionEntrada(),
                                entries.getFechaSalida(),
                                entries.getCosteReparacion()
                        ));
                    }
                }
            } else {
                //reparaciones pendientes a partir de una fecha en concreto
                if (a_partir) {
                    for (DBRegistroEntradas entries : entradas) {
                        if (entries.getFechaSalida().equals("") &&
                                (entries.getFechaEntrada().equals(fecha) ||
                                        ((Integer.parseInt(entries.getFechaEntrada().substring(0, 2)) >
                                                Integer.parseInt(fecha.substring(0, 2))) &&
                                                (Integer.parseInt(entries.getFechaEntrada().substring(3, 5)) >=
                                                        Integer.parseInt(fecha.substring(3, 5))) &&
                                                (Integer.parseInt(entries.getFechaEntrada().substring(6, 10)) >=
                                                        Integer.parseInt(fecha.substring(6, 10)))))) {
                            list.add(new ConsultaReparacionesPendientes(
                                    entries.getNombre(),
                                    entries.getPrimerApellido(),
                                    entries.getSegundoApellido(),
                                    entries.getMarcaVehiculo(),
                                    entries.getModeloVehículo(),
                                    entries.getMatriculaVehiculo(),
                                    entries.getFechaEntrada(),
                                    entries.getResumenEntrada(),
                                    entries.getDescripcionEntrada(),
                                    entries.getResolucionEntrada(),
                                    entries.getFechaSalida(),
                                    entries.getCosteReparacion()
                            ));
                        }
                    }
                } else {
                    //día concreto
                    for (DBRegistroEntradas entries : entradas) {
                        if (entries.getFechaSalida().equals("") &&
                                (entries.getFechaEntrada().equals(fecha))) {
                            list.add(new ConsultaReparacionesPendientes(
                                    entries.getNombre(),
                                    entries.getPrimerApellido(),
                                    entries.getSegundoApellido(),
                                    entries.getMarcaVehiculo(),
                                    entries.getModeloVehículo(),
                                    entries.getMatriculaVehiculo(),
                                    entries.getFechaEntrada(),
                                    entries.getResumenEntrada(),
                                    entries.getDescripcionEntrada(),
                                    entries.getResolucionEntrada(),
                                    entries.getFechaSalida(),
                                    entries.getCosteReparacion()
                            ));
                        }
                    }
                }
            }
        }
        return list;
    }

    //Devolverá una lista con todas las reparaciones resueltas
    @Override
    public List<ConsultaReparacionesPendientes> getReparacionesResueltas(String fecha, boolean todas,
                                                                         boolean a_partir) {
        List<ConsultaReparacionesPendientes> list = new ArrayList<>();
        List<DBRegistroEntradas> entradas = DBRegistroEntradas.getAllEntradas();
        if (entradas.size() > 0) {
            if (todas) {
                //reparaciones resueltas en total
                for (DBRegistroEntradas entries : entradas) {
                    if (!entries.getFechaSalida().equals("")) {
                        list.add(new ConsultaReparacionesPendientes(
                                entries.getNombre(),
                                entries.getPrimerApellido(),
                                entries.getSegundoApellido(),
                                entries.getMarcaVehiculo(),
                                entries.getModeloVehículo(),
                                entries.getMatriculaVehiculo(),
                                entries.getFechaEntrada(),
                                entries.getResumenEntrada(),
                                entries.getDescripcionEntrada(),
                                entries.getResolucionEntrada(),
                                entries.getFechaSalida(),
                                entries.getCosteReparacion()
                        ));
                    }
                }
            } else {
                //reparaciones resueltas a partir de una fecha en concreto
                if (a_partir) {
                    for (DBRegistroEntradas entries : entradas) {
                        if (!entries.getFechaSalida().equals("") &&
                                (entries.getFechaSalida().equals(fecha) ||
                                        ((Integer.parseInt(entries.getFechaSalida().substring(0, 2)) >
                                                Integer.parseInt(fecha.substring(0, 2))) &&
                                                (Integer.parseInt(entries.getFechaSalida().substring(3, 5)) >=
                                                        Integer.parseInt(fecha.substring(3, 5))) &&
                                                (Integer.parseInt(entries.getFechaSalida().substring(6, 10)) >=
                                                        Integer.parseInt(fecha.substring(6, 10)))))) {
                            list.add(new ConsultaReparacionesPendientes(
                                    entries.getNombre(),
                                    entries.getPrimerApellido(),
                                    entries.getSegundoApellido(),
                                    entries.getMarcaVehiculo(),
                                    entries.getModeloVehículo(),
                                    entries.getMatriculaVehiculo(),
                                    entries.getFechaEntrada(),
                                    entries.getResumenEntrada(),
                                    entries.getDescripcionEntrada(),
                                    entries.getResolucionEntrada(),
                                    entries.getFechaSalida(),
                                    entries.getCosteReparacion()
                            ));
                        }
                    }
                } else {
                    //día concreto
                    for (DBRegistroEntradas entries : entradas) {
                        if (!entries.getFechaSalida().equals("") &&
                                (entries.getFechaSalida().equals(fecha))) {
                            list.add(new ConsultaReparacionesPendientes(
                                    entries.getNombre(),
                                    entries.getPrimerApellido(),
                                    entries.getSegundoApellido(),
                                    entries.getMarcaVehiculo(),
                                    entries.getModeloVehículo(),
                                    entries.getMatriculaVehiculo(),
                                    entries.getFechaEntrada(),
                                    entries.getResumenEntrada(),
                                    entries.getDescripcionEntrada(),
                                    entries.getResolucionEntrada(),
                                    entries.getFechaSalida(),
                                    entries.getCosteReparacion()
                            ));
                        }
                    }
                }
            }
        }
        return list;
    }

    @Override
    public void attach(Context context, ConsultarReparacionesContract.View view) {
        this.mContext = context;
        this.mView = view;
    }
}
