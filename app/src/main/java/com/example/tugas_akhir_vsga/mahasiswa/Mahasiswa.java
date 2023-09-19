package com.example.tugas_akhir_vsga.mahasiswa;

public class Mahasiswa {
    private String id_mahasiswa, nim, nama, alamat, tgl_lahir, jenis_kelamin;

    public Mahasiswa(){

    }

    public Mahasiswa(String id_mahasiswa, String nim, String nama, String tgl_lahir, String jenis_kelamin, String alamat){
        this.id_mahasiswa = id_mahasiswa;
        this.nim = nim;
        this.nama = nama;
        this.tgl_lahir = tgl_lahir;
        this.jenis_kelamin = jenis_kelamin;
        this.alamat = alamat;
    }

    public String getId_mahasiswa() {
        return id_mahasiswa;
    }

    public void setId_mahasiswa(String id_mahasiswa) {
        this.id_mahasiswa = id_mahasiswa;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTgl_lahir() {
        return tgl_lahir;
    }

    public void setTgl_lahir(String tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String toString(){
        return nama;
    }
}
