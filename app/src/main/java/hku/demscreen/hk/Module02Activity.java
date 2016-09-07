package hku.demscreen.hk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Module02Activity extends AppCompatActivity {

    String Tag = "Module02Activity";
    Vibrator vibrator;

    String fileName = "04 - Task 02 Question 1";

    // Main screen
    TextView questionNumber;
    ImageView scoreCorrect;
    ImageView info;

    // Question
    ImageView previousQuestion;
    ImageView nextQuestion;
    CardView cardView1;
    CardView cardView2;
    CardView cardView3;
    CardView cardView4;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module02);

        //Main screen
        info = (ImageView) findViewById(R.id.info_m02);
        scoreCorrect = (ImageView) findViewById(R.id.score_correct_m02);
        questionNumber = (TextView) findViewById(R.id.question_number_m02);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Question
        previousQuestion = (ImageView) findViewById(R.id.m02_previous_question);
        nextQuestion = (ImageView) findViewById(R.id.m02_next_question);

        cardView1 = (CardView) findViewById(R.id.m02_card1);
        cardView2 = (CardView) findViewById(R.id.m02_card2);
        cardView3 = (CardView) findViewById(R.id.m02_card3);
        cardView4 = (CardView) findViewById(R.id.m02_card4);

        imageView1 = (ImageView) findViewById(R.id.m02_image1);
        imageView2 = (ImageView) findViewById(R.id.m02_image2);
        imageView3 = (ImageView) findViewById(R.id.m02_image3);
        imageView4 = (ImageView) findViewById(R.id.m02_image4);

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
            View rootView = getWindow().getDecorView().getRootView();
            GlobalVariables.saveScreenshot(rootView, fileName);
            nextModule();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Views on click listeners
    private void mainQuestion() {

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView1.setAlpha(1f);
                imageView2.setAlpha(0.5f);
                imageView3.setAlpha(0.5f);
                imageView4.setAlpha(0.5f);
                vibrator.vibrate(100);
                if (GlobalVariables.m02QuestionNo == 3) {
                    GlobalVariables.m02Score[2] = 1;
                }
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView1.setAlpha(0.5f);
                imageView2.setAlpha(1f);
                imageView3.setAlpha(0.5f);
                imageView4.setAlpha(0.5f);
                vibrator.vibrate(100);
                if (GlobalVariables.m02QuestionNo == 1) {
                    GlobalVariables.m02Score[0] = 1;
                }
                if (GlobalVariables.m02QuestionNo == 4) {
                    GlobalVariables.m02Score[3] = 1;
                }
            }
        });

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView1.setAlpha(0.5f);
                imageView2.setAlpha(0.5f);
                imageView3.setAlpha(1f);
                imageView4.setAlpha(0.5f);
                vibrator.vibrate(100);
            }
        });

        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView1.setAlpha(0.5f);
                imageView2.setAlpha(0.5f);
                imageView3.setAlpha(0.5f);
                imageView4.setAlpha(1f);
                vibrator.vibrate(100);
                if (GlobalVariables.m02QuestionNo == 2) {
                    GlobalVariables.m02Score[1] = 1;
                }
            }
        });

        previousQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GlobalVariables.m02QuestionNo == 2) {
                    questionDecrement();
                } else if (GlobalVariables.m02QuestionNo == 3) {
                    questionDecrement();
                } else if (GlobalVariables.m02QuestionNo == 4) {
                    questionDecrement();
                }
                setViewModule();
            }
        });

        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GlobalVariables.m02QuestionNo == 1) {
                    questionIncrement();
                } else if (GlobalVariables.m02QuestionNo == 2) {
                    questionIncrement();
                } else if (GlobalVariables.m02QuestionNo == 3) {
                    questionIncrement();
                }
                setViewModule();
            }
        });

        scoreCorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View rootView = getWindow().getDecorView().getRootView();
                GlobalVariables.saveScreenshot(rootView, fileName);
                onCorrect();
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GlobalVariables.m02QuestionNo == 1) {
                    new SweetAlertDialog(Module02Activity.this)
                            .setTitleText(getString(R.string.picture_pointing))
                            .setContentText(getString(R.string.java_task2_question1))
                            .show();
                } else if (GlobalVariables.m02QuestionNo == 2) {
                    new SweetAlertDialog(Module02Activity.this)
                            .setTitleText(getString(R.string.picture_pointing))
                            .setContentText(getString(R.string.java_task2_question2))
                            .show();
                } else if (GlobalVariables.m02QuestionNo == 3) {
                    new SweetAlertDialog(Module02Activity.this)
                            .setTitleText(getString(R.string.picture_pointing))
                            .setContentText(getString(R.string.java_task2_question3))
                            .show();
                } else if (GlobalVariables.m02QuestionNo == 4) {
                    new SweetAlertDialog(Module02Activity.this)
                            .setTitleText(getString(R.string.picture_pointing))
                            .setContentText(getString(R.string.java_task2_question4))
                            .show();
                }
            }
        });
    }

    // Increments question number and animates content view
    private void questionIncrement() {
        GlobalVariables.m02QuestionNo = GlobalVariables.m02QuestionNo + 1;
        animateRight();
    }

    // Decrements question number and animates content view
    private void questionDecrement() {
        GlobalVariables.m02QuestionNo = GlobalVariables.m02QuestionNo - 1;
        animateLeft();
    }

    // Sets views for all questions
    private void setViewModule() {
        imageView1.setAlpha(1f);
        imageView2.setAlpha(1f);
        imageView3.setAlpha(1f);
        imageView4.setAlpha(1f);
        if (GlobalVariables.m02QuestionNo == 1) {
            previousQuestion.setVisibility(View.GONE);
            nextQuestion.setVisibility(View.VISIBLE);
            imageView1.setImageResource(R.drawable.m02q1_1);
            imageView2.setImageResource(R.drawable.m02q1_2);
            imageView3.setImageResource(R.drawable.m02q1_3);
            imageView4.setImageResource(R.drawable.m02q1_4);
            questionNumber.setText("1/4");
            fileName = "04 - Task 02 Question 1";
        } else if (GlobalVariables.m02QuestionNo == 2) {
            previousQuestion.setVisibility(View.VISIBLE);
            nextQuestion.setVisibility(View.VISIBLE);
            imageView1.setImageResource(R.drawable.m02q2_1);
            imageView2.setImageResource(R.drawable.m02q2_2);
            imageView3.setImageResource(R.drawable.m02q2_3);
            imageView4.setImageResource(R.drawable.m02q2_4);
            questionNumber.setText("2/4");
            fileName = "04 - Task 02 Question 2";
        } else if (GlobalVariables.m02QuestionNo == 3) {
            previousQuestion.setVisibility(View.VISIBLE);
            nextQuestion.setVisibility(View.VISIBLE);
            imageView1.setImageResource(R.drawable.m02q3_1);
            imageView2.setImageResource(R.drawable.m02q3_2);
            imageView3.setImageResource(R.drawable.m02q3_3);
            imageView4.setImageResource(R.drawable.m02q3_4);
            questionNumber.setText("3/4");
            fileName = "04 - Task 02 Question 3";
        } else if (GlobalVariables.m02QuestionNo == 4) {
            previousQuestion.setVisibility(View.VISIBLE);
            nextQuestion.setVisibility(View.GONE);
            imageView1.setImageResource(R.drawable.m02q4_1);
            imageView2.setImageResource(R.drawable.m02q4_2);
            imageView3.setImageResource(R.drawable.m02q4_3);
            imageView4.setImageResource(R.drawable.m02q4_4);
            questionNumber.setText("4/4");
            fileName = "04 - Task 02 Question 4";
        }
    }

    // On correct button
    private void onCorrect() {
        if (GlobalVariables.m02QuestionNo == 1) {
            questionIncrement();
        } else if (GlobalVariables.m02QuestionNo == 2) {
            questionIncrement();
        } else if (GlobalVariables.m02QuestionNo == 3) {
            questionIncrement();
        } else if (GlobalVariables.m02QuestionNo == 4) {
            nextModule();
        }
        setViewModule();
    }

    // Content left animation
    private void animateLeft() {
        YoYo.with(Techniques.SlideInLeft)
                .duration(500)
                .playOn(findViewById(R.id.content_m02));
    }

    // Content right animation
    private void animateRight() {
        YoYo.with(Techniques.SlideInRight)
                .duration(500)
                .playOn(findViewById(R.id.content_m02));
    }

    // Starts next selected Task
    private void nextModule() {
        if (GlobalVariables.modulesSelected[2]) {
            Intent intentModulesActivity = new Intent(Module02Activity.this, Module03Activity.class);
            Module02Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[3]) {
            Intent intentModulesActivity = new Intent(Module02Activity.this, Module04Activity.class);
            Module02Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[4]) {
            Intent intentModulesActivity = new Intent(Module02Activity.this, Module05Activity.class);
            Module02Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[5]) {
            Intent intentModulesActivity = new Intent(Module02Activity.this, Module06Activity.class);
            Module02Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[6]) {
            Intent intentModulesActivity = new Intent(Module02Activity.this, ResultsActivity.class);
            Module02Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[7]) {
            Intent intentModulesActivity = new Intent(Module02Activity.this, Module08Activity.class);
            Module02Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[8]) {
            Intent intentModulesActivity = new Intent(Module02Activity.this, Module09Activity.class);
            Module02Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[9]) {
            Intent intentModulesActivity = new Intent(Module02Activity.this, Module10Activity.class);
            Module02Activity.this.startActivity(intentModulesActivity);
        } else {
            Intent intentModulesActivity = new Intent(Module02Activity.this, ResultsActivity.class);
            Module02Activity.this.startActivity(intentModulesActivity);
        }
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }
}
