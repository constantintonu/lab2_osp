package com.example.root.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button button;
    Button addButton;
    EditText prodName;
    EditText prodDesc;
    EditText prodPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        addButton = (Button) findViewById(R.id.addButton);
        prodName = (EditText) findViewById(R.id.editText);
        prodDesc = (EditText) findViewById(R.id.editText2);
        prodPrice = (EditText) findViewById(R.id.editText3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                //i.putExtra("someString", editText.getText().toString());
                startActivity(i);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                cv.put("name", prodName.getText().toString());
                cv.put("desc", prodDesc.getText().toString());
                cv.put("price", Integer.parseInt(prodPrice.getText().toString()));

                Uri uri = getContentResolver().insert(Uri.parse(ItemsProvider.tableUri), cv);
                Log.d("[ndk][lab2]","Result of adding new item: " + uri.toString());
            }
        });
    }
}
