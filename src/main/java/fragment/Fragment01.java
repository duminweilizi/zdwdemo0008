package fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lx011213.R;

import java.util.ArrayList;

import frag.MyFragment;
import frag.MyFragment01;

/**
 * Created by Administrator on 2018/1/13/013.
 */

public class Fragment01 extends Fragment implements View.OnClickListener{

    private String path = "http://gank.io/api/data/Android/10/";
    private int page;
    private HorizontalScrollView hsv;
    private LinearLayout ll;
    private ViewPager vp;
    //导航栏数据
    private String[] strings = new String[]{"关注", "推荐", "科技", "视频", "数码", "汽车", "福利", "Android",
            "iOS", "休息视频"};
    private ArrayList<TextView> textViews;
    private ArrayList<Fragment> fragments;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment01, container, false);
        //获取id
        hsv = view.findViewById(R.id.hsv);
        ll = view.findViewById(R.id.ll);
        vp = view.findViewById(R.id.vp);
        //设置导航栏颜色和字体大小与边距
        init();
        //定义一个集合放入导航栏展示的数据
        fragments = new ArrayList<>();
        //设置独立的有特殊要求的数据
        MyFragment01 myFragment01 = new MyFragment01();
        fragments.add(myFragment01);
        //设置数据
        for (int i = 1; i < 10; i++) {
            MyFragment myFragment = new MyFragment();
            Bundle bundle = new Bundle();
            //每个页面显示的数据都不同
            bundle.putString("title",path+(i+1));
            myFragment.setArguments(bundle);
            fragments.add(myFragment);
        }
        //设置适配器
        vp.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        //设置导航栏文字点击变色，未选中不变色
        into();
        return view;
    }

    //设置点击变色
    private void into() {
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //使用for循环，有几个fragment就会判断几个
                for (int i = 0; i < fragments.size(); i++) {
                    //选中时颜色
                    if (i == position){
                        textViews.get(i).setTextColor(Color.RED);
                    }else{
                        //未选中时颜色
                        textViews.get(i).setTextColor(Color.BLACK);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void init() {
        //定义一个textview集合放入数据
        textViews = new ArrayList<>();
        //使用for循环给导航栏字体添加颜色
        for (int i = 0; i < strings.length; i++) {
            TextView tv = new TextView(getActivity());
            tv.setText(strings[i]);
            tv.setTextSize(25);
            //默认选中第一个
            if (i == 0) {
                tv.setTextColor(Color.RED);
            }
            //导航栏的宽高
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            //给导航栏设置边距
            params.setMargins(10,10,10,10);
            //获取数据
            tv.setId(i);
            //导航栏点击事件
            tv.setOnClickListener(this);
            //把数据放入集合中
            textViews.add(tv);
            //添加到导航栏
            ll.addView(tv,params);
        }
    }

    //点击事件,联动
    @Override
    public void onClick(View view) {
        int id = view.getId();
        vp.setCurrentItem(id);
    }
}
