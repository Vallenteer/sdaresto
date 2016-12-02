package com.nbs.app.sdaresto_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class Menu extends AppCompatActivity {
    ListView list;
    String[] web={
            "Meja 1",
            "Meja 2",
            "Meja 3",
            "Meja 4",
            "Meja 5"
    };
    String[] harga={
            "Meja 1",
            "Meja 2",
            "Meja 3",
            "Meja 4",
            "Meja 5"
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


            }


        });


    }
}
