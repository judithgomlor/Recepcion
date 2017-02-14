package com.ot.jgomez.recepcion.views.solvereparacion;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.ot.jgomez.recepcion.R;
import com.ot.jgomez.recepcion.items.ConsultaReparacionesPendientes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SolveReparacionActivity extends AppCompatActivity implements View.OnClickListener {

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

    @Override
    public void onClick(View v) {
        if(v == this.mBtnAceptar) {

        } else if(v == this.mBtnCancelar) {

        } else if(v == this.mBtnCambiarFecha) {

        }
    }
}
