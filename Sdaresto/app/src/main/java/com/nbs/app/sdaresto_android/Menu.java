package com.nbs.app.sdaresto_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class Menu extends AppCompatActivity {
    ListView list;
    public static int id_menu;
    public static String nama_menu;
    String[] web={
            "Sushi Salmon",
            "Sushi Tuna",
            "Ayam Goreng",
            "Nasi Goreng",
            "Es Teh"
    };
    String[] harga={
            "11000",
            "11000",
            "12000",
            "14000",
            "2000"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ListViewHandlerMenu adapter = new ListViewHandlerMenu(Menu.this,web,harga);
        list=(ListView) findViewById(R.id.lv_Menu);
        list.setItemsCanFocus(true);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                id_menu=position;
                nama_menu=web[position];
                Intent intent= new Intent(Menu.this,data_pesanan.class);
                startActivity(intent);

            }


        });


    }

    public void konfrimasiPesanan(View v)
    {
        Intent intent= new Intent(Menu.this,konfrimasi.class);
        startActivity(intent);
    }
}
