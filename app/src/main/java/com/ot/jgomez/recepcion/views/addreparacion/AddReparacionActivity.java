package com.ot.jgomez.recepcion.views.addreparacion;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;

import com.ot.jgomez.recepcion.R;
import com.ot.jgomez.recepcion.database.DBClientes;
import com.ot.jgomez.recepcion.items.ConsultaClientes;
import com.ot.jgomez.recepcion.views.addcliente.AddClienteActivity;

import java.util.ArrayList;
import java.util.List;

public class AddReparacionActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnSave;
    private Button mBtnCancel;
    private Button mBtnCrear;
    private Button mBtnRecuperar;
    private Spinner mSpinnerNombre;
    private Spinner mSpinnerPrimerApellido;
    private Spinner mSpinnerSegundoApellido;
    private Spinner mSpinnerMarca;
    private Spinner mSpinnerModelo;
    private Spinner mSpinnerMatricula;
    private CalendarView mCalendar;
    private String mFechaEntrada;
    private EditText mResumen;
    private EditText mDescripcion;
    private List<String> mListNombres;
    private List<String> mListPrimerosApellidos;
    private List<String> mListSegundosApellidos;
    private List<String> mListMarcas;
    private List<String> mListModelos;
    private List<String> mListMatriculas;
    private List<ConsultaClientes> mListClientes;
    private ArrayAdapter<String> mAdapterNombre;
    private ArrayAdapter<String> mAdapterPrimerApellido;
    private ArrayAdapter<String> mAdapterSegundoApellido;
    private ArrayAdapter<String> mAdapterMarca;
    private ArrayAdapter<String> mAdapterModelo;
    private ArrayAdapter<String> mAdapterMatricula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reparacion);

        ActionBar myBar = getSupportActionBar();
        myBar.setDisplayHomeAsUpEnabled(true);
        myBar.setDisplayShowHomeEnabled(true);
        myBar.setHomeButtonEnabled(true);

        init();
    }

    private void init() {
        this.mBtnSave = (Button) findViewById(R.id.boton_aceptar_nueva_reparacion);
        this.mBtnSave.setOnClickListener(this);
        this.mBtnCancel = (Button) findViewById(R.id.boton_cancelar_nueva_reparacion);
        this.mBtnCancel.setOnClickListener(this);
        this.mBtnCrear = (Button) findViewById(R.id.btn_crear_datos_clientes);
        this.mBtnCrear.setOnClickListener(this);
        this.mBtnRecuperar = (Button) findViewById(R.id.btn_recuperar_datos_clientes);
        this.mBtnRecuperar.setOnClickListener(this);
        this.mSpinnerMarca = (Spinner) findViewById(R.id.spinner_marca_cargada);
        this.mSpinnerModelo = (Spinner) findViewById(R.id.spinner_modelo_cargada);
        this.mSpinnerMatricula = (Spinner) findViewById(R.id.spinner_matricula_cargada);
        this.mCalendar = (CalendarView) findViewById(R.id.calendar_datos_reparacion);
        this.mResumen = (EditText) findViewById(R.id.editar_resumen_reparacion);
        this.mDescripcion = (EditText) findViewById(R.id.editar_descripcion_reparacion);
        this.mListClientes = new ArrayList<>();
        this.mListNombres = new ArrayList<>();
        this.mListPrimerosApellidos = new ArrayList<>();
        this.mListSegundosApellidos = new ArrayList<>();
        this.mListMarcas = new ArrayList<>();
        this.mListModelos = new ArrayList<>();
        this.mListMatriculas = new ArrayList<>();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v == this.mBtnSave) {

        } else if(v == this.mBtnCancel) {

        } else if(v == this.mBtnCrear) {
            Intent myIntent = new Intent(this, AddClienteActivity.class);
            startActivity(myIntent);
        } else if(v == this.mBtnRecuperar) {
            List<DBClientes> clientes = DBClientes.getAllClientes();
            for(DBClientes clients : clientes) {
                this.mListClientes.add(new ConsultaClientes(
                        clients.getNombreCliente(),
                        clients.getPrimerApellidoCliente(),
                        clients.getSegundoApellidoCliente(),
                        clients.getTelefonoCliente(),
                        clients.getMarcaVehiculo(),
                        clients.getModeloVehiculo(),
                        clients.getMatriculaVehiculo()
                ));
            }

            for(int i = 0; i < this.mListClientes.size(); ++i) {
                this.mListNombres.add(this.mListClientes.get(i).getmNombre());
                this.mListPrimerosApellidos.add(this.mListClientes.get(i).getmPrimerApellido());
                this.mListSegundosApellidos.add(this.mListClientes.get(i).getmSegundoApellido());
                this.mListMarcas.add(this.mListClientes.get(i).getmMarcaVehiculo());
                this.mListModelos.add(this.mListClientes.get(i).getmModeloVehiculo());
                this.mListMatriculas.add(this.mListClientes.get(i).getmMatriculaVehiculo());
            }

            this.mAdapterNombre = new ArrayAdapter<String>(getApplicationContext(),
                    R.layout.support_simple_spinner_dropdown_item,
                    this.mListNombres);
            this.mAdapterNombre.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            this.mSpinnerNombre.setAdapter(this.mAdapterNombre);
        }
    }
}
