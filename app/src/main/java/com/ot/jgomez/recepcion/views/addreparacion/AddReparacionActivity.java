package com.ot.jgomez.recepcion.views.addreparacion;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ot.jgomez.recepcion.R;
import com.ot.jgomez.recepcion.control.ComparatorStrings;
import com.ot.jgomez.recepcion.database.DBClientes;
import com.ot.jgomez.recepcion.items.ConsultaClientes;
import com.ot.jgomez.recepcion.views.addcliente.AddClienteActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AddReparacionActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private static final int REQUEST_NEW_CLIENT = 1;
    private static final String EXTRA_NAME = "nombre";
    private static final String EXTRA_FIRST_SURNAME = "primer_apellido";
    private static final String EXTRA_SECOND_SURNAME = "segundo_apellido";
    private static final String EXTRA_MARCA = "marca";
    private static final String EXTRA_MODELO = "modelo";
    private static final String EXTRA_MATRICULA = "matricula";
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
        this.mSpinnerNombre = (Spinner) findViewById(R.id.spinner_nombre_cargado);
        this.mSpinnerPrimerApellido = (Spinner) findViewById(R.id.spinner_primer_apellido_cargado);
        this.mSpinnerSegundoApellido = (Spinner) findViewById(R.id.spinner_segundo_apellido_cargado);
        this.mSpinnerMarca = (Spinner) findViewById(R.id.spinner_marca_cargada);
        this.mSpinnerModelo = (Spinner) findViewById(R.id.spinner_modelo_cargado);
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
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v == this.mBtnSave) {

        } else if (v == this.mBtnCancel) {

        } else if (v == this.mBtnCrear) {
            Intent myIntent = new Intent(this, AddClienteActivity.class);
            startActivityForResult(myIntent, REQUEST_NEW_CLIENT);
        } else if (v == this.mBtnRecuperar) {
            this.mListClientes.clear();
            this.mListNombres.clear();
            this.mListPrimerosApellidos.clear();
            this.mListSegundosApellidos.clear();
            this.mListMarcas.clear();
            this.mListModelos.clear();
            this.mListMatriculas.clear();
            List<DBClientes> clientes = DBClientes.getAllClientes();
            if (clientes.size() != 0) {
                for (DBClientes clients : clientes) {
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

                for (int i = 0; i < this.mListClientes.size(); ++i) {
                    this.mListNombres.add(this.mListClientes.get(i).getmNombre());
                    this.mListPrimerosApellidos.add(this.mListClientes.get(i).getmPrimerApellido());
                    this.mListSegundosApellidos.add(this.mListClientes.get(i).getmSegundoApellido());
                    this.mListMarcas.add(this.mListClientes.get(i).getmMarcaVehiculo());
                    this.mListModelos.add(this.mListClientes.get(i).getmModeloVehiculo());
                    this.mListMatriculas.add(this.mListClientes.get(i).getmMatriculaVehiculo());
                }

                ComparatorStrings compare = new ComparatorStrings();
                Collections.sort(this.mListNombres,compare);

                //adapter nombre
                this.mAdapterNombre = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1,
                        this.mListNombres);
                this.mAdapterNombre.setDropDownViewResource(android.R.layout.simple_list_item_1);
                this.mSpinnerNombre.setAdapter(this.mAdapterNombre);
                this.mSpinnerNombre.setOnItemSelectedListener(this);
            } else {
                Toast.makeText(this, "No hay clientes guardados", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_NEW_CLIENT) {
            if (resultCode == RESULT_OK) {
                String nombre = data.getStringExtra(EXTRA_NAME);
                String primerApellido = data.getStringExtra(EXTRA_FIRST_SURNAME);
                String segundoApellido = data.getStringExtra(EXTRA_SECOND_SURNAME);
                String marca = data.getStringExtra(EXTRA_MARCA);
                String modelo = data.getStringExtra(EXTRA_MODELO);
                String matricula = data.getStringExtra(EXTRA_MATRICULA);

                //habrá que volver a empezar de nuevo --> Como si le diésemos al botón de recuperar:
                //falta acabar de ponerlo bien
                this.mListClientes.clear();
                this.mListNombres.clear();
                this.mListPrimerosApellidos.clear();
                this.mListSegundosApellidos.clear();
                this.mListMarcas.clear();
                this.mListModelos.clear();
                this.mListMatriculas.clear();

                List<DBClientes> clientes = DBClientes.getAllClientes();
                if (clientes.size() != 0) {
                    for (DBClientes clients : clientes) {
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

                    for (int i = 0; i < this.mListClientes.size(); ++i) {
                        this.mListNombres.add(this.mListClientes.get(i).getmNombre());
                        this.mListPrimerosApellidos.add(this.mListClientes.get(i).getmPrimerApellido());
                        this.mListSegundosApellidos.add(this.mListClientes.get(i).getmSegundoApellido());
                        this.mListMarcas.add(this.mListClientes.get(i).getmMarcaVehiculo());
                        this.mListModelos.add(this.mListClientes.get(i).getmModeloVehiculo());
                        this.mListMatriculas.add(this.mListClientes.get(i).getmMatriculaVehiculo());
                    }

                    ComparatorStrings compare = new ComparatorStrings();
                    Collections.sort(this.mListNombres,compare);

                    //adapter nombre
                    this.mAdapterNombre = new ArrayAdapter<String>(this,
                            android.R.layout.simple_list_item_1,
                            this.mListNombres);
                    this.mAdapterNombre.setDropDownViewResource(android.R.layout.simple_list_item_1);
                    this.mSpinnerNombre.setAdapter(this.mAdapterNombre);
                    this.mSpinnerNombre.setOnItemSelectedListener(this);
                }
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getAdapter() == this.mAdapterNombre) {
            this.mListPrimerosApellidos.clear();
            for (int i = 0; i < this.mListClientes.size(); ++i) {
                if (this.mListClientes.get(i).getmNombre().equals(this.mListNombres.get(position))) {
                    this.mListPrimerosApellidos.add(this.mListClientes.get(i).getmPrimerApellido());
                }
            }

            //adapter primer apellido
            this.mAdapterPrimerApellido = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,
                    this.mListPrimerosApellidos);
            this.mAdapterPrimerApellido.setDropDownViewResource(android.R.layout.simple_list_item_1);
            this.mSpinnerPrimerApellido.setAdapter(this.mAdapterPrimerApellido);
            this.mSpinnerPrimerApellido.setOnItemSelectedListener(this);
        } else if (parent.getAdapter() == this.mAdapterPrimerApellido) {
            this.mListSegundosApellidos.clear();
            for (int i = 0; i < this.mListClientes.size(); ++i) {
                if (this.mListClientes.get(i).getmPrimerApellido().equals(this.mListPrimerosApellidos.
                        get(position))) {
                    this.mListSegundosApellidos.add(this.mListClientes.get(i).getmSegundoApellido());
                }
            }

            //adapter segundo apellido
            this.mAdapterSegundoApellido = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,
                    this.mListSegundosApellidos);
            this.mAdapterSegundoApellido.setDropDownViewResource(android.R.layout.simple_list_item_1);
            this.mSpinnerSegundoApellido.setAdapter(this.mAdapterSegundoApellido);
            this.mSpinnerSegundoApellido.setOnItemSelectedListener(this);
        } else if (parent.getAdapter() == this.mAdapterSegundoApellido) {
            this.mListMarcas.clear();
            for (int i = 0; i < this.mListClientes.size(); ++i) {
                if (this.mListClientes.get(i).getmSegundoApellido().equals(this.mListSegundosApellidos.
                        get(position))) {
                    this.mListMarcas.add(this.mListClientes.get(i).getmMarcaVehiculo());
                }
            }

            //adapter marca vehículo
            this.mAdapterMarca = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,
                    this.mListMarcas);
            this.mAdapterMarca.setDropDownViewResource(android.R.layout.simple_list_item_1);
            this.mSpinnerMarca.setAdapter(this.mAdapterMarca);
            this.mSpinnerMarca.setOnItemSelectedListener(this);
        } else if (parent.getAdapter() == this.mAdapterMarca) {
            this.mListModelos.clear();
            for (int i = 0; i < this.mListClientes.size(); ++i) {
                if (this.mListClientes.get(i).getmMarcaVehiculo().equals(this.mListMarcas.get(position))) {
                    this.mListModelos.add(this.mListClientes.get(i).getmModeloVehiculo());
                }
            }

            //adapter modelo vehículo
            this.mAdapterModelo = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,
                    this.mListModelos);
            this.mAdapterModelo.setDropDownViewResource(android.R.layout.simple_list_item_1);
            this.mSpinnerModelo.setAdapter(this.mAdapterModelo);
            this.mSpinnerModelo.setOnItemSelectedListener(this);
        } else if (parent.getAdapter() == this.mAdapterModelo) {
            this.mListMatriculas.clear();
            for (int i = 0; i < this.mListClientes.size(); ++i) {
                if (this.mListClientes.get(i).getmModeloVehiculo().equals(this.mListModelos.get(position))) {
                    this.mListMatriculas.add(this.mListClientes.get(i).getmMatriculaVehiculo());
                }
            }

            //adapter matrícula vehículo
            this.mAdapterMatricula = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,
                    this.mListMatriculas);
            this.mAdapterMatricula.setDropDownViewResource(android.R.layout.simple_list_item_1);
            this.mSpinnerMatricula.setAdapter(this.mAdapterMatricula);
            this.mSpinnerMatricula.setOnItemSelectedListener(this);
        } else if (parent.getAdapter() == this.mAdapterMatricula) {

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
