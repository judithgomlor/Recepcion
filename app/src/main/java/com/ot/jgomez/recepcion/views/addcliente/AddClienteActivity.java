package com.ot.jgomez.recepcion.views.addcliente;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

    private EditText mEditNombre;
    private EditText mEditPrimerApellido;
    private EditText mEditSegundoApellido;
    private EditText mEditTelefono;
    private Button mBtnGuardar;
    private Button mBtnCancelar;


    //añade primer vehículo
    private LinearLayout mLinearLayoutPrimero;
    private ImageView mPrimerImageViewImage;
    private TextView mPrimerTextViewTitulo;
    private TextView mPrimerTextViewMarca;
    private TextView mPrimerTextViewModelo;
    private TextView mPrimerTextViewMatricula;
    private Button mBtnAñadeSegundo;
    private Button mBtnDeshacePrimero;
    private EditText mPrimeroEditTextMatricula;
    private EditText mPrimerEditTextMarca;
    private EditText mPrimerEditTextModelo;
    private boolean mAddSegundo = false;
    private boolean mEliminaPrimero = false;

    //añade segundo vehículo
    private LinearLayout mLinearLayoutSegundo;
    private ImageView mSegundoImageViewImage;
    private TextView mSegundoTextViewTitulo;
    private TextView mSegundoTextViewMarca;
    private TextView mSegundoTextViewModelo;
    private TextView mSegundaTextViewMatricula;
    private EditText mSegundoEditTextMarca;
    private EditText mSegundoEditTextModelo;
    private EditText mSegundoEditTextMatricula;
    private Button mBtnAñadeTercer;
    private Button mBtnDeshaceSegundo;
    private boolean mAddTercero = false;

    //añade tercer vehículo
    private LinearLayout mLinearLayoutTercero;
    private ImageView mTercerImageViewImage;
    private TextView mTercerTextViewTitulo;
    private TextView mTercerTextViewMarca;
    private TextView mTercerTextViewModelo;
    private TextView mTercerTextViewMatricula;
    private EditText mTercerEditTextMarca;
    private EditText mTercerEditTextModelo;
    private EditText mTercerEditTextMatricula;
    private Button mBtnAñadeCuarto;
    private Button mBtnDeshaceTercero;
    private boolean mAddCuarto = false;

    //añade cuarto vehículo
    private LinearLayout mLinearLayoutCuarto;
    private ImageView mCuartaImageViewImage;
    private TextView mCuartoTextViewTitulo;
    private TextView mCuartoTextViewMarca;
    private TextView mCuartoTextViewModelo;
    private TextView mCuartoTextViewMatricula;
    private EditText mCuartoEditTextMarca;
    private EditText mCuartoEditTextModelo;
    private EditText mCuartoEditTextMatricula;
    private Button mBtnAñadeQuinto;
    private Button mBtnDeshaceCuarto;
    private boolean mAddQuinto = false;

    //añade quinto vehículo
    private LinearLayout mLinearLayoutQuinto;
    private ImageView mQuintoImageViewImage;
    private TextView mQuintoTextViewTitulo;
    private TextView mQuintoTextViewMarca;
    private TextView mQuintoTextViewModelo;
    private TextView mQuintoTextViewMatricula;
    private EditText mQuintoEditTextMarca;
    private EditText mQuintoEditTextModelo;
    private EditText mQuintoEditTextMatricula;
    private Button mBtnAñadeSexto;
    private Button mBtnDeshaceQuinto;
    private boolean mAddSexto = false;

    //añade sexto vehículo
    private LinearLayout mLinearLayoutSexto;
    private ImageView mSextoImageViewImage;
    private TextView mSextoTextViewTitulo;
    private TextView mSextoTextViewMarca;
    private TextView mSextoTextViewModelo;
    private TextView mSextaTextViewMatricula;
    private EditText mSextoEditTextMarca;
    private EditText mSextoEditTextModelo;
    private EditText mSextoEditTextMatricula;
    private Button mBtnAñadeSeptimo;
    private Button mBtnDeshaceSexto;
    private boolean mAddSeptimo = false;

    //añade séptimo vehículo
    private LinearLayout mLinearLayoutSeptimo;
    private ImageView mSeptimoImageViewImage;
    private TextView mSeptimoTextViewTitulo;
    private TextView mSeptimoTextViewMarca;
    private TextView mSeptimoTextViewModelo;
    private TextView mSeptimaTextViewMatricula;
    private EditText mSeptimoEditTextMarca;
    private EditText mSeptimoEditTextModelo;
    private EditText mSeptimoEditTextMatricula;
    private Button mBtnAñadeOctavo;
    private Button mBtnDeshaceSeptimo;
    private boolean mAddOctavo = false;

    //añade octavo vehículo
    private LinearLayout mLinearLayoutOctavo;
    private ImageView mOctavoImageViewImage;
    private TextView mOctavoTextViewTitulo;
    private TextView mOctavoTextViewMarca;
    private TextView mOctavoTextViewModelo;
    private TextView mOctavoTextViewMatricula;
    private EditText mOctavoEditTextMarca;
    private EditText mOctavoEditTextModelo;
    private EditText mOctavoEditTextMatricula;
    private Button mBtnAñadeNoveno;
    private Button mBtnDeshaceOctavo;
    private boolean mAddNoveno = false;

    //añade noveno vehículo
    private LinearLayout mLinearLayoutNoveno;
    private ImageView mNovenaImageViewImage;
    private TextView mNovenoTextViewTitulo;
    private TextView mNovenoTextViewMarca;
    private TextView mNovenoTextViewModelo;
    private TextView mNovenoTextViewMatricula;
    private EditText mNovenoEditTextMarca;
    private EditText mNovenoEditTextModelo;
    private EditText mNovenoEditTextMatricula;
    private Button mBtnAñadeDecimo;
    private Button mBtnDeshaceNoveno;
    private boolean mAddDecimo = false;

    //añade décimo vehículo
    private LinearLayout mLinearLayoutDecimo;
    private ImageView mDecimaImageViewImage;
    private TextView mDecimoTextViewTitulo;
    private TextView mDecimoTextViewMarca;
    private TextView mDecimoTextViewModelo;
    private TextView mDecimaTextViewMatricula;
    private EditText mDecimoEditTextMarca;
    private EditText mDecimoEditTextModelo;
    private EditText mDecimoEditTextMatricula;
    private Button mBtnDeshaceDecimo;
    private boolean mEliminaDecimo = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cliente);

        ActionBar myBar = getSupportActionBar();
        myBar.setDisplayShowHomeEnabled(true);
        myBar.setHomeButtonEnabled(true);
        myBar.setDisplayHomeAsUpEnabled(true);
        myBar.setTitle(R.string.ficha_cliente);

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
            Intent returnIntent = new Intent();
            returnIntent.putExtra(this.mEditNombre.getText().toString(), this.EXTRA_NAME);
            returnIntent.putExtra(this.mEditPrimerApellido.getText().toString(),
                    this.EXTRA_FIRST_SURNAME);
            returnIntent.putExtra(this.mEditSegundoApellido.getText().toString(),
                    this.EXTRA_SECOND_SURNAME);
            returnIntent.putExtra(this.mPrimerEditTextMarca.getText().toString(),
                    this.EXTRA_MARCA);
            returnIntent.putExtra(this.mPrimerEditTextModelo.getText().toString(),
                    this.EXTRA_MODELO);
            returnIntent.putExtra(this.mPrimeroEditTextMatricula.getText().toString(),
                    this.EXTRA_MATRICULA);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
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
            if(this.mPrimerEditTextMarca.getText().length() != 0 ||
                    this.mPrimerEditTextModelo.getText().length() != 0 ||
                    this.mPrimeroEditTextMatricula.getText().length() != 0) {
                this.dialogGone();
            } else {
                this.gonePrimero();
            }
            this.mEliminaPrimero = true;
        } else if (v == this.mBtnDeshaceSegundo) {
            if(this.mSegundoEditTextMarca.getText().length() != 0 ||
                    this.mSegundoEditTextModelo.getText().length() != 0 ||
                    this.mSegundoEditTextMatricula.getText().length() != 0) {
                this.dialogGone();
            } else {
                this.goneSegundo();
            }
            this.mAddSegundo = false;
        } else if (v == this.mBtnDeshaceTercero) {
            if(this.mTercerEditTextMarca.getText().length() != 0 ||
                    this.mTercerEditTextModelo.getText().length() != 0 ||
                    this.mTercerEditTextMatricula.getText().length() != 0) {
                this.dialogGone();
            } else {
                this.goneTercero();
            }
            this.mAddTercero = false;
        } else if (v == this.mBtnDeshaceCuarto) {
            if(this.mCuartoEditTextMarca.getText().length() != 0 ||
                    this.mCuartoEditTextModelo.getText().length() != 0 ||
                    this.mCuartoEditTextMatricula.getText().length() != 0) {
                this.dialogGone();
            } else {
                this.goneCuarto();
            }
            this.mAddCuarto = false;
        } else if (v == this.mBtnDeshaceQuinto) {
            if(this.mQuintoEditTextMarca.getText().length() != 0 ||
                    this.mQuintoEditTextModelo.getText().length() != 0 ||
                    this.mQuintoEditTextMatricula.getText().length() != 0) {
                this.dialogGone();
            } else {
                this.goneQuinto();
            }
            this.mAddQuinto = false;
        } else if (v == this.mBtnDeshaceSexto) {
            if(this.mSextoEditTextMarca.getText().length() != 0 ||
                    this.mSextoEditTextModelo.getText().length() != 0 ||
                    this.mSextoEditTextMatricula.getText().length() != 0) {
                this.dialogGone();
            } else {
                this.goneSexto();
            }
            this.mAddSexto = false;
        } else if (v == this.mBtnDeshaceSeptimo) {
            if(this.mSeptimoEditTextMarca.getText().length() != 0 ||
                    this.mSeptimoEditTextModelo.getText().length() != 0 ||
                    this.mSeptimoEditTextMatricula.getText().length() != 0) {
                this.dialogGone();
            } else {
                this.goneSeptimo();
            }
            this.mAddSeptimo = false;
        } else if (v == this.mBtnDeshaceOctavo) {
            if(this.mOctavoEditTextMarca.getText().length() != 0 ||
                    this.mOctavoEditTextModelo.getText().length() != 0 ||
                    this.mOctavoEditTextMatricula.getText().length() != 0) {
                this.dialogGone();
            } else {
                this.goneOctavo();
            }
            this.mAddOctavo = false;
        } else if (v == this.mBtnDeshaceNoveno) {
            if(this.mNovenoEditTextMarca.getText().length() != 0 ||
                    this.mNovenoEditTextModelo.getText().length() != 0 ||
                    this.mNovenoEditTextMatricula.getText().length() != 0) {
                this.dialogGone();
            } else {
                this.goneNoveno();
            }
            this.mAddNoveno = false;
        } else if (v == this.mBtnDeshaceDecimo) {
            if(this.mDecimoEditTextMarca.getText().length() != 0 ||
                    this.mDecimoEditTextModelo.getText().length() != 0 ||
                    this.mDecimoEditTextMatricula.getText().length() != 0) {
                this.dialogGone();
            } else {
                this.goneDecimo();
            }
            this.mEliminaDecimo = true;
            this.mAddDecimo = false;
        } else if (v == this.mBtnCancelar) {
            if(this.compruebaBools()) this.dialogExit();
            else finish();
        } else if (v == this.mBtnGuardar) {
            this.compruebaCampos();
        }
    }

    private void compruebaCampos() {
        String nombre, primerApellido, segundoApellido, telefono, matricula, modelo, marca;
        if(this.mEditNombre.getText().length() == 0) {
            this.mEditNombre.setError(getApplicationContext().getResources().getString(R.string.falta_nombre));
        } else if(this.mEditPrimerApellido.getText().length() == 0) {
            this.mEditPrimerApellido.setError(getApplicationContext().getResources().getString(R.string.falta_primer_apellido));
        } else if(this.mEditTelefono.getText().length() == 0) {
            this.mEditTelefono.setError(getApplicationContext().getResources().getString(R.string.falta_telefono));
        } else {
            nombre = this.mEditNombre.getText().toString();
            primerApellido = this.mEditPrimerApellido.getText().toString();
            if(this.mEditSegundoApellido.getText().length() == 0) {
                segundoApellido = "";
            }
            else {
                segundoApellido = this.mEditSegundoApellido.getText().toString();
            }
            telefono = this.mEditTelefono.getText().toString();

            if(!this.mEliminaPrimero) {
                if(this.mPrimerEditTextMarca.getText().length() == 0) {
                    this.mPrimerEditTextMarca.setError(getApplicationContext().getResources().getString(R.string.falta_marca));
                } else if(this.mPrimerEditTextModelo.getText().length() == 0) {
                    this.mPrimerEditTextModelo.setError(getApplicationContext().getResources().getString(R.string.falta_modelo));
                } else if(this.mPrimeroEditTextMatricula.getText().length() == 0) {
                    this.mPrimeroEditTextMatricula.setError(getApplicationContext().getResources().getString(R.string.falta_matricula));
                } else {
                    marca = this.mPrimerEditTextMarca.getText().toString();
                    modelo = this.mPrimerEditTextModelo.getText().toString();
                    matricula = this.mPrimeroEditTextMatricula.getText().toString();
                    this.saveChanges(nombre, primerApellido, segundoApellido, telefono, marca, modelo, matricula);
                }
            } if(this.mAddSegundo) {
                if(this.mSegundoEditTextMarca.getText().length() == 0) {
                    this.mSegundoEditTextMarca.setError(getApplicationContext().getResources().getString(R.string.falta_marca));
                } else if(this.mSegundoEditTextModelo.getText().length() == 0) {
                    this.mSegundoEditTextModelo.setError(getApplicationContext().getResources().getString(R.string.falta_modelo));
                } else if(this.mSegundoEditTextMatricula.getText().length() == 0) {
                    this.mSegundoEditTextMatricula.setError(getApplicationContext().getResources().getString(R.string.falta_matricula));
                } else {
                    marca = this.mSegundoEditTextMarca.getText().toString();
                    modelo = this.mSegundoEditTextModelo.getText().toString();
                    matricula = this.mSegundoEditTextMatricula.getText().toString();
                    this.saveChanges(nombre, primerApellido, segundoApellido, telefono, marca, modelo, matricula);
                }
            } if(this.mAddTercero) {
                if(this.mTercerEditTextMarca.getText().length() == 0) {
                    this.mTercerEditTextMarca.setError(getApplicationContext().getResources().getString(R.string.falta_marca));
                } else if(this.mTercerEditTextModelo.getText().length() == 0) {
                    this.mTercerEditTextModelo.setError(getApplicationContext().getResources().getString(R.string.falta_modelo));
                } else if(this.mTercerEditTextMatricula.getText().length() == 0) {
                    this.mTercerEditTextMatricula.setError(getApplicationContext().getResources().getString(R.string.falta_matricula));
                } else {
                    marca = this.mTercerEditTextMarca.getText().toString();
                    modelo = this.mTercerEditTextModelo.getText().toString();
                    matricula = this.mTercerEditTextMatricula.getText().toString();
                    this.saveChanges(nombre, primerApellido, segundoApellido, telefono, marca, modelo, matricula);
                }
            } if(this.mAddCuarto) {
                if(this.mCuartoEditTextMarca.getText().length() == 0) {
                    this.mCuartoEditTextMarca.setError(getApplicationContext().getResources().getString(R.string.falta_marca));
                } else if(this.mCuartoEditTextModelo.getText().length() == 0) {
                    this.mCuartoEditTextModelo.setError(getApplicationContext().getResources().getString(R.string.falta_modelo));
                } else if(this.mCuartoEditTextMatricula.getText().length() == 0) {
                    this.mCuartoEditTextMatricula.setError(getApplicationContext().getResources().getString(R.string.falta_matricula));
                } else {
                    marca = this.mCuartoEditTextMarca.getText().toString();
                    modelo = this.mCuartoEditTextModelo.getText().toString();
                    matricula = this.mCuartoEditTextMatricula.getText().toString();
                    this.saveChanges(nombre, primerApellido, segundoApellido, telefono, marca, modelo, matricula);
                }
            } if(this.mAddQuinto) {
                if(this.mQuintoEditTextMarca.getText().length() == 0) {
                    this.mQuintoEditTextMarca.setError(getApplicationContext().getResources().getString(R.string.falta_marca));
                } else if(this.mQuintoEditTextModelo.getText().length() == 0) {
                    this.mQuintoEditTextModelo.setError(getApplicationContext().getResources().getString(R.string.falta_modelo));
                } else if(this.mQuintoEditTextMatricula.getText().length() == 0) {
                    this.mQuintoEditTextMatricula.setError(getApplicationContext().getResources().getString(R.string.falta_matricula));
                } else {
                    marca = this.mQuintoEditTextMarca.getText().toString();
                    modelo = this.mQuintoEditTextModelo.getText().toString();
                    matricula = this.mQuintoEditTextMatricula.getText().toString();
                    this.saveChanges(nombre, primerApellido, segundoApellido, telefono, marca, modelo, matricula);
                }
            } if(this.mAddSexto) {
                if(this.mSextoEditTextMarca.getText().length() == 0) {
                    this.mSextoEditTextMarca.setError(getApplicationContext().getResources().getString(R.string.falta_marca));
                } else if(this.mSextoEditTextModelo.getText().length() == 0) {
                    this.mSextoEditTextModelo.setError(getApplicationContext().getResources().getString(R.string.falta_modelo));
                } else if(this.mSextoEditTextMatricula.getText().length() == 0) {
                    this.mSextoEditTextMatricula.setError(getApplicationContext().getResources().getString(R.string.falta_matricula));
                } else {
                    marca = this.mSextoEditTextMarca.getText().toString();
                    modelo = this.mSextoEditTextModelo.getText().toString();
                    matricula = this.mSextoEditTextMatricula.getText().toString();
                    this.saveChanges(nombre, primerApellido, segundoApellido, telefono, marca, modelo, matricula);
                }
            } if(this.mAddSeptimo) {
                if(this.mSeptimoEditTextMarca.getText().length() == 0) {
                    this.mSeptimoEditTextMarca.setError(getApplicationContext().getResources().getString(R.string.falta_marca));
                } else if(this.mSeptimoEditTextModelo.getText().length() == 0) {
                    this.mSeptimoEditTextModelo.setError(getApplicationContext().getResources().getString(R.string.falta_modelo));
                } else if(this.mSeptimoEditTextMatricula.getText().length() == 0) {
                    this.mSeptimoEditTextMatricula.setError(getApplicationContext().getResources().getString(R.string.falta_matricula));
                } else {
                    marca = this.mSeptimoEditTextMarca.getText().toString();
                    modelo = this.mSeptimoEditTextModelo.getText().toString();
                    matricula = this.mSeptimoEditTextMatricula.getText().toString();
                    this.saveChanges(nombre, primerApellido, segundoApellido, telefono, marca, modelo, matricula);
                }
            } if(this.mAddOctavo) {
                if(this.mOctavoEditTextMarca.getText().length() == 0) {
                    this.mOctavoEditTextMarca.setError(getApplicationContext().getResources().getString(R.string.falta_marca));
                } else if(this.mOctavoEditTextModelo.getText().length() == 0) {
                    this.mOctavoEditTextModelo.setError(getApplicationContext().getResources().getString(R.string.falta_modelo));
                } else if(this.mOctavoEditTextMatricula.getText().length() == 0) {
                    this.mOctavoEditTextMatricula.setError(getApplicationContext().getResources().getString(R.string.falta_matricula));
                } else {
                    marca = this.mOctavoEditTextMarca.getText().toString();
                    modelo = this.mOctavoEditTextModelo.getText().toString();
                    matricula = this.mOctavoEditTextMatricula.getText().toString();
                    this.saveChanges(nombre, primerApellido, segundoApellido, telefono, marca, modelo, matricula);
                }
            } if(this.mAddNoveno) {
                if(this.mNovenoEditTextMarca.getText().length() == 0) {
                    this.mNovenoEditTextMarca.setError(getApplicationContext().getResources().getString(R.string.falta_marca));
                } else if(this.mNovenoEditTextModelo.getText().length() == 0) {
                    this.mNovenoEditTextModelo.setError(getApplicationContext().getResources().getString(R.string.falta_modelo));
                } else if(this.mNovenoEditTextMatricula.getText().length() == 0) {
                    this.mNovenoEditTextMatricula.setError(getApplicationContext().getResources().getString(R.string.falta_matricula));
                } else {
                    marca = this.mNovenoEditTextMarca.getText().toString();
                    modelo = this.mNovenoEditTextModelo.getText().toString();
                    matricula = this.mNovenoEditTextMatricula.getText().toString();
                    this.saveChanges(nombre, primerApellido, segundoApellido, telefono, marca, modelo, matricula);
                }
            } if(this.mAddDecimo) {
                if(this.mNovenoEditTextMarca.getText().length() == 0) {
                    this.mNovenoEditTextMarca.setError(getApplicationContext().getResources().getString(R.string.falta_marca));
                } else if(this.mNovenoEditTextModelo.getText().length() == 0) {
                    this.mNovenoEditTextModelo.setError(getApplicationContext().getResources().getString(R.string.falta_modelo));
                } else if(this.mNovenoEditTextMatricula.getText().length() == 0) {
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
        DBClientes cliente = new DBClientes(nombre, primerApellido, segundoApellido, marca, modelo,
                matricula, telefono);
        cliente.save();
        Toast.makeText(this, "Cliente guardado", Toast.LENGTH_LONG).show();
        Intent returnIntent = new Intent();
        returnIntent.putExtra(this.EXTRA_NAME, nombre);
        returnIntent.putExtra(this.EXTRA_FIRST_SURNAME, primerApellido);
        returnIntent.putExtra(this.EXTRA_SECOND_SURNAME, segundoApellido);
        returnIntent.putExtra(this.EXTRA_MARCA, marca);
        returnIntent.putExtra(this.EXTRA_MODELO, modelo);
        returnIntent.putExtra(this.EXTRA_MATRICULA, matricula);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    private boolean compruebaBools() {
        if(this.mAddSegundo || this.mAddTercero || this.mAddCuarto || this.mAddQuinto ||
                this.mAddSexto || this.mAddSeptimo || this.mAddOctavo || this.mAddNoveno ||
                this.mAddDecimo ||
                this.mEditNombre.getText().length() != 0 || this.mEditTelefono.getText().length() != 0 ||
                this.mEditPrimerApellido.getText().length() != 0 || this.mEditSegundoApellido.getText().length() != 0 ||
                this.mPrimerEditTextMarca.getText().length() != 0 || this.mPrimerEditTextModelo.getText().length() != 0 ||
                this.mPrimeroEditTextMatricula.getText().length() != 0) {
            return true;
        }
        else {
            return false;
        }
    }

    private void dialogExit() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setTitle(R.string.confirmacion);

        //set the custom dialog components

        TextView textoDialog = (TextView) dialog.findViewById(R.id.txtvw_custom_dialog);
        textoDialog.setText(R.string.texto_custom_dialog);
        Button buttonCancelar = (Button) dialog.findViewById(R.id.boton_cancelar_dialog);
        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Button buttonAceptar = (Button) dialog.findViewById(R.id.boton_aceptar_dialog);
        buttonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        dialog.show();
    }

    private void dialogGone() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setTitle(R.string.confirmacion);

        //set the custom dialog components
        TextView textoDialog = (TextView) findViewById(R.id.txtvw_custom_dialog);
        textoDialog.setText(R.string.texto_custom_dialog_eliminar_elemento);
        Button buttonCancelar = (Button) findViewById(R.id.boton_cancelar_dialog);
        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Button buttonAceptar = (Button) findViewById(R.id.boton_aceptar_dialog);
        buttonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        dialog.show();
    }

    private void initPrimero() {
        this.mLinearLayoutPrimero = (LinearLayout) findViewById(R.id.añade_primer_vehiculo);
        this.mPrimerImageViewImage = (ImageView) findViewById(R.id.imagen_primer_coche);
        this.mPrimerTextViewTitulo = (TextView) findViewById(R.id.txtvw_primera_imagen);
        this.mPrimerTextViewMarca = (TextView) findViewById(R.id.txtvw_primera_marca);
        this.mPrimerEditTextMarca = (EditText) findViewById(R.id.editar_primera_marca);
        this.mPrimerTextViewModelo = (TextView) findViewById(R.id.txtvw_primer_modelo);
        this.mPrimerEditTextModelo = (EditText) findViewById(R.id.editar_primer_modelo);
        this.mPrimerTextViewMatricula = (TextView) findViewById(R.id.txtvw_primera_matricula);
        this.mPrimeroEditTextMatricula = (EditText) findViewById(R.id.editar_primera_matricula);
        this.mBtnAñadeSegundo = (Button) findViewById(R.id.boton_añade_segundo);
        this.mBtnAñadeSegundo.setOnClickListener(this);
        this.mBtnDeshacePrimero = (Button) findViewById(R.id.boton_deshacer_primer_vehiculo);
        this.mBtnDeshacePrimero.setOnClickListener(this);
    }


    private void initSegundo() {
        this.mLinearLayoutSegundo = (LinearLayout) findViewById(R.id.añade_segundo_vehiculo);
        this.mLinearLayoutSegundo.setVisibility(View.GONE);
        this.mSegundoImageViewImage = (ImageView) findViewById(R.id.imagen_segundo_coche);
        this.mSegundoImageViewImage.setVisibility(View.GONE);
        this.mSegundoTextViewTitulo = (TextView) findViewById(R.id.txtvw_segunda_imagen);
        this.mSegundoTextViewTitulo.setVisibility(View.GONE);
        this.mSegundoTextViewMarca = (TextView) findViewById(R.id.txtvw_segunda_marca);
        this.mSegundoTextViewMarca.setVisibility(View.GONE);
        this.mSegundoEditTextMarca = (EditText) findViewById(R.id.editar_segunda_marca);
        this.mSegundoEditTextMarca.setVisibility(View.GONE);
        this.mSegundoTextViewModelo = (TextView) findViewById(R.id.txtvw_segundo_modelo);
        this.mSegundoTextViewModelo.setVisibility(View.GONE);
        this.mSegundoEditTextModelo = (EditText) findViewById(R.id.editar_segundo_modelo);
        this.mSegundoEditTextModelo.setVisibility(View.GONE);
        this.mSegundaTextViewMatricula = (TextView) findViewById(R.id.txtvw_segunda_matricula);
        this.mSegundaTextViewMatricula.setVisibility(View.GONE);
        this.mSegundoEditTextMatricula = (EditText) findViewById(R.id.editar_segunda_matricula);
        this.mSegundoEditTextMatricula.setVisibility(View.GONE);
        this.mBtnAñadeTercer = (Button) findViewById(R.id.boton_añade_tercero);
        this.mBtnAñadeTercer.setVisibility(View.GONE);
        this.mBtnDeshaceSegundo = (Button) findViewById(R.id.boton_deshacer_segundo_vehiculo);
        this.mBtnDeshaceSegundo.setVisibility(View.GONE);
    }

    private void initTercero() {
        this.mLinearLayoutTercero = (LinearLayout) findViewById(R.id.añade_tercer_vehiculo);
        this.mLinearLayoutTercero.setVisibility(View.GONE);
        this.mTercerImageViewImage = (ImageView) findViewById(R.id.imagen_tercer_coche);
        this.mTercerImageViewImage.setVisibility(View.GONE);
        this.mTercerTextViewTitulo = (TextView) findViewById(R.id.txtvw_tercera_imagen);
        this.mTercerTextViewTitulo.setVisibility(View.GONE);
        this.mTercerTextViewMarca = (TextView) findViewById(R.id.txtvw_tercera_marca);
        this.mTercerTextViewMarca.setVisibility(View.GONE);
        this.mTercerEditTextMarca = (EditText) findViewById(R.id.editar_tercera_marca);
        this.mTercerEditTextMarca.setVisibility(View.GONE);
        this.mTercerTextViewModelo = (TextView) findViewById(R.id.txtvw_tercer_modelo);
        this.mTercerTextViewModelo.setVisibility(View.GONE);
        this.mTercerEditTextModelo = (EditText) findViewById(R.id.editar_tercer_modelo);
        this.mTercerEditTextModelo.setVisibility(View.GONE);
        this.mTercerTextViewMatricula = (TextView) findViewById(R.id.txtvw_tercera_matricula);
        this.mTercerTextViewMatricula.setVisibility(View.GONE);
        this.mTercerEditTextMatricula = (EditText) findViewById(R.id.editar_tercera_matricula);
        this.mTercerEditTextMatricula.setVisibility(View.GONE);
        this.mBtnAñadeCuarto = (Button) findViewById(R.id.boton_añade_cuarto);
        this.mBtnAñadeCuarto.setVisibility(View.GONE);
        this.mBtnDeshaceTercero = (Button) findViewById(R.id.boton_deshacer_tercer_vehiculo);
        this.mBtnDeshaceTercero.setVisibility(View.GONE);
    }

    private void initCuarto() {
        this.mLinearLayoutCuarto = (LinearLayout) findViewById(R.id.añade_cuarto_vehiculo);
        this.mLinearLayoutCuarto.setVisibility(View.GONE);
        this.mCuartaImageViewImage = (ImageView) findViewById(R.id.imagen_cuarto_coche);
        this.mCuartaImageViewImage.setVisibility(View.GONE);
        this.mCuartoTextViewTitulo = (TextView) findViewById(R.id.txtvw_cuarta_imagen);
        this.mCuartoTextViewTitulo.setVisibility(View.GONE);
        this.mCuartoTextViewMarca = (TextView) findViewById(R.id.txtvw_cuarta_marca);
        this.mCuartoTextViewMarca.setVisibility(View.GONE);
        this.mCuartoEditTextMarca = (EditText) findViewById(R.id.editar_cuarta_marca);
        this.mCuartoEditTextMarca.setVisibility(View.GONE);
        this.mCuartoTextViewModelo = (TextView) findViewById(R.id.txtvw_cuarto_modelo);
        this.mCuartoTextViewModelo.setVisibility(View.GONE);
        this.mCuartoEditTextModelo = (EditText) findViewById(R.id.editar_cuarto_modelo);
        this.mCuartoEditTextModelo.setVisibility(View.GONE);
        this.mCuartoTextViewMatricula = (TextView) findViewById(R.id.txtvw_cuarta_matricula);
        this.mCuartoTextViewMatricula.setVisibility(View.GONE);
        this.mCuartoEditTextMatricula = (EditText) findViewById(R.id.editar_cuarta_matricula);
        this.mCuartoEditTextMatricula.setVisibility(View.GONE);
        this.mBtnAñadeQuinto = (Button) findViewById(R.id.boton_añade_quinto);
        this.mBtnAñadeQuinto.setVisibility(View.GONE);
        this.mBtnDeshaceCuarto = (Button) findViewById(R.id.boton_deshacer_cuarto_vehiculo);
        this.mBtnDeshaceCuarto.setVisibility(View.GONE);
    }

    private void initQuinto() {
        this.mLinearLayoutQuinto = (LinearLayout) findViewById(R.id.añade_quinto_vehiculo);
        this.mLinearLayoutQuinto.setVisibility(View.GONE);
        this.mQuintoImageViewImage = (ImageView) findViewById(R.id.imagen_quinto_coche);
        this.mQuintoImageViewImage.setVisibility(View.GONE);
        this.mQuintoTextViewTitulo = (TextView) findViewById(R.id.txtvw_quinta_imagen);
        this.mQuintoTextViewTitulo.setVisibility(View.GONE);
        this.mQuintoTextViewMarca = (TextView) findViewById(R.id.txtvw_quinta_marca);
        this.mQuintoTextViewMarca.setVisibility(View.GONE);
        this.mQuintoEditTextMarca = (EditText) findViewById(R.id.editar_quinta_marca);
        this.mQuintoEditTextMarca.setVisibility(View.GONE);
        this.mQuintoTextViewModelo = (TextView) findViewById(R.id.txtvw_quinto_modelo);
        this.mQuintoTextViewModelo.setVisibility(View.GONE);
        this.mQuintoEditTextModelo = (EditText) findViewById(R.id.editar_quinto_modelo);
        this.mQuintoEditTextModelo.setVisibility(View.GONE);
        this.mQuintoTextViewMatricula = (TextView) findViewById(R.id.txtvw_quinta_matricula);
        this.mQuintoTextViewMatricula.setVisibility(View.GONE);
        this.mQuintoEditTextMatricula = (EditText) findViewById(R.id.editar_quinta_matricula);
        this.mQuintoEditTextMatricula.setVisibility(View.GONE);
        this.mBtnAñadeSexto = (Button) findViewById(R.id.boton_añade_sexto);
        this.mBtnAñadeSexto.setVisibility(View.GONE);
        this.mBtnDeshaceQuinto = (Button) findViewById(R.id.boton_deshacer_quinto_vehiculo);
        this.mBtnDeshaceQuinto.setVisibility(View.GONE);
    }

    private void initSexto() {
        this.mLinearLayoutSexto = (LinearLayout) findViewById(R.id.añade_sexto_vehiculo);
        this.mLinearLayoutSexto.setVisibility(View.GONE);
        this.mSextoImageViewImage = (ImageView) findViewById(R.id.imagen_sexto_coche);
        this.mSextoImageViewImage.setVisibility(View.GONE);
        this.mSextoTextViewTitulo = (TextView) findViewById(R.id.txtvw_sexta_imagen);
        this.mSextoTextViewTitulo.setVisibility(View.GONE);
        this.mSextoTextViewMarca = (TextView) findViewById(R.id.txtvw_sexta_marca);
        this.mSextoTextViewMarca.setVisibility(View.GONE);
        this.mSextoEditTextMarca = (EditText) findViewById(R.id.editar_sexta_marca);
        this.mSextoEditTextMarca.setVisibility(View.GONE);
        this.mSextoTextViewModelo = (TextView) findViewById(R.id.txtvw_sexto_modelo);
        this.mSextoTextViewModelo.setVisibility(View.GONE);
        this.mSextoEditTextModelo = (EditText) findViewById(R.id.editar_sexto_modelo);
        this.mSextoEditTextModelo.setVisibility(View.GONE);
        this.mSextaTextViewMatricula = (TextView) findViewById(R.id.txtvw_sexta_matricula);
        this.mSextaTextViewMatricula.setVisibility(View.GONE);
        this.mSextoEditTextMatricula = (EditText) findViewById(R.id.editar_sexta_matricula);
        this.mSextoEditTextMatricula.setVisibility(View.GONE);
        this.mBtnAñadeSeptimo = (Button) findViewById(R.id.boton_añade_septimo);
        this.mBtnAñadeSeptimo.setVisibility(View.GONE);
        this.mBtnDeshaceSexto = (Button) findViewById(R.id.boton_deshacer_sexto_vehiculo);
        this.mBtnDeshaceSexto.setVisibility(View.GONE);
    }

    private void initSeptimo() {
        this.mLinearLayoutSeptimo = (LinearLayout) findViewById(R.id.añade_septimo_vehiculo);
        this.mLinearLayoutSeptimo.setVisibility(View.GONE);
        this.mSeptimoImageViewImage = (ImageView) findViewById(R.id.imagen_septimo_coche);
        this.mSeptimoImageViewImage.setVisibility(View.GONE);
        this.mSeptimoTextViewTitulo = (TextView) findViewById(R.id.txtvw_septima_imagen);
        this.mSeptimoTextViewTitulo.setVisibility(View.GONE);
        this.mSeptimoTextViewMarca = (TextView) findViewById(R.id.txtvw_septima_marca);
        this.mSeptimoTextViewMarca.setVisibility(View.GONE);
        this.mSeptimoEditTextMarca = (EditText) findViewById(R.id.editar_septima_marca);
        this.mSeptimoEditTextMarca.setVisibility(View.GONE);
        this.mSeptimoTextViewModelo = (TextView) findViewById(R.id.txtvw_septimo_modelo);
        this.mSeptimoTextViewModelo.setVisibility(View.GONE);
        this.mSeptimoEditTextModelo = (EditText) findViewById(R.id.editar_septimo_modelo);
        this.mSeptimoEditTextModelo.setVisibility(View.GONE);
        this.mSeptimaTextViewMatricula = (TextView) findViewById(R.id.txtvw_septima_matricula);
        this.mSeptimaTextViewMatricula.setVisibility(View.GONE);
        this.mSeptimoEditTextMatricula = (EditText) findViewById(R.id.editar_septima_matricula);
        this.mSeptimoEditTextMatricula.setVisibility(View.GONE);
        this.mBtnAñadeOctavo = (Button) findViewById(R.id.boton_añade_octavo);
        this.mBtnAñadeOctavo.setVisibility(View.GONE);
        this.mBtnDeshaceSeptimo = (Button) findViewById(R.id.boton_deshacer_septimo_vehiculo);
        this.mBtnDeshaceSeptimo.setVisibility(View.GONE);
    }

    private void initOctavo() {
        this.mLinearLayoutOctavo = (LinearLayout) findViewById(R.id.añade_octavo_vehiculo);
        this.mLinearLayoutOctavo.setVisibility(View.GONE);
        this.mOctavoImageViewImage = (ImageView) findViewById(R.id.imagen_octavo_coche);
        this.mOctavoImageViewImage.setVisibility(View.GONE);
        this.mOctavoTextViewTitulo = (TextView) findViewById(R.id.txtvw_octava_imagen);
        this.mOctavoTextViewTitulo.setVisibility(View.GONE);
        this.mOctavoTextViewMarca = (TextView) findViewById(R.id.txtvw_octava_marca);
        this.mOctavoTextViewMarca.setVisibility(View.GONE);
        this.mOctavoEditTextMarca = (EditText) findViewById(R.id.editar_octava_marca);
        this.mOctavoEditTextMarca.setVisibility(View.GONE);
        this.mOctavoTextViewModelo = (TextView) findViewById(R.id.txtvw_octavo_modelo);
        this.mOctavoTextViewModelo.setVisibility(View.GONE);
        this.mOctavoEditTextModelo = (EditText) findViewById(R.id.editar_octavo_modelo);
        this.mOctavoEditTextModelo.setVisibility(View.GONE);
        this.mOctavoTextViewMatricula = (TextView) findViewById(R.id.txtvw_octava_matricula);
        this.mOctavoTextViewMatricula.setVisibility(View.GONE);
        this.mOctavoEditTextMatricula = (EditText) findViewById(R.id.editar_octava_matricula);
        this.mOctavoEditTextMatricula.setVisibility(View.GONE);
        this.mBtnAñadeNoveno = (Button) findViewById(R.id.boton_añade_noveno);
        this.mBtnAñadeNoveno.setVisibility(View.GONE);
        this.mBtnDeshaceOctavo = (Button) findViewById(R.id.boton_deshacer_octavo_vehiculo);
        this.mBtnDeshaceOctavo.setVisibility(View.GONE);
    }

    private void initNoveno() {
        this.mLinearLayoutNoveno = (LinearLayout) findViewById(R.id.añade_noveno_vehiculo);
        this.mLinearLayoutNoveno.setVisibility(View.GONE);
        this.mNovenaImageViewImage = (ImageView) findViewById(R.id.imagen_novena_coche);
        this.mNovenaImageViewImage.setVisibility(View.GONE);
        this.mNovenoTextViewTitulo = (TextView) findViewById(R.id.txtvw_novena_imagen);
        this.mNovenoTextViewTitulo.setVisibility(View.GONE);
        this.mNovenoTextViewMarca = (TextView) findViewById(R.id.txtvw_novena_marca);
        this.mNovenoTextViewMarca.setVisibility(View.GONE);
        this.mNovenoEditTextMarca = (EditText) findViewById(R.id.editar_novena_marca);
        this.mNovenoEditTextMarca.setVisibility(View.GONE);
        this.mNovenoTextViewModelo = (TextView) findViewById(R.id.txtvw_noveno_modelo);
        this.mNovenoTextViewModelo.setVisibility(View.GONE);
        this.mNovenoEditTextModelo = (EditText) findViewById(R.id.editar_noveno_modelo);
        this.mNovenoEditTextModelo.setVisibility(View.GONE);
        this.mNovenoTextViewMatricula = (TextView) findViewById(R.id.txtvw_novena_matricula);
        this.mNovenoTextViewMatricula.setVisibility(View.GONE);
        this.mNovenoEditTextMatricula = (EditText) findViewById(R.id.editar_novena_matricula);
        this.mNovenoEditTextMatricula.setVisibility(View.GONE);
        this.mBtnAñadeDecimo = (Button) findViewById(R.id.boton_añade_decimo);
        this.mBtnAñadeDecimo.setVisibility(View.GONE);
        this.mBtnDeshaceNoveno = (Button) findViewById(R.id.boton_deshacer_noveno_vehiculo);
        this.mBtnDeshaceNoveno.setVisibility(View.GONE);
    }

    private void initDecimo() {
        this.mLinearLayoutDecimo = (LinearLayout) findViewById(R.id.añade_decimo_vehiculo);
        this.mLinearLayoutDecimo.setVisibility(View.GONE);
        this.mDecimaImageViewImage = (ImageView) findViewById(R.id.imagen_decimo_coche);
        this.mDecimaImageViewImage.setVisibility(View.GONE);
        this.mDecimoTextViewTitulo = (TextView) findViewById(R.id.txtvw_decima_imagen);
        this.mDecimoTextViewTitulo.setVisibility(View.GONE);
        this.mDecimoTextViewMarca = (TextView) findViewById(R.id.txtvw_decima_marca);
        this.mDecimoTextViewMarca.setVisibility(View.GONE);
        this.mDecimoEditTextMarca = (EditText) findViewById(R.id.editar_decima_marca);
        this.mDecimoEditTextMarca.setVisibility(View.GONE);
        this.mDecimoTextViewModelo = (TextView) findViewById(R.id.txtvw_decimo_modelo);
        this.mDecimoTextViewModelo.setVisibility(View.GONE);
        this.mDecimoEditTextModelo = (EditText) findViewById(R.id.editar_decimo_modelo);
        this.mDecimoEditTextModelo.setVisibility(View.GONE);
        this.mDecimaTextViewMatricula = (TextView) findViewById(R.id.txtvw_decima_matricula);
        this.mDecimaTextViewMatricula.setVisibility(View.GONE);
        this.mDecimoEditTextMatricula = (EditText) findViewById(R.id.editar_decima_matricula);
        this.mDecimoEditTextMatricula.setVisibility(View.GONE);
        this.mBtnDeshaceDecimo = (Button) findViewById(R.id.boton_deshacer_decimo_vehiculo);
        this.mBtnDeshaceDecimo.setVisibility(View.GONE);
    }

    private void gonePrimero() {
        this.mLinearLayoutPrimero.setVisibility(View.GONE);
        this.mPrimerImageViewImage.setVisibility(View.GONE);
        this.mPrimerTextViewTitulo.setVisibility(View.GONE);
        this.mPrimerTextViewMarca.setVisibility(View.GONE);
        this.mPrimerEditTextMarca.setVisibility(View.GONE);
        this.mPrimerTextViewModelo.setVisibility(View.GONE);
        this.mPrimerEditTextModelo.setVisibility(View.GONE);
        this.mPrimerTextViewMatricula.setVisibility(View.GONE);
        this.mPrimeroEditTextMatricula.setVisibility(View.GONE);
        this.mBtnAñadeSegundo.setVisibility(View.GONE);
        this.mBtnDeshacePrimero.setVisibility(View.GONE);
    }

    private void viewSegundo() {
        this.mLinearLayoutSegundo.setVisibility(View.VISIBLE);
        this.mSegundoImageViewImage.setVisibility(View.VISIBLE);
        this.mSegundoTextViewTitulo.setVisibility(View.VISIBLE);
        this.mSegundoTextViewMarca.setVisibility(View.VISIBLE);
        this.mSegundoEditTextMarca.setVisibility(View.VISIBLE);
        this.mSegundoTextViewModelo.setVisibility(View.VISIBLE);
        this.mSegundoEditTextModelo.setVisibility(View.VISIBLE);
        this.mSegundaTextViewMatricula.setVisibility(View.VISIBLE);
        this.mSegundoEditTextMatricula.setVisibility(View.VISIBLE);
        this.mBtnAñadeTercer.setVisibility(View.VISIBLE);
        this.mBtnAñadeTercer.setOnClickListener(this);
        this.mBtnDeshaceSegundo.setVisibility(View.VISIBLE);
        this.mBtnDeshaceSegundo.setOnClickListener(this);
        //hay que dejar de ver el botón de añadir segundo
        this.mBtnAñadeSegundo.setVisibility(View.GONE);
    }

    private void goneSegundo() {
        this.mLinearLayoutSegundo.setVisibility(View.GONE);
        this.mSegundoImageViewImage.setVisibility(View.GONE);
        this.mSegundoTextViewTitulo.setVisibility(View.GONE);
        this.mSegundoTextViewMarca.setVisibility(View.GONE);
        this.mSegundoEditTextMarca.setVisibility(View.GONE);
        this.mSegundoTextViewModelo.setVisibility(View.GONE);
        this.mSegundoEditTextModelo.setVisibility(View.GONE);
        this.mSegundaTextViewMatricula.setVisibility(View.GONE);
        this.mSegundoEditTextMatricula.setVisibility(View.GONE);
        this.mBtnAñadeTercer.setVisibility(View.GONE);
        this.mBtnDeshaceSegundo.setVisibility(View.GONE);
        //hay que volver a dejar ver el botón de añadir segundo
        this.mBtnAñadeSegundo.setVisibility(View.VISIBLE);
    }

    private void viewTercero() {
        this.mLinearLayoutTercero.setVisibility(View.VISIBLE);
        this.mTercerImageViewImage.setVisibility(View.VISIBLE);
        this.mTercerTextViewTitulo.setVisibility(View.VISIBLE);
        this.mTercerTextViewMarca.setVisibility(View.VISIBLE);
        this.mTercerEditTextMarca.setVisibility(View.VISIBLE);
        this.mTercerTextViewModelo.setVisibility(View.VISIBLE);
        this.mTercerEditTextModelo.setVisibility(View.VISIBLE);
        this.mTercerTextViewMatricula.setVisibility(View.VISIBLE);
        this.mTercerEditTextMatricula.setVisibility(View.VISIBLE);
        this.mBtnAñadeCuarto.setVisibility(View.VISIBLE);
        this.mBtnAñadeCuarto.setOnClickListener(this);
        this.mBtnDeshaceTercero.setVisibility(View.VISIBLE);
        this.mBtnDeshaceTercero.setOnClickListener(this);
        //hay que dejar de ver el botón de añadir tercero
        this.mBtnAñadeTercer.setVisibility(View.GONE);
    }

    private void goneTercero() {
        this.mLinearLayoutTercero.setVisibility(View.GONE);
        this.mTercerImageViewImage.setVisibility(View.GONE);
        this.mTercerTextViewTitulo.setVisibility(View.GONE);
        this.mTercerTextViewMarca.setVisibility(View.GONE);
        this.mTercerEditTextMarca.setVisibility(View.GONE);
        this.mTercerTextViewModelo.setVisibility(View.GONE);
        this.mTercerEditTextModelo.setVisibility(View.GONE);
        this.mTercerTextViewMatricula.setVisibility(View.GONE);
        this.mTercerEditTextMatricula.setVisibility(View.GONE);
        this.mBtnAñadeCuarto.setVisibility(View.GONE);
        this.mBtnDeshaceTercero.setVisibility(View.GONE);
        //hay que volver a dejar ver el botón de añadir tercero
        this.mBtnAñadeTercer.setVisibility(View.VISIBLE);
    }

    private void viewCuarto() {
        this.mLinearLayoutCuarto.setVisibility(View.VISIBLE);
        this.mCuartaImageViewImage.setVisibility(View.VISIBLE);
        this.mCuartoTextViewTitulo.setVisibility(View.VISIBLE);
        this.mCuartoTextViewMarca.setVisibility(View.VISIBLE);
        this.mCuartoEditTextMarca.setVisibility(View.VISIBLE);
        this.mCuartoTextViewModelo.setVisibility(View.VISIBLE);
        this.mCuartoEditTextModelo.setVisibility(View.VISIBLE);
        this.mCuartoTextViewMatricula.setVisibility(View.VISIBLE);
        this.mCuartoEditTextMatricula.setVisibility(View.VISIBLE);
        this.mBtnAñadeQuinto.setVisibility(View.VISIBLE);
        this.mBtnAñadeQuinto.setOnClickListener(this);
        this.mBtnDeshaceCuarto.setVisibility(View.VISIBLE);
        this.mBtnDeshaceCuarto.setOnClickListener(this);
        //hay que dejar de ver el botón de añadir cuarto
        this.mBtnAñadeCuarto.setVisibility(View.GONE);
    }

    private void goneCuarto() {
        this.mLinearLayoutCuarto.setVisibility(View.GONE);
        this.mCuartaImageViewImage.setVisibility(View.GONE);
        this.mCuartoTextViewTitulo.setVisibility(View.GONE);
        this.mCuartoTextViewMarca.setVisibility(View.GONE);
        this.mCuartoEditTextMarca.setVisibility(View.GONE);
        this.mCuartoTextViewModelo.setVisibility(View.GONE);
        this.mCuartoEditTextModelo.setVisibility(View.GONE);
        this.mCuartoTextViewMatricula.setVisibility(View.GONE);
        this.mCuartoEditTextMatricula.setVisibility(View.GONE);
        this.mBtnAñadeQuinto.setVisibility(View.GONE);
        this.mBtnDeshaceCuarto.setVisibility(View.GONE);
        //hay que volver a dejar ver el botón de añadir cuarto
        this.mBtnAñadeCuarto.setVisibility(View.VISIBLE);
    }

    private void viewQuinto() {
        this.mLinearLayoutQuinto.setVisibility(View.VISIBLE);
        this.mQuintoImageViewImage.setVisibility(View.VISIBLE);
        this.mQuintoTextViewTitulo.setVisibility(View.VISIBLE);
        this.mQuintoTextViewMarca.setVisibility(View.VISIBLE);
        this.mQuintoEditTextMarca.setVisibility(View.VISIBLE);
        this.mQuintoTextViewModelo.setVisibility(View.VISIBLE);
        this.mQuintoEditTextModelo.setVisibility(View.VISIBLE);
        this.mQuintoTextViewMatricula.setVisibility(View.VISIBLE);
        this.mQuintoEditTextMatricula.setVisibility(View.VISIBLE);
        this.mBtnAñadeSexto.setVisibility(View.VISIBLE);
        this.mBtnAñadeSexto.setOnClickListener(this);
        this.mBtnDeshaceQuinto.setVisibility(View.VISIBLE);
        this.mBtnDeshaceQuinto.setOnClickListener(this);
        //hay que dejar de ver el botón de añadir quinto
        this.mBtnAñadeQuinto.setVisibility(View.GONE);
    }

    private void goneQuinto() {
        this.mLinearLayoutQuinto.setVisibility(View.GONE);
        this.mQuintoImageViewImage.setVisibility(View.GONE);
        this.mQuintoTextViewTitulo.setVisibility(View.GONE);
        this.mQuintoTextViewMarca.setVisibility(View.GONE);
        this.mQuintoEditTextMarca.setVisibility(View.GONE);
        this.mQuintoTextViewModelo.setVisibility(View.GONE);
        this.mQuintoEditTextModelo.setVisibility(View.GONE);
        this.mQuintoTextViewMatricula.setVisibility(View.GONE);
        this.mQuintoEditTextMatricula.setVisibility(View.GONE);
        this.mBtnAñadeSexto.setVisibility(View.GONE);
        this.mBtnDeshaceQuinto.setVisibility(View.GONE);
        //hay que volver a dejar ver el botón de añadir quinto
        this.mBtnAñadeQuinto.setVisibility(View.VISIBLE);
    }

    private void viewSexto() {
        this.mLinearLayoutSexto.setVisibility(View.VISIBLE);
        this.mSextoImageViewImage.setVisibility(View.VISIBLE);
        this.mSextoTextViewTitulo.setVisibility(View.VISIBLE);
        this.mSextoTextViewMarca.setVisibility(View.VISIBLE);
        this.mSextoEditTextMarca.setVisibility(View.VISIBLE);
        this.mSextoTextViewModelo.setVisibility(View.VISIBLE);
        this.mSextoEditTextModelo.setVisibility(View.VISIBLE);
        this.mSextaTextViewMatricula.setVisibility(View.VISIBLE);
        this.mSextoEditTextMatricula.setVisibility(View.VISIBLE);
        this.mBtnAñadeSeptimo.setVisibility(View.VISIBLE);
        this.mBtnAñadeSeptimo.setOnClickListener(this);
        this.mBtnDeshaceSexto.setVisibility(View.VISIBLE);
        this.mBtnDeshaceSexto.setOnClickListener(this);
        //hay que dejar de ver el botón de añadir sexto
        this.mBtnAñadeSexto.setVisibility(View.GONE);
    }

    private void goneSexto() {
        this.mLinearLayoutSexto.setVisibility(View.GONE);
        this.mSextoImageViewImage.setVisibility(View.GONE);
        this.mSextoTextViewTitulo.setVisibility(View.GONE);
        this.mSextoTextViewMarca.setVisibility(View.GONE);
        this.mSextoEditTextMarca.setVisibility(View.GONE);
        this.mSextoTextViewModelo.setVisibility(View.GONE);
        this.mSextoEditTextModelo.setVisibility(View.GONE);
        this.mSextaTextViewMatricula.setVisibility(View.GONE);
        this.mSextoEditTextMatricula.setVisibility(View.GONE);
        this.mBtnAñadeSeptimo.setVisibility(View.GONE);
        this.mBtnDeshaceSexto.setVisibility(View.GONE);
        //hay que volver a dejar ver el botón de añadir sexto
        this.mBtnAñadeSexto.setVisibility(View.VISIBLE);
    }

    private void viewSeptimo() {
        this.mLinearLayoutSeptimo.setVisibility(View.VISIBLE);
        this.mSeptimoImageViewImage.setVisibility(View.VISIBLE);
        this.mSeptimoTextViewTitulo.setVisibility(View.VISIBLE);
        this.mSeptimoTextViewMarca.setVisibility(View.VISIBLE);
        this.mSeptimoEditTextMarca.setVisibility(View.VISIBLE);
        this.mSeptimoTextViewModelo.setVisibility(View.VISIBLE);
        this.mSeptimoEditTextModelo.setVisibility(View.VISIBLE);
        this.mSeptimaTextViewMatricula.setVisibility(View.VISIBLE);
        this.mSeptimoEditTextMatricula.setVisibility(View.VISIBLE);
        this.mBtnAñadeOctavo.setVisibility(View.VISIBLE);
        this.mBtnAñadeOctavo.setOnClickListener(this);
        this.mBtnDeshaceSeptimo.setVisibility(View.VISIBLE);
        this.mBtnDeshaceSeptimo.setOnClickListener(this);
        //hay que dejar de ver el botón de añadir séptimo
        this.mBtnAñadeSeptimo.setVisibility(View.GONE);
    }

    private void goneSeptimo() {
        this.mLinearLayoutSeptimo.setVisibility(View.GONE);
        this.mSeptimoImageViewImage.setVisibility(View.GONE);
        this.mSeptimoTextViewTitulo.setVisibility(View.GONE);
        this.mSeptimoTextViewMarca.setVisibility(View.GONE);
        this.mSeptimoEditTextMarca.setVisibility(View.GONE);
        this.mSeptimoTextViewModelo.setVisibility(View.GONE);
        this.mSeptimoEditTextModelo.setVisibility(View.GONE);
        this.mSeptimaTextViewMatricula.setVisibility(View.GONE);
        this.mSeptimoEditTextMatricula.setVisibility(View.GONE);
        this.mBtnAñadeOctavo.setVisibility(View.GONE);
        this.mBtnDeshaceSeptimo.setVisibility(View.GONE);
        //hay que volver a dejar ver el botón de añadir septimo
        this.mBtnAñadeSeptimo.setVisibility(View.VISIBLE);
    }

    private void viewOctavo() {
        this.mLinearLayoutOctavo.setVisibility(View.VISIBLE);
        this.mOctavoImageViewImage.setVisibility(View.VISIBLE);
        this.mOctavoTextViewTitulo.setVisibility(View.VISIBLE);
        this.mOctavoTextViewMarca.setVisibility(View.VISIBLE);
        this.mOctavoEditTextMarca.setVisibility(View.VISIBLE);
        this.mOctavoTextViewModelo.setVisibility(View.VISIBLE);
        this.mOctavoEditTextModelo.setVisibility(View.VISIBLE);
        this.mOctavoTextViewMatricula.setVisibility(View.VISIBLE);
        this.mOctavoEditTextMatricula.setVisibility(View.VISIBLE);
        this.mBtnAñadeNoveno.setVisibility(View.VISIBLE);
        this.mBtnAñadeNoveno.setOnClickListener(this);
        this.mBtnDeshaceOctavo.setVisibility(View.VISIBLE);
        this.mBtnDeshaceOctavo.setOnClickListener(this);
        //hay que dejar de ver el botón de añadir octavo
        this.mBtnAñadeOctavo.setVisibility(View.GONE);
    }

    private void goneOctavo() {
        this.mLinearLayoutOctavo.setVisibility(View.GONE);
        this.mOctavoImageViewImage.setVisibility(View.GONE);
        this.mOctavoTextViewTitulo.setVisibility(View.GONE);
        this.mOctavoTextViewMarca.setVisibility(View.GONE);
        this.mOctavoEditTextMarca.setVisibility(View.GONE);
        this.mOctavoTextViewModelo.setVisibility(View.GONE);
        this.mOctavoEditTextModelo.setVisibility(View.GONE);
        this.mOctavoTextViewMatricula.setVisibility(View.GONE);
        this.mOctavoEditTextMatricula.setVisibility(View.GONE);
        this.mBtnAñadeNoveno.setVisibility(View.GONE);
        this.mBtnDeshaceOctavo.setVisibility(View.GONE);
        //hay que volver a dejar ver el botón de añadir octavo
        this.mBtnAñadeOctavo.setVisibility(View.VISIBLE);
    }

    private void viewNoveno() {
        this.mLinearLayoutNoveno.setVisibility(View.VISIBLE);
        this.mNovenaImageViewImage.setVisibility(View.VISIBLE);
        this.mNovenoTextViewTitulo.setVisibility(View.VISIBLE);
        this.mNovenoTextViewMarca.setVisibility(View.VISIBLE);
        this.mNovenoEditTextMarca.setVisibility(View.VISIBLE);
        this.mNovenoTextViewModelo.setVisibility(View.VISIBLE);
        this.mNovenoEditTextModelo.setVisibility(View.VISIBLE);
        this.mNovenoTextViewMatricula.setVisibility(View.VISIBLE);
        this.mNovenoEditTextMatricula.setVisibility(View.VISIBLE);
        this.mBtnAñadeDecimo.setVisibility(View.VISIBLE);
        this.mBtnAñadeDecimo.setOnClickListener(this);
        this.mBtnDeshaceNoveno.setVisibility(View.VISIBLE);
        this.mBtnDeshaceNoveno.setOnClickListener(this);
        //hay que dejar de ver el botón de añadir noveno
        this.mBtnAñadeNoveno.setVisibility(View.GONE);
    }

    private void goneNoveno() {
        this.mLinearLayoutNoveno.setVisibility(View.GONE);
        this.mNovenaImageViewImage.setVisibility(View.GONE);
        this.mNovenoTextViewTitulo.setVisibility(View.GONE);
        this.mNovenoTextViewMarca.setVisibility(View.GONE);
        this.mNovenoEditTextMarca.setVisibility(View.GONE);
        this.mNovenoTextViewModelo.setVisibility(View.GONE);
        this.mNovenoEditTextModelo.setVisibility(View.GONE);
        this.mNovenoTextViewMatricula.setVisibility(View.GONE);
        this.mNovenoEditTextMatricula.setVisibility(View.GONE);
        this.mBtnAñadeDecimo.setVisibility(View.GONE);
        this.mBtnDeshaceNoveno.setVisibility(View.GONE);
        //hay que volver a dejar ver el botón de añadir noveno
        this.mBtnAñadeNoveno.setVisibility(View.VISIBLE);

    }

    private void viewDecimo() {
        this.mLinearLayoutDecimo.setVisibility(View.VISIBLE);
        this.mDecimaImageViewImage.setVisibility(View.VISIBLE);
        this.mDecimoTextViewTitulo.setVisibility(View.VISIBLE);
        this.mDecimoTextViewMarca.setVisibility(View.VISIBLE);
        this.mDecimoEditTextMarca.setVisibility(View.VISIBLE);
        this.mDecimoTextViewModelo.setVisibility(View.VISIBLE);
        this.mDecimoEditTextModelo.setVisibility(View.VISIBLE);
        this.mDecimaTextViewMatricula.setVisibility(View.VISIBLE);
        this.mDecimoEditTextMatricula.setVisibility(View.VISIBLE);
        this.mBtnDeshaceDecimo.setVisibility(View.VISIBLE);
        this.mBtnDeshaceDecimo.setOnClickListener(this);
        //hay que dejar de ver el botón de añadir décimo
        this.mBtnAñadeDecimo.setVisibility(View.GONE);
    }

    private void goneDecimo() {
        this.mLinearLayoutDecimo.setVisibility(View.GONE);
        this.mDecimaImageViewImage.setVisibility(View.GONE);
        this.mDecimoTextViewTitulo.setVisibility(View.GONE);
        this.mDecimoTextViewMarca.setVisibility(View.GONE);
        this.mDecimoEditTextMarca.setVisibility(View.GONE);
        this.mDecimoTextViewModelo.setVisibility(View.GONE);
        this.mDecimoEditTextModelo.setVisibility(View.GONE);
        this.mDecimaTextViewMatricula.setVisibility(View.GONE);
        this.mDecimoEditTextMatricula.setVisibility(View.GONE);
        this.mBtnDeshaceDecimo.setVisibility(View.GONE);
        //hay que volver a dejar ver el botón de añadir décimo
        this.mBtnAñadeDecimo.setVisibility(View.VISIBLE);
    }


}
