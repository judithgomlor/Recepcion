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
    ¡¡¡¡¡Falta por mirar que se añada el primero y que después se mire si existe otro igual
    para borrar el elemento que está repetido de la lista auxiliar !!!!!
     */
    private void preparaListas() {
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
