package com.example.roy.model;

import java.io.Serializable;

public class DataAlgoritma implements Serializable {
    String nameAlgoritma, gambar, deskripsi, baca_lebih_lanjut ;

    public DataAlgoritma(String nameAlgoritma, String gambar, String deskripsi, String baca_lebih_lanjut) {
        this.nameAlgoritma = nameAlgoritma;
        this.gambar = gambar;
        this.deskripsi = deskripsi;
        this.baca_lebih_lanjut = baca_lebih_lanjut;
    }

    public String getNameAlgoritma() {
        return nameAlgoritma;
    }

    public String getGambar() {
        return gambar;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getBaca_lebih_lanjut() {
        return baca_lebih_lanjut;
    }
}
