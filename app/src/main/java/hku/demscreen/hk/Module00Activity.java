package hku.demscreen.hk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Module00Activity extends AppCompatActivity {

    String Tag = "Module00Activity";

    // Main screen
    ImageView info;
    Vibrator vibrator;

    // Question 00
    ImageView m00Circle01;
    ImageView m00Circle02;
    ImageView m00Circle03;
    ImageView m00Circle04;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module00);

        //Main screen
        info = (ImageView) findViewById(R.id.info00);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Question 00
        m00Circle01 = (ImageView) findViewById(R.id.m00_circle1);
        m00Circle02 = (ImageView) findViewById(R.id.m00_circle2);
        m00Circle03 = (ImageView) findViewById(R.id.m00_circle3);
        m00Circle04 = (ImageView) findViewById(R.id.m00_circle4);

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SweetAlertDialog(Module00Activity.this)
                        .setTitleText("Practice Task")
                        .setContentText("Tap on the circles!")
                        .show();
            }
        });

        mainQuestion00();
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

    // Question 00
    private void mainQuestion00() {
        final ImageView m00_circle1_tick = (ImageView) findViewById(R.id.m00_circle1_tick);
        final ImageView m00_circle2_tick = (ImageView) findViewById(R.id.m00_circle2_tick);
        final ImageView m00_circle3_tick = (ImageView) findViewById(R.id.m00_circle3_tick);
        final ImageView m00_circle4_tick = (ImageView) findViewById(R.id.m00_circle4_tick);
        final boolean[] ticked = {false, false, false, false};
        m00Circle01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(100);
                if (ticked[0]) {
                    m00_circle1_tick.setVisibility(View.GONE);
                    ticked[0] = false;
                } else {
                    m00_circle1_tick.setVisibility(View.VISIBLE);
                    ticked[0] = true;
                }
            }
        });
        m00Circle02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(100);
                if (ticked[1]) {
                    m00_circle2_tick.setVisibility(View.GONE);
                    ticked[1] = false;
                } else {
                    m00_circle2_tick.setVisibility(View.VISIBLE);
                    ticked[1] = true;
                }
            }
        });
        m00Circle03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(100);
                if (ticked[2]) {
                    m00_circle3_tick.setVisibility(View.GONE);
                    ticked[2] = false;
                } else {
                    m00_circle3_tick.setVisibility(View.VISIBLE);
                    ticked[2] = true;
                }
            }
        });
        m00Circle04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(100);
                if (ticked[3]) {
                    m00_circle4_tick.setVisibility(View.GONE);
                    ticked[3] = false;
                } else {
                    m00_circle4_tick.setVisibility(View.VISIBLE);
                    ticked[3] = true;
                }
            }
        });
    }

    private void nextModule() {
        if (GlobalVariables.modulesSelected[0]) {
            Intent intentModulesActivity = new Intent(Module00Activity.this, Module01Activity.class);
            Module00Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[1]) {
            Intent intentModulesActivity = new Intent(Module00Activity.this, Module02Activity.class);
            Module00Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[2]) {
            Intent intentModulesActivity = new Intent(Module00Activity.this, Module03Activity.class);
            Module00Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[3]) {
            Intent intentModulesActivity = new Intent(Module00Activity.this, Module04Activity.class);
            Module00Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[4]) {
            Intent intentModulesActivity = new Intent(Module00Activity.this, ResultsActivity.class);
            Module00Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[5]) {
            Intent intentModulesActivity = new Intent(Module00Activity.this, ResultsActivity.class);
            Module00Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[6]) {
            Intent intentModulesActivity = new Intent(Module00Activity.this, ResultsActivity.class);
            Module00Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[7]) {
            Intent intentModulesActivity = new Intent(Module00Activity.this, ResultsActivity.class);
            Module00Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[8]) {
            Intent intentModulesActivity = new Intent(Module00Activity.this, ResultsActivity.class);
            Module00Activity.this.startActivity(intentModulesActivity);
        } else if (GlobalVariables.modulesSelected[9]) {
            Intent intentModulesActivity = new Intent(Module00Activity.this, ResultsActivity.class);
            Module00Activity.this.startActivity(intentModulesActivity);
        } else {
            Intent intentModulesActivity = new Intent(Module00Activity.this, ResultsActivity.class);
            Module00Activity.this.startActivity(intentModulesActivity);
        }
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }
}
