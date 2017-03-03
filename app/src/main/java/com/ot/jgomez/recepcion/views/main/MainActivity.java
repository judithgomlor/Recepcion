package com.ot.jgomez.recepcion.views.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.ot.jgomez.recepcion.R;
import com.ot.jgomez.recepcion.views.addcliente.AddClienteActivity;
import com.ot.jgomez.recepcion.views.addreparacion.AddReparacionActivity;
import com.ot.jgomez.recepcion.views.consultacliente.ConsultaClienteActivity;
import com.ot.jgomez.recepcion.views.consultarreparaciones.ConsultarReparacionesActivity;
import com.ot.jgomez.recepcion.views.listaclientes.ListaClientesActivity;
import com.ot.jgomez.recepcion.views.modificacliente.ModificaClienteActivity;
import com.ot.jgomez.recepcion.views.modificareparacion.ModificaReparacionActivity;
import com.ot.jgomez.recepcion.views.solvereparacion.SolveReparacionActivity;
import com.ot.jgomez.recepcion.views.visualizacuentas.VisualizaCuentasActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton mBtnAñadirCliente;
    private ImageButton mBtnModificarCliente;
    private ImageButton mBtnConsultarCliente;
    private ImageButton mBtnAñadirReparacion;
    private ImageButton mBtnModificarReparacion;
    private ImageButton mBtnResolverReparacion;
    private ImageButton mBtnConsultarReparacion;
    private ImageButton mBtnConsultarCuentas;
    private ImageButton mBtnAñadirRepuestos;
    private ImageButton mBtnModificarRepuestos;
    private ImageButton mBtnConsultarRepuestos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getApplicationContext().getString(R.string.consultar_reparaciones));

        this.init();
    }

    private void init() {
        this.mBtnAñadirCliente = (ImageButton) findViewById(R.id.añade_cliente_button);
        this.mBtnAñadirCliente.setOnClickListener(this);

        this.mBtnModificarCliente = (ImageButton) findViewById(R.id.modifica_cliente_button);
        this.mBtnModificarCliente.setOnClickListener(this);

        this.mBtnConsultarCliente = (ImageButton) findViewById(R.id.consulta_cliente_button);
        this.mBtnConsultarCliente.setOnClickListener(this);

        this.mBtnAñadirReparacion = (ImageButton) findViewById(R.id.añadir_reparacion_button);
        this.mBtnAñadirReparacion.setOnClickListener(this);

        this.mBtnModificarReparacion = (ImageButton) findViewById(R.id.modifica_reparacion_button);
        this.mBtnModificarReparacion.setOnClickListener(this);

        this.mBtnResolverReparacion = (ImageButton) findViewById(R.id.resuelve_reparacion_button);
        this.mBtnResolverReparacion.setOnClickListener(this);

        this.mBtnConsultarReparacion = (ImageButton) findViewById(R.id.consulta_reparacion_button);
        this.mBtnConsultarReparacion.setOnClickListener(this);

        this.mBtnConsultarCuentas = (ImageButton) findViewById(R.id.consulta_cuentas_button);
        this.mBtnConsultarCuentas.setOnClickListener(this);

        this.mBtnAñadirRepuestos = (ImageButton) findViewById(R.id.añadir_repuestos_button);
        this.mBtnAñadirRepuestos.setOnClickListener(this);

        this.mBtnModificarRepuestos = (ImageButton) findViewById(R.id.modifica_respuestos_button);
        this.mBtnModificarRepuestos.setOnClickListener(this);

        this.mBtnConsultarRepuestos = (ImageButton) findViewById(R.id.consulta_repuestos_button);
        this.mBtnConsultarRepuestos.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        Log.d("MainActivity", "onOptionsItemSelectec");

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_calendar) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent myIntent;
        if (v == this.mBtnAñadirCliente) {
            myIntent = new Intent(this, AddClienteActivity.class);
            startActivity(myIntent);
        } else if (v == this.mBtnModificarCliente) {
            myIntent = new Intent(this, ModificaClienteActivity.class);
            startActivity(myIntent);
        } else if (v == this.mBtnConsultarCliente) {
            myIntent = new Intent(this, ListaClientesActivity.class);
            startActivity(myIntent);
        } else if (v == this.mBtnAñadirReparacion) {
            myIntent = new Intent(this, AddReparacionActivity.class);
            startActivity(myIntent);
        } else if (v == this.mBtnModificarReparacion) {
            myIntent = new Intent(this, ModificaReparacionActivity.class);
            startActivity(myIntent);
        } else if (v == this.mBtnResolverReparacion) {
            myIntent = new Intent(this, SolveReparacionActivity.class);
            startActivity(myIntent);
        } else if (v == this.mBtnConsultarReparacion) {
            myIntent = new Intent(this, ConsultarReparacionesActivity.class);
            startActivity(myIntent);
        } else if (v == this.mBtnConsultarCuentas) {
            myIntent = new Intent(this, VisualizaCuentasActivity.class);
            startActivity(myIntent);
        } else if (v == this.mBtnAñadirRepuestos) {
            //aún no está)
        } else if (v == this.mBtnModificarRepuestos) {
            //aún no está
        } else if (v == this.mBtnConsultarRepuestos) {
            //aún no está
        }
    }
}
