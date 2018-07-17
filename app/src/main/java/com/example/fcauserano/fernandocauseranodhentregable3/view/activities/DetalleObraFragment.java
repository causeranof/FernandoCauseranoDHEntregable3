package com.example.fcauserano.fernandocauseranodhentregable3.view.activities;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fcauserano.fernandocauseranodhentregable3.R;
import com.example.fcauserano.fernandocauseranodhentregable3.controller.ArtistaController;
import com.example.fcauserano.fernandocauseranodhentregable3.model.POJO.Artista;
import com.example.fcauserano.fernandocauseranodhentregable3.model.POJO.Obra;
import com.example.fcauserano.fernandocauseranodhentregable3.utils.ResultListener;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleObraFragment extends Fragment {

    public static final String KEY_OBRA = "key_obra";
    private ImageView imagenObra;
    private TextView tituloObra;
    private TextView nombreArtista;
    private TextView nacionalidadArtista;
    private TextView influeciaArtista;
    private ArtistaController artistaController;

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
        artistaController = new ArtistaController(getActivity());

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

        artistaController.obtenerArtistaPorId(obra.getArtistId(), new ResultListener<Artista>() {
            @Override
            public void finish(Artista artista) {
                obra.setArtista(artista);
                nombreArtista.setText(obra.getArtista().getName());
                nacionalidadArtista.setText(obra.getArtista().getNationality());
                influeciaArtista.setText(obra.getArtista().getInfluenced_by());
            }
        });

        return view;
    }

}
