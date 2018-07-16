package com.example.fcauserano.fernandocauseranodhentregable3.view.activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.fcauserano.fernandocauseranodhentregable3.R;

public class DetalleObraActivity extends AppCompatActivity {

    private DetalleObraFragment detalleObraFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_obra);

        Bundle bundle = getIntent().getExtras();

        detalleObraFragment = new DetalleObraFragment();
        detalleObraFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.contenedor_fragment_detalle_obra, detalleObraFragment);
        fragmentTransaction.commit();



    }
}
