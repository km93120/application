package e.sofian.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by sofian on 18/04/2018.
 */

public class Adaptter extends ArrayAdapter<String> {

    String adresses[] ;
    int loyers [];
    int apercus [];
    Context context ;
    public Adaptter(@NonNull Context context, String adr [] , int loy[] , int aprc[])
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
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item,parent,false);
            viewHolder.aprc = (ImageView) convertView.findViewById(R.id.imageView);
            viewHolder.adr = (TextView) convertView.findViewById(R.id.textView1);
            viewHolder.loy = (TextView) convertView.findViewById(R.id.textView2);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.aprc.setImageResource(apercus[position]);
        viewHolder.adr.setText(adresses[position]);
        viewHolder.loy.setText(loyers[position]);

        return convertView;

    }

    static class ViewHolder
    {
        ImageView aprc;
        TextView adr;
        TextView loy;

    }
}
