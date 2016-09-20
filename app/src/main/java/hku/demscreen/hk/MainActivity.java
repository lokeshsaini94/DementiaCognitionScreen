package hku.demscreen.hk;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ayz4sci.androidfactory.permissionhelper.PermissionHelper;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.Locale;

import pl.tajchert.nammu.PermissionCallback;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static final int DATE_PICKER_DIALOG_ID = 0;
    static final int LANGUAGE_DIALOG_ID = 1;
    final CharSequence[] language_radio = {"English", "Chinese"};
    Locale myLocale;

    int mYear, mMonth, mDay;
    EditText userName;
    EditText userId;
    TextView userAge;
    RadioButton userMale;
    RadioButton userFemale;
    RadioGroup userSex;

    // Gets and sets date
    private DatePickerDialog.OnDateSetListener dPickerListner = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            mYear = i;
            mMonth = i1 + 1;
            mDay = i2;
            userAge.setText(mDay + "/" + mMonth + "/" + mYear);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText) findViewById(R.id.user_name);
        userAge = (TextView) findViewById(R.id.user_age);
        userId = (EditText) findViewById(R.id.user_id);
        userMale = (RadioButton) findViewById(R.id.radioMale);
        userFemale = (RadioButton) findViewById(R.id.radioFemale);
        userSex = (RadioGroup) findViewById(R.id.radioSex);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        userAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_PICKER_DIALOG_ID);
            }
        });

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
                String mUserName = userName.getText().toString();
                String mUserAge = userAge.getText().toString();
                String mUserId = userId.getText().toString();
                Boolean nameEmpty = mUserName.matches("");
                Boolean ageEmpty = mUserAge.matches("DD/MM/YYYY");
                Boolean idEmpty = mUserId.matches("");
                Boolean sexEmpty = (userSex.getCheckedRadioButtonId() == -1);

                if (nameEmpty) {
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .playOn(findViewById(R.id.name_text_input_layout));
                    userName.setError(getString(R.string.java_input_name));
                }
                if (ageEmpty) {
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .playOn(findViewById(R.id.age_group_text));
                    userAge.setError(getString(R.string.java_input_age));
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
                if (!nameEmpty && !ageEmpty && !sexEmpty && !idEmpty) {
                    GlobalVariables.userName = userName.getText().toString();
                    GlobalVariables.userInitials = getInitials(GlobalVariables.userName);
                    GlobalVariables.userID = userId.getText().toString();
                    GlobalVariables.userAge = mDay + "/" + mMonth + "/" + mYear;
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

        requestPermission();
    }

    // Created Dialog for DatePicker and Language selector
    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DATE_PICKER_DIALOG_ID) {
            return new DatePickerDialog(this, dPickerListner, mYear, mMonth, mDay);
        } else if (id == LANGUAGE_DIALOG_ID) {
            AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Select the Language")
                    .setSingleChoiceItems(language_radio, -1, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            if (language_radio[which] == "English") {
                                setLocale("en");
                            } else if (language_radio[which] == "Chinese") {
                                setLocale("zh");
                            }
                            Toast.makeText(getApplicationContext(),language_radio[which] + " language selected.", Toast.LENGTH_SHORT).show();
                            //dismissing the dialog when the user makes a selection.
                            dialog.dismiss();
                        }
                    });
            AlertDialog alertdialog2 = builder2.create();
            return alertdialog2;
        } else {
            return null;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        userName.setText(GlobalVariables.userName);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
            // Handle the item action
        } else if (id == R.id.nav_aboutus) {
            // Handle the item action
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // Sets Locale (Language for the app)
    public void setLocale(String lang) {
        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
//        Intent refresh = new Intent(this, MainActivity.class);
//        startActivity(refresh);
//        finish();
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
