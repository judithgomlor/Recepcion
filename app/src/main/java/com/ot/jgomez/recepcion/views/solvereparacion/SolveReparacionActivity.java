package com.ot.jgomez.recepcion.views.solvereparacion;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.ot.jgomez.recepcion.R;

public class SolveReparacionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solve_reparacion);

        ActionBar myBar = getSupportActionBar();
        myBar.setDisplayHomeAsUpEnabled(true);
        myBar.setHomeButtonEnabled(true);
        myBar.setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
