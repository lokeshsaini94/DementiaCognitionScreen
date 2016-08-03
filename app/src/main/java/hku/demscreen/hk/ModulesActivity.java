package hku.demscreen.hk;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ModulesActivity extends AppCompatActivity {

    TextView userName;
    GridView gridView;
    String[] moduleNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modules);

        userName = (TextView) findViewById(R.id.activity_modules_username);

        userName.setText(GlobalVariables.userName);

        gridView = (GridView) findViewById(R.id.gridView);

        moduleNames = getResources().getStringArray(R.array.module_names);

        ListAdapter adapter = new MyAdapter(this, moduleNames);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                View tick = view.findViewById(R.id.card_tick);
                View card = view.findViewById(R.id.card_container);
                if (GlobalVariables.modulesSelected[i]) {
                    tick.setVisibility(View.GONE);
                    card.setBackgroundColor(getResources().getColor(R.color.light_grey));
                    GlobalVariables.modulesSelected[i] = false;

                } else {
                    tick.setVisibility(View.VISIBLE);
                    card.setBackgroundColor(getResources().getColor(R.color.white));
                    GlobalVariables.modulesSelected[i] = true;
                }
            }
        });

        Toast.makeText(getApplicationContext(), "Name: " + GlobalVariables.userName + ", Age: " + GlobalVariables.userAge + ", ID:" + GlobalVariables.userID + ", Sex:" + GlobalVariables.userSex, Toast.LENGTH_LONG).show();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_modules);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentModulesActivity = new Intent(ModulesActivity.this, Module01Activity.class);
                ModulesActivity.this.startActivity(intentModulesActivity);
            }
        });
    }
}
