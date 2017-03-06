package com.ot.jgomez.recepcion.views.visualizacuentas;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.ot.jgomez.recepcion.R;
import com.ot.jgomez.recepcion.adapters.ConsultaListaEntradasYResueltas;
import com.ot.jgomez.recepcion.database.DBRegistroEntradas;
import com.ot.jgomez.recepcion.items.ConsultaReparacionesPendientes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class VisualizaCuentasActivity extends AppCompatActivity implements View.OnClickListener,
        DatePickerDialog.OnDateSetListener, CompoundButton.OnCheckedChangeListener {

    private String mAñoSeleccionado;
    private String mMesSeleccionado;
    private TextView mTextTotalEntradas;
    private TextView mTextTotalResueltas;
    private TextView mTextTotalDinero;
    private Button mBtnCambiaFecha;
    private RecyclerView mRecyclerEntradas;
    private RecyclerView mRecyclerResueltas;
    private ConsultaListaEntradasYResueltas mAdapterEntradas;
    private ConsultaListaEntradasYResueltas mAdapterResueltas;
    private List<ConsultaReparacionesPendientes> mListEntradasDiarias;
    private List<ConsultaReparacionesPendientes> mListEntradasMensuales;
    private List<ConsultaReparacionesPendientes> mListEntradasAnuales;
    private List<ConsultaReparacionesPendientes> mListResueltasDiarias;
    private List<ConsultaReparacionesPendientes> mListResueltasMensuales;
    private List<ConsultaReparacionesPendientes> mListResueltasAnuales;
    private boolean mHayEntradasDiarias;
    private boolean mHayResueltasDiarias;
    private boolean mHayEntradasMensuales;
    private boolean mHayResueltasMensuales;
    private boolean mHayEntradasAnuales;
    private boolean mHayResueltasAnuales;
    private String mFechaActual;
    private TextView mTextNoEntradas;
    private TextView mTextNoResueltas;
    private RadioButton mRdBtnDia;
    private RadioButton mRdBtnMes;
    private RadioButton mRdBtnAño;
    private TextView mTextDia;
    private TextView mTextMes;
    private TextView mTextAño;
    private TextView mCuentaDia;
    private TextView mCuentaMes;
    private TextView mCuentaAño;

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
        Log.d("VisualizaCuentas", "initLists");
        this.mListEntradasDiarias.clear();
        this.mListResueltasDiarias.clear();
        this.mHayEntradasDiarias = false;
        this.mHayResueltasDiarias = false;
        List<DBRegistroEntradas> entradas = DBRegistroEntradas.getAllEntradas();
        if (entradas.size() != 0) {
            for (DBRegistroEntradas entries : entradas) {
                if (entries.getFechaSalida().equals("") &&
                        entries.getFechaEntrada().equals(this.mFechaActual)) {
                    this.mHayEntradasDiarias = true;
                    this.mListEntradasDiarias.add(new ConsultaReparacionesPendientes(
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
                    this.mHayResueltasDiarias = true;
                    this.mListResueltasDiarias.add(new ConsultaReparacionesPendientes(
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
        Log.d("VisualizaCuentas", "initLists() final -> tamaño de listas = " +
                this.mListEntradasDiarias.size() + " " + this.mListResueltasDiarias.size());
        this.llenaAdapters(this.mFechaActual);
    }

    private void initListsMensual(String mes) {
        this.mListEntradasMensuales.clear();
        this.mListResueltasMensuales.clear();
        this.mHayEntradasMensuales = false;
        this.mHayResueltasMensuales = false;
        List<DBRegistroEntradas> entradas = DBRegistroEntradas.getAllEntradas();
        if (entradas.size() != 0) {
            for (DBRegistroEntradas entries : entradas) {
                if (entries.getFechaSalida().equals("") &&
                        entries.getFechaEntrada().substring(3, 5).equals(mes)) {
                    this.mHayEntradasMensuales = true;
                    this.mListEntradasMensuales.add(new ConsultaReparacionesPendientes(
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
                } else if (entries.getFechaSalida().substring(3, 5).equals(mes)) {
                    this.mHayResueltasMensuales = true;
                    this.mListResueltasMensuales.add(new ConsultaReparacionesPendientes(
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
        this.llenaAdapters(mes);
    }

    private void initListsAnuales(String año) {
        this.mListEntradasAnuales.clear();
        this.mListResueltasAnuales.clear();
        this.mHayEntradasAnuales = false;
        this.mHayResueltasAnuales = false;
        List<DBRegistroEntradas> entradas = DBRegistroEntradas.getAllEntradas();
        if (entradas.size() != 0) {
            for (DBRegistroEntradas entries : entradas) {
                if (entries.getFechaSalida().equals("") &&
                        entries.getFechaEntrada().substring(6, 10).equals(año)) {
                    this.mHayEntradasAnuales = true;
                    this.mListEntradasAnuales.add(new ConsultaReparacionesPendientes(
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
                } else if (entries.getFechaSalida().substring(6, 10).equals(año)) {
                    this.mHayResueltasAnuales = true;
                    this.mListResueltasAnuales.add(new ConsultaReparacionesPendientes(
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
        this.llenaAdapters(año);
    }

    private void llenaAdapters(String fecha) {
        Log.d("VisualizaCuentas", "llenaAdapters() con la fecha = " +fecha);
        String noEntradas = "";
        String noResueltas = "";
        if (this.mRdBtnDia.isChecked()) {
            Log.d("VisualizaCuentas", "");
            noEntradas = "No hay entradas sin resolver para el día " + fecha;
            noResueltas = "No hay entradas resueltas para el día " + fecha;
            if (!this.mHayEntradasDiarias) {
                this.mRecyclerEntradas.setVisibility(View.GONE);
                this.mTextNoEntradas.setVisibility(View.VISIBLE);
                this.mTextNoEntradas.setText(noEntradas);
            } else if (this.mHayEntradasDiarias) {
                this.mTextNoEntradas.setVisibility(View.GONE);
                this.mRecyclerEntradas.setVisibility(View.VISIBLE);
                this.mAdapterEntradas = new ConsultaListaEntradasYResueltas(this.mListEntradasDiarias, this);
                this.mRecyclerEntradas.setLayoutManager(new LinearLayoutManager(this));
                this.mRecyclerEntradas.setAdapter(this.mAdapterEntradas);
            }

            if (!this.mHayResueltasDiarias) {
                this.mRecyclerResueltas.setVisibility(View.GONE);
                this.mTextNoResueltas.setVisibility(View.VISIBLE);
                this.mTextNoResueltas.setText(noResueltas);
            } else if (this.mHayResueltasDiarias) {
                this.mTextNoResueltas.setVisibility(View.GONE);
                this.mRecyclerResueltas.setVisibility(View.VISIBLE);
                this.mAdapterResueltas = new ConsultaListaEntradasYResueltas(this.mListResueltasDiarias, this);
                this.mRecyclerResueltas.setLayoutManager(new LinearLayoutManager(this));
                this.mRecyclerResueltas.setAdapter(this.mAdapterResueltas);
            }
        } else if (this.mRdBtnMes.isChecked()) {
            noEntradas = "No hay entradas sin resolver para el mes " + fecha;
            noResueltas = "No hay entradas resueltas para el mes " + fecha;
            if (!this.mHayEntradasMensuales) {
                this.mRecyclerEntradas.setVisibility(View.GONE);
                this.mTextNoEntradas.setVisibility(View.VISIBLE);
                this.mTextNoEntradas.setText(noEntradas);
            } else if (this.mHayEntradasMensuales) {
                this.mTextNoEntradas.setVisibility(View.GONE);
                this.mRecyclerEntradas.setVisibility(View.VISIBLE);
                this.mAdapterEntradas = new ConsultaListaEntradasYResueltas(this.mListEntradasMensuales, this);
                this.mRecyclerEntradas.setLayoutManager(new LinearLayoutManager(this));
                this.mRecyclerEntradas.setAdapter(this.mAdapterEntradas);
            }

            if (!this.mHayResueltasMensuales) {
                this.mRecyclerResueltas.setVisibility(View.GONE);
                this.mTextNoResueltas.setVisibility(View.VISIBLE);
                this.mTextNoResueltas.setText(noResueltas);
            } else if (this.mHayResueltasMensuales) {
                this.mTextNoResueltas.setVisibility(View.GONE);
                this.mRecyclerResueltas.setVisibility(View.VISIBLE);
                this.mAdapterResueltas = new ConsultaListaEntradasYResueltas(this.mListResueltasMensuales, this);
                this.mRecyclerResueltas.setLayoutManager(new LinearLayoutManager(this));
                this.mRecyclerResueltas.setAdapter(this.mAdapterResueltas);
            }
        } else if (this.mRdBtnAño.isChecked()) {
            noEntradas = "No hay entradas sin resolver para el año " + fecha;
            noResueltas = "No hay entradas resueltas para el año " + fecha;
            if (!this.mHayEntradasAnuales) {
                this.mRecyclerEntradas.setVisibility(View.GONE);
                this.mTextNoEntradas.setVisibility(View.VISIBLE);
                this.mTextNoEntradas.setText(noEntradas);
            } else if (this.mHayEntradasAnuales) {
                this.mTextNoEntradas.setVisibility(View.GONE);
                this.mRecyclerEntradas.setVisibility(View.VISIBLE);
                this.mAdapterEntradas = new ConsultaListaEntradasYResueltas(this.mListEntradasAnuales, this);
                this.mRecyclerEntradas.setLayoutManager(new LinearLayoutManager(this));
                this.mRecyclerEntradas.setAdapter(this.mAdapterEntradas);
            }

            if (!this.mHayResueltasAnuales) {
                this.mRecyclerResueltas.setVisibility(View.GONE);
                this.mTextNoResueltas.setVisibility(View.VISIBLE);
                this.mTextNoResueltas.setText(noResueltas);
            } else if (this.mHayResueltasAnuales) {
                this.mTextNoResueltas.setVisibility(View.GONE);
                this.mRecyclerResueltas.setVisibility(View.VISIBLE);
                this.mAdapterResueltas = new ConsultaListaEntradasYResueltas(this.mListResueltasAnuales, this);
                this.mRecyclerResueltas.setLayoutManager(new LinearLayoutManager(this));
                this.mRecyclerResueltas.setAdapter(this.mAdapterResueltas);
            }
        }

        this.hazCuentas();
    }

    private void hazCuentas() {
        int count = 0;
        if (this.mRdBtnDia.isChecked()) {
            if (!this.mHayEntradasDiarias && !this.mHayResueltasDiarias) {
                this.mTextTotalDinero.setText(String.valueOf(count));
            } else if ((!this.mHayEntradasDiarias && this.mHayResueltasDiarias) ||
                    (this.mHayEntradasDiarias && this.mHayResueltasDiarias)) {
                for (int i = 0; i < this.mListResueltasDiarias.size(); ++i) {
                    count += Integer.parseInt(this.mListResueltasDiarias.get(i).getmPrecioReparacion());
                }
                this.mTextTotalDinero.setText(String.valueOf(count));
            } else if (this.mHayEntradasDiarias && !this.mHayResueltasDiarias) {
                this.mTextTotalDinero.setText(String.valueOf(count));
            }
        } else if (this.mRdBtnMes.isChecked()) {
            if (!this.mHayEntradasMensuales && !this.mHayResueltasMensuales) {
                this.mTextTotalDinero.setText(String.valueOf(count));
            } else if ((!this.mHayEntradasMensuales && this.mHayResueltasMensuales) ||
                    (this.mHayEntradasMensuales && this.mHayResueltasMensuales)) {
                for (int i = 0; i < this.mListResueltasMensuales.size(); ++i) {
                    count += Integer.parseInt(this.mListResueltasMensuales.get(i).getmPrecioReparacion());
                }
                this.mTextTotalDinero.setText(String.valueOf(count));
            } else if (this.mHayEntradasMensuales && !this.mHayResueltasMensuales) {
                this.mTextTotalDinero.setText(String.valueOf(count));
            }
        } else if (this.mRdBtnAño.isChecked()) {
            if (!this.mHayEntradasAnuales && !this.mHayResueltasAnuales) {
                this.mTextTotalDinero.setText(String.valueOf(count));
            } else if ((!this.mHayEntradasAnuales && this.mHayResueltasAnuales) ||
                    (this.mHayEntradasAnuales && this.mHayResueltasAnuales)) {
                for (int i = 0; i < this.mListResueltasAnuales.size(); ++i) {
                    count += Integer.parseInt(this.mListResueltasAnuales.get(i).getmPrecioReparacion());
                }
                this.mTextTotalDinero.setText(String.valueOf(count));
            } else if (this.mHayEntradasAnuales && !this.mHayResueltasAnuales) {
                this.mTextTotalDinero.setText(String.valueOf(count));
            }
        }
    }

    private void init() {
        this.mTextTotalEntradas = (TextView) findViewById(R.id.txtvw_editar_total_entradas);
        this.mTextTotalResueltas = (TextView) findViewById(R.id.txtvw_editar_total_resueltas);
        this.mTextTotalDinero = (TextView) findViewById(R.id.txtvw_editar_total_dinero);
        this.mBtnCambiaFecha = (Button) findViewById(R.id.btn_cambia_fecha_cuentas);
        this.mBtnCambiaFecha.setOnClickListener(this);
        this.mRdBtnDia = (RadioButton) findViewById(R.id.rdbtn_cuentas_diarias);
        this.mRdBtnDia.setChecked(true);
        this.mRdBtnDia.setOnCheckedChangeListener(this);
        this.mRdBtnMes = (RadioButton) findViewById(R.id.rdbtn_cuentas_mensuales);
        this.mRdBtnMes.setOnCheckedChangeListener(this);
        this.mRdBtnAño = (RadioButton) findViewById(R.id.rdbtn_cuentas_anuales);
        this.mRdBtnAño.setOnCheckedChangeListener(this);
        this.mTextDia = (TextView) findViewById(R.id.txtvw_cuentas_diarias);
        this.mTextMes = (TextView) findViewById(R.id.txtvw_cuentas_mensuales);
        this.mTextMes.setVisibility(View.GONE);
        this.mTextAño = (TextView) findViewById(R.id.txtvw_cuentas_anuales);
        this.mTextAño.setVisibility(View.GONE);
        this.mCuentaDia = (TextView) findViewById(R.id.txtvw_total_diario);
        this.mCuentaDia.setVisibility(View.GONE);
        this.mCuentaMes = (TextView) findViewById(R.id.txtvw_total_mensual);
        this.mCuentaMes.setVisibility(View.GONE);
        this.mCuentaAño = (TextView) findViewById(R.id.txtvw_total_anual);
        this.mCuentaAño.setVisibility(View.GONE);
        this.mRecyclerEntradas = (RecyclerView) findViewById(R.id.recycler_view_total_entradas);
        this.mRecyclerResueltas = (RecyclerView) findViewById(R.id.recycler_view_total_resueltas);
        this.mTextNoEntradas = (TextView) findViewById(R.id.txtvw_lista_entradas_vacia);
        this.mTextNoResueltas = (TextView) findViewById(R.id.txtvw_lista_resueltas_vacia);
        this.mListEntradasDiarias = new ArrayList<>();
        this.mListResueltasDiarias = new ArrayList<>();
        this.mListEntradasMensuales = new ArrayList<>();
        this.mListResueltasMensuales = new ArrayList<>();
        this.mListEntradasAnuales = new ArrayList<>();
        this.mListResueltasAnuales = new ArrayList<>();
        this.mHayEntradasDiarias = false;
        this.mHayResueltasDiarias = false;
        this.mHayEntradasMensuales = false;
        this.mHayResueltasMensuales = false;
        this.mHayEntradasAnuales = false;
        this.mHayResueltasAnuales = false;
        this.initDia();
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
        this.mTextDia.setText(this.mFechaActual);
        Log.d("VisualizaCuentas", "initDia -> this.mRdBtnDia.isChecked()" + this.mRdBtnDia.isChecked());
        Log.d("VisualizaCuentas", "initDia -> this.mRdBtnMes.isChecked()" + this.mRdBtnMes.isChecked());
        Log.d("VisualizaCuentas", "initDia -> this.mRdBtnAño.isChecked()" + this.mRdBtnAño.isChecked());
        if (this.mRdBtnDia.isChecked()) {
            Log.d("VisualizaCuentas", "initDia -> el mRdBtnDia está presionado y voy a initLists con la fecha = " +
                    this.mFechaActual);
            this.initLists();
        } else if (this.mRdBtnMes.isChecked()) {
            Log.d("VisualizaCuentas", "initDia -> el mRdBtnMes está presionado y voy a initListsMensual con la fecha = " +
                    this.mFechaActual.substring(3, 5));
            this.initListsMensual(this.mFechaActual.substring(3, 5));
        } else if (this.mRdBtnAño.isChecked()) {
            Log.d("VisualizaCuentas", "initDia -> el mRdBtnAño está presionado y voy a initListsAnual con la fecha = " +
                    this.mFechaActual.substring(6, 10));
            this.initListsAnuales(this.mFechaActual.substring(6, 10));
        }
    }

    @Override
    public void onClick(View v) {
        if (v == this.mBtnCambiaFecha) {
            if (this.mRdBtnDia.isChecked()) {
                this.cambiaDiaFecha();
            } else if (this.mRdBtnMes.isChecked()) {
                this.cambiaMesFecha();
            } else if (this.mRdBtnAño.isChecked()) {
                this.cambiaAñoFecha();
            } else {
                this.dialogNoHayDatos();
            }
        }
    }

    private void dialogNoHayDatos() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog_atencion);

        dialog.setTitle(R.string.titulo_aviso);

        TextView textViewDescripcion = (TextView) dialog.findViewById(R.id.txtvw_custom_dialog_atencion);
        textViewDescripcion.setText(R.string.texto_elige_una_opcion);

        Button buttonAceptar = (Button) dialog.findViewById(R.id.boton_aceptar_dialog_atencion);
        buttonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void cambiaAñoFecha() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.content_dialog_meses);

        dialog.setTitle(R.string.titulo_elige_año);
        ListView listView = (ListView) dialog.findViewById(R.id.list_meses);
        final List<String> listAños = new ArrayList<>();
        int añoActual = 2017;
        for (int i = añoActual; i < añoActual + 50; ++i) {
            listAños.add(String.valueOf(i));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                listAños);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mAñoSeleccionado = listAños.get(position);
                mTextAño.setText(mAñoSeleccionado);
                dialog.dismiss();
            }
        });
        Button buttonCancelar = (Button) dialog.findViewById(R.id.boton_cancelar_escoger_fechas);
        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void cambiaMesFecha() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.content_dialog_meses);

        dialog.setTitle(R.string.titulo_elige_mes);
        ListView listView = (ListView) dialog.findViewById(R.id.list_meses);
        final List<String> listMeses = new ArrayList<>();

        listMeses.add("Enero");
        listMeses.add("Febrero");
        listMeses.add("Marzo");
        listMeses.add("Abril");
        listMeses.add("Mayo");
        listMeses.add("Junio");
        listMeses.add("Julio");
        listMeses.add("Agosto");
        listMeses.add("Septiembre");
        listMeses.add("Octubre");
        listMeses.add("Noviembre");
        listMeses.add("Diciembre");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                listMeses);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mMesSeleccionado = listMeses.get(position);
                mTextMes.setText(mMesSeleccionado);
                dialog.dismiss();
            }
        });
        Button buttonCancelar = (Button) dialog.findViewById(R.id.boton_cancelar_escoger_fechas);
        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void cambiaDiaFecha() {
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
        this.mTextDia.setText(this.mFechaActual);
        if (this.mRdBtnDia.isChecked()) {
            this.initLists();
        } else if (this.mRdBtnMes.isChecked()) {
            this.initListsMensual(this.mFechaActual.substring(3, 5));
        } else if (this.mRdBtnAño.isChecked()) {
            this.initListsAnuales(this.mFechaActual.substring(6, 10));
        }
    }

    private String getMesByNumber(String num) {
        String mes = "";
        switch (num) {
            case "01":
                mes = "Enero";
                break;
            case "02":
                mes = "Febrero";
                break;
            case "03":
                mes = "Marzo";
                break;
            case "04":
                mes = "Abril";
                break;
            case "05":
                mes = "Mayo";
                break;
            case "06":
                mes = "Junio";
                break;
            case "07":
                mes = "Julio";
                break;
            case "08":
                mes = "Agosto";
                break;
            case "09":
                mes = "Septiembre";
                break;
            case "10":
                mes = "Octubre";
                break;
            case "11":
                mes = "Noviembre";
                break;
            case "12":
                mes = "Diciembre";
                break;
        }
        return mes;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView == this.mRdBtnDia) {
            if (isChecked) {
                this.mRdBtnMes.setChecked(false);
                this.mRdBtnAño.setChecked(false);
                this.mTextDia.setVisibility(View.VISIBLE);
                this.mCuentaDia.setVisibility(View.VISIBLE);
            } else {
                this.mTextDia.setVisibility(View.GONE);
                this.mCuentaDia.setVisibility(View.GONE);
            }
        } else if (buttonView == this.mRdBtnMes) {
            if (isChecked) {
                this.mRdBtnDia.setChecked(false);
                this.mRdBtnAño.setChecked(false);
                this.mTextMes.setVisibility(View.VISIBLE);
                String mes = this.getMesByNumber(this.mFechaActual.substring(3, 5));
                this.mTextMes.setText(mes);
                this.mCuentaMes.setVisibility(View.VISIBLE);
            } else {
                this.mTextMes.setVisibility(View.GONE);
                this.mCuentaMes.setVisibility(View.GONE);
            }
        } else if (buttonView == this.mRdBtnAño) {
            if (isChecked) {
                this.mRdBtnDia.setChecked(false);
                this.mRdBtnMes.setChecked(false);
                this.mTextAño.setVisibility(View.VISIBLE);
                this.mTextAño.setText(this.mFechaActual.substring(6, this.mFechaActual.length()));
                this.mCuentaAño.setVisibility(View.VISIBLE);
            } else {
                this.mTextAño.setVisibility(View.GONE);
                this.mCuentaAño.setVisibility(View.GONE);
            }
        }
    }
}