package hku.demscreen.hk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
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

    Vibrator vibrator;

    File file;
    String data = "";
    File fileCSV;
    String dataCSV1 = "";
    String dataCSV2 = "";

    int csvSexMale;
    int m01Total;
    int m02Total;
    int m03Total;
    int m04Total;
    double m05Total;
    int m06Total;
    int m07Total;
    int m08Total;
    int m09Total;
    int m10Total;

    double totalScore = 0;

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
    TextView m06Score1;
    TextView m07Score1;
    TextView m07Score2;
    TextView m07Score3;
    TextView m07Score4;
    TextView m08Score1;
    TextView m08Score2;
    TextView m08Score3;
    TextView m08Score4;
    TextView m08Score5;
    TextView m09Score1;
    TextView m09Score2;
    TextView m09Score3;
    TextView m10Score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        results01 = (CardView) findViewById(R.id.m01_result);
        results02 = (CardView) findViewById(R.id.m02_result);
        results03 = (CardView) findViewById(R.id.m03_result);
        results04 = (CardView) findViewById(R.id.m04_result);
        results05 = (CardView) findViewById(R.id.m05_result);
        results06 = (CardView) findViewById(R.id.m06_result);
        results07 = (CardView) findViewById(R.id.m07_result);
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
        m06Score1 = (TextView) findViewById(R.id.m06_score1);
        m07Score1 = (TextView) findViewById(R.id.m07_score1);
        m07Score2 = (TextView) findViewById(R.id.m07_score2);
        m07Score3 = (TextView) findViewById(R.id.m07_score3);
        m07Score4 = (TextView) findViewById(R.id.m07_score4);
        m08Score1 = (TextView) findViewById(R.id.m08_score1);
        m08Score2 = (TextView) findViewById(R.id.m08_score2);
        m08Score3 = (TextView) findViewById(R.id.m08_score3);
        m08Score4 = (TextView) findViewById(R.id.m08_score4);
        m08Score5 = (TextView) findViewById(R.id.m08_score5);
        m09Score1 = (TextView) findViewById(R.id.m09_score1);
        m09Score2 = (TextView) findViewById(R.id.m09_score2);
        m09Score3 = (TextView) findViewById(R.id.m09_score3);
        m10Score = (TextView) findViewById(R.id.m10_score);

        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        GlobalVariables.testTimeEnd = df.format(Calendar.getInstance().getTime());
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Dementia Cognition Screen/" + GlobalVariables.userID + "_" + GlobalVariables.userInitials + "/";
        file = new File(path + "00 - Results" + ".txt");
        file.getParentFile().mkdirs(); //if the folder doesn't exists it's created
        fileCSV = new File(path + "00 - Results" + ".csv");
        fileCSV.getParentFile().mkdirs(); //if the folder doesn't exists it's created

        setValuesCSV();

        data += "Patient's Name: " + GlobalVariables.userName + (System.getProperty("line.separator"));
        data += "Patient's ID: " + GlobalVariables.userID + (System.getProperty("line.separator"));
        data += "Patient's Education: " + GlobalVariables.userEdu + (System.getProperty("line.separator"));
        data += "Patient's DOB: " + GlobalVariables.userAge + (System.getProperty("line.separator"));
        data += "Patient's Sex: " + GlobalVariables.userSex + (System.getProperty("line.separator"));
        data += "Test Language: " + GlobalVariables.testLanguage + " (" + GlobalVariables.testLanguageString + ")" + (System.getProperty("line.separator"));
        data += "Test Date: " + GlobalVariables.testDate + (System.getProperty("line.separator"));
        data += "Test Start Time: " + GlobalVariables.testTimeStart + (System.getProperty("line.separator"));
        data += "Test End Time: " + GlobalVariables.testTimeEnd + (System.getProperty("line.separator"));
        data += "" + (System.getProperty("line.separator"));
        data += "" + (System.getProperty("line.separator"));
        SaveTextData(file, data);

        dataCSV1 += "Initials,Gender,Education,DOB,Language,Date,Start time,End time";
        dataCSV2 += "" + GlobalVariables.userInitials;
        dataCSV2 += "," + csvSexMale;
        dataCSV2 += "," + GlobalVariables.userEdu;
        dataCSV2 += "," + GlobalVariables.userAge;
        dataCSV2 += "," + GlobalVariables.testLanguage;
        dataCSV2 += "," + "(" + GlobalVariables.testDate + ")";
        dataCSV2 += "," + "(" + GlobalVariables.testTimeStart + ")";
        dataCSV2 += "," + "(" + GlobalVariables.testTimeEnd + ")";

        userName = (TextView) findViewById(R.id.activity_results_username);

        userName.setText(GlobalVariables.userName);

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

            dataCSV1 += ",PNT.cherry,PNT.dolphin,PNT.rabbit,PNT.corn,PNT.totalscore";
            dataCSV2 += "," + GlobalVariables.m01Score[0];
            dataCSV2 += "," + GlobalVariables.m01Score[1];
            dataCSV2 += "," + GlobalVariables.m01Score[2];
            dataCSV2 += "," + GlobalVariables.m01Score[3];
            dataCSV2 += "," + m01Total;
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

            dataCSV1 += ",PP.duck,PP.grapes,PP.bird,PP.veg,PP.totalscore";
            dataCSV2 += "," + GlobalVariables.m02Score[0];
            dataCSV2 += "," + GlobalVariables.m02Score[1];
            dataCSV2 += "," + GlobalVariables.m02Score[2];
            dataCSV2 += "," + GlobalVariables.m02Score[3];
            dataCSV2 += "," + m02Total;
        }

        if (GlobalVariables.modulesSelected[2]) {
            results03.setVisibility(View.VISIBLE);
            m03Score1.setText(String.valueOf(GlobalVariables.m03ScoreQ1[0]));
            m03Score2.setText(String.valueOf(GlobalVariables.m03ScoreQ1[1]));
            m03Score3.setText(String.valueOf(GlobalVariables.m03ScoreQ1[2]));
            m03Score4.setText(String.valueOf(GlobalVariables.m03ScoreQ1[3]));
            m03Score5.setText(String.valueOf(GlobalVariables.m03ScoreQ1[4]));

            data = "Task 03 " + getString(R.string.word_learning) + (System.getProperty("line.separator"));
            if (GlobalVariables.m03QuestionNo == 1) {
                data += "Question 1: " + intToResult(GlobalVariables.m03ScoreQ1[0]) + (System.getProperty("line.separator"));
                data += "Question 2: " + intToResult(GlobalVariables.m03ScoreQ1[1]) + (System.getProperty("line.separator"));
                data += "Question 3: " + intToResult(GlobalVariables.m03ScoreQ1[2]) + (System.getProperty("line.separator"));
                data += "Question 4: " + intToResult(GlobalVariables.m03ScoreQ1[3]) + (System.getProperty("line.separator"));
                data += "Question 5: " + intToResult(GlobalVariables.m03ScoreQ1[4]) + (System.getProperty("line.separator"));
            } else if (GlobalVariables.m03QuestionNo == 2) {
                data += "Question 1: " + intToResult(GlobalVariables.m03ScoreQ2[0]) + (System.getProperty("line.separator"));
                data += "Question 2: " + intToResult(GlobalVariables.m03ScoreQ2[1]) + (System.getProperty("line.separator"));
                data += "Question 3: " + intToResult(GlobalVariables.m03ScoreQ2[2]) + (System.getProperty("line.separator"));
                data += "Question 4: " + intToResult(GlobalVariables.m03ScoreQ2[3]) + (System.getProperty("line.separator"));
                data += "Question 5: " + intToResult(GlobalVariables.m03ScoreQ2[4]) + (System.getProperty("line.separator"));
            }
            data += "" + (System.getProperty("line.separator"));
            SaveTextData(file, data);

            dataCSV1 += ",WL1.bicycle,WL1.mist,WL1.wardrobe,WL1.teacher,WL1.rectangle,WL2.bicycle,WL2.mist,WL2.wardrobe,WL2.teacher,WL2.rectangle,WL.totalscore";
            dataCSV2 += "," + GlobalVariables.m03ScoreQ1[0];
            dataCSV2 += "," + GlobalVariables.m03ScoreQ1[1];
            dataCSV2 += "," + GlobalVariables.m03ScoreQ1[2];
            dataCSV2 += "," + GlobalVariables.m03ScoreQ1[3];
            dataCSV2 += "," + GlobalVariables.m03ScoreQ1[4];
            dataCSV2 += "," + GlobalVariables.m03ScoreQ2[0];
            dataCSV2 += "," + GlobalVariables.m03ScoreQ2[1];
            dataCSV2 += "," + GlobalVariables.m03ScoreQ2[2];
            dataCSV2 += "," + GlobalVariables.m03ScoreQ2[3];
            dataCSV2 += "," + GlobalVariables.m03ScoreQ2[4];
            dataCSV2 += "," + m03Total;
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

            dataCSV1 += ",O.year,O.month,O.date,O.CE,O.totalscore";
            dataCSV2 += "," + GlobalVariables.m04Score[0];
            dataCSV2 += "," + GlobalVariables.m04Score[1];
            dataCSV2 += "," + GlobalVariables.m04Score[2];
            dataCSV2 += "," + GlobalVariables.m04Score[3];
            dataCSV2 += "," + m04Total;
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
            dataCSV1 += ",FC,FC.RT,FR,FR.RT,FC.totalscore";
            dataCSV2 += "," + GlobalVariables.m05Score[0];
            dataCSV2 += "," + GlobalVariables.m05TimeTaken[0];
            dataCSV2 += "," + GlobalVariables.m05Score[1];
            dataCSV2 += "," + GlobalVariables.m05TimeTaken[1];
            dataCSV2 += "," + m05Total;
        }

        if (GlobalVariables.modulesSelected[5]) {
            results06.setVisibility(View.VISIBLE);
            m06Score1.setText(String.valueOf((GlobalVariables.m06Score[0] + GlobalVariables.m06Score[1]) - GlobalVariables.m06Score[2]));

            data = "Task 06 " + getString(R.string.trails) + (System.getProperty("line.separator"));
            data += "Question 1: " + GlobalVariables.m06Score[0] + " - Time: " + GlobalVariables.m06TimeTaken[0] + (System.getProperty("line.separator"));
            data += "Question 2: " + GlobalVariables.m06Score[1] + " - Time: " + GlobalVariables.m06TimeTaken[1] + (System.getProperty("line.separator"));
            data += "Question 3: " + GlobalVariables.m06Score[2] + " - Time: " + GlobalVariables.m06TimeTaken[2] + (System.getProperty("line.separator"));
            data += "Task Result: " + ((GlobalVariables.m06Score[0] + GlobalVariables.m06Score[1]) - GlobalVariables.m06Score[2]) + (System.getProperty("line.separator"));
            data += "" + (System.getProperty("line.separator"));
            SaveTextData(file, data);

            dataCSV1 += ",TM1.circles,TM1.RT,TM2.squares,TM2.RT,TM3.mixed,TM3.RT,TM4.exec";
            dataCSV2 += "," + GlobalVariables.m06Score[0];
            dataCSV2 += "," + GlobalVariables.m06TimeTaken[0];
            dataCSV2 += "," + GlobalVariables.m06Score[1];
            dataCSV2 += "," + GlobalVariables.m06TimeTaken[1];
            dataCSV2 += "," + GlobalVariables.m06Score[2];
            dataCSV2 += "," + GlobalVariables.m06TimeTaken[2];
            dataCSV2 += "," + m06Total;
        }

        if (GlobalVariables.modulesSelected[6]) {
            results07.setVisibility(View.VISIBLE);
            m07Score1.setText(String.valueOf(GlobalVariables.m07Score[0]));
            m07Score2.setText(String.valueOf(GlobalVariables.m07Score[1]));
            m07Score3.setText(String.valueOf(GlobalVariables.m07Score[2]));
            m07Score4.setText(String.valueOf(GlobalVariables.m07Score[3]));

            data = "Task 07 " + getString(R.string.rule_finding) + (System.getProperty("line.separator"));
            data += "Question 1: " + intToResult(GlobalVariables.m07Score[0]) + (System.getProperty("line.separator"));
            data += "Question 2: " + intToResult(GlobalVariables.m07Score[1]) + (System.getProperty("line.separator"));
            data += "Question 3: " + intToResult(GlobalVariables.m07Score[2]) + (System.getProperty("line.separator"));
            data += "Question 4: " + intToResult(GlobalVariables.m07Score[3]) + (System.getProperty("line.separator"));
            data += "" + (System.getProperty("line.separator"));
            SaveTextData(file, data);

            for (int i = 0; i < 34; i++) {
                GlobalVariables.m07TappedFigureInt[i] = (GlobalVariables.m07TappedFigure[i]) ? 1 : 0;
            }

            dataCSV1 += ",RF1.1,RF1.2,RF1.3,RF1.4,RF1.5,RF1.6,RF1.7,RF1.score,RF2.1,RF2.2,RF2.3,RF2.4,RF2.5,RF2.6,RF2.7,RF2.8,RF2.score,RF3.1,RF3.2,RF3.3,RF3.4,RF3.5,RF3.6,RF3.7,RF3.8,RF3.score,RF4.1,RF4.2,RF4.3,RF4.4,RF4.5,RF4.6,RF4.7,RF4.8,RF4.score,RF.totalscore";
            dataCSV2 += "," + GlobalVariables.m07TappedFigureInt[3];
            dataCSV2 += "," + GlobalVariables.m07TappedFigureInt[4];
            dataCSV2 += "," + GlobalVariables.m07TappedFigureInt[5];
            dataCSV2 += "," + GlobalVariables.m07TappedFigureInt[6];
            dataCSV2 += "," + GlobalVariables.m07TappedFigureInt[7];
            dataCSV2 += "," + GlobalVariables.m07TappedFigureInt[8];
            dataCSV2 += "," + GlobalVariables.m07TappedFigureInt[9];
            dataCSV2 += "," + GlobalVariables.m07Score[0];
            dataCSV2 += "," + GlobalVariables.m07TappedFigureInt[10];
            dataCSV2 += "," + GlobalVariables.m07TappedFigureInt[11];
            dataCSV2 += "," + GlobalVariables.m07TappedFigureInt[12];
            dataCSV2 += "," + GlobalVariables.m07TappedFigureInt[13];
            dataCSV2 += "," + GlobalVariables.m07TappedFigureInt[14];
            dataCSV2 += "," + GlobalVariables.m07TappedFigureInt[15];
            dataCSV2 += "," + GlobalVariables.m07TappedFigureInt[16];
            dataCSV2 += "," + GlobalVariables.m07TappedFigureInt[17];
            dataCSV2 += "," + GlobalVariables.m07Score[1];
            dataCSV2 += "," + GlobalVariables.m07TappedFigureInt[18];
            dataCSV2 += "," + GlobalVariables.m07TappedFigureInt[19];
            dataCSV2 += "," + GlobalVariables.m07TappedFigureInt[20];
            dataCSV2 += "," + GlobalVariables.m07TappedFigureInt[21];
            dataCSV2 += "," + GlobalVariables.m07TappedFigureInt[22];
            dataCSV2 += "," + GlobalVariables.m07TappedFigureInt[23];
            dataCSV2 += "," + GlobalVariables.m07TappedFigureInt[24];
            dataCSV2 += "," + GlobalVariables.m07TappedFigureInt[25];
            dataCSV2 += "," + GlobalVariables.m07Score[2];
            dataCSV2 += "," + GlobalVariables.m07TappedFigureInt[26];
            dataCSV2 += "," + GlobalVariables.m07TappedFigureInt[27];
            dataCSV2 += "," + GlobalVariables.m07TappedFigureInt[28];
            dataCSV2 += "," + GlobalVariables.m07TappedFigureInt[29];
            dataCSV2 += "," + GlobalVariables.m07TappedFigureInt[30];
            dataCSV2 += "," + GlobalVariables.m07TappedFigureInt[31];
            dataCSV2 += "," + GlobalVariables.m07TappedFigureInt[32];
            dataCSV2 += "," + GlobalVariables.m07TappedFigureInt[33];
            dataCSV2 += "," + GlobalVariables.m07Score[3];
            dataCSV2 += "," + m07Total;
        }

        if (GlobalVariables.modulesSelected[7]) {
            results08.setVisibility(View.VISIBLE);
            m08Score1.setText(String.valueOf(GlobalVariables.m08ScoreQ1[0]));
            m08Score2.setText(String.valueOf(GlobalVariables.m08ScoreQ1[1]));
            m08Score3.setText(String.valueOf(GlobalVariables.m08ScoreQ1[2]));
            m08Score4.setText(String.valueOf(GlobalVariables.m08ScoreQ1[3]));
            m08Score5.setText(String.valueOf(GlobalVariables.m08ScoreQ1[4]));

            data = "Task 08 " + getString(R.string.verbal_memory) + (System.getProperty("line.separator"));
            data += "Question 1.1: " + intToResult(GlobalVariables.m08ScoreQ1[0]) + (System.getProperty("line.separator"));
            data += "Question 1.2: " + intToResult(GlobalVariables.m08ScoreQ1[1]) + (System.getProperty("line.separator"));
            data += "Question 1.3: " + intToResult(GlobalVariables.m08ScoreQ1[2]) + (System.getProperty("line.separator"));
            data += "Question 1.4: " + intToResult(GlobalVariables.m08ScoreQ1[3]) + (System.getProperty("line.separator"));
            data += "Question 1.5: " + intToResult(GlobalVariables.m08ScoreQ1[4]) + (System.getProperty("line.separator"));
            data += "Question 2.1: " + intToResult(GlobalVariables.m08ScoreQ2[0]) + (System.getProperty("line.separator"));
            data += "Question 2.2: " + intToResult(GlobalVariables.m08ScoreQ2[1]) + (System.getProperty("line.separator"));
            data += "Question 2.3: " + intToResult(GlobalVariables.m08ScoreQ2[2]) + (System.getProperty("line.separator"));
            data += "Question 2.4: " + intToResult(GlobalVariables.m08ScoreQ2[3]) + (System.getProperty("line.separator"));
            data += "Question 2.5: " + intToResult(GlobalVariables.m08ScoreQ2[4]) + (System.getProperty("line.separator"));
            data += "" + (System.getProperty("line.separator"));
            SaveTextData(file, data);

            dataCSV1 += ",WM.bicycle,WM.mist,WM.wardrobe,WM.teacher,WM.rectangle,WR.bicycle,WR.mist,WR.wardrobe,WR.teacher,WR.rectangle,WM.totalscore";
            dataCSV2 += "," + GlobalVariables.m08ScoreQ1[0];
            dataCSV2 += "," + GlobalVariables.m08ScoreQ1[1];
            dataCSV2 += "," + GlobalVariables.m08ScoreQ1[2];
            dataCSV2 += "," + GlobalVariables.m08ScoreQ1[3];
            dataCSV2 += "," + GlobalVariables.m08ScoreQ1[4];
            dataCSV2 += "," + GlobalVariables.m08ScoreQ2[0];
            dataCSV2 += "," + GlobalVariables.m08ScoreQ2[1];
            dataCSV2 += "," + GlobalVariables.m08ScoreQ2[2];
            dataCSV2 += "," + GlobalVariables.m08ScoreQ2[3];
            dataCSV2 += "," + GlobalVariables.m08ScoreQ2[4];
            dataCSV2 += "," + m08Total;
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

            dataCSV1 += ",EM.bread,EM.corn,EM.parrot,EM.totalscore";
            dataCSV2 += "," + GlobalVariables.m09Score[0];
            dataCSV2 += "," + GlobalVariables.m09Score[1];
            dataCSV2 += "," + GlobalVariables.m09Score[2];
            dataCSV2 += "," + m09Total;
        }

        if (GlobalVariables.modulesSelected[9]) {
            int m10FinalScore = 0;
            results10.setVisibility(View.VISIBLE);
            for (int i : GlobalVariables.m10Score) {
                m10FinalScore += i;
            }
            int m10WrongScoreTotal = GlobalVariables.m10WrongScore[0] + GlobalVariables.m10WrongScore[1] + GlobalVariables.m10WrongScore[2] + GlobalVariables.m10WrongScore[3] + GlobalVariables.m10WrongScore[4] + GlobalVariables.m10WrongScore[5];
            if (m10WrongScoreTotal > 0) {
                m10FinalScore -= m10WrongScoreTotal;
            }
            m10Score.setText(String.valueOf(m10FinalScore));

            data = "Task 10 " + getString(R.string.attention) + (System.getProperty("line.separator"));
            data += "Question 1: " + intToResult(GlobalVariables.m10Score[0]) + (System.getProperty("line.separator"));
            data += "Question 2: " + intToResult(GlobalVariables.m10Score[1]) + (System.getProperty("line.separator"));
            data += "Question 3: " + intToResult(GlobalVariables.m10Score[2]) + (System.getProperty("line.separator"));
            data += "Questions wrong: " + m10WrongScoreTotal + (System.getProperty("line.separator"));
            data += "" + (System.getProperty("line.separator"));
            SaveTextData(file, data);

            dataCSV1 += ",AA.today,AA.down,AA.hello,AA.goodbye,AA.and,AA.drink,AA.no,AA.up,AA.yes,AA.totalscore";
            dataCSV2 += "," + GlobalVariables.m10WrongScore[0];
            dataCSV2 += "," + GlobalVariables.m10Score[0];
            dataCSV2 += "," + GlobalVariables.m10WrongScore[1];
            dataCSV2 += "," + GlobalVariables.m10Score[1];
            dataCSV2 += "," + GlobalVariables.m10WrongScore[2];
            dataCSV2 += "," + GlobalVariables.m10WrongScore[3];
            dataCSV2 += "," + GlobalVariables.m10Score[2];
            dataCSV2 += "," + GlobalVariables.m10WrongScore[4];
            dataCSV2 += "," + GlobalVariables.m10WrongScore[5];
            dataCSV2 += "," + m10Total;
        }

        dataCSV1 += ",Totalscore" + (System.getProperty("line.separator"));
        SaveTextData(fileCSV, dataCSV1);
        dataCSV2 += "," + totalScore + (System.getProperty("line.separator"));
        SaveTextData(fileCSV, dataCSV2);
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
            vibrator.vibrate(100);
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

    private void setValuesCSV() {
        if (GlobalVariables.userSex.equalsIgnoreCase("male")) {
            csvSexMale = 1;
        } else if (GlobalVariables.userSex.equalsIgnoreCase("female")) {
            csvSexMale = 2;
        }

        m01Total = GlobalVariables.m01Score[0] + GlobalVariables.m01Score[1] + GlobalVariables.m01Score[2] + GlobalVariables.m01Score[3];
        m02Total = GlobalVariables.m02Score[0] + GlobalVariables.m02Score[1] + GlobalVariables.m02Score[2] + GlobalVariables.m02Score[3];
        if (GlobalVariables.m03QuestionNo == 1) {
            m03Total = GlobalVariables.m03ScoreQ1[0] + GlobalVariables.m03ScoreQ1[1] + GlobalVariables.m03ScoreQ1[2] + GlobalVariables.m03ScoreQ1[3] + GlobalVariables.m03ScoreQ1[4];
        } else if (GlobalVariables.m03QuestionNo == 2) {
            m03Total = GlobalVariables.m03ScoreQ2[0] + GlobalVariables.m03ScoreQ2[1] + GlobalVariables.m03ScoreQ2[2] + GlobalVariables.m03ScoreQ2[3] + GlobalVariables.m03ScoreQ2[4];
        }
        m04Total = GlobalVariables.m04Score[0] + GlobalVariables.m04Score[1] + GlobalVariables.m04Score[2] + GlobalVariables.m04Score[3];
        m05Total = GlobalVariables.m05Score[0] + GlobalVariables.m05Score[1];
        m06Total = GlobalVariables.m06Score[0] + GlobalVariables.m06Score[1] - GlobalVariables.m06Score[2];
        m07Total = GlobalVariables.m07Score[0] + GlobalVariables.m07Score[1] + GlobalVariables.m07Score[2] + GlobalVariables.m07Score[3];
        m08Total = GlobalVariables.m08ScoreQ1[0] + GlobalVariables.m08ScoreQ1[1] + GlobalVariables.m08ScoreQ1[2] + GlobalVariables.m08ScoreQ1[3] + GlobalVariables.m08ScoreQ1[4] + GlobalVariables.m08ScoreQ2[0] + GlobalVariables.m08ScoreQ2[1] + GlobalVariables.m08ScoreQ2[2] + GlobalVariables.m08ScoreQ2[3] + GlobalVariables.m08ScoreQ2[4];
        m09Total = GlobalVariables.m09Score[0] + GlobalVariables.m09Score[1] + GlobalVariables.m09Score[2];
        m10Total = GlobalVariables.m10Score[0] + GlobalVariables.m10Score[1] + GlobalVariables.m10Score[2];

        totalScore = m01Total + m02Total + m03Total + m04Total + m05Total + m06Total + m07Total + m08Total + m09Total + m10Total;
    }
}
