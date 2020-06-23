package com.example.workwithjson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<Phone> adapter;
    private EditText nameText, priceText;
    private List<Phone> phones;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameText=findViewById(R.id.nameText);
        priceText=findViewById(R.id.priceText);

        phones=new ArrayList<>();
        listView=findViewById(R.id.list);
        adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, phones);
        listView.setAdapter(adapter);

    }
    public void addPhone(View view){
        String name=nameText.getText().toString();
        int price=Integer.parseInt(priceText.getText().toString());
        Phone phone=new Phone(name, price);
        phones.add(phone);
        adapter.notifyDataSetChanged();
    }

    public void save(View view){
        boolean result=JSONHelper.exportToJSON(this, phones);
        if (result){
            Toast.makeText(this, "Данные сохранены", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Не удалось сохранить данные", Toast.LENGTH_LONG).show();
        }
    }
    public void open(View view){
        phones=JSONHelper.importFromJSON(this);
        if (phones!=null) {
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, phones);
            listView.setAdapter(adapter);
            Toast.makeText(this, "Данные восстановлены", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Не удалось открыть данные", Toast.LENGTH_LONG).show();

            }
        }
    }

