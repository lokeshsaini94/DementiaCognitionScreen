package hku.demscreen.hk;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import hku.demscreen.hk.Module01.M1Q1Fragment;
import hku.demscreen.hk.Module01.M1Q2Fragment;
import hku.demscreen.hk.Module01.M1Q3Fragment;
import hku.demscreen.hk.Module01.M1Q4Fragment;

public class Module01Activity extends AppCompatActivity {

    public static ViewPager viewPager;
    PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module01);

        viewPager = (ViewPager) findViewById(R.id.view_pager01);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_module_one);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Next Module will start.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    class PagerAdapter extends FragmentPagerAdapter {
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new M1Q1Fragment();
                case 1:
                    return new M1Q2Fragment();
                case 2:
                    return new M1Q3Fragment();
                case 3:
                    return new M1Q4Fragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
