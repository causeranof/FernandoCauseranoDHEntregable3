package com.example.fcauserano.fernandocauseranodhentregable3.view.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.fcauserano.fernandocauseranodhentregable3.R;
import com.example.fcauserano.fernandocauseranodhentregable3.model.POJO.Obra;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements ObrasFragment.NotificadorObra {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_chat:
                        Intent intentChat = new Intent(MainActivity.this, ChatActivity.class);
                        startActivity(intentChat);
                        break;
                    case R.id.item_cerrar_sesion:
                        cerrarSesion();
                        Intent intentLogin = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intentLogin);
                        finish();
                        break;
                }
                return true;
            }
        });
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment_obras, new ObrasFragment());
        fragmentTransaction.commit();

    }

    @Override
    public void notificarObra(Obra obra) {
        Intent intent = new Intent(this, DetalleObraActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(DetalleObraFragment.KEY_OBRA, obra);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void cerrarSesion() {
        if (FirebaseAuth.getInstance().getCurrentUser()!=null){
            if (AccessToken.getCurrentAccessToken()!=null){
                LoginManager.getInstance().logOut();
            }
            FirebaseAuth.getInstance().signOut();
        }

    }
}
