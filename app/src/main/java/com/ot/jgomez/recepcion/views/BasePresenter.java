package com.ot.jgomez.recepcion.views;

/**
 * Created by jgomez on 9/02/17.
 */

public interface BasePresenter<T,V> {
    void attach(T context, V view);
}
