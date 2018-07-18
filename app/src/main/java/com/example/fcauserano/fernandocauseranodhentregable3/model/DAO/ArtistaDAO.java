package com.example.fcauserano.fernandocauseranodhentregable3.model.DAO;

import com.example.fcauserano.fernandocauseranodhentregable3.model.POJO.Artista;
import com.example.fcauserano.fernandocauseranodhentregable3.utils.ResultListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ArtistaDAO {

    private static final String ARTISTS = "artists";

    public void obtenerArtistaPorID(String idArtista, final ResultListener<Artista> artistaResultListener){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference().child(ARTISTS).child(idArtista);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    Artista artista = dataSnapshot.getValue(Artista.class);
                    artistaResultListener.finish(artista);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void obtenerArtistas(final ResultListener<List<Artista>> resultListener){
        FirebaseDatabase artistasDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = artistasDatabase.getReference().child(ARTISTS);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    List<Artista> artistas = new ArrayList<>();
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                        Artista artista = snapshot.getValue(Artista.class);
                        artistas.add(artista);
                    }
                    resultListener.finish(artistas);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
