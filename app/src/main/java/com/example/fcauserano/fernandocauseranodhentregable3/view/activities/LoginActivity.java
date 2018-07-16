package com.example.fcauserano.fernandocauseranodhentregable3.view.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fcauserano.fernandocauseranodhentregable3.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseUser user;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;
    private Button btnCrearCuenta;
    private Button btnIngresar;
    private LoginButton btnLoginFacebook;
    private CallbackManager callbackManagerFacebook;
    private EditText editTextEmail;
    private EditText editTextClave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.editText_email);
        editTextClave = findViewById(R.id.editText_clave);

        btnCrearCuenta = findViewById(R.id.btn_crear_cuenta);
        btnIngresar = findViewById(R.id.btn_ingresar);
        btnLoginFacebook = findViewById(R.id.btn_login_facebook);

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        };

        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editTextEmail.getText().toString().isEmpty()) {
                    crearCuenta(editTextEmail.getText().toString(), editTextClave.getText().toString());
                } else {
                    Toast.makeText(LoginActivity.this, "El email no puede estar vacio", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editTextEmail.getText().toString().isEmpty()) {
                    loginUsuario(editTextEmail.getText().toString(), editTextClave.getText().toString());
                } else {
                    Toast.makeText(LoginActivity.this, "El email no puede estar vacio", Toast.LENGTH_SHORT).show();
                }
            }
        });

        callbackManagerFacebook = CallbackManager.Factory.create();
        btnLoginFacebook.setReadPermissions("email", "public_profile");
        btnLoginFacebook.registerCallback(callbackManagerFacebook, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    private void handleFacebookAccessToken(AccessToken accessToken) {
        AuthCredential authCredential = FacebookAuthProvider.getCredential(accessToken.getToken());
        auth.signInWithCredential(authCredential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            user = auth.getCurrentUser();

                        } else {
                            Toast.makeText(LoginActivity.this, "Error en el ingreso a la cuenta. " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void loginUsuario(final String usuario, String clave) {
        auth.signInWithEmailAndPassword(usuario, clave)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            user = auth.getCurrentUser();

                        } else {
                            Toast.makeText(LoginActivity.this, "Error en el ingreso a la cuenta. " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void crearCuenta(String usuario, String clave) {
        auth.createUserWithEmailAndPassword(usuario, clave)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            user = auth.getCurrentUser();

                        } else {
                            Toast.makeText(LoginActivity.this, "Error al crear la cuenta. " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManagerFacebook.onActivityResult(requestCode, resultCode, data);
    }
}
