package hku.demscreen.hk;

import android.app.Application;
import android.graphics.Color;

public class GlobalVariables extends Application {
    public static int lghtGrayColorValue = Color.parseColor("#f5f5f5");
    public static int whiteColorValue = Color.parseColor("#ffffff");
    public static String userName = "";
    public static int userAge = 0;
    public static String userID = "";
    public static String userSex = "";
    public static boolean[] modulesSelected = {true, true, true, true, true, false, false, true, true, true};
    public static int m01QuestionNo = 1;
    public static int[] m01Score = {0, 0, 0, 0};
    public static int m02QuestionNo = 1;
    public static int[] m02Score = {0, 0, 0, 0};
    public static int m03QuestionNo = 1;
    public static int[] m03Score = {0, 0, 0, 0, 0};
    public static int m04QuestionNo = 1;
    public static int[] m04Score = {0, 0, 0, 0};
    public static int m05QuestionNo = 1;
    public static double[] m05Score = {0, 0};
    public static int m08QuestionNo = 1;
    public static int[] m08Score = {0, 0, 0, 0, 0};
    public static int m09QuestionNo = 1;
    public static int[] m09Score = {0, 0, 0};
    public static int m10QuestionNo = 1;
    public static int[] m10Score = {0, 0, 0};


    public static int m08CurrentMCQNo = 1;
    public static int[] m08MCQno = {0, 0, 0, 0, 0};
    public static int[] m08MCQcardChecked = {0, 0, 0, 0};
    public static int m10TeachQuestionNo = 0;
    public static int m10PracticeQuestionNo = 1;
    public static int m10TaskNo = 1;
    public static int m10WrongScore = 0;
    public static boolean m10YesDone = false;
}
