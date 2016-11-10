package hku.demscreen.hk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ModulesActivity extends AppCompatActivity {

    private String fileName = "01 - Tasks Selection";
    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modules);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        TextView userName = (TextView) findViewById(R.id.activity_modules_username);

        userName.setText(GlobalVariables.userName);

        GridView gridView = (GridView) findViewById(R.id.gridView);

        String[] moduleNames = getResources().getStringArray(R.array.module_names);

        ListAdapter adapter = new MyGridViewAdapter(this, moduleNames);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                vibrator.vibrate(100);
                ImageView tick = (ImageView) view.findViewById(R.id.card_tick);
                CardView moduleCardView = (CardView) view.findViewById(R.id.card_container);
                if (GlobalVariables.modulesSelected[i]) {
                    tick.setVisibility(View.GONE);
                    moduleCardView.setCardBackgroundColor(GlobalVariables.lghtGrayColorValue);
                    GlobalVariables.modulesSelected[i] = false;

                } else {
                    tick.setVisibility(View.VISIBLE);
                    moduleCardView.setCardBackgroundColor(GlobalVariables.whiteColorValue);
                    GlobalVariables.modulesSelected[i] = true;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_next) {
            View rootView = getWindow().getDecorView().getRootView();
            GlobalVariables.saveScreenshot(rootView, fileName);
            DateFormat df = new SimpleDateFormat("EEE d MMM yyyy");
            GlobalVariables.testDate = df.format(Calendar.getInstance().getTime());
            DateFormat df2 = new SimpleDateFormat("HH:mm:ss");
            GlobalVariables.testTimeStart = df2.format(Calendar.getInstance().getTime());
            Intent intentModulesActivity = new Intent(ModulesActivity.this, Module00Activity.class);
            ModulesActivity.this.startActivity(intentModulesActivity);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
