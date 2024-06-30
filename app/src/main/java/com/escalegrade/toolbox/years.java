package com.escalegrade.toolbox;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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

public class years extends AppCompatActivity {

    private EditText nameInput;
    private Button predictButton;
    private ConstraintLayout mainLayout;
    private TextView ageTextView;
    private ImageView ageImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_years);

        nameInput = findViewById(R.id.editTextText);
        predictButton = findViewById(R.id.button);
        mainLayout = findViewById(R.id.main);
        ageTextView = findViewById(R.id.textView);
        ageImageView = findViewById(R.id.imageView3);

        predictButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameInput.getText().toString().trim();
                if (!name.isEmpty()) {
                    predictAge(name);
                } else {
                    Toast.makeText(years.this, "Por favor, ingrese un nombre.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void predictAge(String name) {
        String url = "https://api.agify.io/?name=" + name;
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int age = response.getInt("age");
                            ageTextView.setText("Edad: " + age);

                            if (age < 18) {
                                mainLayout.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                                ageImageView.setImageResource(R.drawable.joven);
                                Toast.makeText(years.this, "Joven", Toast.LENGTH_SHORT).show();
                            } else if (age < 60) {
                                mainLayout.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
                                ageImageView.setImageResource(R.drawable.adulto);
                                Toast.makeText(years.this, "Adulto", Toast.LENGTH_SHORT).show();
                            } else {
                                mainLayout.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                                ageImageView.setImageResource(R.drawable.anciano);
                                Toast.makeText(years.this, "Anciano", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(years.this, "Error al procesar la respuesta.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(years.this, "Error en la solicitud.", Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(jsonObjectRequest);

        ImageView imageView2 = findViewById(R.id.imageView2);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(years.this, "ImageView clicked!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(years.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }
}
