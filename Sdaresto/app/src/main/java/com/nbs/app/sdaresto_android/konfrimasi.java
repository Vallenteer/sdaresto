package com.nbs.app.sdaresto_android;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class konfrimasi extends AppCompatActivity {

    TextView daftar_pesanan ;
    Button btn_pesan;
    String list_pesanan;
    public ArrayList<String> pesanan = new ArrayList<String>();
    Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfrimasi);
        daftar_pesanan=  (TextView) findViewById(R.id.tv_Konfrimasi);
        btn_pesan= (Button) findViewById(R.id.btn_pesan);
        if(MainActivity.Menu_pesanan.size()<1)
            list_pesanan="Masih Belum ada Pesanan";
        else
        {
            list_pesanan="Meja " + String.valueOf(MainActivity.table_number);

            for(int i=0;i<MainActivity.Menu_pesanan.size();i++)
            {
                String Id_Menu = MainActivity.id_menu_pesanan.get(i);
                String Nama_Pesanan = MainActivity.Menu_pesanan.get(i);
                String Jumlah_Pesanan = MainActivity.Jumlah_pesanan.get(i);
                String Pesan_Khusus = MainActivity.pesanan_khusus.get(i);
                list_pesanan += "\n\nPesanan : "+Nama_Pesanan+"\n"+ //nama_pesanan
                        "Jumlah : "+Jumlah_Pesanan+"\n"+
                        "Pesanan Khusus : "+Pesan_Khusus+"\n"+
                       "\n -----------------------------------";
                pesanan.add("?id_menu="+Id_Menu+"&jumlah_pesanan="+Jumlah_Pesanan+"&pesanan_khusus="+Pesan_Khusus+"&no_meja="+String.valueOf(MainActivity.table_number));
            }
       }
        daftar_pesanan.setText(list_pesanan);

        btn_pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.Menu_pesanan.size()>=1)
                {
                    for(int i=0;i<pesanan.size();i++) {// pesan_task
                        new pesan_task().execute(pesanan.get(i).toString());
                    }

                    MainActivity.id_menu_pesanan.clear();
                    MainActivity.Menu_pesanan.clear();
                    MainActivity.Jumlah_pesanan.clear();
                    MainActivity.pesanan_khusus.clear();
                    finish();
                    finish();
                }
                else
                {
                    //float notif gk ada pesanan
                    Toast.makeText(getApplicationContext(),
                            "Pesanan tidak ada.",
                            Toast.LENGTH_LONG).show();
                }

            }
        });




    }
    public void showResult (String st) { //"Toast toast" is declared in the class
        try {
            toast.getView().isShown();     // true if visible
            toast.setText(st);
        } catch (Exception e) {         // invisible if exception
            toast = Toast.makeText(this, st, Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    private class pesan_task extends AsyncTask<String, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... params) {
            URL url;
            JSONObject json_conn_result = new JSONObject();
            String json_str = "";
            String order=params[0];

            try {
                url = new URL("http://sdaresto.cloudapp.net/order.php" + order);
                HttpURLConnection url_connection = (HttpURLConnection) url.openConnection();

                url_connection.setRequestMethod("GET");
                url_connection.setDoInput(true);
                url_connection.setDoOutput(true);

                //Di sini tambahin first_name, last_name, etc.
                //Pemisahnya pakai tanda &
                //Disarankan pake cara lain buat gabung2innya, Kelvin waktu itu kayaknya ada nemu
                //String daftar = pesanan   ;

                String POST ="";

                OutputStream out = url_connection.getOutputStream();
                out.write(POST.getBytes());

                InputStream in = url_connection.getInputStream();


                StringBuilder string_builder = new StringBuilder();


                int byte_read ;

                while((byte_read = in.read())!=-1){
                    string_builder.append((char)byte_read);
                }

                json_str= new String(string_builder);

            } catch (Exception e) {
                e.printStackTrace();
            }

            //create JSONObject from the string
            try
            {
                json_conn_result = new JSONObject(json_str);
            }
            catch (JSONException ex)
            {
                ex.printStackTrace();
            }
            return json_conn_result;
        }

        @Override
        protected void onPostExecute(JSONObject result) {


            //ambil pesan
            Integer success = 0;
            String message = "";
            String notif_result="";


            try {
                success = result.getInt("success");
                message = result.getString("failreason");
            }catch (JSONException e){e.printStackTrace();}
            if(success==1)
            {
                finish();
            }

            showResult(message);



        }

    }
}
