package com.example.tugas_akhir_vsga;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tugas_akhir_vsga.database.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button buttonRegister = findViewById(R.id.btn_register);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText txt_username = findViewById(R.id.txt_username);
                EditText txt_password = findViewById(R.id.txt_password);

                String username = txt_username.getText().toString().trim();
                String password = txt_password.getText().toString().trim();

                databaseHelper.addUser(username, password);
                Toast.makeText(RegisterActivity.this, "Data berhasil ditambahkan, Silahkan login", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}