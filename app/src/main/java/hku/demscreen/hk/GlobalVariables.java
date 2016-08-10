package hku.demscreen.hk;

import android.app.Application;
import android.graphics.Color;

public class GlobalVariables extends Application {
    public static int lghtGrayColorValue = Color.parseColor("#f5f5f5");
    public static String userName = "";
    public static int userAge = 0;
    public static String userID = "";
    public static String userSex = "";
    public static boolean[] modulesSelected = {true, true, true, true, true, true, true, true, true, true};
    public static int m01QuestionNo = 1;
    public static int[] m01Score = {0, 0, 0, 0};
    public static int m02QuestionNo = 1;
    public static int[] m02Score = {0, 0, 0, 0};
    public static int m03QuestionNo = 1;
    public static int[] m03Score = {0, 0, 0, 0, 0};
    public static int m04QuestionNo = 1;
    public static int[] m04Score = {0, 0, 0, 0};
}
