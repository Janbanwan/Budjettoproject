package com.example.j.budjetto;

import java.text.SimpleDateFormat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public EditText title;
    public EditText amount;
    public EditText balanssi;
    public EditText resultti;
    public Spinner taip;
    public Button calculate;
    public Button display;
    String item;
    double tulos;
    DatabaseHelper myDb;
    double totalAmount;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title = (EditText) findViewById(R.id.editTitle);
        taip = (Spinner) findViewById(R.id.spinnerTaip);
        amount = (EditText) findViewById(R.id.editAmount);
        calculate = (Button) findViewById(R.id.buttonCalculate);
        display = (Button) findViewById(R.id.buttonDisplay);
        balanssi = (EditText) findViewById(R.id.editBalance);
        resultti = (EditText) findViewById(R.id.editResult);
        myDb = new DatabaseHelper(this);

        changeActivities();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.valinnat, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        taip.setAdapter(adapter);

        taip.setOnItemSelectedListener(this);

        totalAmount = myDb.getData();
        resultti.setText("0");
        resultti.setText(totalAmount+"");
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
        item = parent.getItemAtPosition(pos).toString();

        if(item.equals("Income")){
            calculate.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    double vali = Double.parseDouble(amount.getText().toString());
                    tulos = Double.parseDouble(resultti.getText().toString()) + vali;
                    resultti.setText(tulos + "");
                    myDb.insertData(item,vali,getDate());
                }
            });
        }
        else{
            calculate.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    double vali = Double.parseDouble(amount.getText().toString());
                    tulos = Double.parseDouble(resultti.getText().toString()) - vali;
                    resultti.setText(tulos + "");
                    myDb.insertData(item,vali*-1,getDate());
                }
            });
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private String getDate(){
        Date nyt = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd-MM-yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(nyt);

    }

    public void changeActivities(){
        display.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent tent = new Intent(MainActivity.this,DisplayActivity.class);
                startActivity(tent);
            }
        });
    }

}
