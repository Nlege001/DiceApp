package com.example.diceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final int MAX_DICE = 3;
    public static final String win = "You Won!";
    public static final String lose = "You lost";
    public int score = 0;
    public boolean scoreBool;

    private int mVisibleDice;
    private Dice[] mDice;
    private ImageView[] mDiceImageViews;
    private Menu mMenu;
    private CountDownTimer mTimer;
    private Button calculate;
    private TextView scoreText;
    private TextView winOrLose;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Create an array of Dice
        mDice = new Dice[MAX_DICE];
        for (int i = 0; i < MAX_DICE; i++) {
            mDice[i] = new Dice(i + 1);
        }

        // Create an array of ImageViews
        mDiceImageViews = new ImageView[MAX_DICE];
        mDiceImageViews[0] = findViewById(R.id.dice1);
        mDiceImageViews[1] = findViewById(R.id.dice2);
        mDiceImageViews[2] = findViewById(R.id.dice3);

        // All dice are initially visible
        mVisibleDice = MAX_DICE;

        showDice();

        calculate = findViewById(R.id.button2);
        scoreText = findViewById(R.id.textView2);
        winOrLose = findViewById(R.id.textView3);
        calculate.setOnClickListener(v -> {
            assignScoreUI();
            scoreStatus();
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        mMenu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    private void showDice() {
        // Display only the number of dice visible
        for (int i = 0; i < mVisibleDice; i++) {
            Drawable diceDrawable = ContextCompat.getDrawable(this, mDice[i].getImageId());
            mDiceImageViews[i].setImageDrawable(diceDrawable);
            mDiceImageViews[i].setContentDescription(Integer.toString(mDice[i].getNumber()));
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        // Determine which menu option was chosen
        if (item.getItemId() == R.id.action_one) {
            changeDiceVisibility(1);
            showDice();
            return true;
        } else if (item.getItemId() == R.id.action_two) {
            changeDiceVisibility(2);
            showDice();
            return true;
        } else if (item.getItemId() == R.id.action_three) {
            changeDiceVisibility(3);
            showDice();
            return true;
        } else if (item.getItemId() == R.id.action_stop) {
            mTimer.cancel();
            item.setVisible(false);
            return true;
        } else if (item.getItemId() == R.id.action_roll) {
            rollDice();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void changeDiceVisibility(int numVisible) {
        mVisibleDice = numVisible;

        // Make dice visible
        for (int i = 0; i < numVisible; i++) {
            mDiceImageViews[i].setVisibility(View.VISIBLE);
        }

        // Hide remaining dice
        for (int i = numVisible; i < MAX_DICE; i++) {
            mDiceImageViews[i].setVisibility(View.GONE);
        }
    }

    private void rollDice() {
        mMenu.findItem(R.id.action_stop).setVisible(true);

        if (mTimer != null) {
            mTimer.cancel();
        }

        mTimer = new CountDownTimer(2000, 100) {
            public void onTick(long millisUntilFinished) {
                for (int i = 0; i < mVisibleDice; i++) {
                    mDice[i].roll();
                }
                showDice();
            }

            public void onFinish() {
                mMenu.findItem(R.id.action_stop).setVisible(false);
            }
        }.start();
    }

    private int calculateScore() {
        for (int i = 0; i < mVisibleDice; i++) {
            score += mDice[i].assignScore();
        }
        return score;


    }
    public void assignScoreUI(){
        String scoreString = String.valueOf(calculateScore());
        scoreText.setText(scoreString);
    }

    public boolean winOrLose(){
        if(mVisibleDice ==2){
            if(score == 7 || score == 11){
                scoreBool = true;
            }
            else if(score == 2 || score == 12){
                scoreBool=  false;
            }
            else{scoreBool = true;}
        }
        else if(mVisibleDice == 3){
            if(score % 7 == 0 || score % 11 == 0){
                scoreBool = true;
            }
            else if(score == 18 || score == 3){
                scoreBool = false;
            }
            else{scoreBool = true;}
        }
        return scoreBool;
    }
    public void scoreStatus(){
        if(winOrLose()){
            winOrLose.setText(win);
            score = 0;
        }
        else{
            winOrLose.setText(lose);
            score = 0;
        }
    }
}
