package com.example.performit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> items;
    private ListView list;
    private Button button;
    private TextView tv1;
    public static final String s1="summary";
    public static final String s2="date";
    public static final String s3="priority";
    public static final String s4="status";
    private TextView sumtext,datext,pritext,statext;
    private String str1,str2,str3,str4;
    private ArrayAdapter<String> itemAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent1=getIntent();
        str1=intent1.getStringExtra(s1);
        str4=intent1.getStringExtra(s2);
        str2=intent1.getStringExtra(s3);
        str3=intent1.getStringExtra(s4);
        list=findViewById(R.id.list1);
        button=findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendetiled();
                Additem(v);
            }
        });
        items=new ArrayList<>();
        itemAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,items);
        list.setAdapter(itemAdapter);



    }
    public void opendetiled(){
        Intent intent =new Intent(this,detiled.class);
        startActivity(intent);
    }
    private void Additem(View v){
        EditText input=findViewById(R.id.editTextText1);
        String itemText=input.getText().toString();
        if(!(itemText).equals("")){
            itemAdapter.add(itemText);
            input.setText("");
        }
        else{
            Toast.makeText(getApplicationContext(),"Enter text",Toast.LENGTH_LONG).show();
        }
    }
}