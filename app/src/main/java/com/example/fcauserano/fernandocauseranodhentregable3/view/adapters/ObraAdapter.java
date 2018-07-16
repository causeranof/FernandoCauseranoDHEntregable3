package com.example.fcauserano.fernandocauseranodhentregable3.view.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fcauserano.fernandocauseranodhentregable3.R;
import com.example.fcauserano.fernandocauseranodhentregable3.model.POJO.Obra;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class ObraAdapter extends RecyclerView.Adapter {

    private List<Obra> obras;
    private NotificadorObraCelda notificadorObraCelda;

    public ObraAdapter(List<Obra> obras, NotificadorObraCelda notificadorObraCelda) {
        this.obras = obras;
        this.notificadorObraCelda = notificadorObraCelda;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.celda_fragment_obras, parent, false);
        ViewHolderObra viewHolderObra = new ViewHolderObra(view);
        return viewHolderObra;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Obra obra = obras.get(position);
        ViewHolderObra viewHolderObra = (ViewHolderObra) holder;
        viewHolderObra.cargarObra(obra);
    }

    @Override
    public int getItemCount() {
        if (obras != null) {
            return obras.size();
        } else {
            return 0;
        }
    }

    public void actualizarObras(List<Obra> listadoObras) {
        this.obras.addAll(listadoObras);
        notifyDataSetChanged();
    }

    private class ViewHolderObra extends RecyclerView.ViewHolder {
        private TextView textViewTituloObra;
        private ImageView imageViewImagenObra;
        private CardView cardViewDetalleObra;

        public ViewHolderObra(View itemView) {
            super(itemView);
            textViewTituloObra = itemView.findViewById(R.id.textviewObra);
            imageViewImagenObra = itemView.findViewById(R.id.imageviewObra);
            cardViewDetalleObra = itemView.findViewById(R.id.celda_obra);
            cardViewDetalleObra.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Obra obra = obras.get(getAdapterPosition());
                    notificadorObraCelda.notificarObraClickeada(obra);
                }
            });
        }

        public void cargarObra(Obra obra) {
            textViewTituloObra.setText(obra.getName());
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference reference = storage.getReference();
            reference = reference.child("obras/" + obra.getImage());

            Glide.with(itemView.getContext())
                    .using(new FirebaseImageLoader())
                    .load(reference)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.image_error)
                    .into(imageViewImagenObra);
        }
    }

    public interface NotificadorObraCelda{
        void notificarObraClickeada(Obra obra);
    }
}
