package com.example.activity_service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private Button set, unset;
    private RadioButton menit, lima, tigaplh, jam;
    private RadioGroup time;
    public int change = 60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        set = (Button) findViewById(R.id.btnset);
        unset = (Button) findViewById(R.id.btnunset);
        menit = (RadioButton) findViewById(R.id.r0);
        lima = (RadioButton) findViewById(R.id.r1);
        tigaplh = (RadioButton) findViewById(R.id.r2);
        jam = (RadioButton) findViewById(R.id.r3);
        time = (RadioGroup) findViewById(R.id.rg);

        unset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mDisable = new Intent(MainActivity.this,WallpaperChangeService.class);
                stopService(mDisable);
                finish();
            }
        });

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mRadioId = time.getCheckedRadioButtonId();
                if (menit.getId()==mRadioId){change=60;}
                else if (lima.getId()==mRadioId){change=5*60;}
                else if (tigaplh.getId()==mRadioId){change=30*60;}
                else if (jam.getId()==mRadioId){change=60*60;}

                Intent mServ = new Intent(MainActivity.this,WallpaperChangeService.class);
                Bundle bundle = new Bundle();
                bundle.putInt("durasi",change);
                mServ.putExtras(bundle);
                startService(mServ);
                finish();
            }
        });
    }
}
