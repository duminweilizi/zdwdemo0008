package frag;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.lx011213.R;
import com.google.gson.Gson;

import java.util.List;

import adapter.MyAdapter;
import bean.SuperClass;
import url.GetURL;

/**
 * Created by Administrator on 2018/1/13/013.
 */

public class MyFragment extends Fragment {

    private ListView lv;
    private String path;

    @SuppressLint("StaticFieldLeak")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.myfragment_item, container, false);
        Bundle bundle = getArguments();
        lv = view.findViewById(R.id.lv);
        path = bundle.getString("title");
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String data = GetURL.getData(path);
                return data;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Gson gson = new Gson();
                SuperClass superClass = gson.fromJson(s, SuperClass.class);
                List<SuperClass.ResultsBean> list = superClass.getResults();
                lv.setAdapter(new MyAdapter(list,getActivity()));
            }
        }.execute();
        return view;
    }
}
