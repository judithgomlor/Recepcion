package com.ot.jgomez.recepcion.api.entities;

/**
 * Created by jgomez on 9/02/17.
 */

public class MakesForVehicle {
    private int MakeId;
    private String MakeName;
    private int VehicleTypeId;
    private String VehicleTypeName;

    public int getMakeId() {
        return MakeId;
    }

    public void setMakeId(int makeId) {
        MakeId = makeId;
    }

    public String getMakeName() {
        return MakeName;
    }

    public void setMakeName(String makeName) {
        MakeName = makeName;
    }

    public int getVehicleTypeId() {
        return VehicleTypeId;
    }

    public void setVehicleTypeId(int vehicleTypeId) {
        VehicleTypeId = vehicleTypeId;
    }

    public String getVehicleTypeName() {
        return VehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        VehicleTypeName = vehicleTypeName;
    }
}
