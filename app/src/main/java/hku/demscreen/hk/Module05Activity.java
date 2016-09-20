package hku.demscreen.hk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Module05Activity extends AppCompatActivity {

    public String timeTaken;
    String Tag = "Module05Activity";
    Vibrator vibrator;
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;
    String fileName = "07 - Task 05 Question 1";
    // Main screen
    TextView questionNumber;
    ImageView score0;
    ImageView score1;
    ImageView score2;
    ImageView score3;
    ImageView score4;
    ImageView info;
    ImageView reset;
    // Question
    SimpleDrawingView canvas1;
    SimpleDrawingView canvas2;
    ImageView previousQuestion;
    ImageView nextQuestion;
    View figure;
    private long startTime = 0L;
    private Handler customHandler = new Handler();
    // Updates Timer continuously
    public Runnable updateTimerThread = new Runnable() {
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updatedTime = timeSwapBuff + timeInMilliseconds;
            int secs = (int) (timeInMilliseconds / 1000);
            if (GlobalVariables.m05QuestionNo == 2) {
                secs = secs - 30;
            }
            int mins = secs / 60;
            secs = secs % 60;
            mins = mins % 60;
            if (secs < 0) {
                secs = 0;
            }
            //int milliseconds = (int) (updatedTime % 1000);
            //+ ":" + String.format("%03d", milliseconds)
            timeTaken = String.format("%02d", mins) + ":" + String.format("%02d", secs);
            customHandler.postDelayed(this, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module05);

        //Main screen
        info = (ImageView) findViewById(R.id.info_m05);
        reset = (ImageView) findViewById(R.id.reset_m05);
        score0 = (ImageView) findViewById(R.id.score_0_m05);
        score1 = (ImageView) findViewById(R.id.score_1_m05);
        score2 = (ImageView) findViewById(R.id.score_2_m05);
        score3 = (ImageView) findViewById(R.id.score_3_m05);
        score4 = (ImageView) findViewById(R.id.score_4_m05);
        questionNumber = (TextView) findViewById(R.id.question_number_m05);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Question
        canvas1 = (SimpleDrawingView) findViewById(R.id.m05_canvas1);
        canvas2 = (SimpleDrawingView) findViewById(R.id.m05_canvas2);
        previousQuestion = (ImageView) findViewById(R.id.m05_previous_question);
        nextQuestion = (ImageView) findViewById(R.id.m05_next_question);
        figure = findViewById(R.id.m05_card1);

        //Main screen

        canvas2.setWillNotDraw(true);

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
            if (GlobalVariables.m05QuestionNo == 1) {
                StopTimer();
                GlobalVariables.m05TimeTaken[0] = "" + timeTaken;
            } else if (GlobalVariables.m05QuestionNo == 2) {
                StopTimer();
                GlobalVariables.m05TimeTaken[1] = "" + timeTaken;
            }
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
                if (GlobalVariables.m05QuestionNo == 2) {
                    questionDecrement();
                }
                setViewModule();
            }
        });

        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GlobalVariables.m05QuestionNo == 1) {
                    questionIncrement();
                }
                setViewModule();
            }
        });

        score0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GlobalVariables.m05QuestionNo == 1) {
                    GlobalVariables.m05Score[0] = 0;
                } else if (GlobalVariables.m05QuestionNo == 2) {
                    GlobalVariables.m05Score[1] = 0;
                }
                View rootView = getWindow().getDecorView().getRootView();
                GlobalVariables.saveScreenshot(rootView, fileName);
                onCorrect();
            }
        });

        score1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GlobalVariables.m05QuestionNo == 1) {
                    GlobalVariables.m05Score[0] = 0.5;
                } else if (GlobalVariables.m05QuestionNo == 2) {
                    GlobalVariables.m05Score[1] = 1;
                }
                View rootView = getWindow().getDecorView().getRootView();
                GlobalVariables.saveScreenshot(rootView, fileName);
                onCorrect();
            }
        });
        score2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GlobalVariables.m05QuestionNo == 1) {
                    GlobalVariables.m05Score[0] = 1;
                } else if (GlobalVariables.m05QuestionNo == 2) {
                    GlobalVariables.m05Score[1] = 2;
                }
                View rootView = getWindow().getDecorView().getRootView();
                GlobalVariables.saveScreenshot(rootView, fileName);
                onCorrect();
            }
        });
        score3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GlobalVariables.m05QuestionNo == 1) {
                    GlobalVariables.m05Score[0] = 1.5;
                } else if (GlobalVariables.m05QuestionNo == 2) {
                    GlobalVariables.m05Score[1] = 3;
                }
                View rootView = getWindow().getDecorView().getRootView();
                GlobalVariables.saveScreenshot(rootView, fileName);
                onCorrect();
            }
        });
        score4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GlobalVariables.m05QuestionNo == 1) {
                    GlobalVariables.m05Score[0] = 2;
                } else if (GlobalVariables.m05QuestionNo == 2) {
                    GlobalVariables.m05Score[1] = 4;
                }
                View rootView = getWindow().getDecorView().getRootView();
                GlobalVariables.saveScreenshot(rootView, fileName);
                onCorrect();
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SweetAlertDialog(Module05Activity.this)
                        .setTitleText(getString(R.string.figure_copy))
                        .setContentText(getString(R.string.java_task5_question))
                        .show();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GlobalVariables.m05QuestionNo == 1) {
                    canvas1.clearCanvas();
                } else if (GlobalVariables.m05QuestionNo == 2) {
                    canvas2.clearCanvas();
                }
            }
        });
    }

    // Increments question number and animates content view
    private void questionIncrement() {
        GlobalVariables.m05QuestionNo = 2;
        animateRight();
    }

    // Decrements question number and animates content view
    private void questionDecrement() {
        GlobalVariables.m05QuestionNo = 1;
        animateLeft();
    }

    // Sets views for all questions
    private void setViewModule() {
        if (GlobalVariables.m05QuestionNo == 1) {
            previousQuestion.setVisibility(View.GONE);
            nextQuestion.setVisibility(View.VISIBLE);
            questionNumber.setText("1/2");
            canvas1.setVisibility(View.VISIBLE);
            canvas2.setVisibility(View.GONE);
            figure.setVisibility(View.VISIBLE);
            fileName = "07 - Task 05 Question 1";
            StopTimer();
            StartTimer();
        } else if (GlobalVariables.m05QuestionNo == 2) {
            previousQuestion.setVisibility(View.VISIBLE);
            nextQuestion.setVisibility(View.GONE);
            questionNumber.setText("2/2");
            canvas1.setVisibility(View.GONE);
            canvas2.setVisibility(View.VISIBLE);
            invisibleImage();
            fileName = "07 - Task 05 Question 2";
            StopTimer();
            StartTimer();
        }
    }

    // Set invisible to image after 30 seconds
    private void invisibleImage() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                figure.setVisibility(View.INVISIBLE);
                canvas2.setWillNotDraw(false);
                canvas2.clearCanvas();
            }
        }, 30000);
    }

    // On correct button
    private void onCorrect() {
        if (GlobalVariables.m05QuestionNo == 1) {
            StopTimer();
            GlobalVariables.m05TimeTaken[0] = "" + timeTaken;
            questionIncrement();
        } else if (GlobalVariables.m05QuestionNo == 2) {
            StopTimer();
            GlobalVariables.m05TimeTaken[1] = "" + timeTaken;
            nextModule();
        }
        setViewModule();
    }

    // Content left animation
    private void animateLeft() {
        YoYo.with(Techniques.SlideInLeft)
                .duration(500)
                .playOn(findViewById(R.id.content_m05));
    }

    // Content right animation
    private void animateRight() {
        YoYo.with(Techniques.SlideInRight)
                .duration(500)
                .playOn(findViewById(R.id.content_m05));
    }

    // Starts Timer
    public void StartTimer() {
        startTime = SystemClock.uptimeMillis();
        customHandler.postDelayed(updateTimerThread, 0);
    }

    // Stops Timer
    public void StopTimer() {
        timeSwapBuff += timeInMilliseconds;
        customHandler.removeCallbacks(updateTimerThread);
    }

    // Starts next selected Task
    private void nextModule() {
        if (GlobalVariables.modulesSelected[5]) {
            Intent intentModulesActivity = new Intent(Module05Activity.this, Module06Activity.class);
            Module05Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[6]) {
            Intent intentModulesActivity = new Intent(Module05Activity.this, Module07Activity.class);
            Module05Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[7]) {
            Intent intentModulesActivity = new Intent(Module05Activity.this, Module08Activity.class);
            Module05Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[8]) {
            Intent intentModulesActivity = new Intent(Module05Activity.this, Module09Activity.class);
            Module05Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[9]) {
            Intent intentModulesActivity = new Intent(Module05Activity.this, Module10Activity.class);
            Module05Activity.this.startActivity(intentModulesActivity);
        } else {
            Intent intentModulesActivity = new Intent(Module05Activity.this, ResultsActivity.class);
            Module05Activity.this.startActivity(intentModulesActivity);
        }
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }
}
