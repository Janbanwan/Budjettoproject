package com.example.j.budjetto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    public ListView lista;
    public ArrayList jeejee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        lista = (ListView) findViewById(R.id.lista);
        myDb = new DatabaseHelper(this);
        jeejee = myDb.getObjectData();


        ListAdapter janneAdapteri = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, jeejee);
        lista.setAdapter(janneAdapteri);

    }
}
