package com.ot.jgomez.recepcion.views.main;

import android.content.Context;

import com.ot.jgomez.recepcion.api.entities.Makes;
import com.ot.jgomez.recepcion.api.entities.ModelsForMake;
import com.ot.jgomez.recepcion.views.BasePresenter;
import com.ot.jgomez.recepcion.views.BaseView;

import java.util.List;

/**
 * Created by jgomez on 9/02/17.
 */

public interface MainContract {
    interface View extends BaseView<Presenter> {
        void fillDataMakes(List<Makes> list);
        void fillDataModelsbyMakes(List<ModelsForMake> list);
    }

    interface  Presenter extends BasePresenter<Context, View> {
        void getMakes(String search);
        void getModelsbyMakes(String search);
    }
}
