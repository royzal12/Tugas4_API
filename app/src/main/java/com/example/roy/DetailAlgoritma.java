package com.example.roy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.roy.model.DataAlgoritma;

public class DetailAlgoritma extends AppCompatActivity {
    DataAlgoritma AlgoritmaSelected;
    ImageView imgProfile;
    TextView titleProfile, officialWeb, descProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_detail);

        Intent intent = getIntent();
        AlgoritmaSelected = (DataAlgoritma) intent.getSerializableExtra("ALGORITMA_SELECTED");
        inisialisasi();
    }
    void inisialisasi() {
        titleProfile = findViewById(R.id.titleProfile);
        imgProfile = findViewById(R.id.imgProfile);
        descProfile = findViewById(R.id.descProfile);
        officialWeb = findViewById(R.id.officialWeb);

        titleProfile.setText(AlgoritmaSelected.getNameAlgoritma());
        descProfile.setText(AlgoritmaSelected.getDeskripsi());
        officialWeb.setText(AlgoritmaSelected.getBaca_lebih_lanjut());

        Glide
                .with(this)
                .load(AlgoritmaSelected.getGambar())
                .placeholder(R.drawable.ic_refres_foreground)
                .into(imgProfile);
    }

    public void actionToLink(View v) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(AlgoritmaSelected.getBaca_lebih_lanjut()));
        startActivity(intent);
    }

}
