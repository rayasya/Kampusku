package com.example.tugas_akhir_vsga.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tugas_akhir_vsga.mahasiswa.Mahasiswa;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super (context, "ujikom.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //create table users untuk login
        String createTableUsers = "CREATE TABLE users (id_user INTEGER PRIMARY KEY, username TEXT, password TEXT)";
        sqLiteDatabase.execSQL(createTableUsers);

        //create table mahasiswa untuk data mahasiswa
        String createTableMahasiswa = "CREATE TABLE mahasiswa (id_mahasiswa INTEGER PRIMARY KEY, nim TEXT, nama TEXT, tgl_lahir TEXT, jenis_kelamin TEXT, alamat TEXT)";
        sqLiteDatabase.execSQL(createTableMahasiswa);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS users");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS mahasiswa");
        onCreate(sqLiteDatabase);
    }

    //fungsi untuk login
    public long addUser (String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        return db.insert("users", null, values);
    }

    public boolean checkUser (String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"id_user"};
        String selection = "username = ? AND password = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query("users", columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count > 0;
    }

    //fungsi terkait data mahasiswa
    public long addMahasiswa(String nim, String nama, String tgl_lahir, String jenis_kelamin, String alamat){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nim", nim);
        values.put("nama", nama);
        values.put("tgl_lahir", tgl_lahir);
        values.put("jenis_kelamin", jenis_kelamin);
        values.put("alamat", alamat);

        return db.insert("mahasiswa", null, values);
    }

    public List<Mahasiswa> getAllMahasiswa(){
        List<Mahasiswa> mahasiswaList = new ArrayList<>();

        String selectQuery = "SELECT * FROM mahasiswa";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                Mahasiswa mahasiswa = new Mahasiswa();
                // Handle id column
                int idIndex = cursor.getColumnIndex("id_mahasiswa");
                if (idIndex >= 0) {
                    mahasiswa.setId_mahasiswa(cursor.getString(idIndex));
                }

                // Handle nim column
                int nimIndex = cursor.getColumnIndex("nim");
                if (nimIndex >= 0) {
                    mahasiswa.setNim(cursor.getString(nimIndex));
                }

                // Handle nama column
                int namaIndex = cursor.getColumnIndex("nama");
                if (namaIndex >= 0) {
                    mahasiswa.setNama(cursor.getString(namaIndex));
                }

                // Handle alamat column
                int alamatIndex = cursor.getColumnIndex("alamat");
                if (alamatIndex >= 0) {
                    mahasiswa.setAlamat(cursor.getString(alamatIndex));
                }

                // Handle tanggal_lahir column
                int tanggalLahirIndex = cursor.getColumnIndex("tgl_lahir");
                if (tanggalLahirIndex >= 0) {
                    mahasiswa.setTgl_lahir(cursor.getString(tanggalLahirIndex));
                }

                // Handle jenis_kelamin column
                int jenisKelaminIndex = cursor.getColumnIndex("jenis_kelamin");
                if (jenisKelaminIndex >= 0) {
                    mahasiswa.setJenis_kelamin(cursor.getString(jenisKelaminIndex));
                }

                mahasiswaList.add(mahasiswa);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return mahasiswaList;
    }

    public int updateMahasiswa (int id, String nimBaru, String namaBaru, String tanggalBaru, String genderBaru, String alamatBaru){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nim", nimBaru);
        values.put("nama", namaBaru);
        values.put("tgl_lahir", tanggalBaru);
        values.put("jenis_kelamin", genderBaru);
        values.put("alamat", alamatBaru);

        return db.update("mahasiswa", values, "id_mahasiswa = ?", new String[]{String.valueOf(id)});
    }

    public int deleteMahasiswa (int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("mahasiswa", "id_mahasiswa = ?", new String[]{String.valueOf(id)});
    }

    public int getMahasiswaCount(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM mahasiswa", null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        db.close();

        return count;
    }
}
