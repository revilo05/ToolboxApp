package com.escalegrade.toolbox;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class genero extends AppCompatActivity {

    private EditText nameInput;
    private Button predictButton;
    private ConstraintLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genero);

        nameInput = findViewById(R.id.editTextText);
        predictButton = findViewById(R.id.button);
        mainLayout = findViewById(R.id.main);

        predictButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameInput.getText().toString().trim();
                if (!name.isEmpty()) {
                    predictGender(name);
                } else {
                    Toast.makeText(genero.this, "Por favor, ingrese un nombre.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void predictGender(String name) {
        String url = "https://api.genderize.io/?name=" + name;
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String gender = response.getString("gender");
                            if ("male".equals(gender)) {
                                mainLayout.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
                            } else if ("female".equals(gender)) {
                                mainLayout.setBackgroundColor(getResources().getColor(R.color.holo_pink));
                            } else {
                                Toast.makeText(genero.this, "Género no determinado.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(genero.this, "Error al procesar la respuesta.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(genero.this, "Error en la solicitud.", Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(jsonObjectRequest);

        ImageView imageView2 = findViewById(R.id.imageView2);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(genero.this, "ImageView clicked!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(genero.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }
}
