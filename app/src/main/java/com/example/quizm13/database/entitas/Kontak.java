package com.example.quizm13.database.entitas;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Kontak {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String nama;

    @ColumnInfo(name = "no_hp")
    public String HP;

}
