package com.example.mindgate.mpchart;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    TabLayout tabLayout;
    ViewPager viewPager;
    PagerAdapter pagerAdapter;
    Toolbar toolbar;
    AppCompatImageView addMoney;
    DatePickerDialog datePickerDialog;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    int hour,minute,years,month,day;
    private Date date;
    private Calendar myCalendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                day=dayOfMonth;
                years=year;
                month=monthOfYear;
                updateLabel();


            }
        };
        setViewPager();
    }

    private void updateLabel() {
        String myFormat = getString(R.string.month_year_format); //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
        String dateselected=sdf.format(myCalendar.getTime());
        Toast.makeText(this, "dateselected:"+dateselected, Toast.LENGTH_SHORT).show();
    }

    private void setViewPager() {
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void initView() {
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        addMoney= (AppCompatImageView) findViewById(R.id.addMoney);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.overView)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.insights)));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        pagerAdapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        setClickListener();
        toolbar.setTitle(getString(R.string.seller_mode));
    }

    @Override
    public void setClickListener() {
        addMoney.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addMoney:
                startActivity(new Intent(this,AddMoney.class));
                /*startActivity(new Intent(this,Dummy.class));*/
                //datePickerDialog.show();
                break;
        }
    }
}
