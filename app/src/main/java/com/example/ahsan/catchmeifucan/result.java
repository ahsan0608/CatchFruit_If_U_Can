package com.example.ahsan.catchmeifucan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by ahsan on 8/21/2017.
 */

public class result extends Activity {

    SharedPreferences settings;
    String printFin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView scoreLabel = (TextView) findViewById(R.id.score);
        TextView highScoreLabel = (TextView) findViewById(R.id.highScore);
        TextView congrates = (TextView) findViewById(R.id.congrates);
        Button bTryAgain = (Button) findViewById(R.id.tryAgain);

        congrates.setVisibility(TextView.INVISIBLE);
        Intent intent = getIntent();
        int score = intent.getIntExtra("SCORE", 0);
        printFin = String.valueOf(score);
        scoreLabel.setText(printFin);

        //int s = Integer.parseInt(printFin);


        //call sharedPreference that i wanna save same data so i need a private room which i call "GAME_DATA"
        settings =  getApplicationContext().getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        //i put may data named "HIGH_SCORE" in the room
        int highScore = settings.getInt("HIGH_SORE",0);


        if (score > highScore){
            congrates.setVisibility(TextView.VISIBLE);
            highScoreLabel.setText("High Score :"+printFin);
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("HIGH_SORE",score);
            editor.commit();
        }else {
            highScoreLabel.setText("High Score : "+highScore);
        }

        bTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }
}
