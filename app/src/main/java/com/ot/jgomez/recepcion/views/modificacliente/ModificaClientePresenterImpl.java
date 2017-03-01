package com.ot.jgomez.recepcion.views.modificacliente;

import android.content.Context;
import android.util.Log;

import com.ot.jgomez.recepcion.control.MergeSort;
import com.ot.jgomez.recepcion.database.DBClientes;
import com.ot.jgomez.recepcion.items.ConsultaClientes;
import com.ot.jgomez.recepcion.items.NombrePos;

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
