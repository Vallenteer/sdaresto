package com.nbs.app.sdaresto_android;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListViewHandlerTable extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] web;
    public ListViewHandlerTable(Activity context, String[] web) {
        super(context, R.layout.list_view_handler_table, web);

        this.context = context;
        this.web = web;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_view_handler_table, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.tv_tulisan);

        txtTitle.setText(Html.fromHtml(web[position]) );

        return  rowView;
    }
}