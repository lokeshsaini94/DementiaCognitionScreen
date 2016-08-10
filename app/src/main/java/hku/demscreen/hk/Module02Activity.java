package hku.demscreen.hk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
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

public class Module02Activity extends AppCompatActivity {

    String Tag = "Module02Activity";
    Vibrator vibrator;

    // Main screen
    TextView questionNumber;
    ImageView scoreCorrect;
    ImageView info;

    // Question 02
    ImageView m02PreviousQuestion;
    ImageView m02NextQuestion;
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
        info = (ImageView) findViewById(R.id.info02);
        scoreCorrect = (ImageView) findViewById(R.id.score_correct_m02);
        questionNumber = (TextView) findViewById(R.id.question_number_m02);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Question 01
        m02PreviousQuestion = (ImageView) findViewById(R.id.m02_previous_question);
        m02NextQuestion = (ImageView) findViewById(R.id.m02_next_question);

        cardView1 = (CardView) findViewById(R.id.m02_card1);
        cardView2 = (CardView) findViewById(R.id.m02_card2);
        cardView3 = (CardView) findViewById(R.id.m02_card3);
        cardView4 = (CardView) findViewById(R.id.m02_card4);

        imageView1 = (ImageView) findViewById(R.id.m02_image1);
        imageView2 = (ImageView) findViewById(R.id.m02_image2);
        imageView3 = (ImageView) findViewById(R.id.m02_image3);
        imageView4 = (ImageView) findViewById(R.id.m02_image4);

        //Main screen

        setViewModule02();

        scoreCorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m02OnCorrect();
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GlobalVariables.m02QuestionNo == 1) {
                    new SweetAlertDialog(Module02Activity.this)
                            .setTitleText("Picture Pointing")
                            .setContentText("In these 4 pictures, which one is the goose?")
                            .show();
                } else if (GlobalVariables.m02QuestionNo == 2) {
                    new SweetAlertDialog(Module02Activity.this)
                            .setTitleText("Picture Pointing")
                            .setContentText("In these 4 pictures, which one is the pomegranate?")
                            .show();
                } else if (GlobalVariables.m02QuestionNo == 3) {
                    new SweetAlertDialog(Module02Activity.this)
                            .setTitleText("Picture Pointing")
                            .setContentText("In these 4 pictures, which one is the bird?")
                            .show();
                } else if (GlobalVariables.m02QuestionNo == 4) {
                    new SweetAlertDialog(Module02Activity.this)
                            .setTitleText("Picture Pointing")
                            .setContentText("In these 4 pictures, which one is the vegetable?")
                            .show();
                } else {

                }
            }
        });

        mainQuestion02();
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
            Toast.makeText(getApplicationContext(), "q1= " + GlobalVariables.m02Score[0] + " q2= " + GlobalVariables.m02Score[1] + " q3= " + GlobalVariables.m02Score[2] + " q4= " + GlobalVariables.m02Score[3], Toast.LENGTH_SHORT).show();
            nextModule();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Question 01
    private void mainQuestion02() {

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

        m02PreviousQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GlobalVariables.m02QuestionNo == 1) {
                    Log.d(Tag, "Error: previous button tapped on m02q1 (Module02Activity)");
                } else if (GlobalVariables.m02QuestionNo == 2) {
                    question02Decrement();
                } else if (GlobalVariables.m02QuestionNo == 3) {
                    question02Decrement();
                } else if (GlobalVariables.m02QuestionNo == 4) {
                    question02Decrement();
                } else {
                    Log.d(Tag, "Error: Something went wrong (Module02Activity.m02PreviousQuestion.setOnClickListener)");
                }
                setViewModule02();
            }
        });

        m02NextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GlobalVariables.m02QuestionNo == 1) {
                    question02Increment();
                } else if (GlobalVariables.m02QuestionNo == 2) {
                    question02Increment();
                } else if (GlobalVariables.m02QuestionNo == 3) {
                    question02Increment();
                } else if (GlobalVariables.m02QuestionNo == 4) {
                    Log.d(Tag, "Error: next button tapped on m02q4 (Module02Activity)");
                } else {
                    Log.d(Tag, "Error: Something went wrong (Module02Activity.m02NextQuestion.setOnClickListener)");
                }
                setViewModule02();
            }
        });
    }

    private void question02Increment() {
        GlobalVariables.m02QuestionNo = GlobalVariables.m02QuestionNo + 1;
        m02AnimateRight();
    }

    private void question02Decrement() {
        GlobalVariables.m02QuestionNo = GlobalVariables.m02QuestionNo - 1;
        m02AnimateLeft();
    }

    private void setViewModule02() {
        imageView1.setAlpha(1f);
        imageView2.setAlpha(1f);
        imageView3.setAlpha(1f);
        imageView4.setAlpha(1f);
        if (GlobalVariables.m02QuestionNo == 1) {
            m02PreviousQuestion.setVisibility(View.GONE);
            m02NextQuestion.setVisibility(View.VISIBLE);
            imageView1.setImageResource(R.drawable.m02q1_1);
            imageView2.setImageResource(R.drawable.m02q1_2);
            imageView3.setImageResource(R.drawable.m02q1_3);
            imageView4.setImageResource(R.drawable.m02q1_4);
            questionNumber.setText("1/4");
        } else if (GlobalVariables.m02QuestionNo == 2) {
            m02PreviousQuestion.setVisibility(View.VISIBLE);
            m02NextQuestion.setVisibility(View.VISIBLE);
            imageView1.setImageResource(R.drawable.m02q2_1);
            imageView2.setImageResource(R.drawable.m02q2_2);
            imageView3.setImageResource(R.drawable.m02q2_3);
            imageView4.setImageResource(R.drawable.m02q2_4);
            questionNumber.setText("2/4");
        } else if (GlobalVariables.m02QuestionNo == 3) {
            m02PreviousQuestion.setVisibility(View.VISIBLE);
            m02NextQuestion.setVisibility(View.VISIBLE);
            imageView1.setImageResource(R.drawable.m02q3_1);
            imageView2.setImageResource(R.drawable.m02q3_2);
            imageView3.setImageResource(R.drawable.m02q3_3);
            imageView4.setImageResource(R.drawable.m02q3_4);
            questionNumber.setText("3/4");
        } else if (GlobalVariables.m02QuestionNo == 4) {
            m02PreviousQuestion.setVisibility(View.VISIBLE);
            m02NextQuestion.setVisibility(View.GONE);
            imageView1.setImageResource(R.drawable.m02q4_1);
            imageView2.setImageResource(R.drawable.m02q4_2);
            imageView3.setImageResource(R.drawable.m02q4_3);
            imageView4.setImageResource(R.drawable.m02q4_4);
            questionNumber.setText("4/4");
        } else {
            Toast.makeText(getApplicationContext(), "Something went wrong (Module02Activity.setView)", Toast.LENGTH_SHORT).show();
            GlobalVariables.m02QuestionNo = 1;
            setViewModule02();
        }
    }

    private void m02OnCorrect() {
        if (GlobalVariables.m02QuestionNo == 1) {
            Toast.makeText(getApplicationContext(), "Next Question", Toast.LENGTH_SHORT).show();
            question02Increment();
        } else if (GlobalVariables.m02QuestionNo == 2) {
            Toast.makeText(getApplicationContext(), "Next Question", Toast.LENGTH_SHORT).show();
            question02Increment();
        } else if (GlobalVariables.m02QuestionNo == 3) {
            Toast.makeText(getApplicationContext(), "Next Question", Toast.LENGTH_SHORT).show();
            question02Increment();
        } else if (GlobalVariables.m02QuestionNo == 4) {
            Toast.makeText(getApplicationContext(), "Next Task", Toast.LENGTH_SHORT).show();
            nextModule();
        } else {
            Log.d(Tag, "Error: Something went wrong (Module02Activity.m02OnCorrect)");
        }
        setViewModule02();
    }

    private void m02AnimateLeft() {
        YoYo.with(Techniques.SlideInLeft)
                .duration(500)
                .playOn(findViewById(R.id.content_m02));
    }

    private void m02AnimateRight() {
        YoYo.with(Techniques.SlideInRight)
                .duration(500)
                .playOn(findViewById(R.id.content_m02));
    }

    private void nextModule() {
        if (GlobalVariables.modulesSelected[2]) {
            Intent intentModulesActivity = new Intent(Module02Activity.this, Module03Activity.class);
            Module02Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[3]) {
            Intent intentModulesActivity = new Intent(Module02Activity.this, Module04Activity.class);
            Module02Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[4]) {
            Intent intentModulesActivity = new Intent(Module02Activity.this, ResultsActivity.class);
            Module02Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[5]) {
            Intent intentModulesActivity = new Intent(Module02Activity.this, ResultsActivity.class);
            Module02Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[6]) {
            Intent intentModulesActivity = new Intent(Module02Activity.this, ResultsActivity.class);
            Module02Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[7]) {
            Intent intentModulesActivity = new Intent(Module02Activity.this, ResultsActivity.class);
            Module02Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[8]) {
            Intent intentModulesActivity = new Intent(Module02Activity.this, ResultsActivity.class);
            Module02Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[9]) {
            Intent intentModulesActivity = new Intent(Module02Activity.this, ResultsActivity.class);
            Module02Activity.this.startActivity(intentModulesActivity);
        } else {
            Intent intentModulesActivity = new Intent(Module02Activity.this, ResultsActivity.class);
            Module02Activity.this.startActivity(intentModulesActivity);
        }
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }
}
