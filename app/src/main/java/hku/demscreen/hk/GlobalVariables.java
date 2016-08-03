package hku.demscreen.hk;

import android.app.Application;

public class GlobalVariables extends Application {
    public static String userName = "";
    public static int userAge = 0;
    public static String userID = "";
    public static String userSex = "";
    public static boolean[] modulesSelected = {true, true, true, true, true, true, true, true, true, true};
    public static int[] m1Score = {0, 0, 0, 0};

    public static void m1PreviousPage() {
        Module01Activity.viewPager.setCurrentItem(getItem(-1), true);
    }

    public static void m1NextPage() {
        Module01Activity.viewPager.setCurrentItem(getItem(+1), true);
    }

    private static int getItem(int i) {
        return Module01Activity.viewPager.getCurrentItem() + i;
    }
}
