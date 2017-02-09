package com.ot.jgomez.recepcion.database;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by jgomez on 8/02/17.
 */
@Database(name = ClientesDatabase.NAME, version = ClientesDatabase.VERSION)
public class ClientesDatabase {
    public static final String NAME = "clientesDDBB";
    public static final int VERSION = 1;
}
