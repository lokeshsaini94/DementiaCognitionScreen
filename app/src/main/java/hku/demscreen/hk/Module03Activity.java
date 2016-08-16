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
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Module03Activity extends AppCompatActivity {

    String Tag = "Module03Activity";
    Vibrator vibrator;

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
    CardView cardView5;
    ImageView cardTick1;
    ImageView cardTick2;
    ImageView cardTick3;
    ImageView cardTick4;
    ImageView cardTick5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module03);

        //Main screen
        info = (ImageView) findViewById(R.id.info_m03);
        scoreCorrect = (ImageView) findViewById(R.id.score_correct_m03);
        questionNumber = (TextView) findViewById(R.id.question_number_m03);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Question
        previousQuestion = (ImageView) findViewById(R.id.m03_previous_question);
        nextQuestion = (ImageView) findViewById(R.id.m03_next_question);

        cardView1 = (CardView) findViewById(R.id.m03_card1);
        cardView2 = (CardView) findViewById(R.id.m03_card2);
        cardView3 = (CardView) findViewById(R.id.m03_card3);
        cardView4 = (CardView) findViewById(R.id.m03_card4);
        cardView5 = (CardView) findViewById(R.id.m03_card5);
        cardTick1 = (ImageView) findViewById(R.id.m03_card1_tick);
        cardTick2 = (ImageView) findViewById(R.id.m03_card2_tick);
        cardTick3 = (ImageView) findViewById(R.id.m03_card3_tick);
        cardTick4 = (ImageView) findViewById(R.id.m03_card4_tick);
        cardTick5 = (ImageView) findViewById(R.id.m03_card5_tick);

        //Main screen

        resetItems();

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
                vibrator.vibrate(100);
                if (GlobalVariables.m03Score[0] == 0) {
                    GlobalVariables.m03Score[0] = 1;
                    cardTick1.setVisibility(View.VISIBLE);
                    cardView1.setCardBackgroundColor(GlobalVariables.whiteColorValue);
                } else {
                    GlobalVariables.m03Score[0] = 0;
                    cardTick1.setVisibility(View.GONE);
                    cardView1.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);
                }
                if (GlobalVariables.m03QuestionNo == 1) {
                    hideNextIfScore5();
                }
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(100);
                if (GlobalVariables.m03Score[1] == 0) {
                    GlobalVariables.m03Score[1] = 1;
                    cardTick2.setVisibility(View.VISIBLE);
                    cardView2.setCardBackgroundColor(GlobalVariables.whiteColorValue);
                } else {
                    GlobalVariables.m03Score[1] = 0;
                    cardTick2.setVisibility(View.GONE);
                    cardView2.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);
                }
                if (GlobalVariables.m03QuestionNo == 1) {
                    hideNextIfScore5();
                }
            }
        });

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(100);
                if (GlobalVariables.m03Score[2] == 0) {
                    GlobalVariables.m03Score[2] = 1;
                    cardTick3.setVisibility(View.VISIBLE);
                    cardView3.setCardBackgroundColor(GlobalVariables.whiteColorValue);
                } else {
                    GlobalVariables.m03Score[2] = 0;
                    cardTick3.setVisibility(View.GONE);
                    cardView3.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);
                }
                if (GlobalVariables.m03QuestionNo == 1) {
                    hideNextIfScore5();
                }
            }
        });

        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(100);
                if (GlobalVariables.m03Score[3] == 0) {
                    GlobalVariables.m03Score[3] = 1;
                    cardTick4.setVisibility(View.VISIBLE);
                    cardView4.setCardBackgroundColor(GlobalVariables.whiteColorValue);
                } else {
                    GlobalVariables.m03Score[3] = 0;
                    cardTick4.setVisibility(View.GONE);
                    cardView4.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);
                }
                if (GlobalVariables.m03QuestionNo == 1) {
                    hideNextIfScore5();
                }
            }
        });

        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(100);
                if (GlobalVariables.m03Score[4] == 0) {
                    GlobalVariables.m03Score[4] = 1;
                    cardTick5.setVisibility(View.VISIBLE);
                    cardView5.setCardBackgroundColor(GlobalVariables.whiteColorValue);
                } else {
                    GlobalVariables.m03Score[4] = 0;
                    cardTick5.setVisibility(View.GONE);
                    cardView5.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);
                }
                if (GlobalVariables.m03QuestionNo == 1) {
                    hideNextIfScore5();
                }
            }
        });

        previousQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questionDecrement();
                setViewModule();
            }
        });

        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questionIncrement();
                setViewModule();
            }
        });

        scoreCorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCorrect();
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SweetAlertDialog(Module03Activity.this)
                        .setTitleText(getString(R.string.word_learning))
                        .setContentText("Remember these 5 words and repeat them.")
                        .show();
            }
        });
    }

    // Hides next question button if all answers are correct
    private void hideNextIfScore5() {
        if (GlobalVariables.m03Score[0] == 1 && GlobalVariables.m03Score[1] == 1 && GlobalVariables.m03Score[2] == 1 && GlobalVariables.m03Score[3] == 1 && GlobalVariables.m03Score[4] == 1) {
            nextQuestion.setVisibility(View.GONE);
            questionNumber.setText("1/1");
        } else {
            nextQuestion.setVisibility(View.VISIBLE);
            questionNumber.setText("1/2");
        }
    }

    // Resets card views and scores
    private void resetItems() {
        GlobalVariables.m03Score[0] = 0;
        GlobalVariables.m03Score[1] = 0;
        GlobalVariables.m03Score[2] = 0;
        GlobalVariables.m03Score[3] = 0;
        GlobalVariables.m03Score[4] = 0;
        cardTick1.setVisibility(View.GONE);
        cardView1.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);
        cardTick2.setVisibility(View.GONE);
        cardView2.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);
        cardTick3.setVisibility(View.GONE);
        cardView3.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);
        cardTick4.setVisibility(View.GONE);
        cardView4.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);
        cardTick5.setVisibility(View.GONE);
        cardView5.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);
    }

    // Increments question number and animates content view
    private void questionIncrement() {
        previousQuestion.setVisibility(View.VISIBLE);
        nextQuestion.setVisibility(View.GONE);
        GlobalVariables.m03QuestionNo = 2;
        resetItems();
        animateRight();
    }

    // Decrements question number and animates content view
    private void questionDecrement() {
        previousQuestion.setVisibility(View.GONE);
        nextQuestion.setVisibility(View.VISIBLE);
        GlobalVariables.m03QuestionNo = 1;
        resetItems();
        animateLeft();
    }

    // Sets views for all questions
    private void setViewModule() {
        if (GlobalVariables.m03QuestionNo == 1) {
            questionNumber.setText("1/2");
            questionDecrement();
        } else if (GlobalVariables.m03QuestionNo == 2) {
            questionNumber.setText("2/2");
            questionIncrement();
        }
    }

    // On correct button
    private void onCorrect() {
        if (GlobalVariables.m03QuestionNo == 1) {
            if (GlobalVariables.m03Score[0] == 1 && GlobalVariables.m03Score[1] == 1 && GlobalVariables.m03Score[2] == 1 && GlobalVariables.m03Score[3] == 1 && GlobalVariables.m03Score[4] == 1) {
                nextModule();
            } else {
                questionIncrement();
                setViewModule();
                GlobalVariables.m03QuestionNo = 2;
                Toast.makeText(getApplicationContext(), "Next Question", Toast.LENGTH_SHORT).show();
            }
        } else if (GlobalVariables.m03QuestionNo == 2) {
            nextModule();
        }
    }

    // Content left animation
    private void animateLeft() {
        YoYo.with(Techniques.SlideInLeft)
                .duration(500)
                .playOn(findViewById(R.id.content_m03));
    }

    // Content right animation
    private void animateRight() {
        YoYo.with(Techniques.SlideInRight)
                .duration(500)
                .playOn(findViewById(R.id.content_m03));
    }

    // Starts next selected Task
    private void nextModule() {
        if (GlobalVariables.modulesSelected[3]) {
            Intent intentModulesActivity = new Intent(Module03Activity.this, Module04Activity.class);
            Module03Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[4]) {
            Intent intentModulesActivity = new Intent(Module03Activity.this, Module05Activity.class);
            Module03Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[5]) {
            Intent intentModulesActivity = new Intent(Module03Activity.this, ResultsActivity.class);
            Module03Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[6]) {
            Intent intentModulesActivity = new Intent(Module03Activity.this, ResultsActivity.class);
            Module03Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[7]) {
            Intent intentModulesActivity = new Intent(Module03Activity.this, Module08Activity.class);
            Module03Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[8]) {
            Intent intentModulesActivity = new Intent(Module03Activity.this, Module09Activity.class);
            Module03Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[9]) {
            Intent intentModulesActivity = new Intent(Module03Activity.this, ResultsActivity.class);
            Module03Activity.this.startActivity(intentModulesActivity);
        } else {
            Intent intentModulesActivity = new Intent(Module03Activity.this, ResultsActivity.class);
            Module03Activity.this.startActivity(intentModulesActivity);
        }
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }
}
