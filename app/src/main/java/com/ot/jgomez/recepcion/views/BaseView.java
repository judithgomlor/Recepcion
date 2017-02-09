package com.ot.jgomez.recepcion.views;

/**
 * Created by jgomez on 9/02/17.
 */

public interface BaseView<T> {
    void setPresenter(T presenter);
    void showMessage(String message);
}
