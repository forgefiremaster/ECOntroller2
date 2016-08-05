package com.example.lucas.econtroller;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private BluetoothAdapter BA;
    private Set<BluetoothDevice> pairedDevices;
    ListView lv;
    String[] endereco = new String[15];
    String[] lista = new String[15];
    String[] nome = new String[15];
    private LinearLayout listDevices;
    LinearLayout.LayoutParams params = null;
    Switch aSwitch;
    Button parear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BA = BluetoothAdapter.getDefaultAdapter();
        listDevices = (LinearLayout) findViewById(R.id.aparelhosEncontrados);
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        aSwitch = (Switch) findViewById(R.id.offOn);

        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                on();
            }
        });

        parear = (Button) findViewById(R.id.parear);
        parear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list();
            }
        });
    }

    public void on(){
        if(BA == null){
            Toast.makeText(this, new String(getResources().getString(R.string.dispositivoNaoSuporta)), Toast.LENGTH_SHORT).show();
        }else{
            //Se o bloetooth estiver desligado então liga
            if (!BA.isEnabled()) {
                Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(turnOn, 0);
                Toast.makeText(this, new String(getResources().getString(R.string.ligando)), Toast.LENGTH_LONG).show();
                aSwitch.setText("Off");


            } else {
                //desligar o bluetooth
                BA.disable();
                Toast.makeText(this, new String(getResources().getString(R.string.desligado)), Toast.LENGTH_LONG).show();
                aSwitch.setText("On");
            }
        }

    }

    public void visible(){
        if(BA.isEnabled()){
            Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            startActivityForResult(getVisible, 0);
            Toast.makeText(MainActivity.this,"Visibilidade ativada.",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(MainActivity.this, "Ligue o Bluetooth.", Toast.LENGTH_LONG).show();
        }
    }


    public void list() {
        if (BA.isEnabled()) {
            final ArrayAdapter adapter;
            pairedDevices = BA.getBondedDevices();
            ArrayList<String> list = new ArrayList<String>();
            ArrayAdapter<String> Listadapter;
            int j = 0;
            for (BluetoothDevice devices : pairedDevices) {
                lista[j] = devices.getName();
                endereco[j] = devices.getAddress();
                nome[j] = devices.getName();
                list.add(devices.getName());
                // Create LinearLayout
                LinearLayout ll = new LinearLayout(this);
                ll.setOrientation(LinearLayout.HORIZONTAL);
                // Create TextView
                TextView product = new TextView(this);
                product.setText("Nome: "+nome[j]+"    ");
                ll.addView(product);
                // Create Button
                final Button btn = new Button(this);
                // Give button an ID
                btn.setId(j+1);
                btn.setText(nome[j]);
                // set the layoutParams on the button
                btn.setLayoutParams(params);
                // Set click listener for button
                btn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        cameBluetoothInfo(nome[0]);
                    }
                });
                //Add button to LinearLayout
                ll.addView(btn);
                //Add button to LinearLayout defined in XML
                listDevices.addView(ll);
                j++;
            }
            Toast.makeText(MainActivity.this, "Encontrando dipositivos.", Toast.LENGTH_LONG).show();
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void cameBluetoothInfo(String nome){
        Intent intent = new Intent(this, BluetoothInfo.class);
        intent.putExtra("nome",nome);
        startActivity(intent);
    }
}
