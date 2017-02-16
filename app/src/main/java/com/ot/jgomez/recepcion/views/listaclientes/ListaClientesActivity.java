package com.ot.jgomez.recepcion.views.listaclientes;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.ot.jgomez.recepcion.R;
import com.ot.jgomez.recepcion.adapters.ConsultaClientesRvAdapter;
import com.ot.jgomez.recepcion.control.ComparatorStrings;
import com.ot.jgomez.recepcion.database.DBClientes;
import com.ot.jgomez.recepcion.items.ConsultaClientes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListaClientesActivity extends AppCompatActivity implements ListaClientesContract.View {

    private List<ConsultaClientes> mListClientes;
    private RecyclerView mRecyclerView;
    private ConsultaClientesRvAdapter mAdapter;
    private ListaClientesPresenterImpl mPresenter;
    private List<String> mListNombres;
    private List<String> mListPrimerosApellidos;
    private List<String> mListSegundosApellidos;
    private List<String> mListTelefonos;

    //listas auxiliares
    private List<ConsultaClientes> mListAuxClientes;
    private List<String> mListAuxNombres;
    private List<String> mListAuxPrimerosApellidos;
    private List<String> mListAuxSegundosApellidos;
    private List<String> mListAuxTelefonos;

    //listas secundarias
    private List<ConsultaClientes> mListAuxClientes_v2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_clientes);

        ActionBar myBar = getSupportActionBar();
        myBar.setHomeButtonEnabled(true);
        myBar.setDisplayShowHomeEnabled(true);
        myBar.setDisplayHomeAsUpEnabled(true);
        myBar.setTitle(R.string.lista_clientes);

        if (this.mPresenter == null) {
            this.mPresenter = new ListaClientesPresenterImpl();
        }
        this.mPresenter.attach(this, this);

        this.mListClientes = new ArrayList<>();
        this.mListNombres = new ArrayList<>();
        this.mListPrimerosApellidos = new ArrayList<>();
        this.mListSegundosApellidos = new ArrayList<>();
        this.mListTelefonos = new ArrayList<>();
        this.mListAuxClientes = new ArrayList<>();
        this.mListAuxNombres = new ArrayList<>();
        this.mListAuxPrimerosApellidos = new ArrayList<>();
        this.mListAuxSegundosApellidos = new ArrayList<>();
        this.mListAuxTelefonos = new ArrayList<>();
        this.mListAuxClientes_v2 = new ArrayList<>();

        this.mListClientes = this.mPresenter.getClientes();

        if (this.mListClientes.size() > 0) {
            this.preparaListas();
        }

        this.mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_lista_clientes);
        this.mAdapter = new ConsultaClientesRvAdapter(this.mListClientes, this);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.mRecyclerView.setAdapter(this.mAdapter);

    }

    /*
    Recorre las listas para controlar que no hayan clientes repetidos
     */
    private void preparaListas() {
        /*
        //Primero hay que mirar los nombres repetidos
        for(int i = 0; i < this.mListClientes.size(); ++i) {
            this.mListNombres.add(this.mListClientes.get(i).getmNombre());
        }

        //ordenamos la lista por orden alfabético
        ComparatorStrings compare = new ComparatorStrings();
        Collections.sort(this.mListNombres, compare);

        //miramos que no existan repetidos
        String nombreAnterior, nombreActual;
        nombreAnterior = this.mListNombres.get(0);
        this.mListAuxNombres.add(nombreAnterior);
        if(this.mListNombres.size() > 1) {
            for(int i = 1; i < this.mListNombres.size(); ++i) {
                nombreActual = this.mListNombres.get(i);
                if(!nombreAnterior.equals(nombreActual)) {
                    this.mListAuxNombres.add(nombreActual);
                }
                nombreAnterior = nombreActual;
            }
        }
*/

        /*
        Log.d("ListaClientes", "this.mListClientes.size() = " + this.mListClientes.size());
        ConsultaClientes clienteAnterior, clienteActual;
        this.mListAuxClientes.add(this.mListClientes.get(0));
        if (this.mListClientes.size() > 1) {
            for (int i = 0; i < this.mListClientes.size(); ++i) {
                clienteAnterior = this.mListClientes.get(i);
                Log.d("ListaClientes", "clienteAnterior = " + clienteAnterior.getmNombre() + " " +
                        clienteAnterior.getmPrimerApellido() + " " + clienteAnterior.getmSegundoApellido() +
                        " " + clienteAnterior.getmTelefono());
                for (int j = 0; j < this.mListClientes.size(); ++j) {
                    clienteActual = this.mListAuxClientes.get(j);
                    Log.d("ListaClientes", "clienteActual = " + clienteActual.getmNombre() + " " +
                            clienteActual.getmPrimerApellido() + " " + clienteActual.getmSegundoApellido() +
                            " " + clienteActual.getmTelefono());
                    Log.d("ListaClientes", "entonces estamos comparando:" + clienteAnterior.getmNombre() + " y " +
                            clienteActual.getmNombre());
                    if (!clienteAnterior.getmNombre().equals(clienteActual.getmNombre()) ||
                            !clienteAnterior.getmPrimerApellido().equals(clienteActual.getmPrimerApellido()) ||
                            !clienteAnterior.getmSegundoApellido().equals(clienteActual.getmSegundoApellido()) ||
                            !clienteAnterior.getmTelefono().equals(clienteActual.getmTelefono())) {
                        this.mListAuxClientes.add(clienteActual);
                        Log.d("ListaClientes", "he añadido el clienteActual");
                    }
                }
            }
        }
        Log.d("ListaClientes", "this.mListAuxClientes.size() = " + this.mListAuxClientes.size());
        */
/*
        for(int i = 0; i < this.mListClientes.size(); ++i) {
            this.mListNombres.add(this.mListClientes.get(i).getmNombre());
            this.mListPrimerosApellidos.add(this.mListClientes.get(i).getmPrimerApellido());
            this.mListSegundosApellidos.add(this.mListClientes.get(i).getmSegundoApellido());
            this.mListTelefonos.add(this.mListClientes.get(i).getmTelefono());
        }

        Log.d("ListaClientes", "this.mListNombres = " +this.mListNombres);
        Log.d("ListaClientes", "this.mListPrimerosApellidos = " +this.mListPrimerosApellidos);
        Log.d("ListaClientes", "this.mListSegundosApellidos = " +this.mListSegundosApellidos);
        Log.d("ListaClientes", "this.mListTelefonos = " +this.mListTelefonos);

        //ordenamos la lista de nombres para ver si hay repetidos
        ComparatorStrings compare = new ComparatorStrings();
        Collections.sort(this.mListNombres, compare);
        Log.d("ListaClientes", "Después de ordenar --> this.mListNombres = " +this.mListNombres);

        //ordenamos la lista de clientes a ver qué ocurre
        String nombreAnterior, nombreActual;
        nombreAnterior = this.mListNombres.get(0);
        this.mListAuxNombres.add(nombreAnterior);
        if(this.mListNombres.size() > 1) {
            for(int i = 0; i < this.mListNombres.size(); ++i) {
                nombreActual = this.mListNombres.get(i);
                if(!nombreAnterior.equals(nombreActual)) {
                    this.mListAuxNombres.add(nombreActual);
                }
                nombreAnterior = nombreActual;
            }
        }

        Log.d("ListaClientes", "this.mListAuxNombres = " +this.mListAuxNombres);

        */ /*
        ConsultaClientes clienteAnterior, clienteActual, clienteAux;
        this.mListAuxClientes.add(this.mListClientes.get(0));
        if (this.mListClientes.size() > 1) {
            for (int i = 0; i < this.mListClientes.size(); ++i) {
                clienteAnterior = this.mListClientes.get(i);
                Log.d("ListaCliente", "clienteAnterior = " + clienteAnterior.getmNombre() + " "
                        + clienteAnterior.getmPrimerApellido() + " " + clienteAnterior.getmSegundoApellido() +
                        " " + clienteAnterior.getmTelefono());
                for (int j = 0; j < this.mListClientes.size(); ++j) {
                    clienteActual = this.mListClientes.get(j);
                    Log.d("ListaCliente", "clienteActual = " + clienteActual.getmNombre() + " "
                            + clienteActual.getmPrimerApellido() + " " + clienteActual.getmSegundoApellido() +
                            " " + clienteActual.getmTelefono());
                    if (!clienteAnterior.getmNombre().equals(clienteActual.getmNombre()) ||
                            !clienteAnterior.getmPrimerApellido().equals(clienteActual.getmPrimerApellido()) ||
                            !clienteAnterior.getmSegundoApellido().equals(clienteActual.getmSegundoApellido()) ||
                            !clienteAnterior.getmTelefono().equals(clienteActual.getmTelefono())) {
                        Log.d("ListaCliente", "el clienteActual y el clienteAnterior son DIFERENTES");
                        Log.d("ListaCliente", "ahora miramos que no exista en la lista auxiliar");
                        /*for(int k = 0; k < this.mListAuxClientes.size(); ++k) {
                            clienteAux = this.mListAuxClientes.get(k);
                            if(!clienteAux.getmNombre().equals(clienteActual.getmNombre()) ||
                                    !clienteAux.getmPrimerApellido().equals(clienteActual.getmPrimerApellido()) ||
                                    !clienteAux.getmSegundoApellido().equals(clienteActual.getmSegundoApellido()) ||
                                    !clienteAux.getmTelefono().equals(clienteActual.getmTelefono())) {
                                Log.d("ListaCliente", "entonces añadiríamos este cliente");
                                this.mListAuxClientes.add(clienteAux);
                            }
                        }

                        //añadiremos los que detecte diferente en una lista a parte sin recorrerla y ya la miraremos luego
                        this.mListAuxClientes_v2.add(clienteActual);
                    } else {
                        Log.d("ListaCliente", "el clienteActual y el clienteAnterior son IGUALES");
                    }
                }
                //this.mListAuxClientes.add(this.mListClientes.get(i));
            }
        }

        for (int i = 0; i < this.mListAuxClientes_v2.size(); ++i) {
            Log.d("ListaCliente", "this.mListAuxClientes_v2.get(i) = " +
                    this.mListAuxClientes_v2.get(i).getmNombre() + " " +
                    this.mListAuxClientes_v2.get(i).getmPrimerApellido() + " " +
                    this.mListAuxClientes_v2.get(i).getmSegundoApellido() + " " +
                    this.mListAuxClientes_v2.get(i).getmTelefono());
        }
        //this.mListClientes = this.mListAuxClientes; */
/*
        //probamos con el contains
        for(int i = 0; i < this.mListClientes.size(); ++i) {
            if(!this.mListAuxClientes.contains(this.mListClientes.get(i))) {
                this.mListAuxClientes.add(this.mListClientes.get(i));
                Log.d("ListaClientes", "he añadido el elemento a la lista auxiliar");
            } else {
                Log.d("ListaClientes", "ya existía el elemento");
            }
        }*/

        ConsultaClientes clienteAnterior, clienteActual;
        for(int i = 0; i < this.mListClientes.size(); ++i) {
            clienteAnterior = this.mListClientes.get(i);
            for(int k = i +1; k < this.mListClientes.size(); ++k) {
                clienteActual = this.mListClientes.get(k);
                if(!clienteAnterior.getmNombre().equals(clienteActual.getmNombre()) ||
                        !clienteAnterior.getmPrimerApellido().equals(clienteActual.getmPrimerApellido()) ||
                        !clienteAnterior.getmSegundoApellido().equals(clienteActual.getmSegundoApellido()) ||
                        !clienteAnterior.getmTelefono().equals(clienteActual.getmTelefono())) {
                    Log.d("ListaClientes", "AL FIIINNN son diferentes");
                    this.mListAuxClientes.add(clienteActual);
                } else {
                    Log.d("ListaClientes", "LLORA porque NO son diferentes");
                }
            }
        }
        this.mListClientes = this.mListAuxClientes;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void fillData(List<ConsultaClientes> list) {
        this.mListClientes = list;
    }

    @Override
    public void setPresenter(ListaClientesContract.Presenter presenter) {
        this.mPresenter = (ListaClientesPresenterImpl) presenter;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
