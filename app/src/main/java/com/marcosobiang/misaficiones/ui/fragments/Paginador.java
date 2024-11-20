package com.marcosobiang.misaficiones.ui.fragments;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.marcosobiang.misaficiones.fr.aficiones.Comer;
import com.marcosobiang.misaficiones.fr.aficiones.Dormir;
import com.marcosobiang.misaficiones.fr.aficiones.Programar;

public class Paginador extends FragmentPagerAdapter {
    private final Context context;
    public Paginador(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
         switch(position){
            case 0:
                return new Comer();
            case 1:
                return new Dormir();

             case 2:
                 return new Programar();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
