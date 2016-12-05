package com.nbs.app.sdaresto_android;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class konfrimasi extends AppCompatActivity {

    TextView daftar_pesanan ;
    Button btn_pesan;
    String list_pesanan;
    public String pesanan;
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
            list_pesanan="";
            pesanan="";
            for(int i=0;i<MainActivity.Menu_pesanan.size();i++)
            {
                String Id_Menu = MainActivity.id_menu_pesanan.get(i);
                String Nama_Pesanan = MainActivity.Menu_pesanan.get(i);
                String Jumlah_Pesanan = MainActivity.Jumlah_pesanan.get(i);
                String Pesan_Khusus = MainActivity.pesanan_khusus.get(i);
                list_pesanan += "\n\nPesanan : "+Nama_Pesanan+"\n"+
                        "Jumlah : "+Jumlah_Pesanan+"\n"+
                        "Pesanan Khusus : "+Pesan_Khusus+"\n"+
                       "\n -----------------------------------";
                pesanan+="$id_menu="+Id_Menu+"$jumlah_pesanan="+Jumlah_Pesanan+"$pesanan_khusus="+Pesan_Khusus+"$no_meja="+String.valueOf(MainActivity.table_number)+";";
            }
       }
        daftar_pesanan.setText(list_pesanan);

        btn_pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.Menu_pesanan.size()>=1)
                {
                    // pesan_task
                    new pesan_task().execute();

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

    public class pesan_task extends AsyncTask<Activity, Integer, JSONObject>
    {
        Activity activity;

        @Override
        protected JSONObject doInBackground(Activity... params)
        {
            URL url;
            String conn_string_result="";

            activity = params[0];

            try {
                url = new URL("http://sdaresto.cloudapp.net/order.php");
                HttpsURLConnection url_connection = (HttpsURLConnection) url.openConnection();

                url_connection.setRequestMethod("POST");
                url_connection.setDoInput(true);
                url_connection.setDoOutput(true);


                StringBuilder sb = new StringBuilder(pesanan);
                String order = sb.toString();

                String POST=order;

                OutputStream out = url_connection.getOutputStream();
                out.write(POST.getBytes());

                InputStream in = url_connection.getInputStream();


                StringBuilder string_builder = new StringBuilder();


                int byte_read;

                while((byte_read = in.read()) != -1){
                    string_builder.append((char)byte_read);
                }

                conn_string_result = new String(string_builder);

            } catch (Exception  e)
            {e.printStackTrace();}

            JSONObject connection_json=new JSONObject();

            try{
                connection_json= new JSONObject(conn_string_result);
            }catch (JSONException e){e.printStackTrace();
                Log.e("INVALID JSON GOT", conn_string_result);}
            Log.d("JSON STRING LOGIN_task", connection_json.toString());
            return connection_json;
        }

        @Override
        protected void onPostExecute(JSONObject result)
        {

            try {
                JSONObject json_object = result.getJSONObject("user_info");
            }
            catch (JSONException e) {e.printStackTrace();}

        }

    }

}
