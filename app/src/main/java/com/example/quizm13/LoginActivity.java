package com.example.quizm13;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    private SharedPrefManager sharedPrefManager;

    private EditText etNama, etPassword;
    private Button btnLogin;
    private ProgressBar pbLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar_title_layout);

        setContentView(R.layout.activity_login);

        etNama = findViewById(R.id.etNama);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        pbLogin = findViewById(R.id.pbLogin);

        sharedPrefManager = new SharedPrefManager(this);

        login();

    }

    private void login() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = etNama.getText().toString();
                final String password = etPassword.getText().toString();

                pbLogin.setVisibility(View.VISIBLE);

                if (username.isEmpty() || password.isEmpty()) {
                    pbLogin.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, "username dan password harus diisi...!", Toast.LENGTH_SHORT).show();
                } else {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            String spUsername = sharedPrefManager.getUsername();
                            String spPassword = sharedPrefManager.getPassword();

                            if (username.equals(spUsername) && password.equals(spPassword)) {
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                Toast.makeText(LoginActivity.this, "Berhasil login...", Toast.LENGTH_SHORT).show();
                                sharedPrefManager.saveIsLogin(true);
                                finishAffinity();
                                startActivity(intent);
                            } else {
                                pbLogin.setVisibility(View.GONE);
                                Toast.makeText(LoginActivity.this, "username atau passwprd salah...!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, 3000);
                }
            }
        });
    }
}