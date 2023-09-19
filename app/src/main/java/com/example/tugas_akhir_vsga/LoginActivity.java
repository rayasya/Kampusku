package com.example.tugas_akhir_vsga;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tugas_akhir_vsga.database.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEdittext, passwordEdittext;
    private Button loginButton;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        //inisialisasi edittext dan button
        usernameEdittext = findViewById(R.id.txt_username);
        passwordEdittext = findViewById(R.id.txt_password);
        loginButton = findViewById(R.id.btn_login);

        databaseHelper = new DatabaseHelper(this);

        TextView btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEdittext.getText().toString().trim();
                String password = passwordEdittext.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Username dan Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else{
                    boolean loginSuccess = databaseHelper.checkUser(username, password);
                    if (loginSuccess){
                        Toast.makeText(LoginActivity.this, "Berhasil Login", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this, "Gagal Login, data tidak ditemukan", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}