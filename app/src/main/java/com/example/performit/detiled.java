package com.example.performit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class detiled extends AppCompatActivity {
    String[] item={"LOW","MEDIUM","HIGH"};
    private AutoCompleteTextView at;
    ArrayAdapter<String> adapteritems;
     private Button b2;
      private RadioGroup RG2;
      private RadioButton rb;
      private String items;
       private EditText et1;
      private View et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detiled);
        b2=findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openact();
            }

        });
        et1=findViewById(R.id.editTextText2);
        et2=findViewById(R.id.editTextDate3);
        RG2=findViewById(R.id.idRadioGroup2);
        at=findViewById(R.id.Autocompletetext);
        adapteritems=new ArrayAdapter<String>(this,R.layout.list_item,item);
        at.setAdapter(adapteritems);
        at.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                items=adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(),"item: "+item,Toast.LENGTH_LONG).show();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(detiled.this, MainActivity.class);
                String st1=et1.getText().toString();
                String st3=et2.toString();
                String st2=rb.getText().toString();
                i.putExtra(MainActivity.s1,st1);
                i.putExtra(MainActivity.s2,st3);
                i.putExtra(MainActivity.s3,st2);
                i.putExtra(MainActivity.s4,items);
                startActivity(i);

            }
        });
    }
    public void openact() {
        Intent intent =new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void checkbutton(View v){
        int radioId=RG2.getCheckedRadioButtonId();
        rb=findViewById(radioId);
    }
}