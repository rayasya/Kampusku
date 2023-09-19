package com.example.tugas_akhir_vsga;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class PreviewMahasiswaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_mahasiswa);

        getSupportActionBar().setTitle("Preview Data Mahasiswa");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String nim = intent.getStringExtra("nim");
        String nama = intent.getStringExtra("nama");
        String tgl_lahir = intent.getStringExtra("tgl_lahir");
        String jenis_kelamin = intent.getStringExtra("jenis_kelamin");
        String alamat = intent.getStringExtra("alamat");

        EditText txt_nim = findViewById(R.id.txt_nim);
        EditText txt_nama = findViewById(R.id.txt_nama);
        EditText txt_tglLahir = findViewById(R.id.editText_tglLahir);
        EditText txt_alamat = findViewById(R.id.txt_alamat);
        txt_nim.setText(nim);
        txt_nama.setText(nama);
        txt_tglLahir.setText(tgl_lahir);
        txt_alamat.setText(alamat);

        //set radio group
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        RadioButton radioButtonLaki = findViewById(R.id.radioButtonLaki);
        RadioButton radioButtonPerempuan = findViewById(R.id.radioButtonPerempuan);
        if (jenis_kelamin.equals("Perempuan")){
            radioButtonPerempuan.setChecked(true);
        }else{
            radioButtonLaki.setChecked(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}