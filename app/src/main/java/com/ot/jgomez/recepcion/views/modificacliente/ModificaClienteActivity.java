package com.ot.jgomez.recepcion.views.modificacliente;

import android.app.Dialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ot.jgomez.recepcion.R;
import com.ot.jgomez.recepcion.adapters.ListaBuscaClientesDialogAdapters;
import com.ot.jgomez.recepcion.items.ConsultaClientes;

import java.util.List;

public class ModificaClienteActivity extends AppCompatActivity implements
        ModificaClienteContract.View, View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private ModificaClientePresenterImpl mPresenter;
    private List<ConsultaClientes> mList;
    private Button mBtnAceptar;
    private Button mBtnCancelar;
    private CheckBox mCheckBoxDatosPersonales;
    private CheckBox mCheckBoxAñadirVehiculo;
    private CheckBox mCheckBoxModificarVehiculo;
    private CheckBox mCheckBoxEliminarVehiculo;
    private RecyclerView mRecyclerDialog;

    //datos personales
    private EditText mEditNombreApellidos;
    private EditText mEditTelefono;

    //primer vehículo
    private EditText mEditPrimeraMarcaModelo;
    private EditText mEditPrimeraMatricula;
    private LinearLayout mLayoutPrimero;


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

        //datos personales
        this.mEditNombreApellidos = (EditText) findViewById(R.id.edtxt_busca_nombre_apellidos);
        //cuando clickemos un EditText, se abrirá un Dialog con una búsqueda más completa.
        //en principio, solo buscando el nombre será suficiente para elegir el cliente
        this.mEditNombreApellidos.setOnClickListener(this);
        this.mEditTelefono = (EditText) findViewById(R.id.edtxt_busca_telefono);

        //primer vehículo
        this.mEditPrimeraMarcaModelo = (EditText) findViewById(R.id.edtxt_busca_primer_vehiculo);
        this.mEditPrimeraMatricula = (EditText) findViewById(R.id.edtxt_busca_primera_matricula);
        this.mLayoutPrimero = (LinearLayout) findViewById(R.id.layout_busca_primer_vehiculo);

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
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
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
        } else if (v == this.mBtnCancelar) {
            //cancelar cambios -->  si se ha modificado algo, preguntar si se quiere ir sin guardar
            //                      si no hay cambios nuevos, NP
        } else if (v == this.mEditNombreApellidos) {
            this.dialogBuscaCliente();
        }
    }

    /**
     * Con esta función conseguimos que se abra un dialog externo a la pantalla y podremos
     * visualizar mejor la búsqueda de nuestro cliente.
     */
    private void dialogBuscaCliente() {
        final Dialog dialog = new Dialog(this);
        //dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog_looking_for);

        //set the custom dialog components
        //TextView textoTitulo = (TextView) dialog.findViewById(R.id.txtvw_titulo_dialog);
        //textoTitulo.setText(R.string.confirmacion);

        dialog.setTitle(R.string.busqueda);

        //usamos una recyclerview para enseñar la lista de nombres
        EditText editBusqueda = (EditText) dialog.findViewById(R.id.edit_busca_nombre_dialog);
        editBusqueda.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String aux;
                int index;
                String espacio = " ";
                if (s.length() >= 2) {
                    index = s.length();
                    Log.d("ModificaCliente", "index = " + index);
                    Log.d("ModificaCliente", "s.toString().substring(index) = "
                            + s.toString().substring(0, index - 1));
                    if (espacio.equals(s.charAt(index - 1))) {
                        aux = s.toString().substring(0, 1).toUpperCase() +
                                s.toString().substring(1, index - 1) +
                                s.toString().substring(index - 1, index).toUpperCase();
                        Log.d("ModificaCliente", "if -> aux = " + aux);
                    } else {
                        aux = s.toString().substring(0, 1).toUpperCase() +
                                s.toString().substring(1, s.length());
                        Log.d("ModificaCliente", "else -> aux = " + aux);
                    }
                    buscaNombre(aux);
                }
            }
        });

        this.mRecyclerDialog = (RecyclerView) dialog.findViewById(R.id.recycler_view_dialog_busca_nombre);
        Button buttonCancelar = (Button) dialog.findViewById(R.id.boton_cancelar_dialog_busca_cliente);
        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Button buttonAceptar = (Button) dialog.findViewById(R.id.boton_aceptar_dialog_busca_cliente);
        buttonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        dialog.show();
    }

    private void buscaNombre(String nombre) {
        this.mList = this.mPresenter.getClientes(nombre);
        if (this.mList.size() > 0) {
            ListaBuscaClientesDialogAdapters adapter = new ListaBuscaClientesDialogAdapters(this, this.mList);
            this.mRecyclerDialog.setLayoutManager(new LinearLayoutManager(this));
            this.mRecyclerDialog.setAdapter(adapter);
        } else {
            this.showMessage("No existen clientes con ese nombre");
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView == this.mCheckBoxAñadirVehiculo) {
            if (isChecked) {

            } else {

            }
        } else if (buttonView == this.mCheckBoxDatosPersonales) {
            if (isChecked) {

            } else {

            }
        } else if (buttonView == this.mCheckBoxEliminarVehiculo) {
            if (isChecked) {

            } else {

            }
        } else if (buttonView == this.mCheckBoxModificarVehiculo) {

        }
    }
}
