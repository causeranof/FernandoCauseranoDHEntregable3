package com.example.fcauserano.fernandocauseranodhentregable3.model.DAO;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.fcauserano.fernandocauseranodhentregable3.model.POJO.Artista;
import com.example.fcauserano.fernandocauseranodhentregable3.model.POJO.Obra;

@Database(entities = {Artista.class, Obra.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;
    private static AppDatabase INSTANCE_IN_MEMORY;
    private static final String DATABASE = "Entregable3";

    public abstract ArtistaRoomDAO artistaRoomDAO();
    public abstract ObraRoomDAO obraRoomDAO();

    public static AppDatabase getDatabaseInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE)
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
    public static AppDatabase getInMemoryDatabase(Context context){
        if (INSTANCE_IN_MEMORY == null){
            INSTANCE_IN_MEMORY = Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class)
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE_IN_MEMORY;
    }

    public static void destroyInstance() {
        INSTANCE = null;
        INSTANCE_IN_MEMORY = null;
    }


}
