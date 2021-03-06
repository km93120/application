package com.example.khafi.myapplications;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;



public class Adaptter extends ArrayAdapter<String> {

    String adresses[] ;
    int loyers [];
    int apercus [];
    Context context;

    //public Adaptter(@NonNull Context context, String adr [] , int loy [] , int aprc[])
    public Adaptter(@NonNull Context context, String adr [] , int loy [] , int aprc[])
    {
        super(context, R.layout.listview_item);
        this.adresses = adr;
        this.loyers = loy;
        this.apercus = aprc;
        this.context = context;
    }
    @Override
    public int getCount()
    {
        return adresses.length;
    }

    public View getView(int position , View convertView, ViewGroup parent)
    {
        ViewHolder viewHolder = new ViewHolder();

        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item,parent,false);
            viewHolder.aprc = convertView.findViewById(R.id.imageView);
            viewHolder.adr  = convertView.findViewById(R.id.textView1);
            viewHolder.loy  = convertView.findViewById(R.id.textView2);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();

        }


        //  java.lang.NullPointerException: Attempt to read from field 'android.widget.ImageView com.example.khafi.myapplication.Adaptter$ViewHolder.aprc' on a null object reference
        //        at com.example.khafi.myapplication.Adaptter.getView(Adaptter.java:58)
        viewHolder.adr.setText(adresses[position]);
        viewHolder.loy.setText(" " +loyers[position]);
        viewHolder.aprc.setImageResource(apercus[position]);


        return convertView;

    }

    static class ViewHolder
    {
        ImageView aprc;
        TextView adr;
        TextView loy;

    }
}
