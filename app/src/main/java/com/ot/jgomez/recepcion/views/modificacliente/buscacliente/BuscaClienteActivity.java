package com.ot.jgomez.recepcion.views.modificacliente.buscacliente;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ot.jgomez.recepcion.R;
import com.ot.jgomez.recepcion.adapters.ListaBuscaClientesDialogAdapters;
import com.ot.jgomez.recepcion.items.ConsultaClientes;
import com.ot.jgomez.recepcion.views.modificacliente.ModificaClienteContract;
import com.ot.jgomez.recepcion.views.modificacliente.ModificaClientePresenterImpl;

import java.util.ArrayList;
import java.util.List;

public class BuscaClienteActivity extends AppCompatActivity implements TextWatcher, View.OnClickListener, ModificaClienteContract.View {

    private static final String EXTRA_NOMBRE = "nombre";
    private static final String EXTRA_PRIMER_APELLIDO = "primer_apellido";
    private static final String EXTRA_SEGUNDO_APELLIDO = "segundo_apellido";
    private static final String EXTRA_TELEFONO = "telefono";
    private static final int REQUEST_CODE_OK = 1;
    private static final int REQUEST_CODE_KO = 2;
    private EditText mEditBusqueda;
    private Button mBtnReinicia;
    private List<ConsultaClientes> mList;
    private ModificaClientePresenterImpl mPresenter;
    private ListaBuscaClientesDialogAdapters mAdapter;
    private RecyclerView mRecyclerView;
    private String mNombre;
    private String mPrimerApellido;
    private String mSegundoApellido;
    private String mTelefono;
    private boolean mBoolHayDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_cliente);

        ActionBar myBar = getSupportActionBar();
        myBar.setHomeButtonEnabled(true);
        myBar.setDisplayHomeAsUpEnabled(true);
        myBar.setDisplayShowHomeEnabled(true);
        myBar.setTitle(R.string.busqueda);

        if (this.mPresenter == null) {
            this.mPresenter = new ModificaClientePresenterImpl();
        }
        this.mPresenter.attach(this, this);

        this.init();
    }

    private void init() {
        this.mEditBusqueda = (EditText) findViewById(R.id.edit_busca_nombre);
        this.mEditBusqueda.addTextChangedListener(this);
        this.mBtnReinicia = (Button) findViewById(R.id.boton_reinicia_busqueda_cliente);
        this.mBtnReinicia.setOnClickListener(this);
        this.mList = new ArrayList<>();
        this.mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_busca_nombre_cliente);

        this.mList = this.mPresenter.getAllClientes();
        if (this.mList.size() > 0) {
            this.mAdapter = new ListaBuscaClientesDialogAdapters(this, this.mList);
            this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            this.mRecyclerView.setAdapter(this.mAdapter);
        } else {
            this.showMessage("No hay clientes guardados");
        }
        this.mNombre = "";
        this.mPrimerApellido = "";
        this.mSegundoApellido = "";
        this.mTelefono = "";
        this.mBoolHayDatos = false;
        Log.d("BuscaCliente", "this.mBoolHayDatos = " +this.mBoolHayDatos);
    }

    public void pasaOtraActividad (Bundle bundle) {
        this.mNombre = bundle.getString(this.EXTRA_NOMBRE);
        this.mPrimerApellido = bundle.getString(this.EXTRA_PRIMER_APELLIDO);
        this.mSegundoApellido = bundle.getString(this.EXTRA_SEGUNDO_APELLIDO);
        this.mTelefono = bundle.getString(this.EXTRA_TELEFONO);
        if(!this.mNombre.equals("") && !this.mPrimerApellido.equals("") &&
                !this.mSegundoApellido.equals("") && !this.mTelefono.equals("")) {
            this.mBoolHayDatos = true;
        } else {
            this.mBoolHayDatos = false;
        }

        if(this.mBoolHayDatos) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra(this.EXTRA_NOMBRE, this.mNombre);
            returnIntent.putExtra(this.EXTRA_PRIMER_APELLIDO, this.mPrimerApellido);
            returnIntent.putExtra(this.EXTRA_SEGUNDO_APELLIDO, this.mSegundoApellido);
            returnIntent.putExtra(this.EXTRA_TELEFONO, this.mTelefono);

            setResult(this.RESULT_OK, returnIntent);
            finish();
        } else {
            Intent returnIntent = new Intent();
            setResult(this.RESULT_CANCELED, returnIntent);
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if(!this.mBoolHayDatos) {
                Intent returnIntent = new Intent();
                setResult(this.RESULT_CANCELED, returnIntent);
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() >= 2) {
            buscaNombre(s.toString());
        }
    }

    private void buscaNombre(String nombre) {
        this.mList = this.mPresenter.getClientes(nombre);
        if (this.mList.size() > 0) {
            this.mAdapter = new ListaBuscaClientesDialogAdapters(this, this.mList);
            this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            this.mRecyclerView.setAdapter(this.mAdapter);
        } else {
            this.showMessage("No existen clientes con ese nombre");
        }
    }

    @Override
    public void onClick(View v) {
        if (v == this.mBtnReinicia) {
            this.mEditBusqueda.setText("");
            this.mList = this.mPresenter.getAllClientes();
            if (this.mList.size() > 0) {
                this.mAdapter = new ListaBuscaClientesDialogAdapters(this, this.mList);
                this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                this.mRecyclerView.setAdapter(this.mAdapter);
            } else {
                this.showMessage("No hay clientes guardados");
            }
        }
    }

    @Override
    public void fillData(List<ConsultaClientes> list) {

    }

    @Override
    public void setPresenter(ModificaClienteContract.Presenter presenter) {
        this.mPresenter = (ModificaClientePresenterImpl) presenter;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
