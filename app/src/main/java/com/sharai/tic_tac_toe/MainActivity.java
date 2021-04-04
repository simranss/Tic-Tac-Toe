package com.sharai.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Animation fadeIn;

    // 0: x & 1: o
    int activePlayer = 0;

    GridLayout gridLayout;
    Button playAgainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridLayout = findViewById(R.id.grid);
        playAgainButton = findViewById(R.id.play_again_button);
    }

    public void dropIn(View view) {
        ImageView piece = (ImageView) view;
        if (activePlayer == 0) {
            piece.setImageResource(R.drawable.x);
            activePlayer = 1;
        } else if (activePlayer == 1) {
            piece.setImageResource(R.drawable.o);
            activePlayer = 0;
        }
        fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        piece.startAnimation(fadeIn);
    }
}