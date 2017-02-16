package com.ot.jgomez.recepcion.views.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionMenu;
import com.ot.jgomez.recepcion.R;
import com.ot.jgomez.recepcion.adapters.ConsultaReparacionRvAdapter;
import com.ot.jgomez.recepcion.items.ConsultaReparaciones;
import com.ot.jgomez.recepcion.views.addcliente.AddClienteActivity;
import com.ot.jgomez.recepcion.views.addreparacion.AddReparacionActivity;
import com.ot.jgomez.recepcion.views.listaclientes.ListaClientesActivity;
import com.ot.jgomez.recepcion.views.solvereparacion.SolveReparacionActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView mRecyclerHoyPendiente;
    private RecyclerView mRecyclerHoyResuelto;
    private RecyclerView mRecyclerAyerPendiente;
    private RecyclerView mRecyclerAyerResuelto;
    private TextView mHoyPendiente;
    private TextView mHoyResuelto;
    private TextView mAyerPendiente;
    private TextView mAyerResuelto;
    private ConsultaReparacionRvAdapter mAdapterHoyPendiente;
    private ConsultaReparacionRvAdapter mAdapterHoyResuelto;
    private ConsultaReparacionRvAdapter mAdapterAyerPendiente;
    private ConsultaReparacionRvAdapter mAdapterAyerResuelto;
    private List<ConsultaReparaciones> mListHoyPendiente;
    private List<ConsultaReparaciones> mListHoyResuelto;
    private List<ConsultaReparaciones> mListAyerPendiente;
    private List<ConsultaReparaciones> mListAyerResuelto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getApplicationContext().getString(R.string.consultar_reparaciones));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
               this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        init();
        iniLists();
        layoutsAdapters();
    }

    private void iniLists() {
        this.mListAyerPendiente = new ArrayList<>();
        this.mListAyerResuelto = new ArrayList<>();
        this.mListHoyPendiente = new ArrayList<>();
        this.mListHoyResuelto = new ArrayList<>();
    }

    private void layoutsAdapters() {
        this.mAdapterAyerPendiente = new ConsultaReparacionRvAdapter(this.mListAyerPendiente, getApplicationContext());
        this.mRecyclerAyerPendiente.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        this.mRecyclerAyerPendiente.setAdapter(this.mAdapterAyerPendiente);
        if(this.mRecyclerAyerPendiente.getAdapter().getItemCount() == 0) {
            this.mRecyclerAyerPendiente.setVisibility(View.GONE);
            this.mAyerPendiente.setVisibility(View.VISIBLE);
        } else {
            this.mRecyclerAyerPendiente.setVisibility(View.VISIBLE);
            this.mAyerPendiente.setVisibility(View.GONE);
        }

        this.mAdapterAyerResuelto = new ConsultaReparacionRvAdapter(this.mListAyerResuelto, getApplicationContext());
        this.mRecyclerAyerResuelto.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        this.mRecyclerAyerResuelto.setAdapter(this.mAdapterAyerResuelto);
        if(this.mRecyclerAyerResuelto.getAdapter().getItemCount() == 0) {
            this.mRecyclerAyerResuelto.setVisibility(View.GONE);
            this.mAyerResuelto.setVisibility(View.VISIBLE);
        } else {
            this.mRecyclerAyerResuelto.setVisibility(View.VISIBLE);
            this.mAyerResuelto.setVisibility(View.GONE);
        }

        this.mAdapterHoyPendiente = new ConsultaReparacionRvAdapter(this.mListHoyPendiente, getApplicationContext());
        this.mRecyclerHoyPendiente.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        this.mRecyclerHoyPendiente.setAdapter(this.mAdapterHoyPendiente);
        if(this.mRecyclerHoyPendiente.getAdapter().getItemCount() == 0) {
            this.mRecyclerHoyPendiente.setVisibility(View.GONE);
            this.mHoyPendiente.setVisibility(View.VISIBLE);
        } else {
            this.mRecyclerHoyPendiente.setVisibility(View.VISIBLE);
            this.mHoyPendiente.setVisibility(View.GONE);
        }

        this.mAdapterHoyResuelto = new ConsultaReparacionRvAdapter(this.mListHoyResuelto, getApplicationContext());
        this.mRecyclerHoyResuelto.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        this.mRecyclerHoyResuelto.setAdapter(this.mAdapterHoyResuelto);
        if(this.mRecyclerHoyResuelto.getAdapter().getItemCount() == 0) {
            this.mRecyclerHoyResuelto.setVisibility(View.GONE);
            this.mHoyResuelto.setVisibility(View.VISIBLE);
        } else {
            this.mRecyclerHoyResuelto.setVisibility(View.VISIBLE);
            this.mHoyResuelto.setVisibility(View.GONE);
        }
    }

    private void init() {
        this.mRecyclerHoyResuelto = (RecyclerView) findViewById(R.id.recycler_consulta_reparacion_resuelta_hoy);
        this.mRecyclerHoyPendiente = (RecyclerView) findViewById(R.id.recycler_consulta_reparacion_pendiente_hoy);
        this.mRecyclerAyerResuelto = (RecyclerView) findViewById(R.id.recycler_consulta_reparacion_resuelta_ayer);
        this.mRecyclerAyerPendiente = (RecyclerView) findViewById(R.id.recycler_consulta_reparacion_pendiente_ayer);
        this.mHoyResuelto = (TextView) findViewById(R.id.no_hoy_resuelto);
        this.mHoyPendiente = (TextView) findViewById(R.id.no_hoy_pendiente);
        this.mAyerResuelto = (TextView) findViewById(R.id.no_ayer_resueltas);
        this.mAyerPendiente = (TextView) findViewById(R.id.no_ayer_pendiente);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_add_cliente) {
            Intent myIntent = new Intent(this, AddClienteActivity.class);
            startActivity(myIntent);
        } else if (id == R.id.nav_add_entrada) {
            Intent myIntent = new Intent(this, AddReparacionActivity.class);
            startActivity(myIntent);
        } else if (id == R.id.nav_res_entrada) {
            Intent myIntent = new Intent(this, SolveReparacionActivity.class);
            startActivity(myIntent);
        } else if (id == R.id.nav_consulta_cliente) {
            Intent myIntent = new Intent(this, ListaClientesActivity.class);
            startActivity(myIntent);
        } else if (id == R.id.nav_consulta_reparacion) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
