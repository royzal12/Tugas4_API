package com.example.roy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.roy.model.DataAlgoritma;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    final String linkAPI = "https://ewinsutriandi.github.io/mockapi/algoritma_pengurutan.json";

    ArrayList<DataAlgoritma> dataAlgoritmas= new ArrayList();
    JSONObject dataAlgpritmaAPI;
    ListView listview;
    FloatingActionButton btnRefresh;
    ProgressBar loadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRefresh = findViewById(R.id.btnRefresh);
        btnRefresh.setOnClickListener(view -> getDataAlgoritma());
        loadingIndicator = findViewById(R.id.loadingIndicator);
        getDataAlgoritma();
    }

    void setupListview () {
        listview = findViewById(R.id.list_item);
        AdapterAlgoritma adapter = new AdapterAlgoritma(this, dataAlgoritmas);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(onItemClick);
    }

    private AdapterView.OnItemClickListener onItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            DataAlgoritma aSELECTED = dataAlgoritmas.get(position);
            Intent intent = new Intent(MainActivity.this, DetailAlgoritma.class);
            intent.putExtra("ALGORITMA_SELECTED", aSELECTED);
            startActivity(intent);
        }
    };

    void getDataAlgoritma() {
        dataAlgoritmas.clear();
        loadingIndicator.setVisibility(View.VISIBLE);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, linkAPI, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        dataAlgpritmaAPI = response;
                        refreshView();
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("error", error.toString());
                        loadingIndicator.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this, "Erorr: Gagal Mengambil Data", Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);
        setupListview();
    }

    void refreshView() {
        Iterator<String> key = dataAlgpritmaAPI.keys();
        while(key.hasNext()) {
            String nameAlgoritma = key.next();
            try {
                JSONObject data = dataAlgpritmaAPI.getJSONObject(nameAlgoritma);
                String gambar = data.getString("gambar");
                String deskripsi = data.getString("deskripsi");
                String baca_lebih_lanjut = data.getString("baca_lebih_lanjut");
                dataAlgoritmas.add(new DataAlgoritma(nameAlgoritma, gambar, deskripsi, baca_lebih_lanjut));
            }catch (JSONException e) {
                e.printStackTrace();
            }
        }
        loadingIndicator.setVisibility(View.GONE);
        setupListview();
    }
}