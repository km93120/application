package e.sofian.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class LocationActivity extends AppCompatActivity {

    Intent intent = getIntent();

    int    loyers  [] = intent.getIntArrayExtra("tableauLoyers");
    String adresses[] = intent.getStringArrayExtra("tableauAdresses");
    int    apercus [] = intent.getIntArrayExtra("tableauApercus");




    ListView listView;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        listView = (ListView) findViewById(R.id.listview);
        Adaptter adaptter = new Adaptter(LocationActivity.this,adresses,loyers,apercus);
        listView.setAdapter(adaptter);

    }

}
