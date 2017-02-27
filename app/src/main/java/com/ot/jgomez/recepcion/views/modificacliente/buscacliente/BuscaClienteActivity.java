package com.ot.jgomez.recepcion.views.modificacliente.buscacliente;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
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

    private static final int REQUEST_CODE_OK = 1;
    private static final int REQUEST_CODE_KO = 2;
    private EditText mEditBusqueda;
    private boolean mBoolPrimerEspacio;
    private boolean mBoolSegundoEspacio;
    private Button mBtnRefresca;
    private Button mBtnVuelve;
    private String mPrimeraPalabra;
    private String mSegundaPalabra;
    private String mTerceraPalabra;
    private int mLengthPrimeraPalabra;
    private int mLengthSegundaPalabra;
    private List<ConsultaClientes> mList;
    private ModificaClientePresenterImpl mPresenter;
    private ListaBuscaClientesDialogAdapters mAdapter;
    private RecyclerView mRecyclerView;

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
        this.mBoolPrimerEspacio = false;
        this.mBoolSegundoEspacio = false;
        this.mEditBusqueda = (EditText) findViewById(R.id.edit_busca_nombre);
        this.mEditBusqueda.addTextChangedListener(this);
        this.mBtnRefresca = (Button) findViewById(R.id.boton_refresca_busca_cliente);
        this.mBtnRefresca.setOnClickListener(this);
        this.mBtnVuelve = (Button) findViewById(R.id.boton_vuelve_busca_cliente);
        this.mBtnVuelve.setOnClickListener(this);
        this.mList = new ArrayList<>();
        this.mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_busca_nombre_cliente);
        this.mPrimeraPalabra = "";
        this.mSegundaPalabra = "";
        this.mTerceraPalabra = "";
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
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
        String aux;
        int index;
        if (s.length() >= 2) {
            index = s.length();
            //falta un booleano para ponerlo a true una vez encontremos un cambio de palabra
            if (!mBoolPrimerEspacio && !mBoolSegundoEspacio) {
                if ((s.charAt(index - 2)) == ' ') {
                    mBoolPrimerEspacio = true;
                } else {
                    mPrimeraPalabra = s.toString().substring(0, 1).toUpperCase() +
                            s.toString().substring(1, s.length());
                }
            } else if (mBoolPrimerEspacio && !mBoolSegundoEspacio) {
                mLengthPrimeraPalabra = mPrimeraPalabra.length();
                if (s.charAt(index - 2) == ' ') {
                    mBoolSegundoEspacio = true;
                } else {
                    mSegundaPalabra = s.toString().substring(mLengthPrimeraPalabra,
                            mLengthPrimeraPalabra + 1).toUpperCase() +
                            s.toString().substring(mLengthPrimeraPalabra + 1, s.length());
                }
            } else if (mBoolPrimerEspacio && mBoolSegundoEspacio) {
                mLengthSegundaPalabra = mLengthPrimeraPalabra + mSegundaPalabra.length();
                mTerceraPalabra = s.toString().substring(mLengthSegundaPalabra,
                        mLengthSegundaPalabra + 1).toUpperCase() +
                        s.toString().substring(mLengthSegundaPalabra + 1, s.length());
            }

            if (!mPrimeraPalabra.equals("") && !mSegundaPalabra.equals("") &&
                    !mTerceraPalabra.equals("")) {
                aux = mPrimeraPalabra + mSegundaPalabra + mTerceraPalabra;
            } else if (!mPrimeraPalabra.equals("") && !mSegundaPalabra.equals("") &&
                    mTerceraPalabra.equals("")) {
                aux = mPrimeraPalabra + mSegundaPalabra;
            } else if (!mPrimeraPalabra.equals("") && mSegundaPalabra.equals("") &&
                    mTerceraPalabra.equals("")) {
                aux = mPrimeraPalabra;
            } else {
                aux = "";
            }
            buscaNombre(aux);
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
        if (v == this.mBtnRefresca) {
            if(this.mAdapter != null) {
                this.mAdapter.refrescaLista();
            }
        } else if (v == this.mBtnVuelve) {
            this.mEditBusqueda.setText("");
            this.mRecyclerView.setAdapter(null);
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
