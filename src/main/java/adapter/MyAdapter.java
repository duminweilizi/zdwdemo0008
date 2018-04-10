package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lx011213.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import bean.SuperClass;

/**
 * Created by Administrator on 2018/1/14/014.
 */

public class MyAdapter extends BaseAdapter {
    List<SuperClass.ResultsBean> list;
    Context context;
    private ViewHolder1 holder1;
    private ViewHolder2 holder2;

    public MyAdapter(List<SuperClass.ResultsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getImages()!=null&&!list.get(position).getImages().equals("")){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public int getViewTypeCount() {
       return 2;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        int type = getItemViewType(i);
        if (type == 0){
            if (view == null){
                view = View.inflate(context, R.layout.item1,null);
                holder1 = new ViewHolder1();
                holder1.tv1 = view.findViewById(R.id.tv1);
                holder1.tv2 = view.findViewById(R.id.tv2);
                view.setTag(holder1);
            }else{
                holder1 = (ViewHolder1) view.getTag();
            }
            holder1.tv1.setText(list.get(i).getCreatedAt());
            holder1.tv2.setText(list.get(i).getDesc());
        }else{
            if (view == null){
                view = View.inflate(context, R.layout.item2,null);
                holder2 = new MyAdapter.ViewHolder2();
                holder2.tvv1 = view.findViewById(R.id.tvv1);
                holder2.tvv2 = view.findViewById(R.id.tvv2);
                holder2.img = view.findViewById(R.id.img);
                view.setTag(holder2);
            }else{
                holder2 = (ViewHolder2) view.getTag();
            }
            holder2.tvv1.setText(list.get(i).getCreatedAt());
            holder2.tvv2.setText(list.get(i).getDesc());
//            String s = list.get(i).getImages().get(0);
//            if (s == null){
//
//            }else{
                ImageLoader.getInstance().displayImage(list.get(i).getImages().get(0),holder2.img);
//            }
        }
        return view;
    }
    class ViewHolder1{
        TextView tv1,tv2;
    }
    class ViewHolder2{
        TextView tvv1,tvv2;
        ImageView img;
    }
}
