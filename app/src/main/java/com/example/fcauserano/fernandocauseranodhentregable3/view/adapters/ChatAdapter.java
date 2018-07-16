package com.example.fcauserano.fernandocauseranodhentregable3.view.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fcauserano.fernandocauseranodhentregable3.R;
import com.example.fcauserano.fernandocauseranodhentregable3.model.POJO.Mensaje;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter {

    private List<Mensaje> mensajes;
    private Context context;

    public ChatAdapter(Context context) {
        mensajes = new ArrayList<>();
        this.context = context;
    }

    public void agregarMensaje(Mensaje mensaje) {
        mensajes.add(mensaje);
        notifyItemChanged(mensajes.size());
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.celda_mensaje_chat, parent, false);
        ViewHolderChat viewHolderChat = new ViewHolderChat(view);
        return viewHolderChat;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Mensaje mensaje = mensajes.get(position);
        ViewHolderChat viewHolderChat = (ViewHolderChat) holder;
        viewHolderChat.cargarMensaje(mensaje);
    }

    @Override
    public int getItemCount() {
        if (mensajes != null) {
            return mensajes.size();
        } else {
            return 0;
        }
    }

    private class ViewHolderChat extends RecyclerView.ViewHolder {
        private TextView txtNombreMensaje;
        private TextView txtFechaMensaje;
        private TextView txtDetalleMensaje;

        public ViewHolderChat(View itemView) {
            super(itemView);
            txtNombreMensaje = itemView.findViewById(R.id.txt_nombre_mensaje);
            txtFechaMensaje = itemView.findViewById(R.id.txt_fecha_mensaje);
            txtDetalleMensaje = itemView.findViewById(R.id.txt_detalle_mensaje);
        }

        @SuppressLint("ResourceAsColor")
        public void cargarMensaje(Mensaje mensaje) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user.getEmail().equals(mensaje.getAutor())) {
                txtNombreMensaje.setText("Tú");
                txtNombreMensaje.setTextColor(context.getResources().getColor(R.color.colorNombrePropio));
                txtDetalleMensaje.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
                txtDetalleMensaje.setBackgroundColor(context.getResources().getColor(R.color.colorMensajePropio));
            } else {
                txtNombreMensaje.setText(mensaje.getAutor());
                txtNombreMensaje.setTextColor(context.getResources().getColor(R.color.colorNombreAjeno));
                txtDetalleMensaje.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            }
            txtFechaMensaje.setText(mensaje.getFecha());
            txtDetalleMensaje.setText(mensaje.getMensaje());

        }
    }
}
