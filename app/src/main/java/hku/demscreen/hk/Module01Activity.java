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

public class Module01Activity extends AppCompatActivity {

    String Tag = "Module01Activity";

    // Main screen
    TextView questionNumber;
    ImageView scoreCorrect;
    ImageView scoreWrong;
    ImageView info;
    ImageView sound;

    // Question 01
    ImageView m01PreviousQuestion;
    ImageView m01NextQuestion;
    ImageView m01Image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module01);

        //Main screen
        info = (ImageView) findViewById(R.id.info01);
        sound = (ImageView) findViewById(R.id.sound_m01);
        scoreCorrect = (ImageView) findViewById(R.id.score_correct_m01);
        scoreWrong = (ImageView) findViewById(R.id.score_wrong_m01);
        questionNumber = (TextView) findViewById(R.id.question_number_m01);

        // Question 01
        m01PreviousQuestion = (ImageView) findViewById(R.id.m01_previous_question);
        m01NextQuestion = (ImageView) findViewById(R.id.m01_next_question);
        m01Image = (ImageView) findViewById(R.id.m01_image);

        //Main screen
        scoreCorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m01OnCorrect();
            }
        });

        scoreWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m01OnWrong();
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SweetAlertDialog(Module01Activity.this)
                        .setTitleText("Picture Naming")
                        .setContentText("What's in the picture?")
                        .show();
            }
        });

        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Recording will start/stop", Toast.LENGTH_SHORT).show();
            }
        });

        mainQuestion01();
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
            Toast.makeText(getApplicationContext(), "q1= " + GlobalVariables.m01Score[0] + " q2= " + GlobalVariables.m01Score[1] + " q3= " + GlobalVariables.m01Score[2] + " q4= " + GlobalVariables.m01Score[3], Toast.LENGTH_SHORT).show();
            nextModule();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Question 01
    private void mainQuestion01() {
        // Question 01
        m01PreviousQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GlobalVariables.m01QuestionNo == 1) {
                    Log.d(Tag, "Error: previous button tapped on m01q1 (Module01Activity)");
                } else if (GlobalVariables.m01QuestionNo == 2) {
                    question01Decrement();
                } else if (GlobalVariables.m01QuestionNo == 3) {
                    question01Decrement();
                } else if (GlobalVariables.m01QuestionNo == 4) {
                    question01Decrement();
                } else {
                    Log.d(Tag, "Error: Something went wrong (Module01Activity.m01PreviousQuestion.setOnClickListener)");
                }
                setViewModule01();
            }
        });

        m01NextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GlobalVariables.m01QuestionNo == 1) {
                    question01Increment();
                } else if (GlobalVariables.m01QuestionNo == 2) {
                    question01Increment();
                } else if (GlobalVariables.m01QuestionNo == 3) {
                    question01Increment();
                } else if (GlobalVariables.m01QuestionNo == 4) {
                    Log.d(Tag, "Error: next button tapped on m01q4 (Module01Activity)");
                } else {
                    Log.d(Tag, "Error: Something went wrong (Module01Activity.m01NextQuestion.setOnClickListener)");
                }
                setViewModule01();
            }
        });
    }

    private void question01Increment() {
        GlobalVariables.m01QuestionNo = GlobalVariables.m01QuestionNo + 1;
        m01AnimateRight();
    }

    private void question01Decrement() {
        GlobalVariables.m01QuestionNo = GlobalVariables.m01QuestionNo - 1;
        m01AnimateLeft();
    }

    private void setViewModule01() {
        if (GlobalVariables.m01QuestionNo == 1) {
            m01PreviousQuestion.setVisibility(View.GONE);
            m01NextQuestion.setVisibility(View.VISIBLE);
            m01Image.setImageResource(R.drawable.m01q1);
            questionNumber.setText("1/4");
        } else if (GlobalVariables.m01QuestionNo == 2) {
            m01PreviousQuestion.setVisibility(View.VISIBLE);
            m01NextQuestion.setVisibility(View.VISIBLE);
            m01Image.setImageResource(R.drawable.m01q2);
            questionNumber.setText("2/4");
        } else if (GlobalVariables.m01QuestionNo == 3) {
            m01PreviousQuestion.setVisibility(View.VISIBLE);
            m01NextQuestion.setVisibility(View.VISIBLE);
            m01Image.setImageResource(R.drawable.m01q3);
            questionNumber.setText("3/4");
        } else if (GlobalVariables.m01QuestionNo == 4) {
            m01PreviousQuestion.setVisibility(View.VISIBLE);
            m01NextQuestion.setVisibility(View.GONE);
            m01Image.setImageResource(R.drawable.m01q4);
            questionNumber.setText("4/4");
        } else {
            Toast.makeText(getApplicationContext(), "Something went wrong (Module01Activity.setView)", Toast.LENGTH_SHORT).show();
            GlobalVariables.m01QuestionNo = 1;
            setViewModule01();
        }
    }

    private void m01OnCorrect() {
        if (GlobalVariables.m01QuestionNo == 1) {
            GlobalVariables.m01Score[0] = 1;
            question01Increment();
        } else if (GlobalVariables.m01QuestionNo == 2) {
            GlobalVariables.m01Score[1] = 1;
            question01Increment();
        } else if (GlobalVariables.m01QuestionNo == 3) {
            GlobalVariables.m01Score[2] = 1;
            question01Increment();
        } else if (GlobalVariables.m01QuestionNo == 4) {
            GlobalVariables.m01Score[3] = 1;
        } else {
            Log.d(Tag, "Error: Something went wrong (Module01Activity.scoreCorrect.setOnClickListener)");
        }
        Toast.makeText(getApplicationContext(), "Correct Answer", Toast.LENGTH_SHORT).show();
        setViewModule01();
    }

    private void m01OnWrong() {
        if (GlobalVariables.m01QuestionNo == 1) {
            GlobalVariables.m01Score[0] = 0;
            question01Increment();
        } else if (GlobalVariables.m01QuestionNo == 2) {
            GlobalVariables.m01Score[1] = 0;
            question01Increment();
        } else if (GlobalVariables.m01QuestionNo == 3) {
            GlobalVariables.m01Score[2] = 0;
            question01Increment();
        } else if (GlobalVariables.m01QuestionNo == 4) {
            GlobalVariables.m01Score[3] = 0;
            nextModule();
        } else {
            Log.d(Tag, "Error: Something went wrong (Module01Activity.scoreWrong.setOnClickListener)");
        }
        Toast.makeText(getApplicationContext(), "Wrong Answer", Toast.LENGTH_SHORT).show();
        setViewModule01();
    }

    private void m01AnimateLeft() {
        YoYo.with(Techniques.SlideInLeft)
                .duration(500)
                .playOn(findViewById(R.id.content_m01));
    }

    private void m01AnimateRight() {
        YoYo.with(Techniques.SlideInRight)
                .duration(500)
                .playOn(findViewById(R.id.content_m01));
    }

    private void nextModule() {
        if (GlobalVariables.modulesSelected[1]) {
            Intent intentModulesActivity = new Intent(Module01Activity.this, Module02Activity.class);
            Module01Activity.this.startActivity(intentModulesActivity);
        } else {
            Intent intentModulesActivity = new Intent(Module01Activity.this, ResultsActivity.class);
            Module01Activity.this.startActivity(intentModulesActivity);
        }
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }
}
