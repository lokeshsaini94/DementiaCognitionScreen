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

public class Module09Activity extends AppCompatActivity {

    String Tag = "Module09Activity";
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
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module09);

        //Main screen
        info = (ImageView) findViewById(R.id.info_m09);
        scoreCorrect = (ImageView) findViewById(R.id.score_correct_m09);
        questionNumber = (TextView) findViewById(R.id.question_number_m09);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Question
        previousQuestion = (ImageView) findViewById(R.id.m09_previous_question);
        nextQuestion = (ImageView) findViewById(R.id.m09_next_question);

        cardView1 = (CardView) findViewById(R.id.m09_card1);
        cardView2 = (CardView) findViewById(R.id.m09_card2);
        cardView3 = (CardView) findViewById(R.id.m09_card3);
        cardView4 = (CardView) findViewById(R.id.m09_card4);

        imageView1 = (ImageView) findViewById(R.id.m09_image1);
        imageView2 = (ImageView) findViewById(R.id.m09_image2);
        imageView3 = (ImageView) findViewById(R.id.m09_image3);
        imageView4 = (ImageView) findViewById(R.id.m09_image4);

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
                if (GlobalVariables.m09QuestionNo == 1) {
                    GlobalVariables.m09Score[0] = 1;
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
                if (GlobalVariables.m09QuestionNo == 3) {
                    GlobalVariables.m09Score[2] = 1;
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
                if (GlobalVariables.m09QuestionNo == 2) {
                    GlobalVariables.m09Score[1] = 1;
                }
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
            }
        });

        previousQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GlobalVariables.m09QuestionNo == 2) {
                    questionDecrement();
                } else if (GlobalVariables.m09QuestionNo == 3) {
                    questionDecrement();
                }
                setViewModule();
            }
        });

        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GlobalVariables.m09QuestionNo == 1) {
                    questionIncrement();
                } else if (GlobalVariables.m09QuestionNo == 2) {
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

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SweetAlertDialog(Module09Activity.this)
                        .setTitleText(getString(R.string.episodic_memory))
                        .setContentText("In these 4 pictures, which one do you remember from before?")
                        .show();

            }
        });
    }

    // Increments question number and animates content view
    private void questionIncrement() {
        GlobalVariables.m09QuestionNo = GlobalVariables.m09QuestionNo + 1;
        animateRight();
    }

    // Decrements question number and animates content view
    private void questionDecrement() {
        GlobalVariables.m09QuestionNo = GlobalVariables.m09QuestionNo - 1;
        animateLeft();
    }

    // Sets views for all questions
    private void setViewModule() {
        imageView1.setAlpha(1f);
        imageView2.setAlpha(1f);
        imageView3.setAlpha(1f);
        imageView4.setAlpha(1f);
        if (GlobalVariables.m09QuestionNo == 1) {
            previousQuestion.setVisibility(View.GONE);
            nextQuestion.setVisibility(View.VISIBLE);
            imageView1.setImageResource(R.drawable.m09q1_1);
            imageView2.setImageResource(R.drawable.m09q1_2);
            imageView3.setImageResource(R.drawable.m09q1_3);
            imageView4.setImageResource(R.drawable.m09q1_4);
            questionNumber.setText("1/3");
        } else if (GlobalVariables.m09QuestionNo == 2) {
            previousQuestion.setVisibility(View.VISIBLE);
            nextQuestion.setVisibility(View.VISIBLE);
            imageView1.setImageResource(R.drawable.m09q2_1);
            imageView2.setImageResource(R.drawable.m09q2_2);
            imageView3.setImageResource(R.drawable.m09q2_3);
            imageView4.setImageResource(R.drawable.m09q2_4);
            questionNumber.setText("2/3");
        } else if (GlobalVariables.m09QuestionNo == 3) {
            previousQuestion.setVisibility(View.VISIBLE);
            nextQuestion.setVisibility(View.GONE);
            imageView1.setImageResource(R.drawable.m09q3_1);
            imageView2.setImageResource(R.drawable.m09q3_2);
            imageView3.setImageResource(R.drawable.m09q3_3);
            imageView4.setImageResource(R.drawable.m09q3_4);
            questionNumber.setText("3/3");
        }
    }

    // On correct button
    private void onCorrect() {
        if (GlobalVariables.m09QuestionNo == 1) {
            Toast.makeText(getApplicationContext(), "Next Question", Toast.LENGTH_SHORT).show();
            questionIncrement();
        } else if (GlobalVariables.m09QuestionNo == 2) {
            Toast.makeText(getApplicationContext(), "Next Question", Toast.LENGTH_SHORT).show();
            questionIncrement();
        } else if (GlobalVariables.m09QuestionNo == 3) {
            nextModule();
        }
        setViewModule();
    }

    // Content left animation
    private void animateLeft() {
        YoYo.with(Techniques.SlideInLeft)
                .duration(500)
                .playOn(findViewById(R.id.content_m09));
    }

    // Content right animation
    private void animateRight() {
        YoYo.with(Techniques.SlideInRight)
                .duration(500)
                .playOn(findViewById(R.id.content_m09));
    }

    // Starts next selected Task
    private void nextModule() {
        if (GlobalVariables.modulesSelected[9]) {
            Intent intentModulesActivity = new Intent(Module09Activity.this, Module10Activity.class);
            Module09Activity.this.startActivity(intentModulesActivity);
        } else {
            Intent intentModulesActivity = new Intent(Module09Activity.this, ResultsActivity.class);
            Module09Activity.this.startActivity(intentModulesActivity);
        }
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }
}
