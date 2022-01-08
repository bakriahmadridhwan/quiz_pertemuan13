package com.example.quizm13.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.quizm13.database.entitas.Kontak;

import java.util.List;

@Dao
public interface KontakDao {

    @Query("SELECT * FROM kontak")
    List<Kontak> getAll();

    @Insert
    void insertAll(Kontak... kontaks);

    @Query("UPDATE Kontak SET nama=:nama, no_hp=:no_hp WHERE id=:id")
    void update(int id, String nama, String no_hp);

    @Query("SELECT * FROM kontak WHERE id=:id")
    Kontak get(int id);

    @Delete
    void delete(Kontak kontak);

}
