package e.sofian.myapplication;

import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;




public class MainActivity extends Activity {

    Button Agence;
    Button Louer;
    public Button Vendre;
    public Button Acheter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Agence = findViewById(R.id.button);
        Agence.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(
                        MainActivity.this,
                        MapsActivity.class
                );

                startActivity(intent);

            }

        });
        /*Louer = findViewById(R.id.button2);
        Louer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
               Intent intent = new Intent(
                    MainActivity.this,
                        .class
                );

                startActivity(intent);

            }

        });*/
    }
}
