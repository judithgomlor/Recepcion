package com.ot.jgomez.recepcion.views.modificacliente;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ot.jgomez.recepcion.R;
import com.ot.jgomez.recepcion.items.ConsultaClientes;
import com.ot.jgomez.recepcion.views.modificacliente.buscacliente.BuscaClienteActivity;

import java.util.ArrayList;
import java.util.List;

public class ModificaClienteActivity extends AppCompatActivity implements
        ModificaClienteContract.View, View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private static final int REQUEST_CODE_OK = 1;
    private static final String EXTRA_NOMBRE = "nombre";
    private static final String EXTRA_PRIMER_APELLIDO = "primer_apellido";
    private static final String EXTRA_SEGUNDO_APELLIDO = "segundo_apellido";
    private static final String EXTRA_TELEFONO = "telefono";

    private ModificaClientePresenterImpl mPresenter;
    private List<ConsultaClientes> mList;
    private Button mBtnAceptar;
    private Button mBtnCancelar;
    private CheckBox mCheckBoxDatosPersonales;
    private CheckBox mCheckBoxAñadirVehiculo;
    private CheckBox mCheckBoxModificarVehiculo;
    private CheckBox mCheckBoxEliminarVehiculo;
    private String mNombre;
    private String mPrimerApellido;
    private String mSegundoApellido;
    private String mTelefono;
    private int mCont;
    private String mPrimeraMarca, mPrimerModelo, mPrimeraMatricula;
    private String mSegundaMarca, mSegundoModelo, mSegundaMatricula;
    private String mTerceraMarca, mTercerModelo, mTerceraMatricula;
    private String mCuartaMarca, mCuartoModelo, mCuartaMatricula;
    private String mQuintaMarca, mQuintoModelo, mQuintaMatricula;
    private String mSextaMarca, mSextoModelo, mSextaMatricula;
    private String mSeptimaMarca, mSeptimoModelo, mSeptimaMatricula;
    private String mOctavaMarca, mOctavoModelo, mOctavaMatricula;
    private String mNovenaMarca, mNovenoModelo, mNovenaMatricula;
    private String mDecimaMarca, mDecimoModelo, mDecimaMatricula;

    /**
     * En el caso de que tenga más de un vehículo la misma persona, tendremos que tener listos los
     * layouts para irlos haciendo visibles e invisibles.
     */
    //datos personales
    private EditText mEditNombreApellidos;
    private EditText mEditTelefono;

    //primer vehículo
    private EditText mEditPrimeraMarcaModelo;
    private EditText mEditPrimeraMatricula;

    //segundo vehículo
    private EditText mEditSegundaMarcaModelo;
    private EditText mEditSegundaMatricula;
    private LinearLayout mLayoutSegundo;

    //tercer vehículo
    private EditText mEditTerceraMarcaModelo;
    private EditText mEditTerceraMatricula;
    private LinearLayout mLayoutTercero;

    //cuarto vehículo
    private EditText mEditCuartaMarcaModelo;
    private EditText mEditCuartaMatricula;
    private LinearLayout mLayoutCuarto;

    //quinto vehículo
    private EditText mEditQuintaMarcaModelo;
    private EditText mEditQuintaMatricula;
    private LinearLayout mLayoutQuinto;

    //sexto vehículo
    private EditText mEditSextaMarcaModelo;
    private EditText mEditSextaMatricula;
    private LinearLayout mLayoutSexto;

    //séptimo vehículo
    private EditText mEditSeptimaMarcaModelo;
    private EditText mEditSeptimaMatricula;
    private LinearLayout mLayoutSeptimo;

    //octavo vehículo
    private EditText mEditOctavaMarcaModelo;
    private EditText mEditOctavaMatricula;
    private LinearLayout mLayoutOctavo;

    //noveno vehículo
    private EditText mEditNovenaMarcaModelo;
    private EditText mEditNovenaMatricula;
    private LinearLayout mLayoutNoveno;

    //décimo vehículo
    private EditText mEditDecimaMarcaModelo;
    private EditText mEditDecimaMatricula;
    private LinearLayout mLayoutDecimo;


    /**
     * Variables para modificar las diferentes opciones
     */
    //Modificar datos personales
    private LinearLayout mLayoutModificaDatosPersonales;
    private EditText mEditModificaNombre;
    private EditText mEditModificaPrimerApellido;
    private EditText mEditModificaSegundoApellido;
    private EditText mEditModificaTelefono;

    //Modificar vehículo --> habrán tantos checkbox como vehículos tenga el cliente
    private LinearLayout mLayoutModificaVehiculos;
    private CheckBox mCheckModificaPrimerVehiculo;
    private LinearLayout mLayoutModificaPrimerVehiculo;
    private EditText mEditModificaPrimeraMarca;
    private EditText mEditModificaPrimerModelo;
    private EditText mEditModificaPrimeraMatricula;

    private CheckBox mCheckModificaSegundoVehiculo;
    private LinearLayout mLayoutModificaSegundoVehiculo;
    private EditText mEditModificaSegundaMarca;
    private EditText mEditModificaSegundoModelo;
    private EditText mEditModificaSegundaMatricula;

    private CheckBox mCheckModificaTercerVehiculo;
    private LinearLayout mLayoutModificaTercerVehiculo;
    private EditText mEditModificaTerceraMarca;
    private EditText mEditModificaTerceroModelo;
    private EditText mEditModificaTerceraMatricula;

    private CheckBox mCheckModificaCuartoVehiculo;
    private LinearLayout mLayoutModificaCuartoVehiculo;
    private EditText mEditModificaCuartaMarca;
    private EditText mEditModificaCuartoModelo;
    private EditText mEditModificaCuartaMatricula;

    private CheckBox mCheckModificaQuintoVehiculo;
    private LinearLayout mLayoutModificaQuintoVehiculo;
    private EditText mEditModificaQuintaMarca;
    private EditText mEditModificaQuintoModelo;
    private EditText mEditModificaQuintaMatricula;

    private CheckBox mCheckModificaSextoVehiculo;
    private LinearLayout mLayoutModificaSextoVehiculo;
    private EditText mEditModificaSextaMarca;
    private EditText mEditModificaSextoModelo;
    private EditText mEditModificaSextaMatricula;

    private CheckBox mCheckModificaSeptimoVehiculo;
    private LinearLayout mLayoutModificaSeptimoVehiculo;
    private EditText mEditModificaSeptimaMarca;
    private EditText mEditModificaSeptimoModelo;
    private EditText mEditModificaSeptimaMatricula;

    private CheckBox mCheckModificaOctavoVehiculo;
    private LinearLayout mLayoutModificaOctavoVehiculo;
    private EditText mEditModificaOctavaMarca;
    private EditText mEditModificaOctavoModelo;
    private EditText mEditModificaOctavaMatricula;

    private CheckBox mCheckModificaNovenoVehiculo;
    private LinearLayout mLayoutModificaNovenoVehiculo;
    private EditText mEditModificaNovenaMarca;
    private EditText mEditModificaNovenoModelo;
    private EditText mEditModificaNovenaMatricula;

    private CheckBox mCheckModificaDecimoVehiculo;
    private LinearLayout mLayoutModificaDecimoVehiculo;
    private EditText mEditModificaDecimaMarca;
    private EditText mEditModificaDecimoModelo;
    private EditText mEditModificaDecimaMatricula;

    //Añadir vehículo --> Campos:
    private LinearLayout mLayoutAñadirVehiculo;
    private EditText mAñadeMarca;
    private EditText mAñadeModelo;
    private EditText mAñadeMatricula;

    //Elimina vehículo --> Campos:
    private LinearLayout mLayoutEliminaVehiculos;
    private CheckBox mCheckEliminaPrimerVehiculo;
    private CheckBox mCheckEliminaSegundoVehiculo;
    private CheckBox mCheckEliminaTercerVehiculo;
    private CheckBox mCheckEliminaCuartoVehiculo;
    private CheckBox mCheckEliminaQuintoVehiculo;
    private CheckBox mCheckEliminaSextoVehiculo;
    private CheckBox mCheckEliminaSeptimoVehiculo;
    private CheckBox mCheckEliminaOctavoVehiculo;
    private CheckBox mCheckEliminaNovenoVehiculo;
    private CheckBox mCheckEliminaDecimoVehiculo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_cliente);

        ActionBar myBar = getSupportActionBar();
        myBar.setHomeButtonEnabled(true);
        myBar.setDisplayHomeAsUpEnabled(true);
        myBar.setDisplayShowHomeEnabled(true);
        myBar.setTitle(R.string.modifica_cliente);

        if (this.mPresenter == null) {
            this.mPresenter = new ModificaClientePresenterImpl();
        }
        this.mPresenter.attach(this, this);

        this.init();
    }

    private void init() {
        this.mBtnAceptar = (Button) findViewById(R.id.boton_aceptar_modificacion_cliente);
        this.mBtnAceptar.setOnClickListener(this);
        this.mBtnCancelar = (Button) findViewById(R.id.boton_cancelar_modificacion_cliente);
        this.mCheckBoxDatosPersonales = (CheckBox) findViewById(R.id.ckbox_modifica_datos_personales);
        this.mCheckBoxDatosPersonales.setOnClickListener(this);
        this.mCheckBoxDatosPersonales.setOnCheckedChangeListener(this);
        this.mCheckBoxAñadirVehiculo = (CheckBox) findViewById(R.id.ckbox_añadir_vehiculos);
        this.mCheckBoxAñadirVehiculo.setOnCheckedChangeListener(this);
        this.mCheckBoxModificarVehiculo = (CheckBox) findViewById(R.id.ckbox_modifica_vehiculos);
        this.mCheckBoxModificarVehiculo.setOnCheckedChangeListener(this);
        this.mCheckBoxEliminarVehiculo = (CheckBox) findViewById(R.id.ckbox_elimina_vehiculos);
        this.mCheckBoxEliminarVehiculo.setOnCheckedChangeListener(this);
        this.mList = new ArrayList<>();
        this.mList = this.mPresenter.getClientesSinTratarLista();

        //datos personales
        this.mEditNombreApellidos = (EditText) findViewById(R.id.edtxt_busca_nombre_apellidos);
        //cuando clickemos un EditText, se abrirá un Dialog con una búsqueda más completa.
        //en principio, solo buscando el nombre será suficiente para elegir el cliente
        this.mEditNombreApellidos.setOnClickListener(this);
        this.mEditTelefono = (EditText) findViewById(R.id.edtxt_busca_telefono);

        //primer vehículo
        this.mEditPrimeraMarcaModelo = (EditText) findViewById(R.id.edtxt_busca_primer_vehiculo);
        this.mEditPrimeraMatricula = (EditText) findViewById(R.id.edtxt_busca_primera_matricula);

        //segundo vehículo
        this.mEditSegundaMarcaModelo = (EditText) findViewById(R.id.edtxt_busca_segundo_vehiculo);
        this.mEditSegundaMatricula = (EditText) findViewById(R.id.edtxt_busca_segunda_matricula);
        this.mLayoutSegundo = (LinearLayout) findViewById(R.id.layout_busca_segundo_vehiculo);
        this.mLayoutSegundo.setVisibility(View.GONE);

        //tercer vehículo
        this.mEditTerceraMarcaModelo = (EditText) findViewById(R.id.edtxt_busca_tercer_vehiculo);
        this.mEditTerceraMatricula = (EditText) findViewById(R.id.edtxt_busca_tercera_matricula);
        this.mLayoutTercero = (LinearLayout) findViewById(R.id.layout_busca_tercer_vehiculo);
        this.mLayoutTercero.setVisibility(View.GONE);

        //cuarto vehículo
        this.mEditCuartaMarcaModelo = (EditText) findViewById(R.id.edtxt_busca_cuarto_vehiculo);
        this.mEditCuartaMatricula = (EditText) findViewById(R.id.edtxt_busca_cuarta_matricula);
        this.mLayoutCuarto = (LinearLayout) findViewById(R.id.layout_busca_cuarto_vehiculo);
        this.mLayoutCuarto.setVisibility(View.GONE);

        //quinto vehículo
        this.mEditQuintaMarcaModelo = (EditText) findViewById(R.id.edtxt_busca_quinto_vehiculo);
        this.mEditQuintaMatricula = (EditText) findViewById(R.id.edtxt_busca_quinta_matricula);
        this.mLayoutQuinto = (LinearLayout) findViewById(R.id.layout_busca_quinto_vehiculo);
        this.mLayoutQuinto.setVisibility(View.GONE);

        //sexto vehículo
        this.mEditSextaMarcaModelo = (EditText) findViewById(R.id.edtxt_busca_sexto_vehiculo);
        this.mEditSextaMatricula = (EditText) findViewById(R.id.edtxt_busca_sexta_matricula);
        this.mLayoutSexto = (LinearLayout) findViewById(R.id.layout_busca_sexto_vehiculo);
        this.mLayoutSexto.setVisibility(View.GONE);

        //séptimo vehículo
        this.mEditSeptimaMarcaModelo = (EditText) findViewById(R.id.edtxt_busca_septimo_vehiculo);
        this.mEditSeptimaMatricula = (EditText) findViewById(R.id.edtxt_busca_septima_matricula);
        this.mLayoutSeptimo = (LinearLayout) findViewById(R.id.layout_busca_septimo_vehiculo);
        this.mLayoutSeptimo.setVisibility(View.GONE);

        //octavo vehículo
        this.mEditOctavaMarcaModelo = (EditText) findViewById(R.id.edtxt_busca_octavo_vehiculo);
        this.mEditOctavaMatricula = (EditText) findViewById(R.id.edtxt_busca_octava_matricula);
        this.mLayoutOctavo = (LinearLayout) findViewById(R.id.layout_busca_octavo_vehiculo);
        this.mLayoutOctavo.setVisibility(View.GONE);

        //noveno vehículo
        this.mEditNovenaMarcaModelo = (EditText) findViewById(R.id.edtxt_busca_noveno_vehiculo);
        this.mEditNovenaMatricula = (EditText) findViewById(R.id.edtxt_busca_novena_matricula);
        this.mLayoutNoveno = (LinearLayout) findViewById(R.id.layout_busca_noveno_vehiculo);
        this.mLayoutNoveno.setVisibility(View.GONE);

        //décimo vehículo
        this.mEditDecimaMarcaModelo = (EditText) findViewById(R.id.edtxt_busca_decimo_vehiculo);
        this.mEditDecimaMatricula = (EditText) findViewById(R.id.edtxt_busca_decima_matricula);
        this.mLayoutDecimo = (LinearLayout) findViewById(R.id.layout_busca_decimo_vehiculo);
        this.mLayoutDecimo.setVisibility(View.GONE);

        //Parte de modificar:
        //Modifica datos personales
        this.mEditModificaNombre = (EditText) findViewById(R.id.modifica_cliente_nuevo_nombre);
        this.mEditModificaPrimerApellido = (EditText) findViewById(R.id.modifica_cliente_nuevo_primer_apellido);
        this.mEditModificaSegundoApellido = (EditText) findViewById(R.id.modifica_cliente_nuevo_segundo_apellido);
        this.mEditModificaTelefono = (EditText) findViewById(R.id.modifica_cliente_nuevo_telefono);
        this.mLayoutModificaDatosPersonales = (LinearLayout) findViewById(R.id.layout_modifica_datos_personales);
        this.mLayoutModificaDatosPersonales.setVisibility(View.GONE);

        //Modifica vehículos
        this.mLayoutModificaVehiculos = (LinearLayout) findViewById(R.id.layout_modifica_vehiculos);
        this.mLayoutModificaVehiculos.setVisibility(View.GONE);
        //Primer vehículo
        this.mCheckModificaPrimerVehiculo = (CheckBox) findViewById(R.id.checkbox_modifica_primer_vehiculo);
        this.mCheckModificaPrimerVehiculo.setOnCheckedChangeListener(this);
        this.mCheckModificaPrimerVehiculo.setVisibility(View.GONE);
        this.mLayoutModificaPrimerVehiculo = (LinearLayout) findViewById(R.id.layout_modifica_primer_vehiculo_modifica_cliente);
        this.mLayoutModificaPrimerVehiculo.setVisibility(View.GONE);
        this.mEditModificaPrimeraMarca = (EditText) findViewById(R.id.editar_marca_modifica_cliente_modifica_primer_vehiculo);
        this.mEditModificaPrimerModelo = (EditText) findViewById(R.id.editar_modelo_modifica_cliente_modifica_primer_vehiculo);
        this.mEditModificaPrimeraMatricula = (EditText) findViewById(R.id.editar_matricula_modifica_cliente_modifica_primer_vehiculo);

        //Segundo vehículo
        this.mCheckModificaSegundoVehiculo = (CheckBox) findViewById(R.id.checkbox_modifica_segundo_vehiculo);
        this.mCheckModificaSegundoVehiculo.setOnCheckedChangeListener(this);
        this.mCheckModificaSegundoVehiculo.setVisibility(View.GONE);
        this.mLayoutModificaSegundoVehiculo = (LinearLayout) findViewById(R.id.layout_modifica_segundo_vehiculo_modifica_cliente);
        this.mLayoutModificaSegundoVehiculo.setVisibility(View.GONE);
        this.mEditModificaSegundaMarca = (EditText) findViewById(R.id.editar_marca_modifica_cliente_modifica_segundo_vehiculo);
        this.mEditModificaSegundoModelo = (EditText) findViewById(R.id.editar_modelo_modifica_cliente_modifica_segundo_vehiculo);
        this.mEditModificaSegundaMatricula = (EditText) findViewById(R.id.editar_matricula_modifica_cliente_modifica_segundo_vehiculo);

        //Tercer vehículo
        this.mCheckModificaTercerVehiculo = (CheckBox) findViewById(R.id.checkbox_modifica_tercer_vehiculo);
        this.mCheckModificaTercerVehiculo.setOnCheckedChangeListener(this);
        this.mCheckModificaTercerVehiculo.setVisibility(View.GONE);
        this.mLayoutModificaTercerVehiculo = (LinearLayout) findViewById(R.id.layout_modifica_tercer_vehiculo_modifica_cliente);
        this.mLayoutModificaTercerVehiculo.setVisibility(View.GONE);
        this.mEditModificaTerceraMarca = (EditText) findViewById(R.id.editar_marca_modifica_cliente_modifica_tercer_vehiculo);
        this.mEditModificaTerceroModelo = (EditText) findViewById(R.id.editar_modelo_modifica_cliente_modifica_tercer_vehiculo);
        this.mEditModificaTerceraMatricula = (EditText) findViewById(R.id.editar_matricula_modifica_cliente_modifica_tercer_vehiculo);

        //Cuarto vehículo
        this.mCheckModificaCuartoVehiculo = (CheckBox) findViewById(R.id.checkbox_modifica_cuarto_vehiculo);
        this.mCheckModificaCuartoVehiculo.setOnCheckedChangeListener(this);
        this.mCheckModificaCuartoVehiculo.setVisibility(View.GONE);
        this.mLayoutModificaCuartoVehiculo = (LinearLayout) findViewById(R.id.layout_modifica_cuarto_vehiculo_modifica_cliente);
        this.mLayoutModificaCuartoVehiculo.setVisibility(View.GONE);
        this.mEditModificaCuartaMarca = (EditText) findViewById(R.id.editar_marca_modifica_cliente_modifica_cuarto_vehiculo);
        this.mEditModificaCuartoModelo = (EditText) findViewById(R.id.editar_modelo_modifica_cliente_modifica_cuarto_vehiculo);
        this.mEditModificaCuartaMatricula = (EditText) findViewById(R.id.editar_matricula_modifica_cliente_modifica_cuarto_vehiculo);

        //Quinto vehículo
        this.mCheckModificaQuintoVehiculo = (CheckBox) findViewById(R.id.checkbox_modifica_quinto_vehiculo);
        this.mCheckModificaQuintoVehiculo.setOnCheckedChangeListener(this);
        this.mCheckModificaQuintoVehiculo.setVisibility(View.GONE);
        this.mLayoutModificaQuintoVehiculo = (LinearLayout) findViewById(R.id.layout_modifica_quinto_vehiculo_modifica_cliente);
        this.mLayoutModificaQuintoVehiculo.setVisibility(View.GONE);
        this.mEditModificaQuintaMarca = (EditText) findViewById(R.id.editar_marca_modifica_cliente_modifica_quinto_vehiculo);
        this.mEditModificaQuintoModelo = (EditText) findViewById(R.id.editar_modelo_modifica_cliente_modifica_quinto_vehiculo);
        this.mEditModificaQuintaMatricula = (EditText) findViewById(R.id.editar_matricula_modifica_cliente_modifica_quinto_vehiculo);

        //Sexto vehículo
        this.mCheckModificaSextoVehiculo = (CheckBox) findViewById(R.id.checkbox_modifica_sexto_vehiculo);
        this.mCheckModificaSextoVehiculo.setOnCheckedChangeListener(this);
        this.mCheckModificaSextoVehiculo.setVisibility(View.GONE);
        this.mLayoutModificaSextoVehiculo = (LinearLayout) findViewById(R.id.layout_modifica_sexto_vehiculo_modifica_cliente);
        this.mLayoutModificaSextoVehiculo.setVisibility(View.GONE);
        this.mEditModificaSextaMarca = (EditText) findViewById(R.id.editar_marca_modifica_cliente_modifica_sexto_vehiculo);
        this.mEditModificaSextoModelo = (EditText) findViewById(R.id.editar_modelo_modifica_cliente_modifica_sexto_vehiculo);
        this.mEditModificaSextaMatricula = (EditText) findViewById(R.id.editar_matricula_modifica_cliente_modifica_sexto_vehiculo);

        //Septimo vehículo
        this.mCheckModificaSeptimoVehiculo = (CheckBox) findViewById(R.id.checkbox_modifica_septimo_vehiculo);
        this.mCheckModificaSeptimoVehiculo.setOnCheckedChangeListener(this);
        this.mCheckModificaSeptimoVehiculo.setVisibility(View.GONE);
        this.mLayoutModificaSeptimoVehiculo = (LinearLayout) findViewById(R.id.layout_modifica_septimo_vehiculo_modifica_cliente);
        this.mLayoutModificaSeptimoVehiculo.setVisibility(View.GONE);
        this.mEditModificaSeptimaMarca = (EditText) findViewById(R.id.editar_marca_modifica_cliente_modifica_septimo_vehiculo);
        this.mEditModificaSeptimoModelo = (EditText) findViewById(R.id.editar_modelo_modifica_cliente_modifica_septimo_vehiculo);
        this.mEditModificaSeptimaMatricula = (EditText) findViewById(R.id.editar_matricula_modifica_cliente_modifica_septimo_vehiculo);

        //Octavo vehículo
        this.mCheckModificaOctavoVehiculo = (CheckBox) findViewById(R.id.checkbox_modifica_octavo_vehiculo);
        this.mCheckModificaOctavoVehiculo.setOnCheckedChangeListener(this);
        this.mCheckModificaOctavoVehiculo.setVisibility(View.GONE);
        this.mLayoutModificaOctavoVehiculo = (LinearLayout) findViewById(R.id.layout_modifica_octavo_vehiculo_modifica_cliente);
        this.mLayoutModificaOctavoVehiculo.setVisibility(View.GONE);
        this.mEditModificaOctavaMarca = (EditText) findViewById(R.id.editar_marca_modifica_cliente_modifica_octavo_vehiculo);
        this.mEditModificaOctavoModelo = (EditText) findViewById(R.id.editar_modelo_modifica_cliente_modifica_octavo_vehiculo);
        this.mEditModificaOctavaMatricula = (EditText) findViewById(R.id.editar_matricula_modifica_cliente_modifica_octavo_vehiculo);

        //Noveno vehículo
        this.mCheckModificaNovenoVehiculo = (CheckBox) findViewById(R.id.checkbox_modifica_noveno_vehiculo);
        this.mCheckModificaNovenoVehiculo.setOnCheckedChangeListener(this);
        this.mCheckModificaNovenoVehiculo.setVisibility(View.GONE);
        this.mLayoutModificaNovenoVehiculo = (LinearLayout) findViewById(R.id.layout_modifica_noveno_vehiculo_modifica_cliente);
        this.mLayoutModificaNovenoVehiculo.setVisibility(View.GONE);
        this.mEditModificaNovenaMarca = (EditText) findViewById(R.id.editar_marca_modifica_cliente_modifica_noveno_vehiculo);
        this.mEditModificaNovenoModelo = (EditText) findViewById(R.id.editar_modelo_modifica_cliente_modifica_noveno_vehiculo);
        this.mEditModificaNovenaMatricula = (EditText) findViewById(R.id.editar_matricula_modifica_cliente_modifica_noveno_vehiculo);

        //Décimo vehículo
        this.mCheckModificaDecimoVehiculo = (CheckBox) findViewById(R.id.checkbox_modifica_decimo_vehiculo);
        this.mCheckModificaDecimoVehiculo.setOnCheckedChangeListener(this);
        this.mCheckModificaDecimoVehiculo.setVisibility(View.GONE);
        this.mLayoutModificaDecimoVehiculo = (LinearLayout) findViewById(R.id.layout_modifica_decimo_vehiculo_modifica_cliente);
        this.mLayoutModificaDecimoVehiculo.setVisibility(View.GONE);
        this.mEditModificaDecimaMarca = (EditText) findViewById(R.id.editar_marca_modifica_cliente_modifica_decimo_vehiculo);
        this.mEditModificaDecimoModelo = (EditText) findViewById(R.id.editar_modelo_modifica_cliente_modifica_decimo_vehiculo);
        this.mEditModificaDecimaMatricula = (EditText) findViewById(R.id.editar_matricula_modifica_cliente_modifica_decimo_vehiculo);

        //Añadir vehículo nuevo
        this.mLayoutAñadirVehiculo = (LinearLayout) findViewById(R.id.layout_añade_vehiculo_modifica_cliente);
        this.mLayoutAñadirVehiculo.setVisibility(View.GONE);
        this.mAñadeMarca = (EditText) findViewById(R.id.editar_nueva_marca_modifica_cliente);
        this.mAñadeModelo = (EditText) findViewById(R.id.editar_nuevo_modelo_modifica_cliente);
        this.mAñadeMatricula = (EditText) findViewById(R.id.editar_nueva_matricula_modifica_cliente);

        //Elimina vehículo
        this.mLayoutEliminaVehiculos = (LinearLayout) findViewById(R.id.layout_elimina_vehiculos);
        this.mLayoutEliminaVehiculos.setVisibility(View.GONE);
        this.mCheckEliminaPrimerVehiculo = (CheckBox) findViewById(R.id.checkbox_elimina_primer_vehiculo);
        this.mCheckEliminaPrimerVehiculo.setOnCheckedChangeListener(this);
        this.mCheckEliminaSegundoVehiculo = (CheckBox) findViewById(R.id.checkbox_elimina_segundo_vehiculo);
        this.mCheckEliminaSegundoVehiculo.setOnCheckedChangeListener(this);
        this.mCheckEliminaTercerVehiculo = (CheckBox) findViewById(R.id.checkbox_elimina_tercer_vehiculo);
        this.mCheckEliminaTercerVehiculo.setOnCheckedChangeListener(this);
        this.mCheckEliminaCuartoVehiculo = (CheckBox) findViewById(R.id.checkbox_elimina_cuarto_vehiculo);
        this.mCheckEliminaCuartoVehiculo.setOnCheckedChangeListener(this);
        this.mCheckEliminaQuintoVehiculo = (CheckBox) findViewById(R.id.checkbox_elimina_quinto_vehiculo);
        this.mCheckEliminaQuintoVehiculo.setOnCheckedChangeListener(this);
        this.mCheckEliminaSextoVehiculo = (CheckBox) findViewById(R.id.checkbox_elimina_sexto_vehiculo);
        this.mCheckEliminaSextoVehiculo.setOnCheckedChangeListener(this);
        this.mCheckEliminaSeptimoVehiculo = (CheckBox) findViewById(R.id.checkbox_elimina_septimo_vehiculo);
        this.mCheckEliminaSeptimoVehiculo.setOnCheckedChangeListener(this);
        this.mCheckEliminaOctavoVehiculo = (CheckBox) findViewById(R.id.checkbox_elimina_octavo_vehiculo);
        this.mCheckEliminaOctavoVehiculo.setOnCheckedChangeListener(this);
        this.mCheckEliminaNovenoVehiculo = (CheckBox) findViewById(R.id.checkbox_elimina_noveno_vehiculo);
        this.mCheckEliminaNovenoVehiculo.setOnCheckedChangeListener(this);
        this.mCheckEliminaDecimoVehiculo = (CheckBox) findViewById(R.id.checkbox_elimina_decimo_vehiculo);
        this.mCheckEliminaDecimoVehiculo.setOnCheckedChangeListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (this.hayCambios()) {
                this.dialogExit();
            } else {
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void fillData(List<ConsultaClientes> list) {
        this.mList = list;
    }

    @Override
    public void setPresenter(ModificaClienteContract.Presenter presenter) {
        this.mPresenter = (ModificaClientePresenterImpl) presenter;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        if (v == this.mBtnAceptar) {
            //guardar cambios
            this.guardaCambios();
        } else if (v == this.mBtnCancelar) {
            //cancelar cambios -->  si se ha modificado algo, preguntar si se quiere ir sin guardar
            //                      si no hay cambios nuevos, NP
            if (this.hayCambios()) {
                this.dialogExit();
            } else {
                finish();
            }
        } else if (v == this.mEditNombreApellidos) {
            //iremos a una pantalla nueva para que sea más fácil el flujo de información, pero sería
            // lo mismo.
            Intent myIntent = new Intent(this, BuscaClienteActivity.class);
            startActivityForResult(myIntent, this.REQUEST_CODE_OK);
        }
    }

    private void guardaCambios() {

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

    private boolean hayCambios() {
        if (this.mCheckBoxDatosPersonales.isChecked()) {
            if (!this.mNombre.equals(this.mEditModificaNombre.getText()) ||
                    !this.mPrimerApellido.equals(this.mEditModificaPrimerApellido.getText()) ||
                    !this.mSegundoApellido.equals(this.mEditModificaSegundoApellido.getText()) ||
                    !this.mTelefono.equals(this.mEditModificaTelefono.getText())) {
                return true;
            }
        } else if (this.mCheckBoxEliminarVehiculo.isChecked()) {
            if (this.mCheckEliminaPrimerVehiculo.isChecked() || this.mCheckEliminaSegundoVehiculo.isChecked() ||
                    this.mCheckEliminaTercerVehiculo.isChecked() || this.mCheckEliminaCuartoVehiculo.isChecked() ||
                    this.mCheckEliminaQuintoVehiculo.isChecked() || this.mCheckEliminaSextoVehiculo.isChecked() ||
                    this.mCheckEliminaSeptimoVehiculo.isChecked() || this.mCheckEliminaOctavoVehiculo.isChecked() ||
                    this.mCheckEliminaNovenoVehiculo.isChecked() || this.mCheckEliminaCuartoVehiculo.isChecked()) {
                return true;
            }
        } else if (this.mCheckBoxModificarVehiculo.isChecked()) {
            if (this.mCheckModificaPrimerVehiculo.isChecked()) {
                if (!this.mEditModificaPrimeraMarca.getText().toString().equals("") ||
                        !this.mEditModificaPrimerModelo.getText().toString().equals("") ||
                        !this.mEditModificaPrimeraMatricula.getText().toString().equals("")) {
                    return true;
                }
            } else if (this.mCheckModificaSegundoVehiculo.isChecked()) {
                if (!this.mEditModificaSegundaMarca.getText().toString().equals("") ||
                        !this.mEditModificaSegundoModelo.getText().toString().equals("") ||
                        !this.mEditModificaSegundaMatricula.getText().toString().equals("")) {
                    return true;
                }
            } else if (this.mCheckModificaTercerVehiculo.isChecked()) {
                if (!this.mEditModificaTerceraMarca.getText().toString().equals("") ||
                        !this.mEditModificaTerceroModelo.getText().toString().equals("") ||
                        !this.mEditModificaTerceraMatricula.getText().toString().equals("")) {
                    return true;
                }
            } else if (this.mCheckModificaCuartoVehiculo.isChecked()) {
                if (!this.mEditModificaCuartaMarca.getText().toString().equals("") ||
                        !this.mEditModificaCuartoModelo.getText().toString().equals("") ||
                        !this.mEditModificaCuartaMatricula.getText().toString().equals("")) {
                    return true;
                }
            } else if (this.mCheckModificaQuintoVehiculo.isChecked()) {
                if (!this.mEditModificaQuintaMarca.getText().toString().equals("") ||
                        !this.mEditModificaQuintoModelo.getText().toString().equals("") ||
                        !this.mEditModificaQuintaMatricula.getText().toString().equals("")) {
                    return true;
                }
            } else if (this.mCheckModificaSextoVehiculo.isChecked()) {
                if (!this.mEditModificaSextaMarca.getText().toString().equals("") ||
                        !this.mEditModificaSextoModelo.getText().toString().equals("") ||
                        !this.mEditModificaSextaMatricula.getText().toString().equals("")) {
                    return true;
                }
            } else if (this.mCheckModificaSeptimoVehiculo.isChecked()) {
                if (!this.mEditModificaSeptimaMarca.getText().toString().equals("") ||
                        !this.mEditModificaSeptimoModelo.getText().toString().equals("") ||
                        !this.mEditModificaSeptimaMatricula.getText().toString().equals("")) {
                    return true;
                }
            } else if (this.mCheckModificaOctavoVehiculo.isChecked()) {
                if (!this.mEditModificaOctavaMarca.getText().toString().equals("") ||
                        !this.mEditModificaOctavoModelo.getText().toString().equals("") ||
                        !this.mEditModificaOctavaMatricula.getText().toString().equals("")) {
                    return true;
                }
            } else if (this.mCheckModificaNovenoVehiculo.isChecked()) {
                if (!this.mEditModificaNovenaMarca.getText().toString().equals("") ||
                        !this.mEditModificaNovenoModelo.getText().toString().equals("") ||
                        !this.mEditModificaNovenaMatricula.getText().toString().equals("")) {
                    return true;
                }
            } else if (this.mCheckModificaDecimoVehiculo.isChecked()) {
                if (!this.mEditModificaDecimaMarca.getText().toString().equals("") ||
                        !this.mEditModificaDecimoModelo.getText().toString().equals("") ||
                        !this.mEditModificaDecimaMatricula.getText().toString().equals("")) {
                    return true;
                }
            }
        } else if (this.mCheckBoxAñadirVehiculo.isChecked()) {
            if (!this.mAñadeMarca.getText().toString().equals("") ||
                    !this.mAñadeModelo.getText().toString().equals("") ||
                    !this.mAñadeMatricula.getText().toString().equals("")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView == this.mCheckBoxAñadirVehiculo) {
            if (isChecked) {
                this.mLayoutAñadirVehiculo.setVisibility(View.VISIBLE);
            } else {
                this.mLayoutAñadirVehiculo.setVisibility(View.GONE);
            }
        } else if (buttonView == this.mCheckBoxDatosPersonales) {
            if (isChecked) {
                this.mLayoutModificaDatosPersonales.setVisibility(View.VISIBLE);
                this.mEditModificaNombre.setText(this.mNombre);
                this.mEditModificaPrimerApellido.setText(this.mPrimerApellido);
                this.mEditModificaSegundoApellido.setText(this.mSegundoApellido);
                this.mEditModificaTelefono.setText(this.mTelefono);
            } else {
                this.mLayoutModificaDatosPersonales.setVisibility(View.GONE);
            }
        } else if (buttonView == this.mCheckBoxEliminarVehiculo) {
            if (isChecked) {
                this.mLayoutEliminaVehiculos.setVisibility(View.VISIBLE);
            } else {
                this.mLayoutEliminaVehiculos.setVisibility(View.GONE);
            }
        } else if (buttonView == this.mCheckEliminaPrimerVehiculo) {
            if (isChecked) {
                //función para eliminar el vehículo correspondiente
            }
        } else if (buttonView == this.mCheckEliminaSegundoVehiculo) {
            if (isChecked) {
                //función para eliminar el vehículo correspondiente
            }
        } else if (buttonView == this.mCheckEliminaTercerVehiculo) {
            if (isChecked) {
                //función para eliminar el vehículo correspondiente
            }
        } else if (buttonView == this.mCheckEliminaCuartoVehiculo) {
            if (isChecked) {
                //función para eliminar el vehículo correspondiente
            }
        } else if (buttonView == this.mCheckEliminaQuintoVehiculo) {
            if (isChecked) {
                //función para eliminar el vehículo correspondiente
            }
        } else if (buttonView == this.mCheckEliminaSextoVehiculo) {
            if (isChecked) {
                //función para eliminar el vehículo correspondiente
            }
        } else if (buttonView == this.mCheckEliminaSeptimoVehiculo) {
            if (isChecked) {
                //función para eliminar el vehículo correspondiente
            }
        } else if (buttonView == this.mCheckEliminaOctavoVehiculo) {
            if (isChecked) {
                //función para eliminar el vehículo correspondiente
            }
        } else if (buttonView == this.mCheckEliminaNovenoVehiculo) {
            if (isChecked) {
                //función para eliminar el vehículo correspondiente
            }
        } else if (buttonView == this.mCheckEliminaDecimoVehiculo) {
            if (isChecked) {
                //función para eliminar el vehículo correspondiente
            }
        } else if (buttonView == this.mCheckBoxModificarVehiculo) {
            if (isChecked) {
                this.mLayoutModificaVehiculos.setVisibility(View.VISIBLE);
            } else {
                this.mLayoutModificaVehiculos.setVisibility(View.GONE);
            }
        } else if (buttonView == this.mCheckModificaPrimerVehiculo) {
            if (isChecked) {
                this.mLayoutModificaPrimerVehiculo.setVisibility(View.VISIBLE);
                this.mEditModificaPrimeraMarca.setText(this.mPrimeraMarca);
                this.mEditModificaPrimerModelo.setText(this.mPrimerModelo);
                this.mEditModificaPrimeraMatricula.setText(this.mPrimeraMatricula);
            } else {
                this.mLayoutModificaPrimerVehiculo.setVisibility(View.GONE);
            }
        } else if (buttonView == this.mCheckModificaSegundoVehiculo) {
            if (isChecked) {
                this.mLayoutModificaSegundoVehiculo.setVisibility(View.VISIBLE);
                this.mEditModificaSegundaMarca.setText(this.mSegundaMarca);
                this.mEditModificaSegundoModelo.setText(this.mSegundoModelo);
                this.mEditModificaSegundaMatricula.setText(this.mSegundaMatricula);
            } else {
                this.mLayoutModificaSegundoVehiculo.setVisibility(View.GONE);
            }
        } else if (buttonView == this.mCheckModificaTercerVehiculo) {
            if (isChecked) {
                this.mLayoutModificaTercerVehiculo.setVisibility(View.VISIBLE);
                this.mEditModificaTerceraMarca.setText(this.mTerceraMarca);
                this.mEditModificaTerceroModelo.setText(this.mTercerModelo);
                this.mEditModificaTerceraMatricula.setText(this.mTerceraMatricula);
            } else {
                this.mLayoutModificaTercerVehiculo.setVisibility(View.GONE);
            }
        } else if (buttonView == this.mCheckModificaCuartoVehiculo) {
            if (isChecked) {
                this.mLayoutModificaCuartoVehiculo.setVisibility(View.VISIBLE);
                this.mEditModificaCuartaMarca.setText(this.mCuartaMarca);
                this.mEditModificaCuartoModelo.setText(this.mCuartoModelo);
                this.mEditModificaCuartaMatricula.setText(this.mCuartaMatricula);
            } else {
                this.mLayoutModificaCuartoVehiculo.setVisibility(View.GONE);
            }
        } else if (buttonView == this.mCheckModificaQuintoVehiculo) {
            if (isChecked) {
                this.mLayoutModificaQuintoVehiculo.setVisibility(View.VISIBLE);
                this.mEditModificaQuintaMarca.setText(this.mQuintaMarca);
                this.mEditModificaQuintoModelo.setText(this.mQuintoModelo);
                this.mEditModificaQuintaMatricula.setText(this.mQuintaMatricula);
            } else {
                this.mLayoutModificaQuintoVehiculo.setVisibility(View.GONE);
            }
        } else if (buttonView == this.mCheckModificaSextoVehiculo) {
            if (isChecked) {
                this.mLayoutModificaSextoVehiculo.setVisibility(View.VISIBLE);
                this.mEditModificaSextaMarca.setText(this.mSextaMarca);
                this.mEditModificaSextoModelo.setText(this.mSextoModelo);
                this.mEditModificaSextaMatricula.setText(this.mSextaMatricula);
            } else {
                this.mLayoutModificaSextoVehiculo.setVisibility(View.GONE);
            }
        } else if (buttonView == this.mCheckModificaSeptimoVehiculo) {
            if (isChecked) {
                this.mLayoutModificaSeptimoVehiculo.setVisibility(View.VISIBLE);
                this.mEditModificaSeptimaMarca.setText(this.mSeptimaMarca);
                this.mEditModificaSeptimoModelo.setText(this.mSeptimoModelo);
                this.mEditModificaSeptimaMatricula.setText(this.mSeptimaMatricula);
            } else {
                this.mLayoutModificaSeptimoVehiculo.setVisibility(View.GONE);
            }
        } else if (buttonView == this.mCheckModificaOctavoVehiculo) {
            if (isChecked) {
                this.mLayoutModificaOctavoVehiculo.setVisibility(View.VISIBLE);
                this.mEditModificaOctavaMarca.setText(this.mOctavaMarca);
                this.mEditModificaOctavoModelo.setText(this.mOctavoModelo);
                this.mEditModificaOctavaMatricula.setText(this.mOctavaMatricula);
            } else {
                this.mLayoutModificaOctavoVehiculo.setVisibility(View.GONE);
            }
        } else if (buttonView == this.mCheckModificaNovenoVehiculo) {
            if (isChecked) {
                this.mLayoutModificaNovenoVehiculo.setVisibility(View.VISIBLE);
                this.mEditModificaNovenaMarca.setText(this.mNovenaMarca);
                this.mEditModificaNovenoModelo.setText(this.mNovenoModelo);
                this.mEditModificaNovenaMatricula.setText(this.mNovenaMatricula);
            } else {
                this.mLayoutModificaNovenoVehiculo.setVisibility(View.GONE);
            }
        } else if (buttonView == this.mCheckModificaDecimoVehiculo) {
            if (isChecked) {
                this.mLayoutModificaDecimoVehiculo.setVisibility(View.VISIBLE);
                this.mEditModificaDecimaMarca.setText(this.mDecimaMarca);
                this.mEditModificaDecimoModelo.setText(this.mDecimoModelo);
                this.mEditModificaDecimaMatricula.setText(this.mDecimaMatricula);
            } else {
                this.mLayoutModificaDecimoVehiculo.setVisibility(View.GONE);
            }
        }
    }

    private void consultaCampos() {
        ConsultaClientes cliente;
        List<ConsultaClientes> listVehiculosClientes = new ArrayList<>();
        this.mCont = 0;
        for (int i = 0; i < this.mList.size(); ++i) {
            cliente = this.mList.get(i);
            if (cliente.getmNombre().equals(this.mNombre) &&
                    cliente.getmPrimerApellido().equals(this.mPrimerApellido) &&
                    cliente.getmSegundoApellido().equals(this.mSegundoApellido) &&
                    cliente.getmTelefono().equals(this.mTelefono)) {
                this.mCont++;
                listVehiculosClientes.add(cliente);
            }
        }
        this.rellenaCampos(listVehiculosClientes, this.mCont);
    }

    private void rellenaCampos(List<ConsultaClientes> listVehiculosClientes, int cont) {
        switch (cont) {
            case 1:
                this.rellenaPrimerVehiculo(listVehiculosClientes);
                break;
            case 2:
                this.rellenaPrimerVehiculo(listVehiculosClientes);
                this.rellenaSegundoVehiculo(listVehiculosClientes);
                break;
            case 3:
                this.rellenaPrimerVehiculo(listVehiculosClientes);
                this.rellenaSegundoVehiculo(listVehiculosClientes);
                this.rellenaTercerVehiculo(listVehiculosClientes);
                break;
            case 4:
                this.rellenaPrimerVehiculo(listVehiculosClientes);
                this.rellenaSegundoVehiculo(listVehiculosClientes);
                this.rellenaTercerVehiculo(listVehiculosClientes);
                this.rellenaCuartoVehiculo(listVehiculosClientes);
                break;
            case 5:
                this.rellenaPrimerVehiculo(listVehiculosClientes);
                this.rellenaSegundoVehiculo(listVehiculosClientes);
                this.rellenaTercerVehiculo(listVehiculosClientes);
                this.rellenaCuartoVehiculo(listVehiculosClientes);
                this.rellenaQuintoVehiculo(listVehiculosClientes);
                break;
            case 6:
                this.rellenaPrimerVehiculo(listVehiculosClientes);
                this.rellenaSegundoVehiculo(listVehiculosClientes);
                this.rellenaTercerVehiculo(listVehiculosClientes);
                this.rellenaCuartoVehiculo(listVehiculosClientes);
                this.rellenaQuintoVehiculo(listVehiculosClientes);
                this.rellenaSextoVehiculo(listVehiculosClientes);
                break;
            case 7:
                this.rellenaPrimerVehiculo(listVehiculosClientes);
                this.rellenaSegundoVehiculo(listVehiculosClientes);
                this.rellenaTercerVehiculo(listVehiculosClientes);
                this.rellenaCuartoVehiculo(listVehiculosClientes);
                this.rellenaQuintoVehiculo(listVehiculosClientes);
                this.rellenaSextoVehiculo(listVehiculosClientes);
                this.rellenaSeptimoVehiculo(listVehiculosClientes);
                break;
            case 8:
                this.rellenaPrimerVehiculo(listVehiculosClientes);
                this.rellenaSegundoVehiculo(listVehiculosClientes);
                this.rellenaTercerVehiculo(listVehiculosClientes);
                this.rellenaCuartoVehiculo(listVehiculosClientes);
                this.rellenaQuintoVehiculo(listVehiculosClientes);
                this.rellenaSextoVehiculo(listVehiculosClientes);
                this.rellenaSeptimoVehiculo(listVehiculosClientes);
                this.rellenaOctavoVehiculo(listVehiculosClientes);
                break;
            case 9:
                this.rellenaPrimerVehiculo(listVehiculosClientes);
                this.rellenaSegundoVehiculo(listVehiculosClientes);
                this.rellenaTercerVehiculo(listVehiculosClientes);
                this.rellenaCuartoVehiculo(listVehiculosClientes);
                this.rellenaQuintoVehiculo(listVehiculosClientes);
                this.rellenaSextoVehiculo(listVehiculosClientes);
                this.rellenaSeptimoVehiculo(listVehiculosClientes);
                this.rellenaOctavoVehiculo(listVehiculosClientes);
                this.rellenaNovenoVehiculo(listVehiculosClientes);
                break;
            case 10:
                this.rellenaPrimerVehiculo(listVehiculosClientes);
                this.rellenaSegundoVehiculo(listVehiculosClientes);
                this.rellenaTercerVehiculo(listVehiculosClientes);
                this.rellenaCuartoVehiculo(listVehiculosClientes);
                this.rellenaQuintoVehiculo(listVehiculosClientes);
                this.rellenaSextoVehiculo(listVehiculosClientes);
                this.rellenaSeptimoVehiculo(listVehiculosClientes);
                this.rellenaOctavoVehiculo(listVehiculosClientes);
                this.rellenaNovenoVehiculo(listVehiculosClientes);
                this.rellenaDecimoVehiculo(listVehiculosClientes);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == this.RESULT_OK) {
            this.mCont = 0;
            this.mNombre = data.getStringExtra(this.EXTRA_NOMBRE);
            this.mPrimerApellido = data.getStringExtra(this.EXTRA_PRIMER_APELLIDO);
            this.mSegundoApellido = data.getStringExtra(this.EXTRA_SEGUNDO_APELLIDO);
            String nombre_apellidos = this.mNombre + " " + this.mPrimerApellido + " "
                    + this.mSegundoApellido;
            this.mTelefono = data.getStringExtra(this.EXTRA_TELEFONO);
            this.mEditNombreApellidos.setText(nombre_apellidos);
            this.mEditTelefono.setText(this.mTelefono);
            this.reseteaCamposAModificar();
            this.reseteaCheckBox();
            this.consultaCampos();
        } else if (resultCode == this.RESULT_CANCELED) {
            if (this.mEditNombreApellidos.getText().length() == 0 &&
                    this.mEditTelefono.getText().length() == 0) {
                this.mEditNombreApellidos.setText("");
                this.mEditTelefono.setText("");
                this.mEditPrimeraMarcaModelo.setText("");
                this.mEditPrimeraMatricula.setText("");
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void reseteaCheckBox() {
        this.mCheckBoxAñadirVehiculo.setChecked(false);
        this.mCheckBoxDatosPersonales.setChecked(false);
        this.mCheckBoxEliminarVehiculo.setChecked(false);
        this.mCheckBoxModificarVehiculo.setChecked(false);

        //checkbox modifica
        this.mCheckModificaPrimerVehiculo.setChecked(false);
        this.mCheckModificaPrimerVehiculo.setText("");
        this.mCheckModificaPrimerVehiculo.setVisibility(View.GONE);
        this.mCheckModificaSegundoVehiculo.setChecked(false);
        this.mCheckModificaSegundoVehiculo.setText("");
        this.mCheckModificaSegundoVehiculo.setVisibility(View.GONE);
        this.mCheckModificaTercerVehiculo.setChecked(false);
        this.mCheckModificaTercerVehiculo.setText("");
        this.mCheckModificaTercerVehiculo.setVisibility(View.GONE);
        this.mCheckModificaCuartoVehiculo.setChecked(false);
        this.mCheckModificaCuartoVehiculo.setText("");
        this.mCheckModificaCuartoVehiculo.setVisibility(View.GONE);
        this.mCheckModificaQuintoVehiculo.setChecked(false);
        this.mCheckModificaQuintoVehiculo.setText("");
        this.mCheckModificaQuintoVehiculo.setVisibility(View.GONE);
        this.mCheckModificaSextoVehiculo.setChecked(false);
        this.mCheckModificaSextoVehiculo.setText("");
        this.mCheckModificaSextoVehiculo.setVisibility(View.GONE);
        this.mCheckModificaSeptimoVehiculo.setChecked(false);
        this.mCheckModificaSeptimoVehiculo.setText("");
        this.mCheckModificaSeptimoVehiculo.setVisibility(View.GONE);
        this.mCheckModificaOctavoVehiculo.setChecked(false);
        this.mCheckModificaOctavoVehiculo.setText("");
        this.mCheckModificaOctavoVehiculo.setVisibility(View.GONE);
        this.mCheckModificaNovenoVehiculo.setChecked(false);
        this.mCheckModificaNovenoVehiculo.setText("");
        this.mCheckModificaNovenoVehiculo.setVisibility(View.GONE);
        this.mCheckModificaDecimoVehiculo.setChecked(false);
        this.mCheckModificaDecimoVehiculo.setText("");
        this.mCheckModificaDecimoVehiculo.setVisibility(View.GONE);

        //checkbox elimina
        this.mCheckEliminaPrimerVehiculo.setChecked(false);
        this.mCheckEliminaPrimerVehiculo.setText("");
        this.mCheckEliminaPrimerVehiculo.setVisibility(View.GONE);
        this.mCheckEliminaSegundoVehiculo.setChecked(false);
        this.mCheckEliminaSegundoVehiculo.setText("");
        this.mCheckEliminaSegundoVehiculo.setVisibility(View.GONE);
        this.mCheckEliminaTercerVehiculo.setChecked(false);
        this.mCheckEliminaTercerVehiculo.setText("");
        this.mCheckEliminaTercerVehiculo.setVisibility(View.GONE);
        this.mCheckEliminaCuartoVehiculo.setChecked(false);
        this.mCheckEliminaCuartoVehiculo.setText("");
        this.mCheckEliminaCuartoVehiculo.setVisibility(View.GONE);
        this.mCheckEliminaQuintoVehiculo.setChecked(false);
        this.mCheckEliminaQuintoVehiculo.setText("");
        this.mCheckEliminaQuintoVehiculo.setVisibility(View.GONE);
        this.mCheckEliminaSextoVehiculo.setChecked(false);
        this.mCheckEliminaSextoVehiculo.setText("");
        this.mCheckEliminaSextoVehiculo.setVisibility(View.GONE);
        this.mCheckEliminaSeptimoVehiculo.setChecked(false);
        this.mCheckEliminaSeptimoVehiculo.setText("");
        this.mCheckEliminaSeptimoVehiculo.setVisibility(View.GONE);
        this.mCheckEliminaOctavoVehiculo.setChecked(false);
        this.mCheckEliminaOctavoVehiculo.setText("");
        this.mCheckEliminaOctavoVehiculo.setVisibility(View.GONE);
        this.mCheckEliminaNovenoVehiculo.setChecked(false);
        this.mCheckEliminaNovenoVehiculo.setText("");
        this.mCheckEliminaNovenoVehiculo.setVisibility(View.GONE);
        this.mCheckEliminaDecimoVehiculo.setChecked(false);
        this.mCheckEliminaDecimoVehiculo.setText("");
        this.mCheckEliminaDecimoVehiculo.setVisibility(View.GONE);
    }

    private void reseteaCamposAModificar() {
        this.borraCampos(this.mEditModificaPrimeraMarca, this.mEditModificaPrimerModelo,
                this.mEditModificaPrimeraMatricula);
        this.borraCampos(this.mEditModificaSegundaMarca, this.mEditModificaSegundoModelo,
                this.mEditModificaSegundaMatricula);
        this.borraCampos(this.mEditModificaTerceraMarca, this.mEditModificaTerceroModelo,
                this.mEditModificaTerceraMatricula);
        this.borraCampos(this.mEditModificaCuartaMarca, this.mEditModificaCuartoModelo,
                this.mEditModificaCuartaMatricula);
        this.borraCampos(this.mEditModificaQuintaMarca, this.mEditModificaQuintoModelo,
                this.mEditModificaQuintaMatricula);
        this.borraCampos(this.mEditModificaSextaMarca, this.mEditModificaSextoModelo,
                this.mEditModificaSextaMatricula);
        this.borraCampos(this.mEditModificaSeptimaMarca, this.mEditModificaSeptimoModelo,
                this.mEditModificaSeptimaMatricula);
        this.borraCampos(this.mEditModificaOctavaMarca, this.mEditModificaOctavoModelo,
                this.mEditModificaOctavaMatricula);
        this.borraCampos(this.mEditModificaNovenaMarca, this.mEditModificaNovenoModelo,
                this.mEditModificaNovenaMatricula);
        this.borraCampos(this.mEditModificaDecimaMarca, this.mEditModificaDecimoModelo,
                this.mEditModificaDecimaMatricula);
    }

    private void borraCampos(EditText marca, EditText modelo, EditText matricula) {
        marca.setText("");
        modelo.setText("");
        matricula.setText("");
    }

    private void rellenaPrimerVehiculo(List<ConsultaClientes> listVehiculosClientes) {
        this.mPrimeraMarca = listVehiculosClientes.get(0).getmMarcaVehiculo();
        this.mPrimerModelo = listVehiculosClientes.get(0).getmModeloVehiculo();
        this.mPrimeraMatricula = listVehiculosClientes.get(0).getmMatriculaVehiculo();
        String primeraMarcaModelo = this.mPrimeraMarca + " - " + this.mPrimerModelo;
        this.mEditPrimeraMarcaModelo.setText(primeraMarcaModelo);
        this.mEditPrimeraMatricula.setText(this.mPrimeraMatricula);
        this.mLayoutSegundo.setVisibility(View.GONE);
        this.mLayoutTercero.setVisibility(View.GONE);
        this.mLayoutCuarto.setVisibility(View.GONE);
        this.mLayoutQuinto.setVisibility(View.GONE);
        this.mLayoutSexto.setVisibility(View.GONE);
        this.mLayoutSeptimo.setVisibility(View.GONE);
        this.mLayoutOctavo.setVisibility(View.GONE);
        this.mLayoutNoveno.setVisibility(View.GONE);
        this.mLayoutDecimo.setVisibility(View.GONE);

        //Modificar Vehículo
        this.mCheckModificaPrimerVehiculo.setVisibility(View.VISIBLE);
        this.mCheckModificaPrimerVehiculo.setText(primeraMarcaModelo);
        this.mCheckModificaPrimerVehiculo.setOnCheckedChangeListener(this);

        //Eliminar Vehículo
        this.mCheckEliminaPrimerVehiculo.setVisibility(View.VISIBLE);
        this.mCheckEliminaPrimerVehiculo.setText(primeraMarcaModelo);
        this.mCheckEliminaPrimerVehiculo.setOnCheckedChangeListener(this);

        this.mCheckEliminaSegundoVehiculo.setVisibility(View.GONE);
        this.mCheckEliminaTercerVehiculo.setVisibility(View.GONE);
        this.mCheckEliminaCuartoVehiculo.setVisibility(View.GONE);
        this.mCheckEliminaQuintoVehiculo.setVisibility(View.GONE);
        this.mCheckEliminaSextoVehiculo.setVisibility(View.GONE);
        this.mCheckEliminaSeptimoVehiculo.setVisibility(View.GONE);
        this.mCheckEliminaOctavoVehiculo.setVisibility(View.GONE);
        this.mCheckEliminaNovenoVehiculo.setVisibility(View.GONE);
        this.mCheckEliminaDecimoVehiculo.setVisibility(View.GONE);
    }

    private void rellenaSegundoVehiculo(List<ConsultaClientes> listVehiculosClientes) {
        this.mSegundaMarca = listVehiculosClientes.get(1).getmMarcaVehiculo();
        this.mSegundoModelo = listVehiculosClientes.get(1).getmModeloVehiculo();
        this.mSegundaMatricula = listVehiculosClientes.get(1).getmMatriculaVehiculo();
        String segundaMarcaModelo = this.mSegundaMarca + " - " + this.mSegundoModelo;
        this.mEditSegundaMarcaModelo.setText(segundaMarcaModelo);
        this.mEditSegundaMatricula.setText(this.mSegundaMatricula);
        this.mLayoutSegundo.setVisibility(View.VISIBLE);
        this.mLayoutTercero.setVisibility(View.GONE);
        this.mLayoutCuarto.setVisibility(View.GONE);
        this.mLayoutQuinto.setVisibility(View.GONE);
        this.mLayoutSexto.setVisibility(View.GONE);
        this.mLayoutSeptimo.setVisibility(View.GONE);
        this.mLayoutOctavo.setVisibility(View.GONE);
        this.mLayoutNoveno.setVisibility(View.GONE);
        this.mLayoutDecimo.setVisibility(View.GONE);

        //Modificar Vehículo
        this.mCheckModificaSegundoVehiculo.setVisibility(View.VISIBLE);
        this.mCheckModificaSegundoVehiculo.setText(segundaMarcaModelo);
        this.mCheckModificaSegundoVehiculo.setOnCheckedChangeListener(this);

        //Eliminar Vehículo
        this.mCheckEliminaSegundoVehiculo.setVisibility(View.VISIBLE);
        this.mCheckEliminaSegundoVehiculo.setText(segundaMarcaModelo);
        this.mCheckEliminaSegundoVehiculo.setOnCheckedChangeListener(this);

        this.mCheckEliminaTercerVehiculo.setVisibility(View.GONE);
        this.mCheckEliminaCuartoVehiculo.setVisibility(View.GONE);
        this.mCheckEliminaQuintoVehiculo.setVisibility(View.GONE);
        this.mCheckEliminaSextoVehiculo.setVisibility(View.GONE);
        this.mCheckEliminaSeptimoVehiculo.setVisibility(View.GONE);
        this.mCheckEliminaOctavoVehiculo.setVisibility(View.GONE);
        this.mCheckEliminaNovenoVehiculo.setVisibility(View.GONE);
        this.mCheckEliminaDecimoVehiculo.setVisibility(View.GONE);
    }

    private void rellenaTercerVehiculo(List<ConsultaClientes> listVehiculosClientes) {
        this.mTerceraMarca = listVehiculosClientes.get(2).getmMarcaVehiculo();
        this.mTercerModelo = listVehiculosClientes.get(2).getmModeloVehiculo();
        this.mTerceraMatricula = listVehiculosClientes.get(2).getmMatriculaVehiculo();
        String terceraMarcaModelo = this.mTerceraMarca + " - " + this.mTercerModelo;
        this.mEditTerceraMarcaModelo.setText(terceraMarcaModelo);
        this.mEditTerceraMatricula.setText(this.mTerceraMatricula);
        this.mLayoutTercero.setVisibility(View.VISIBLE);
        this.mLayoutCuarto.setVisibility(View.GONE);
        this.mLayoutQuinto.setVisibility(View.GONE);
        this.mLayoutSexto.setVisibility(View.GONE);
        this.mLayoutSeptimo.setVisibility(View.GONE);
        this.mLayoutOctavo.setVisibility(View.GONE);
        this.mLayoutNoveno.setVisibility(View.GONE);
        this.mLayoutDecimo.setVisibility(View.GONE);

        //Modificar Vehículo
        this.mCheckModificaTercerVehiculo.setVisibility(View.VISIBLE);
        this.mCheckModificaTercerVehiculo.setText(terceraMarcaModelo);
        this.mCheckModificaTercerVehiculo.setOnCheckedChangeListener(this);
        //Eliminar Vehículo
        this.mCheckEliminaTercerVehiculo.setVisibility(View.VISIBLE);
        this.mCheckEliminaTercerVehiculo.setText(terceraMarcaModelo);
        this.mCheckEliminaTercerVehiculo.setOnCheckedChangeListener(this);

        this.mCheckEliminaCuartoVehiculo.setVisibility(View.GONE);
        this.mCheckEliminaQuintoVehiculo.setVisibility(View.GONE);
        this.mCheckEliminaSextoVehiculo.setVisibility(View.GONE);
        this.mCheckEliminaSeptimoVehiculo.setVisibility(View.GONE);
        this.mCheckEliminaOctavoVehiculo.setVisibility(View.GONE);
        this.mCheckEliminaNovenoVehiculo.setVisibility(View.GONE);
        this.mCheckEliminaDecimoVehiculo.setVisibility(View.GONE);
    }

    private void rellenaCuartoVehiculo(List<ConsultaClientes> listVehiculosClientes) {
        this.mCuartaMarca = listVehiculosClientes.get(3).getmMarcaVehiculo();
        this.mCuartoModelo = listVehiculosClientes.get(3).getmModeloVehiculo();
        this.mCuartaMatricula = listVehiculosClientes.get(3).getmMatriculaVehiculo();
        String cuartaMarcaModelo = this.mCuartaMarca + " - " + this.mCuartoModelo;
        this.mEditCuartaMarcaModelo.setText(cuartaMarcaModelo);
        this.mEditCuartaMatricula.setText(this.mCuartaMatricula);
        this.mLayoutCuarto.setVisibility(View.VISIBLE);
        this.mLayoutQuinto.setVisibility(View.GONE);
        this.mLayoutSexto.setVisibility(View.GONE);
        this.mLayoutSeptimo.setVisibility(View.GONE);
        this.mLayoutOctavo.setVisibility(View.GONE);
        this.mLayoutNoveno.setVisibility(View.GONE);
        this.mLayoutDecimo.setVisibility(View.GONE);

        //Modificar Vehículo
        this.mCheckModificaCuartoVehiculo.setVisibility(View.VISIBLE);
        this.mCheckModificaCuartoVehiculo.setText(cuartaMarcaModelo);
        this.mCheckModificaCuartoVehiculo.setOnCheckedChangeListener(this);
        //Eliminar Vehículo
        this.mCheckEliminaCuartoVehiculo.setVisibility(View.VISIBLE);
        this.mCheckEliminaCuartoVehiculo.setText(cuartaMarcaModelo);
        this.mCheckEliminaCuartoVehiculo.setOnCheckedChangeListener(this);

        this.mCheckEliminaQuintoVehiculo.setVisibility(View.GONE);
        this.mCheckEliminaSextoVehiculo.setVisibility(View.GONE);
        this.mCheckEliminaSeptimoVehiculo.setVisibility(View.GONE);
        this.mCheckEliminaOctavoVehiculo.setVisibility(View.GONE);
        this.mCheckEliminaNovenoVehiculo.setVisibility(View.GONE);
        this.mCheckEliminaDecimoVehiculo.setVisibility(View.GONE);
    }

    private void rellenaQuintoVehiculo(List<ConsultaClientes> listVehiculosClientes) {
        this.mQuintaMarca = listVehiculosClientes.get(4).getmMarcaVehiculo();
        this.mQuintoModelo = listVehiculosClientes.get(4).getmModeloVehiculo();
        this.mQuintaMatricula = listVehiculosClientes.get(4).getmMatriculaVehiculo();
        String quintaMarcaModelo = this.mQuintaMarca + " - " + this.mQuintoModelo;
        this.mEditQuintaMarcaModelo.setText(quintaMarcaModelo);
        this.mEditQuintaMatricula.setText(this.mQuintaMatricula);
        this.mLayoutQuinto.setVisibility(View.VISIBLE);
        this.mLayoutSexto.setVisibility(View.GONE);
        this.mLayoutSeptimo.setVisibility(View.GONE);
        this.mLayoutOctavo.setVisibility(View.GONE);
        this.mLayoutNoveno.setVisibility(View.GONE);
        this.mLayoutDecimo.setVisibility(View.GONE);

        //Modificar Vehículo
        this.mCheckModificaQuintoVehiculo.setVisibility(View.VISIBLE);
        this.mCheckModificaQuintoVehiculo.setText(quintaMarcaModelo);
        this.mCheckModificaQuintoVehiculo.setOnCheckedChangeListener(this);
        //Eliminar Vehículo
        this.mCheckEliminaQuintoVehiculo.setVisibility(View.VISIBLE);
        this.mCheckEliminaQuintoVehiculo.setText(quintaMarcaModelo);
        this.mCheckEliminaQuintoVehiculo.setOnCheckedChangeListener(this);

        this.mCheckEliminaSextoVehiculo.setVisibility(View.GONE);
        this.mCheckEliminaSeptimoVehiculo.setVisibility(View.GONE);
        this.mCheckEliminaOctavoVehiculo.setVisibility(View.GONE);
        this.mCheckEliminaNovenoVehiculo.setVisibility(View.GONE);
        this.mCheckEliminaDecimoVehiculo.setVisibility(View.GONE);
    }

    private void rellenaSextoVehiculo(List<ConsultaClientes> listVehiculosClientes) {
        this.mSextaMarca = listVehiculosClientes.get(5).getmMarcaVehiculo();
        this.mSextoModelo = listVehiculosClientes.get(5).getmModeloVehiculo();
        this.mSextaMatricula = listVehiculosClientes.get(5).getmMatriculaVehiculo();
        String sextaMarcaModelo = this.mSextaMarca + " - " + this.mSextoModelo;
        this.mEditSextaMarcaModelo.setText(sextaMarcaModelo);
        this.mEditSextaMatricula.setText(this.mSextaMatricula);
        this.mLayoutSexto.setVisibility(View.VISIBLE);
        this.mLayoutSeptimo.setVisibility(View.GONE);
        this.mLayoutOctavo.setVisibility(View.GONE);
        this.mLayoutNoveno.setVisibility(View.GONE);
        this.mLayoutDecimo.setVisibility(View.GONE);

        //Modificar Vehículo
        this.mCheckModificaSextoVehiculo.setVisibility(View.VISIBLE);
        this.mCheckModificaSextoVehiculo.setText(sextaMarcaModelo);
        this.mCheckModificaSextoVehiculo.setOnCheckedChangeListener(this);

        //Eliminar Vehículo
        this.mCheckEliminaSextoVehiculo.setVisibility(View.VISIBLE);
        this.mCheckEliminaSextoVehiculo.setText(sextaMarcaModelo);
        this.mCheckEliminaSextoVehiculo.setOnCheckedChangeListener(this);


        this.mCheckEliminaSeptimoVehiculo.setVisibility(View.GONE);
        this.mCheckEliminaOctavoVehiculo.setVisibility(View.GONE);
        this.mCheckEliminaNovenoVehiculo.setVisibility(View.GONE);
        this.mCheckEliminaDecimoVehiculo.setVisibility(View.GONE);
    }

    private void rellenaSeptimoVehiculo(List<ConsultaClientes> listVehiculosClientes) {
        this.mSeptimaMarca = listVehiculosClientes.get(6).getmMarcaVehiculo();
        this.mSeptimoModelo = listVehiculosClientes.get(6).getmModeloVehiculo();
        this.mSeptimaMatricula = listVehiculosClientes.get(6).getmMatriculaVehiculo();
        String septimaMarcaModelo = this.mSeptimaMarca + " - " + this.mSeptimoModelo;
        this.mEditSeptimaMarcaModelo.setText(septimaMarcaModelo);
        this.mEditSeptimaMatricula.setText(this.mSeptimaMatricula);
        this.mLayoutSeptimo.setVisibility(View.VISIBLE);
        this.mLayoutOctavo.setVisibility(View.GONE);
        this.mLayoutNoveno.setVisibility(View.GONE);
        this.mLayoutDecimo.setVisibility(View.GONE);

        //Modificar Vehículo
        this.mCheckModificaSeptimoVehiculo.setVisibility(View.VISIBLE);
        this.mCheckModificaSeptimoVehiculo.setText(septimaMarcaModelo);
        this.mCheckModificaSeptimoVehiculo.setOnCheckedChangeListener(this);

        //Eliminar Vehículo
        this.mCheckEliminaSeptimoVehiculo.setVisibility(View.VISIBLE);
        this.mCheckEliminaSeptimoVehiculo.setText(septimaMarcaModelo);
        this.mCheckEliminaSeptimoVehiculo.setOnCheckedChangeListener(this);

        this.mCheckEliminaOctavoVehiculo.setVisibility(View.GONE);
        this.mCheckEliminaNovenoVehiculo.setVisibility(View.GONE);
        this.mCheckEliminaDecimoVehiculo.setVisibility(View.GONE);
    }

    private void rellenaOctavoVehiculo(List<ConsultaClientes> listVehiculosClientes) {
        this.mOctavaMarca = listVehiculosClientes.get(7).getmMarcaVehiculo();
        this.mOctavoModelo = listVehiculosClientes.get(7).getmModeloVehiculo();
        this.mOctavaMatricula = listVehiculosClientes.get(7).getmMatriculaVehiculo();
        String octavaMarcaModelo = this.mOctavaMarca + " - " + this.mOctavoModelo;
        this.mEditOctavaMarcaModelo.setText(octavaMarcaModelo);
        this.mEditOctavaMatricula.setText(this.mOctavaMatricula);
        this.mLayoutOctavo.setVisibility(View.VISIBLE);
        this.mLayoutNoveno.setVisibility(View.GONE);
        this.mLayoutDecimo.setVisibility(View.GONE);

        //Modificar Vehículo
        this.mCheckModificaOctavoVehiculo.setVisibility(View.VISIBLE);
        this.mCheckModificaOctavoVehiculo.setText(octavaMarcaModelo);
        this.mCheckModificaOctavoVehiculo.setOnCheckedChangeListener(this);

        //Eliminar Vehículo
        this.mCheckEliminaOctavoVehiculo.setVisibility(View.VISIBLE);
        this.mCheckEliminaOctavoVehiculo.setText(octavaMarcaModelo);
        this.mCheckEliminaOctavoVehiculo.setOnCheckedChangeListener(this);

        this.mCheckEliminaNovenoVehiculo.setVisibility(View.GONE);
        this.mCheckEliminaDecimoVehiculo.setVisibility(View.GONE);
    }

    private void rellenaNovenoVehiculo(List<ConsultaClientes> listVehiculosClientes) {
        this.mNovenaMarca = listVehiculosClientes.get(8).getmMarcaVehiculo();
        this.mNovenoModelo = listVehiculosClientes.get(8).getmModeloVehiculo();
        this.mNovenaMatricula = listVehiculosClientes.get(8).getmMatriculaVehiculo();
        String novenaMarcaModelo = this.mNovenaMarca + " - " + this.mNovenoModelo;
        this.mEditNovenaMarcaModelo.setText(novenaMarcaModelo);
        this.mEditNovenaMatricula.setText(this.mNovenaMatricula);
        this.mLayoutNoveno.setVisibility(View.VISIBLE);
        this.mLayoutDecimo.setVisibility(View.GONE);

        //Modificar Vehículo
        this.mCheckModificaNovenoVehiculo.setVisibility(View.VISIBLE);
        this.mCheckModificaNovenoVehiculo.setText(novenaMarcaModelo);
        this.mCheckModificaNovenoVehiculo.setOnCheckedChangeListener(this);

        //Eliminar Vehículo
        this.mCheckEliminaNovenoVehiculo.setVisibility(View.VISIBLE);
        this.mCheckEliminaNovenoVehiculo.setText(novenaMarcaModelo);
        this.mCheckEliminaNovenoVehiculo.setOnCheckedChangeListener(this);

        this.mCheckEliminaDecimoVehiculo.setVisibility(View.GONE);
    }

    private void rellenaDecimoVehiculo(List<ConsultaClientes> listVehiculosClientes) {
        this.mDecimaMarca = listVehiculosClientes.get(9).getmMarcaVehiculo();
        this.mDecimoModelo = listVehiculosClientes.get(9).getmModeloVehiculo();
        this.mDecimaMatricula = listVehiculosClientes.get(9).getmMatriculaVehiculo();
        String decimaMarcaModelo = this.mDecimaMarca + " - " + this.mDecimoModelo;
        this.mEditDecimaMarcaModelo.setText(decimaMarcaModelo);
        this.mEditDecimaMatricula.setText(this.mDecimaMatricula);
        this.mLayoutDecimo.setVisibility(View.VISIBLE);

        //Modificar Vehículo
        this.mCheckModificaDecimoVehiculo.setVisibility(View.VISIBLE);
        this.mCheckModificaDecimoVehiculo.setText(decimaMarcaModelo);
        this.mCheckModificaDecimoVehiculo.setOnCheckedChangeListener(this);

        //Eliminar Vehículo
        this.mCheckEliminaDecimoVehiculo.setVisibility(View.VISIBLE);
        this.mCheckEliminaDecimoVehiculo.setText(decimaMarcaModelo);
        this.mCheckEliminaDecimoVehiculo.setOnCheckedChangeListener(this);
    }

}
