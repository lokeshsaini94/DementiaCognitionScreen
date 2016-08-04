package hku.demscreen.hk;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class Module01Activity extends AppCompatActivity {

    String Tag = "Module01Activity";

    ImageView sound;
    ImageView scoreCorrect;
    ImageView scoreWrong;
    ImageView previousQuestion;
    ImageView nextQuestion;
    ImageView m1qImage;
    TextView m1qNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module01);

        sound = (ImageView) findViewById(R.id.sound);
        scoreCorrect = (ImageView) findViewById(R.id.score_correct);
        scoreWrong = (ImageView) findViewById(R.id.score_wrong);
        m1qImage = (ImageView) findViewById(R.id.m1q_image);
        m1qNumber = (TextView)findViewById(R.id.m1q_number);

        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Sound will Play", Toast.LENGTH_SHORT).show();
            }
        });

        scoreCorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GlobalVariables.m1Question == 1) {
                    GlobalVariables.m1Score[0] = 1;
                    questionIncrement();
                }else if (GlobalVariables.m1Question == 2) {
                    GlobalVariables.m1Score[1] = 1;
                    questionIncrement();
                }else if (GlobalVariables.m1Question == 3) {
                    GlobalVariables.m1Score[2] = 1;
                    questionIncrement();
                }else if (GlobalVariables.m1Question == 4) {
                    GlobalVariables.m1Score[3] = 1;
                } else {
                    Log.d(Tag, "Error: Something went wrong (Module01Activity.scoreCorrect.setOnClickListener)");
                }
                Toast.makeText(getApplicationContext(), "Correct Answer", Toast.LENGTH_SHORT).show();
                setView();
            }
        });

        scoreWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GlobalVariables.m1Question == 1) {
                    GlobalVariables.m1Score[0] = 0;
                    questionIncrement();
                }else if (GlobalVariables.m1Question == 2) {
                    GlobalVariables.m1Score[1] = 0;
                    questionIncrement();
                }else if (GlobalVariables.m1Question == 3) {
                    GlobalVariables.m1Score[2] = 0;
                    questionIncrement();
                }else if (GlobalVariables.m1Question == 4) {
                    GlobalVariables.m1Score[3] = 0;
                } else {
                    Log.d(Tag, "Error: Something went wrong (Module01Activity.scoreWrong.setOnClickListener)");
                }
                Toast.makeText(getApplicationContext(), "Wrong Answer", Toast.LENGTH_SHORT).show();
                setView();
            }
        });

        previousQuestion = (ImageView) findViewById(R.id.previous_question);
        nextQuestion = (ImageView) findViewById(R.id.next_question);

        previousQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GlobalVariables.m1Question == 1) {
                    Log.d(Tag, "Error: previous button tapped on m1q1 (Module01Activity)");
                }else if (GlobalVariables.m1Question == 2) {
                    questionDecrement();
                }else if (GlobalVariables.m1Question == 3) {
                    questionDecrement();
                }else if (GlobalVariables.m1Question == 4) {
                    questionDecrement();
                } else {
                    Log.d(Tag, "Error: Something went wrong (Module01Activity.previousQuestion.setOnClickListener)");
                }
                setView();
            }
        });

        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GlobalVariables.m1Question == 1) {
                    questionIncrement();
                }else if (GlobalVariables.m1Question == 2) {
                    questionIncrement();
                }else if (GlobalVariables.m1Question == 3) {
                    questionIncrement();
                }else if (GlobalVariables.m1Question == 4) {
                    Log.d(Tag, "Error: next button tapped on m1q4 (Module01Activity)");
                } else {
                    Log.d(Tag, "Error: Something went wrong (Module01Activity.nextQuestion.setOnClickListener)");
                }
                setView();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_module_one);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "q1= " + GlobalVariables.m1Score[0] + " q2= " + GlobalVariables.m1Score[1] + " q3= " + GlobalVariables.m1Score[2] + " q4= " + GlobalVariables.m1Score[3], Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void questionIncrement() {
        GlobalVariables.m1Question = GlobalVariables.m1Question + 1;
        animateActivityRight();
    }

    private void questionDecrement() {
        GlobalVariables.m1Question = GlobalVariables.m1Question - 1;
        animateActivityLeft();
    }

    private void setView() {
        if (GlobalVariables.m1Question == 1) {
            previousQuestion.setVisibility(View.GONE);
            nextQuestion.setVisibility(View.VISIBLE);
            m1qImage.setImageResource(R.drawable.m1q1);
            m1qNumber.setText("1/4");
        } else if (GlobalVariables.m1Question == 2) {
            previousQuestion.setVisibility(View.VISIBLE);
            nextQuestion.setVisibility(View.VISIBLE);
            m1qImage.setImageResource(R.drawable.m1q2);
            m1qNumber.setText("2/4");
        } else if (GlobalVariables.m1Question == 3) {
            previousQuestion.setVisibility(View.VISIBLE);
            nextQuestion.setVisibility(View.VISIBLE);
            m1qImage.setImageResource(R.drawable.m1q3);
            m1qNumber.setText("3/4");
        } else if (GlobalVariables.m1Question == 4) {
            previousQuestion.setVisibility(View.VISIBLE);
            nextQuestion.setVisibility(View.GONE);
            m1qImage.setImageResource(R.drawable.m1q4);
            m1qNumber.setText("4/4");
        } else {
            Toast.makeText(getApplicationContext(), "Something went wrong (Module01Activity.setView)", Toast.LENGTH_SHORT).show();
            GlobalVariables.m1Question = 1;
            setView();
        }
    }

    private void animateActivityLeft() {
        YoYo.with(Techniques.SlideInLeft)
                .duration(500)
                .playOn(findViewById(R.id.content_m1q));
    }

    private void animateActivityRight() {
        YoYo.with(Techniques.SlideInRight)
                .duration(500)
                .playOn(findViewById(R.id.content_m1q));
    }
}
