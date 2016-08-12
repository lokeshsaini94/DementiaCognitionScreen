package hku.demscreen.hk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Module04Activity extends AppCompatActivity {

    String Tag = "Module04Activity";

    // Main screen
    TextView questionNumber;
    ImageView scoreCorrect;
    ImageView scoreWrong;
    ImageView info;
    ImageView sound;

    // Question
    ImageView previousQuestion;
    ImageView nextQuestion;
    TextView question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module04);

        //Main screen
        info = (ImageView) findViewById(R.id.info_m04);
        sound = (ImageView) findViewById(R.id.sound_m04);
        scoreCorrect = (ImageView) findViewById(R.id.score_correct_m04);
        scoreWrong = (ImageView) findViewById(R.id.score_wrong_m04);
        questionNumber = (TextView) findViewById(R.id.question_number_m04);

        // Question
        previousQuestion = (ImageView) findViewById(R.id.m04_previous_question);
        nextQuestion = (ImageView) findViewById(R.id.m04_next_question);
        question = (TextView) findViewById(R.id.m01_question);

        //Main screen

        setViewModule();

        mainQuestion();
    }

    // Action bar next button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_next) {
            Toast.makeText(getApplicationContext(), "q1= " + GlobalVariables.m04Score[0] + " q2= " + GlobalVariables.m04Score[1] + " q3= " + GlobalVariables.m04Score[2] + " q4= " + GlobalVariables.m04Score[3], Toast.LENGTH_SHORT).show();
            nextModule();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Views on click listeners
    private void mainQuestion() {
        previousQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GlobalVariables.m04QuestionNo == 1) {
                    Log.d(Tag, "Error: previous button tapped on m04q1 (Module04Activity)");
                } else if (GlobalVariables.m04QuestionNo == 2) {
                    questionDecrement();
                } else if (GlobalVariables.m04QuestionNo == 3) {
                    questionDecrement();
                } else if (GlobalVariables.m04QuestionNo == 4) {
                    questionDecrement();
                } else {
                    Log.d(Tag, "Error: Something went wrong (Module04Activity.previousQuestion.setOnClickListener)");
                }
                setViewModule();
            }
        });

        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GlobalVariables.m04QuestionNo == 1) {
                    questionIncrement();
                } else if (GlobalVariables.m04QuestionNo == 2) {
                    questionIncrement();
                } else if (GlobalVariables.m04QuestionNo == 3) {
                    questionIncrement();
                } else if (GlobalVariables.m04QuestionNo == 4) {
                    Log.d(Tag, "Error: next button tapped on m04q4 (Module04Activity)");
                } else {
                    Log.d(Tag, "Error: Something went wrong (Module04Activity.nextQuestion.setOnClickListener)");
                }
                setViewModule();
            }
        });

        scoreCorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCorrect();
            }
        });

        scoreWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onWrong();
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SweetAlertDialog(Module04Activity.this)
                        .setTitleText(getString(R.string.orientation))
                        .setContentText("Answer the following questions.")
                        .show();
            }
        });

        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Recording will start/stop", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Increments question number and animates content view
    private void questionIncrement() {
        GlobalVariables.m04QuestionNo = GlobalVariables.m04QuestionNo + 1;
        animateRight();
    }

    // Decrements question number and animates content view
    private void questionDecrement() {
        GlobalVariables.m04QuestionNo = GlobalVariables.m04QuestionNo - 1;
        animateLeft();
    }

    // Sets views for all questions
    private void setViewModule() {
        if (GlobalVariables.m04QuestionNo == 1) {
            previousQuestion.setVisibility(View.GONE);
            nextQuestion.setVisibility(View.VISIBLE);
            question.setText(R.string.module_04_question1);
            questionNumber.setText("1/4");
        } else if (GlobalVariables.m04QuestionNo == 2) {
            previousQuestion.setVisibility(View.VISIBLE);
            nextQuestion.setVisibility(View.VISIBLE);
            question.setText(R.string.module_04_question2);
            questionNumber.setText("2/4");
        } else if (GlobalVariables.m04QuestionNo == 3) {
            previousQuestion.setVisibility(View.VISIBLE);
            nextQuestion.setVisibility(View.VISIBLE);
            question.setText(R.string.module_04_question3);
            questionNumber.setText("3/4");
        } else if (GlobalVariables.m04QuestionNo == 4) {
            previousQuestion.setVisibility(View.VISIBLE);
            nextQuestion.setVisibility(View.GONE);
            question.setText(R.string.module_04_question4);
            questionNumber.setText("4/4");
        } else {
            Toast.makeText(getApplicationContext(), "Something went wrong (Module04Activity.setView)", Toast.LENGTH_SHORT).show();
            GlobalVariables.m04QuestionNo = 1;
            setViewModule();
        }
    }

    // On correct button
    private void onCorrect() {
        if (GlobalVariables.m04QuestionNo == 1) {
            Toast.makeText(getApplicationContext(), "Next Question", Toast.LENGTH_SHORT).show();
            GlobalVariables.m04Score[0] = 1;
            questionIncrement();
        } else if (GlobalVariables.m04QuestionNo == 2) {
            Toast.makeText(getApplicationContext(), "Next Question", Toast.LENGTH_SHORT).show();
            GlobalVariables.m04Score[1] = 1;
            questionIncrement();
        } else if (GlobalVariables.m04QuestionNo == 3) {
            Toast.makeText(getApplicationContext(), "Next Question", Toast.LENGTH_SHORT).show();
            GlobalVariables.m04Score[2] = 1;
            questionIncrement();
        } else if (GlobalVariables.m04QuestionNo == 4) {
            GlobalVariables.m04Score[3] = 1;
            Toast.makeText(getApplicationContext(), "q1= " + GlobalVariables.m04Score[0] + " q2= " + GlobalVariables.m04Score[1] + " q3= " + GlobalVariables.m04Score[2] + " q4= " + GlobalVariables.m04Score[3], Toast.LENGTH_SHORT).show();
            nextModule();
        } else {
            Log.d(Tag, "Error: Something went wrong (Module04Activity.scoreCorrect.setOnClickListener)");
        }
        setViewModule();
    }

    // On wrong button
    private void onWrong() {
        if (GlobalVariables.m04QuestionNo == 1) {
            GlobalVariables.m04Score[0] = 0;
            questionIncrement();
        } else if (GlobalVariables.m04QuestionNo == 2) {
            GlobalVariables.m04Score[1] = 0;
            questionIncrement();
        } else if (GlobalVariables.m04QuestionNo == 3) {
            GlobalVariables.m04Score[2] = 0;
            questionIncrement();
        } else if (GlobalVariables.m04QuestionNo == 4) {
            GlobalVariables.m04Score[3] = 0;
            nextModule();
        } else {
            Log.d(Tag, "Error: Something went wrong (Module04Activity.scoreWrong.setOnClickListener)");
        }
        Toast.makeText(getApplicationContext(), "Wrong Answer", Toast.LENGTH_SHORT).show();
        setViewModule();
    }

    // Content left animation
    private void animateLeft() {
        YoYo.with(Techniques.SlideInLeft)
                .duration(500)
                .playOn(findViewById(R.id.content_m04));
    }

    // Content right animation
    private void animateRight() {
        YoYo.with(Techniques.SlideInRight)
                .duration(500)
                .playOn(findViewById(R.id.content_m04));
    }

    // Starts next selected Task
    private void nextModule() {
        if (GlobalVariables.modulesSelected[4]) {
            Intent intentModulesActivity = new Intent(Module04Activity.this, ResultsActivity.class);
            Module04Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[5]) {
            Intent intentModulesActivity = new Intent(Module04Activity.this, ResultsActivity.class);
            Module04Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[6]) {
            Intent intentModulesActivity = new Intent(Module04Activity.this, ResultsActivity.class);
            Module04Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[7]) {
            Intent intentModulesActivity = new Intent(Module04Activity.this, Module08Activity.class);
            Module04Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[8]) {
            Intent intentModulesActivity = new Intent(Module04Activity.this, ResultsActivity.class);
            Module04Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[9]) {
            Intent intentModulesActivity = new Intent(Module04Activity.this, ResultsActivity.class);
            Module04Activity.this.startActivity(intentModulesActivity);
        } else {
            Intent intentModulesActivity = new Intent(Module04Activity.this, ResultsActivity.class);
            Module04Activity.this.startActivity(intentModulesActivity);
        }
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }
}
