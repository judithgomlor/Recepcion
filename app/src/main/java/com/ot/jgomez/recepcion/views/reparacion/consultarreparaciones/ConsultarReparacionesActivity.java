package com.ot.jgomez.recepcion.views.reparacion.consultarreparaciones;

import android.app.DatePickerDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ot.jgomez.recepcion.R;
import com.ot.jgomez.recepcion.adapters.ConsultaListaAdapter;
import com.ot.jgomez.recepcion.items.ConsultaReparacionesPendientes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ConsultarReparacionesActivity extends AppCompatActivity implements View.OnClickListener,
        CompoundButton.OnCheckedChangeListener, DatePickerDialog.OnDateSetListener, ConsultarReparacionesContract.View {

    private CheckBox mCheckHoy;
    private CheckBox mCheckAPartir;
    private CheckBox mCheckDiaConcreto;
    private CheckBox mCheckTodas;
    private CheckBox mCheckPendientes;
    private CheckBox mCheckResueltas;
    private TextView mTextDiaConcreto;
    private TextView mTextHoy;
    private TextView mTextAPartir;
    private TextView mTextEntradasPendientes;
    private TextView mTextEntradasResueltas;
    private String mFechaActual;
    private LinearLayout mLayoutRecyclerPendientes;
    private LinearLayout mLayoutRecyclerResueltas;
    private RecyclerView mRecyclerPendientes;
    private RecyclerView mRecyclerResueltas;
    private ConsultaListaAdapter mAdapterPendientes;
    private ConsultaListaAdapter mAdapterResueltas;
    private List<ConsultaReparacionesPendientes> mListPendientes;
    private List<ConsultaReparacionesPendientes> mListResueltas;
    private ConsultarReparacionesPresenterImpl mPresenter;
    private boolean mBoolDiaConcreto;
    private boolean mBoolAPartir;
    private boolean mBoolTodas;
    private boolean mBoolHoy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_reparaciones);

        ActionBar myBar = getSupportActionBar();
        myBar.setHomeButtonEnabled(true);
        myBar.setDisplayHomeAsUpEnabled(true);
        myBar.setDisplayShowHomeEnabled(true);
        myBar.setTitle(R.string.consultar_reparaciones);

        if (this.mPresenter == null) {
            this.mPresenter = new ConsultarReparacionesPresenterImpl();
        }
        this.mPresenter.attach(this, this);

        this.init();
    }

    /**
     * Inicializa todos los campos de la actividad.
     */
    private void init() {
        this.mCheckHoy = (CheckBox) findViewById(R.id.checkbox_consulta_reparaciones_hoy);
        this.mCheckHoy.setOnCheckedChangeListener(this);
        this.mTextHoy = (TextView) findViewById(R.id.txtvw_consulta_reparaciones_hoy);
        this.mTextHoy.setVisibility(View.GONE);

        this.mCheckAPartir = (CheckBox) findViewById(R.id.checkbox_consulta_reparaciones_a_partir_dia);
        this.mCheckAPartir.setOnCheckedChangeListener(this);
        this.mTextAPartir = (TextView) findViewById(R.id.txtvw_consulta_reparaciones_a_partir_dia);
        this.mTextAPartir.setVisibility(View.GONE);

        this.mCheckDiaConcreto = (CheckBox) findViewById(R.id.checkbox_consulta_reparaciones_dia_concreto);
        this.mCheckDiaConcreto.setOnCheckedChangeListener(this);
        this.mTextDiaConcreto = (TextView) findViewById(R.id.txtvw_consulta_reparaciones_dia_concreto);
        this.mTextDiaConcreto.setVisibility(View.GONE);

        this.mCheckTodas = (CheckBox) findViewById(R.id.checkbox_consulta_reparaciones_todas);
        this.mCheckTodas.setOnCheckedChangeListener(this);

        this.mCheckPendientes = (CheckBox) findViewById(R.id.checkbox_consulta_reparaciones_pendientes);
        this.mCheckPendientes.setOnCheckedChangeListener(this);
        this.mLayoutRecyclerPendientes = (LinearLayout) findViewById(R.id.layout_recycler_view_lista_pendientes_consulta_reparacion);
        this.mLayoutRecyclerPendientes.setVisibility(View.GONE);
        this.mTextEntradasPendientes = (TextView) findViewById(R.id.txtvw_entradas_pendientes);
        this.mRecyclerPendientes = (RecyclerView) findViewById(R.id.recycler_view_lista_pendientes_consulta_reparacion);

        this.mCheckResueltas = (CheckBox) findViewById(R.id.checkbox_consulta_reparaciones_resueltas);
        this.mCheckResueltas.setOnCheckedChangeListener(this);
        this.mLayoutRecyclerResueltas = (LinearLayout) findViewById(R.id.layout_recycler_view_lista_resueltas_consulta_reparacion);
        this.mLayoutRecyclerResueltas.setVisibility(View.GONE);
        this.mTextEntradasResueltas = (TextView) findViewById(R.id.txtvw_entradas_resueltas);
        this.mRecyclerResueltas = (RecyclerView) findViewById(R.id.recycler_view_lista_resueltos_consulta_reparacion);

        this.mListPendientes = new ArrayList<>();
        this.mListResueltas = new ArrayList<>();

        this.mBoolAPartir = false;
        this.mBoolDiaConcreto = false;
        this.mBoolTodas = false;
        this.mBoolHoy = false;
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
        if (v == this.mTextAPartir) {
            this.mBoolAPartir = true;
            this.cambiaFecha();
        } else if (v == this.mTextDiaConcreto) {
            this.mBoolDiaConcreto = true;
            this.cambiaFecha();
        }
    }

    /**
     * Inicializa el día por defecto como el día actual.
     */
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

    /**
     * Muestra un dialog para cambiar la fecha de búsqueda.
     */
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
        this.actualizarListas();
        if (this.mBoolAPartir) {
            this.mTextAPartir.setText(this.mFechaActual);
        } else if (this.mBoolDiaConcreto) {
            this.mTextDiaConcreto.setText(this.mFechaActual);
        }
    }

    /**
     * Utilizaremos esta función para cada vez que cambiemos la fecha, que la lista de la RV lo
     * tenga en cuenta para buscar los datos.
     **/
    private void actualizarListas() {
        //parte de la RV de la lista de entradas pendientes
        Log.d("ConsultarReparaciones", "Actualizamos la lista de pendientes con la fecha = "
                + this.mFechaActual);
        this.mListPendientes = this.mPresenter.getReparacionesPendientes(this.mFechaActual,
                this.mBoolTodas, this.mBoolAPartir);
        this.mAdapterPendientes = new ConsultaListaAdapter(this.mListPendientes, this,
                this.mBoolHoy);
        this.mRecyclerPendientes.setLayoutManager(new LinearLayoutManager(this));
        this.mRecyclerPendientes.setAdapter(this.mAdapterPendientes);
        if (this.mListPendientes.size() == 0) {
            this.mRecyclerPendientes.setVisibility(View.GONE);
            String text = this.getResources().getString(R.string.no_entradas_pendientes) +
                    " " + this.mFechaActual;
            this.mTextEntradasPendientes.setText(text);
            this.mTextEntradasPendientes.setVisibility(View.VISIBLE);
        } else {
            this.mTextEntradasPendientes.setVisibility(View.GONE);
            this.mRecyclerPendientes.setVisibility(View.VISIBLE);
        }

        //parte de la RV de la lista de entradas resueltas
        Log.d("ConsultarReparaciones", "Actualizamos la lista de resueltas con la fecha = "
                + this.mFechaActual);
        this.mListResueltas = this.mPresenter.getReparacionesResueltas(this.mFechaActual,
                this.mBoolTodas, this.mBoolAPartir);
        this.mAdapterResueltas = new ConsultaListaAdapter(this.mListResueltas, this,
                this.mBoolHoy);
        this.mRecyclerResueltas.setLayoutManager(new LinearLayoutManager(this));
        this.mRecyclerResueltas.setAdapter(this.mAdapterResueltas);
        if (this.mListResueltas.size() > 0) {
            this.mRecyclerResueltas.setVisibility(View.VISIBLE);
            this.mTextEntradasResueltas.setVisibility(View.GONE);
        } else {
            this.mTextEntradasResueltas.setVisibility(View.VISIBLE);
            String text = this.getResources().getString(R.string.no_entradas_resueltas) +
                    " " + this.mFechaActual;
            this.mTextEntradasResueltas.setText(text);
            this.mRecyclerResueltas.setVisibility(View.GONE);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView == this.mCheckHoy) {
            if (isChecked) {
                this.mCheckDiaConcreto.setChecked(false);
                this.mCheckAPartir.setChecked(false);
                this.mCheckTodas.setChecked(false);
                this.mTextHoy.setVisibility(View.VISIBLE);
                this.initDia();
                this.mTextHoy.setText(this.mFechaActual);
                this.mBoolHoy = true;
                this.actualizarListas();
            } else {
                this.mTextHoy.setVisibility(View.GONE);
                this.mBoolHoy = false;
            }
        } else if (buttonView == this.mCheckAPartir) {
            if (isChecked) {
                this.mCheckHoy.setChecked(false);
                this.mCheckTodas.setChecked(false);
                this.mCheckDiaConcreto.setChecked(false);
                this.mTextAPartir.setVisibility(View.VISIBLE);
                this.initDia();
                this.mTextAPartir.setText(this.mFechaActual);
                this.mTextAPartir.setOnClickListener(this);
                this.actualizarListas();
            } else {
                this.mTextAPartir.setVisibility(View.GONE);
                this.mBoolAPartir = false;
            }
        } else if (buttonView == this.mCheckDiaConcreto) {
            if (isChecked) {
                this.mCheckHoy.setChecked(false);
                this.mCheckAPartir.setChecked(false);
                this.mCheckTodas.setChecked(false);
                this.mTextDiaConcreto.setVisibility(View.VISIBLE);
                this.initDia();
                this.mTextDiaConcreto.setText(this.mFechaActual);
                this.mTextDiaConcreto.setOnClickListener(this);
                this.actualizarListas();
            } else {
                this.mTextDiaConcreto.setVisibility(View.GONE);
                this.mBoolDiaConcreto = false;
            }
        } else if (buttonView == this.mCheckTodas) {
            if (isChecked) {
                this.mCheckHoy.setChecked(false);
                this.mCheckAPartir.setChecked(false);
                this.mCheckDiaConcreto.setChecked(false);
                this.mBoolTodas = true;
                this.actualizarListas();
            } else {
                this.mBoolTodas = false;
            }
        } else if (buttonView == this.mCheckPendientes) {
            if (isChecked) {
                this.mLayoutRecyclerPendientes.setVisibility(View.VISIBLE);
            } else {
                this.mLayoutRecyclerPendientes.setVisibility(View.GONE);
            }
        } else if (buttonView == this.mCheckResueltas) {
            if (isChecked) {
                this.mLayoutRecyclerResueltas.setVisibility(View.VISIBLE);
            } else {
                this.mLayoutRecyclerResueltas.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void fillData(List<ConsultaReparacionesPendientes> list) {
        this.mAdapterPendientes.notifyDataSetChanged();
        this.mAdapterResueltas.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(ConsultarReparacionesContract.Presenter presenter) {
        this.mPresenter = (ConsultarReparacionesPresenterImpl) presenter;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
