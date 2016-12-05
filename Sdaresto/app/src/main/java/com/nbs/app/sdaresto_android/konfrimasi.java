package com.nbs.app.sdaresto_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class konfrimasi extends AppCompatActivity {

    TextView daftar_pesanan ;
    String list_pesanan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfrimasi);
        daftar_pesanan=  (TextView) findViewById(R.id.tv_Konfrimasi);
        if(MainActivity.Menu_pesanan.size()<1)
            list_pesanan="Masih Belum ada Pesanan";
        else
        {
            list_pesanan="";
            for(int i=0;i<MainActivity.Menu_pesanan.size();i++)
            {

                String Nama_Pesanan = MainActivity.Menu_pesanan.get(i);
                String Jumlah_Pesanan = MainActivity.Jumlah_pesanan.get(i);
                String Pesan_Khusus = MainActivity.pesanan_khusus.get(i);
                list_pesanan += "\n\nPesanan : "+Nama_Pesanan+"\n"+
                        "Jumlah : "+Jumlah_Pesanan+"\n"+
                        "Pesanan Khusus : "+Pesan_Khusus+"\n"+
                       "\n -----------------------------------";
            }
            daftar_pesanan.setText(list_pesanan);
        }




    }
}
