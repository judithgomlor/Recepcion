package com.ot.jgomez.recepcion.views.addreparacion;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ot.jgomez.recepcion.R;
import com.ot.jgomez.recepcion.control.ComparatorStrings;
import com.ot.jgomez.recepcion.database.DBClientes;
import com.ot.jgomez.recepcion.database.DBRegistroEntradas;
import com.ot.jgomez.recepcion.items.ConsultaClientes;
import com.ot.jgomez.recepcion.views.addcliente.AddClienteActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class AddReparacionActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener,
        CalendarView.OnDateChangeListener, DatePickerDialog.OnDateSetListener {

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
    private Button mBtnCambiaDia;
    private Spinner mSpinnerNombre;
    private Spinner mSpinnerPrimerApellido;
    private Spinner mSpinnerSegundoApellido;
    private Spinner mSpinnerMarca;
    private Spinner mSpinnerModelo;
    private Spinner mSpinnerMatricula;
    private TextView mTextFechaActual;
    private EditText mEditResumen;
    private EditText mEditDescripcion;
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
    private String mNombre;
    private String mPrimerApellido;
    private String mSegundoApellido;
    private String mMarca;
    private String mModelo;
    private String mMatricula;
    private String mFechaEntrada;
    private String mParseFechaEntrada;
    private String mResumen;
    private String mDescripcion;
    private boolean mCambiaDia = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reparacion);

        ActionBar myBar = getSupportActionBar();
        myBar.setDisplayHomeAsUpEnabled(true);
        myBar.setDisplayShowHomeEnabled(true);
        myBar.setHomeButtonEnabled(true);

        init();
        initDia();
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
        this.mBtnCambiaDia = (Button) findViewById(R.id.boton_cambia_dia);
        this.mBtnCambiaDia.setOnClickListener(this);
        this.mSpinnerNombre = (Spinner) findViewById(R.id.spinner_nombre_cargado);
        this.mSpinnerPrimerApellido = (Spinner) findViewById(R.id.spinner_primer_apellido_cargado);
        this.mSpinnerSegundoApellido = (Spinner) findViewById(R.id.spinner_segundo_apellido_cargado);
        this.mSpinnerMarca = (Spinner) findViewById(R.id.spinner_marca_cargada);
        this.mSpinnerModelo = (Spinner) findViewById(R.id.spinner_modelo_cargado);
        this.mSpinnerMatricula = (Spinner) findViewById(R.id.spinner_matricula_cargada);
        this.mTextFechaActual = (TextView) findViewById(R.id.txtvw_fecha_actual);
        this.mEditResumen = (EditText) findViewById(R.id.editar_resumen_reparacion);
        this.mEditDescripcion = (EditText) findViewById(R.id.editar_descripcion_reparacion);
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
        if(v == this.mBtnCambiaDia) {
            this.cambiaDia();
        } else if (v == this.mBtnSave) {
            this.guardaEntrada();
        } else if (v == this.mBtnCancel) {
            this.cancelaEntrada();
        } else if (v == this.mBtnCrear) {
            Intent myIntent = new Intent(this, AddClienteActivity.class);
            startActivityForResult(myIntent, REQUEST_NEW_CLIENT);
        } else if (v == this.mBtnRecuperar) {
            this.recuperaDatos();
        }
    }

    private void cancelaEntrada() {
        //tendrá que salir con dialog custom --> tener en cuenta el título
    }

    private void recuperaDatos() {
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

    private void cambiaDia() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = new DatePickerDialog(
                this,
                R.style.DialogTheme,
                this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show();

    }

    private void guardaEntrada() {
        if(this.mEditResumen.getText().length() == 0) {
            this.mResumen =  "";
        } else {
            this.mResumen = this.mEditResumen.getText().toString();
        }

        if(this.mEditDescripcion.getText().length() == 0) {
            this.mDescripcion = "";
        } else {
            this.mDescripcion = this.mEditDescripcion.getText().toString();
        }

        DBRegistroEntradas registroEntrada = new DBRegistroEntradas(this.mNombre, this.mPrimerApellido, this.mSegundoApellido,
                this.mMarca, this.mModelo, this.mMatricula, this.mFechaEntrada,
                this.mResumen, this.mDescripcion, "", "");
        registroEntrada.save();
        Toast.makeText(this, "Entrada guardada", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void initDia() {
        Calendar c = Calendar.getInstance();
        String year = String.valueOf(c.getTime().getYear());
        String aux_year = "20" + year.substring(1,3);
        String month = String.valueOf(c.getTime().getMonth()+1);
        if(month.length() == 1) month = "0" + month;
        String day = String.valueOf(c.getTime().getDate());
        this.mFechaEntrada = day + month + aux_year;
        this.mParseFechaEntrada = day + "." + month + "." + aux_year;
        this.mTextFechaActual.setText(this.mParseFechaEntrada);
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

                this.mListNombres.add(0,nombre);
                this.mAdapterNombre = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                        this.mListNombres);
                this.mAdapterNombre.setDropDownViewResource(android.R.layout.simple_list_item_1);
                this.mSpinnerNombre.setAdapter(this.mAdapterNombre);


                this.mListPrimerosApellidos.add(0, primerApellido);
                this.mAdapterPrimerApellido = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                        this.mListPrimerosApellidos);
                this.mAdapterPrimerApellido.setDropDownViewResource(android.R.layout.simple_list_item_1);
                this.mSpinnerPrimerApellido.setAdapter(this.mAdapterPrimerApellido);

                this.mListSegundosApellidos.add(0, segundoApellido);
                this.mAdapterSegundoApellido = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                        this.mListSegundosApellidos);
                this.mAdapterSegundoApellido.setDropDownViewResource(android.R.layout.simple_list_item_1);
                this.mSpinnerSegundoApellido.setAdapter(this.mAdapterSegundoApellido);

                this.mListMarcas.add(0, marca);
                this.mAdapterMarca = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                        this.mListMarcas);
                this.mAdapterMarca.setDropDownViewResource(android.R.layout.simple_list_item_1);
                this.mSpinnerMarca.setAdapter(this.mAdapterMarca);

                this.mListModelos.add(0, modelo);
                this.mAdapterModelo = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                        this.mListModelos);
                this.mAdapterModelo.setDropDownViewResource(android.R.layout.simple_list_item_1);
                this.mSpinnerModelo.setAdapter(this.mAdapterModelo);

                this.mListMatriculas.add(0, matricula);
                this.mAdapterMatricula = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                        this.mListMatriculas);
                this.mAdapterMatricula.setDropDownViewResource(android.R.layout.simple_list_item_1);
                this.mSpinnerMatricula.setAdapter(this.mAdapterMatricula);

                this.mNombre = nombre;
                this.mPrimerApellido = primerApellido;
                this.mSegundoApellido = segundoApellido;
                this.mMarca = marca;
                this.mModelo = modelo;
                this.mMatricula = matricula;
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

            this.mNombre = this.mListNombres.get(position);
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

            this.mPrimerApellido = this.mListPrimerosApellidos.get(position);
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

            this.mSegundoApellido = this.mListSegundosApellidos.get(position);
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

            this.mMarca = this.mListMarcas.get(position);
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

            this.mModelo = this.mListModelos.get(position);
        } else if (parent.getAdapter() == this.mAdapterMatricula) {
            this.mMatricula = this.mListMatriculas.get(position);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

        String parseYear = Integer.toString(year);
        String parseMonth = Integer.toString(month+1);
        if(parseMonth.length() == 1) parseMonth = "0" + parseMonth;
        String parseDay = Integer.toString(dayOfMonth);
        this.mFechaEntrada = parseDay + parseMonth + parseYear;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        this.mCambiaDia = true;
        String parseYear = Integer.toString(year);
        String parseMonth = Integer.toString(month+1);
        if(parseMonth.length() == 1) parseMonth = "0" + parseMonth;
        String parseDay = Integer.toString(dayOfMonth);
        this.mFechaEntrada = parseDay + parseMonth + parseYear;
        this.mParseFechaEntrada = parseDay + "." + parseMonth + "." + parseYear;
        this.mTextFechaActual.setText(this.mParseFechaEntrada);
    }
}
