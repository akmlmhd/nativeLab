package com.example.mindsharpener;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView questionTextView, questionTextView6, resultTextView, pointTextView;
    private RadioGroup levelRadioGroup;
    private RadioButton i3RadioButton, i5RadioButton, i7RadioButton;
    private EditText answerEditText;
    private Button checkButton;

    private int currentLevel;
    private int points = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        questionTextView = findViewById(R.id.Textview4);
        questionTextView6 = findViewById(R.id.Textview6);
        resultTextView = findViewById(R.id.Textview8);
        pointTextView = findViewById(R.id.Textview7);
        levelRadioGroup = findViewById(R.id.radio_group_id);
        i3RadioButton = findViewById(R.id.radio_button_1);
        i5RadioButton = findViewById(R.id.radio_button_2);
        i7RadioButton = findViewById(R.id.radio_button_3);
        answerEditText = findViewById(R.id.edittext);
        checkButton = findViewById(R.id.button);

        // Set a listener for the check button
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MainActivity", "Check button clicked");
                checkAnswer();
            }
        });

        // Set initial question
        setQuestion();
    }

    // Method to generate a single random number based on the specified level
    private int generateRandomNumber(int level) {
        Random random = new Random();
        int maxDigits = level / 2 + 1;
        return random.nextInt((int) Math.pow(10, maxDigits));
    }

    // Method to set the question based on the selected level
    // Method to set the question based on the selected level
    private void setQuestion() {
        int levelId = levelRadioGroup.getCheckedRadioButtonId();
        if (levelId == i3RadioButton.getId()) {
            currentLevel = 3;
        } else if (levelId == i5RadioButton.getId()) {
            currentLevel = 5;
        } else if (levelId == i7RadioButton.getId()) {
            currentLevel = 7;
        }

        // Generate a single random number
        int randomNumber = generateRandomNumber(currentLevel);

        // Display the random number in both Textview4 and Textview6
        questionTextView.setText(String.valueOf(randomNumber));
        questionTextView6.setText(String.valueOf(randomNumber));
    }


    // Method to check the user's answer
    private void checkAnswer() {
        // Get user answer
        String userAnswerStr = answerEditText.getText().toString().trim();
        if (!userAnswerStr.isEmpty()) {
            int userAnswer = Integer.parseInt(userAnswerStr);

            // Compare the answer with the user's input
            if (userAnswer == generateRandomNumber(currentLevel)) {
                points++; // Increase points for correct answer
            } else {
                points--; // Deduct points for incorrect answer
            }

            // Update points and display another question
            pointTextView.setText(String.valueOf(points));
            setQuestion();

            // Clear the answerEditText
            answerEditText.getText().clear();
        } else {
            // Handle the case when the answer is empty
            Log.d("MainActivity", "Answer is empty");
        }
    }
}



