package com.ot.jgomez.recepcion.views.visualizacuentas;

import android.app.DatePickerDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.ot.jgomez.recepcion.R;
import com.ot.jgomez.recepcion.adapters.ConsultaListaEntradasYResueltas;
import com.ot.jgomez.recepcion.database.DBRegistroEntradas;
import com.ot.jgomez.recepcion.items.ConsultaReparacionesPendientes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class VisualizaCuentasActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private TextView mTextFecha;
    private TextView mTextTotalEntradas;
    private TextView mTextTotalResueltas;
    private TextView mTextTotalDinero;
    private Button mBtnCambiaDia;
    private RecyclerView mRecyclerEntradas;
    private RecyclerView mRecyclerResueltas;
    private ConsultaListaEntradasYResueltas mAdapterEntradas;
    private ConsultaListaEntradasYResueltas mAdapterResueltas;
    private List<ConsultaReparacionesPendientes> mListEntradas;
    private List<ConsultaReparacionesPendientes> mListResueltas;
    private boolean mHayEntradas;
    private boolean mHayResueltas;
    private String mFechaActual;
    private TextView mTextNoEntradas;
    private TextView mTextNoResueltas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualiza_cuentas);

        ActionBar myBar = getSupportActionBar();
        myBar.setHomeButtonEnabled(true);
        myBar.setDisplayHomeAsUpEnabled(true);
        myBar.setDisplayShowHomeEnabled(true);
        myBar.setTitle(R.string.cuentas);

        this.init();
    }

    private void initLists() {
        this.mListEntradas.clear();
        this.mListResueltas.clear();
        this.mHayEntradas = false;
        this.mHayResueltas = false;
        List<DBRegistroEntradas> entradas = DBRegistroEntradas.getAllEntradas();
        if (entradas.size() != 0) {
            for (DBRegistroEntradas entries : entradas) {
                if (entries.getFechaSalida().equals("") &&
                        entries.getFechaEntrada().equals(this.mFechaActual)) {
                    this.mHayEntradas = true;
                    this.mListEntradas.add(new ConsultaReparacionesPendientes(
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
                            entries.getFechaSalida(),
                            entries.getCosteReparacion()
                    ));
                } else if (entries.getFechaSalida().equals(this.mFechaActual)) {
                    this.mHayResueltas = true;
                    this.mListResueltas.add(new ConsultaReparacionesPendientes(
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
                            entries.getFechaSalida(),
                            entries.getCosteReparacion()
                    ));
                }
            }
        }
        this.llenaAdapters();
    }

    private void llenaAdapters() {
        String noEntradas = "No hay entradas sin resolver para el día " + this.mFechaActual;
        String noResueltas = "No hay entradas resueltas para el día " + this.mFechaActual;
        if (!this.mHayEntradas) {
            this.mRecyclerEntradas.setVisibility(View.GONE);
            this.mTextNoEntradas.setVisibility(View.VISIBLE);
            this.mTextNoEntradas.setText(noEntradas);
        } else {
            this.mTextNoEntradas.setVisibility(View.GONE);
            this.mRecyclerEntradas.setVisibility(View.VISIBLE);
            this.mAdapterEntradas = new ConsultaListaEntradasYResueltas(this.mListEntradas, this);
            this.mRecyclerEntradas.setLayoutManager(new LinearLayoutManager(this));
            this.mRecyclerEntradas.setAdapter(this.mAdapterEntradas);
        }

        if (!this.mHayResueltas) {
            this.mRecyclerResueltas.setVisibility(View.GONE);
            this.mTextNoResueltas.setVisibility(View.VISIBLE);
            this.mTextNoResueltas.setText(noResueltas);
        } else {
            this.mTextNoResueltas.setVisibility(View.GONE);
            this.mRecyclerResueltas.setVisibility(View.VISIBLE);
            this.mAdapterResueltas = new ConsultaListaEntradasYResueltas(this.mListResueltas, this);
            this.mRecyclerResueltas.setLayoutManager(new LinearLayoutManager(this));
            this.mRecyclerResueltas.setAdapter(this.mAdapterResueltas);
        }
        this.hazCuentas();
    }

    private void hazCuentas() {
        int count = 0;
        if (!this.mHayEntradas && !this.mHayResueltas) {
            this.mTextTotalDinero.setText(String.valueOf(count));
        } else if (!this.mHayEntradas && this.mHayResueltas) {
            for (int i = 0; i < this.mListResueltas.size(); ++i) {
                count += Integer.parseInt(this.mListResueltas.get(i).getmPrecioReparacion());
            }
            this.mTextTotalDinero.setText(String.valueOf(count));
        } else if (this.mHayEntradas && !this.mHayResueltas) {
            this.mTextTotalDinero.setText(String.valueOf(count));
        }
    }

    private void init() {
        this.mTextFecha = (TextView) findViewById(R.id.txtvw_dia_actual_total_cuentas);
        this.mTextTotalEntradas = (TextView) findViewById(R.id.txtvw_editar_total_entradas);
        this.mTextTotalResueltas = (TextView) findViewById(R.id.txtvw_editar_total_resueltas);
        this.mTextTotalDinero = (TextView) findViewById(R.id.txtvw_editar_total_dinero);
        this.mBtnCambiaDia = (Button) findViewById(R.id.boton_cambia_dia_cuentas);
        this.mBtnCambiaDia.setOnClickListener(this);
        this.mRecyclerEntradas = (RecyclerView) findViewById(R.id.recycler_view_total_entradas);
        this.mRecyclerResueltas = (RecyclerView) findViewById(R.id.recycler_view_total_resueltas);
        this.mTextNoEntradas = (TextView) findViewById(R.id.txtvw_lista_entradas_vacia);
        this.mTextNoResueltas = (TextView) findViewById(R.id.txtvw_lista_resueltas_vacia);
        this.mListEntradas = new ArrayList<>();
        this.mListResueltas = new ArrayList<>();
        this.initDia();
        this.mHayEntradas = false;
        this.mHayResueltas = false;
    }

    private void initDia() {
        Calendar c = Calendar.getInstance();
        String year = String.valueOf(c.getTime().getYear());
        String aux_year = "20" + year.substring(1, 3);
        String month = String.valueOf(c.getTime().getMonth() + 1);
        if (month.length() == 1) month = "0" + month;
        String day = String.valueOf(c.getTime().getDate());
        if (day.length() == 1) day = "0" + day;
        this.mFechaActual = day + "." + month + "." + aux_year;
        this.mTextFecha.setText(this.mFechaActual);
        this.initLists();
    }

    @Override
    public void onClick(View v) {
        if (v == this.mBtnCambiaDia) {
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String parseYear = Integer.toString(year);
        String parseMonth = Integer.toString(month + 1);
        if (parseMonth.length() == 1) parseMonth = "0" + parseMonth;
        String parseDay = Integer.toString(dayOfMonth);
        if (parseDay.length() == 1) parseDay = "0" + parseDay;
        this.mFechaActual = parseDay + "." + parseMonth + "." + parseYear;
        this.mTextFecha.setText(this.mFechaActual);
        this.initLists();
    }
}