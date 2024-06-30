package com.escalegrade.toolbox;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button butts = findViewById(R.id.button1);
        butts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menu.this, genero.class);
                startActivity(intent);
            }
        });

        Button butts2 = findViewById(R.id.button2);
        butts2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menu.this, years.class);
                startActivity(intent);
            }
        });

        Button butts3 = findViewById(R.id.button3);
        butts3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menu.this, universidades.class);
                startActivity(intent);
            }
        });

        Button butts4 = findViewById(R.id.button4);
        butts4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menu.this, clima.class);
                startActivity(intent);
            }
        });

        ImageView imageView = findViewById(R.id.imageView4);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(menu.this, "ImageView clicked!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(menu.this, zoodom.class);
                startActivity(intent);

            }
        });

        ImageView imageView2 = findViewById(R.id.imageView2);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(menu.this, "ImageView clicked!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(menu.this, MainActivity.class);
                startActivity(intent);

            }
        });

        Button butts5 = findViewById(R.id.but5);
        butts5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menu.this, aboutme.class);
                startActivity(intent);
            }
        });
    }
}