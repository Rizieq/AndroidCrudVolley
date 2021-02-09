package com.rizieq.androidcrudvolley;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.rizieq.androidcrudvolley.util.AppController;
import com.rizieq.androidcrudvolley.util.ServerApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class InsertData extends AppCompatActivity {

    EditText npm, nama, prodi, fakultas;
    Button btnBatal, btnSimpan;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);

        // mengambil data dari intent
        Intent data = getIntent();
        final int update = data.getIntExtra("update", 0);
        String intent_npm = data.getStringExtra("npm");
        String intent_prodi = data.getStringExtra("prodi");
        String intent_fakultas = data.getStringExtra("fakultas");
        String intent_nama = data.getStringExtra("nama");
        // mengakhiri mengambil data dari intent


        npm = findViewById(R.id.inp_npm);
        nama = findViewById(R.id.inp_nama);
        prodi = findViewById(R.id.inp_prodi);
        fakultas = findViewById(R.id.inp_fakultas);
        btnBatal = findViewById(R.id.btn_cancel);
        btnSimpan = findViewById(R.id.btn_simpan);
        pd = new ProgressDialog(InsertData.this);

        // kondisi update / insert
        if (update == 1)
        {
            btnSimpan.setText("Update Data");
            npm.setText(intent_npm);
            npm.setVisibility(View.GONE);
            nama.setText(intent_nama);
            fakultas.setText(intent_fakultas);
            prodi.setText(intent_prodi);


        }

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (update == 1)
                {
                    updateData();
                } else {
                    simpanData();
                }
            }
        });
        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(InsertData.this,MainActivity.class);
                startActivity(main);

            }
        });

    }
    private void updateData() {
        pd.setMessage("Update Data");
        pd.setCancelable(false);
        pd.show();

        StringRequest updateReq = new StringRequest(Request.Method.POST, ServerApi.URL_UPDATE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.cancel();
                try {
                    JSONObject res = new JSONObject(response);

                    Toast.makeText(InsertData.this, "pesan : " + res.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(InsertData.this,MainActivity.class));
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(InsertData.this, "Pesan : Gagal Insert Data", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("npm", npm.getText().toString());
                map.put("nama", nama.getText().toString());
                map.put("prodi", prodi.getText().toString());
                map.put("fakultas", fakultas.getText().toString());

                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(updateReq);
    }
    private void simpanData() {
        pd.setMessage("Menyimpan Data");
        pd.setCancelable(false);
        pd.show();

        StringRequest sendData = new StringRequest(Request.Method.POST, ServerApi.URL_INSERT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.cancel();
                try {
                    JSONObject res = new JSONObject(response);

                    Toast.makeText(InsertData.this, "pesan : " + res.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(InsertData.this,MainActivity.class));
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(InsertData.this, "Pesan : Gagal Insert Data", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("npm", npm.getText().toString());
                map.put("nama", nama.getText().toString());
                map.put("prodi", prodi.getText().toString());
                map.put("fakultas", fakultas.getText().toString());

                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(sendData);
    }
}
