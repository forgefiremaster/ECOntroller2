package com.example.lucas.econtroller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.lucas.econtroller.fragments.FragmentTab1;
import com.example.lucas.econtroller.fragments.FragmentTab2;
import com.example.lucas.econtroller.fragments.FragmentTab3;
import com.example.lucas.econtroller.BluetoothInfo;
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
            fragment = new FragmentTab1();
        }else if(position == 1){
            fragment = new FragmentTab2();
        }else{
            fragment = new FragmentTab3();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
