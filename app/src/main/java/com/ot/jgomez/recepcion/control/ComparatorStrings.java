package com.ot.jgomez.recepcion.control;

import java.util.Comparator;

/**
 * Created by jgomez on 14/02/17.
 */

public class ComparatorStrings implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        return o1.compareToIgnoreCase(o2);
    }
}
