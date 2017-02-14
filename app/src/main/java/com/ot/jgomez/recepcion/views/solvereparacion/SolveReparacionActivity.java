package com.ot.jgomez.recepcion.views.solvereparacion;

import android.app.DatePickerDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ot.jgomez.recepcion.R;
import com.ot.jgomez.recepcion.control.ComparatorStrings;
import com.ot.jgomez.recepcion.database.DBRegistroEntradas;
import com.ot.jgomez.recepcion.items.ConsultaReparacionesPendientes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SolveReparacionActivity extends AppCompatActivity implements View.OnClickListener,
        DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener {

    private Spinner mSpinnerNombre;
    private Spinner mSpinnerPrimerApellido;
    private Spinner mSpinnerSegundoApellido;
    private Spinner mSpinnerReparacionPendiente;
    private Spinner mSpinnerMarca;
    private Spinner mSpinnerModelo;
    private Spinner mSpinnerMatricula;
    private ArrayAdapter<String> mAdapterNombre;
    private ArrayAdapter<String> mAdapterPrimerApellido;
    private ArrayAdapter<String> mAdapterSegundoApellido;
    private ArrayAdapter<String> mAdapterReparacionesPendientes;
    private ArrayAdapter<String> mAdapterMarca;
    private ArrayAdapter<String> mAdapterModelo;
    private ArrayAdapter<String> mAdapterMatricula;
    private List<String> mListNombres;
    private List<String> mListPrimerosApellidos;
    private List<String> mListSegundosApellidos;
    private List<String> mListReparacionesPendientes;
    private List<String> mListMarcas;
    private List<String> mListModelos;
    private List<String> mListMatricula;
    private List<ConsultaReparacionesPendientes> mListReparaciones;
    private TextView mTextoFechaEntrada;
    private TextView mTextoResumen;
    private TextView mTextDescripcion;
    private String mFechaSalida;
    private TextView mTextoFechaSalida;
    private Button mBtnAceptar;
    private Button mBtnCancelar;
    private Button mBtnCambiarFecha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solve_reparacion);

        ActionBar myBar = getSupportActionBar();
        myBar.setDisplayHomeAsUpEnabled(true);
        myBar.setHomeButtonEnabled(true);
        myBar.setDisplayShowHomeEnabled(true);

        this.init();
        this.initDia();
        this.initSpinners();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        this.mSpinnerNombre = (Spinner) findViewById(R.id.spinner_nombre_solve_reparacion);
        this.mSpinnerPrimerApellido = (Spinner) findViewById(R.id.spinner_primer_apellido_reparacion);
        this.mSpinnerSegundoApellido = (Spinner) findViewById(R.id.spinner_segundo_apellido_reparacion);
        this.mSpinnerReparacionPendiente = (Spinner) findViewById(R.id.spinner_reparaciones_pendientes);
        this.mSpinnerMarca = (Spinner) findViewById(R.id.spinner_marca_vehiculo_reparacion);
        this.mSpinnerModelo = (Spinner) findViewById(R.id.spinner_modelo_vehiculo_reparacion);
        this.mSpinnerMatricula = (Spinner) findViewById(R.id.spinner_matricula_vehiculo_reparacion);
        this.mTextoFechaEntrada = (TextView) findViewById(R.id.txtvw_fecha_entrada_reparacion);
        this.mTextoResumen = (TextView) findViewById(R.id.txtvw_resumen_vehiculo_reparacion);
        this.mTextDescripcion = (TextView) findViewById(R.id.txtvw_descripcion_vehiculo_reparado);
        this.mTextoFechaSalida = (TextView) findViewById(R.id.txtvw_fecha_salida);
        this.mBtnAceptar = (Button) findViewById(R.id.boton_aceptar_resolucion);
        this.mBtnAceptar.setOnClickListener(this);
        this.mBtnCancelar = (Button) findViewById(R.id.boton_cancelar_resolucion);
        this.mBtnCancelar.setOnClickListener(this);
        this.mBtnCambiarFecha = (Button) findViewById(R.id.boton_cambia_dia_salida);
        this.mBtnCambiarFecha.setOnClickListener(this);
        this.mListNombres = new ArrayList<>();
        this.mListPrimerosApellidos = new ArrayList<>();
        this.mListSegundosApellidos = new ArrayList<>();
        this.mListReparacionesPendientes = new ArrayList<>();
        this.mListReparaciones = new ArrayList<>();
        this.mListMarcas = new ArrayList<>();
        this.mListModelos = new ArrayList<>();
        this.mListMatricula = new ArrayList<>();
    }

    private void initDia() {
        Calendar c = Calendar.getInstance();
        String year = String.valueOf(c.getTime().getYear());
        String aux_year = "20" + year.substring(1,3);
        String month = String.valueOf(c.getTime().getMonth()+1);
        if(month.length() == 1) month = "0" + month;
        String day = String.valueOf(c.getTime().getDate());
        if(day.length() == 1) day = "0" + day;
        this.mFechaSalida = day + "." + month + "." + aux_year;
        this.mTextoFechaSalida.setText(this.mFechaSalida);
    }

    private void initSpinners() {
        List<DBRegistroEntradas> entradas = DBRegistroEntradas.getAllEntradas();
        if(entradas.size() != 0) {
            for(DBRegistroEntradas entries : entradas) {
                this.mListReparaciones.add(new ConsultaReparacionesPendientes(
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
                        entries.getFechaSalida()
                ));
            }

            for(int i = 0; i < this.mListReparaciones.size(); ++i) {
                this.mListNombres.add(this.mListReparaciones.get(i).getmNombre());
                this.mListPrimerosApellidos.add(this.mListReparaciones.get(i).getmPrimerApellido());
                this.mListSegundosApellidos.add(this.mListReparaciones.get(i).getmSegundoApellido());
                this.mListReparacionesPendientes.add(this.mListReparaciones.get(i).getmResumenEntrada());
                this.mListMarcas.add(this.mListReparaciones.get(i).getmMarcaVehiculo());
                this.mListModelos.add(this.mListReparaciones.get(i).getmModeloVehiculo());
                this.mListMatricula.add(this.mListReparaciones.get(i).getmMatriculaVehiculo());

                ComparatorStrings compare = new ComparatorStrings();
                Collections.sort(this.mListNombres, compare);

                //adapter nombre
                this.mAdapterNombre = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1,
                        this.mListNombres);
                this.mAdapterNombre.setDropDownViewResource(android.R.layout.simple_list_item_1);
                this.mSpinnerNombre.setAdapter(this.mAdapterNombre);
                this.mSpinnerNombre.setOnItemSelectedListener(this);
            }
        } else {
            Toast.makeText(this, "No hay reparaciones pendientes", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        if(v == this.mBtnAceptar) {

        } else if(v == this.mBtnCancelar) {

        } else if(v == this.mBtnCambiarFecha) {
            this.cambiaFecha();
        }
    }

    private void cambiaFecha() {
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

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String parseYear = Integer.toString(year);
        String parseMonth = Integer.toString(month+1);
        if(parseMonth.length() == 1) parseMonth = "0" + parseMonth;
        String parseDay = Integer.toString(dayOfMonth);
        if(parseDay.length() == 1) parseDay = "0" + parseDay;
        this.mFechaSalida = parseDay + "." + parseMonth + "." + parseYear;
        this.mTextoFechaSalida.setText(this.mFechaSalida);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getAdapter() == this.mAdapterNombre) {
            this.mListPrimerosApellidos.clear();
            for(int i = 0; i < this.mListReparaciones.size(); ++i) {
                if(this.mListReparaciones.get(i).getmNombre().equals(this.mListNombres.get(position))) {
                    this.mListPrimerosApellidos.add(this.mListReparaciones.get(i).getmPrimerApellido());
                }
            }

            //adapter primer apellido
            this.mAdapterPrimerApellido = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,
                    this.mListPrimerosApellidos);
            this.mAdapterPrimerApellido.setDropDownViewResource(android.R.layout.simple_list_item_1);
            this.mSpinnerPrimerApellido.setAdapter(this.mAdapterPrimerApellido);
            this.mSpinnerPrimerApellido.setOnItemSelectedListener(this);
        } //adapter primer apellido
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
