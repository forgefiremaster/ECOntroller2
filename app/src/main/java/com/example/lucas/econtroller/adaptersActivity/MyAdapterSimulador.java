package com.example.lucas.econtroller.adaptersActivity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.lucas.econtroller.activitys.AdicionarAparelhosAoSimuladorActivity;
import com.example.lucas.econtroller.activitys.BluetoothInfo;
import com.example.lucas.econtroller.fragments.AdicionarAparelhosSimuladosFragment;
import com.example.lucas.econtroller.fragments.ListarAparelhosSimuladosFragment;

/**
 * Created by Lucas on 13/08/2016.
 */
public class MyAdapterSimulador extends FragmentPagerAdapter {
    Fragment fragment;
    public MyAdapterSimulador(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        if (position == 0){
            fragment = new AdicionarAparelhosSimuladosFragment();
        }else{
            fragment = new ListarAparelhosSimuladosFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
