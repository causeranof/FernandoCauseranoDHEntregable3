package com.example.fcauserano.fernandocauseranodhentregable3.view.activities;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.fcauserano.fernandocauseranodhentregable3.R;
import com.example.fcauserano.fernandocauseranodhentregable3.model.POJO.Artista;
import com.example.fcauserano.fernandocauseranodhentregable3.model.POJO.Obra;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleObraFragment extends Fragment {


    public static final String KEY_OBRA = "key_obra";
    private static final String ARTISTS = "artists";
    private ImageView imagenObra;
    private TextView tituloObra;
    private TextView nombreArtista;
    private TextView nacionalidadArtista;
    private TextView influeciaArtista;



    public DetalleObraFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_obra, container, false);
        ImageView imagenObra = view.findViewById(R.id.detalle_imagen_obra);
        tituloObra = view.findViewById(R.id.detalle_titulo_obra);
        nombreArtista = view.findViewById(R.id.detalle_nombre_artista);
        nacionalidadArtista = view.findViewById(R.id.detalle_nacionalidad_artista);
        influeciaArtista = view.findViewById(R.id.detalle_influenciado_artista);

        Bundle bundle = getArguments();
        final Obra obra = (Obra) bundle.getSerializable(KEY_OBRA);
        tituloObra.setText(obra.getName());
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference imageReference = storage.getReference();
        imageReference = imageReference.child("obras/" + obra.getImage());

        Glide.with(getActivity())
                .using(new FirebaseImageLoader())
                .load(imageReference)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.image_error)
                .into(imagenObra);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference().child(ARTISTS).child(obra.getArtistId());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    Artista artista = dataSnapshot.getValue(Artista.class);
                    obra.setArtista(artista);
                    nombreArtista.setText(obra.getArtista().getName());
                    nacionalidadArtista.setText(obra.getArtista().getNationality());
                    influeciaArtista.setText(obra.getArtista().getInfluenced_by());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return view;
    }

}
