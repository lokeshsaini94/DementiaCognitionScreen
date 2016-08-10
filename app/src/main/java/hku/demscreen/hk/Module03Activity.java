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

    // Question 02
    ImageView m03PreviousQuestion;
    ImageView m03NextQuestion;
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
        info = (ImageView) findViewById(R.id.info03);
        scoreCorrect = (ImageView) findViewById(R.id.score_correct_m03);
        questionNumber = (TextView) findViewById(R.id.question_number_m03);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Question 01
        m03PreviousQuestion = (ImageView) findViewById(R.id.m03_previous_question);
        m03NextQuestion = (ImageView) findViewById(R.id.m03_next_question);

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

        resetItems();

        //Main screen

        setViewModule03();

        scoreCorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m03OnCorrect();
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SweetAlertDialog(Module03Activity.this)
                        .setTitleText("Word Learning")
                        .setContentText("Remember these 5 words and repeat them.")
                        .show();
            }
        });

        mainQuestion03();
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
            Toast.makeText(getApplicationContext(), "q1= " + GlobalVariables.m03Score[0] + " q2= " + GlobalVariables.m03Score[1] + " q3= " + GlobalVariables.m03Score[2] + " q4= " + GlobalVariables.m03Score[3] + " q5= " + GlobalVariables.m03Score[4], Toast.LENGTH_SHORT).show();
            nextModule();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Question 01
    private void mainQuestion03() {

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(100);
                if (GlobalVariables.m03Score[0] == 0) {
                    GlobalVariables.m03Score[0] = 1;
                    cardTick1.setVisibility(View.VISIBLE);
                    cardView1.setCardBackgroundColor(getResources().getColor(R.color.white));
                } else {
                    GlobalVariables.m03Score[0] = 0;
                    cardTick1.setVisibility(View.GONE);
                    cardView1.setCardBackgroundColor(getResources().getColor(R.color.light_grey));
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
                    cardView2.setCardBackgroundColor(getResources().getColor(R.color.white));
                } else {
                    GlobalVariables.m03Score[1] = 0;
                    cardTick2.setVisibility(View.GONE);
                    cardView2.setCardBackgroundColor(getResources().getColor(R.color.light_grey));
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
                    cardView3.setCardBackgroundColor(getResources().getColor(R.color.white));
                } else {
                    GlobalVariables.m03Score[2] = 0;
                    cardTick3.setVisibility(View.GONE);
                    cardView3.setCardBackgroundColor(getResources().getColor(R.color.light_grey));
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
                    cardView4.setCardBackgroundColor(getResources().getColor(R.color.white));
                } else {
                    GlobalVariables.m03Score[3] = 0;
                    cardTick4.setVisibility(View.GONE);
                    cardView4.setCardBackgroundColor(getResources().getColor(R.color.light_grey));
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
                    cardView5.setCardBackgroundColor(getResources().getColor(R.color.white));
                } else {
                    GlobalVariables.m03Score[4] = 0;
                    cardTick5.setVisibility(View.GONE);
                    cardView5.setCardBackgroundColor(getResources().getColor(R.color.light_grey));
                }
                if (GlobalVariables.m03QuestionNo == 1) {
                    hideNextIfScore5();
                }
            }
        });

        m03PreviousQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                question03Decrement();
                setViewModule03();
            }
        });

        m03NextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                question03Increment();
                setViewModule03();
            }
        });
    }

    private void hideNextIfScore5() {
        if (GlobalVariables.m03Score[0] == 1 && GlobalVariables.m03Score[1] == 1 && GlobalVariables.m03Score[2] == 1 && GlobalVariables.m03Score[3] == 1 && GlobalVariables.m03Score[4] == 1) {
            m03NextQuestion.setVisibility(View.GONE);
            questionNumber.setText("1/1");
        } else {
            m03NextQuestion.setVisibility(View.VISIBLE);
            questionNumber.setText("1/2");
        }
    }

    private void resetItems() {
        GlobalVariables.m03Score[0] = 0;
        GlobalVariables.m03Score[1] = 0;
        GlobalVariables.m03Score[2] = 0;
        GlobalVariables.m03Score[3] = 0;
        GlobalVariables.m03Score[4] = 0;
        cardTick1.setVisibility(View.GONE);
        cardView1.setCardBackgroundColor(getResources().getColor(R.color.light_grey));
        cardTick2.setVisibility(View.GONE);
        cardView2.setCardBackgroundColor(getResources().getColor(R.color.light_grey));
        cardTick3.setVisibility(View.GONE);
        cardView3.setCardBackgroundColor(getResources().getColor(R.color.light_grey));
        cardTick4.setVisibility(View.GONE);
        cardView4.setCardBackgroundColor(getResources().getColor(R.color.light_grey));
        cardTick5.setVisibility(View.GONE);
        cardView5.setCardBackgroundColor(getResources().getColor(R.color.light_grey));
    }

    private void question03Increment() {
        m03PreviousQuestion.setVisibility(View.VISIBLE);
        m03NextQuestion.setVisibility(View.GONE);
        GlobalVariables.m03QuestionNo = 2;
        resetItems();
        m03AnimateRight();
    }

    private void question03Decrement() {
        m03PreviousQuestion.setVisibility(View.GONE);
        m03NextQuestion.setVisibility(View.VISIBLE);
        GlobalVariables.m03QuestionNo = 1;
        resetItems();
        m03AnimateLeft();
    }

    private void setViewModule03() {
        if (GlobalVariables.m02QuestionNo == 1) {
            questionNumber.setText("1/2");
            question03Decrement();
        } else if (GlobalVariables.m02QuestionNo == 2) {
            questionNumber.setText("2/2");
            question03Increment();
        } else {
        }
    }

    private void m03OnCorrect() {
        if (GlobalVariables.m03QuestionNo == 1) {
            if (GlobalVariables.m03Score[0] == 1 && GlobalVariables.m03Score[1] == 1 && GlobalVariables.m03Score[2] == 1 && GlobalVariables.m03Score[3] == 1 && GlobalVariables.m03Score[4] == 1) {
                nextModule();
                Toast.makeText(getApplicationContext(), "Next Task", Toast.LENGTH_SHORT).show();
            } else {
                question03Increment();
                setViewModule03();
                Toast.makeText(getApplicationContext(), "Next Question", Toast.LENGTH_SHORT).show();
            }
        } else if (GlobalVariables.m03QuestionNo == 2) {
            nextModule();
            Toast.makeText(getApplicationContext(), "Next Task", Toast.LENGTH_SHORT).show();
        }
    }

    private void m03AnimateLeft() {
        YoYo.with(Techniques.SlideInLeft)
                .duration(500)
                .playOn(findViewById(R.id.content_m03));
    }

    private void m03AnimateRight() {
        YoYo.with(Techniques.SlideInRight)
                .duration(500)
                .playOn(findViewById(R.id.content_m03));
    }

    private void nextModule() {
        if (GlobalVariables.modulesSelected[3]) {
            Intent intentModulesActivity = new Intent(Module03Activity.this, Module04Activity.class);
            Module03Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[4]) {
            Intent intentModulesActivity = new Intent(Module03Activity.this, ResultsActivity.class);
            Module03Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[5]) {
            Intent intentModulesActivity = new Intent(Module03Activity.this, ResultsActivity.class);
            Module03Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[6]) {
            Intent intentModulesActivity = new Intent(Module03Activity.this, ResultsActivity.class);
            Module03Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[7]) {
            Intent intentModulesActivity = new Intent(Module03Activity.this, ResultsActivity.class);
            Module03Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[8]) {
            Intent intentModulesActivity = new Intent(Module03Activity.this, ResultsActivity.class);
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
