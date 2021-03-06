package com.example.fcauserano.fernandocauseranodhentregable3.model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.fcauserano.fernandocauseranodhentregable3.model.POJO.Obra;

import java.util.List;

@Dao
public interface ObraRoomDAO {

    @Query("select * from obra")
    List<Obra> loadAllObras();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAllObras(List<Obra> obras);

    @Query("delete from obra")
    void deleteAllObras();
}

