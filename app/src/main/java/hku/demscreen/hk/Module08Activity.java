package hku.demscreen.hk;

import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
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

import java.io.IOException;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Module08Activity extends AppCompatActivity {

    private final String Tag = Module08Activity.class.getSimpleName();
    private Vibrator vibrator;

    private String fileName = "09 - Task 08 Question 1";

    // Main screen
    private TextView questionNumber;
    private ImageView scoreCorrect, info;

    // Question
    private ImageView previousQuestion;
    private CardView cardView1, cardView2, cardView3, cardView4, cardView5;
    private ImageView cardTick1, cardTick2, cardTick3, cardTick4, cardTick5;

    private CardView cardViewMCQ1, cardViewMCQ2, cardViewMCQ3, cardViewMCQ4;
    private ImageView cardTickMCQ1, cardTickMCQ2, cardTickMCQ3, cardTickMCQ4;
    private TextView textViewMCQ1, textViewMCQ2, textViewMCQ3, textViewMCQ4;

    private View mainLayout, mcqLayout;
    private MediaRecorder myAudioRecorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module08);

        //Main screen
        info = (ImageView) findViewById(R.id.info_m08);
        scoreCorrect = (ImageView) findViewById(R.id.score_correct_m08);
        questionNumber = (TextView) findViewById(R.id.question_number_m08);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Question
        previousQuestion = (ImageView) findViewById(R.id.m08_previous_question);

        cardView1 = (CardView) findViewById(R.id.m08_card1);
        cardView2 = (CardView) findViewById(R.id.m08_card2);
        cardView3 = (CardView) findViewById(R.id.m08_card3);
        cardView4 = (CardView) findViewById(R.id.m08_card4);
        cardView5 = (CardView) findViewById(R.id.m08_card5);
        cardTick1 = (ImageView) findViewById(R.id.m08_card1_tick);
        cardTick2 = (ImageView) findViewById(R.id.m08_card2_tick);
        cardTick3 = (ImageView) findViewById(R.id.m08_card3_tick);
        cardTick4 = (ImageView) findViewById(R.id.m08_card4_tick);
        cardTick5 = (ImageView) findViewById(R.id.m08_card5_tick);

        cardViewMCQ1 = (CardView) findViewById(R.id.m08_q_card1);
        cardViewMCQ2 = (CardView) findViewById(R.id.m08_q_card2);
        cardViewMCQ3 = (CardView) findViewById(R.id.m08_q_card3);
        cardViewMCQ4 = (CardView) findViewById(R.id.m08_q_card4);

        cardTickMCQ1 = (ImageView) findViewById(R.id.m08_q_card1_tick);
        cardTickMCQ2 = (ImageView) findViewById(R.id.m08_q_card2_tick);
        cardTickMCQ3 = (ImageView) findViewById(R.id.m08_q_card3_tick);
        cardTickMCQ4 = (ImageView) findViewById(R.id.m08_q_card4_tick);

        textViewMCQ1 = (TextView) findViewById(R.id.m08_q_text1);
        textViewMCQ2 = (TextView) findViewById(R.id.m08_q_text2);
        textViewMCQ3 = (TextView) findViewById(R.id.m08_q_text3);
        textViewMCQ4 = (TextView) findViewById(R.id.m08_q_text4);


        mainLayout = findViewById(R.id.m08_5item_layout);
        mcqLayout = findViewById(R.id.m08_4item_layout);

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
                vibrator.vibrate(100);
                if (GlobalVariables.m08ScoreQ1[0] == 0) {
                    GlobalVariables.m08ScoreQ1[0] = 1;
                    cardTick1.setVisibility(View.VISIBLE);
                    cardView1.setCardBackgroundColor(GlobalVariables.whiteColorValue);
                } else {
                    GlobalVariables.m08ScoreQ1[0] = 0;
                    cardTick1.setVisibility(View.GONE);
                    cardView1.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);
                }
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(100);
                if (GlobalVariables.m08ScoreQ1[1] == 0) {
                    GlobalVariables.m08ScoreQ1[1] = 1;
                    cardTick2.setVisibility(View.VISIBLE);
                    cardView2.setCardBackgroundColor(GlobalVariables.whiteColorValue);
                } else {
                    GlobalVariables.m08ScoreQ1[1] = 0;
                    cardTick2.setVisibility(View.GONE);
                    cardView2.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);
                }
            }
        });

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(100);
                if (GlobalVariables.m08ScoreQ1[2] == 0) {
                    GlobalVariables.m08ScoreQ1[2] = 1;
                    cardTick3.setVisibility(View.VISIBLE);
                    cardView3.setCardBackgroundColor(GlobalVariables.whiteColorValue);
                } else {
                    GlobalVariables.m08ScoreQ1[2] = 0;
                    cardTick3.setVisibility(View.GONE);
                    cardView3.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);
                }
            }
        });

        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(100);
                if (GlobalVariables.m08ScoreQ1[3] == 0) {
                    GlobalVariables.m08ScoreQ1[3] = 1;
                    cardTick4.setVisibility(View.VISIBLE);
                    cardView4.setCardBackgroundColor(GlobalVariables.whiteColorValue);
                } else {
                    GlobalVariables.m08ScoreQ1[3] = 0;
                    cardTick4.setVisibility(View.GONE);
                    cardView4.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);
                }
            }
        });

        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(100);
                if (GlobalVariables.m08ScoreQ1[4] == 0) {
                    GlobalVariables.m08ScoreQ1[4] = 1;
                    cardTick5.setVisibility(View.VISIBLE);
                    cardView5.setCardBackgroundColor(GlobalVariables.whiteColorValue);
                } else {
                    GlobalVariables.m08ScoreQ1[4] = 0;
                    cardTick5.setVisibility(View.GONE);
                    cardView5.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);
                }
            }
        });

        cardViewMCQ1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(100);
                if (GlobalVariables.m08MCQcardChecked[0] == 0) {
                    GlobalVariables.m08MCQcardChecked[0] = 1;
                    cardTickMCQ1.setVisibility(View.VISIBLE);
                    cardViewMCQ1.setCardBackgroundColor(GlobalVariables.whiteColorValue);

                    GlobalVariables.m08MCQcardChecked[1] = 0;
                    cardTickMCQ2.setVisibility(View.GONE);
                    cardViewMCQ2.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);
                    GlobalVariables.m08MCQcardChecked[2] = 0;
                    cardTickMCQ3.setVisibility(View.GONE);
                    cardViewMCQ3.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);
                    GlobalVariables.m08MCQcardChecked[3] = 0;
                    cardTickMCQ4.setVisibility(View.GONE);
                    cardViewMCQ4.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);

                    if (GlobalVariables.m08CurrentMCQNo == 3) {
                        GlobalVariables.m08ScoreQ2[2] = 1;
                    }
                } else {
                    GlobalVariables.m08MCQcardChecked[0] = 0;
                    cardTickMCQ1.setVisibility(View.GONE);
                    cardViewMCQ1.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);

                    if (GlobalVariables.m08CurrentMCQNo == 3) {
                        GlobalVariables.m08ScoreQ2[2] = 0;
                    }
                }
            }
        });

        cardViewMCQ2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(100);
                if (GlobalVariables.m08MCQcardChecked[1] == 0) {
                    GlobalVariables.m08MCQcardChecked[1] = 1;
                    cardTickMCQ2.setVisibility(View.VISIBLE);
                    cardViewMCQ2.setCardBackgroundColor(GlobalVariables.whiteColorValue);

                    GlobalVariables.m08MCQcardChecked[0] = 0;
                    cardTickMCQ1.setVisibility(View.GONE);
                    cardViewMCQ1.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);
                    GlobalVariables.m08MCQcardChecked[2] = 0;
                    cardTickMCQ3.setVisibility(View.GONE);
                    cardViewMCQ3.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);
                    GlobalVariables.m08MCQcardChecked[3] = 0;
                    cardTickMCQ4.setVisibility(View.GONE);
                    cardViewMCQ4.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);

                    if (GlobalVariables.m08CurrentMCQNo == 4) {
                        GlobalVariables.m08ScoreQ2[3] = 1;
                    }

                    if (GlobalVariables.m08CurrentMCQNo == 5) {
                        GlobalVariables.m08ScoreQ2[4] = 1;
                    }
                } else {
                    GlobalVariables.m08MCQcardChecked[1] = 0;
                    cardTickMCQ2.setVisibility(View.GONE);
                    cardViewMCQ2.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);

                    if (GlobalVariables.m08CurrentMCQNo == 4) {
                        GlobalVariables.m08ScoreQ2[3] = 0;
                    }

                    if (GlobalVariables.m08CurrentMCQNo == 5) {
                        GlobalVariables.m08ScoreQ2[4] = 0;
                    }
                }
            }
        });

        cardViewMCQ3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(100);
                if (GlobalVariables.m08MCQcardChecked[2] == 0) {
                    GlobalVariables.m08MCQcardChecked[2] = 1;
                    cardTickMCQ3.setVisibility(View.VISIBLE);
                    cardViewMCQ3.setCardBackgroundColor(GlobalVariables.whiteColorValue);

                    GlobalVariables.m08MCQcardChecked[1] = 0;
                    cardTickMCQ2.setVisibility(View.GONE);
                    cardViewMCQ2.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);
                    GlobalVariables.m08MCQcardChecked[0] = 0;
                    cardTickMCQ1.setVisibility(View.GONE);
                    cardViewMCQ1.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);
                    GlobalVariables.m08MCQcardChecked[3] = 0;
                    cardTickMCQ4.setVisibility(View.GONE);
                    cardViewMCQ4.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);

                    if (GlobalVariables.m08CurrentMCQNo == 1) {
                        GlobalVariables.m08ScoreQ2[0] = 1;
                    }
                } else {
                    GlobalVariables.m08MCQcardChecked[2] = 0;
                    cardTickMCQ3.setVisibility(View.GONE);
                    cardViewMCQ3.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);

                    if (GlobalVariables.m08CurrentMCQNo == 1) {
                        GlobalVariables.m08ScoreQ2[0] = 0;
                    }
                }
            }
        });

        cardViewMCQ4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(100);
                if (GlobalVariables.m08MCQcardChecked[3] == 0) {
                    GlobalVariables.m08MCQcardChecked[3] = 1;
                    cardTickMCQ4.setVisibility(View.VISIBLE);
                    cardViewMCQ4.setCardBackgroundColor(GlobalVariables.whiteColorValue);

                    GlobalVariables.m08MCQcardChecked[1] = 0;
                    cardTickMCQ2.setVisibility(View.GONE);
                    cardViewMCQ2.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);
                    GlobalVariables.m08MCQcardChecked[2] = 0;
                    cardTickMCQ3.setVisibility(View.GONE);
                    cardViewMCQ3.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);
                    GlobalVariables.m08MCQcardChecked[0] = 0;
                    cardTickMCQ1.setVisibility(View.GONE);
                    cardViewMCQ1.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);

                    if (GlobalVariables.m08CurrentMCQNo == 2) {
                        GlobalVariables.m08ScoreQ2[1] = 1;
                    }
                } else {
                    GlobalVariables.m08MCQcardChecked[3] = 0;
                    cardTickMCQ4.setVisibility(View.GONE);
                    cardViewMCQ4.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);

                    if (GlobalVariables.m08CurrentMCQNo == 2) {
                        GlobalVariables.m08ScoreQ2[1] = 0;
                    }
                }
            }
        });

        previousQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GlobalVariables.m08QuestionNo == 2) {
                    questionDecrement();
                }
                setViewModule();
            }
        });

        scoreCorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(100);
                if (GlobalVariables.m08QuestionNo == 1) {
                    GlobalVariables.m08MCQno[0] = GlobalVariables.m08ScoreQ1[0];
                    GlobalVariables.m08MCQno[1] = GlobalVariables.m08ScoreQ1[1];
                    GlobalVariables.m08MCQno[2] = GlobalVariables.m08ScoreQ1[2];
                    GlobalVariables.m08MCQno[3] = GlobalVariables.m08ScoreQ1[3];
                    GlobalVariables.m08MCQno[4] = GlobalVariables.m08ScoreQ1[4];
                }
                View rootView = getWindow().getDecorView().getRootView();
                GlobalVariables.saveScreenshot(rootView, fileName);
                onCorrect();
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SweetAlertDialog(Module08Activity.this)
                        .setTitleText(getString(R.string.verbal_memory))
                        .setContentText(getString(R.string.java_task8_question))
                        .show();
            }
        });
    }

    // Increments question number and animates content view
    private void questionIncrement() {
        GlobalVariables.m08QuestionNo = 2;
        animateRight();
    }

    // Decrements question number and animates content view
    private void questionDecrement() {
        GlobalVariables.m08QuestionNo = 1;
        animateLeft();
    }

    // Sets views for all questions
    private void setViewModule() {
        setCardsView();
        if (GlobalVariables.m08QuestionNo == 1) {
            mainLayout.setVisibility(View.VISIBLE);
            mcqLayout.setVisibility(View.GONE);
            previousQuestion.setVisibility(View.GONE);
            questionNumber.setText("1/1");
            fileName = "10 - Task 08 Question 1";
        } else if (GlobalVariables.m08QuestionNo == 2) {
            mainLayout.setVisibility(View.GONE);
            mcqLayout.setVisibility(View.VISIBLE);
            previousQuestion.setVisibility(View.VISIBLE);
            questionNumber.setText("");
            resetMCQItems();
            if (GlobalVariables.m08MCQno[0] == 0) {
                GlobalVariables.m08CurrentMCQNo = 1;
                textViewMCQ1.setText(R.string.module_08_mcq1_word1);
                textViewMCQ2.setText(R.string.module_08_mcq1_word2);
                textViewMCQ3.setText(R.string.module_08_mcq1_word3);
                textViewMCQ4.setText(R.string.module_08_mcq1_word4);
                fileName = "10 - Task 08 Question 2.1";
            } else if (GlobalVariables.m08MCQno[1] == 0) {
                GlobalVariables.m08CurrentMCQNo = 2;
                textViewMCQ1.setText(R.string.module_08_mcq2_word1);
                textViewMCQ2.setText(R.string.module_08_mcq2_word2);
                textViewMCQ3.setText(R.string.module_08_mcq2_word3);
                textViewMCQ4.setText(R.string.module_08_mcq2_word4);
                fileName = "10 - Task 08 Question 2.2";
            } else if (GlobalVariables.m08MCQno[2] == 0) {
                GlobalVariables.m08CurrentMCQNo = 3;
                textViewMCQ1.setText(R.string.module_08_mcq3_word1);
                textViewMCQ2.setText(R.string.module_08_mcq3_word2);
                textViewMCQ3.setText(R.string.module_08_mcq3_word3);
                textViewMCQ4.setText(R.string.module_08_mcq3_word4);
                fileName = "10 - Task 08 Question 2.3";
            } else if (GlobalVariables.m08MCQno[3] == 0) {
                GlobalVariables.m08CurrentMCQNo = 4;
                textViewMCQ1.setText(R.string.module_08_mcq4_word1);
                textViewMCQ2.setText(R.string.module_08_mcq4_word2);
                textViewMCQ3.setText(R.string.module_08_mcq4_word3);
                textViewMCQ4.setText(R.string.module_08_mcq4_word4);
                fileName = "10 - Task 08 Question 2.4";
            } else if (GlobalVariables.m08MCQno[4] == 0) {
                GlobalVariables.m08CurrentMCQNo = 5;
                textViewMCQ1.setText(R.string.module_08_mcq5_word1);
                textViewMCQ2.setText(R.string.module_08_mcq5_word2);
                textViewMCQ3.setText(R.string.module_08_mcq5_word3);
                textViewMCQ4.setText(R.string.module_08_mcq5_word4);
                fileName = "10 - Task 08 Question 2.5";
            }
        }
    }

    // Sets card views for all questions depending on the score
    private void setCardsView() {
        if (GlobalVariables.m08ScoreQ1[0] == 1) {
            cardTick1.setVisibility(View.VISIBLE);
            cardView1.setCardBackgroundColor(GlobalVariables.whiteColorValue);
        } else {
            cardTick1.setVisibility(View.GONE);
            cardView1.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);
        }

        if (GlobalVariables.m08ScoreQ1[1] == 1) {
            cardTick2.setVisibility(View.VISIBLE);
            cardView2.setCardBackgroundColor(GlobalVariables.whiteColorValue);
        } else {
            cardTick2.setVisibility(View.GONE);
            cardView2.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);
        }

        if (GlobalVariables.m08ScoreQ1[2] == 1) {
            cardTick3.setVisibility(View.VISIBLE);
            cardView3.setCardBackgroundColor(GlobalVariables.whiteColorValue);
        } else {
            cardTick3.setVisibility(View.GONE);
            cardView3.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);
        }

        if (GlobalVariables.m08ScoreQ1[3] == 1) {
            cardTick4.setVisibility(View.VISIBLE);
            cardView4.setCardBackgroundColor(GlobalVariables.whiteColorValue);
        } else {
            cardTick4.setVisibility(View.GONE);
            cardView4.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);
        }

        if (GlobalVariables.m08ScoreQ1[4] == 1) {
            cardTick5.setVisibility(View.VISIBLE);
            cardView5.setCardBackgroundColor(GlobalVariables.whiteColorValue);
        } else {
            cardTick5.setVisibility(View.GONE);
            cardView5.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);
        }

        if (GlobalVariables.m08MCQcardChecked[0] == 1) {
            cardTickMCQ1.setVisibility(View.VISIBLE);
            cardViewMCQ1.setCardBackgroundColor(GlobalVariables.whiteColorValue);
        } else {
            cardTickMCQ1.setVisibility(View.GONE);
            cardViewMCQ1.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);
        }

        if (GlobalVariables.m08MCQcardChecked[1] == 1) {
            cardTickMCQ2.setVisibility(View.VISIBLE);
            cardViewMCQ2.setCardBackgroundColor(GlobalVariables.whiteColorValue);
        } else {
            cardTickMCQ2.setVisibility(View.GONE);
            cardViewMCQ2.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);
        }

        if (GlobalVariables.m08MCQcardChecked[2] == 1) {
            cardTickMCQ3.setVisibility(View.VISIBLE);
            cardViewMCQ3.setCardBackgroundColor(GlobalVariables.whiteColorValue);
        } else {
            cardTickMCQ3.setVisibility(View.GONE);
            cardViewMCQ3.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);
        }

        if (GlobalVariables.m08MCQcardChecked[3] == 1) {
            cardTickMCQ4.setVisibility(View.VISIBLE);
            cardViewMCQ4.setCardBackgroundColor(GlobalVariables.whiteColorValue);
        } else {
            cardTickMCQ4.setVisibility(View.GONE);
            cardViewMCQ4.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);
        }
    }

    // Resets MCQ card views for each question
    private void resetMCQItems() {
        GlobalVariables.m08MCQcardChecked[0] = 0;
        GlobalVariables.m08MCQcardChecked[1] = 0;
        GlobalVariables.m08MCQcardChecked[2] = 0;
        GlobalVariables.m08MCQcardChecked[3] = 0;
        cardTickMCQ1.setVisibility(View.GONE);
        cardViewMCQ1.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);
        cardTickMCQ2.setVisibility(View.GONE);
        cardViewMCQ2.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);
        cardTickMCQ3.setVisibility(View.GONE);
        cardViewMCQ3.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);
        cardTickMCQ4.setVisibility(View.GONE);
        cardViewMCQ4.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);
    }

    // On correct button
    private void onCorrect() {
        if (GlobalVariables.m08QuestionNo == 1) {
            if (GlobalVariables.m08ScoreQ1[0] == 1 && GlobalVariables.m08ScoreQ1[1] == 1 && GlobalVariables.m08ScoreQ1[2] == 1 && GlobalVariables.m08ScoreQ1[3] == 1 && GlobalVariables.m08ScoreQ1[4] == 1) {
                nextModule();
            }
        } else if (GlobalVariables.m08QuestionNo == 2) {
            if (GlobalVariables.m08MCQno[0] == 0) {
                GlobalVariables.m08MCQno[0] = 1;
            } else if (GlobalVariables.m08MCQno[1] == 0) {
                GlobalVariables.m08MCQno[1] = 1;
            } else if (GlobalVariables.m08MCQno[2] == 0) {
                GlobalVariables.m08MCQno[2] = 1;
            } else if (GlobalVariables.m08MCQno[3] == 0) {
                GlobalVariables.m08MCQno[3] = 1;
            } else if (GlobalVariables.m08MCQno[4] == 0) {
                GlobalVariables.m08MCQno[4] = 1;
            }
            if (GlobalVariables.m08MCQno[0] == 1 && GlobalVariables.m08MCQno[1] == 1 && GlobalVariables.m08MCQno[2] == 1 && GlobalVariables.m08MCQno[3] == 1 && GlobalVariables.m08MCQno[4] == 1) {
                nextModule();
            }
        }
        questionIncrement();
        setViewModule();
    }

    // Start recording Audio
    private void startAudioRecorder() {
        String outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Dementia Cognition Screen/" + GlobalVariables.userID + "_" + GlobalVariables.userInitials + "/09 - Task 08 Recording.mp3";
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

    // Content left animation
    private void animateLeft() {
        YoYo.with(Techniques.SlideInLeft)
                .duration(500)
                .playOn(findViewById(R.id.content_m08));
    }

    // Content right animation
    private void animateRight() {
        YoYo.with(Techniques.SlideInRight)
                .duration(500)
                .playOn(findViewById(R.id.content_m08));
    }

    // Starts next selected Task
    private void nextModule() {
        stopAudioRecorder();
        if (GlobalVariables.modulesSelected[8]) {
            Intent intentModulesActivity = new Intent(Module08Activity.this, Module09Activity.class);
            Module08Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[9]) {
            Intent intentModulesActivity = new Intent(Module08Activity.this, Module10Activity.class);
            Module08Activity.this.startActivity(intentModulesActivity);
        } else {
            Intent intentModulesActivity = new Intent(Module08Activity.this, ResultsActivity.class);
            Module08Activity.this.startActivity(intentModulesActivity);
        }
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }
}
