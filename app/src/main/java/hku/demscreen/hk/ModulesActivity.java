package hku.demscreen.hk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ModulesActivity extends AppCompatActivity {

    TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modules);

        userName = (TextView)findViewById(R.id.activity_modules_username);
        userName.setText(GlobalVariables.userName);
    }
}
