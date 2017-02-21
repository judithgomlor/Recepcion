package com.ot.jgomez.recepcion.views.consultarreparaciones;

import android.app.DatePickerDialog;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.TextView;

import com.ot.jgomez.recepcion.R;
import com.ot.jgomez.recepcion.adapters.ConsultaListaAdapter;
import com.ot.jgomez.recepcion.items.ConsultaReparacionesPendientes;

import java.util.Calendar;
import java.util.List;

public class ConsultarReparacionesActivity extends AppCompatActivity implements View.OnClickListener,
        CompoundButton.OnCheckedChangeListener, DatePickerDialog.OnDateSetListener {

    private CheckBox mCheckHoy;
    private CheckBox mCheckOtro;
    private CheckBox mCheckTodas;
    private TextView mTextOtraFecha;
    private TextView mTextHoy;
    private String mFechaActual;
    private RecyclerView mRecyclerPendientes;
    private RecyclerView mRecyclerResueltas;
    private ConsultaListaAdapter mAdapterPendientes;
    private ConsultaListaAdapter mAdapterResueltas;
    private List<ConsultaReparacionesPendientes> mListPendientes;
    private List<ConsultaReparacionesPendientes> mListResueltas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_reparaciones);

        ActionBar myBar = getSupportActionBar();
        myBar.setHomeButtonEnabled(true);
        myBar.setDisplayHomeAsUpEnabled(true);
        myBar.setDisplayShowHomeEnabled(true);
        myBar.setTitle(R.string.consultar_reparaciones);

        this.init();
    }

    private void init() {
        this.mCheckHoy = (CheckBox) findViewById(R.id.checkbox_consulta_reparaciones_hoy);
        this.mCheckHoy.setOnCheckedChangeListener(this);
        this.mTextHoy = (TextView) findViewById(R.id.txtvw_consulta_reparaciones_hoy);
        this.mTextHoy.setVisibility(View.GONE);
        this.mCheckOtro = (CheckBox) findViewById(R.id.checkbox_consulta_reparaciones_otro_dia);
        this.mCheckOtro.setOnCheckedChangeListener(this);
        this.mTextOtraFecha = (TextView) findViewById(R.id.txtvw_consulta_reparaciones_cambia_dia);
        this.mTextOtraFecha.setVisibility(View.GONE);
        this.mCheckTodas = (CheckBox) findViewById(R.id.checkbox_consulta_reparaciones_todas);
        this.mCheckTodas.setOnCheckedChangeListener(this);
        this.mRecyclerPendientes = (RecyclerView) findViewById(R.id.recycler_view_lista_pendientes_consulta_reparacion);
        this.mRecyclerResueltas = (RecyclerView) findViewById(R.id.recycler_view_lista_resueltos_consulta_reparacion);
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
        if (v == this.mTextOtraFecha) {
            this.cambiaFecha();
        }
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
        String parseMonth = Integer.toString(month + 1);
        if (parseMonth.length() == 1) parseMonth = "0" + parseMonth;
        String parseDay = Integer.toString(dayOfMonth);
        if (parseDay.length() == 1) parseDay = "0" + parseDay;
        this.mFechaActual = parseDay + "." + parseMonth + "." + parseYear;
        this.mTextOtraFecha.setText(this.mFechaActual);
    }

    private void getLists() {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView == this.mCheckHoy) {
            if (isChecked) {
                this.mCheckOtro.setChecked(false);
                this.mCheckTodas.setChecked(false);
                this.mTextHoy.setVisibility(View.VISIBLE);
                this.initDia();
                this.mTextHoy.setText(this.mFechaActual);
                this.getLists();
            } else {
                this.mTextHoy.setVisibility(View.GONE);
            }
        } else if (buttonView == this.mCheckOtro) {
            if (isChecked) {
                this.mCheckHoy.setChecked(false);
                this.mCheckTodas.setChecked(false);
                this.mTextOtraFecha.setVisibility(View.VISIBLE);
                this.initDia();
                this.mTextOtraFecha.setText(this.mFechaActual);
                this.mTextOtraFecha.setOnClickListener(this);
            } else {
                this.mTextOtraFecha.setVisibility(View.GONE);
            }
        } else if (buttonView == this.mCheckTodas) {
            if (isChecked) {
                this.mCheckHoy.setChecked(false);
                this.mCheckOtro.setChecked(false);
            }
        }
    }
}
