package com.yaolaizai.ylzx.quickindex;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends Activity {
    private ListView lvMain;
    private TextView tvMainWord;
    private QuickIndex qiMain;

    private List<Person> data;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvMain = (ListView) findViewById(R.id.lv_main);
        tvMainWord = (TextView) findViewById(R.id.tv_main_word);
        qiMain = (QuickIndex) findViewById(R.id.qi_main);
        initData();

        qiMain.setOnIndexChangedListener(new QuickIndex.OnIndexChangedListener() {
            @Override
            public void onIndexChanged(String word) {

                //移除未被处理的消息
                handler.removeCallbacksAndMessages(null);

                tvMainWord.setVisibility(View.VISIBLE);
                tvMainWord.setText(word);

                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getPinYin().substring(0, 1).equals(word)) {
                        //让position = i的元素显示在列表的开头
                        lvMain.setSelection(i);
                        return;
                    }
                }
            }

            @Override
            public void onUp() {
                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        tvMainWord.setVisibility(View.GONE);
                    }
                }, 2000);
            }
        });


        lvMain.setAdapter(new MyAdapter());


    }

class MyAdapter extends BaseAdapter{


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView= LayoutInflater.from(MainActivity.this).inflate(R.layout.item_main, null);

            viewHolder.tv_item_word = (TextView) convertView.findViewById(R.id.tv_item_word);
            viewHolder.tv_item_name = (TextView) convertView.findViewById(R.id.tv_item_name);
            convertView.setTag(viewHolder);// 通过setTag将ViewHolder和convertView绑定
        }  else {
            viewHolder = (ViewHolder) convertView.getTag(); // 获取，通过ViewHolder找到相应的控件
        }

        viewHolder.tv_item_word.setText(data.get(position).getPinYin().substring(0,1));
        viewHolder.tv_item_name.setText(data.get(position).getName());

        if(position == 0){
            viewHolder.tv_item_word.setVisibility(View.VISIBLE);
        }else{
            //得到当前position对应的person的首字符
            String currChar = data.get(position).getPinYin().substring(0,1);
            //得到上一个position对应的person的首字符
            String preChar = data.get(position - 1).getPinYin().substring(0,1);

            if(currChar.equals(preChar)){
                viewHolder.tv_item_word.setVisibility(View.GONE);
            }else{
                viewHolder.tv_item_word.setVisibility(View.VISIBLE);

            }
        }

        return convertView;
    }


    class ViewHolder {
        TextView tv_item_word;
        TextView tv_item_name;
    }
}




    private void initData() {
        data = new ArrayList<Person>();
        // 虚拟数据
        data.add(new Person("张三"));
        data.add(new Person("张三"));
        data.add(new Person("张三"));
        data.add(new Person("张三"));
        data.add(new Person("张三"));
        data.add(new Person("李四"));
        data.add(new Person("李四"));
        data.add(new Person("李四"));
        data.add(new Person("李四"));
        data.add(new Person("李四"));
        data.add(new Person("王二"));
        data.add(new Person("王二"));
        data.add(new Person("王二"));
        data.add(new Person("王二"));
        data.add(new Person("王二"));
        data.add(new Person("王二"));
        data.add(new Person("麻子"));
        data.add(new Person("麻子"));
        data.add(new Person("麻子"));
        data.add(new Person("麻子"));

        data.add(new Person("阿三"));
        data.add(new Person("阿三"));
        data.add(new Person("阿三"));
        data.add(new Person("阿三"));
        data.add(new Person("阿三"));

        //进行排序
        Collections.sort(data);
    }

}
