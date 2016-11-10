package hku.demscreen.hk;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ayz4sci.androidfactory.permissionhelper.PermissionHelper;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.Locale;

import pl.tajchert.nammu.PermissionCallback;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int LANGUAGE_DIALOG_ID = 1;
    private final CharSequence[] language_radio = {"English", "Chinese", "Russian"};
    boolean doubleBackToExitPressedOnce = false;
    private Vibrator vibrator;
    private EditText userName, userId, userEdu;
    private EditText userAgeDate, userAgeMonth, userAgeYear;
    private RadioButton userMale, userFemale;
    private RadioGroup userSex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        userName = (EditText) findViewById(R.id.user_name);
        userAgeDate = (EditText) findViewById(R.id.user_age_DD);
        userAgeMonth = (EditText) findViewById(R.id.user_age_MM);
        userAgeYear = (EditText) findViewById(R.id.user_age_YYYY);
        userId = (EditText) findViewById(R.id.user_id);
        userEdu = (EditText) findViewById(R.id.user_edu);
        userMale = (RadioButton) findViewById(R.id.radioMale);
        userFemale = (RadioButton) findViewById(R.id.radioFemale);
        userSex = (RadioGroup) findViewById(R.id.radioSex);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        userMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userFemale.setError(null);
                GlobalVariables.userSex = getString(R.string.male);
            }
        });

        userFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userFemale.setError(null);
                GlobalVariables.userSex = getString(R.string.female);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(100);
                Boolean nameEmpty = userName.getText().toString().matches("");
                Boolean ageDDEmpty = userAgeDate.getText().toString().matches("");
                Boolean ageMMEmpty = userAgeMonth.getText().toString().matches("");
                Boolean ageYYYYEmpty = userAgeYear.getText().toString().matches("");
                Boolean idEmpty = userId.getText().toString().matches("");
                Boolean sexEmpty = (userSex.getCheckedRadioButtonId() == -1);

                if (nameEmpty) {
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .playOn(findViewById(R.id.name_text_input_layout));
                    userName.setError(getString(R.string.java_input_name));
                }
                if (ageDDEmpty) {
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .playOn(findViewById(R.id.age_group_text));
                    userAgeDate.setError(getString(R.string.java_input_age));
                }
                if (ageMMEmpty) {
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .playOn(findViewById(R.id.age_group_text));
                    userAgeMonth.setError(getString(R.string.java_input_age));
                }
                if (ageYYYYEmpty) {
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .playOn(findViewById(R.id.age_group_text));
                    userAgeYear.setError(getString(R.string.java_input_age));
                }
                if (idEmpty) {
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .playOn(findViewById(R.id.id_text_input_layout));
                    userId.setError(getString(R.string.java_input_id));
                }
                if (sexEmpty) {
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .playOn(findViewById(R.id.sex_radio_group_text));
                    userFemale.setError(getString(R.string.java_input_sex));
                }
                if (!nameEmpty && !ageDDEmpty && !ageMMEmpty && !ageYYYYEmpty && !sexEmpty && !idEmpty) {
                    GlobalVariables.userName = userName.getText().toString();
                    GlobalVariables.userAgeDate = userAgeDate.getText().toString();
                    GlobalVariables.userAgeMonth = userAgeMonth.getText().toString();
                    GlobalVariables.userAgeYear = userAgeYear.getText().toString();
                    GlobalVariables.userAge = GlobalVariables.userAgeDate + "/" + GlobalVariables.userAgeMonth + "/" + GlobalVariables.userAgeYear;
                    GlobalVariables.userInitials = getInitials(GlobalVariables.userName);
                    GlobalVariables.userID = userId.getText().toString();
                    String xyz = userEdu.getText().toString();
                    if (!(xyz == "")) {
                        GlobalVariables.userEdu = xyz;
                    }
                    Intent intentModulesActivity = new Intent(MainActivity.this, ModulesActivity.class);
                    MainActivity.this.startActivity(intentModulesActivity);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SharedPreferences sharedPref = getSharedPreferences("mypref", 0);
        int langIndex = sharedPref.getInt("language", 0);
        setLanguage(langIndex);

        requestPermission();
    }

    // Created Dialog for DatePicker and Language selector
    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == LANGUAGE_DIALOG_ID) {
            SharedPreferences sharedPref = getSharedPreferences("mypref", 0);
            int langIndex = sharedPref.getInt("language", 0);
            AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Select the Language")
                    .setSingleChoiceItems(language_radio, langIndex, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int langIndex) {
                            setLanguage(langIndex);

                            // Create object of SharedPreferences.
                            SharedPreferences sharedPref = getSharedPreferences("mypref", 0);
                            //now get Editor
                            SharedPreferences.Editor editor = sharedPref.edit();
                            //put your value
                            editor.putInt("language", langIndex);
                            //commits your edits
                            editor.commit();

                            Toast.makeText(getApplicationContext(), language_radio[langIndex] + " language selected.", Toast.LENGTH_SHORT).show();
                            //dismissing the dialog when the user makes a selection.
                            dialog.dismiss();
                        }
                    });
            return builder2.create();
        } else {
            return null;
        }
    }

    // Sets Language Index and starts setLocale to set the language.
    private void setLanguage(int langIndex) {
        if (language_radio[langIndex] == "English") {
            GlobalVariables.testLanguage = 1;
            GlobalVariables.testLanguageString = (String) language_radio[langIndex];
            setLocale("en");
        } else if (language_radio[langIndex] == "Chinese") {
            GlobalVariables.testLanguage = 2;
            GlobalVariables.testLanguageString = (String) language_radio[langIndex];
            setLocale("zh");
        } else if (language_radio[langIndex] == "Russian") {
            GlobalVariables.testLanguage = 3;
            GlobalVariables.testLanguageString = (String) language_radio[langIndex];
            setLocale("ru");
        }
    }

    // Sets Locale (Language for the app)
    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
