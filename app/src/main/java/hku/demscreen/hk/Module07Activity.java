package hku.demscreen.hk;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Module07Activity extends AppCompatActivity {

    String Tag = "Module07Activity";
    Vibrator vibrator;

    // Main screen
    TextView questionNumber;
    ImageView scoreCorrect;
    ImageView info;

    // Question
    ImageView figure1;
    ImageView figure2;
    ImageView figure3;
    ImageView figure4;
    ImageView figure1Dot;
    ImageView figure2Dot;
    ImageView figure3Dot;
    ImageView figure4Dot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module07);

        //Main screen
        info = (ImageView) findViewById(R.id.info_m07);
        scoreCorrect = (ImageView) findViewById(R.id.score_correct_m07);
        questionNumber = (TextView) findViewById(R.id.question_number_m07);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Question
        figure1 = (ImageView) findViewById(R.id.m07_figure1);
        figure2 = (ImageView) findViewById(R.id.m07_figure2);
        figure3 = (ImageView) findViewById(R.id.m07_figure3);
        figure4 = (ImageView) findViewById(R.id.m07_figure4);
        figure1Dot = (ImageView) findViewById(R.id.m07_figure1_dot);
        figure2Dot = (ImageView) findViewById(R.id.m07_figure2_dot);
        figure3Dot = (ImageView) findViewById(R.id.m07_figure3_dot);
        figure4Dot = (ImageView) findViewById(R.id.m07_figure4_dot);

        //Main screen

        GlobalVariables.m07MovesCount = 0;
        Arrays.fill(GlobalVariables.m07TappedFigure, false);

        mainQuestion();
        setViewModule();

        figure1.setClickable(false);
        figure2.setClickable(false);
        figure3.setClickable(false);
        figure4.setClickable(false);
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

        figure1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(100);
                trueIfCorrectGuessFigure1();
                onTwoConsecutiveCorrects();
                mainFunctions();
            }
        });

        figure2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(100);
                trueIfCorrectGuessFigure2();
                onTwoConsecutiveCorrects();
                mainFunctions();
            }
        });

        figure3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(100);
                trueIfCorrectGuessFigure3();
                onTwoConsecutiveCorrects();
                mainFunctions();
            }
        });

        figure4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(100);
                trueIfCorrectGuessFigure4();
                onTwoConsecutiveCorrects();
                mainFunctions();
            }
        });

        scoreCorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainFunctions();
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SweetAlertDialog(Module07Activity.this)
                        .setTitleText(getString(R.string.rule_finding))
                        .setContentText(getString(R.string.java_task7_question))
                        .show();
            }
        });
    }

    // Sets current move's boolean answer to true for figure 1
    private void trueIfCorrectGuessFigure1() {
        if (GlobalVariables.m07MovesCount == 4 || GlobalVariables.m07MovesCount == 6 || GlobalVariables.m07MovesCount == 8 || GlobalVariables.m07MovesCount == 10 || GlobalVariables.m07MovesCount == 12 || GlobalVariables.m07MovesCount == 14 || GlobalVariables.m07MovesCount == 16 || GlobalVariables.m07MovesCount == 20 || GlobalVariables.m07MovesCount == 23 || GlobalVariables.m07MovesCount == 27 || GlobalVariables.m07MovesCount == 31) {
            GlobalVariables.m07TappedFigure[GlobalVariables.m07MovesCount - 1] = true;
        }
    }

    // Sets current move's boolean answer to true for figure 2
    private void trueIfCorrectGuessFigure2() {
        if (GlobalVariables.m07MovesCount == 5 || GlobalVariables.m07MovesCount == 7 || GlobalVariables.m07MovesCount == 9 || GlobalVariables.m07MovesCount == 17 || GlobalVariables.m07MovesCount == 21 || GlobalVariables.m07MovesCount == 26 || GlobalVariables.m07MovesCount == 30) {
            GlobalVariables.m07TappedFigure[GlobalVariables.m07MovesCount - 1] = true;
        }
    }

    // Sets current move's boolean answer to true for figure 3
    private void trueIfCorrectGuessFigure3() {
        if (GlobalVariables.m07MovesCount == 11 || GlobalVariables.m07MovesCount == 13 || GlobalVariables.m07MovesCount == 15 || GlobalVariables.m07MovesCount == 19 || GlobalVariables.m07MovesCount == 24 || GlobalVariables.m07MovesCount == 28) {
            GlobalVariables.m07TappedFigure[GlobalVariables.m07MovesCount - 1] = true;
        }
    }

    // Sets current move's boolean answer to true for figure 4
    private void trueIfCorrectGuessFigure4() {
        if (GlobalVariables.m07MovesCount == 18 || GlobalVariables.m07MovesCount == 22 || GlobalVariables.m07MovesCount == 25 || GlobalVariables.m07MovesCount == 29) {
            GlobalVariables.m07TappedFigure[GlobalVariables.m07MovesCount - 1] = true;
        }
    }

    // Sets score and question end on two consecutive correct responses
    private void onTwoConsecutiveCorrects() {
        if (GlobalVariables.m07QuestionNo == 1 && GlobalVariables.m07TappedFigure[GlobalVariables.m07MovesCount - 1] && GlobalVariables.m07TappedFigure[GlobalVariables.m07MovesCount - 2]) {
            GlobalVariables.m07MovesCount = 10;
            GlobalVariables.m07Score[0] = 1;
        } else if (GlobalVariables.m07QuestionNo == 2 && GlobalVariables.m07TappedFigure[GlobalVariables.m07MovesCount - 1] && GlobalVariables.m07TappedFigure[GlobalVariables.m07MovesCount - 2]) {
            GlobalVariables.m07MovesCount = 17;
            GlobalVariables.m07Score[1] = 1;
        } else if (GlobalVariables.m07QuestionNo == 3 && GlobalVariables.m07TappedFigure[GlobalVariables.m07MovesCount - 1] && GlobalVariables.m07TappedFigure[GlobalVariables.m07MovesCount - 2]) {
            GlobalVariables.m07MovesCount = 24;
            GlobalVariables.m07Score[2] = 1;
        } else if (GlobalVariables.m07QuestionNo == 4 && GlobalVariables.m07TappedFigure[GlobalVariables.m07MovesCount - 1] && GlobalVariables.m07TappedFigure[GlobalVariables.m07MovesCount - 2]) {
            GlobalVariables.m07MovesCount = 31;
            GlobalVariables.m07Score[3] = 1;
        }
        setViewModule();
    }

    // Initiates next move
    private void mainFunctions() {
        GlobalVariables.m07MovesCount++;
        if (GlobalVariables.m07MovesCount > 31) {
            nextModule();
        }
        figureNumberSet();
        setViewModule();
    }

    // Sets Current and Previous figure depending on the current move
    private void figureNumberSet() {
        if (GlobalVariables.m07MovesCount == 1) {
            GlobalVariables.m07CurrentFigure = 1;
            GlobalVariables.m07PreviousFigure = 0;
        } else if (GlobalVariables.m07MovesCount == 3 || GlobalVariables.m07MovesCount == 5 || GlobalVariables.m07MovesCount == 7 || GlobalVariables.m07MovesCount == 9 || GlobalVariables.m07MovesCount == 11 || GlobalVariables.m07MovesCount == 28) {
            GlobalVariables.m07CurrentFigure = 1;
            GlobalVariables.m07PreviousFigure = 2;
        } else if (GlobalVariables.m07MovesCount == 13 || GlobalVariables.m07MovesCount == 15 || GlobalVariables.m07MovesCount == 17 || GlobalVariables.m07MovesCount == 21) {
            GlobalVariables.m07CurrentFigure = 1;
            GlobalVariables.m07PreviousFigure = 3;
        } else if (GlobalVariables.m07MovesCount == 24) {
            GlobalVariables.m07CurrentFigure = 1;
            GlobalVariables.m07PreviousFigure = 4;
        } else if (GlobalVariables.m07MovesCount == 2 || GlobalVariables.m07MovesCount == 4 || GlobalVariables.m07MovesCount == 6 || GlobalVariables.m07MovesCount == 8 || GlobalVariables.m07MovesCount == 10 || GlobalVariables.m07MovesCount == 18 || GlobalVariables.m07MovesCount == 22) {
            GlobalVariables.m07CurrentFigure = 2;
            GlobalVariables.m07PreviousFigure = 1;
        } else if (GlobalVariables.m07MovesCount == 27 || GlobalVariables.m07MovesCount == 31) {
            GlobalVariables.m07CurrentFigure = 2;
            GlobalVariables.m07PreviousFigure = 4;
        } else if (GlobalVariables.m07MovesCount == 12 || GlobalVariables.m07MovesCount == 14 || GlobalVariables.m07MovesCount == 16 || GlobalVariables.m07MovesCount == 25 || GlobalVariables.m07MovesCount == 29) {
            GlobalVariables.m07CurrentFigure = 3;
            GlobalVariables.m07PreviousFigure = 1;
        } else if (GlobalVariables.m07MovesCount == 20) {
            GlobalVariables.m07CurrentFigure = 3;
            GlobalVariables.m07PreviousFigure = 4;
        } else if (GlobalVariables.m07MovesCount == 19 || GlobalVariables.m07MovesCount == 23) {
            GlobalVariables.m07CurrentFigure = 4;
            GlobalVariables.m07PreviousFigure = 2;
        } else if (GlobalVariables.m07MovesCount == 26 || GlobalVariables.m07MovesCount == 30) {
            GlobalVariables.m07CurrentFigure = 4;
            GlobalVariables.m07PreviousFigure = 3;
        }

        questionNumberSet();
    }

    // Sets Question number depending on the current move
    private void questionNumberSet() {
        if (GlobalVariables.m07MovesCount >= 1 && GlobalVariables.m07MovesCount <= 3) {
            GlobalVariables.m07QuestionNo = 0;
        } else if (GlobalVariables.m07MovesCount >= 4 && GlobalVariables.m07MovesCount <= 10) {
            GlobalVariables.m07QuestionNo = 1;
        } else if (GlobalVariables.m07MovesCount >= 11 && GlobalVariables.m07MovesCount <= 17) {
            GlobalVariables.m07QuestionNo = 2;
        } else if (GlobalVariables.m07MovesCount >= 18 && GlobalVariables.m07MovesCount <= 24) {
            GlobalVariables.m07QuestionNo = 3;
        } else if (GlobalVariables.m07MovesCount >= 25 && GlobalVariables.m07MovesCount <= 31) {
            GlobalVariables.m07QuestionNo = 4;
        }
    }

    // Sets views for all questions
    private void setViewModule() {
        if (GlobalVariables.m07QuestionNo == 0) {
            scoreCorrect.setVisibility(View.VISIBLE);
            figure1.setClickable(false);
            figure2.setClickable(false);
            figure3.setClickable(false);
            figure4.setClickable(false);
        } else {
            scoreCorrect.setVisibility(View.INVISIBLE);
            figure1.setClickable(true);
            figure2.setClickable(true);
            figure3.setClickable(true);
            figure4.setClickable(true);
        }

        if (GlobalVariables.m07QuestionNo == 0) {
            questionNumber.setText("0/4");
        } else if (GlobalVariables.m07QuestionNo == 1) {
            questionNumber.setText("1/4");
        } else if (GlobalVariables.m07QuestionNo == 2) {
            questionNumber.setText("2/4");
        } else if (GlobalVariables.m07QuestionNo == 3) {
            questionNumber.setText("3/4");
        } else if (GlobalVariables.m07QuestionNo == 4) {
            questionNumber.setText("4/4");
        }
        figuresHighlight();
        figuresRedDot();
    }

    // Makes Red dot visible for current figure
    private void figuresRedDot() {
        if (GlobalVariables.m07CurrentFigure == 1) {
            figure1Dot.setVisibility(View.VISIBLE);
            figure2Dot.setVisibility(View.GONE);
            figure3Dot.setVisibility(View.GONE);
            figure4Dot.setVisibility(View.GONE);
        } else if (GlobalVariables.m07CurrentFigure == 2) {
            figure1Dot.setVisibility(View.GONE);
            figure2Dot.setVisibility(View.VISIBLE);
            figure3Dot.setVisibility(View.GONE);
            figure4Dot.setVisibility(View.GONE);
        } else if (GlobalVariables.m07CurrentFigure == 3) {
            figure1Dot.setVisibility(View.GONE);
            figure2Dot.setVisibility(View.GONE);
            figure3Dot.setVisibility(View.VISIBLE);
            figure4Dot.setVisibility(View.GONE);
        } else if (GlobalVariables.m07CurrentFigure == 4) {
            figure1Dot.setVisibility(View.GONE);
            figure2Dot.setVisibility(View.GONE);
            figure3Dot.setVisibility(View.GONE);
            figure4Dot.setVisibility(View.VISIBLE);
        }
    }

    // Changes color for previous figure
    private void figuresHighlight() {
        if (GlobalVariables.m07PreviousFigure == 0) {
            figure1.setColorFilter(Color.GRAY);
            figure2.setColorFilter(Color.GRAY);
            figure3.setColorFilter(Color.GRAY);
            figure4.setColorFilter(Color.GRAY);
        } else if (GlobalVariables.m07PreviousFigure == 1) {
            figure1.setColorFilter(Color.LTGRAY);
            figure2.setColorFilter(Color.GRAY);
            figure3.setColorFilter(Color.GRAY);
            figure4.setColorFilter(Color.GRAY);
        } else if (GlobalVariables.m07PreviousFigure == 2) {
            figure1.setColorFilter(Color.GRAY);
            figure2.setColorFilter(Color.LTGRAY);
            figure3.setColorFilter(Color.GRAY);
            figure4.setColorFilter(Color.GRAY);
        } else if (GlobalVariables.m07PreviousFigure == 3) {
            figure1.setColorFilter(Color.GRAY);
            figure2.setColorFilter(Color.GRAY);
            figure3.setColorFilter(Color.LTGRAY);
            figure4.setColorFilter(Color.GRAY);
        } else if (GlobalVariables.m07PreviousFigure == 4) {
            figure1.setColorFilter(Color.GRAY);
            figure2.setColorFilter(Color.GRAY);
            figure3.setColorFilter(Color.GRAY);
            figure4.setColorFilter(Color.LTGRAY);
        }
    }

    // Starts next selected Task
    private void nextModule() {
        if (GlobalVariables.modulesSelected[7]) {
            Intent intentModulesActivity = new Intent(Module07Activity.this, Module08Activity.class);
            Module07Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[8]) {
            Intent intentModulesActivity = new Intent(Module07Activity.this, Module09Activity.class);
            Module07Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[9]) {
            Intent intentModulesActivity = new Intent(Module07Activity.this, Module10Activity.class);
            Module07Activity.this.startActivity(intentModulesActivity);
        } else {
            Intent intentModulesActivity = new Intent(Module07Activity.this, ResultsActivity.class);
            Module07Activity.this.startActivity(intentModulesActivity);
        }
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }
}
