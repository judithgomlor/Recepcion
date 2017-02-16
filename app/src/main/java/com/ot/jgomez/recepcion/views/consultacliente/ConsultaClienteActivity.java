package com.ot.jgomez.recepcion.views.consultacliente;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ot.jgomez.recepcion.R;
import com.ot.jgomez.recepcion.items.ConsultaClientes;

import java.util.ArrayList;
import java.util.List;

public class ConsultaClienteActivity extends AppCompatActivity implements ConsultaClienteContract.View {

    private ConsultaClientePresenterImpl mPresenter;
    private List<ConsultaClientes> mListClientes;
    private List<String> mListMarcas;
    private List<String> mListModelos;
    private List<String> mListMatriculas;

    //extras
    private static final String EXTRA_NOMBRE = "nombre";
    private static final String EXTRA_PRIMER_APELLIDO = "primer_apellido";
    private static final String EXTRA_SEGUNDO_APELLIDO = "segundo_apellido";
    private static final String EXTRA_TELEFONO = "telefono";

    //datos personales
    private TextView mTextNombre;
    private TextView mTextApellidos;
    private TextView mTextTelefono;
    private String mNombre;
    private String mPrimerApellido;
    private String mSegundoApellido;
    private String mTelefono;

    //primer vehículo
    private LinearLayout mLayoutPrimerVehiculo;
    private TextView mTextPrimeraMarca;
    private TextView mTextPrimerModelo;
    private TextView mTextPrimeraMatricula;
    private String mPrimeraMarca;
    private String mPrimerModelo;
    private String mPrimeraMatricula;

    //segundo vehículo
    private LinearLayout mLayoutSegundoVehiculo;
    private TextView mTextSegundaMarca;
    private TextView mTextSegundoModelo;
    private TextView mTextSegundaMatricula;
    private String mSegundaMarca;
    private String mSegundoModelo;
    private String mSegundaMatricula;

    //tercer vehículo
    private LinearLayout mLayoutTercerVehiculo;
    private TextView mTextTerceraMarca;
    private TextView mTextTercerModelo;
    private TextView mTextTerceraMatricula;
    private String mTerceraMarca;
    private String mTercerModelo;
    private String mTerceraMatricula;

    //cuarto vehículo
    private LinearLayout mLayoutCuartoVehiculo;
    private TextView mTextCuartaMarca;
    private TextView mTextCuartoModelo;
    private TextView mTextCuartaMatricula;
    private String mCuartaMarca;
    private String mCuartoModelo;
    private String mCuartaMatricula;

    //quinto vehículo
    private LinearLayout mLayoutQuintoVehiculo;
    private TextView mTextQuintaMarca;
    private TextView mTextQuintoModelo;
    private TextView mTextQuintaMatricula;
    private String mQuintaMarca;
    private String mQuintoModelo;
    private String mQuintaMatricula;

    //sexto vehículo
    private LinearLayout mLayoutSextoVehiculo;
    private TextView mTextSextaMarca;
    private TextView mTextSextoModelo;
    private TextView mTextSextaMatricula;
    private String mSextaMarca;
    private String mSextoModelo;
    private String mSextaMatricula;

    //séptimo vehículo
    private LinearLayout mLayoutSeptimoVehiculo;
    private TextView mTextSeptimaMarca;
    private TextView mTextSeptimoModelo;
    private TextView mTextSeptimaMatricula;
    private String mSeptimaMarca;
    private String mSeptimoModelo;
    private String mSeptimaMatricula;

    //octavo vehículo
    private LinearLayout mLayoutOctavoVehiculo;
    private TextView mTextOctavaMarca;
    private TextView mTextOctavoModelo;
    private TextView mTextOctavaMatricula;
    private String mOctavaMarca;
    private String mOctavoModelo;
    private String mOctavaMatricula;

    //noveno vehículo
    private LinearLayout mLayoutNovenoVehiculo;
    private TextView mTextNovenaMarca;
    private TextView mTextNovenoModelo;
    private TextView mTextNovenaMatricula;
    private String mNovenaMarca;
    private String mNovenoModelo;
    private String mNovenaMatricula;

