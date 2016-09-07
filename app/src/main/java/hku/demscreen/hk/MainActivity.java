package hku.demscreen.hk;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ayz4sci.androidfactory.permissionhelper.PermissionHelper;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import pl.tajchert.nammu.PermissionCallback;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EditText userName;
    EditText userAge;
    EditText userId;
    RadioButton userMale;
    RadioButton userFemale;
    RadioGroup userSex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText) findViewById(R.id.user_name);
        userAge = (EditText) findViewById(R.id.user_age);
        userId = (EditText) findViewById(R.id.user_id);
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
                String mUserName = userName.getText().toString();
                String mUserAge = userAge.getText().toString();
                Boolean nameEmpty = mUserName.matches("");
                Boolean ageEmpty = mUserAge.matches("");
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
                            .playOn(findViewById(R.id.age_text_input_layout));
                    userAge.setError(getString(R.string.java_input_age));
                }
                if (sexEmpty) {
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .playOn(findViewById(R.id.sex_radio_group_text));
                    userFemale.setError(getString(R.string.java_input_sex));
                }
                if (!nameEmpty && !ageEmpty && !sexEmpty) {
                    GlobalVariables.userName = userName.getText().toString();
                    int x = Integer.parseInt(userAge.getText().toString());
                    GlobalVariables.userAge = x;
                    GlobalVariables.userID = userId.getText().toString();
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

        if (id == R.id.nav_tutorial) {
            // Handle the item action
        } else if (id == R.id.nav_settings) {
            // Handle the item action
        } else if (id == R.id.nav_feedback) {
            // Handle the item action
        } else if (id == R.id.nav_aboutus) {
            // Handle the item action
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

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
}
