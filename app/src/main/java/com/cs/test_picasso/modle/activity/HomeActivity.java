package com.cs.test_picasso.modle.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.cs.test_picasso.adapter.MyAdapter;
import com.cs.test_picasso.R;
import com.cs.test_picasso.entity.News;
import com.cs.test_picasso.utils.OkMannager;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenshuai on 2016/10/22.
 */

public class HomeActivity extends Activity {
    private ListView listview;
    private MyAdapter myAdapter;
    private OkMannager mannager;
    private ProgressDialog dialog;
    private final String COOK="http://www.tngou.net/api/cook/list";
    private List<News.TngouBean> mlist=new ArrayList<News.TngouBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

    }

    private void initData() {




    }

    private void initView() {
        listview = (ListView) findViewById(R.id.listview);
        mannager=OkMannager.getInstance();
        mannager.asyncJsonStringByURL(COOK, new OkMannager.Funcl() {
            @Override
            public void onResponse(String result) {
                Log.d("TAG",result);
                Gson gson=new Gson();
                News news = gson.fromJson(result, News.class);
                if (news.isStatus()) {
                    mlist.addAll(news.getTngou());
                    myAdapter.notifyDataSetChanged();//异步任务里面必须要实现的方法

                }




            }
        });

        myAdapter=new MyAdapter(this,mlist);

        listview.setAdapter(myAdapter);
        dialog=new ProgressDialog(this);
        dialog.setTitle("loading........");

    }

    /**
     * Params ，参数的类型发送给在执行该任务。
       Progress ，背景计算期间发布的进展单元的类型。进度条
       Result ，背景计算的结果的类型。 我们需要传递的类型
     */
    class Mytask extends AsyncTask<String ,Void,List<News.TngouBean>>{
        /**
         * 在执行任务前，调用UI线程上。 此步骤通常用于设置任务，例如通过在用户界面中显示进度条。
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        /**
         * 立即在后台线程调用onPreExecute()完成执行。 此步骤用于执行可能需要很长时间的后台计算。 异步任务的参数传递到此步骤
         * @param strings
         * @return
         */
        @Override
        protected List<News.TngouBean> doInBackground(String... strings) {

            return null;
        }

        /**
         * 通话结束后在UI线程调用publishProgress(Progress...) 执行的时间未定义。 此方法用于在后台计算仍在执行时在用户界面中显示任何形式的进度。 例如，它可以用于动画进度条或在文本字段中显示日志。
         * @param values
         */
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        /**
         * 调用后台计算结束后在UI线程上。 背景计算的结果作为参数传递到该步骤。
         * @param tngouBeen
         */
        @Override
        protected void onPostExecute(List<News.TngouBean> tngouBeen) {
            super.onPostExecute(tngouBeen);
            dialog.dismiss();

        }
    }
}
