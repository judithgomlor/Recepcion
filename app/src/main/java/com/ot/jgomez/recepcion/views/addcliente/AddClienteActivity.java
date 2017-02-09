package com.ot.jgomez.recepcion.views.addcliente;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.ot.jgomez.recepcion.R;

public class AddClienteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cliente);

        ActionBar myBar = getSupportActionBar();
        myBar.setDisplayShowHomeEnabled(true);
        myBar.setHomeButtonEnabled(true);
        myBar.setDisplayHomeAsUpEnabled(true);
        myBar.setTitle(R.string.ficha_cliente);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
