package com.example.mindgate.mpchart;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import static com.example.mindgate.mpchart.R.id.saveData;

public class AddMoney extends BaseActivity implements View.OnClickListener {
    AppCompatImageView addMoneyImageView,saveData;
    AppCompatEditText editText1,editText2,editText3,editText4,editText5,editText6,editText7;
    int totalmoney,amount1,amount2,amount3,amount4,amount5,amount6,amount7;
    AppCompatTextView totalAmount,getTotalAmount;
    private SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmoney);
        initView();
    }

    @Override
    public void initView() {
        addMoneyImageView= (AppCompatImageView) findViewById(R.id.saveData);
        editText1= (AppCompatEditText) findViewById(R.id.amountEditText1);
        editText2= (AppCompatEditText) findViewById(R.id.amountEditText2);
        editText3= (AppCompatEditText) findViewById(R.id.amountEditText3);
        editText4= (AppCompatEditText) findViewById(R.id.amountEditText4);
        editText5= (AppCompatEditText) findViewById(R.id.amountEditText5);
        editText6= (AppCompatEditText) findViewById(R.id.amountEditText6);
        editText7= (AppCompatEditText) findViewById(R.id.amountEditText7);
        totalAmount= (AppCompatTextView) findViewById(R.id.totalAmount);
        getTotalAmount= (AppCompatTextView) findViewById(R.id.total);
        saveData= (AppCompatImageView) findViewById(R.id.saveData);
        sharedPreferences = getSharedPreferences("Week amount", Context.MODE_PRIVATE);
        editor= sharedPreferences.edit();
        setClickListener();
    }

    @Override
    public void setClickListener() {
        getTotalAmount.setOnClickListener(this);
        saveData.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.saveData:
                saveData();
                startActivity(new Intent(this,MainActivity.class));
                finish();
                //getTotalAmount.performClick();
                break;
            case R.id.total:
                Amountmodel amountmodel=new Amountmodel();
                //amount1=amountmodel.setMondayAmount(Integer.parseInt((editText1.getText().toString())));
                amount1=Integer.parseInt(editText1.getText().toString());
                amount2=Integer.parseInt(editText2.getText().toString());
                amount3=Integer.parseInt(editText3.getText().toString());
                amount4=Integer.parseInt(editText4.getText().toString());
                amount5=Integer.parseInt(editText5.getText().toString());
                amount6=Integer.parseInt(editText6.getText().toString());
                amount7=Integer.parseInt(editText7.getText().toString());
                totalmoney=amount1+amount2+amount3+amount4+amount5+amount6+amount7;
                totalAmount.setText(totalmoney+"");
                break;

        }
    }

    private void saveData() {
        editor.putInt("mondayAmount",amount1);
        editor.putInt("tuesdayAmount",amount2);
        editor.putInt("wednesdayAmount",amount3);
        editor.putInt("thursdayAmount",amount4);
        editor.putInt("fridayAmount",amount5);
        editor.putInt("saturdayAmount",amount6);
        editor.putInt("sundayAmount",amount7);
        editor.putInt("totalAmount",totalmoney);
        editor.apply();
    }
}
