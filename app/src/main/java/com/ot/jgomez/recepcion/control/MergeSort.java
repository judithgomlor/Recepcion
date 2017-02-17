package com.ot.jgomez.recepcion.control;

import android.util.Log;

import com.ot.jgomez.recepcion.items.NombrePos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jgomez on 17/02/17.
 */


/**
 * Clase que ordena una lista de strings junto con la posición que tiene en la lista original.
 */
public class MergeSort {

    private List<NombrePos> strList;

    public MergeSort(List<NombrePos> strList) {
        this.strList = strList;
    }

    public void sort() {
        this.strList = mergeSort(this.strList);
    }

    private List<NombrePos> mergeSort(List<NombrePos> whole) {
        List<NombrePos> left = new ArrayList<>();
        List<NombrePos> right = new ArrayList<>();
        int center;

        if (whole.size() == 1) {
            return whole;
        } else {
            center = whole.size() / 2;

            //copiamos la mitad izquierda de la lista whole en "left"
            for (int i = 0; i < center; ++i) {
                left.add(whole.get(i));
            }

            //copiamos la mitad derecha de la lista whole en "right"
            for (int i = center; i < whole.size(); ++i) {
                right.add(whole.get(i));
            }

            //ordenamos los valores de la izquierda y derecha de la lista
            left = mergeSort(left);
            right = mergeSort(right);

            //volvemos a unir las listas
            merge(left, right, whole);
        }
        return whole;
    }

    private void merge(List<NombrePos> left, List<NombrePos> right,
                       List<NombrePos> whole) {
        int leftIndex = 0;
        int rightIndex = 0;
        int wholeIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
                if ((left.get(leftIndex).getmNombre().compareTo(
                        right.get(rightIndex).getmNombre()
                )) < 0) {
                    whole.set(wholeIndex, left.get(leftIndex));
                    leftIndex++;
                } else {
                    whole.set(wholeIndex, right.get(rightIndex));
                    rightIndex++;
                }
                wholeIndex++;
        }

        List<NombrePos> rest = new ArrayList<>();
        int restIndex;
        if(leftIndex >= left.size()) {
            rest = right;
            restIndex = rightIndex;
        } else {
            rest = left;
            restIndex = leftIndex;
        }

        for(int i = restIndex; i < rest.size(); ++i) {
            whole.set(wholeIndex, rest.get(i));
            wholeIndex++;
        }
    }

    public void show() {
        Log.d("MergeSort", "ordenados: ");
        for(int i = 0; i < strList.size(); ++i) {
            Log.d("MergeSort", strList.get(i).getmNombre());
        }
    }

}
