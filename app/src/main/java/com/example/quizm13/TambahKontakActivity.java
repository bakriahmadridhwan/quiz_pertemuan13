package com.example.quizm13;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizm13.database.AppDatabase;
import com.example.quizm13.database.entitas.Kontak;

public class TambahKontakActivity extends AppCompatActivity {

    private EditText etNama, etHP;
    private Button btnTambahKontak;
    AppDatabase database;

    int id = 0;
    boolean isEdit = false;

    ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_kontak);

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etNama = findViewById(R.id.etNama);
        etHP = findViewById(R.id.etHP);
        btnTambahKontak = findViewById(R.id.btnTambahKontak);

        database = AppDatabase.getInstance(getApplicationContext());

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        if (id > 0) {
            isEdit = true;
            this.setTitle("Ubah Kontak");

            Kontak kontak = database.kontakDao().get(id);
            etNama.setText(kontak.nama);
            etHP.setText(kontak.HP);
        } else {
            isEdit = false;
            this.setTitle("Tambah Kontak");
        }


        btnTambahKontak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Kontak kontak = new Kontak();
                kontak.nama = etNama.getText().toString();
                kontak.HP = etHP.getText().toString();

                String nama = etNama.getText().toString().trim();
                String hp = etHP.getText().toString().trim();

                if (isEdit) {
                    database.kontakDao().update(id, kontak.nama, kontak.HP);
                    Toast.makeText(TambahKontakActivity.this, "Data berhasil diubah", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    if (nama.equals("") || hp.equals("")) {
                        Toast.makeText(TambahKontakActivity.this, "data harus diisi...!", Toast.LENGTH_SHORT).show();
                    } else {
                        database.kontakDao().insertAll(kontak);
                        Toast.makeText(TambahKontakActivity.this, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}