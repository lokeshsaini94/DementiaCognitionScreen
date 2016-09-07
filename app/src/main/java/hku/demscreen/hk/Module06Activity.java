package hku.demscreen.hk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Module06Activity extends AppCompatActivity {

    String Tag = "Module06Activity";
    String fileName = "08 - Task 06 Question 1";

    // Main screen
    TextView questionNumber;
    ImageView info;
    ImageView scoreCorrect;
    SeekBar score;
    TextView scoreText;
    boolean[] questionDoneOnce = {false, false, false};
    // Question
    ImageView previousQuestion;
    ImageView nextQuestion;
    CardView cardView1;
    CardView cardView2;
    ImageView trailsDemo;
    View canvas1;
    View canvas2;
    View canvas3;
    View canvas4;
    View canvas5;
    View canvas6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module06);

        //Main screen
        info = (ImageView) findViewById(R.id.info_m06);
        scoreCorrect = (ImageView) findViewById(R.id.score_correct_m06);
        score = (SeekBar) findViewById(R.id.m06_seekbar);
        scoreText = (TextView) findViewById(R.id.score_m06);
        questionNumber = (TextView) findViewById(R.id.question_number_m06);

        // Question
        cardView1 = (CardView) findViewById(R.id.m06_card1);
        cardView2 = (CardView) findViewById(R.id.m06_card2);
        trailsDemo = (ImageView) findViewById(R.id.m06_figure);
        canvas1 = findViewById(R.id.m06_canvas1);
        canvas2 = findViewById(R.id.m06_canvas2);
        canvas3 = findViewById(R.id.m06_canvas3);
        canvas4 = findViewById(R.id.m06_canvas4);
        canvas5 = findViewById(R.id.m06_canvas5);
        canvas6 = findViewById(R.id.m06_canvas6);
        previousQuestion = (ImageView) findViewById(R.id.m06_previous_question);
        nextQuestion = (ImageView) findViewById(R.id.m06_next_question);

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

        score.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (GlobalVariables.m06QuestionNo == 3) {
                    GlobalVariables.m06Score[0] = i;
                    scoreText.setText("" + i);
                } else if (GlobalVariables.m06QuestionNo == 6) {
                    GlobalVariables.m06Score[1] = i;
                    scoreText.setText("" + i);
                } else if (GlobalVariables.m06QuestionNo == 9) {
                    GlobalVariables.m06Score[2] = i;
                    scoreText.setText("" + i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

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
                if (GlobalVariables.m06QuestionNo == 1 || GlobalVariables.m06QuestionNo == 2 || GlobalVariables.m06QuestionNo == 3) {
                    new SweetAlertDialog(Module06Activity.this)
                            .setTitleText(getString(R.string.trails))
                            .setContentText(getString(R.string.java_task6_question1))
                            .show();
                } else if (GlobalVariables.m06QuestionNo == 4 || GlobalVariables.m06QuestionNo == 5 || GlobalVariables.m06QuestionNo == 6) {
                    new SweetAlertDialog(Module06Activity.this)
                            .setTitleText(getString(R.string.trails))
                            .setContentText(getString(R.string.java_task6_question2))
                            .show();
                } else if (GlobalVariables.m06QuestionNo == 7 || GlobalVariables.m06QuestionNo == 8 || GlobalVariables.m06QuestionNo == 9) {
                    new SweetAlertDialog(Module06Activity.this)
                            .setTitleText(getString(R.string.trails))
                            .setContentText(getString(R.string.java_task6_question3_1) +
                                    "\n" +
                                    getString(R.string.java_task6_question3_2) +
                                    "\n" +
                                    getString(R.string.java_task6_question3_3))
                            .show();
                }
            }
        });

        previousQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GlobalVariables.m06QuestionNo == 2) {
                    questionDecrement();
                } else if (GlobalVariables.m06QuestionNo == 3) {
                    questionDecrement();
                } else if (GlobalVariables.m06QuestionNo == 4) {
                    questionDecrement();
                } else if (GlobalVariables.m06QuestionNo == 5) {
                    questionDecrement();
                } else if (GlobalVariables.m06QuestionNo == 6) {
                    questionDecrement();
                } else if (GlobalVariables.m06QuestionNo == 7) {
                    questionDecrement();
                } else if (GlobalVariables.m06QuestionNo == 8) {
                    questionDecrement();
                } else if (GlobalVariables.m06QuestionNo == 9) {
                    questionDecrement();
                }
                setViewModule();
            }
        });

        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GlobalVariables.m06QuestionNo == 1) {
                    questionIncrement();
                } else if (GlobalVariables.m06QuestionNo == 2) {
                    questionIncrement();
                } else if (GlobalVariables.m06QuestionNo == 3) {
                    questionIncrement();
                } else if (GlobalVariables.m06QuestionNo == 4) {
                    questionIncrement();
                } else if (GlobalVariables.m06QuestionNo == 5) {
                    questionIncrement();
                } else if (GlobalVariables.m06QuestionNo == 6) {
                    questionIncrement();
                } else if (GlobalVariables.m06QuestionNo == 7) {
                    questionIncrement();
                } else if (GlobalVariables.m06QuestionNo == 8) {
                    questionIncrement();
                }
                setViewModule();
            }
        });
    }

    // Increments question number and animates content view
    private void questionIncrement() {
        GlobalVariables.m06QuestionNo = GlobalVariables.m06QuestionNo + 1;
        animateRight();
    }

    // Decrements question number and animates content view
    private void questionDecrement() {
        GlobalVariables.m06QuestionNo = GlobalVariables.m06QuestionNo - 1;
        animateLeft();
    }

    // Sets views for all questions
    private void setViewModule() {
        if (GlobalVariables.m06QuestionNo == 1) {
            previousQuestion.setVisibility(View.GONE);
            nextQuestion.setVisibility(View.VISIBLE);
            cardView1.setVisibility(View.VISIBLE);
            cardView2.setVisibility(View.GONE);
            trailsDemo.setImageResource(R.drawable.ic_trails1_1);
            score.setVisibility(View.GONE);
            scoreText.setVisibility(View.GONE);
            questionNumber.setText("1/1");
            fileName = "08 - Task 06 Question 1 A Rule";
        } else if (GlobalVariables.m06QuestionNo == 2) {
            previousQuestion.setVisibility(View.VISIBLE);
            nextQuestion.setVisibility(View.VISIBLE);
            cardView1.setVisibility(View.GONE);
            cardView2.setVisibility(View.VISIBLE);
            canvas1.setVisibility(View.VISIBLE);
            canvas2.setVisibility(View.GONE);
            canvas3.setVisibility(View.GONE);
            canvas4.setVisibility(View.GONE);
            canvas5.setVisibility(View.GONE);
            canvas6.setVisibility(View.GONE);
            score.setVisibility(View.GONE);
            scoreText.setVisibility(View.GONE);
            questionNumber.setText("1/1");
            fileName = "08 - Task 06 Question 1 B User";
        } else if (GlobalVariables.m06QuestionNo == 3) {
            previousQuestion.setVisibility(View.VISIBLE);
            nextQuestion.setVisibility(View.VISIBLE);
            cardView1.setVisibility(View.GONE);
            cardView2.setVisibility(View.VISIBLE);
            canvas1.setVisibility(View.GONE);
            canvas2.setVisibility(View.VISIBLE);
            canvas3.setVisibility(View.GONE);
            canvas4.setVisibility(View.GONE);
            canvas5.setVisibility(View.GONE);
            canvas6.setVisibility(View.GONE);
            score.setVisibility(View.VISIBLE);
            scoreText.setVisibility(View.VISIBLE);
            if (!questionDoneOnce[0]) {
                score.setProgress(0);
                scoreText.setText("" + 0);
                questionDoneOnce[0] = true;
            }
            questionNumber.setText("1/1");
            fileName = "08 - Task 06 Question 1 C Patient";
        } else if (GlobalVariables.m06QuestionNo == 4) {
            previousQuestion.setVisibility(View.VISIBLE);
            nextQuestion.setVisibility(View.VISIBLE);
            cardView1.setVisibility(View.VISIBLE);
            cardView2.setVisibility(View.GONE);
            trailsDemo.setImageResource(R.drawable.ic_trails2_1);
            score.setVisibility(View.GONE);
            scoreText.setVisibility(View.GONE);
            questionNumber.setText("2/2");
            fileName = "08 - Task 06 Question 2 A Rule";
        } else if (GlobalVariables.m06QuestionNo == 5) {
            previousQuestion.setVisibility(View.VISIBLE);
            nextQuestion.setVisibility(View.VISIBLE);
            cardView1.setVisibility(View.GONE);
            cardView2.setVisibility(View.VISIBLE);
            canvas1.setVisibility(View.GONE);
            canvas2.setVisibility(View.GONE);
            canvas3.setVisibility(View.VISIBLE);
            canvas4.setVisibility(View.GONE);
            canvas5.setVisibility(View.GONE);
            canvas6.setVisibility(View.GONE);
            score.setVisibility(View.GONE);
            scoreText.setVisibility(View.GONE);
            questionNumber.setText("2/2");
            fileName = "08 - Task 06 Question 2 B User";
        } else if (GlobalVariables.m06QuestionNo == 6) {
            previousQuestion.setVisibility(View.VISIBLE);
            nextQuestion.setVisibility(View.VISIBLE);
            cardView1.setVisibility(View.GONE);
            cardView2.setVisibility(View.VISIBLE);
            canvas1.setVisibility(View.GONE);
            canvas2.setVisibility(View.GONE);
            canvas3.setVisibility(View.GONE);
            canvas4.setVisibility(View.VISIBLE);
            canvas5.setVisibility(View.GONE);
            canvas6.setVisibility(View.GONE);
            score.setVisibility(View.VISIBLE);
            scoreText.setVisibility(View.VISIBLE);
            if (!questionDoneOnce[1]) {
                score.setProgress(0);
                scoreText.setText("" + 0);
                questionDoneOnce[1] = true;
            }
            questionNumber.setText("2/2");
            fileName = "08 - Task 06 Question 2 C Patient";
        } else if (GlobalVariables.m06QuestionNo == 7) {
            previousQuestion.setVisibility(View.VISIBLE);
            nextQuestion.setVisibility(View.VISIBLE);
            cardView1.setVisibility(View.VISIBLE);
            cardView2.setVisibility(View.GONE);
            trailsDemo.setImageResource(R.drawable.ic_trails3_1);
            score.setVisibility(View.GONE);
            scoreText.setVisibility(View.GONE);
            questionNumber.setText("3/3");
            fileName = "08 - Task 06 Question 3 A Rule";
        } else if (GlobalVariables.m06QuestionNo == 8) {
            previousQuestion.setVisibility(View.VISIBLE);
            nextQuestion.setVisibility(View.VISIBLE);
            cardView1.setVisibility(View.GONE);
            cardView2.setVisibility(View.VISIBLE);
            canvas1.setVisibility(View.GONE);
            canvas2.setVisibility(View.GONE);
            canvas3.setVisibility(View.GONE);
            canvas4.setVisibility(View.GONE);
            canvas5.setVisibility(View.VISIBLE);
            canvas6.setVisibility(View.GONE);
            score.setVisibility(View.GONE);
            scoreText.setVisibility(View.GONE);
            questionNumber.setText("3/3");
            fileName = "08 - Task 06 Question 3 B User";
        } else if (GlobalVariables.m06QuestionNo == 9) {
            previousQuestion.setVisibility(View.VISIBLE);
            nextQuestion.setVisibility(View.GONE);
            cardView1.setVisibility(View.GONE);
            cardView2.setVisibility(View.VISIBLE);
            canvas1.setVisibility(View.GONE);
            canvas2.setVisibility(View.GONE);
            canvas3.setVisibility(View.GONE);
            canvas4.setVisibility(View.GONE);
            canvas5.setVisibility(View.GONE);
            canvas6.setVisibility(View.VISIBLE);
            score.setVisibility(View.VISIBLE);
            scoreText.setVisibility(View.VISIBLE);
            if (!questionDoneOnce[2]) {
                score.setProgress(0);
                scoreText.setText("" + 0);
                questionDoneOnce[2] = true;
            }
            questionNumber.setText("3/3");
            fileName = "08 - Task 06 Question 3 C Patient";
        }
    }

    // On correct button
    private void onCorrect() {
        if (GlobalVariables.m06QuestionNo == 1) {
            questionIncrement();
        } else if (GlobalVariables.m06QuestionNo == 2) {
            questionIncrement();
        } else if (GlobalVariables.m06QuestionNo == 3) {
            questionIncrement();
        } else if (GlobalVariables.m06QuestionNo == 4) {
            questionIncrement();
        } else if (GlobalVariables.m06QuestionNo == 5) {
            questionIncrement();
        } else if (GlobalVariables.m06QuestionNo == 6) {
            questionIncrement();
        } else if (GlobalVariables.m06QuestionNo == 7) {
            questionIncrement();
        } else if (GlobalVariables.m06QuestionNo == 8) {
            questionIncrement();
        } else if (GlobalVariables.m06QuestionNo == 9) {
            nextModule();
        }
        setViewModule();
    }

    // Content left animation
    private void animateLeft() {
        YoYo.with(Techniques.SlideInLeft)
                .duration(500)
                .playOn(findViewById(R.id.content_m06));
    }

    // Content right animation
    private void animateRight() {
        YoYo.with(Techniques.SlideInRight)
                .duration(500)
                .playOn(findViewById(R.id.content_m06));
    }

    // Starts next selected Task
    private void nextModule() {
        if (GlobalVariables.modulesSelected[6]) {
            Intent intentModulesActivity = new Intent(Module06Activity.this, ResultsActivity.class);
            Module06Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[7]) {
            Intent intentModulesActivity = new Intent(Module06Activity.this, Module08Activity.class);
            Module06Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[8]) {
            Intent intentModulesActivity = new Intent(Module06Activity.this, Module09Activity.class);
            Module06Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[9]) {
            Intent intentModulesActivity = new Intent(Module06Activity.this, Module10Activity.class);
            Module06Activity.this.startActivity(intentModulesActivity);
        } else {
            Intent intentModulesActivity = new Intent(Module06Activity.this, ResultsActivity.class);
            Module06Activity.this.startActivity(intentModulesActivity);
        }
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }
}
