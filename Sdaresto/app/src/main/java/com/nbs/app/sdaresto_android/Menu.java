package com.nbs.app.sdaresto_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class Menu extends AppCompatActivity {
    TextView table_num;
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
        table_num= (TextView) findViewById(R.id.TitleMenu);
        table_num.setText("Meja " +String.valueOf(MainActivity.table_number)+"\n Menu :");
        ListViewHandlerMenu adapter = new ListViewHandlerMenu(Menu.this,web,harga);
        list=(ListView) findViewById(R.id.lv_Menu);
        list.setItemsCanFocus(true);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                id_menu=position+1;
                nama_menu=web[position];
                Intent intent= new Intent(Menu.this,data_pesanan.class);
                startActivity(intent);

            }


        });
        //a


    }

    public void konfrimasiPesanan(View v)
    {
        Intent intent= new Intent(Menu.this,konfrimasi.class);
        startActivity(intent);
    }
}
