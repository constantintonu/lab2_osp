package com.example.root.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    TextView textView;
    ListView lv;
    List<MyMenuItem> items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = (TextView) findViewById(R.id.textView);
        textView.setText("Hello");
        lv = (ListView) findViewById(R.id.listView);

        items = new ArrayList<MyMenuItem>();
        items.add(new MyMenuItem("Coffee", 6, "Simple black coffee"));
        items.add(new MyMenuItem("Caffe latte", 7, "Coffee with milk"));
        items.add(new MyMenuItem("Espresso", 7, "Simple espresso"));
        items.add(new MyMenuItem("Caffe macchiatto", 8, "Espresso with foamed milk"));

        Cursor cursor = getContentResolver().query(Uri.parse(ItemsProvider.tableUri), null, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    String desc = cursor.getString(cursor.getColumnIndex("desc"));
                    String price = cursor.getString(cursor.getColumnIndex("price"));

                    items.add(new MyMenuItem(name, Float.parseFloat(price), desc));
                } while (cursor.moveToNext());
            }
        }

        MenuAdapter adapter = new MenuAdapter(this, items);
        lv.setAdapter(adapter);
    }
}
