package com.anshaysingh.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //0 -> white & 1 -> black
    int active_player = 0;

    boolean gameIsActive = true;

    //2 means unplayed
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winning_postions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};


    public void diveIn (View view) {

        ImageView counter = (ImageView) view;

        int tapped_counter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tapped_counter] == 2 && gameIsActive) {

            counter.setTranslationY(-1000f);

            gameState[tapped_counter] = active_player;
            if (active_player == 0) {
                counter.setImageResource(R.drawable.connect3whitepiece);
                counter.animate().translationYBy(1000f).rotation(360).setDuration(500);
                active_player = 1;
            } else {
                counter.setImageResource(R.drawable.connect3blackpiece);
                counter.animate().translationYBy(1000f).rotation(360).setDuration(500);
                active_player = 0;
            }
        }
        for(int[] winningposition : winning_postions) {
            if(gameState[winningposition[0]] == gameState[winningposition[1]] &&
                    gameState[winningposition[1]] == gameState[winningposition[2]] && gameState[winningposition[0]] != 2) {
                //Someone Won!
                gameIsActive = false;
                String winner = new String("Black");
                if(gameState[winningposition[0]] == 0) {
                    winner = "White";
                }
                TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                winnerMessage.setText(winner + " has Won!");
                LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                layout.setVisibility(View.VISIBLE);
            }
            else {
                boolean gameIsOver = true;
                for (int counterState : gameState) {
                    if(counterState == 2)
                        gameIsOver = false;
                }
                if(gameIsOver) {

                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                    winnerMessage.setText("Its a draw!");
                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);

                }
            }
        }

    }

    public void playAgain (View view) {
        gameIsActive = true;
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        active_player = 0;
        for(int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
