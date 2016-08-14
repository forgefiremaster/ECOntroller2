package com.example.lucas.econtroller;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Outline;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageButton;


/**
 * Created by Lucas on 04/08/2016.
 */
public class BluetoothInfo extends AppCompatActivity implements ActionBar.TabListener {
    String nome = "";
    public ActionBar actionBar;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bluetooth_info_layout);
        //pegandos os parâmetros enviados
        Intent intent = getIntent();
        nome = intent.getStringExtra("nome");

        //Escutando a view Pager
        viewPager = (ViewPager) findViewById(R.id.container);
        //Crio um novo MyAdapter passando como parametro um FragmentManager para seu construtor
        viewPager.setAdapter(new MyAdapter((getSupportFragmentManager())));
        //Criando um change para atulaizar para o actionBar ao passar o dedo
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    actionBar.setTitle(new String(getResources().getString(R.string.aparelhos)));
                } else if (position == 1) {
                    actionBar.setTitle(new String(getResources().getString(R.string.consumo)));
                } else {
                    actionBar.setTitle(new String(getResources().getString(R.string.simulador)));
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //getSupportActionBar - estamos usando a biblioteca de suporte para usá getSupportActionBar () para obter uma instância da barra de ação
        actionBar = getSupportActionBar();

        //setDisplayShowTitleEnabled - nós não queremos para mostrar o título da atividade na barra de ação para que desativar esta opção
        actionBar.setDisplayShowHomeEnabled(false);

        //newtab - criamos os três objectos de tabulação. Eles só aparece na barra de ação quando adicioná-los à Barra de ação
        //setNavigationMode - queremos utilizar a navegação Tab por isso, definir o modo de navegação aqui, passando NAVIGATION_MODE_TABS como o parâmetro
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        //Setando títuloas na TAB
        ActionBar.Tab tab1 = actionBar.newTab().setText(new String(getResources().getString(R.string.aparelhos)));
        tab1.setTabListener(this);
        ActionBar.Tab tab2 = actionBar.newTab().setText(new String(getResources().getString(R.string.consumo)));
        tab2.setTabListener(this);
        ActionBar.Tab tab3 = actionBar.newTab().setText(new String(getResources().getString(R.string.simulador)));
        tab3.setTabListener(this);

        //Adicionando as tabs no action bar
        actionBar.addTab(tab1);
        actionBar.addTab(tab2);
        actionBar.addTab(tab3);

    }

    public String getMyData() {
        return nome;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        //pego a posição atual do view Pager
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

}