//        Intent refresh = new Intent(this, MainActivity.class);
//        startActivity(refresh);
//        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        userName.setText(GlobalVariables.userName);
        userId.setText(GlobalVariables.userID);
        userEdu.setText(GlobalVariables.userEdu);
        userAgeDate.setText(GlobalVariables.userAgeDate);
        userAgeMonth.setText(GlobalVariables.userAgeMonth);
        userAgeYear.setText(GlobalVariables.userAgeYear);
        userAgeYear.setText(GlobalVariables.userAgeYear);
        if (GlobalVariables.userSex == getString(R.string.male)) {
            userMale.setChecked(true);
            userFemale.setChecked(false);
        } else if (GlobalVariables.userSex == getString(R.string.female)) {
            userMale.setChecked(false);
            userFemale.setChecked(true);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_language) {
            showDialog(LANGUAGE_DIALOG_ID);
        } else if (id == R.id.nav_feedback) {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.feedback_email)});
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        } else if (id == R.id.nav_aboutus) {
            Intent intentModulesActivity = new Intent(MainActivity.this, AboutActivity.class);
            MainActivity.this.startActivity(intentModulesActivity);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Requests required Permissions
    private void requestPermission() {
        final PermissionHelper permissionHelper = PermissionHelper.getInstance(this);
        permissionHelper.verifyPermission(
                new String[]{getString(R.string.java_permission_write_storage), getString(R.string.java_permission_record_audio)},
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO},
                new PermissionCallback() {

                    @Override
                    public void permissionGranted() {
//                        Toast.makeText(getApplicationContext(), "Great!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void permissionRefused() {
                        requestPermission();
                    }
                }
        );
        permissionHelper.customiseUI(R.color.color_primary, ContextCompat.getDrawable(getApplicationContext(), R.mipmap.ic_launcher));
        permissionHelper.getNotAllowButton().setVisibility(View.INVISIBLE);
        permissionHelper.getBackButton().setVisibility(View.INVISIBLE);
    }

    // Returns Initials of the name
    public String getInitials(String text) {
        String initials = "";
        text = text.replaceAll("[.,]", ""); // Replace dots, etc (optional)
        for (String s : text.split(" ")) {
            initials += s.charAt(0);
        }
        return initials;
    }
}
