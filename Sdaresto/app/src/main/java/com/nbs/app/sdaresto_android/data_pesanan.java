package com.nbs.app.sdaresto_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class data_pesanan extends AppCompatActivity {

    public TextView tv_nama_menu;
    public Button btn_pesan;
    public EditText et_jumlah_pesanan;
    public EditText et_pesanan_khusus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pesanan);
        tv_nama_menu = (TextView) findViewById(R.id.menu_pesanan);
        btn_pesan = (Button) findViewById(R.id.btn_inputpesan);
        et_jumlah_pesanan = (EditText) findViewById(R.id.jumlah_pesanan);
        et_pesanan_khusus = (EditText) findViewById(R.id.pesanankhusus);
        tv_nama_menu.setText(Menu.nama_menu);

        btn_pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // masukin ke list tadi
                if(et_jumlah_pesanan.getText().toString().toString().equals("")) {
                    MainActivity.Jumlah_pesanan.add("1");
                    Log.d("hahahahah","Jumlah Pesanan 0");
                }
                else{
                    MainActivity.Jumlah_pesanan.add(et_jumlah_pesanan.getText().toString());
                    Log.d("Jumlah Pesanan",et_jumlah_pesanan.getText().toString());
                }
                if(et_pesanan_khusus.getText().toString().equals(""))
                {
                    MainActivity.pesanan_khusus.add("-");
                    Log.d("pesanan khusus","kosong");
                }
                else
                {
                    MainActivity.pesanan_khusus.add(et_pesanan_khusus.getText().toString());
                    Log.d("Pesanan Khusus",et_pesanan_khusus.getText().toString());
                }
                MainActivity.Menu_pesanan.add(Menu.nama_menu.toString());
                MainActivity.id_menu_pesanan.add(String.valueOf(Menu.id_menu));
                finish();

            }
        });
    }
}
