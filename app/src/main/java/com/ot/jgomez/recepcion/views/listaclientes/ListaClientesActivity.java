package com.ot.jgomez.recepcion.views.listaclientes;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ot.jgomez.recepcion.R;
import com.ot.jgomez.recepcion.adapters.ConsultaClientesRvAdapter;
import com.ot.jgomez.recepcion.control.ComparatorStrings;
import com.ot.jgomez.recepcion.control.MergeSort;
import com.ot.jgomez.recepcion.database.DBClientes;
import com.ot.jgomez.recepcion.items.ConsultaClientes;
import com.ot.jgomez.recepcion.items.NombrePos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListaClientesActivity extends AppCompatActivity implements ListaClientesContract.View {

    private List<ConsultaClientes> mListClientes;
    private RecyclerView mRecyclerView;
    private ConsultaClientesRvAdapter mAdapter;
    private ListaClientesPresenterImpl mPresenter;

    //listas auxiliares
    private List<ConsultaClientes> mListAuxClientes;
    private List<NombrePos> mListAuxNombresPos;

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
        this.mListAuxClientes = new ArrayList<>();
        this.mListAuxNombresPos = new ArrayList<>();

        this.mListClientes = this.mPresenter.getClientes();

        if (this.mListClientes.size() > 0) {
            this.copiaNombres();
            MergeSort merge = new MergeSort(this.mListAuxNombresPos);
            merge.sort();
            this.ordenaListaInicial();
            this.preparaListas_v2();
        }

        this.mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_lista_clientes);
        this.mAdapter = new ConsultaClientesRvAdapter(this.mListClientes, this);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.mRecyclerView.setAdapter(this.mAdapter);
    }

    private void ordenaListaInicial() {
        this.mListAuxClientes.clear();
        ConsultaClientes cliente;
        int pos;
        for (int i = 0; i < this.mListAuxNombresPos.size(); ++i) {
            pos = this.mListAuxNombresPos.get(i).getmPosicion();
            cliente = new ConsultaClientes(
                    this.mListClientes.get(pos).getmNombre(),
                    this.mListClientes.get(pos).getmPrimerApellido(),
                    this.mListClientes.get(pos).getmSegundoApellido(),
                    this.mListClientes.get(pos).getmNombreApellidos(),
                    this.mListClientes.get(pos).getmTelefono(),
                    this.mListClientes.get(pos).getmMarcaVehiculo(),
                    this.mListClientes.get(pos).getmModeloVehiculo(),
                    this.mListClientes.get(pos).getmMatriculaVehiculo()
            );
            this.mListAuxClientes.add(cliente);
        }
    }

    /*
    Copiaremos los nombres de la lista de clientes junto con la posición que ocupen
     */
    private void copiaNombres() {
        for (int i = 0; i < this.mListClientes.size(); ++i) {
            this.mListAuxNombresPos.add(new NombrePos(
                    this.mListClientes.get(i).getmNombre(),
                    i
            ));
        }
    }

    private void preparaListas_v2() {
        List<ConsultaClientes> clientes = new ArrayList<>();
        ConsultaClientes clienteAnterior, clienteActual;
        clienteAnterior = this.mListAuxClientes.get(0);
        clientes.add(clienteAnterior);
        if (this.mListAuxClientes.size() > 1) {
            for (int i = 0; i < this.mListAuxClientes.size(); ++i) {
                clienteActual = this.mListAuxClientes.get(i);
                if (!clienteAnterior.getmNombre().equals(clienteActual.getmNombre()) ||
                        !clienteAnterior.getmPrimerApellido().equals(clienteActual.getmPrimerApellido()) ||
                        !clienteAnterior.getmSegundoApellido().equals(clienteActual.getmSegundoApellido()) ||
                        !clienteAnterior.getmTelefono().equals(clienteActual.getmTelefono())) {
                    clientes.add(clienteActual);
                }
                clienteAnterior = clienteActual;
            }
        }
        this.mListClientes = clientes;
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
