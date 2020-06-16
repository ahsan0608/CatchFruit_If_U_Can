package com.example.ahsan.catchmeifucan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

/**
 * Created by ahsan on 8/24/2017.
 */

public class lol extends Activity {

    String printFin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lol_layout);

        TextView lolText = (TextView) findViewById(R.id.lolScore);

        Intent intent = getIntent();
        int score = intent.getIntExtra("SCORE", 0);
        printFin = String.valueOf(score);
        lolText.setText(printFin);


/*        Intent i = getIntent();
        int a = i.getIntExtra("SCORE",0);
        lolText.setText(a);*/

/*        Intent i= getIntent();
        i.getStringExtra("name");*/

/*        Bundle extras = getIntent().getExtras();
        int score = extras.getInt("SCORE");
        lolText.setText(score);*/
    }
}
