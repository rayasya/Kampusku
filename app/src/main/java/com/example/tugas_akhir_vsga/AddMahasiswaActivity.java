package com.example.tugas_akhir_vsga;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.tugas_akhir_vsga.database.DatabaseHelper;

import java.util.Calendar;

public class AddMahasiswaActivity extends AppCompatActivity {

    private EditText tf_nim, tf_nama, tf_alamat, editText_tglLahir;
    private String jenis_kelamin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mahasiswa);

        getSupportActionBar().setTitle("Tambah Data Mahasiswa");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //input tanggal
        editText_tglLahir = findViewById(R.id.editText_tglLahir);
        editText_tglLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });

        //tambah data
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        Button simpan = findViewById(R.id.btn_simpan);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tf_nim = findViewById(R.id.txt_nim);
                tf_nama = findViewById(R.id.txt_nama);
                tf_alamat = findViewById(R.id.txt_alamat);

                String nim = tf_nim.getText().toString().trim();
                String nama = tf_nama.getText().toString().trim();
                String tgl_lahir = editText_tglLahir.getText().toString().trim();
                genderPilihan();
                String alamat = tf_alamat.getText().toString().trim();

                databaseHelper.addMahasiswa(nim, nama, tgl_lahir, jenis_kelamin, alamat);
                Toast.makeText(AddMahasiswaActivity.this, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                onBackPressed();
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

    private void showDatePicker(){
        final Calendar calendar = Calendar.getInstance();
        int tahun = calendar.get(Calendar.YEAR);
        int bulan = calendar.get(Calendar.MONTH);
        int hari = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        String selectedDate = day + "-"+ (month+1) + "-" + year;
                        editText_tglLahir.setText(selectedDate);
                    }
                }, tahun, bulan, hari
        );
        datePickerDialog.show();
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