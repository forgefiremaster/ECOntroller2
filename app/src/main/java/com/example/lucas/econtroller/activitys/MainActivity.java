package com.example.lucas.econtroller.activitys;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.lucas.econtroller.R;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BluetoothAdapter BA;
    private Set<BluetoothDevice> pairedDevices;
    String[] endereco = new String[15];
    String[] lista = new String[15];
    String[] nome = new String[15];
    private LinearLayout listDevices;
    LinearLayout.LayoutParams params = null;
    Button button;

    SwipeRefreshLayout swipeLayout ;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setElevation(0);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        listDevices = (LinearLayout) findViewById(R.id.aparelhosEncontrados);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        button = (Button) findViewById(R.id.ligar_button);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        BA = BluetoothAdapter.getDefaultAdapter();
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 120);
        params.setMargins(5,5,5,5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(BA == null){
                    Toast.makeText(getBaseContext(),"Seu aparelho não suporta o bluetooth.",Toast.LENGTH_SHORT).show();
                }else{
                    on();
                }
            }
        });
        swipeLayout.setColorSchemeColors(getResources().getColor(R.color.vermelho),getResources().getColor(R.color.azul),getResources().getColor(R.color.amarelo), getResources().getColor(R.color.green));
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeLayout.setRefreshing(true);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list();
                        swipeLayout.setRefreshing(false);
                    }
                }, 2800);
                Toast.makeText(MainActivity.this, "Encontrando dipositivos.", Toast.LENGTH_LONG).show();
            }
        });


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

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_simulador) {
            Intent intent = new Intent(this, SimuladorActivity.class);
            startActivity(intent);
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
            Toast.makeText(getBaseContext(),"Ligando.",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getBaseContext(),"O Bluetooth já está ligado.",Toast.LENGTH_SHORT).show();
        }
    }
    public void disable(){
        //desligar o bluetooth
        BA.disable();
        Toast.makeText(this, new String(getResources().getString(R.string.desligado)), Toast.LENGTH_LONG).show();
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
                // Create Button
                final Button btn = new Button(this);
                // Give button an ID
                btn.setId(j + 1);
                btn.setText(nome[j]);
                btn.getBackground().setAlpha(10);
                btn.setTextSize(20);
                btn.setTextColor(getResources().getColor(R.color.white));
               //  set the layoutParams on the button
                btn.setLayoutParams(params);
               //  Set click listener for button
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
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void chameBluetoothInfo(String nome) {
        Intent intent = new Intent(this, BluetoothInfo.class);
        intent.putExtra("nome", nome);
        startActivity(intent);
    }
}
