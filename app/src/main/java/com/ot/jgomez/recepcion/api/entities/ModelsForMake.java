package com.ot.jgomez.recepcion.api.entities;

/**
 * Created by jgomez on 9/02/17.
 */

public class ModelsForMake {
    private int Make_ID;
    private String Make_Name;
    private int Model_ID;
    private String Model_Name;

    public int getMake_ID() {
        return Make_ID;
    }

    public void setMake_ID(int make_ID) {
        Make_ID = make_ID;
    }

    public String getMake_Name() {
        return Make_Name;
    }

    public void setMake_Name(String make_Name) {
        Make_Name = make_Name;
    }

    public int getModel_ID() {
        return Model_ID;
    }

    public void setModel_ID(int model_ID) {
        Model_ID = model_ID;
    }

    public String getModel_Name() {
        return Model_Name;
    }

    public void setModel_Name(String model_Name) {
        Model_Name = model_Name;
    }
}
