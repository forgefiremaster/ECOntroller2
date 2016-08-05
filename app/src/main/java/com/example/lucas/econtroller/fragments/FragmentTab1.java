package com.example.lucas.econtroller.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.lucas.econtroller.BluetoothInfo;
import com.example.lucas.econtroller.R;

 /**
 * Created by Lucas on 23/07/2016.
 */
public class FragmentTab1 extends Fragment {
     TextView textview;
     private String nome = "";

     public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment_tab1, container, false);
         textview = (TextView) view.findViewById(R.id.tabtextview);

         BluetoothInfo activity = (BluetoothInfo) getActivity();
         nome = activity.getMyData();
         textview.setText(nome);

         return view;
     }
 }
