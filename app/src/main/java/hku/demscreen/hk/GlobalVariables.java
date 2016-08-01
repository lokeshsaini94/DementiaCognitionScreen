package hku.demscreen.hk;

import android.app.Application;

/**
 * Created by lokeshsaini94 on 30-07-2016.
 */
public class GlobalVariables extends Application {
    public static String userName = "Username";
    public static int userAge = 0;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }
}
