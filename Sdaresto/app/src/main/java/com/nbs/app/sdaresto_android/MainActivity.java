package com.nbs.app.sdaresto_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

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
    public static ArrayList<String> id_menu_pesanan = new ArrayList<String>();
    public static ArrayList<String> Menu_pesanan = new ArrayList<String>();
    public static ArrayList<String> Jumlah_pesanan = new ArrayList<String>();
    public static ArrayList<String> pesanan_khusus = new ArrayList<String>();


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
                table_number=position+1;
                //menghilangkan cache
                id_menu_pesanan.clear();
                Menu_pesanan.clear();
                Jumlah_pesanan.clear();
                pesanan_khusus.clear();
                Intent intent = new Intent(MainActivity.this, Menu.class);
                startActivity(intent);

            }


        });

    }
}
