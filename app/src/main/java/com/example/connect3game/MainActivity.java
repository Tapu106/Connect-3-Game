package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
//    0 yellow and red 1 empty 2
    int playerColor = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive = true;

    public  void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameActive) {
            gameState[tappedCounter] = playerColor;
            counter.setTranslationY(-1500);

            if (playerColor == 0) {
                counter.setImageResource(R.drawable.yellow);
                playerColor = 1;
            } else {
                counter.setImageResource(R.drawable.red);

                playerColor = 0;
            }
            counter.animate().translationYBy(1500).setDuration(300);
            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {

                    gameActive = false;
                    String winner = "";

                    if (playerColor == 1) {
                        winner = "yellow";
                    } else {
                        winner = "red";
                    }


                    Toast.makeText(this, winner + " has won", Toast.LENGTH_SHORT).show();
                    TextView winnerView = (TextView) findViewById(R.id.winnerTextView);
                    Button playAgain = (Button) findViewById(R.id.playAgainButton);
                    winnerView.setText(winner + " has WON!!");
                    playAgain.setVisibility(view.VISIBLE);
                    winnerView.setVisibility(view.VISIBLE);
                }
            }

        }
    }

    public void playAgain(View view){
        TextView winnerView = (TextView) findViewById(R.id.winnerTextView);
        Button playAgain = (Button) findViewById(R.id.playAgainButton);
        playAgain.setVisibility(view.INVISIBLE);
        winnerView.setVisibility(view.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i =0; i<gridLayout.getChildCount();i++){
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
         playerColor = 0;

        gameActive = true;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
