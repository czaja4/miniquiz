package com.example.miniquiz;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    TextView questionText, scoreText;
    Button startButton, resetButton, answerA, answerB, answerC;

    List<Question> questions;
    List<Question> quizQuestions;

    int currentQuestion = 0;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionText = findViewById(R.id.questionText);
        scoreText = findViewById(R.id.scoreText);

        startButton = findViewById(R.id.startButton);
        resetButton = findViewById(R.id.resetButton);

        answerA = findViewById(R.id.answerA);
        answerB = findViewById(R.id.answerB);
        answerC = findViewById(R.id.answerC);

        loadQuestions();

        startButton.setOnClickListener(v -> startQuiz());
        resetButton.setOnClickListener(v -> resetQuiz());

        View.OnClickListener answerListener = v -> checkAnswer(((Button)v).getText().toString());

        answerA.setOnClickListener(answerListener);
        answerB.setOnClickListener(answerListener);
        answerC.setOnClickListener(answerListener);
    }

    private void loadQuestions() {
        questions = new ArrayList<>();

        questions.add(new Question("Stolica Włoch to:", "Rzym", "Paryż", "Madryt", "Rzym"));
        questions.add(new Question("2 + 2 =", "3", "4", "5", "4"));
        questions.add(new Question("Kolor nieba:", "Zielony", "Niebieski", "Czerwony", "Niebieski"));
        questions.add(new Question("Stolica Polski:", "Warszawa", "Kraków", "Gdańsk", "Warszawa"));
        questions.add(new Question("5 * 3 =", "15", "10", "20", "15"));
        questions.add(new Question("Największy ocean:", "Atlantycki", "Spokojny", "Indyjski", "Spokojny"));
    }

    private void startQuiz() {
        Collections.shuffle(questions);
        quizQuestions = questions.subList(0, 5);

        score = 0;
        currentQuestion = 0;

        showQuizUI();
        showQuestion();
    }

    private void showQuizUI() {
        questionText.setVisibility(View.VISIBLE);
        answerA.setVisibility(View.VISIBLE);
        answerB.setVisibility(View.VISIBLE);
        answerC.setVisibility(View.VISIBLE);
    }

    private void showQuestion() {
        Question q = quizQuestions.get(currentQuestion);

        questionText.setText(q.question);
        answerA.setText(q.answerA);
        answerB.setText(q.answerB);
        answerC.setText(q.answerC);
    }

    private void checkAnswer(String answer) {
        Question q = quizQuestions.get(currentQuestion);

        if (answer.equals(q.correctAnswer)) {
            score++;
        }

        scoreText.setText("Wynik: " + score);

        currentQuestion++;

        if (currentQuestion < 5) {
            showQuestion();
        } else {
            questionText.setText("Koniec quizu! Twój wynik: " + score + " / 5");
            answerA.setVisibility(View.GONE);
            answerB.setVisibility(View.GONE);
            answerC.setVisibility(View.GONE);
        }
    }

    private void resetQuiz() {
        score = 0;
        currentQuestion = 0;

        scoreText.setText("Wynik: 0");

        questionText.setVisibility(View.GONE);
        answerA.setVisibility(View.GONE);
        answerB.setVisibility(View.GONE);
        answerC.setVisibility(View.GONE);
    }
}