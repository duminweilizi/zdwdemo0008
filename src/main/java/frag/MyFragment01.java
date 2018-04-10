package frag;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lx011213.R;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;


import java.util.ArrayList;
import java.util.List;

import adapter.MyAdapter;
import bean.SuperClass;
import url.GetURL;

/**
 * Created by Administrator on 2018/1/13/013.
 */

public class MyFragment01 extends Fragment {

    private PullToRefreshListView plv;
    private String path = "http://gank.io/api/data/Android/10/";
    private int page;
    private List<SuperClass.ResultsBean> list = new ArrayList<>();
    private MyAdapter myAdapter;
    //使用handler进行关闭刷新
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.myfragment01, container, false);
        plv = view.findViewById(R.id.plv);
        init(0);
        myAdapter = new MyAdapter(list,getActivity());
        plv.setAdapter(myAdapter);
        plv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
           @Override
           public void onRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
               list.clear();
               page++;
               init(page);
               handler.postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       plv.onRefreshComplete();
                   }
               },100);
               //吐司
               Toast.makeText(getActivity(),"刷新",Toast.LENGTH_SHORT).show();
           }
        });
        plv.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
           @Override
           public void onLastItemVisible() {
                page++;
                init(page);
               //吐司
               Toast.makeText(getActivity(),"加载",Toast.LENGTH_SHORT).show();
           }
        });
        return view;
    }

    private void init(int page) {
        new MyAsy().execute(path+page);
    }

    private class MyAsy extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... strings) {
            String data = GetURL.getData(path + page);
            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Gson gson = new Gson();
            SuperClass superClass = gson.fromJson(s, SuperClass.class);
            List<SuperClass.ResultsBean> list1 = superClass.getResults();
            list.addAll(list1);
            myAdapter.notifyDataSetChanged();
        }
    }
}
