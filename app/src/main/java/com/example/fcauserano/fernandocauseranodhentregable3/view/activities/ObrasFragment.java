package com.example.fcauserano.fernandocauseranodhentregable3.view.activities;


import android.content.Context;
import android.os.Bundle;
import android.os.RecoverySystem;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fcauserano.fernandocauseranodhentregable3.R;
import com.example.fcauserano.fernandocauseranodhentregable3.controller.ObraController;
import com.example.fcauserano.fernandocauseranodhentregable3.model.POJO.Obra;
import com.example.fcauserano.fernandocauseranodhentregable3.utils.ResultListener;
import com.example.fcauserano.fernandocauseranodhentregable3.view.adapters.ObraAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ObrasFragment extends Fragment implements ObraAdapter.NotificadorObraCelda {

    private RecyclerView recyclerViewObras;
    private List<Obra> listadoObras;
    private ObraAdapter obraAdapter;
    private NotificadorObra notificadorObra;


    public ObrasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_obras, container, false);
        recyclerViewObras = view.findViewById(R.id.recycleer_fragment_obras);
        listadoObras = new ArrayList<>();
        obraAdapter = new ObraAdapter(listadoObras, this);
        obtenerObras();
        recyclerViewObras.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerViewObras.setHasFixedSize(true);
        recyclerViewObras.setAdapter(obraAdapter);

        return view;
    }

    private void obtenerObras() {
        final ObraController obraController = new ObraController(getActivity());
        obraController.obtenerObras(new ResultListener<List<Obra>>() {
            @Override
            public void finish(List<Obra> result) {
                listadoObras = result;
                obraAdapter.actualizarObras(result);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        notificadorObra = (NotificadorObra) context;
    }

    @Override
    public void notificarObraClickeada(Obra obra) {
        notificadorObra.notificarObra(obra);
    }

    public interface NotificadorObra {
        void notificarObra(Obra obra);
    }
}
