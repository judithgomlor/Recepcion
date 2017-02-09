package com.ot.jgomez.recepcion.api.entities;

import java.util.List;

/**
 * Created by jgomez on 9/02/17.
 */

public class DataResponse<T> {
    public int Count;
    public String Message;
    public String SearchCriteria;
    public List<T> Results;
}