    //décimo vehículo
    private LinearLayout mLayoutDecimoVehiculo;
    private TextView mTextDecimaMarca;
    private TextView mTextDecimoModelo;
    private TextView mTextDecimaMatricula;
    private String mDecimaMarca;
    private String mDecimoModelo;
    private String mDecimaMatricula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_cliente);

        ActionBar myBar = getSupportActionBar();
        myBar.setDisplayHomeAsUpEnabled(true);
        myBar.setDisplayShowHomeEnabled(true);
        myBar.setHomeButtonEnabled(true);
        myBar.setTitle(R.string.consultar_cliente);

        if (this.mPresenter == null) {
            this.mPresenter = new ConsultaClientePresenterImpl();
        }
        this.mPresenter.attach(this, this);

        this.mListClientes = new ArrayList<>();
        this.mListClientes = this.mPresenter.getClientes();

        this.init();
        this.goneVehiculos();
        this.getInfoClientes();
        this.escribeTextViews();
    }

    private void goneVehiculos() {
        //primer vehículo
        this.mLayoutPrimerVehiculo.setVisibility(View.GONE);
        //segundo vehículo
        this.mLayoutSegundoVehiculo.setVisibility(View.GONE);
        //tercer vehículo
        this.mLayoutTercerVehiculo.setVisibility(View.GONE);
        //cuarto vehículo
        this.mLayoutCuartoVehiculo.setVisibility(View.GONE);
        //quinto vehículo
        this.mLayoutQuintoVehiculo.setVisibility(View.GONE);
        //sexto vehículo
        this.mLayoutSextoVehiculo.setVisibility(View.GONE);
        //séptimo vehículo
        this.mLayoutSeptimoVehiculo.setVisibility(View.GONE);
        //octavo vehículo
        this.mLayoutOctavoVehiculo.setVisibility(View.GONE);
        //noveno vehículo
        this.mLayoutNovenoVehiculo.setVisibility(View.GONE);
        //décimo vehículo
        this.mLayoutDecimoVehiculo.setVisibility(View.GONE);
    }

    private void escribeTextViews() {
        //datos personales
        this.escribeDatosPersonales();
        int count = this.cuantosVehiculosTieneCliente();
        if (count > 0) {
            this.visibleVehiculosPonInfo(count);
            this.ponInfoEnCamposVehiculo(count);
        }
    }

    /*
    Setteamos los campos de "Marca", "Modelo" y "Matrícula" correspondientes con el cliente.
     */
    private void ponInfoEnCamposVehiculo(int count) {
        switch (count) {
            case 1:
                this.mTextPrimeraMarca.setText(this.mPrimeraMarca);
                this.mTextPrimerModelo.setText(this.mPrimerModelo);
                this.mTextPrimeraMatricula.setText(this.mPrimeraMatricula);
                break;
            case 2:
                this.mTextPrimeraMarca.setText(this.mPrimeraMarca);
                this.mTextPrimerModelo.setText(this.mPrimerModelo);
                this.mTextPrimeraMatricula.setText(this.mPrimeraMatricula);

                this.mTextSegundaMarca.setText(this.mSegundaMarca);
                this.mTextSegundoModelo.setText(this.mSegundoModelo);
                this.mTextSegundaMatricula.setText(this.mSegundaMatricula);
                break;
            case 3:
                this.mTextPrimeraMarca.setText(this.mPrimeraMarca);
                this.mTextPrimerModelo.setText(this.mPrimerModelo);
                this.mTextPrimeraMatricula.setText(this.mPrimeraMatricula);

                this.mTextSegundaMarca.setText(this.mSegundaMarca);
                this.mTextSegundoModelo.setText(this.mSegundoModelo);
                this.mTextSegundaMatricula.setText(this.mSegundaMatricula);

                this.mTextTerceraMarca.setText(this.mTerceraMarca);
                this.mTextTercerModelo.setText(this.mTercerModelo);
                this.mTextTerceraMatricula.setText(this.mTerceraMatricula);
                break;
            case 4:
                this.mTextPrimeraMarca.setText(this.mPrimeraMarca);
                this.mTextPrimerModelo.setText(this.mPrimerModelo);
                this.mTextPrimeraMatricula.setText(this.mPrimeraMatricula);

                this.mTextSegundaMarca.setText(this.mSegundaMarca);
                this.mTextSegundoModelo.setText(this.mSegundoModelo);
                this.mTextSegundaMatricula.setText(this.mSegundaMatricula);

                this.mTextTerceraMarca.setText(this.mTerceraMarca);
                this.mTextTercerModelo.setText(this.mTercerModelo);
                this.mTextTerceraMatricula.setText(this.mTerceraMatricula);

                this.mTextCuartaMarca.setText(this.mCuartaMarca);
                this.mTextCuartoModelo.setText(this.mCuartoModelo);
                this.mTextCuartaMatricula.setText(this.mCuartaMatricula);
                break;
            case 5:
                this.mTextPrimeraMarca.setText(this.mPrimeraMarca);
                this.mTextPrimerModelo.setText(this.mPrimerModelo);
                this.mTextPrimeraMatricula.setText(this.mPrimeraMatricula);

                this.mTextSegundaMarca.setText(this.mSegundaMarca);
                this.mTextSegundoModelo.setText(this.mSegundoModelo);
                this.mTextSegundaMatricula.setText(this.mSegundaMatricula);

                this.mTextTerceraMarca.setText(this.mTerceraMarca);
                this.mTextTercerModelo.setText(this.mTercerModelo);
                this.mTextTerceraMatricula.setText(this.mTerceraMatricula);

                this.mTextCuartaMarca.setText(this.mCuartaMarca);
                this.mTextCuartoModelo.setText(this.mCuartoModelo);
                this.mTextCuartaMatricula.setText(this.mCuartaMatricula);

                this.mTextQuintaMarca.setText(this.mQuintaMarca);
                this.mTextQuintoModelo.setText(this.mQuintoModelo);
                this.mTextQuintaMatricula.setText(this.mQuintaMatricula);
                break;
            case 6:
                this.mTextPrimeraMarca.setText(this.mPrimeraMarca);
                this.mTextPrimerModelo.setText(this.mPrimerModelo);
                this.mTextPrimeraMatricula.setText(this.mPrimeraMatricula);

                this.mTextSegundaMarca.setText(this.mSegundaMarca);
                this.mTextSegundoModelo.setText(this.mSegundoModelo);
                this.mTextSegundaMatricula.setText(this.mSegundaMatricula);

                this.mTextTerceraMarca.setText(this.mTerceraMarca);
                this.mTextTercerModelo.setText(this.mTercerModelo);
                this.mTextTerceraMatricula.setText(this.mTerceraMatricula);

                this.mTextCuartaMarca.setText(this.mCuartaMarca);
                this.mTextCuartoModelo.setText(this.mCuartoModelo);
                this.mTextCuartaMatricula.setText(this.mCuartaMatricula);

                this.mTextQuintaMarca.setText(this.mQuintaMarca);
                this.mTextQuintoModelo.setText(this.mQuintoModelo);
                this.mTextQuintaMatricula.setText(this.mQuintaMatricula);

                this.mTextSextaMarca.setText(this.mSextaMarca);
                this.mTextSextoModelo.setText(this.mSextoModelo);
                this.mTextSextaMatricula.setText(this.mSextaMatricula);
                break;
            case 7:
                this.mTextPrimeraMarca.setText(this.mPrimeraMarca);
                this.mTextPrimerModelo.setText(this.mPrimerModelo);
                this.mTextPrimeraMatricula.setText(this.mPrimeraMatricula);

                this.mTextSegundaMarca.setText(this.mSegundaMarca);
                this.mTextSegundoModelo.setText(this.mSegundoModelo);
                this.mTextSegundaMatricula.setText(this.mSegundaMatricula);

                this.mTextTerceraMarca.setText(this.mTerceraMarca);
                this.mTextTercerModelo.setText(this.mTercerModelo);
                this.mTextTerceraMatricula.setText(this.mTerceraMatricula);

                this.mTextCuartaMarca.setText(this.mCuartaMarca);
                this.mTextCuartoModelo.setText(this.mCuartoModelo);
                this.mTextCuartaMatricula.setText(this.mCuartaMatricula);

                this.mTextQuintaMarca.setText(this.mQuintaMarca);
                this.mTextQuintoModelo.setText(this.mQuintoModelo);
                this.mTextQuintaMatricula.setText(this.mQuintaMatricula);

                this.mTextSextaMarca.setText(this.mSextaMarca);
                this.mTextSextoModelo.setText(this.mSextoModelo);
                this.mTextSextaMatricula.setText(this.mSextaMatricula);

                this.mTextSeptimaMarca.setText(this.mSeptimaMarca);
                this.mTextSeptimoModelo.setText(this.mSeptimoModelo);
                this.mTextSeptimaMatricula.setText(this.mSeptimaMatricula);
                break;
            case 8:
                this.mTextPrimeraMarca.setText(this.mPrimeraMarca);
                this.mTextPrimerModelo.setText(this.mPrimerModelo);
                this.mTextPrimeraMatricula.setText(this.mPrimeraMatricula);

                this.mTextSegundaMarca.setText(this.mSegundaMarca);
                this.mTextSegundoModelo.setText(this.mSegundoModelo);
                this.mTextSegundaMatricula.setText(this.mSegundaMatricula);

                this.mTextTerceraMarca.setText(this.mTerceraMarca);
                this.mTextTercerModelo.setText(this.mTercerModelo);
                this.mTextTerceraMatricula.setText(this.mTerceraMatricula);

                this.mTextCuartaMarca.setText(this.mCuartaMarca);
                this.mTextCuartoModelo.setText(this.mCuartoModelo);
                this.mTextCuartaMatricula.setText(this.mCuartaMatricula);

                this.mTextQuintaMarca.setText(this.mQuintaMarca);
                this.mTextQuintoModelo.setText(this.mQuintoModelo);
                this.mTextQuintaMatricula.setText(this.mQuintaMatricula);

                this.mTextSextaMarca.setText(this.mSextaMarca);
                this.mTextSextoModelo.setText(this.mSextoModelo);
                this.mTextSextaMatricula.setText(this.mSextaMatricula);

                this.mTextSeptimaMarca.setText(this.mSeptimaMarca);
                this.mTextSeptimoModelo.setText(this.mSeptimoModelo);
                this.mTextSeptimaMatricula.setText(this.mSeptimaMatricula);

                this.mTextOctavaMarca.setText(this.mOctavaMarca);
                this.mTextOctavoModelo.setText(this.mOctavoModelo);
                this.mTextOctavaMatricula.setText(this.mOctavaMatricula);
                break;
            case 9:
                this.mTextPrimeraMarca.setText(this.mPrimeraMarca);
                this.mTextPrimerModelo.setText(this.mPrimerModelo);
                this.mTextPrimeraMatricula.setText(this.mPrimeraMatricula);

                this.mTextSegundaMarca.setText(this.mSegundaMarca);
                this.mTextSegundoModelo.setText(this.mSegundoModelo);
                this.mTextSegundaMatricula.setText(this.mSegundaMatricula);

                this.mTextTerceraMarca.setText(this.mTerceraMarca);
                this.mTextTercerModelo.setText(this.mTercerModelo);
                this.mTextTerceraMatricula.setText(this.mTerceraMatricula);

                this.mTextCuartaMarca.setText(this.mCuartaMarca);
                this.mTextCuartoModelo.setText(this.mCuartoModelo);
                this.mTextCuartaMatricula.setText(this.mCuartaMatricula);

                this.mTextQuintaMarca.setText(this.mQuintaMarca);
                this.mTextQuintoModelo.setText(this.mQuintoModelo);
                this.mTextQuintaMatricula.setText(this.mQuintaMatricula);

                this.mTextSextaMarca.setText(this.mSextaMarca);
                this.mTextSextoModelo.setText(this.mSextoModelo);
                this.mTextSextaMatricula.setText(this.mSextaMatricula);

                this.mTextSeptimaMarca.setText(this.mSeptimaMarca);
                this.mTextSeptimoModelo.setText(this.mSeptimoModelo);
                this.mTextSeptimaMatricula.setText(this.mSeptimaMatricula);

                this.mTextOctavaMarca.setText(this.mOctavaMarca);
                this.mTextOctavoModelo.setText(this.mOctavoModelo);
                this.mTextOctavaMatricula.setText(this.mOctavaMatricula);

                this.mTextNovenaMarca.setText(this.mNovenaMarca);
                this.mTextNovenoModelo.setText(this.mNovenoModelo);
                this.mTextNovenaMatricula.setText(this.mNovenaMatricula);
                break;
            case 10:
                this.mTextPrimeraMarca.setText(this.mPrimeraMarca);
                this.mTextPrimerModelo.setText(this.mPrimerModelo);
                this.mTextPrimeraMatricula.setText(this.mPrimeraMatricula);

                this.mTextSegundaMarca.setText(this.mSegundaMarca);
                this.mTextSegundoModelo.setText(this.mSegundoModelo);
                this.mTextSegundaMatricula.setText(this.mSegundaMatricula);

                this.mTextTerceraMarca.setText(this.mTerceraMarca);
                this.mTextTercerModelo.setText(this.mTercerModelo);
                this.mTextTerceraMatricula.setText(this.mTerceraMatricula);

                this.mTextCuartaMarca.setText(this.mCuartaMarca);
                this.mTextCuartoModelo.setText(this.mCuartoModelo);
                this.mTextCuartaMatricula.setText(this.mCuartaMatricula);

                this.mTextQuintaMarca.setText(this.mQuintaMarca);
                this.mTextQuintoModelo.setText(this.mQuintoModelo);
                this.mTextQuintaMatricula.setText(this.mQuintaMatricula);

                this.mTextSextaMarca.setText(this.mSextaMarca);
                this.mTextSextoModelo.setText(this.mSextoModelo);
                this.mTextSextaMatricula.setText(this.mSextaMatricula);

                this.mTextSeptimaMarca.setText(this.mSeptimaMarca);
                this.mTextSeptimoModelo.setText(this.mSeptimoModelo);
                this.mTextSeptimaMatricula.setText(this.mSeptimaMatricula);

                this.mTextOctavaMarca.setText(this.mOctavaMarca);
                this.mTextOctavoModelo.setText(this.mOctavoModelo);
                this.mTextOctavaMatricula.setText(this.mOctavaMatricula);

                this.mTextNovenaMarca.setText(this.mNovenaMarca);
                this.mTextNovenoModelo.setText(this.mNovenoModelo);
                this.mTextNovenaMatricula.setText(this.mNovenaMatricula);

                this.mTextDecimaMarca.setText(this.mDecimaMarca);
                this.mTextDecimoModelo.setText(this.mDecimoModelo);
                this.mTextDecimaMatricula.setText(this.mDecimaMatricula);
                break;
            default:
                break;
        }
    }

    /*
    Hacemos visibles todos los campos de los vehículos que tenga el cliente y cogemos la información
     de las listas para meterlas en los Strings que luego enseñaremos.
     */
    private void visibleVehiculosPonInfo(int count) {
        switch (count) {
            case 1:
                this.mLayoutPrimerVehiculo.setVisibility(View.VISIBLE);
                this.mPrimeraMarca = this.mListMarcas.get(0);
                this.mPrimerModelo = this.mListModelos.get(0);
                this.mPrimeraMatricula = this.mListMatriculas.get(0);
                break;
            case 2:
                this.mLayoutPrimerVehiculo.setVisibility(View.VISIBLE);
                this.mPrimeraMarca = this.mListMarcas.get(0);
                this.mPrimerModelo = this.mListModelos.get(0);
                this.mPrimeraMatricula = this.mListMatriculas.get(0);

                this.mLayoutSegundoVehiculo.setVisibility(View.VISIBLE);
                this.mSegundaMarca = this.mListMarcas.get(1);
                this.mSegundoModelo = this.mListModelos.get(1);
                this.mSegundaMatricula = this.mListMatriculas.get(1);
                break;
            case 3:
                this.mLayoutPrimerVehiculo.setVisibility(View.VISIBLE);
                this.mPrimeraMarca = this.mListMarcas.get(0);
                this.mPrimerModelo = this.mListModelos.get(0);
                this.mPrimeraMatricula = this.mListMatriculas.get(0);

                this.mLayoutSegundoVehiculo.setVisibility(View.VISIBLE);
                this.mSegundaMarca = this.mListMarcas.get(1);
                this.mSegundoModelo = this.mListModelos.get(1);
                this.mSegundaMatricula = this.mListMatriculas.get(1);

                this.mLayoutTercerVehiculo.setVisibility(View.VISIBLE);
                this.mTerceraMarca = this.mListMarcas.get(2);
                this.mTercerModelo = this.mListModelos.get(2);
                this.mTerceraMatricula = this.mListMatriculas.get(2);
                break;
            case 4:
                this.mLayoutPrimerVehiculo.setVisibility(View.VISIBLE);
                this.mPrimeraMarca = this.mListMarcas.get(0);
                this.mPrimerModelo = this.mListModelos.get(0);
                this.mPrimeraMatricula = this.mListMatriculas.get(0);

                this.mLayoutSegundoVehiculo.setVisibility(View.VISIBLE);
                this.mSegundaMarca = this.mListMarcas.get(1);
                this.mSegundoModelo = this.mListModelos.get(1);
                this.mSegundaMatricula = this.mListMatriculas.get(1);

                this.mLayoutTercerVehiculo.setVisibility(View.VISIBLE);
                this.mTerceraMarca = this.mListMarcas.get(2);
                this.mTercerModelo = this.mListModelos.get(2);
                this.mTerceraMatricula = this.mListMatriculas.get(2);

                this.mLayoutCuartoVehiculo.setVisibility(View.VISIBLE);
                this.mCuartaMarca = this.mListMarcas.get(3);
                this.mCuartoModelo = this.mListModelos.get(3);
                this.mCuartaMatricula = this.mListMatriculas.get(3);
                break;
            case 5:
                this.mLayoutPrimerVehiculo.setVisibility(View.VISIBLE);
                this.mPrimeraMarca = this.mListMarcas.get(0);
                this.mPrimerModelo = this.mListModelos.get(0);
                this.mPrimeraMatricula = this.mListMatriculas.get(0);

                this.mLayoutSegundoVehiculo.setVisibility(View.VISIBLE);
                this.mSegundaMarca = this.mListMarcas.get(1);
                this.mSegundoModelo = this.mListModelos.get(1);
                this.mSegundaMatricula = this.mListMatriculas.get(1);

                this.mLayoutTercerVehiculo.setVisibility(View.VISIBLE);
                this.mTerceraMarca = this.mListMarcas.get(2);
                this.mTercerModelo = this.mListModelos.get(2);
                this.mTerceraMatricula = this.mListMatriculas.get(2);

                this.mLayoutCuartoVehiculo.setVisibility(View.VISIBLE);
                this.mCuartaMarca = this.mListMarcas.get(3);
                this.mCuartoModelo = this.mListModelos.get(3);
                this.mCuartaMatricula = this.mListMatriculas.get(3);

                this.mLayoutQuintoVehiculo.setVisibility(View.VISIBLE);
                this.mQuintaMarca = this.mListMarcas.get(4);
                this.mQuintoModelo = this.mListModelos.get(4);
                this.mQuintaMatricula = this.mListMatriculas.get(4);
                break;
            case 6:
                this.mLayoutPrimerVehiculo.setVisibility(View.VISIBLE);
                this.mPrimeraMarca = this.mListMarcas.get(0);
                this.mPrimerModelo = this.mListModelos.get(0);
                this.mPrimeraMatricula = this.mListMatriculas.get(0);

                this.mLayoutSegundoVehiculo.setVisibility(View.VISIBLE);
                this.mSegundaMarca = this.mListMarcas.get(1);
                this.mSegundoModelo = this.mListModelos.get(1);
                this.mSegundaMatricula = this.mListMatriculas.get(1);

                this.mLayoutTercerVehiculo.setVisibility(View.VISIBLE);
                this.mTerceraMarca = this.mListMarcas.get(2);
                this.mTercerModelo = this.mListModelos.get(2);
                this.mTerceraMatricula = this.mListMatriculas.get(2);

                this.mLayoutCuartoVehiculo.setVisibility(View.VISIBLE);
                this.mCuartaMarca = this.mListMarcas.get(3);
                this.mCuartoModelo = this.mListModelos.get(3);
                this.mCuartaMatricula = this.mListMatriculas.get(3);

                this.mLayoutQuintoVehiculo.setVisibility(View.VISIBLE);
                this.mQuintaMarca = this.mListMarcas.get(4);
                this.mQuintoModelo = this.mListModelos.get(4);
                this.mQuintaMatricula = this.mListMatriculas.get(4);

                this.mLayoutSextoVehiculo.setVisibility(View.VISIBLE);
                this.mSextaMarca = this.mListMarcas.get(5);
                this.mSextoModelo = this.mListModelos.get(5);
                this.mSextaMatricula = this.mListMatriculas.get(5);
                break;
            case 7:
                this.mLayoutPrimerVehiculo.setVisibility(View.VISIBLE);
                this.mPrimeraMarca = this.mListMarcas.get(0);
                this.mPrimerModelo = this.mListModelos.get(0);
                this.mPrimeraMatricula = this.mListMatriculas.get(0);

                this.mLayoutSegundoVehiculo.setVisibility(View.VISIBLE);
                this.mSegundaMarca = this.mListMarcas.get(1);
                this.mSegundoModelo = this.mListModelos.get(1);
                this.mSegundaMatricula = this.mListMatriculas.get(1);

                this.mLayoutTercerVehiculo.setVisibility(View.VISIBLE);
                this.mTerceraMarca = this.mListMarcas.get(2);
                this.mTercerModelo = this.mListModelos.get(2);
                this.mTerceraMatricula = this.mListMatriculas.get(2);

                this.mLayoutCuartoVehiculo.setVisibility(View.VISIBLE);
                this.mCuartaMarca = this.mListMarcas.get(3);
                this.mCuartoModelo = this.mListModelos.get(3);
                this.mCuartaMatricula = this.mListMatriculas.get(3);

                this.mLayoutQuintoVehiculo.setVisibility(View.VISIBLE);
                this.mQuintaMarca = this.mListMarcas.get(4);
                this.mQuintoModelo = this.mListModelos.get(4);
                this.mQuintaMatricula = this.mListMatriculas.get(4);

                this.mLayoutSextoVehiculo.setVisibility(View.VISIBLE);
                this.mSextaMarca = this.mListMarcas.get(5);
                this.mSextoModelo = this.mListModelos.get(5);
                this.mSextaMatricula = this.mListMatriculas.get(5);

                this.mLayoutSeptimoVehiculo.setVisibility(View.VISIBLE);
                this.mSeptimaMarca = this.mListMarcas.get(6);
                this.mSeptimoModelo = this.mListModelos.get(6);
                this.mSeptimaMatricula = this.mListMatriculas.get(6);
                break;
            case 8:
                this.mLayoutPrimerVehiculo.setVisibility(View.VISIBLE);
                this.mPrimeraMarca = this.mListMarcas.get(0);
                this.mPrimerModelo = this.mListModelos.get(0);
                this.mPrimeraMatricula = this.mListMatriculas.get(0);

                this.mLayoutSegundoVehiculo.setVisibility(View.VISIBLE);
                this.mSegundaMarca = this.mListMarcas.get(1);
                this.mSegundoModelo = this.mListModelos.get(1);
                this.mSegundaMatricula = this.mListMatriculas.get(1);

                this.mLayoutTercerVehiculo.setVisibility(View.VISIBLE);
                this.mTerceraMarca = this.mListMarcas.get(2);
                this.mTercerModelo = this.mListModelos.get(2);
                this.mTerceraMatricula = this.mListMatriculas.get(2);

                this.mLayoutCuartoVehiculo.setVisibility(View.VISIBLE);
                this.mCuartaMarca = this.mListMarcas.get(3);
                this.mCuartoModelo = this.mListModelos.get(3);
                this.mCuartaMatricula = this.mListMatriculas.get(3);

                this.mLayoutQuintoVehiculo.setVisibility(View.VISIBLE);
                this.mQuintaMarca = this.mListMarcas.get(4);
                this.mQuintoModelo = this.mListModelos.get(4);
                this.mQuintaMatricula = this.mListMatriculas.get(4);

                this.mLayoutSextoVehiculo.setVisibility(View.VISIBLE);
                this.mSextaMarca = this.mListMarcas.get(5);
                this.mSextoModelo = this.mListModelos.get(5);
                this.mSextaMatricula = this.mListMatriculas.get(5);

                this.mLayoutSeptimoVehiculo.setVisibility(View.VISIBLE);
                this.mSeptimaMarca = this.mListMarcas.get(6);
                this.mSeptimoModelo = this.mListModelos.get(6);
                this.mSeptimaMatricula = this.mListMatriculas.get(6);

                this.mLayoutOctavoVehiculo.setVisibility(View.VISIBLE);
                this.mOctavaMarca = this.mListMarcas.get(7);
                this.mOctavoModelo = this.mListModelos.get(7);
                this.mOctavaMatricula = this.mListModelos.get(7);
                break;
            case 9:
                this.mLayoutPrimerVehiculo.setVisibility(View.VISIBLE);
                this.mPrimeraMarca = this.mListMarcas.get(0);
                this.mPrimerModelo = this.mListModelos.get(0);
                this.mPrimeraMatricula = this.mListMatriculas.get(0);

                this.mLayoutSegundoVehiculo.setVisibility(View.VISIBLE);
                this.mSegundaMarca = this.mListMarcas.get(1);
                this.mSegundoModelo = this.mListModelos.get(1);
                this.mSegundaMatricula = this.mListMatriculas.get(1);

                this.mLayoutTercerVehiculo.setVisibility(View.VISIBLE);
                this.mTerceraMarca = this.mListMarcas.get(2);
                this.mTercerModelo = this.mListModelos.get(2);
                this.mTerceraMatricula = this.mListMatriculas.get(2);

                this.mLayoutCuartoVehiculo.setVisibility(View.VISIBLE);
                this.mCuartaMarca = this.mListMarcas.get(3);
                this.mCuartoModelo = this.mListModelos.get(3);
                this.mCuartaMatricula = this.mListMatriculas.get(3);

                this.mLayoutQuintoVehiculo.setVisibility(View.VISIBLE);
                this.mQuintaMarca = this.mListMarcas.get(4);
                this.mQuintoModelo = this.mListModelos.get(4);
                this.mQuintaMatricula = this.mListMatriculas.get(4);

                this.mLayoutSextoVehiculo.setVisibility(View.VISIBLE);
                this.mSextaMarca = this.mListMarcas.get(5);
                this.mSextoModelo = this.mListModelos.get(5);
                this.mSextaMatricula = this.mListMatriculas.get(5);

                this.mLayoutSeptimoVehiculo.setVisibility(View.VISIBLE);
                this.mSeptimaMarca = this.mListMarcas.get(6);
                this.mSeptimoModelo = this.mListModelos.get(6);
                this.mSeptimaMatricula = this.mListMatriculas.get(6);

                this.mLayoutOctavoVehiculo.setVisibility(View.VISIBLE);
                this.mOctavaMarca = this.mListMarcas.get(7);
                this.mOctavoModelo = this.mListModelos.get(7);
                this.mOctavaMatricula = this.mListModelos.get(7);

                this.mLayoutNovenoVehiculo.setVisibility(View.VISIBLE);
                this.mNovenaMarca = this.mListMarcas.get(8);
                this.mNovenoModelo = this.mListModelos.get(8);
                this.mNovenaMatricula = this.mListMatriculas.get(8);
                break;
            case 10:
                this.mLayoutPrimerVehiculo.setVisibility(View.VISIBLE);
                this.mPrimeraMarca = this.mListMarcas.get(0);
                this.mPrimerModelo = this.mListModelos.get(0);
                this.mPrimeraMatricula = this.mListMatriculas.get(0);

                this.mLayoutSegundoVehiculo.setVisibility(View.VISIBLE);
                this.mSegundaMarca = this.mListMarcas.get(1);
                this.mSegundoModelo = this.mListModelos.get(1);
                this.mSegundaMatricula = this.mListMatriculas.get(1);

                this.mLayoutTercerVehiculo.setVisibility(View.VISIBLE);
                this.mTerceraMarca = this.mListMarcas.get(2);
                this.mTercerModelo = this.mListModelos.get(2);
                this.mTerceraMatricula = this.mListMatriculas.get(2);

                this.mLayoutCuartoVehiculo.setVisibility(View.VISIBLE);
                this.mCuartaMarca = this.mListMarcas.get(3);
                this.mCuartoModelo = this.mListModelos.get(3);
                this.mCuartaMatricula = this.mListMatriculas.get(3);

                this.mLayoutQuintoVehiculo.setVisibility(View.VISIBLE);
                this.mQuintaMarca = this.mListMarcas.get(4);
                this.mQuintoModelo = this.mListModelos.get(4);
                this.mQuintaMatricula = this.mListMatriculas.get(4);

                this.mLayoutSextoVehiculo.setVisibility(View.VISIBLE);
                this.mSextaMarca = this.mListMarcas.get(5);
                this.mSextoModelo = this.mListModelos.get(5);
                this.mSextaMatricula = this.mListMatriculas.get(5);

                this.mLayoutSeptimoVehiculo.setVisibility(View.VISIBLE);
                this.mSeptimaMarca = this.mListMarcas.get(6);
                this.mSeptimoModelo = this.mListModelos.get(6);
                this.mSeptimaMatricula = this.mListMatriculas.get(6);

                this.mLayoutOctavoVehiculo.setVisibility(View.VISIBLE);
                this.mOctavaMarca = this.mListMarcas.get(7);
                this.mOctavoModelo = this.mListModelos.get(7);
                this.mOctavaMatricula = this.mListModelos.get(7);

                this.mLayoutNovenoVehiculo.setVisibility(View.VISIBLE);
                this.mNovenaMarca = this.mListMarcas.get(8);
                this.mNovenoModelo = this.mListModelos.get(8);
                this.mNovenaMatricula = this.mListMatriculas.get(8);

                this.mLayoutDecimoVehiculo.setVisibility(View.VISIBLE);
                this.mDecimaMarca = this.mListMarcas.get(9);
                this.mDecimoModelo = this.mListModelos.get(9);
                this.mDecimaMatricula = this.mListMatriculas.get(9);
                break;
            default:
                break;
        }
    }

    /*
    Vamos a contar cuántos vehículos tienen un cliente según si encuentra
    datos con el mismo nombre, apellidos y teléfono del cliente en cuestión.
    La variable va de 0 a 9 (como mucho 10 vehículos por persona)
     */
    private int cuantosVehiculosTieneCliente() {
        int count = 0;
        for (int i = 0; i < this.mListClientes.size(); ++i) {
            if (this.mListClientes.get(i).getmNombre().equals(this.mNombre) &&
                    this.mListClientes.get(i).getmPrimerApellido().equals(this.mPrimerApellido) &&
                    this.mListClientes.get(i).getmSegundoApellido().equals(this.mSegundoApellido) &&
                    this.mListClientes.get(i).getmTelefono().equals(this.mTelefono)) {
                //si coinciden los cuatro campos quiere decir que es la misma persona
                ++count;
                this.mListMarcas.add(this.mListClientes.get(i).getmMarcaVehiculo());
                this.mListModelos.add(this.mListClientes.get(i).getmModeloVehiculo());
                this.mListMatriculas.add(this.mListClientes.get(i).getmMatriculaVehiculo());
            }
        }
        return count;
    }

    private void escribeDatosPersonales() {
        this.mTextNombre.setText(this.mNombre);
        String apellidos = this.mPrimerApellido + " " + this.mSegundoApellido;
        this.mTextApellidos.setText(apellidos);
        this.mTextTelefono.setText(this.mTelefono);
    }

    private void getInfoClientes() {
        this.mNombre = this.getIntent().getStringExtra(EXTRA_NOMBRE);
        this.mPrimerApellido = this.getIntent().getStringExtra(EXTRA_PRIMER_APELLIDO);
        this.mSegundoApellido = this.getIntent().getStringExtra(EXTRA_SEGUNDO_APELLIDO);
        this.mTelefono = this.getIntent().getStringExtra(EXTRA_TELEFONO);
    }

    private void init() {
        this.mListMarcas = new ArrayList<>();
        this.mListMatriculas = new ArrayList<>();
        this.mListModelos = new ArrayList<>();
        //datos personales
        this.initDatosPersonales();
        //primer vehículo
        this.initPrimerVehiculo();
        //segundo vehículo
        this.initSegundoVehiculo();
        //tercer vehículo
        this.initTercerVehiculo();
        //cuarto vehículo
        this.initCuartoVehiculo();
        //quinto vehículo
        this.initQuintoVehiculo();
        //sexto vehículo
        this.initSextoVehiculo();
        //séptimo vehículo
        this.initSeptimoVehiculo();
        //octavo vehículo
        this.initOctavoVehiculo();
        //noveno vehículo
        this.initNovenoVehiculo();
        //décimo vehículo
        this.initDecimoVehiculo();
    }

    private void initDatosPersonales() {
        this.mTextNombre = (TextView) findViewById(R.id.txtvw_nombre_muestra_cliente);
        this.mTextApellidos = (TextView) findViewById(R.id.txtvw_apellidos_muestra_cliente);
        this.mTextTelefono = (TextView) findViewById(R.id.txtvw_telefono_muestra_cliente);
    }

    private void initPrimerVehiculo() {
        this.mLayoutPrimerVehiculo = (LinearLayout) findViewById(R.id.layout_consulta_primer_vehiculo);
        this.mTextPrimeraMarca = (TextView) findViewById(R.id.txtvw_primera_marca_muestra_cliente);
        this.mTextPrimerModelo = (TextView) findViewById(R.id.txtvw_primer_modelo_muestra_cliente);
        this.mTextPrimeraMatricula = (TextView) findViewById(R.id.txtvw_primera_matricula_muestra_cliente);
    }

    private void initSegundoVehiculo() {
        this.mLayoutSegundoVehiculo = (LinearLayout) findViewById(R.id.layout_consulta_segundo_vehiculo);
        this.mTextSegundaMarca = (TextView) findViewById(R.id.txtvw_segunda_marca_muestra_cliente);
        this.mTextSegundoModelo = (TextView) findViewById(R.id.txtvw_segundo_modelo_muestra_cliente);
        this.mTextSegundaMatricula = (TextView) findViewById(R.id.txtvw_segunda_matricula_muestra_cliente);
    }

    private void initTercerVehiculo() {
        this.mLayoutTercerVehiculo = (LinearLayout) findViewById(R.id.layout_consulta_tercer_vehiculo);
        this.mTextTerceraMarca = (TextView) findViewById(R.id.txtvw_tercera_marca_muestra_cliente);
        this.mTextTercerModelo = (TextView) findViewById(R.id.txtvw_tercer_modelo_muestra_cliente);
        this.mTextTerceraMatricula = (TextView) findViewById(R.id.txtvw_tercera_matricula_muestra_cliente);
    }

    private void initCuartoVehiculo() {
        this.mLayoutCuartoVehiculo = (LinearLayout) findViewById(R.id.layout_consulta_cuarto_vehiculo);
        this.mTextCuartaMarca = (TextView) findViewById(R.id.txtvw_cuarta_marca_muestra_cliente);
        this.mTextCuartoModelo = (TextView) findViewById(R.id.txtvw_cuarto_modelo_muestra_cliente);
        this.mTextCuartaMatricula = (TextView) findViewById(R.id.txtvw_cuarta_matricula_muestra_cliente);
    }

    private void initQuintoVehiculo() {
        this.mLayoutQuintoVehiculo = (LinearLayout) findViewById(R.id.layout_consulta_quinto_vehiculo);
        this.mTextQuintaMarca = (TextView) findViewById(R.id.txtvw_quinta_marca_muestra_cliente);
        this.mTextQuintoModelo = (TextView) findViewById(R.id.txtvw_quinto_modelo_muestra_cliente);
        this.mTextQuintaMatricula = (TextView) findViewById(R.id.txtvw_quinta_matricula_muestra_cliente);
    }

    private void initSextoVehiculo() {
        this.mLayoutSextoVehiculo = (LinearLayout) findViewById(R.id.layout_consulta_sexto_vehiculo);
        this.mTextSextaMarca = (TextView) findViewById(R.id.txtvw_sexta_marca_muestra_cliente);
        this.mTextSextoModelo = (TextView) findViewById(R.id.txtvw_sexto_modelo_muestra_cliente);
        this.mTextSextaMatricula = (TextView) findViewById(R.id.txtvw_sexta_matricula_muestra_cliente);
    }

    private void initSeptimoVehiculo() {
        this.mLayoutSeptimoVehiculo = (LinearLayout) findViewById(R.id.layout_consulta_septimo_vehiculo);
        this.mTextSeptimaMarca = (TextView) findViewById(R.id.txtvw_septima_marca_muestra_cliente);
        this.mTextSeptimoModelo = (TextView) findViewById(R.id.txtvw_septimo_modelo_muestra_cliente);
        this.mTextSeptimaMatricula = (TextView) findViewById(R.id.txtvw_septima_matricula_muestra_cliente);
    }

    private void initOctavoVehiculo() {
        this.mLayoutOctavoVehiculo = (LinearLayout) findViewById(R.id.layout_consulta_octavo_vehiculo);
        this.mTextOctavaMarca = (TextView) findViewById(R.id.txtvw_octava_marca_muestra_cliente);
        this.mTextOctavoModelo = (TextView) findViewById(R.id.txtvw_octavo_modelo_muestra_cliente);
        this.mTextOctavaMatricula = (TextView) findViewById(R.id.txtvw_octava_matricula_muestra_cliente);
    }

    private void initNovenoVehiculo() {
        this.mLayoutNovenoVehiculo = (LinearLayout) findViewById(R.id.layout_consulta_noveno_vehiculo);
        this.mTextNovenaMarca = (TextView) findViewById(R.id.txtvw_novena_marca_muestra_cliente);
        this.mTextNovenoModelo = (TextView) findViewById(R.id.txtvw_noveno_modelo_muestra_cliente);
        this.mTextNovenaMatricula = (TextView) findViewById(R.id.txtvw_novena_matricula_muestra_cliente);
    }

    private void initDecimoVehiculo() {
        this.mLayoutDecimoVehiculo = (LinearLayout) findViewById(R.id.layout_consulta_decimo_vehiculo);
        this.mTextDecimaMarca = (TextView) findViewById(R.id.txtvw_decima_marca_muestra_cliente);
        this.mTextDecimoModelo = (TextView) findViewById(R.id.txtvw_decimo_modelo_muestra_cliente);
        this.mTextDecimaMatricula = (TextView) findViewById(R.id.txtvw_decima_matricula_muestra_cliente);
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
        this.mListClientes = list;
    }

    @Override
    public void setPresenter(ConsultaClienteContract.Presenter presenter) {
        this.mPresenter = (ConsultaClientePresenterImpl) presenter;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
