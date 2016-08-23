package hku.demscreen.hk;

import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.io.IOException;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Module04Activity extends AppCompatActivity {

    String Tag = "Module04Activity";

    // Main screen
    TextView questionNumber;
    ImageView scoreCorrect;
    ImageView scoreWrong;
    ImageView info;
    // Question
    ImageView previousQuestion;
    ImageView nextQuestion;
    TextView question;
    private MediaRecorder myAudioRecorder;
    private String outputFile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module04);

        //Main screen
        info = (ImageView) findViewById(R.id.info_m04);
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

        startAudioRecorder();
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
                if (GlobalVariables.m04QuestionNo == 2) {
                    questionDecrement();
                } else if (GlobalVariables.m04QuestionNo == 3) {
                    questionDecrement();
                } else if (GlobalVariables.m04QuestionNo == 4) {
                    questionDecrement();
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
        }
    }

    // On correct button
    private void onCorrect() {
        if (GlobalVariables.m04QuestionNo == 1) {
            GlobalVariables.m04Score[0] = 1;
            questionIncrement();
        } else if (GlobalVariables.m04QuestionNo == 2) {
            GlobalVariables.m04Score[1] = 1;
            questionIncrement();
        } else if (GlobalVariables.m04QuestionNo == 3) {
            GlobalVariables.m04Score[2] = 1;
            questionIncrement();
        } else if (GlobalVariables.m04QuestionNo == 4) {
            GlobalVariables.m04Score[3] = 1;
            nextModule();
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
        }
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

    // Start recording Audio
    private void startAudioRecorder() {
        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Dementia Cognition Screen/" + GlobalVariables.userName + GlobalVariables.userAge + GlobalVariables.userID + "/06 - Task 04 Recording.mp3";
        myAudioRecorder = new MediaRecorder();
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        myAudioRecorder.setOutputFile(outputFile);

        try {
            myAudioRecorder.prepare();
            myAudioRecorder.start();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Stop recording Audio
    private void stopAudioRecorder() {
        myAudioRecorder.stop();
        myAudioRecorder.release();
        myAudioRecorder = null;
    }

    // Starts next selected Task
    private void nextModule() {
        stopAudioRecorder();
        if (GlobalVariables.modulesSelected[4]) {
            Intent intentModulesActivity = new Intent(Module04Activity.this, Module05Activity.class);
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
            Intent intentModulesActivity = new Intent(Module04Activity.this, Module09Activity.class);
            Module04Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[9]) {
            Intent intentModulesActivity = new Intent(Module04Activity.this, Module10Activity.class);
            Module04Activity.this.startActivity(intentModulesActivity);
        } else {
            Intent intentModulesActivity = new Intent(Module04Activity.this, ResultsActivity.class);
            Module04Activity.this.startActivity(intentModulesActivity);
        }
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }
}
