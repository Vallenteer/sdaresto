package com.nbs.app.sdaresto_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView list;
    String[] web={
            "Meja 1",
            "Meja 2",
            "Meja 3",
            "Meja 4",
            "Meja 5"
    };
    public static int table_number;
    public static List Menu_pesanan = new ArrayList();
    public static List Jumlah_pesanan = new ArrayList();
    public static List pesanan_khusus = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListViewHandlerTable adapter = new ListViewHandlerTable(MainActivity.this,web);
        list=(ListView) findViewById(R.id.lv_table);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                table_number=position;
                Intent intent = new Intent(MainActivity.this, Menu.class);
                startActivity(intent);

            }


        });

    }
}
