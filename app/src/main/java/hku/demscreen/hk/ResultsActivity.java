package hku.demscreen.hk;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ResultsActivity extends AppCompatActivity {

    File file;
    String data;

    TextView userName;

    CardView results01;
    CardView results02;
    CardView results03;
    CardView results04;
    CardView results05;
    CardView results06;
    CardView results07;
    CardView results08;
    CardView results09;
    CardView results10;

    TextView m01Score1;
    TextView m01Score2;
    TextView m01Score3;
    TextView m01Score4;
    TextView m02Score1;
    TextView m02Score2;
    TextView m02Score3;
    TextView m02Score4;
    TextView m03Score1;
    TextView m03Score2;
    TextView m03Score3;
    TextView m03Score4;
    TextView m03Score5;
    TextView m04Score1;
    TextView m04Score2;
    TextView m04Score3;
    TextView m04Score4;
    TextView m05Score1;
    TextView m05Score2;
    TextView m08Score1;
    TextView m08Score2;
    TextView m08Score3;
    TextView m08Score4;
    TextView m08Score5;
    TextView m09Score1;
    TextView m09Score2;
    TextView m09Score3;
    TextView m10Score;

    int m10FinalScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);


        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
        GlobalVariables.testTimeEnd = df.format(Calendar.getInstance().getTime());
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Dementia Cognition Screen/" + GlobalVariables.userName + GlobalVariables.userAge + GlobalVariables.userID + "/";
        file = new File(path + "00 - Results" + ".txt");
        file.getParentFile().mkdirs(); //if the folder doesn't exists it's created

        data = "Patient's Name: " + GlobalVariables.userName + (System.getProperty("line.separator"));
        data += "Patient's Age: " + GlobalVariables.userAge + (System.getProperty("line.separator"));
        data += "Patient's Sex: " + GlobalVariables.userSex + (System.getProperty("line.separator"));
        data += "Patient's ID: " + GlobalVariables.userID + (System.getProperty("line.separator"));
        data += "Test Start Time: " + GlobalVariables.testTimeStart + (System.getProperty("line.separator"));
        data += "Test End Time: " + GlobalVariables.testTimeEnd + (System.getProperty("line.separator"));
        data += "" + (System.getProperty("line.separator"));
        data += "" + (System.getProperty("line.separator"));
        SaveTextData(file, data);

        userName = (TextView) findViewById(R.id.activity_results_username);

        userName.setText(GlobalVariables.userName);

        results01 = (CardView) findViewById(R.id.m01_result);
        results02 = (CardView) findViewById(R.id.m02_result);
        results03 = (CardView) findViewById(R.id.m03_result);
        results04 = (CardView) findViewById(R.id.m04_result);
        results05 = (CardView) findViewById(R.id.m05_result);
        results08 = (CardView) findViewById(R.id.m08_result);
        results09 = (CardView) findViewById(R.id.m09_result);
        results10 = (CardView) findViewById(R.id.m10_result);

        m01Score1 = (TextView) findViewById(R.id.m01_score1);
        m01Score2 = (TextView) findViewById(R.id.m01_score2);
        m01Score3 = (TextView) findViewById(R.id.m01_score3);
        m01Score4 = (TextView) findViewById(R.id.m01_score4);
        m02Score1 = (TextView) findViewById(R.id.m02_score1);
        m02Score2 = (TextView) findViewById(R.id.m02_score2);
        m02Score3 = (TextView) findViewById(R.id.m02_score3);
        m02Score4 = (TextView) findViewById(R.id.m02_score4);
        m03Score1 = (TextView) findViewById(R.id.m03_score1);
        m03Score2 = (TextView) findViewById(R.id.m03_score2);
        m03Score3 = (TextView) findViewById(R.id.m03_score3);
        m03Score4 = (TextView) findViewById(R.id.m03_score4);
        m03Score5 = (TextView) findViewById(R.id.m03_score5);
        m04Score1 = (TextView) findViewById(R.id.m04_score1);
        m04Score2 = (TextView) findViewById(R.id.m04_score2);
        m04Score3 = (TextView) findViewById(R.id.m04_score3);
        m04Score4 = (TextView) findViewById(R.id.m04_score4);
        m05Score1 = (TextView) findViewById(R.id.m05_score1);
        m05Score2 = (TextView) findViewById(R.id.m05_score2);
        m08Score1 = (TextView) findViewById(R.id.m08_score1);
        m08Score2 = (TextView) findViewById(R.id.m08_score2);
        m08Score3 = (TextView) findViewById(R.id.m08_score3);
        m08Score4 = (TextView) findViewById(R.id.m08_score4);
        m08Score5 = (TextView) findViewById(R.id.m08_score5);
        m09Score1 = (TextView) findViewById(R.id.m09_score1);
        m09Score2 = (TextView) findViewById(R.id.m09_score2);
        m09Score3 = (TextView) findViewById(R.id.m09_score3);
        m10Score = (TextView) findViewById(R.id.m10_score);

        if (GlobalVariables.modulesSelected[0]) {
            results01.setVisibility(View.VISIBLE);
            m01Score1.setText(String.valueOf(GlobalVariables.m01Score[0]));
            m01Score2.setText(String.valueOf(GlobalVariables.m01Score[1]));
            m01Score3.setText(String.valueOf(GlobalVariables.m01Score[2]));
            m01Score4.setText(String.valueOf(GlobalVariables.m01Score[3]));

            data = "Task 01 " + getString(R.string.picture_naming) + (System.getProperty("line.separator"));
            data += "Question 1: " + intToResult(GlobalVariables.m01Score[0]) + (System.getProperty("line.separator"));
            data += "Question 2: " + intToResult(GlobalVariables.m01Score[1]) + (System.getProperty("line.separator"));
            data += "Question 3: " + intToResult(GlobalVariables.m01Score[2]) + (System.getProperty("line.separator"));
            data += "Question 4: " + intToResult(GlobalVariables.m01Score[3]) + (System.getProperty("line.separator"));
            data += "" + (System.getProperty("line.separator"));
            SaveTextData(file, data);
        }

        if (GlobalVariables.modulesSelected[1]) {
            results02.setVisibility(View.VISIBLE);
            m02Score1.setText(String.valueOf(GlobalVariables.m02Score[0]));
            m02Score2.setText(String.valueOf(GlobalVariables.m02Score[1]));
            m02Score3.setText(String.valueOf(GlobalVariables.m02Score[2]));
            m02Score4.setText(String.valueOf(GlobalVariables.m02Score[3]));

            data = "Task 02 " + getString(R.string.picture_pointing) + (System.getProperty("line.separator"));
            data += "Question 1: " + intToResult(GlobalVariables.m02Score[0]) + (System.getProperty("line.separator"));
            data += "Question 2: " + intToResult(GlobalVariables.m02Score[1]) + (System.getProperty("line.separator"));
            data += "Question 3: " + intToResult(GlobalVariables.m02Score[2]) + (System.getProperty("line.separator"));
            data += "Question 4: " + intToResult(GlobalVariables.m02Score[3]) + (System.getProperty("line.separator"));
            data += "" + (System.getProperty("line.separator"));
            SaveTextData(file, data);
        }

        if (GlobalVariables.modulesSelected[2]) {
            results03.setVisibility(View.VISIBLE);
            m03Score1.setText(String.valueOf(GlobalVariables.m03Score[0]));
            m03Score2.setText(String.valueOf(GlobalVariables.m03Score[1]));
            m03Score3.setText(String.valueOf(GlobalVariables.m03Score[2]));
            m03Score4.setText(String.valueOf(GlobalVariables.m03Score[3]));
            m03Score5.setText(String.valueOf(GlobalVariables.m03Score[4]));

            data = "Task 03 " + getString(R.string.word_learning) + (System.getProperty("line.separator"));
            data += "Question 1: " + intToResult(GlobalVariables.m03Score[0]) + (System.getProperty("line.separator"));
            data += "Question 2: " + intToResult(GlobalVariables.m03Score[1]) + (System.getProperty("line.separator"));
            data += "Question 3: " + intToResult(GlobalVariables.m03Score[2]) + (System.getProperty("line.separator"));
            data += "Question 4: " + intToResult(GlobalVariables.m03Score[3]) + (System.getProperty("line.separator"));
            data += "" + (System.getProperty("line.separator"));
            SaveTextData(file, data);
        }

        if (GlobalVariables.modulesSelected[3]) {
            results04.setVisibility(View.VISIBLE);
            m04Score1.setText(String.valueOf(GlobalVariables.m04Score[0]));
            m04Score2.setText(String.valueOf(GlobalVariables.m04Score[1]));
            m04Score3.setText(String.valueOf(GlobalVariables.m04Score[2]));
            m04Score4.setText(String.valueOf(GlobalVariables.m04Score[3]));

            data = "Task 04 " + getString(R.string.orientation) + (System.getProperty("line.separator"));
            data += "Question 1: " + intToResult(GlobalVariables.m04Score[0]) + (System.getProperty("line.separator"));
            data += "Question 2: " + intToResult(GlobalVariables.m04Score[1]) + (System.getProperty("line.separator"));
            data += "Question 3: " + intToResult(GlobalVariables.m04Score[2]) + (System.getProperty("line.separator"));
            data += "Question 4: " + intToResult(GlobalVariables.m04Score[3]) + (System.getProperty("line.separator"));
            data += "" + (System.getProperty("line.separator"));
            SaveTextData(file, data);
        }

        if (GlobalVariables.modulesSelected[4]) {
            results05.setVisibility(View.VISIBLE);
            m05Score1.setText(String.valueOf(GlobalVariables.m05Score[0]));
            m05Score2.setText(String.valueOf((int) (Math.round(GlobalVariables.m05Score[1]))));

            data = "Task 05 " + getString(R.string.figure_copy) + (System.getProperty("line.separator"));
            data += "Question 1: " + Task5ToResult(GlobalVariables.m05Score[0]) + " - Time: " + GlobalVariables.m05TimeTaken[0] + (System.getProperty("line.separator"));
            data += "Question 2: " + Task5ToResult2(GlobalVariables.m05Score[1]) + " - Time: " + GlobalVariables.m05TimeTaken[1] + (System.getProperty("line.separator"));
            data += "" + (System.getProperty("line.separator"));
            SaveTextData(file, data);
        }

        if (GlobalVariables.modulesSelected[7]) {
            results08.setVisibility(View.VISIBLE);
            m08Score1.setText(String.valueOf(GlobalVariables.m08Score[0]));
            m08Score2.setText(String.valueOf(GlobalVariables.m08Score[1]));
            m08Score3.setText(String.valueOf(GlobalVariables.m08Score[2]));
            m08Score4.setText(String.valueOf(GlobalVariables.m08Score[3]));
            m08Score5.setText(String.valueOf(GlobalVariables.m08Score[4]));

            data = "Task 08 " + getString(R.string.verbal_memory) + (System.getProperty("line.separator"));
            data += "Question 1: " + intToResult(GlobalVariables.m08Score[0]) + (System.getProperty("line.separator"));
            data += "Question 2: " + intToResult(GlobalVariables.m08Score[1]) + (System.getProperty("line.separator"));
            data += "Question 3: " + intToResult(GlobalVariables.m08Score[2]) + (System.getProperty("line.separator"));
            data += "Question 4: " + intToResult(GlobalVariables.m08Score[3]) + (System.getProperty("line.separator"));
            data += "" + (System.getProperty("line.separator"));
            SaveTextData(file, data);
        }

        if (GlobalVariables.modulesSelected[8]) {
            results09.setVisibility(View.VISIBLE);
            m09Score1.setText(String.valueOf(GlobalVariables.m09Score[0]));
            m09Score2.setText(String.valueOf(GlobalVariables.m09Score[1]));
            m09Score3.setText(String.valueOf(GlobalVariables.m09Score[2]));

            data = "Task 09 " + getString(R.string.episodic_memory) + (System.getProperty("line.separator"));
            data += "Question 1: " + intToResult(GlobalVariables.m09Score[0]) + (System.getProperty("line.separator"));
            data += "Question 2: " + intToResult(GlobalVariables.m09Score[1]) + (System.getProperty("line.separator"));
            data += "Question 3: " + intToResult(GlobalVariables.m09Score[2]) + (System.getProperty("line.separator"));
            data += "" + (System.getProperty("line.separator"));
            SaveTextData(file, data);
        }

        if (GlobalVariables.modulesSelected[9]) {
            results10.setVisibility(View.VISIBLE);
            for (int i : GlobalVariables.m10Score) {
                m10FinalScore += i;
            }
            if (GlobalVariables.m10WrongScore > 0) {
                m10FinalScore -= GlobalVariables.m10WrongScore;
            }
            m10Score.setText(String.valueOf(m10FinalScore));

            data = "Task 10 " + getString(R.string.attention) + (System.getProperty("line.separator"));
            data += "Question 1: " + intToResult(GlobalVariables.m10Score[0]) + (System.getProperty("line.separator"));
            data += "Question 2: " + intToResult(GlobalVariables.m10Score[1]) + (System.getProperty("line.separator"));
            data += "Question 3: " + intToResult(GlobalVariables.m10Score[2]) + (System.getProperty("line.separator"));
            data += "Questions wrong: " + GlobalVariables.m10WrongScore + (System.getProperty("line.separator"));
            data += "" + (System.getProperty("line.separator"));
            SaveTextData(file, data);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.results, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_again) {
            Intent intentModulesActivity = new Intent(ResultsActivity.this, MainActivity.class);
            ResultsActivity.this.startActivity(intentModulesActivity);
            finish();
            System.exit(0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void SaveTextData(File file, String data) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            try {
                fos.write(data.getBytes());
                fos.write("\n".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String intToResult(int x) {
        if (x == 1) {
            return "Correct";
        } else if (x == 0) {
            return "Wrong";
        }
        return "Error";
    }

    private String Task5ToResult(double x) {
        if (x == 0) {
            return "All Wrong";
        } else if (x == 0.5) {
            return "1 Correct";
        } else if (x == 1.0) {
            return "2 Correct";
        } else if (x == 1.5) {
            return "3 Correct";
        } else if (x == 2.0) {
            return "4 Correct";
        }
        return "Error";
    }

    private String Task5ToResult2(double x) {
        if (x == 0) {
            return "All Wrong";
        } else if (x == 1.0) {
            return "1 Correct";
        } else if (x == 2.0) {
            return "2 Correct";
        } else if (x == 3.0) {
            return "3 Correct";
        } else if (x == 4.0) {
            return "4 Correct";
        }
        return "Error";
    }
}
