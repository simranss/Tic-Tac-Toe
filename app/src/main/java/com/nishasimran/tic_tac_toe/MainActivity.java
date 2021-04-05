package com.nishasimran.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Animation fadeIn;

    // 0: x & 1: o
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    boolean gameActive = true;

    GridLayout gridLayout;
    TextView resultTextView;
    Button playAgainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridLayout = findViewById(R.id.grid);
        resultTextView = findViewById(R.id.result_text);
        playAgainButton = findViewById(R.id.play_again_button);

        resultTextView.setVisibility(View.GONE);
        playAgainButton.setVisibility(View.GONE);

        playAgainButton.setOnClickListener(v -> {
            gameActive = true;
            playAgainButton.setVisibility(View.GONE);
            resultTextView.setVisibility(View.GONE);

            int childCount = gridLayout.getChildCount();
            for (int i = 0; i < childCount; i++) {
                ImageView piece = (ImageView) gridLayout.getChildAt(i);
                piece.setImageDrawable(null);
            }

            activePlayer = 0;
            gameState = new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2};
            gameActive = true;
        });
    }

    public void dropIn(View view) {
        ImageView piece = (ImageView) view;

        int tappedPiece = Integer.parseInt(piece.getTag().toString());

        if (gameState[tappedPiece] == 2 && gameActive) {
            gameState[tappedPiece] = activePlayer;

            if (activePlayer == 0) {
                piece.setImageResource(R.drawable.x);
                activePlayer = 1;
            } else if (activePlayer == 1) {
                piece.setImageResource(R.drawable.o);
                activePlayer = 0;
            }
            fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
            piece.startAnimation(fadeIn);

            String TAG = MainActivity.this.getString(R.string.main_activity);
            for (int[] winningPosition : winningPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    // someone has won!
                    Log.d(TAG, MainActivity.this.getString(R.string.resultText, (gameState[winningPosition[0]] + 1)));
                    gameActive = false;
                    resultTextView.setVisibility(View.VISIBLE);
                    playAgainButton.setVisibility(View.VISIBLE);
                    resultTextView.setText(MainActivity.this.getString(R.string.resultText, (gameState[winningPosition[0]] + 1)));
                    return;
                }
            }

            boolean draw = false;
            for (int value : gameState) {
                if (value == 2) {
                    draw = false;
                    break;
                } else {
                    draw = true;
                }
            }
            if (draw) {
                Log.d(TAG, MainActivity.this.getString(R.string.resultDraw));
                gameActive = false;
                resultTextView.setVisibility(View.VISIBLE);
                playAgainButton.setVisibility(View.VISIBLE);
                resultTextView.setText(MainActivity.this.getString(R.string.resultDraw));
            }
        }
    }
}