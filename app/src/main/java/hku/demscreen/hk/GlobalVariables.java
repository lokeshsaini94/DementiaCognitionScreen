package hku.demscreen.hk;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Environment;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class GlobalVariables extends Application {
    public static int lghtGrayColorValue = Color.parseColor("#f5f5f5");
    public static int whiteColorValue = Color.parseColor("#ffffff");
    public static int testLanguage = 1;
    public static String testLanguageString = "English";
    public static String testDate = "";
    public static String testTimeStart = "";
    public static String testTimeEnd = "";
    public static String userName = "";
    public static String userInitials = "";
    public static String userAge = "";
    public static String userAgeDate = "";
    public static String userAgeMonth = "";
    public static String userAgeYear = "";
    public static String userID = "";
    public static String userEdu = "";
    public static String userSex = "";
    public static boolean[] modulesSelected = {true, true, true, true, true, true, true, true, true, true};
    public static int m01QuestionNo = 1;
    public static int[] m01Score = {0, 0, 0, 0};
    public static int m02QuestionNo = 1;
    public static int[] m02Score = {0, 0, 0, 0};
    public static int m03QuestionNo = 1;
    public static int[] m03ScoreQ1 = {0, 0, 0, 0, 0};
    public static int[] m03ScoreQ2 = {0, 0, 0, 0, 0};
    public static int m04QuestionNo = 1;
    public static int[] m04Score = {0, 0, 0, 0};
    public static int m05QuestionNo = 1;
    public static double[] m05Score = {0, 0};
    public static int m06QuestionNo = 1;
    public static int[] m06Score = {0, 0, 0};
    public static int m07QuestionNo = 0;
    public static int[] m07Score = {0, 0, 0, 0};
    public static int m08QuestionNo = 1;
    public static int[] m08ScoreQ1 = {0, 0, 0, 0, 0};
    public static int[] m08ScoreQ2 = {0, 0, 0, 0, 0};
    public static int m09QuestionNo = 1;
    public static int[] m09Score = {0, 0, 0};
    public static int m10QuestionNo = 1;
    public static int[] m10Score = {0, 0, 0};


    public static String[] m05TimeTaken = {"", ""};
    public static String[] m06TimeTaken = {"", "", ""};
    public static int m07MovesCount = 0;
    public static int m07CurrentFigure = 0;
    public static int m07PreviousFigure = 0;
    public static boolean[] m07TappedFigure = new boolean[35];
    public static int[] m07TappedFigureInt = new int[34];
    public static int m07Question2Rule = 0;
    public static int m07Question3Rule = 0;
    public static int m07Question4Rule = 0;
    public static int m08CurrentMCQNo = 1;
    public static int[] m08MCQno = {0, 0, 0, 0, 0};
    public static int[] m08MCQcardChecked = {0, 0, 0, 0};
    public static int m10TeachQuestionNo = 0;
    public static int m10PracticeQuestionNo = 1;
    public static int m10TaskNo = 1;
    public static int[] m10WrongScore = {0, 0, 0, 0, 0, 0};
    public static boolean m10YesDone = false;

    // Save screenshot of RootView and filename passed
    public static void saveScreenshot(View rootView, String fileName) {
        rootView.invalidate();  //I try to redraw the view without success
        rootView.requestLayout();
        rootView.setDrawingCacheEnabled(true);
        Bitmap bitmap = rootView.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Dementia Cognition Screen/" + GlobalVariables.userID + "_" + GlobalVariables.userInitials + "/";
        File file = new File(path + fileName + ".jpeg");
        file.getParentFile().mkdirs(); //if the folder doesn't exists it's created

        try {
            boolean asd = file.createNewFile();
            FileOutputStream ostream = new FileOutputStream(file);
            ostream.write(baos.toByteArray());
            ostream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
