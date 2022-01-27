package com.example.diceapp;

import java.util.Random;

public class Dice {
    public static int LARGEST_NUM = 6;
    public static int SMALLEST_NUM = 1;
    public int individualScore;

    private int mNumber = SMALLEST_NUM;
    private int mImageId;
    private Random mRandomGenerator;


    public Dice(int number) {
        setNumber(number);
        mRandomGenerator = new Random();
    }

    public int getNumber() {
        return mNumber;
    }

    public void setNumber(int number) {
        if (number >= SMALLEST_NUM && number <= LARGEST_NUM) {
            mNumber = number;
            switch (number) {
                case 1:
                    mImageId = R.drawable.dice_1;
                    break;
                case 2:
                    mImageId = R.drawable.dice_2;
                    break;
                case 3:
                    mImageId = R.drawable.dice_3;
                    break;
                case 4:
                    mImageId = R.drawable.dice_4;
                    break;
                case 5:
                    mImageId = R.drawable.dice_5;
                    break;
                case 6:
                    mImageId = R.drawable.dice_6;
                    break;
            }
        }
    }

    public int getImageId() {
        return mImageId;
    }

    public void addOne() {
        setNumber(mNumber + 1);
    }

    public void subtractOne() {
        setNumber(mNumber - 1);
    }

    public void roll() {
        setNumber(mRandomGenerator.nextInt(LARGEST_NUM) + 1);
    }

    public int assignScore() {
            if(getImageId() == R.drawable.dice_1){
                individualScore = 1;
            }
            else if(getImageId() == R.drawable.dice_2){
                individualScore = 2;
            }
            else if(getImageId() == R.drawable.dice_3){
                individualScore = 3;
            }
            else if(getImageId() == R.drawable.dice_4){
                individualScore = 4;
            }
            else if(getImageId() == R.drawable.dice_5){
                individualScore = 5;
            }
            else if(getImageId() == R.drawable.dice_6){
                individualScore = 6;
            }
            return individualScore;
        }
    }
