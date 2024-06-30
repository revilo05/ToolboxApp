package com.escalegrade.toolbox;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class clima extends AppCompatActivity {

    private TextView weatherSummary;
    private TextView temperature;
    private TextView humidity;
    private TextView windSpeed;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clima);

        weatherSummary = findViewById(R.id.weatherSummary);
        temperature = findViewById(R.id.temperature);
        humidity = findViewById(R.id.humidity);
        windSpeed = findViewById(R.id.windSpeed);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchWeatherData();
            }
        });

        ImageView imageView2 = findViewById(R.id.imageView2);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(clima.this, "ImageView clicked!", Toast.LENGTH_SHORT).show();
                // Aquí puedes agregar la lógica para navegar a otra actividad
            }
        });
    }

    private void fetchWeatherData() {
        String url = "https://www.meteosource.com/api/v1/free/point?place_id=santo-domingo&language=es&unit=metric&key=j7pntj6vhevorwpzb9tk9bl06gf5g84ek58iafb8";
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject current = response.getJSONObject("current");
                            String weather = current.getString("weather");
                            double temp = current.getDouble("temperature");
                            int hum = current.getInt("humidity");
                            double wind = current.getJSONObject("wind").getDouble("speed");

                            weatherSummary.setText("Clima: " + weather);
                            temperature.setText("Temperatura: " + temp + "°C");
                            humidity.setText("Humedad: " + hum + "%");
                            windSpeed.setText("Velocidad del viento: " + wind + " m/s");

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(clima.this, "Error al procesar la respuesta.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(clima.this, "Error en la solicitud.", Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(jsonObjectRequest);

        ImageView imageView2 = findViewById(R.id.imageView2);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(clima.this, "ImageView clicked!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(clima.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }
}
