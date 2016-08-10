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

    // Question 01
    ImageView m04PreviousQuestion;
    ImageView m04NextQuestion;
    TextView m01question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module04);

        //Main screen
        info = (ImageView) findViewById(R.id.info04);
        sound = (ImageView) findViewById(R.id.sound_m04);
        scoreCorrect = (ImageView) findViewById(R.id.score_correct_m04);
        scoreWrong = (ImageView) findViewById(R.id.score_wrong_m04);
        questionNumber = (TextView) findViewById(R.id.question_number_m04);

        // Question 01
        m04PreviousQuestion = (ImageView) findViewById(R.id.m04_previous_question);
        m04NextQuestion = (ImageView) findViewById(R.id.m04_next_question);
        m01question = (TextView) findViewById(R.id.m01_question);

        //Main screen

        setViewModule04();

        scoreCorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m04OnCorrect();
            }
        });

        scoreWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m04OnWrong();
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SweetAlertDialog(Module04Activity.this)
                        .setTitleText("Orientation")
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

        mainQuestion04();
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

    // Question 04
    private void mainQuestion04() {
        m04PreviousQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GlobalVariables.m04QuestionNo == 1) {
                    Log.d(Tag, "Error: previous button tapped on m04q1 (Module04Activity)");
                } else if (GlobalVariables.m04QuestionNo == 2) {
                    question04Decrement();
                } else if (GlobalVariables.m04QuestionNo == 3) {
                    question04Decrement();
                } else if (GlobalVariables.m04QuestionNo == 4) {
                    question04Decrement();
                } else {
                    Log.d(Tag, "Error: Something went wrong (Module04Activity.m04PreviousQuestion.setOnClickListener)");
                }
                setViewModule04();
            }
        });

        m04NextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GlobalVariables.m04QuestionNo == 1) {
                    question04Increment();
                } else if (GlobalVariables.m04QuestionNo == 2) {
                    question04Increment();
                } else if (GlobalVariables.m04QuestionNo == 3) {
                    question04Increment();
                } else if (GlobalVariables.m04QuestionNo == 4) {
                    Log.d(Tag, "Error: next button tapped on m04q4 (Module04Activity)");
                } else {
                    Log.d(Tag, "Error: Something went wrong (Module04Activity.m04NextQuestion.setOnClickListener)");
                }
                setViewModule04();
            }
        });
    }

    private void question04Increment() {
        GlobalVariables.m04QuestionNo = GlobalVariables.m04QuestionNo + 1;
        m04AnimateRight();
    }

    private void question04Decrement() {
        GlobalVariables.m04QuestionNo = GlobalVariables.m04QuestionNo - 1;
        m04AnimateLeft();
    }

    private void setViewModule04() {
        if (GlobalVariables.m04QuestionNo == 1) {
            m04PreviousQuestion.setVisibility(View.GONE);
            m04NextQuestion.setVisibility(View.VISIBLE);
            m01question.setText(R.string.module_04_question1);
            questionNumber.setText("1/4");
        } else if (GlobalVariables.m04QuestionNo == 2) {
            m04PreviousQuestion.setVisibility(View.VISIBLE);
            m04NextQuestion.setVisibility(View.VISIBLE);
            m01question.setText(R.string.module_04_question2);
            questionNumber.setText("2/4");
        } else if (GlobalVariables.m04QuestionNo == 3) {
            m04PreviousQuestion.setVisibility(View.VISIBLE);
            m04NextQuestion.setVisibility(View.VISIBLE);
            m01question.setText(R.string.module_04_question3);
            questionNumber.setText("3/4");
        } else if (GlobalVariables.m04QuestionNo == 4) {
            m04PreviousQuestion.setVisibility(View.VISIBLE);
            m04NextQuestion.setVisibility(View.GONE);
            m01question.setText(R.string.module_04_question4);
            questionNumber.setText("4/4");
        } else {
            Toast.makeText(getApplicationContext(), "Something went wrong (Module04Activity.setView)", Toast.LENGTH_SHORT).show();
            GlobalVariables.m04QuestionNo = 1;
            setViewModule04();
        }
    }

    private void m04OnCorrect() {
        if (GlobalVariables.m04QuestionNo == 1) {
            Toast.makeText(getApplicationContext(), "Next Question", Toast.LENGTH_SHORT).show();
            GlobalVariables.m04Score[0] = 1;
            question04Increment();
        } else if (GlobalVariables.m04QuestionNo == 2) {
            Toast.makeText(getApplicationContext(), "Next Question", Toast.LENGTH_SHORT).show();
            GlobalVariables.m04Score[1] = 1;
            question04Increment();
        } else if (GlobalVariables.m04QuestionNo == 3) {
            Toast.makeText(getApplicationContext(), "Next Question", Toast.LENGTH_SHORT).show();
            GlobalVariables.m04Score[2] = 1;
            question04Increment();
        } else if (GlobalVariables.m04QuestionNo == 4) {
            Toast.makeText(getApplicationContext(), "Next Task", Toast.LENGTH_SHORT).show();
            GlobalVariables.m04Score[3] = 1;
            nextModule();
        } else {
            Log.d(Tag, "Error: Something went wrong (Module04Activity.scoreCorrect.setOnClickListener)");
        }
        setViewModule04();
    }

    private void m04OnWrong() {
        if (GlobalVariables.m04QuestionNo == 1) {
            GlobalVariables.m04Score[0] = 0;
            question04Increment();
        } else if (GlobalVariables.m04QuestionNo == 2) {
            GlobalVariables.m04Score[1] = 0;
            question04Increment();
        } else if (GlobalVariables.m04QuestionNo == 3) {
            GlobalVariables.m04Score[2] = 0;
            question04Increment();
        } else if (GlobalVariables.m04QuestionNo == 4) {
            GlobalVariables.m04Score[3] = 0;
            nextModule();
        } else {
            Log.d(Tag, "Error: Something went wrong (Module04Activity.scoreWrong.setOnClickListener)");
        }
        Toast.makeText(getApplicationContext(), "Wrong Answer", Toast.LENGTH_SHORT).show();
        setViewModule04();
    }

    private void m04AnimateLeft() {
        YoYo.with(Techniques.SlideInLeft)
                .duration(500)
                .playOn(findViewById(R.id.content_m04));
    }

    private void m04AnimateRight() {
        YoYo.with(Techniques.SlideInRight)
                .duration(500)
                .playOn(findViewById(R.id.content_m04));
    }

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
            Intent intentModulesActivity = new Intent(Module04Activity.this, ResultsActivity.class);
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
