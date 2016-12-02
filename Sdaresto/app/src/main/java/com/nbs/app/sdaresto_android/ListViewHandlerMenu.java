package com.nbs.app.sdaresto_android;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListViewHandlerMenu extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] web;
    private final String[] harga;
    public ListViewHandlerMenu(Activity context, String[] web,String[] harga) {
        super(context, R.layout.list_view_handler_menu, web);

        this.context = context;
        this.web = web;
        this.harga=harga;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_view_handler_menu, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.tv_tulisan);
        txtTitle.setText(Html.fromHtml(web[position]) );
        TextView txtHarga = (TextView) rowView.findViewById(R.id.tv_harga);
        txtHarga.setText(Html.fromHtml(harga[position]) );

        

        return  rowView;
    }
}