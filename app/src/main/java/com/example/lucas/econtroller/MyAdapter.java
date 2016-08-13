package com.example.lucas.econtroller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.lucas.econtroller.fragments.MeusAparelhos;
import com.example.lucas.econtroller.fragments.SimuladorDeAparelhos;
import com.example.lucas.econtroller.fragments.ConsumoDosMeusAparelhos;

/**
 * Created by Lucas on 26/07/2016.
 */
public class MyAdapter extends FragmentPagerAdapter  {
    Fragment fragment;
    public MyAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            fragment = new MeusAparelhos();
        }else if(position == 1){
            fragment = new ConsumoDosMeusAparelhos();
        }else{
            fragment = new SimuladorDeAparelhos();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
