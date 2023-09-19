package com.example.tugas_akhir_vsga;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.tugas_akhir_vsga.database.DatabaseHelper;

public class EditMahasiswaActivity extends AppCompatActivity {

    private String jenis_kelamin, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_mahasiswa);

        getSupportActionBar().setTitle("Update Data Mahasiswa");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
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

        //btn edit action
        Button btn_update = findViewById(R.id.btn_update);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nimBaru = txt_nim.getText().toString().trim();
                String namaBaru = txt_nama.getText().toString().trim();
                String tanggalBaru = txt_tglLahir.getText().toString().trim();
                String alamatBaru = txt_alamat.getText().toString().trim();
                genderPilihan();

                int rowsAffected = databaseHelper.updateMahasiswa(Integer.parseInt(id), nimBaru, namaBaru, tanggalBaru, jenis_kelamin, alamatBaru);
                if (rowsAffected > 0){
                    Toast.makeText(EditMahasiswaActivity.this, "Data berhasil diubah", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                    finish();
                }else{
                    Toast.makeText(EditMahasiswaActivity.this, "Data gagal diubah", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                    finish();
                }
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

    void genderPilihan(){
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        int selectedId = radioGroup.getCheckedRadioButtonId();
        if (selectedId == -1){
            Toast.makeText(this, "Pilih gender terlebih dahulu", Toast.LENGTH_SHORT).show();
        }else{
            RadioButton radioButton =findViewById(selectedId);
            jenis_kelamin = radioButton.getText().toString();
        }
    }
}