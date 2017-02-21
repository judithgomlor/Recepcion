package com.ot.jgomez.recepcion.views.solvereparacion;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ot.jgomez.recepcion.R;
import com.ot.jgomez.recepcion.control.ComparatorStrings;
import com.ot.jgomez.recepcion.database.DBRegistroEntradas;
import com.ot.jgomez.recepcion.database.DBRegistroEntradas_Table;
import com.ot.jgomez.recepcion.items.ConsultaReparacionesPendientes;
import com.raizlabs.android.dbflow.sql.language.Condition;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.Update;
import com.raizlabs.android.dbflow.sql.language.Where;

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
    private List<String> mListAuxNombres;
    private List<String> mListAuxPrimerosApellidos;
    private List<String> mListAuxSegundosApellidos;
    private List<String> mListAuxMarcas;
    private List<String> mListAuxModelos;
    private List<String> mListAuxMatriculas;
    private List<String> mListAuxReparacionesPendientes;
    private List<ConsultaReparacionesPendientes> mListReparaciones;
    private TextView mTextoFechaEntrada;
    private TextView mTextoResumen;
    private TextView mTextDescripcion;
    private String mFechaSalida;
    private TextView mTextoFechaSalida;
    private Button mBtnAceptar;
    private Button mBtnCancelar;
    private Button mBtnCambiarFecha;
    private EditText mEditResolucion;
    private EditText mEditCoste;
    private String mNombre;
    private String mPrimerApellido;
    private String mSegundoApellido;
    private String mFechaEntrada;
    private String mResumen;
    private String mResolucion;
    private String mDescripcion;
    private String mMarcaVehiculo;
    private String mModeloVehiculo;
    private String mMatriculaVehiculo;
    private String mCosteReparacion;
    private boolean mHayReparacionesPendientes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solve_reparacion);

        ActionBar myBar = getSupportActionBar();
        myBar.setDisplayHomeAsUpEnabled(true);
        myBar.setHomeButtonEnabled(true);
        myBar.setDisplayShowHomeEnabled(true);
        myBar.setTitle(R.string.resolucion);

        this.init();
        this.initDia();
        this.initSpinners();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
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
        this.mEditResolucion = (EditText) findViewById(R.id.editar_resolucion_vehiculo_reparacion);
        this.mEditCoste = (EditText) findViewById(R.id.editar_coste_reparacion);
        this.mListNombres = new ArrayList<>();
        this.mListPrimerosApellidos = new ArrayList<>();
        this.mListSegundosApellidos = new ArrayList<>();
        this.mListReparacionesPendientes = new ArrayList<>();
        this.mListReparaciones = new ArrayList<>();
        this.mListMarcas = new ArrayList<>();
        this.mListModelos = new ArrayList<>();
        this.mListMatricula = new ArrayList<>();
        this.mListAuxNombres = new ArrayList<>();
        this.mListAuxMarcas = new ArrayList<>();
        this.mListAuxModelos = new ArrayList<>();
        this.mListAuxMatriculas = new ArrayList<>();
        this.mListAuxPrimerosApellidos = new ArrayList<>();
        this.mListAuxSegundosApellidos = new ArrayList<>();
        this.mListAuxReparacionesPendientes = new ArrayList<>();
        this.mHayReparacionesPendientes = false;
    }

    private void initDia() {
        Calendar c = Calendar.getInstance();
        String year = String.valueOf(c.getTime().getYear());
        String aux_year = "20" + year.substring(1, 3);
        String month = String.valueOf(c.getTime().getMonth() + 1);
        if (month.length() == 1) month = "0" + month;
        String day = String.valueOf(c.getTime().getDate());
        if (day.length() == 1) day = "0" + day;
        this.mFechaSalida = day + "." + month + "." + aux_year;
        this.mTextoFechaSalida.setText(this.mFechaSalida);
        this.mEditResolucion.setText("");
        this.mEditCoste.setText("");
        this.mHayReparacionesPendientes = false;
    }

    private void initSpinners() {
        this.mListReparaciones.clear();
        this.mListNombres.clear();
        this.mListPrimerosApellidos.clear();
        this.mListSegundosApellidos.clear();
        this.mListReparacionesPendientes.clear();
        this.mListMarcas.clear();
        this.mListModelos.clear();
        this.mListMatricula.clear();
        List<DBRegistroEntradas> entradas = DBRegistroEntradas.getAllEntradas();
        if (entradas.size() != 0) {
            for (DBRegistroEntradas entries : entradas) {
                if (entries.getFechaSalida().equals("")) {
                    this.mHayReparacionesPendientes = true;
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
                            entries.getFechaSalida(),
                            entries.getCosteReparacion()
                    ));
                }
            }

            if (this.mHayReparacionesPendientes) {
                for (int i = 0; i < this.mListReparaciones.size(); ++i) {
                    this.mListNombres.add(this.mListReparaciones.get(i).getmNombre());
                    this.mListPrimerosApellidos.add(this.mListReparaciones.get(i).getmPrimerApellido());
                    this.mListSegundosApellidos.add(this.mListReparaciones.get(i).getmSegundoApellido());
                    this.mListReparacionesPendientes.add(this.mListReparaciones.get(i).getmResumenEntrada());
                    this.mListMarcas.add(this.mListReparaciones.get(i).getmMarcaVehiculo());
                    this.mListModelos.add(this.mListReparaciones.get(i).getmModeloVehiculo());
                    this.mListMatricula.add(this.mListReparaciones.get(i).getmMatriculaVehiculo());
                }

                ComparatorStrings compare = new ComparatorStrings();
                Collections.sort(this.mListNombres, compare);

                //miramos si el nombre anterior coincide con el nombre actual
                this.mListAuxNombres.clear();
                String nombreAnterior, nombreActual;
                nombreAnterior = this.mListNombres.get(0);
                this.mListAuxNombres.add(nombreAnterior);
                if (this.mListNombres.size() > 1) {
                    for (int i = 1; i < this.mListNombres.size(); ++i) {
                        nombreActual = this.mListNombres.get(i);
                        if (!nombreAnterior.equals(nombreActual)) {
                            this.mListAuxNombres.add(this.mListNombres.get(i));
                        }
                        nombreAnterior = nombreActual;
                    }
                }

                //adapter nombre
                this.mAdapterNombre = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1,
                        this.mListAuxNombres);
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
        if (v == this.mBtnAceptar) {
            this.guardaCambios();
            this.finish();
        } else if (v == this.mBtnCancelar) {
            if (this.mEditResolucion.getText().length() != 0 ||
                    this.mEditCoste.getText().length() != 0) {
                this.dialogExit();
            } else {
                finish();
            }
        } else if (v == this.mBtnCambiarFecha) {
            this.cambiaFecha();
        }
    }

    private void guardaCambios() {
        if (this.mHayReparacionesPendientes) {
            String aux_resolucion;
            if (this.mEditResolucion.getText().length() == 0) {
                this.mResolucion = "";
                aux_resolucion = this.mResolucion;
            } else {
                this.mResolucion = this.mEditResolucion.getText().toString();
                aux_resolucion = this.mResolucion.substring(0,1).toUpperCase() + this.mResolucion
                        .substring(1,this.mResolucion.length());
            }

            this.mCosteReparacion = this.mEditCoste.getText().toString();

            //actualizar el campo de fechaSalida y resolucionEntrada
            SQLite.update(DBRegistroEntradas.class)
                    .set(DBRegistroEntradas_Table.fechaSalida.eq(this.mFechaSalida),
                            DBRegistroEntradas_Table.resolucionEntrada.eq(aux_resolucion),
                            DBRegistroEntradas_Table.costeReparacion.eq(this.mCosteReparacion))
                    .where(DBRegistroEntradas_Table.nombre.is(this.mNombre))
                    .and(DBRegistroEntradas_Table.primerApellido.is(this.mPrimerApellido))
                    .and(DBRegistroEntradas_Table.segundoApellido.is(this.mSegundoApellido))
                    .and(DBRegistroEntradas_Table.fechaEntrada.is(this.mFechaEntrada))
                    .and(DBRegistroEntradas_Table.marcaVehiculo.is(this.mMarcaVehiculo))
                    .and(DBRegistroEntradas_Table.modeloVehículo.is(this.mModeloVehiculo))
                    .and(DBRegistroEntradas_Table.matriculaVehiculo.is(this.mMatriculaVehiculo))
                    .and(DBRegistroEntradas_Table.resumenEntrada.is(this.mResumen))
                    .async()
                    .execute();

            Toast.makeText(this, "Ficha actualizada", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "No hay nada que guardar", Toast.LENGTH_LONG).show();
        }
        this.initDia();
        this.initSpinners();
    }

    private void dialogExit() {
        final Dialog dialog = new Dialog(this);
        //dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog);

        //set the custom dialog components
        //TextView textoTitulo = (TextView) dialog.findViewById(R.id.txtvw_titulo_dialog);
        //textoTitulo.setText(R.string.confirmacion);

        dialog.setTitle(R.string.confirmacion);

        TextView textoDialog = (TextView) dialog.findViewById(R.id.txtvw_custom_dialog);
        textoDialog.setText(R.string.texto_custom_dialog);
        Button buttonCancelar = (Button) dialog.findViewById(R.id.boton_cancelar_dialog);
        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Button buttonAceptar = (Button) dialog.findViewById(R.id.boton_aceptar_dialog);
        buttonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dialog.show();
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
        this.mFechaSalida = parseDay + "." + parseMonth + "." + parseYear;
        this.mTextoFechaSalida.setText(this.mFechaSalida);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getAdapter() == this.mAdapterNombre) {
            this.mListPrimerosApellidos.clear();
            for (int i = 0; i < this.mListReparaciones.size(); ++i) {
                if (this.mListReparaciones.get(i).getmNombre().equals(this.mListAuxNombres.get(position))) {
                    this.mListPrimerosApellidos.add(this.mListReparaciones.get(i).getmPrimerApellido());
                }
            }

            //ordenamos la lista por si hubiesen repetidos
            ComparatorStrings compare = new ComparatorStrings();
            Collections.sort(this.mListPrimerosApellidos, compare);

            //hacemos una lista auxiliar para que no se vean apellidos repetidos de nombres repetidos
            this.mListAuxPrimerosApellidos.clear();
            String primerApellidoActual, primerApellidoAnterior;
            primerApellidoAnterior = this.mListPrimerosApellidos.get(0);
            this.mListAuxPrimerosApellidos.add(primerApellidoAnterior);
            if (this.mListPrimerosApellidos.size() > 1) {
                for (int i = 1; i < this.mListPrimerosApellidos.size(); ++i) {
                    primerApellidoActual = this.mListPrimerosApellidos.get(i);
                    if (!primerApellidoAnterior.equals(primerApellidoActual)) {
                        this.mListAuxPrimerosApellidos.add(primerApellidoActual);
                    }
                    primerApellidoAnterior = primerApellidoActual;
                }
            }

            //adapter primer apellido
            this.mAdapterPrimerApellido = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,
                    this.mListAuxPrimerosApellidos);
            this.mAdapterPrimerApellido.setDropDownViewResource(android.R.layout.simple_list_item_1);
            this.mSpinnerPrimerApellido.setAdapter(this.mAdapterPrimerApellido);
            this.mSpinnerPrimerApellido.setOnItemSelectedListener(this);

            this.mNombre = this.mListAuxNombres.get(position);
        } else if (parent.getAdapter() == this.mAdapterPrimerApellido) {
            this.mListSegundosApellidos.clear();
            for (int i = 0; i < this.mListReparaciones.size(); ++i) {
                if (this.mListReparaciones.get(i).getmPrimerApellido().equals(
                        this.mListAuxPrimerosApellidos.get(position))) {
                    this.mListSegundosApellidos.add(this.mListReparaciones.get(i).getmSegundoApellido());
                }
            }

            //ordenamos la lista por si hubiesen repetidos
            ComparatorStrings compare = new ComparatorStrings();
            Collections.sort(this.mListSegundosApellidos, compare);

            //hacemos una lista auxiliar para que no salgan segundos apellidos repetidos
            this.mListAuxSegundosApellidos.clear();
            String segundoApellidoAnterior, segundoApellidoActual;
            segundoApellidoAnterior = this.mListSegundosApellidos.get(0);
            this.mListAuxSegundosApellidos.add(segundoApellidoAnterior);
            if (this.mListSegundosApellidos.size() > 1) {
                for (int i = 1; i < this.mListSegundosApellidos.size(); ++i) {
                    segundoApellidoActual = this.mListSegundosApellidos.get(i);
                    if (!segundoApellidoAnterior.equals(segundoApellidoActual)) {
                        this.mListAuxSegundosApellidos.add(segundoApellidoActual);
                    }
                    segundoApellidoAnterior = segundoApellidoActual;
                }
            }

            //adapter segundo apellido
            this.mAdapterSegundoApellido = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,
                    this.mListAuxSegundosApellidos);
            this.mAdapterSegundoApellido.setDropDownViewResource(android.R.layout.simple_list_item_1);
            this.mSpinnerSegundoApellido.setAdapter(this.mAdapterSegundoApellido);
            this.mSpinnerSegundoApellido.setOnItemSelectedListener(this);

            this.mPrimerApellido = this.mListAuxPrimerosApellidos.get(position);
        } else if (parent.getAdapter() == this.mAdapterSegundoApellido) {
            this.mListReparacionesPendientes.clear();
            for (int i = 0; i < this.mListReparaciones.size(); ++i) {
                if (this.mListReparaciones.get(i).getmSegundoApellido().equals(
                        this.mListAuxSegundosApellidos.get(position)
                )) {
                    this.mListReparacionesPendientes.add(this.mListReparaciones.get(i).getmResumenEntrada());
                }
            }

            //ordenamos la lista por si hubiesen repetidos
            ComparatorStrings compare = new ComparatorStrings();
            Collections.sort(this.mListReparacionesPendientes, compare);

            //hacemos una lista auxiliar para que no salgan reparaciones repetidas
            this.mListAuxReparacionesPendientes.clear();
            String reparacionPendienteAnterior, reparacionPendienteActual;
            reparacionPendienteAnterior = this.mListReparacionesPendientes.get(0);
            this.mListAuxReparacionesPendientes.add(reparacionPendienteAnterior);
            if (this.mListReparacionesPendientes.size() > 1) {
                for (int i = 1; i < this.mListReparacionesPendientes.size(); ++i) {
                    reparacionPendienteActual = this.mListReparacionesPendientes.get(i);
                    if (!reparacionPendienteAnterior.equals(reparacionPendienteActual)) {
                        this.mListAuxReparacionesPendientes.add(reparacionPendienteActual);
                    }
                    reparacionPendienteAnterior = reparacionPendienteActual;
                }
            }

            //adapter revisiones pendientes
            this.mAdapterReparacionesPendientes = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,
                    this.mListAuxReparacionesPendientes);
            this.mAdapterReparacionesPendientes.setDropDownViewResource(android.R.layout.simple_list_item_1);
            this.mSpinnerReparacionPendiente.setAdapter(this.mAdapterReparacionesPendientes);
            this.mSpinnerReparacionPendiente.setOnItemSelectedListener(this);

            this.mSegundoApellido = this.mListAuxSegundosApellidos.get(position);
        } else if (parent.getAdapter() == this.mAdapterReparacionesPendientes) {
            this.mListMarcas.clear();
            for (int i = 0; i < this.mListReparaciones.size(); ++i) {
                if (this.mListReparaciones.get(i).getmResumenEntrada().equals(
                        this.mListAuxReparacionesPendientes.get(position)
                )) {
                    this.mListMarcas.add(this.mListReparaciones.get(i).getmMarcaVehiculo());
                }
            }

            //ordenamos la lista por si hubiesen repetidos
            ComparatorStrings compare = new ComparatorStrings();
            Collections.sort(this.mListMarcas, compare);

            //Lista auxiliar para que no salgan marcas repetidas para el mismo cliente
            this.mListAuxMarcas.clear();
            String marcaAnterior, marcaActual;
            marcaAnterior = this.mListMarcas.get(0);
            this.mListAuxMarcas.add(marcaAnterior);
            if (this.mListMarcas.size() > 1) {
                for (int i = 1; i < this.mListMarcas.size(); ++i) {
                    marcaActual = this.mListMarcas.get(i);
                    if (!marcaAnterior.equals(marcaActual)) {
                        this.mListAuxMarcas.add(marcaActual);
                    }
                    marcaAnterior = marcaActual;
                }
            }

            //adapter marca vehículo
            this.mAdapterMarca = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,
                    this.mListAuxMarcas);
            this.mAdapterMarca.setDropDownViewResource(android.R.layout.simple_list_item_1);
            this.mSpinnerMarca.setAdapter(this.mAdapterMarca);
            this.mSpinnerMarca.setOnItemSelectedListener(this);

            this.mResumen = this.mListAuxReparacionesPendientes.get(position);
        } else if (parent.getAdapter() == this.mAdapterMarca) {
            this.mListModelos.clear();
            for (int i = 0; i < this.mListReparaciones.size(); ++i) {
                if (this.mListReparaciones.get(i).getmMarcaVehiculo().equals(this.mListAuxMarcas.get(position))) {
                    //sería muy difícil que nos modelos iguales les pasase lo mismo a la vez (?)
                    if (this.mListReparaciones.get(i).getmResumenEntrada().equals(this.mResumen)) {
                        this.mListModelos.add(this.mListReparaciones.get(i).getmModeloVehiculo());
                    }
                }
            }

            //ordenamos la lista por si hubiesen repetidos
            ComparatorStrings compare = new ComparatorStrings();
            Collections.sort(this.mListModelos, compare);

            //Lista auxiliar para que no salgan modelos repetidos para el mismo cliente
            this.mListAuxModelos.clear();
            String modeloAnterior, modeloActual;
            modeloAnterior = this.mListModelos.get(0);
            this.mListAuxModelos.add(modeloAnterior);
            if (this.mListModelos.size() > 1) {
                for (int i = 1; i < this.mListModelos.size(); ++i) {
                    modeloActual = this.mListModelos.get(i);
                    if (!modeloAnterior.equals(modeloActual)) {
                        this.mListAuxModelos.add(modeloActual);
                    }
                    modeloAnterior = modeloActual;
                }
            }

            //adapter modelo vehículo
            this.mAdapterModelo = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,
                    this.mListAuxModelos);
            this.mAdapterModelo.setDropDownViewResource(android.R.layout.simple_list_item_1);
            this.mSpinnerModelo.setAdapter(this.mAdapterModelo);
            this.mSpinnerModelo.setOnItemSelectedListener(this);

            this.mMarcaVehiculo = this.mListMarcas.get(position);
        } else if (parent.getAdapter() == this.mAdapterModelo) {
            this.mListMatricula.clear();
            for (int i = 0; i < this.mListReparaciones.size(); ++i) {
                if (this.mListReparaciones.get(i).getmModeloVehiculo().equals(this.mListAuxModelos.get(position))) {
                    this.mListMatricula.add(this.mListReparaciones.get(i).getmMatriculaVehiculo());
                }
            }

            //ordenamos la lista por si hubiesen repetidos
            ComparatorStrings compare = new ComparatorStrings();
            Collections.sort(this.mListMatricula, compare);

            //Lista auxiliar para que no salgan matrículas repetidas para un mismo cliente
            this.mListAuxMatriculas.clear();
            String matriculaAnterior, matriculaActual;
            matriculaAnterior = this.mListMatricula.get(0);
            this.mListAuxMatriculas.add(matriculaAnterior);
            if (this.mListMatricula.size() > 1) {
                for (int i = 1; i < this.mListMatricula.size(); ++i) {
                    matriculaActual = this.mListMatricula.get(i);
                    if (!matriculaAnterior.equals(matriculaActual)) {
                        this.mListAuxMatriculas.add(matriculaActual);
                    }
                    matriculaAnterior = matriculaActual;
                }
            }

            //adapter matrícula vehículo
            this.mAdapterMatricula = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,
                    this.mListAuxMatriculas);
            this.mAdapterMatricula.setDropDownViewResource(android.R.layout.simple_list_item_1);
            this.mSpinnerMatricula.setAdapter(this.mAdapterMatricula);
            this.mSpinnerMatricula.setOnItemSelectedListener(this);

            this.mModeloVehiculo = this.mListModelos.get(position);
        } else if (parent.getAdapter() == this.mAdapterMatricula) {
            //le añadiremos valores a los campos de texto sobre los datos de la reparación
            //siendo estos la fecha de entrada, el resumen y la descripción, si los hay.
            for (int i = 0; i < this.mListReparaciones.size(); ++i) {
                if (this.mListReparaciones.get(i).getmMatriculaVehiculo().equals(
                        this.mListAuxMatriculas.get(position))) {
                    this.mTextoFechaEntrada.setText(this.mListReparaciones.get(i).getmFechaEntrada());
                    this.mTextoResumen.setText(this.mListReparaciones.get(i).getmResumenEntrada());
                    this.mTextDescripcion.setText(this.mListReparaciones.get(i).getmDescripcionEntrada());
                }
            }

            this.mMatriculaVehiculo = this.mListMatricula.get(position);
            this.mFechaEntrada = this.mTextoFechaEntrada.getText().toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
