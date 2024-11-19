package com.example.myapplicationofri;

import static android.app.ProgressDialog.show;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        SharedPreferences sharedPreferences = getSharedPreferences("TestApp", MODE_PRIVATE);
        TextView tv  = findViewById(R.id.tvDisplay);
        tv.setText(sharedPreferences.getString("userinput", "no input yet"));

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("are u sure?")
                        .setMessage("just doing an exmaple")
                        .setPositiveButton("OK",null)
                        .setNegativeButton("i regret", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                Toast.makeText(MainActivity.this, "not listening", Toast.LENGTH_LONG).show();
                            }
                        })
                        .show();

                EditText et = findViewById(R.id.etName);
                String name = et.getText().toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("userinput", name);
                editor.apply();
            }
        });
    }
}