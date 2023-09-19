package com.example.tugas_akhir_vsga;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.tugas_akhir_vsga.database.DatabaseHelper;
import com.example.tugas_akhir_vsga.mahasiswa.Mahasiswa;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ReadMahasiswaActivity extends AppCompatActivity {

    private ListView listView;
    private List<Mahasiswa> mahasiswaList = new ArrayList<>();
    private ArrayAdapter<Mahasiswa> adapter;
    private static final int CONTEXT_MENU_OPTION1 = 1;
    private static final int CONTEXT_MENU_OPTION2 = 2;
    private static final int CONTEXT_MENU_OPTION3 = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_mahasiswa);

        getSupportActionBar().setTitle("Data Mahasiswa");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton btn_add =findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ReadMahasiswaActivity.this, AddMahasiswaActivity.class));
            }
        });

        //function load data
        listView = findViewById(R.id.listViewMahasiswa);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mahasiswaList);
        listView.setAdapter(adapter);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        mahasiswaList.addAll(databaseHelper.getAllMahasiswa());

        adapter.notifyDataSetChanged();
        registerForContextMenu(listView);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, CONTEXT_MENU_OPTION1, 0, "Lihat Data");
        menu.add(0, CONTEXT_MENU_OPTION2, 0, "Edit Data");
        menu.add(0, CONTEXT_MENU_OPTION3, 0, "Hapus Data");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int clickedItemPosition = info.position;
        Mahasiswa selectedMahasiswa = adapter.getItem(clickedItemPosition);
        int mahasiswaId = Integer.parseInt(selectedMahasiswa.getId_mahasiswa());

        switch (item.getItemId()) {
            case CONTEXT_MENU_OPTION1:
                Intent intent = new Intent(ReadMahasiswaActivity.this, PreviewMahasiswaActivity.class);
                intent.putExtra("nim", selectedMahasiswa.getNim());
                intent.putExtra("nama", selectedMahasiswa.getNama());
                intent.putExtra("tgl_lahir", selectedMahasiswa.getTgl_lahir());
                intent.putExtra("jenis_kelamin", selectedMahasiswa.getJenis_kelamin());
                intent.putExtra("alamat", selectedMahasiswa.getAlamat());
                startActivity(intent);
                return true;
            case CONTEXT_MENU_OPTION2:
                Intent intent2 = new Intent(ReadMahasiswaActivity.this, EditMahasiswaActivity.class);
                intent2.putExtra("id", selectedMahasiswa.getId_mahasiswa());
                intent2.putExtra("nim", selectedMahasiswa.getNim());
                intent2.putExtra("nama", selectedMahasiswa.getNama());
                intent2.putExtra("tgl_lahir", selectedMahasiswa.getTgl_lahir());
                intent2.putExtra("jenis_kelamin", selectedMahasiswa.getJenis_kelamin());
                intent2.putExtra("alamat", selectedMahasiswa.getAlamat());
                startActivity(intent2);
                return true;
            case CONTEXT_MENU_OPTION3:
                DatabaseHelper databaseHelper = new DatabaseHelper(this);
                int rowsAffected = databaseHelper.deleteMahasiswa(mahasiswaId);
                if (rowsAffected > 0){
                    Toast.makeText(this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                    recreate();
                }else{
                    Toast.makeText(this, "Data gagal Dihapus", Toast.LENGTH_SHORT).show();
                }
            default:
                return super.onContextItemSelected(item);
        }
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

