package com.example.tugas_akhir_vsga;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tugas_akhir_vsga.database.DatabaseHelper;

public class DashboardActivity extends AppCompatActivity {

    private Button readMahasiswaButton, infoButton, logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        getSupportActionBar().setTitle("Dashboard");

        TextView jumlahMhs = findViewById(R.id.txt_jumlahMhs);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        int count = databaseHelper.getMahasiswaCount();
        jumlahMhs.setText(String.valueOf(count));

        readMahasiswaButton = findViewById(R.id.btn_readMahasiswa);
        readMahasiswaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this, ReadMahasiswaActivity.class));
            }
        });

        infoButton = findViewById(R.id.btn_info);
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this, InformasiActivity.class));
            }
        });

        logoutButton = findViewById(R.id.btn_logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    @Override
    protected void onRestart() {
        finish();
        overridePendingTransition(0,0);
        startActivity(getIntent());
        overridePendingTransition(0,0);
        super.onRestart();
    }
}