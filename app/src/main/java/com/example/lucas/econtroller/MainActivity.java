package com.example.lucas.econtroller;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

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
    private boolean bluetoothEstaLigado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        BA = BluetoothAdapter.getDefaultAdapter();
        listDevices = (LinearLayout) findViewById(R.id.aparelhosEncontrados);
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        aSwitch = (Switch) findViewById(R.id.offOn);
        if (BA == null) {
            Toast.makeText(this, new String(getResources().getString(R.string.dispositivoNaoSuporta)), Toast.LENGTH_SHORT).show();
        } else {
            if (BA.isEnabled()) {
                bluetoothEstaLigado = true;
                mudaTextoECorDoSwitch();
            }else{
                bluetoothEstaLigado = false;
                mudaTextoECorDoSwitch();
            }

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

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_simulador) {
        }else if (id == R.id.nav_aparelhos_conectados ){
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void on() {
        //Se o bloetooth estiver desligado então liga
        if (!BA.isEnabled()) {
            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOn, 0);
            Toast.makeText(this, new String(getResources().getString(R.string.ligando)), Toast.LENGTH_LONG).show();

        } else {
            //desligar o bluetooth
            BA.disable();
            Toast.makeText(this, new String(getResources().getString(R.string.desligado)), Toast.LENGTH_LONG).show();
        }
        mudaTextoECorDoSwitch();
    }

    public void visible() {
        if (BA.isEnabled()) {
            Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            startActivityForResult(getVisible, 0);
            Toast.makeText(MainActivity.this, "Visibilidade ativada.", Toast.LENGTH_LONG).show();
        } else {
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
                product.setText("Nome: " + nome[j] + "    ");
                ll.addView(product);
                // Create Button
                final Button btn = new Button(this);
                // Give button an ID
                btn.setId(j + 1);
                btn.setText(nome[j]);
                // set the layoutParams on the button
                btn.setLayoutParams(params);
                // Set click listener for button
                btn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        chameBluetoothInfo(nome[0]);
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
    public void chameBluetoothInfo(String nome) {
        Intent intent = new Intent(this, BluetoothInfo.class);
        intent.putExtra("nome", nome);
        startActivity(intent);
    }

    private void mudaTextoECorDoSwitch() {
        if (bluetoothEstaLigado) {
            aSwitch.setText("On");
            aSwitch.setTextColor(getResources().getColor(R.color.azul));
            bluetoothEstaLigado = false;
        } else {
            aSwitch.setText("Off");
            aSwitch.setTextColor(getResources().getColor(R.color.vermelho));
            bluetoothEstaLigado = true;
        }


    }
}
