package com.example.lucas.econtroller.activitys;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.example.lucas.econtroller.R;
import com.example.lucas.econtroller.adaptersActivity.MyAdapterSimulador;


/**
 * Created by Lucas on 13/08/2016.
 */
public class SimuladorActivity extends AppCompatActivity implements ActionBar.TabListener {
    String nome = "";
    ViewPager viewPager;
    public ActionBar actionBar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simulador_layout_activity);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Escutando a view Pager
        viewPager = (ViewPager) findViewById(R.id.container_simulador);
        //Crio um novo MyAdapter passando como parametro um FragmentManager para seu construtor
        viewPager.setAdapter(new MyAdapterSimulador(getSupportFragmentManager()));
        //Criando um change para atulaizar para o actionBar ao passar o dedo
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    actionBar.setTitle(new String(getResources().getString(R.string.gasto)));
                } else{
                    actionBar.setTitle(new String(getResources().getString(R.string.cadastros)));
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
        ActionBar.Tab tab1 = actionBar.newTab().setText(new String(getResources().getString(R.string.gasto)));
        tab1.setTabListener(this);
        ActionBar.Tab tab2 = actionBar.newTab().setText(new String(getResources().getString(R.string.cadastros)));
        tab2.setTabListener(this);

        //Adicionando as tabs no action bar
        actionBar.addTab(tab1);
        actionBar.addTab(tab2);

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


