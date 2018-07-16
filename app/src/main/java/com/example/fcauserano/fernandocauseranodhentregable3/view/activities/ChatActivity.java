package com.example.fcauserano.fernandocauseranodhentregable3.view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.fcauserano.fernandocauseranodhentregable3.R;
import com.example.fcauserano.fernandocauseranodhentregable3.model.POJO.Mensaje;
import com.example.fcauserano.fernandocauseranodhentregable3.view.adapters.ChatAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ChatActivity extends AppCompatActivity {

    private EditText edtMensaje;
    private RecyclerView recyclerMensajes;
    private Button btnEnviar;

    private ChatAdapter chatAdapter;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        edtMensaje = findViewById(R.id.txt_mensaje_chat);
        recyclerMensajes = findViewById(R.id.rv_mensajes);
        btnEnviar = findViewById(R.id.btn_enviar);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("chat");

        chatAdapter = new ChatAdapter(this);
        recyclerMensajes.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerMensajes.setAdapter(chatAdapter);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH:mm");
                String fecha = dateFormat.format(Calendar.getInstance().getTime());
                Mensaje mensaje = new Mensaje();
                mensaje.setMensaje(edtMensaje.getText().toString());
                mensaje.setFecha(fecha);
                mensaje.setAutor(user.getEmail());
                databaseReference.push().setValue(mensaje);
                edtMensaje.setText("");
            }
        });

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Mensaje mensaje = dataSnapshot.getValue(Mensaje.class);
                chatAdapter.agregarMensaje(mensaje);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        chatAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                recyclerMensajes.scrollToPosition(chatAdapter.getItemCount() - 1);
            }
        });
    }
}
