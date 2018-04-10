package com.example.lx011213;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import java.util.ArrayList;

import fragment.Fragment01;
import fragment.Fragment02;
import fragment.Fragment03;
import fragment.Fragment04;

public class MainActivity extends AppCompatActivity {

    private FrameLayout fl;
    private RadioGroup rg;
    private ArrayList<Fragment> fragments;
    private Fragment01 fragment01;
    private Fragment02 fragment02;
    private Fragment03 fragment03;
    private Fragment04 fragment04;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取id
        initView();
        //定义一个list集合接受fragment
        fragments = new ArrayList<>();
        //实例化对象
        fragment01 = new Fragment01();
        fragment02 = new Fragment02();
        fragment03 = new Fragment03();
        fragment04 = new Fragment04();
        //把对象放入集合中
        fragments.add(fragment01);
        fragments.add(fragment02);
        fragments.add(fragment03);
        fragments.add(fragment04);
        //联动条件
        final FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fl,fragments.get(0)).commit();
        //联动
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rg1:
                        fragmentManager.beginTransaction().replace(R.id.fl,fragment01).commit();
                        break;
                    case R.id.rg2:
                        fragmentManager.beginTransaction().replace(R.id.fl,fragment02).commit();
                        break;
                    case R.id.rg3:
                        fragmentManager.beginTransaction().replace(R.id.fl,fragment03).commit();
                        break;
                    case R.id.rg4:
                        fragmentManager.beginTransaction().replace(R.id.fl,fragment04).commit();
                        break;
                }
            }
        });
    }

    private void initView() {
        fl = findViewById(R.id.fl);
        rg = findViewById(R.id.rg);
    }
}
