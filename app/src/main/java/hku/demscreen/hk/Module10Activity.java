package hku.demscreen.hk;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Module10Activity extends AppCompatActivity {

    String Tag = "Module10Activity";
    Vibrator vibrator;

    // Main screen
    TextView questionNumber;
    ImageView scoreCorrect;
    ImageView info;
    ImageView sound;

    // Question
    ImageView circle01;
    ImageView circle02;
    MediaPlayer audio_and;
    MediaPlayer audio_but;
    MediaPlayer audio_down;
    MediaPlayer audio_drink;
    MediaPlayer audio_go;
    MediaPlayer audio_goodbye;
    MediaPlayer audio_hello;
    MediaPlayer audio_no;
    MediaPlayer audio_today;
    MediaPlayer audio_up;
    MediaPlayer audio_yes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module10);

        //Main screen
        info = (ImageView) findViewById(R.id.info_m10);
        scoreCorrect = (ImageView) findViewById(R.id.score_correct_m10);
        sound = (ImageView) findViewById(R.id.sound_m10);
        questionNumber = (TextView) findViewById(R.id.question_number_m10);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Question
        circle01 = (ImageView) findViewById(R.id.m10_circle1);
        circle02 = (ImageView) findViewById(R.id.m10_circle2);
        audio_and = MediaPlayer.create(this, R.raw.audio_and);
        audio_but = MediaPlayer.create(this, R.raw.audio_but);
        audio_down = MediaPlayer.create(this, R.raw.audio_down);
        audio_drink = MediaPlayer.create(this, R.raw.audio_drink);
        audio_go = MediaPlayer.create(this, R.raw.audio_go);
        audio_goodbye = MediaPlayer.create(this, R.raw.audio_goodbye);
        audio_hello = MediaPlayer.create(this, R.raw.audio_hello);
        audio_no = MediaPlayer.create(this, R.raw.audio_no);
        audio_today = MediaPlayer.create(this, R.raw.audio_today);
        audio_up = MediaPlayer.create(this, R.raw.audio_up);
        audio_yes = MediaPlayer.create(this, R.raw.audio_yes);

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
        circle01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(100);
                if (GlobalVariables.m10TaskNo == 3) {
                    if (GlobalVariables.m10QuestionNo == 1) {
                        GlobalVariables.m10WrongScore += 1;
                    } else if (GlobalVariables.m10QuestionNo == 2) {
                        GlobalVariables.m10Score[0] = 1;
                    } else if (GlobalVariables.m10QuestionNo == 3) {
                        GlobalVariables.m10WrongScore += 1;
                    } else if (GlobalVariables.m10QuestionNo == 4) {
                        GlobalVariables.m10Score[1] = 1;
                    } else if (GlobalVariables.m10QuestionNo == 5) {
                        GlobalVariables.m10WrongScore += 1;
                    } else if (GlobalVariables.m10QuestionNo == 6) {
                        GlobalVariables.m10WrongScore += 1;
                    } else if (GlobalVariables.m10QuestionNo == 7) {
                        GlobalVariables.m10Score[2] = 1;
                    } else if (GlobalVariables.m10QuestionNo == 8) {
                        GlobalVariables.m10WrongScore += 1;
                    } else if (GlobalVariables.m10QuestionNo == 9) {
                        GlobalVariables.m10WrongScore += 1;
                    }
                }
                questionIncrement();
                setViewModule();
                playSound();
            }
        });
        circle02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(100);
                questionIncrement();
                setViewModule();
                playSound();
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
                if (GlobalVariables.m10TaskNo == 1) {
                    new SweetAlertDialog(Module10Activity.this)
                            .setTitleText(getString(R.string.attention))
                            .setContentText("Tap on blue button for correct and yellow button for wrong")
                            .show();
                } else if (GlobalVariables.m10TaskNo == 2) {
                    new SweetAlertDialog(Module10Activity.this)
                            .setTitleText(getString(R.string.attention) + " - Practice")
                            .setContentText("Is this word one of the target words?")
                            .show();
                } else if (GlobalVariables.m10TaskNo == 3) {
                    new SweetAlertDialog(Module10Activity.this)
                            .setTitleText(getString(R.string.attention))
                            .setContentText("Is this word one of the target words?")
                            .show();
                }
            }
        });

        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSound();
            }
        });
    }

    // Increments question number and animates content view
    private void questionIncrement() {
        if (GlobalVariables.m10TaskNo == 1) {
            if (GlobalVariables.m10TeachQuestionNo != 0) {
                if (GlobalVariables.m10TeachQuestionNo == 3)
                {
                    GlobalVariables.m10TaskNo = 2;
                } else {
                    GlobalVariables.m10TeachQuestionNo = GlobalVariables.m10TeachQuestionNo + 1;
                }
            }
        } else if (GlobalVariables.m10TaskNo == 2) {
            if (GlobalVariables.m10PracticeQuestionNo == 3) {
                GlobalVariables.m10TaskNo = 3;
            } else {
                GlobalVariables.m10PracticeQuestionNo = GlobalVariables.m10PracticeQuestionNo + 1;
            }
        } else if (GlobalVariables.m10TaskNo == 3) {
            if (GlobalVariables.m10QuestionNo == 9) {
                nextModule();
            } else {
                GlobalVariables.m10QuestionNo = GlobalVariables.m10QuestionNo + 1;
            }
        }
    }

    // Sets views for all questions
    private void setViewModule() {
        if (GlobalVariables.m10TaskNo == 1) {
            if (GlobalVariables.m10TeachQuestionNo == 0) {
                questionNumber.setText("");
            } else if (GlobalVariables.m10TeachQuestionNo == 1) {
                questionNumber.setText("Words: 1/3");
            } else if (GlobalVariables.m10TeachQuestionNo == 2) {
                questionNumber.setText("Words: 2/3");
            } else if (GlobalVariables.m10TeachQuestionNo == 3) {
                questionNumber.setText("Words: 3/3");
            }
        } else if (GlobalVariables.m10TaskNo == 2) {
            if (GlobalVariables.m10PracticeQuestionNo == 1) {
                questionNumber.setText("Practice: 1/3");
            } else if (GlobalVariables.m10PracticeQuestionNo == 2) {
                questionNumber.setText("Practice: 2/3");
            } else if (GlobalVariables.m10PracticeQuestionNo == 3) {
                questionNumber.setText("Practice: 3/3");
            }
        } else if (GlobalVariables.m10TaskNo == 3) {
            if (GlobalVariables.m10QuestionNo == 1) {
                questionNumber.setText("1/9");
            } else if (GlobalVariables.m10QuestionNo == 2) {
                questionNumber.setText("2/9");
            } else if (GlobalVariables.m10QuestionNo == 3) {
                questionNumber.setText("3/9");
            } else if (GlobalVariables.m10QuestionNo == 4) {
                questionNumber.setText("4/9");
            } else if (GlobalVariables.m10QuestionNo == 5) {
                questionNumber.setText("5/9");
            } else if (GlobalVariables.m10QuestionNo == 6) {
                questionNumber.setText("6/9");
            } else if (GlobalVariables.m10QuestionNo == 7) {
                questionNumber.setText("7/9");
            } else if (GlobalVariables.m10QuestionNo == 8) {
                questionNumber.setText("8/9");
            } else if (GlobalVariables.m10QuestionNo == 9) {
                questionNumber.setText("9/9");
            }
        }
    }

    // Plays audio of the words
    private void playSound() {
        if (GlobalVariables.m10TaskNo == 1) {
            if (GlobalVariables.m10TeachQuestionNo == 1) {
                audio_goodbye.start();
            } else if (GlobalVariables.m10TeachQuestionNo == 2) {
                audio_down.start();
            } else if (GlobalVariables.m10TeachQuestionNo == 3) {
                audio_no.start();
            }
        } else if (GlobalVariables.m10TaskNo == 2) {
            if (GlobalVariables.m10PracticeQuestionNo == 1) {
                audio_go.start();
            } else if (GlobalVariables.m10PracticeQuestionNo == 2) {
                audio_but.start();
            } else if (GlobalVariables.m10PracticeQuestionNo == 3) {
                audio_down.start();
            }
        } else if (GlobalVariables.m10TaskNo == 3) {
            if (GlobalVariables.m10QuestionNo == 1) {
                audio_today.start();
            } else if (GlobalVariables.m10QuestionNo == 2) {
                audio_down.start();
            } else if (GlobalVariables.m10QuestionNo == 3) {
                audio_hello.start();
            } else if (GlobalVariables.m10QuestionNo == 4) {
                audio_goodbye.start();
            } else if (GlobalVariables.m10QuestionNo == 5) {
                audio_and.start();
            } else if (GlobalVariables.m10QuestionNo == 6) {
                audio_drink.start();
            } else if (GlobalVariables.m10QuestionNo == 7) {
                audio_no.start();
            } else if (GlobalVariables.m10QuestionNo == 8) {
                audio_up.start();
            } else if (GlobalVariables.m10QuestionNo == 9) {
                if (!GlobalVariables.m10YesDone) {
                    audio_yes.start();
                    GlobalVariables.m10YesDone = true;
                }
            }
        }
    }

    // On correct button
    private void onCorrect() {
        if (GlobalVariables.m10TaskNo == 1) {
            if (GlobalVariables.m10TeachQuestionNo == 0) {
                GlobalVariables.m10TeachQuestionNo = 1;
                scoreCorrect.setVisibility(View.GONE);
                setViewModule();
                playSound();
            }
        }
    }

    // Starts next selected Task
    private void nextModule() {
        Intent intentModulesActivity = new Intent(Module10Activity.this, ResultsActivity.class);
        Module10Activity.this.startActivity(intentModulesActivity);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }
}
