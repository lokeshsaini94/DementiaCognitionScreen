package hku.demscreen.hk;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

//    GlobalVariables globalVariables = (GlobalVariables) getApplicationContext();

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
                GlobalVariables.userSex = "Male";
            }
        });

        userFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userFemale.setError(null);
                GlobalVariables.userSex = "Female";
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mUserName = userName.getText().toString();
                String mUserAge = userAge.getText().toString();
                String mUserID = userId.getText().toString();

                if (mUserName.matches("")) {
                    userName.setError("Enter the patient's name");
                } else if (mUserAge.matches("")) {
                    userAge.setError("Enter the patient's age");
//                } else if (mUserID.matches("")) {
//                    userId.setError("Enter the patient's ID");
                } else if (userSex.getCheckedRadioButtonId() == -1) {
                    userFemale.setError("Enter the patient's Sex");
                } else {
                    GlobalVariables.userName = userName.getText().toString();
                    int x = Integer.parseInt(userAge.getText().toString());
                    GlobalVariables.userAge = x;
                    GlobalVariables.userID = userId.getText().toString();
                    Intent intentModulesActivity = new Intent(MainActivity.this, ModulesActivity.class);
                    MainActivity.this.startActivity(intentModulesActivity);
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
            // Handle the camera action
        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_feedback) {

        } else if (id == R.id.nav_aboutus) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
