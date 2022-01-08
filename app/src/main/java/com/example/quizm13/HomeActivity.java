package com.example.quizm13;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.quizm13.adapter.KontakAdapter;
import com.example.quizm13.database.AppDatabase;
import com.example.quizm13.database.entitas.Kontak;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    RecyclerView rvKontak;
    AppDatabase database;
    KontakAdapter kontakAdapter;
    List<Kontak> list;
    ImageButton ibKeluar;

    AlertDialog.Builder dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        rvKontak = findViewById(R.id.rvKontak);
        ibKeluar = findViewById(R.id.ibKeluar);

        final SharedPrefManager sharedPrefManager = new SharedPrefManager(this);

        database = AppDatabase.getInstance(getApplicationContext());

        list = new ArrayList<>();
        list.clear();
        list.addAll(database.kontakDao().getAll());
        kontakAdapter = new KontakAdapter(list, getApplicationContext());

        kontakAdapter.setDialog(new KontakAdapter.Dialog() {
            @Override
            public void onClick(int position) {
                final CharSequence[] dialogItem = {"Edit", "Hapus",};
                dialog = new AlertDialog.Builder(HomeActivity.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                Intent intent = new Intent(HomeActivity.this, TambahKontakActivity.class);
                                intent.putExtra("id", list.get(position).id);
                                startActivity(intent);
                                break;
                            case 1:
                                Kontak kontak = list.get(position);
                                database.kontakDao().delete(kontak);
                                Toast.makeText(HomeActivity.this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                                onStart();
                                break;
                        }
                    }
                });
                dialog.show();
            }
        });



        rvKontak.setHasFixedSize(true);
        rvKontak.setLayoutManager(new LinearLayoutManager(this));
        rvKontak.setAdapter(kontakAdapter);

        ExtendedFloatingActionButton fab = findViewById(R.id.fabTambahKontak);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, TambahKontakActivity.class));
            }
        });

        ibKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                Toast.makeText(HomeActivity.this, "Berhasil keluar...", Toast.LENGTH_SHORT).show();
                sharedPrefManager.saveIsLogin(false);
                finishAffinity();
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        list.clear();
        list.addAll(database.kontakDao().getAll());
        kontakAdapter.notifyDataSetChanged();
    }
}