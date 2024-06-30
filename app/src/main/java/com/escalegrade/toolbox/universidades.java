package com.escalegrade.toolbox;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class universidades extends AppCompatActivity {

    private EditText countryInput;
    private Button searchButton;
    private LinearLayout universitiesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universidades);

        countryInput = findViewById(R.id.editTextText);
        searchButton = findViewById(R.id.button);
        universitiesList = findViewById(R.id.universitiesList);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String country = countryInput.getText().toString().trim();
                if (!country.isEmpty()) {
                    searchUniversities(country);
                } else {
                    Toast.makeText(universidades.this, "Por favor, ingrese el nombre de un país.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void searchUniversities(String country) {
        String url = "http://universities.hipolabs.com/search?country=" + country;
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        universitiesList.removeAllViews();  // Limpiar la lista antes de agregar nuevos resultados
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject university = response.getJSONObject(i);
                                String name = university.getString("name");
                                String domain = university.getString("domains").replace("[\"", "").replace("\"]", "");
                                String webPage = university.getString("web_pages").replace("[\"", "").replace("\"]", "");

                                TextView universityName = new TextView(universidades.this);
                                universityName.setText("Nombre: " + name);
                                TextView universityDomain = new TextView(universidades.this);
                                universityDomain.setText("Dominio: " + domain);
                                TextView universityWebPage = new TextView(universidades.this);
                                universityWebPage.setText("Página Web: " + webPage);

                                universitiesList.addView(universityName);
                                universitiesList.addView(universityDomain);
                                universitiesList.addView(universityWebPage);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(universidades.this, "Error al procesar la respuesta.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(universidades.this, "Error en la solicitud.", Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(jsonArrayRequest);

        ImageView imageView2 = findViewById(R.id.imageView2);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(universidades.this, "ImageView clicked!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(universidades.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }
}
