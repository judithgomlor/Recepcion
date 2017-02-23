package com.ot.jgomez.recepcion.views.addcliente;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ot.jgomez.recepcion.R;
import com.ot.jgomez.recepcion.database.DBClientes;

public class AddClienteActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_NEW_CLIENT = 1;
    private static final String EXTRA_NAME = "nombre";
    private static final String EXTRA_FIRST_SURNAME = "primer_apellido";
    private static final String EXTRA_SECOND_SURNAME = "segundo_apellido";
    private static final String EXTRA_MARCA = "marca";
    private static final String EXTRA_MODELO = "modelo";
    private static final String EXTRA_MATRICULA = "matricula";
    private static final String EXTRA_REPARACION = "reparacion";

    private EditText mEditNombre;
    private EditText mEditPrimerApellido;
    private EditText mEditSegundoApellido;
    private EditText mEditTelefono;
    private Button mBtnGuardar;
    private Button mBtnCancelar;


    //añade primer vehículo
    private LinearLayout mLinearLayoutPrimero;
    private Button mBtnAñadeSegundo;
    private Button mBtnDeshacePrimero;
    private EditText mPrimeroEditTextMatricula;
    private EditText mPrimerEditTextMarca;
    private EditText mPrimerEditTextModelo;
    private boolean mAddSegundo = false;
    private boolean mEliminaPrimero = false;

    //añade segundo vehículo
    private LinearLayout mLinearLayoutSegundo;
    private EditText mSegundoEditTextMarca;
    private EditText mSegundoEditTextModelo;
    private EditText mSegundoEditTextMatricula;
    private Button mBtnAñadeTercer;
    private Button mBtnDeshaceSegundo;
    private boolean mAddTercero = false;

    //añade tercer vehículo
    private LinearLayout mLinearLayoutTercero;
    private EditText mTercerEditTextMarca;
    private EditText mTercerEditTextModelo;
    private EditText mTercerEditTextMatricula;
    private Button mBtnAñadeCuarto;
    private Button mBtnDeshaceTercero;
    private boolean mAddCuarto = false;

    //añade cuarto vehículo
    private LinearLayout mLinearLayoutCuarto;
    private EditText mCuartoEditTextMarca;
    private EditText mCuartoEditTextModelo;
    private EditText mCuartoEditTextMatricula;
    private Button mBtnAñadeQuinto;
    private Button mBtnDeshaceCuarto;
    private boolean mAddQuinto = false;

    //añade quinto vehículo
    private LinearLayout mLinearLayoutQuinto;
    private EditText mQuintoEditTextMarca;
    private EditText mQuintoEditTextModelo;
    private EditText mQuintoEditTextMatricula;
    private Button mBtnAñadeSexto;
    private Button mBtnDeshaceQuinto;
    private boolean mAddSexto = false;

    //añade sexto vehículo
    private LinearLayout mLinearLayoutSexto;
    private EditText mSextoEditTextMarca;
    private EditText mSextoEditTextModelo;
    private EditText mSextoEditTextMatricula;
    private Button mBtnAñadeSeptimo;
    private Button mBtnDeshaceSexto;
    private boolean mAddSeptimo = false;

    //añade séptimo vehículo
    private LinearLayout mLinearLayoutSeptimo;
    private EditText mSeptimoEditTextMarca;
    private EditText mSeptimoEditTextModelo;
    private EditText mSeptimoEditTextMatricula;
    private Button mBtnAñadeOctavo;
    private Button mBtnDeshaceSeptimo;
    private boolean mAddOctavo = false;

    //añade octavo vehículo
    private LinearLayout mLinearLayoutOctavo;
    private EditText mOctavoEditTextMarca;
    private EditText mOctavoEditTextModelo;
    private EditText mOctavoEditTextMatricula;
    private Button mBtnAñadeNoveno;
    private Button mBtnDeshaceOctavo;
    private boolean mAddNoveno = false;

    //añade noveno vehículo
    private LinearLayout mLinearLayoutNoveno;
    private EditText mNovenoEditTextMarca;
    private EditText mNovenoEditTextModelo;
    private EditText mNovenoEditTextMatricula;
    private Button mBtnAñadeDecimo;
    private Button mBtnDeshaceNoveno;
    private boolean mAddDecimo = false;

    //añade décimo vehículo
    private LinearLayout mLinearLayoutDecimo;
    private EditText mDecimoEditTextMarca;
    private EditText mDecimoEditTextModelo;
    private EditText mDecimoEditTextMatricula;
    private Button mBtnDeshaceDecimo;
    private boolean mEliminaDecimo = false;

    private boolean mAddReparacion = false;
    private boolean mHayDatos = false;

    private ActionBar mBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cliente);

        if (getIntent().getBooleanExtra(this.EXTRA_REPARACION, false)) this.mAddReparacion = true;

        if (!this.mAddReparacion) {
            this.mBar = getSupportActionBar();
            this.mBar.setDisplayShowHomeEnabled(true);
            this.mBar.setHomeButtonEnabled(true);
            this.mBar.setDisplayHomeAsUpEnabled(true);
            this.mBar.setTitle(R.string.ficha_cliente);
        } else {
            this.mBar = getSupportActionBar();
            this.mBar.setTitle(R.string.ficha_cliente);
        }

        this.mEditNombre = (EditText) findViewById(R.id.editar_nombre);
        this.mEditPrimerApellido = (EditText) findViewById(R.id.editar_primer_apellido);
        this.mEditSegundoApellido = (EditText) findViewById(R.id.editar_segundo_apellido);
        this.mEditTelefono = (EditText) findViewById(R.id.editar_telefono);
        this.mBtnGuardar = (Button) findViewById(R.id.boton_aceptar_nuevo_cliente);
        this.mBtnGuardar.setOnClickListener(this);
        this.mBtnCancelar = (Button) findViewById(R.id.boton_cancelar_nuevo_cliente);
        this.mBtnCancelar.setOnClickListener(this);

        initPrimero();
        initSegundo();
        initTercero();
        initCuarto();
        initQuinto();
        initSexto();
        initSeptimo();
        initOctavo();
        initNoveno();
        initDecimo();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (!this.mAddReparacion) {
                if (this.compruebaBools()) this.dialogExit();
                else finish();
            } else {
                if (this.compruebaBools()) this.dialogExit();
                else finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v == this.mBtnAñadeSegundo) {
            this.viewSegundo();
            this.mAddSegundo = true;
        } else if (v == this.mBtnAñadeTercer) {
            this.viewTercero();
            this.mAddTercero = true;
        } else if (v == this.mBtnAñadeCuarto) {
            this.viewCuarto();
            this.mAddCuarto = true;
        } else if (v == this.mBtnAñadeQuinto) {
            this.viewQuinto();
            this.mAddQuinto = true;
        } else if (v == this.mBtnAñadeSexto) {
            this.viewSexto();
            this.mAddSexto = true;
        } else if (v == this.mBtnAñadeSeptimo) {
            this.viewSeptimo();
            this.mAddSeptimo = true;
        } else if (v == this.mBtnAñadeOctavo) {
            this.viewOctavo();
            this.mAddOctavo = true;
        } else if (v == this.mBtnAñadeNoveno) {
            this.viewNoveno();
            this.mAddNoveno = true;
        } else if (v == this.mBtnAñadeDecimo) {
            this.viewDecimo();
            this.mAddDecimo = true;
            this.mEliminaDecimo = false;
        } else if (v == this.mBtnDeshacePrimero) {
            if (this.mPrimerEditTextMarca.getText().length() != 0 ||
                    this.mPrimerEditTextModelo.getText().length() != 0 ||
                    this.mPrimeroEditTextMatricula.getText().length() != 0) {
                this.dialogGone(1);
            } else {
                this.gonePrimero();
            }
            this.mEliminaPrimero = true;
        } else if (v == this.mBtnDeshaceSegundo) {
            if (this.mSegundoEditTextMarca.getText().length() != 0 ||
                    this.mSegundoEditTextModelo.getText().length() != 0 ||
                    this.mSegundoEditTextMatricula.getText().length() != 0) {
                this.dialogGone(2);
            } else {
                this.goneSegundo();
            }
            this.mAddSegundo = false;
        } else if (v == this.mBtnDeshaceTercero) {
            if (this.mTercerEditTextMarca.getText().length() != 0 ||
                    this.mTercerEditTextModelo.getText().length() != 0 ||
                    this.mTercerEditTextMatricula.getText().length() != 0) {
                this.dialogGone(3);
            } else {
                this.goneTercero();
            }
            this.mAddTercero = false;
        } else if (v == this.mBtnDeshaceCuarto) {
            if (this.mCuartoEditTextMarca.getText().length() != 0 ||
                    this.mCuartoEditTextModelo.getText().length() != 0 ||
                    this.mCuartoEditTextMatricula.getText().length() != 0) {
                this.dialogGone(4);
            } else {
                this.goneCuarto();
            }
            this.mAddCuarto = false;
        } else if (v == this.mBtnDeshaceQuinto) {
            if (this.mQuintoEditTextMarca.getText().length() != 0 ||
                    this.mQuintoEditTextModelo.getText().length() != 0 ||
                    this.mQuintoEditTextMatricula.getText().length() != 0) {
                this.dialogGone(5);
            } else {
                this.goneQuinto();
            }
            this.mAddQuinto = false;
        } else if (v == this.mBtnDeshaceSexto) {
            if (this.mSextoEditTextMarca.getText().length() != 0 ||
                    this.mSextoEditTextModelo.getText().length() != 0 ||
                    this.mSextoEditTextMatricula.getText().length() != 0) {
                this.dialogGone(6);
            } else {
                this.goneSexto();
            }
            this.mAddSexto = false;
        } else if (v == this.mBtnDeshaceSeptimo) {
            if (this.mSeptimoEditTextMarca.getText().length() != 0 ||
                    this.mSeptimoEditTextModelo.getText().length() != 0 ||
                    this.mSeptimoEditTextMatricula.getText().length() != 0) {
                this.dialogGone(7);
            } else {
                this.goneSeptimo();
            }
            this.mAddSeptimo = false;
        } else if (v == this.mBtnDeshaceOctavo) {
            if (this.mOctavoEditTextMarca.getText().length() != 0 ||
                    this.mOctavoEditTextModelo.getText().length() != 0 ||
                    this.mOctavoEditTextMatricula.getText().length() != 0) {
                this.dialogGone(8);
            } else {
                this.goneOctavo();
            }
            this.mAddOctavo = false;
        } else if (v == this.mBtnDeshaceNoveno) {
            if (this.mNovenoEditTextMarca.getText().length() != 0 ||
                    this.mNovenoEditTextModelo.getText().length() != 0 ||
                    this.mNovenoEditTextMatricula.getText().length() != 0) {
                this.dialogGone(9);
            } else {
                this.goneNoveno();
            }
            this.mAddNoveno = false;
        } else if (v == this.mBtnDeshaceDecimo) {
            if (this.mDecimoEditTextMarca.getText().length() != 0 ||
                    this.mDecimoEditTextModelo.getText().length() != 0 ||
                    this.mDecimoEditTextMatricula.getText().length() != 0) {
                this.dialogGone(10);
            } else {
                this.goneDecimo();
            }
            this.mEliminaDecimo = true;
            this.mAddDecimo = false;
        } else if (v == this.mBtnCancelar) {
            if (this.compruebaBools()) this.dialogExit();
            else finish();
        } else if (v == this.mBtnGuardar) {
            this.compruebaCampos();
        }
    }

    private void compruebaCampos() {
        String nombre, primerApellido, segundoApellido, telefono, matricula, modelo, marca;
        if (this.mEditNombre.getText().length() == 0) {
            this.mEditNombre.setError(getApplicationContext().getResources().getString(R.string.falta_nombre));
        } else if (this.mEditPrimerApellido.getText().length() == 0) {
            this.mEditPrimerApellido.setError(getApplicationContext().getResources().getString(R.string.falta_primer_apellido));
        } else if (this.mEditTelefono.getText().length() == 0) {
            this.mEditTelefono.setError(getApplicationContext().getResources().getString(R.string.falta_telefono));
        } else {
            nombre = this.mEditNombre.getText().toString();
            primerApellido = this.mEditPrimerApellido.getText().toString();
            if (this.mEditSegundoApellido.getText().length() == 0) {
                segundoApellido = "";
            } else {
                segundoApellido = this.mEditSegundoApellido.getText().toString();
            }
            telefono = this.mEditTelefono.getText().toString();

            if (!this.mEliminaPrimero) {
                if (this.mPrimerEditTextMarca.getText().length() == 0) {
                    this.mPrimerEditTextMarca.setError(getApplicationContext().getResources().getString(R.string.falta_marca));
                } else if (this.mPrimerEditTextModelo.getText().length() == 0) {
                    this.mPrimerEditTextModelo.setError(getApplicationContext().getResources().getString(R.string.falta_modelo));
                } else if (this.mPrimeroEditTextMatricula.getText().length() == 0) {
                    this.mPrimeroEditTextMatricula.setError(getApplicationContext().getResources().getString(R.string.falta_matricula));
                } else {
                    marca = this.mPrimerEditTextMarca.getText().toString();
                    modelo = this.mPrimerEditTextModelo.getText().toString();
                    matricula = this.mPrimeroEditTextMatricula.getText().toString();
                    this.saveChanges(nombre, primerApellido, segundoApellido, telefono, marca, modelo, matricula);
                }
            }
            if (this.mAddSegundo) {
                if (this.mSegundoEditTextMarca.getText().length() == 0) {
                    this.mSegundoEditTextMarca.setError(getApplicationContext().getResources().getString(R.string.falta_marca));
                } else if (this.mSegundoEditTextModelo.getText().length() == 0) {
                    this.mSegundoEditTextModelo.setError(getApplicationContext().getResources().getString(R.string.falta_modelo));
                } else if (this.mSegundoEditTextMatricula.getText().length() == 0) {
                    this.mSegundoEditTextMatricula.setError(getApplicationContext().getResources().getString(R.string.falta_matricula));
                } else {
                    marca = this.mSegundoEditTextMarca.getText().toString();
                    modelo = this.mSegundoEditTextModelo.getText().toString();
                    matricula = this.mSegundoEditTextMatricula.getText().toString();
                    this.saveChanges(nombre, primerApellido, segundoApellido, telefono, marca, modelo, matricula);
                }
            }
            if (this.mAddTercero) {
                if (this.mTercerEditTextMarca.getText().length() == 0) {
                    this.mTercerEditTextMarca.setError(getApplicationContext().getResources().getString(R.string.falta_marca));
                } else if (this.mTercerEditTextModelo.getText().length() == 0) {
                    this.mTercerEditTextModelo.setError(getApplicationContext().getResources().getString(R.string.falta_modelo));
                } else if (this.mTercerEditTextMatricula.getText().length() == 0) {
                    this.mTercerEditTextMatricula.setError(getApplicationContext().getResources().getString(R.string.falta_matricula));
                } else {
                    marca = this.mTercerEditTextMarca.getText().toString();
                    modelo = this.mTercerEditTextModelo.getText().toString();
                    matricula = this.mTercerEditTextMatricula.getText().toString();
                    this.saveChanges(nombre, primerApellido, segundoApellido, telefono, marca, modelo, matricula);
                }
            }
            if (this.mAddCuarto) {
                if (this.mCuartoEditTextMarca.getText().length() == 0) {
                    this.mCuartoEditTextMarca.setError(getApplicationContext().getResources().getString(R.string.falta_marca));
                } else if (this.mCuartoEditTextModelo.getText().length() == 0) {
                    this.mCuartoEditTextModelo.setError(getApplicationContext().getResources().getString(R.string.falta_modelo));
                } else if (this.mCuartoEditTextMatricula.getText().length() == 0) {
                    this.mCuartoEditTextMatricula.setError(getApplicationContext().getResources().getString(R.string.falta_matricula));
                } else {
                    marca = this.mCuartoEditTextMarca.getText().toString();
                    modelo = this.mCuartoEditTextModelo.getText().toString();
                    matricula = this.mCuartoEditTextMatricula.getText().toString();
                    this.saveChanges(nombre, primerApellido, segundoApellido, telefono, marca, modelo, matricula);
                }
            }
            if (this.mAddQuinto) {
                if (this.mQuintoEditTextMarca.getText().length() == 0) {
                    this.mQuintoEditTextMarca.setError(getApplicationContext().getResources().getString(R.string.falta_marca));
                } else if (this.mQuintoEditTextModelo.getText().length() == 0) {
                    this.mQuintoEditTextModelo.setError(getApplicationContext().getResources().getString(R.string.falta_modelo));
                } else if (this.mQuintoEditTextMatricula.getText().length() == 0) {
                    this.mQuintoEditTextMatricula.setError(getApplicationContext().getResources().getString(R.string.falta_matricula));
                } else {
                    marca = this.mQuintoEditTextMarca.getText().toString();
                    modelo = this.mQuintoEditTextModelo.getText().toString();
                    matricula = this.mQuintoEditTextMatricula.getText().toString();
                    this.saveChanges(nombre, primerApellido, segundoApellido, telefono, marca, modelo, matricula);
                }
            }
            if (this.mAddSexto) {
                if (this.mSextoEditTextMarca.getText().length() == 0) {
                    this.mSextoEditTextMarca.setError(getApplicationContext().getResources().getString(R.string.falta_marca));
                } else if (this.mSextoEditTextModelo.getText().length() == 0) {
                    this.mSextoEditTextModelo.setError(getApplicationContext().getResources().getString(R.string.falta_modelo));
                } else if (this.mSextoEditTextMatricula.getText().length() == 0) {
                    this.mSextoEditTextMatricula.setError(getApplicationContext().getResources().getString(R.string.falta_matricula));
                } else {
                    marca = this.mSextoEditTextMarca.getText().toString();
                    modelo = this.mSextoEditTextModelo.getText().toString();
                    matricula = this.mSextoEditTextMatricula.getText().toString();
                    this.saveChanges(nombre, primerApellido, segundoApellido, telefono, marca, modelo, matricula);
                }
            }
            if (this.mAddSeptimo) {
                if (this.mSeptimoEditTextMarca.getText().length() == 0) {
                    this.mSeptimoEditTextMarca.setError(getApplicationContext().getResources().getString(R.string.falta_marca));
                } else if (this.mSeptimoEditTextModelo.getText().length() == 0) {
                    this.mSeptimoEditTextModelo.setError(getApplicationContext().getResources().getString(R.string.falta_modelo));
                } else if (this.mSeptimoEditTextMatricula.getText().length() == 0) {
                    this.mSeptimoEditTextMatricula.setError(getApplicationContext().getResources().getString(R.string.falta_matricula));
                } else {
                    marca = this.mSeptimoEditTextMarca.getText().toString();
                    modelo = this.mSeptimoEditTextModelo.getText().toString();
                    matricula = this.mSeptimoEditTextMatricula.getText().toString();
                    this.saveChanges(nombre, primerApellido, segundoApellido, telefono, marca, modelo, matricula);
                }
            }
            if (this.mAddOctavo) {
                if (this.mOctavoEditTextMarca.getText().length() == 0) {
                    this.mOctavoEditTextMarca.setError(getApplicationContext().getResources().getString(R.string.falta_marca));
                } else if (this.mOctavoEditTextModelo.getText().length() == 0) {
                    this.mOctavoEditTextModelo.setError(getApplicationContext().getResources().getString(R.string.falta_modelo));
                } else if (this.mOctavoEditTextMatricula.getText().length() == 0) {
                    this.mOctavoEditTextMatricula.setError(getApplicationContext().getResources().getString(R.string.falta_matricula));
                } else {
                    marca = this.mOctavoEditTextMarca.getText().toString();
                    modelo = this.mOctavoEditTextModelo.getText().toString();
                    matricula = this.mOctavoEditTextMatricula.getText().toString();
                    this.saveChanges(nombre, primerApellido, segundoApellido, telefono, marca, modelo, matricula);
                }
            }
            if (this.mAddNoveno) {
                if (this.mNovenoEditTextMarca.getText().length() == 0) {
                    this.mNovenoEditTextMarca.setError(getApplicationContext().getResources().getString(R.string.falta_marca));
                } else if (this.mNovenoEditTextModelo.getText().length() == 0) {
                    this.mNovenoEditTextModelo.setError(getApplicationContext().getResources().getString(R.string.falta_modelo));
                } else if (this.mNovenoEditTextMatricula.getText().length() == 0) {
                    this.mNovenoEditTextMatricula.setError(getApplicationContext().getResources().getString(R.string.falta_matricula));
                } else {
                    marca = this.mNovenoEditTextMarca.getText().toString();
                    modelo = this.mNovenoEditTextModelo.getText().toString();
                    matricula = this.mNovenoEditTextMatricula.getText().toString();
                    this.saveChanges(nombre, primerApellido, segundoApellido, telefono, marca, modelo, matricula);
                }
            }
            if (this.mAddDecimo) {
                if (this.mNovenoEditTextMarca.getText().length() == 0) {
                    this.mNovenoEditTextMarca.setError(getApplicationContext().getResources().getString(R.string.falta_marca));
                } else if (this.mNovenoEditTextModelo.getText().length() == 0) {
                    this.mNovenoEditTextModelo.setError(getApplicationContext().getResources().getString(R.string.falta_modelo));
                } else if (this.mNovenoEditTextMatricula.getText().length() == 0) {
                    this.mNovenoEditTextMatricula.setError(getApplicationContext().getResources().getString(R.string.falta_matricula));
                } else {
                    marca = this.mDecimoEditTextMarca.getText().toString();
                    modelo = this.mDecimoEditTextModelo.getText().toString();
                    matricula = this.mDecimoEditTextMatricula.getText().toString();
                    this.saveChanges(nombre, primerApellido, segundoApellido, telefono, marca, modelo, matricula);
                }
            }
        }
    }

    private void saveChanges(String nombre, String primerApellido, String segundoApellido, String telefono,
                             String marca, String modelo, String matricula) {
        String aux_nombre = nombre.substring(0, 1).toUpperCase() + nombre.substring(1, nombre.length());
        String aux_primer_apellido = primerApellido.substring(0, 1).toUpperCase() + primerApellido.substring(1, primerApellido.length());
        String aux_segundo_apellido = segundoApellido.substring(0, 1).toUpperCase() + segundoApellido.substring(1, segundoApellido.length());
        String aux_nombre_apellidos = aux_nombre + " " + aux_primer_apellido + " " + aux_segundo_apellido;
        String aux_marca = marca.substring(0, 1).toUpperCase() + marca.substring(1, marca.length());
        String aux_modelo = modelo.substring(0, 1).toUpperCase() + modelo.substring(1, modelo.length());
        String aux_matricula = matricula.substring(0, 4) + matricula.substring(4, matricula.length()).toUpperCase();

        DBClientes cliente = new DBClientes(aux_nombre, aux_primer_apellido, aux_segundo_apellido,
                aux_nombre_apellidos, aux_marca, aux_modelo, aux_matricula, telefono);
        cliente.save();
        Toast.makeText(this, "Cliente guardado", Toast.LENGTH_LONG).show();
        Intent returnIntent = new Intent();
        returnIntent.putExtra(this.EXTRA_NAME, aux_nombre);
        returnIntent.putExtra(this.EXTRA_FIRST_SURNAME, aux_primer_apellido);
        returnIntent.putExtra(this.EXTRA_SECOND_SURNAME, aux_segundo_apellido);
        returnIntent.putExtra(this.EXTRA_MARCA, aux_marca);
        returnIntent.putExtra(this.EXTRA_MODELO, aux_modelo);
        returnIntent.putExtra(this.EXTRA_MATRICULA, aux_matricula);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    private boolean compruebaBools() {
        if (this.mAddSegundo || this.mAddTercero || this.mAddCuarto || this.mAddQuinto ||
                this.mAddSexto || this.mAddSeptimo || this.mAddOctavo || this.mAddNoveno ||
                this.mAddDecimo ||
                this.mEditNombre.getText().length() != 0 || this.mEditTelefono.getText().length() != 0 ||
                this.mEditPrimerApellido.getText().length() != 0 || this.mEditSegundoApellido.getText().length() != 0 ||
                this.mPrimerEditTextMarca.getText().length() != 0 || this.mPrimerEditTextModelo.getText().length() != 0 ||
                this.mPrimeroEditTextMatricula.getText().length() != 0) {
            return true;
        } else {
            return false;
        }
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

    private void dialogGone(final int id) {
        final Dialog dialog = new Dialog(this);
       // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog);

        //set the custom dialog components
        //TextView textoTitulo = (TextView) dialog.findViewById(R.id.txtvw_titulo_dialog);
        //textoTitulo.setText(R.string.confirmacion);

        dialog.setTitle(R.string.confirmacion);

        TextView textoDialog = (TextView) findViewById(R.id.txtvw_custom_dialog);
        textoDialog.setText(R.string.texto_custom_dialog_eliminar_elemento);
        Button buttonCancelar = (Button) findViewById(R.id.boton_cancelar_dialog);
        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Button buttonAceptar = (Button) findViewById(R.id.boton_aceptar_dialog);
        buttonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reseteaValores(id);
            }
        });

        dialog.show();
    }

    private void reseteaValores(int id) {
        switch (id) {
            case 1:
                this.mPrimerEditTextMarca.setText("");
                this.mPrimeroEditTextMatricula.setText("");
                this.mPrimerEditTextModelo.setText("");
                break;
            case 2:
                this.mSegundoEditTextMarca.setText("");
                this.mSegundoEditTextModelo.setText("");
                this.mSegundoEditTextMatricula.setText("");
                break;
            case 3:
                this.mTercerEditTextMarca.setText("");
                this.mTercerEditTextModelo.setText("");
                this.mTercerEditTextMatricula.setText("");
                break;
            case 4:
                this.mCuartoEditTextMarca.setText("");
                this.mCuartoEditTextModelo.setText("");
                this.mCuartoEditTextMatricula.setText("");
                break;
            case 5:
                this.mQuintoEditTextMarca.setText("");
                this.mQuintoEditTextModelo.setText("");
                this.mQuintoEditTextMatricula.setText("");
                break;
            case 6:
                this.mSextoEditTextMarca.setText("");
                this.mSextoEditTextModelo.setText("");
                this.mSextoEditTextMatricula.setText("");
                break;
            case 7:
                this.mSeptimoEditTextMarca.setText("");
                this.mSeptimoEditTextModelo.setText("");
                this.mSeptimoEditTextMatricula.setText("");
                break;
            case 8:
                this.mOctavoEditTextMarca.setText("");
                this.mOctavoEditTextModelo.setText("");
                this.mOctavoEditTextMatricula.setText("");
                break;
            case 9:
                this.mNovenoEditTextMarca.setText("");
                this.mNovenoEditTextModelo.setText("");
                this.mNovenoEditTextMatricula.setText("");
                break;
            case 10:
                this.mDecimoEditTextMarca.setText("");
                this.mDecimoEditTextModelo.setText("");
                this.mDecimoEditTextMatricula.setText("");
                break;
            default:
                break;
        }
    }

    private void initPrimero() {
        this.mLinearLayoutPrimero = (LinearLayout) findViewById(R.id.añade_primer_vehiculo);
        this.mPrimerEditTextMarca = (EditText) findViewById(R.id.editar_primera_marca);
        this.mPrimerEditTextModelo = (EditText) findViewById(R.id.editar_primer_modelo);
        this.mPrimeroEditTextMatricula = (EditText) findViewById(R.id.editar_primera_matricula);
        this.mBtnAñadeSegundo = (Button) findViewById(R.id.boton_añade_segundo);
        this.mBtnAñadeSegundo.setOnClickListener(this);
        this.mBtnDeshacePrimero = (Button) findViewById(R.id.boton_deshacer_primer_vehiculo);
        this.mBtnDeshacePrimero.setOnClickListener(this);
    }

    private void initSegundo() {
        this.mLinearLayoutSegundo = (LinearLayout) findViewById(R.id.añade_segundo_vehiculo);
        this.mLinearLayoutSegundo.setVisibility(View.GONE);
        this.mSegundoEditTextMarca = (EditText) findViewById(R.id.editar_segunda_marca);
        this.mSegundoEditTextModelo = (EditText) findViewById(R.id.editar_segundo_modelo);
        this.mSegundoEditTextMatricula = (EditText) findViewById(R.id.editar_segunda_matricula);
        this.mBtnAñadeTercer = (Button) findViewById(R.id.boton_añade_tercero);
        this.mBtnDeshaceSegundo = (Button) findViewById(R.id.boton_deshacer_segundo_vehiculo);
    }

    private void initTercero() {
        this.mLinearLayoutTercero = (LinearLayout) findViewById(R.id.añade_tercer_vehiculo);
        this.mLinearLayoutTercero.setVisibility(View.GONE);
        this.mTercerEditTextMarca = (EditText) findViewById(R.id.editar_tercera_marca);
        this.mTercerEditTextModelo = (EditText) findViewById(R.id.editar_tercer_modelo);
        this.mTercerEditTextMatricula = (EditText) findViewById(R.id.editar_tercera_matricula);
        this.mBtnAñadeCuarto = (Button) findViewById(R.id.boton_añade_cuarto);
        this.mBtnDeshaceTercero = (Button) findViewById(R.id.boton_deshacer_tercer_vehiculo);
    }

    private void initCuarto() {
        this.mLinearLayoutCuarto = (LinearLayout) findViewById(R.id.añade_cuarto_vehiculo);
        this.mLinearLayoutCuarto.setVisibility(View.GONE);
        this.mCuartoEditTextMarca = (EditText) findViewById(R.id.editar_cuarta_marca);
        this.mCuartoEditTextModelo = (EditText) findViewById(R.id.editar_cuarto_modelo);
        this.mCuartoEditTextMatricula = (EditText) findViewById(R.id.editar_cuarta_matricula);
        this.mBtnAñadeQuinto = (Button) findViewById(R.id.boton_añade_quinto);
        this.mBtnDeshaceCuarto = (Button) findViewById(R.id.boton_deshacer_cuarto_vehiculo);
    }

    private void initQuinto() {
        this.mLinearLayoutQuinto = (LinearLayout) findViewById(R.id.añade_quinto_vehiculo);
        this.mLinearLayoutQuinto.setVisibility(View.GONE);
        this.mQuintoEditTextMarca = (EditText) findViewById(R.id.editar_quinta_marca);
        this.mQuintoEditTextModelo = (EditText) findViewById(R.id.editar_quinto_modelo);
        this.mQuintoEditTextModelo.setVisibility(View.GONE);
        this.mQuintoEditTextMatricula = (EditText) findViewById(R.id.editar_quinta_matricula);
        this.mBtnAñadeSexto = (Button) findViewById(R.id.boton_añade_sexto);
        this.mBtnDeshaceQuinto = (Button) findViewById(R.id.boton_deshacer_quinto_vehiculo);
    }

    private void initSexto() {
        this.mLinearLayoutSexto = (LinearLayout) findViewById(R.id.añade_sexto_vehiculo);
        this.mLinearLayoutSexto.setVisibility(View.GONE);
        this.mSextoEditTextMarca = (EditText) findViewById(R.id.editar_sexta_marca);
        this.mSextoEditTextModelo = (EditText) findViewById(R.id.editar_sexto_modelo);
        this.mSextoEditTextMatricula = (EditText) findViewById(R.id.editar_sexta_matricula);
        this.mBtnAñadeSeptimo = (Button) findViewById(R.id.boton_añade_septimo);
        this.mBtnDeshaceSexto = (Button) findViewById(R.id.boton_deshacer_sexto_vehiculo);
    }

    private void initSeptimo() {
        this.mLinearLayoutSeptimo = (LinearLayout) findViewById(R.id.añade_septimo_vehiculo);
        this.mLinearLayoutSeptimo.setVisibility(View.GONE);
        this.mSeptimoEditTextMarca = (EditText) findViewById(R.id.editar_septima_marca);
        this.mSeptimoEditTextModelo = (EditText) findViewById(R.id.editar_septimo_modelo);
        this.mSeptimoEditTextMatricula = (EditText) findViewById(R.id.editar_septima_matricula);
        this.mBtnAñadeOctavo = (Button) findViewById(R.id.boton_añade_octavo);
        this.mBtnDeshaceSeptimo = (Button) findViewById(R.id.boton_deshacer_septimo_vehiculo);
    }

    private void initOctavo() {
        this.mLinearLayoutOctavo = (LinearLayout) findViewById(R.id.añade_octavo_vehiculo);
        this.mLinearLayoutOctavo.setVisibility(View.GONE);
        this.mOctavoEditTextMarca = (EditText) findViewById(R.id.editar_octava_marca);
        this.mOctavoEditTextModelo = (EditText) findViewById(R.id.editar_octavo_modelo);
        this.mOctavoEditTextMatricula = (EditText) findViewById(R.id.editar_octava_matricula);
        this.mBtnAñadeNoveno = (Button) findViewById(R.id.boton_añade_noveno);
        this.mBtnDeshaceOctavo = (Button) findViewById(R.id.boton_deshacer_octavo_vehiculo);
    }

    private void initNoveno() {
        this.mLinearLayoutNoveno = (LinearLayout) findViewById(R.id.añade_noveno_vehiculo);
        this.mLinearLayoutNoveno.setVisibility(View.GONE);
        this.mNovenoEditTextMarca = (EditText) findViewById(R.id.editar_novena_marca);
        this.mNovenoEditTextModelo = (EditText) findViewById(R.id.editar_noveno_modelo);
        this.mNovenoEditTextMatricula = (EditText) findViewById(R.id.editar_novena_matricula);
        this.mBtnAñadeDecimo = (Button) findViewById(R.id.boton_añade_decimo);
        this.mBtnDeshaceNoveno = (Button) findViewById(R.id.boton_deshacer_noveno_vehiculo);
    }

    private void initDecimo() {
        this.mLinearLayoutDecimo = (LinearLayout) findViewById(R.id.añade_decimo_vehiculo);
        this.mLinearLayoutDecimo.setVisibility(View.GONE);
        this.mDecimoEditTextMarca = (EditText) findViewById(R.id.editar_decima_marca);
        this.mDecimoEditTextModelo = (EditText) findViewById(R.id.editar_decimo_modelo);
        this.mDecimoEditTextMatricula = (EditText) findViewById(R.id.editar_decima_matricula);
        this.mBtnDeshaceDecimo = (Button) findViewById(R.id.boton_deshacer_decimo_vehiculo);
    }

    private void gonePrimero() {
        this.mLinearLayoutPrimero.setVisibility(View.GONE);
    }

    private void viewSegundo() {
        this.mLinearLayoutSegundo.setVisibility(View.VISIBLE);
        this.mBtnAñadeTercer.setOnClickListener(this);
        this.mBtnDeshaceSegundo.setOnClickListener(this);
        this.mBtnAñadeSegundo.setVisibility(View.GONE);
    }

    private void goneSegundo() {
        this.mLinearLayoutSegundo.setVisibility(View.GONE);
        this.mBtnAñadeSegundo.setVisibility(View.VISIBLE);
    }

    private void viewTercero() {
        this.mLinearLayoutTercero.setVisibility(View.VISIBLE);
        this.mBtnAñadeCuarto.setOnClickListener(this);
        this.mBtnDeshaceTercero.setOnClickListener(this);
        //hay que dejar de ver el botón de añadir tercero
        this.mBtnAñadeTercer.setVisibility(View.GONE);
    }

    private void goneTercero() {
        this.mLinearLayoutTercero.setVisibility(View.GONE);
        //hay que volver a dejar ver el botón de añadir tercero
        this.mBtnAñadeTercer.setVisibility(View.VISIBLE);
    }

    private void viewCuarto() {
        this.mLinearLayoutCuarto.setVisibility(View.VISIBLE);
        this.mBtnAñadeQuinto.setOnClickListener(this);
        this.mBtnDeshaceCuarto.setOnClickListener(this);
        //hay que dejar de ver el botón de añadir cuarto
        this.mBtnAñadeCuarto.setVisibility(View.GONE);
    }

    private void goneCuarto() {
        this.mLinearLayoutCuarto.setVisibility(View.GONE);
        //hay que volver a dejar ver el botón de añadir cuarto
        this.mBtnAñadeCuarto.setVisibility(View.VISIBLE);
    }

    private void viewQuinto() {
        this.mLinearLayoutQuinto.setVisibility(View.VISIBLE);
        this.mBtnAñadeSexto.setOnClickListener(this);
        this.mBtnDeshaceQuinto.setOnClickListener(this);
        //hay que dejar de ver el botón de añadir quinto
        this.mBtnAñadeQuinto.setVisibility(View.GONE);
    }

    private void goneQuinto() {
        this.mLinearLayoutQuinto.setVisibility(View.GONE);
        //hay que volver a dejar ver el botón de añadir quinto
        this.mBtnAñadeQuinto.setVisibility(View.VISIBLE);
    }

    private void viewSexto() {
        this.mLinearLayoutSexto.setVisibility(View.VISIBLE);
        this.mBtnAñadeSeptimo.setOnClickListener(this);
        this.mBtnDeshaceSexto.setOnClickListener(this);
        //hay que dejar de ver el botón de añadir sexto
        this.mBtnAñadeSexto.setVisibility(View.GONE);
    }

    private void goneSexto() {
        this.mLinearLayoutSexto.setVisibility(View.GONE);
        //hay que volver a dejar ver el botón de añadir sexto
        this.mBtnAñadeSexto.setVisibility(View.VISIBLE);
    }

    private void viewSeptimo() {
        this.mLinearLayoutSeptimo.setVisibility(View.VISIBLE);
        this.mBtnAñadeOctavo.setOnClickListener(this);
        this.mBtnDeshaceSeptimo.setOnClickListener(this);
        //hay que dejar de ver el botón de añadir séptimo
        this.mBtnAñadeSeptimo.setVisibility(View.GONE);
    }

    private void goneSeptimo() {
        this.mLinearLayoutSeptimo.setVisibility(View.GONE);
        //hay que volver a dejar ver el botón de añadir septimo
        this.mBtnAñadeSeptimo.setVisibility(View.VISIBLE);
    }

    private void viewOctavo() {
        this.mLinearLayoutOctavo.setVisibility(View.VISIBLE);
        this.mBtnAñadeNoveno.setOnClickListener(this);
        this.mBtnDeshaceOctavo.setOnClickListener(this);
        //hay que dejar de ver el botón de añadir octavo
        this.mBtnAñadeOctavo.setVisibility(View.GONE);
    }

    private void goneOctavo() {
        this.mLinearLayoutOctavo.setVisibility(View.GONE);
        //hay que volver a dejar ver el botón de añadir octavo
        this.mBtnAñadeOctavo.setVisibility(View.VISIBLE);
    }

    private void viewNoveno() {
        this.mLinearLayoutNoveno.setVisibility(View.VISIBLE);
        this.mBtnAñadeDecimo.setOnClickListener(this);
        this.mBtnDeshaceNoveno.setOnClickListener(this);
        //hay que dejar de ver el botón de añadir noveno
        this.mBtnAñadeNoveno.setVisibility(View.GONE);
    }

    private void goneNoveno() {
        this.mLinearLayoutNoveno.setVisibility(View.GONE);
        //hay que volver a dejar ver el botón de añadir noveno
        this.mBtnAñadeNoveno.setVisibility(View.VISIBLE);

    }

    private void viewDecimo() {
        this.mLinearLayoutDecimo.setVisibility(View.VISIBLE);
        this.mBtnDeshaceDecimo.setOnClickListener(this);
        //hay que dejar de ver el botón de añadir décimo
        this.mBtnAñadeDecimo.setVisibility(View.GONE);
    }

    private void goneDecimo() {
        this.mLinearLayoutDecimo.setVisibility(View.GONE);
        //hay que volver a dejar ver el botón de añadir décimo
        this.mBtnAñadeDecimo.setVisibility(View.VISIBLE);
    }
}
